package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.UserDAO;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class UserDAOImpl implements UserDAO{
	
	public static final String INSERT_QUERY ="INSERT INTO user(Name,email,mobile,password,Address,Role) VALUES(?,?,?,?,?,?)";
	public static final String GET_USER = "SELECT * FROM user WHERE userid = ?";
	public static final String GET_ALL_USER = "SELECT * FROM user";
	public static final String DELETE_USER = "DELETE FROM user WHERE userid = ?";
	public static final String UPDATE_USER="UPDATE user SET name=?, email=?, password=?,mobile=?,address = ?, role=? WHERE userid = ?";
	private static final String LOGIN_QUERY =
			"SELECT * FROM user WHERE email=? AND password=? AND status='ACTIVE'";
	private static final String DEACTIVATE_QUERY =
	        "UPDATE user SET status='INACTIVE' WHERE userid=?";
	private static final String CHECK_EMAIL =
	        "SELECT COUNT(*) FROM user WHERE email = ?";
	private static final String UPDATE_PROFILE =
			"UPDATE user SET Name=?, mobile=?, Address=? WHERE userid=?";

	@Override
	public void addUser(User u) {
        Connection con = DBConnection.getConnection();  // ← GET CONNECTION HERE
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);
			

			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getMobile());
			pstmt.setString(4, u.getPassword());
			pstmt.setString(5, u.getAddress());
			pstmt.setString(6, u.getRole());

			pstmt.executeUpdate();
			
			int i =pstmt.executeUpdate();
			System.out.println(i);	
		} catch (SQLException e) {
			e.printStackTrace();
		  }
	}
	
	@Override
	public User getUser(int userid) {
		
		Connection connection = DBConnection.getConnection();
		User u = null;
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(GET_USER);
			pstmt.setInt(1, userid);
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				 u = getUserByResulySet(res);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public void updateUser(User u) {
		
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_USER);
			
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			pstmt.setString(5, u.getRole());
			pstmt.setInt(6, u.getUserID());
			
			int i=pstmt.executeUpdate();
			System.out.println(i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userID) {
		Connection con = DBConnection.getConnection();  // ← GET CONNECTION HERE
		try (PreparedStatement pstmt = con.prepareStatement(DELETE_USER)) {
	        pstmt.setInt(1, userID);
	        pstmt.executeUpdate();
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public List<User> getAllUsers() {
		
		    List<User> users = new ArrayList<>();

		    String query = "SELECT * FROM user";

		    try {

		        Connection con =
		                DBConnection.getConnection();

		        PreparedStatement ps =
		                con.prepareStatement(query);

		        ResultSet rs =
		                ps.executeQuery();

		        while(rs.next()) {

		            User user = new User();

		            user.setUserID(
		                    rs.getInt("userid"));

		            user.setUsername(
		                    rs.getString("Name"));

		            user.setEmail(
		                    rs.getString("email"));

		            user.setAddress(
		                    rs.getString("Address"));

		            user.setRole(
		                    rs.getString("Role"));

		            user.setStatus(
		                    rs.getString("status"));

		            users.add(user);
		        }

		    } catch (Exception e) {

		        e.printStackTrace();
		    }

		    return users;
		}

	
	private static User getUserByResulySet(ResultSet res)
	        throws SQLException {

	    User u = new User();

	    u.setUserID(
	            res.getInt("User_Id"));

	    u.setUsername(
	            res.getString("Name"));

	    u.setEmail(
	            res.getString("Email"));

	    u.setPassword(
	            res.getString("Password"));

	    u.setAddress(
	            res.getString("Address"));

	    u.setRole(
	            res.getString("Role"));

	    u.setCreatedDate(
	            res.getTimestamp("createdDate"));

	    u.setLastLoginDate(
	            res.getTimestamp("lastLoginDate"));

	    u.setStatus(
	            res.getString("status"));

	    return u;
	}

	@Override
	public User validateUser(String email, String password) {
		Connection con = DBConnection.getConnection();  // ← GET CONNECTION HERE
		
		try (PreparedStatement pstmt = con.prepareStatement(LOGIN_QUERY)) {
	        pstmt.setString(1, email);
	        pstmt.setString(2, password);
	        
	        ResultSet rs = pstmt.executeQuery();
	
	        if(rs.next()) {
	            return extractUser(rs);
	        }
		}
		catch(Exception e) {
	        e.printStackTrace();
	    }
	
	    return null;
	}


	private User extractUser(ResultSet rs) throws SQLException {
		

		    User user = new User();

		    user.setUserID(rs.getInt("userid"));
		    user.setUsername(rs.getString("Name"));
		    user.setEmail(rs.getString("email"));
		    user.setPassword(rs.getString("password"));
		    user.setAddress(rs.getString("Address"));
		    user.setRole(rs.getString("Role"));
		    user.setCreatedDate(rs.getTimestamp("createdDate"));
		    user.setLastLoginDate(rs.getTimestamp("lastDate"));
		    user.setStatus(rs.getString("status"));

		    return user;
		
	}

	@Override
	public void deactivateUser(int userId) {
		Connection con = DBConnection.getConnection();  // ← GET CONNECTION HERE
		
	    try (PreparedStatement pstmt = con.prepareStatement(DEACTIVATE_QUERY)) {
	        pstmt.setInt(1, userId);
	        pstmt.executeUpdate();
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	}

	@Override
	public boolean updateUserStatus(
	        int userId,
	        String status) {

	    String query =
	            "UPDATE user SET status=? WHERE userid=?";

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        PreparedStatement ps =
	                con.prepareStatement(query);

	        ps.setString(1, status);
	        ps.setInt(2, userId);

	        int rows =
	                ps.executeUpdate();

	        return rows > 0;

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return false;
	}

	@Override
	public int getTotalUsers() {

	    String query =
	            "SELECT COUNT(*) FROM user";

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        PreparedStatement ps =
	                con.prepareStatement(query);

	        ResultSet rs =
	                ps.executeQuery();

	        if(rs.next()) {

	            return rs.getInt(1);
	        }

	    } catch(Exception e) {

	        e.printStackTrace();
	    }

	    return 0;
	}

	@Override
	public List<User> getAllDeliveryAgents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmailExists(String email) {

	    try {

	    	Connection con = DBConnection.getConnection();
	        PreparedStatement ps =
	                con.prepareStatement(CHECK_EMAIL);

	        ps.setString(1, email);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            return rs.getInt(1) > 0;
	        }

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return false;
	}

	@Override
	public User getUserById(int userId) {

	    User user = null;

	    try {
	    	Connection con = DBConnection.getConnection();
	        PreparedStatement ps =
	                con.prepareStatement(GET_USER);

	        ps.setInt(1, userId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()) {

	            user = extractUser(rs);

	        }

	    }
	    catch(Exception e) {

	        e.printStackTrace();

	    }

	    return user;
	}

	@Override
	public boolean updateProfile(User user) {

	    try {
	    	Connection con = DBConnection.getConnection();
	        PreparedStatement ps =
	                con.prepareStatement(UPDATE_PROFILE);

	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getMobile());
	        ps.setString(3, user.getAddress());
	        ps.setInt(4, user.getUserID());

	        return ps.executeUpdate() > 0;

	    }
	    catch(Exception e){

	        e.printStackTrace();

	    }

	    return false;

	}
}
		