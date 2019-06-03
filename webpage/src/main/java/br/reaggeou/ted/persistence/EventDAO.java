package br.reaggeou.ted.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.reaggeou.ted.model.AwsEmailSevice;
import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.Event;
import br.reaggeou.ted.model.EventSource;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.util.ConnectionBD;

public class EventDAO {

	private ConnectionBD connectionWB;

	private static final String SQL_SELECT_EVENTS = "SELECT id_event​, title​, description​, href​, local​, ​date​​,​ time​, folder​, vendor​, id_category FROM EVENTS";
	private static final String SQL_SELECT_USER_EVENTS = "SELECT href, title, description, local, date, folder FROM events WHERE id_category=? AND vendor = 'Sympla' AND created_at > NOW() - INTERVAL '1 DAY' AND created_at <= NOW() + INTERVAL '1 DAY'";
	
	public EventDAO() {
		this.connectionWB = ConnectionBD.getConnectionDB();
	}

	public List<Event> filterEventsCategory(Category category) {

		List<Event> eventsFilter = new ArrayList<>();

		for (Event event : listEvents()) {

			if (event.getCategory().getName().equals(category.getName())) {
				eventsFilter.add(event);
			}

		}

		return eventsFilter;

	}

	public List<Event> listEvents() {
		List<Event> events = new ArrayList<Event>();

		try {
			PreparedStatement ps = connectionWB.getConnection().prepareStatement(SQL_SELECT_EVENTS);
			ResultSet rs = ps.executeQuery();
			

			listEvents(events, rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return events;
	}

	private void listEvents(List<Event> events, ResultSet rs) throws SQLException {
		while (rs.next()) {
			Event event = new Event();
			event.setIdEvent(rs.getInt("id_event"));
			event.setTitle(rs.getString("title"));
			event.setDescription(rs.getString("description"));
			event.setHref(rs.getString("href"));
			event.setLocal(rs.getString("local"));
			event.setDate(rs.getDate("date").toLocalDate());
			event.setTime(rs.getTime("time").toLocalTime());
			event.setSource(EventSource.valueOf(rs.getString("event_info_vendor")));

			Category category = new Category();
			category.setIdCategory(rs.getInt("id_category"));
			event.setCategory(category);

			events.add(event);
		}
	}

	public void sendEmail(User user, String[] categories) {
		List<Event> filteredEvents = new ArrayList<Event>();
		
		try {
			for(String id : categories) {
				
				PreparedStatement ps = connectionWB.getConnection().prepareStatement(SQL_SELECT_USER_EVENTS);
				ps.setInt(1, Integer.parseInt(id));
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					Event event = new Event();
					event.setFolder(rs.getString("folder"));;
					event.setTitle(rs.getString("title"));
					event.setDescription(rs.getString("description"));
					event.setHref(rs.getString("href"));
					event.setLocal(rs.getString("local"));
					event.setDate(rs.getDate("date").toLocalDate());

					filteredEvents.add(event);
				}
			}
			
			AwsEmailSevice aws = new AwsEmailSevice(user, filteredEvents);
			aws.sendEmail();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
