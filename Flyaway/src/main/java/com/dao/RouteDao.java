package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.model.Route;

public class RouteDao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/flyaway?useSSL=false";
	private String jdbcUsername = "admin";
	private String jdbcPassword = "admin";
	
//	private String jdbcURL = "jdbc:mysql://localhost:3306/flyawayapp?useSSL=false";
//	private String jdbcUsername = "admin";
//	private String jdbcPassword = "admin";
	
	// routeId, fromCity,toCity, airline;, capacity,fromDate,toDate;price;

	public RouteDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			System.out.println("Db details " + connection.toString());
			
		} catch (SQLException e) {
			// What to do // Debug
			e.printStackTrace();
			SqlException(e);
		} catch (ClassNotFoundException e) {
			// What to do // Debug
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(Route route) throws SQLException {
		
		String INSERT_SQL = "INSERT INTO routes" + "  (fromCity, toCity, airline,capacity,fromDate,toDate,price) VALUES "
				+ " (?, ?, ?,?,?,?,?);";
	
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
			preparedStatement.setString(1, route.getFromCity());
			preparedStatement.setString(2, route.getToCity());
			preparedStatement.setString(3, route.getAirline());
			preparedStatement.setInt(4, route.getCapacity());
			preparedStatement.setDate(5, new java.sql.Date(route.getFromDate().getTime()));
			preparedStatement.setDate(6, new java.sql.Date(route.getToDate().getTime()));
			preparedStatement.setFloat(7, route.getPrice());
			
			System.out.println(preparedStatement);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			SqlException(e);
		}
	}

	public Route getRoute(int arouteId) {
		
		String ROUTE_BY_ID = "select fromCity, toCity, airline,capacity,fromDate,toDate,price from routes where routeId =?";
		Route route = null;
	
		try (Connection connection = getConnection();		
		
		PreparedStatement preparedStatement = connection.prepareStatement(ROUTE_BY_ID);) {
			preparedStatement.setInt(1, arouteId);
			System.out.println(preparedStatement);
		
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				//String routeId 	= rs.getString("routeId");
				String fromCity = rs.getString("fromCity");
				String toCity 	= rs.getString("toCity");
				String airline 	= rs.getString("airline");
				int    capacity = rs.getInt("capacity");
				Date   fromDate = rs.getDate("fromDate");
				Date   toDate 	= rs.getDate("toDate");
				Long	price 	= rs.getLong("price");
				
				route = new Route();
				
				route.setRouteId(arouteId);
				route.setFromCity(fromCity);
				route.setToCity(toCity);
				route.setAirline(airline);
				route.setCapacity(capacity);
				route.setFromDate(fromDate);
				route.setToDate(toDate);
				route.setPrice(price);
				
				System.out.println(route.toString());
				
				//route = new Route(Integer.parseInt(routeId),fromCity,toCity,airline,capacity,fromDate, toDate,price);
			}
		} catch (SQLException e) {
			SqlException(e);
		}
		return route;
	}

	public List<Route> allRoutes() {

		String SELECT_ALL = "select * from routes";
		
		List<Route> routes = new ArrayList<>();

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
			
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				
				String routeId 	= rs.getString("routeId");
				String fromCity = rs.getString("fromCity");
				String toCity 	= rs.getString("toCity");
				String airline 	= rs.getString("airline");
				int    capacity = rs.getInt("capacity");
				Date   fromDate = rs.getDate("fromDate");
				Date   toDate 	= rs.getDate("toDate");
				Long	price 	= rs.getLong("price");
				
				Route route = new Route(Integer.parseInt(routeId),fromCity,toCity,airline,capacity,fromDate, toDate,price);
				routes.add( route);
			}
		} catch (SQLException e) {
			SqlException(e);
		}
		return routes;
	}

	public List<Route> allRoutesBy(String fCity, String tCity, String air, Date aDate ) {

		String SELECT_ALL_BY = "select * from routes  ";
		
		//
		// To do conditions
		//
		
		List<Route> routes = new ArrayList<>();

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY);) {
			
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				
				String routeId 	= rs.getString("routeId");
				String fromCity = rs.getString("fromCity");
				String toCity 	= rs.getString("toCity");
				String airline 	= rs.getString("airline");
				int    capacity = rs.getInt("capacity");
				Date   fromDate = rs.getDate("fromDate");
				Date   toDate 	= rs.getDate("toDate");
				Long	price 	= rs.getLong("price");
				
				Route route = new Route(Integer.parseInt(routeId),fromCity,toCity,airline,capacity,fromDate, toDate,price);
				routes.add( route);
			}
		} catch (SQLException e) {
			SqlException(e);
		}
		return routes;
	}
	
	
	
	public boolean deleteRoute(int routeId) throws SQLException {
		boolean rowDeleted;
		
		String DELETE_SQL = "delete from routes where routeId = ?;";
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);) {
			preparedStatement.setInt(1, routeId);
			System.out.println(preparedStatement);
			
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateRoute(Route route) throws SQLException {
		boolean rowUpdated;
		
	//	 "update routes set fromCity=?, toCity=?, airline=?,capacity=?,fromDate=?,toDate=?,price=? where routeId = ?;";
		String UPDATE_SQL = "update routes set fromCity=?, toCity=?, airline=?,capacity=?,fromDate=?,toDate=?,price=? where routeId = ?;";

		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);) {
			System.out.println("updated route :"+preparedStatement);

			preparedStatement.setString(1, route.getFromCity());
			preparedStatement.setString(2, route.getToCity());
			preparedStatement.setString(3, route.getAirline());
			preparedStatement.setInt(4, route.getCapacity());
			preparedStatement.setDate(5, new java.sql.Date(route.getFromDate().getTime()));
			preparedStatement.setDate(6, new java.sql.Date(route.getToDate().getTime()));
			preparedStatement.setFloat(7, route.getPrice());
			preparedStatement.setInt(8, route.getRouteId());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void SqlException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}


}
