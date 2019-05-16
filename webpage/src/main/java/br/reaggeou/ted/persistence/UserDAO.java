package br.reaggeou.ted.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.util.ConnectionBD;

public class UserDAO {

	private ConnectionBD connectionDB;

	private static final String SQL_INSERT_USER = "INSERT INTO USERS (email, tel) values (?, ?);";
	private static final String SQL_INSERT_USERCATEGORY = "INSERT INTO USER_CATEGORY (id_user, id_category) values (?, ?)";
	private static final String SQL_SELECT_ID_USER = "SELECT id_user FROM USERS WHERE email=?";
	private static final String SQL_SELECT_ID_CATEGORY = "SELECT id_category FROM CATEGORIES WHERE name=?";
	private static final String SQL_SELECT_USERS = "SELECT id_user, email, tel FROM USERS";
	private static final String SQL_SELECT_CATEGORY = "SELECT id_category, name FROM CATEGORIES";
	private static final String SQL_SELECT_CATEGORYID = "SELECT id_category FROM CATEGORIES";
	private static final String SQL_REMOVE_USER = "DELETE FROM USERS WHERE email=?";
	private static final String SQL_REMOVE_USERCATEGORY = "DELETE FROM USER_CATEGORY WHERE id_user=?";

	public UserDAO() {
		this.connectionDB = ConnectionBD.getConnectionDB();
	}

	public void insertUser(User user) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_INSERT_USER);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getTel());
			ps.execute();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertTableUserCategory(User u, Category c) throws SQLException {
		PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_INSERT_USERCATEGORY);
		User user = userGetIdByEmail(u);
		ps.setInt(1, user.getIdUser());
		Category category = categoryGetIdByName(c);
		ps.setInt(2, category.getIdCategory());
		ps.execute();
	}

	public void removeUser(User user) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_REMOVE_USER);
			ps.setString(1, user.getEmail());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeUserCategory(User user) {
		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_REMOVE_USERCATEGORY);
			ps.setInt(1, user.getIdUser());
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

	public Map<Integer, String> mapCategory() {
		Map<Integer, String> mapCategory = new LinkedHashMap<Integer, String>();

		try {
			PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_SELECT_CATEGORY);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				mapCategory.put(rs.getInt("id_category"), rs.getString("name"));
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mapCategory;
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

	private Category categoryGetIdByName(Category category) throws SQLException {
		Category categoryCheck = null;
		PreparedStatement ps = connectionDB.getConnection().prepareStatement(SQL_SELECT_ID_CATEGORY);
		ps.setString(1, category.getName());
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			categoryCheck = new Category();
			categoryCheck.setIdCategory(rs.getInt("id_category"));
			// categoryCheck.setName(rs.getString("name"));
		}

		ps.close();

		return categoryCheck;
	}

}
