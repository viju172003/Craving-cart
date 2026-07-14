package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    Connection connection = DBConnection.getConnection();
    private static final String INSERT_RESTAURANT_QUERY =
    		"INSERT INTO restaurant(name,address,cuisine_type,description,rating,status,userid) VALUES(?,?,?,?,?,?,?)";

//    private static final String INSERT_RESTAURANT_QUERY =
//            "INSERT INTO restaurant(name,address,cuisine_type,description,rating,status) VALUES(?,?,?,?,?,?)";

    private static final String GET_RESTAURANT_QUERY =
            "SELECT * FROM restaurant WHERE restaurant_id=?";

    private static final String UPDATE_RESTAURANT_QUERY =
            "UPDATE restaurant SET name=?,address=?,cuisine_type=?,description=?,rating=?,status=? WHERE restaurant_id=?";

    private static final String DELETE_RESTAURANT_QUERY =
            "DELETE FROM restaurant WHERE restaurant_id=?";

    private static final String GET_ALL_RESTAURANTS_QUERY =
    		
            "SELECT * FROM restaurant WHERE status='ACTIVE'";

    private static final String GET_RESTAURANTS_BY_CUISINE_QUERY =
            "SELECT * FROM restaurant WHERE cuisine_type=?";

    private static final String GET_RESTAURANTS_BY_STATUS_QUERY =
            "SELECT * FROM restaurant WHERE status=?";

    @Override
    public void addRestaurant(Restaurant restaurant) {

        try (PreparedStatement statement =
                connection.prepareStatement(INSERT_RESTAURANT_QUERY)) {

            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getAddress());
            statement.setString(3, restaurant.getCuisineType());
            statement.setString(4, restaurant.getDescription());
            statement.setDouble(5, restaurant.getRating());
            statement.setString(6, restaurant.getStatus());
            statement.setInt(7, restaurant.getUserid());

            statement.executeUpdate();

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int restaurant_id) {

        Restaurant restaurant = null;

        try (PreparedStatement statement =
                connection.prepareStatement(GET_RESTAURANT_QUERY)) {

            statement.setInt(1, restaurant_id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                restaurant = new Restaurant();

                restaurant.setRestaurantId(
                        rs.getInt("restaurant_id"));

                restaurant.setName(
                        rs.getString("name"));

                restaurant.setAddress(
                        rs.getString("address"));

                restaurant.setCuisineType(
                        rs.getString("cuisine_type"));

                restaurant.setDescription(
                        rs.getString("description"));

                restaurant.setRating(
                        rs.getDouble("rating"));

                restaurant.setStatus(
                        rs.getString("status"));

                restaurant.setCreateDate(
                        rs.getTimestamp("create_date"));

                restaurant.setImagePath(
                        rs.getString("image_path"));

                restaurant.setDeliveryTime(
                        rs.getString("delivery_time"));

                restaurant.setUserid(
                        rs.getInt("userid"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

        try (PreparedStatement statement =
                connection.prepareStatement(UPDATE_RESTAURANT_QUERY)) {

            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getAddress());
            statement.setString(3, restaurant.getCuisineType());
            statement.setString(4, restaurant.getDescription());
            statement.setDouble(5, restaurant.getRating());
            statement.setString(6, restaurant.getStatus());
            statement.setInt(7, restaurant.getRestaurantId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurant_id) {

        try (PreparedStatement statement =
                connection.prepareStatement(DELETE_RESTAURANT_QUERY)) {

            statement.setInt(1, restaurant_id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> restaurants = new ArrayList<>();

        try (PreparedStatement statement =
                connection.prepareStatement(GET_ALL_RESTAURANTS_QUERY)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Restaurant restaurant = new Restaurant();

                restaurant.setRestaurantId(
                        rs.getInt("restaurant_id"));

                restaurant.setName(
                        rs.getString("name"));

                restaurant.setAddress(
                        rs.getString("address"));

                restaurant.setCuisineType(
                        rs.getString("cuisine_type"));

                restaurant.setDescription(
                        rs.getString("description"));

                restaurant.setRating(
                        rs.getDouble("rating"));

                restaurant.setStatus(
                        rs.getString("status"));

                restaurant.setCreateDate(
                        rs.getTimestamp("create_date"));

                restaurant.setImagePath(
                        rs.getString("image_path"));

                restaurant.setDeliveryTime(
                        rs.getString("delivery_time"));

                restaurant.setUserid(
                        rs.getInt("userid"));

                restaurants.add(restaurant);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return restaurants;
    }
    @Override
    public List<Restaurant> getRestaurantsByCuisine(String cuisine_type) {

        List<Restaurant> restaurants = new ArrayList<>();

        try (PreparedStatement statement =
                connection.prepareStatement(GET_RESTAURANTS_BY_CUISINE_QUERY)) {

            statement.setString(1, cuisine_type);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Restaurant restaurant = new Restaurant(

                        resultSet.getInt("restaurant_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("cuisine_type"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("create_date")

                );

                restaurants.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    @Override
    public List<Restaurant> getRestaurantsByStatus(String status) {

        List<Restaurant> restaurants = new ArrayList<>();

        try (PreparedStatement statement =
                connection.prepareStatement(GET_RESTAURANTS_BY_STATUS_QUERY)) {

            statement.setString(1, status);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Restaurant restaurant = new Restaurant(

                        resultSet.getInt("restaurant_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("cuisine_type"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("create_date")

                );

                restaurants.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    @Override
    public boolean updateRestaurantStatus(int restaurantId, String status) {

        String query =
                "UPDATE restaurant SET status=? WHERE restaurant_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, status);
            ps.setInt(2, restaurantId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
        
        
    }
    @Override
    public int getTotalRestaurants() {

        String query =
                "SELECT COUNT(*) FROM restaurant";

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
		public Restaurant getRestaurantByUserId(int userId) {

		    Restaurant restaurant = null;

		    String query =
		            "SELECT * FROM restaurant WHERE userid = ?";

		    try {

		        PreparedStatement ps =
		                connection.prepareStatement(query);

		        ps.setInt(1, userId);

		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {

		            restaurant = new Restaurant();

		            restaurant.setRestaurantId(
		                    rs.getInt("restaurant_id"));

		            restaurant.setName(
		                    rs.getString("name"));

		            restaurant.setAddress(
		                    rs.getString("address"));

		            restaurant.setCuisineType(
		                    rs.getString("cuisine_type"));

		            restaurant.setDescription(
		                    rs.getString("description"));

		            restaurant.setRating(
		                    rs.getDouble("rating"));

		            restaurant.setStatus(
		                    rs.getString("status"));

		            // If your Restaurant model has userid
		            restaurant.setUserid(
		                    rs.getInt("userid"));
		            
		            restaurant.setImagePath(
		                    rs.getString("image_path"));

		            restaurant.setDeliveryTime(
		                    rs.getString("delivery_time"));
		        }

		    } catch (Exception e) {

		        e.printStackTrace();
		    }

		    return restaurant;
		}
		
}