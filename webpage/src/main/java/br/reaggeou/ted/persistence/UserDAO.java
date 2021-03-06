package br.reaggeou.ted.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.StatusUser;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.util.ConnectionBD;

public class UserDAO {

	private ConnectionBD connectionDB;

	private static final String SQL_INSERT_USER = "INSERT INTO USERS (email, tel, status) values (?, ?, CAST(? AS status_user));";
	private static final String SQL_INSERT_USERCATEGORY = "INSERT INTO USER_CATEGORY (id_user, id_category) values (?, ?)";
	private static final String SQL_SELECT_ID_USER = "SELECT id_user FROM USERS WHERE email=?";
	private static final String SQL_SELECT_NAME_CATEGORY = "SELECT name FROM CATEGORIES WHERE id_category=?";
	private static final String SQL_SELECT_USERS = "SELECT id_user, email, tel FROM USERS";
	private static final String SQL_SELECT_CATEGORYID = "SELECT id_category FROM CATEGORIES";
	private static final String SQL_CHANGE_STATUS_USER = "UPDATE USERS SET status=CAST(? AS status_user) WHERE id_user=?";
	private static final String SQL_REMOVE_USERCATEGORY = "DELETE FROM USER_CATEGORY WHERE id_user=?";
	private static final String SQL_INSERT_REASONS = "INSERT INTO CANCELLATION_REASONS (id_user, reason) values (?, ?)";
	private static final String SQL_UPDATE_STATUS_USER = "UPDATE USERS SET email=?, tel=?, status=CAST(? AS status_user) WHERE id_user=?";
	private static final String SQL_SELECT_STATUS_USER = "SELECT status FROM USERS WHERE id_user=?";
	
	public UserDAO() {
		this.connectionDB = ConnectionBD.getConnectionDB();
	}

	public void insertUser(User user) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_INSERT_USER);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getTel());
			ps.setString(3, user.getStatus().name());
			ps.execute();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_UPDATE_STATUS_USER);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getTel());
			ps.setString(3, StatusUser.ACTIVE.name());
			ps.setInt(4, user.getIdUser());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User statusUser(User u) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_SELECT_STATUS_USER);
			User user = userGetIdByEmail(u);
			u.setIdUser(user.getIdUser());
			ps.setInt(1, u.getIdUser());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				u.setIdUser(user.getIdUser());
				u.setStatus(StatusUser.valueOf(rs.getString("status")));
				return u; 
			}
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	public void insertTableUserCategory(User u, Category category) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_INSERT_USERCATEGORY);
			User user = userGetIdByEmail(u);
			ps.setInt(1, user.getIdUser());
			ps.setInt(2, category.getIdCategory());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void changeUserStatus(User u) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_CHANGE_STATUS_USER);
			User user = userGetIdByEmail(u);
			ps.setString(1, u.getStatus().name());
			ps.setInt(2, user.getIdUser());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeUserCategory(User u) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_REMOVE_USERCATEGORY);
			User user = userGetIdByEmail(u);
			ps.setInt(1, user.getIdUser());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cancellationReasons(User u, String reason) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_INSERT_REASONS);
			User user = userGetIdByEmail(u);
			ps.setInt(1, user.getIdUser());
			ps.setString(2, reason);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<User> listUser() {

		List<User> users = new ArrayList<User>();

		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_SELECT_USERS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt("id_user"));
				user.setEmail(rs.getString("email"));
				user.setTel(rs.getString("tel"));

				users.add(user);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public Boolean validate(User user) {
		Boolean exists = false;
		for (User e : listUser()) {
			if (e.getEmail().equalsIgnoreCase(user.getEmail()) || e.getTel().equals(user.getTel())) {
				exists = true;
				break;
			}
		}
		return exists;
	}

	public Map<Integer, Integer> mapCategoryId() {
		Map<Integer, Integer> mapCategory = new LinkedHashMap<Integer, Integer>();

		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_SELECT_CATEGORYID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				mapCategory.put(rs.getInt("id_category"), rs.getInt("id_category"));
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mapCategory;
	}

	public List<User> getActiveUsers() {
		List<User> activeUsers = new ArrayList<>();
		try {
			PreparedStatement ps = connectionDB.getConnection()
					.prepareStatement("SELECT id_user, email, tel, status FROM USERS WHERE status=ACTIVE");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt("id_user"));
				user.setEmail(rs.getString("email"));
				user.setTel(rs.getString("tel"));
				user.setStatus(StatusUser.valueOf(rs.getString("status")));

				activeUsers.add(user);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return activeUsers;
	}

	private User userGetIdByEmail(User user) throws SQLException {
		User userCheck = null;
		PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_SELECT_ID_USER);
		ps.setString(1, user.getEmail());
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			userCheck = new User();
			userCheck.setIdUser(rs.getInt("id_user"));
			// userCheck.setEmail(rs.getString("email"));
			// userCheck.setTel(rs.getString("tel"));
		}

		ps.close();

		return userCheck;
	}

	public Category getCategoryByID(String id) throws SQLException {
		Category category = new Category();
		PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_SELECT_NAME_CATEGORY);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			category.setIdCategory(Integer.parseInt(id));
			category.setName(rs.getString("name"));
		}

		ps.close();

		return category;
	}

}
