package br.reaggeou.ted.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.Event;
import br.reaggeou.ted.util.ConnectionWithBank;

public class EventDAO {

	private ConnectionWithBank connectionWB;

	private static final String SQL_SELECT_EVENTS = "SELECT id_event​, title​, description​, href​, local​, ​date​​,​ time​, folder​, vendor​, id_category FROM EVENTS";

	public EventDAO() {
		this.connectionWB = ConnectionWithBank.getConnectionWB();
	}

	public List<Event> listEvents() {
		List<Event> events = new ArrayList<Event>();

		try {
			PreparedStatement ps = connectionWB.getConnection().prepareStatement(SQL_SELECT_EVENTS);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Event event = new Event();
				event.setId_event(rs.getInt("id_event"));
				event.setTitle(rs.getString("title"));
				event.setDescription(rs.getString("description"));
				event.setHref(rs.getString("href"));
				event.setLocal(rs.getString("local"));
				event.setDate(rs.getDate("date").toLocalDate());
				event.setTime(rs.getTime("time").toLocalTime());
//				event.setVendor(Enum);
				
				Category category = new Category();
				category.setId_category(rs.getInt("id_category"));
				event.setCategory(category);
				
				events.add(event);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return events;
	}

}
