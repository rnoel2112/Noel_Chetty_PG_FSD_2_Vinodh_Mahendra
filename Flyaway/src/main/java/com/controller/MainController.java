package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDao;
import com.dao.AirlineDao;
import com.dao.CustomerDao;
import com.dao.PlaceDao;
import com.dao.RouteDao;
import com.model.Admin;
import com.model.Airline;
import com.model.Customer;
import com.model.Place;
import com.model.Route;

@WebServlet("/")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RouteDao 	routeDao;
	private CustomerDao customerDao;
	private AdminDao 	adminDao;
	private AirlineDao  airlineDao;
	private PlaceDao	placeDao;
	
	
	//Lets initialize our database object
	public void init(ServletConfig config) throws ServletException {
		routeDao 	= new RouteDao();
		customerDao = new CustomerDao();
		adminDao	= new AdminDao();
		placeDao	= new PlaceDao();
		airlineDao	= new AirlineDao();
	}
	
	//
	// Handler routine for handling all 
	// 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("RoteManagement=>doGet action:" + action);
		try {
			switch (action) {
			
			case "/admin":
				formtoCall(request, response,"validate-admin-form.jsp");
				break;
			case "/admin-process":
				adminRoute(request, response);
				break;
			case "/changePwd":
				formtoCall(request, response,"change-password-form.jsp");
				break;
			case "/password-process":
				passwordProcess(request, response);
				break;
				
			case "/payment-process":
				paymentProcess(request, response);
				break;
				
			case "/places":
				formtoCall(request, response,"placess-form.jsp");
				break;
			case "/places-process":
				placeProcess(request, response);
				break;
			case "/airlines":
				formtoCall(request, response,"airlines-form.jsp");
				break;
			case "/airlines-process":
				airlinesProcess(request, response);
				break;	

			case "/insert":
				insertRoute(request, response);
				break;
			case "/delete":
				deleteRoute(request, response);
				break;
			case "/edit":
				editForm(request,response);
				break;
			case "/update":
				updateRoute(request, response);
				break;
				
			case "/search":
				listRoutesBy(request, response,"index.jsp");
				break;
				
			case "/success":
				formtoCall(request, response,"success.jsp");
				break;
				
			case "/book":	
				int routeId = Integer.parseInt(request.getParameter("routeId"));
				if (routeDao == null ){
					System.out.println ("routeDao is null");
					routeDao = new RouteDao();
				}
				Route aRoute = routeDao.getByKey(routeId);
				System.out.println(aRoute.toString());
				request.setAttribute("route", aRoute);
				formtoCall(request, response,"register-user-form.jsp");
				break;
			case "/book-process":
				bookRoute(request, response);
				break;
			
			default:
				listRoutes(request, response,"index.jsp");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	private void formtoCall(HttpServletRequest request, HttpServletResponse response,String jspName)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspName);
		dispatcher.forward(request, response);
		//response.sendRedirect(jspName); 
	}
	
	
	private void adminRoute(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
	    String userName	=request.getParameter("userName");  
	    String password	=request.getParameter("password");
	    
		Admin admin = adminDao.getByKey(userName);
		
		List<Airline> listAirlines  = airlineDao.listOfAll();
		List<Place> listPlaces 		= placeDao.listOfAll();
		
		System.out.println(admin.toString());    
	    System.out.println ("userName:"+ userName +" password:"+ password );   
	    
	    if(password.equals(admin.getPassword())){ 	
	    	request.setAttribute("title", "Admin - Add Route");
	    	List<Route> listRoutes = routeDao.listOfAll();
			request.setAttribute("routes", listRoutes);
	    	
			request.setAttribute("airlines", listAirlines);
			request.setAttribute("places", listPlaces);
			
	    	formtoCall(request, response,"add-route-form.jsp");
	    }  
	   
	    //should we invalidate the session ?
        request.setAttribute("error", "Please Check - Invalid user or password");      
	    formtoCall(request, response,"validate-admin-form.jsp");
	}
	
	private void passwordProcess(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		String userName	=request.getParameter("userName");  
		String oPassword	=request.getParameter("oldPassword");
		String nPassword	=request.getParameter("newPassword");
		
		Admin admin = adminDao.getByKey(userName);
		if(oPassword.equals(admin.getPassword())){ 	
			admin.setPassword(nPassword);
			adminDao.update(admin); 
			request.setAttribute("info", "Password changed successfully - relogin");      
			formtoCall(request, response,"validate-admin-form.jsp");
	    } 
		System.out.println(admin.toString());    
		
	    //should we invalidate the session ?
        request.setAttribute("error", "Please Check - Invalid user or password");      
	    formtoCall(request, response,"validate-admin-form.jsp");
	}
	
	private void paymentProcess(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		String firstName=request.getParameter("firstName");  
		String lastName	=request.getParameter("lastName");
		String emailId	=request.getParameter("emailId");
		String phoneNo	=request.getParameter("phoneNo");
		String password	=request.getParameter("password");
		
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmailId(emailId);
		customer.setPhoneNo(phoneNo);
		customer.setPassword(password);
		
		System.out.println(customer.toString());
	
		customerDao.insert(customer); // TODO Check for dupe
		
		
		String fromCity = request.getParameter("fromCity");
		String toCity 	= request.getParameter("toCity");	
		String airline 	= request.getParameter("airline");
		Long	price 	= Long.parseLong(request.getParameter("price"));

		Route route = new Route();
		route.setFromCity(fromCity);
		route.setToCity(toCity);
		route.setAirline(airline);
		route.setPrice(price);
	
		request.setAttribute("route", route);
		request.setAttribute("customer", customer);
  
		formtoCall(request, response,"dummyPayment.jsp");
	
	}
	
	
	
	private void placeProcess(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		// TODO
	}
	
	private void airlinesProcess(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		// TODO
	}
	
	private void bookRoute(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		// TODO
	    String emailId	=request.getParameter("emailId"); 
	    String phoneNo	=request.getParameter("phoneNo"); 
	    String password	=request.getParameter("password");
	    
	    System.out.println ("emailId:"+ emailId +" password:"+ password +"Phone No" + phoneNo);   
	    
	    if(emailId.equals("sim@sim.com") && password.equals("sim")){ 
	    	
	    	request.setAttribute("title", "Admin - Add Route");
	    	List<Route> listRoutes = routeDao.listOfAll();
			request.setAttribute("routes", listRoutes);
	    	formtoCall(request, response,"index.jsp");
	    }  
	   
	    //should we invalidate the session ?
        request.setAttribute("error", "Please Check - Invalid user or password");      
	    formtoCall(request, response,"index.jsp");
	}
	
	
	private void listRoutes(HttpServletRequest request, HttpServletResponse response,String jspToList)
			throws SQLException, IOException, ServletException {
		System.out.println ("in List Route");
		if (routeDao == null ){
			System.out.println ("routeDao is null");
			routeDao = new RouteDao();
		}
		List<Route> listRoutes = routeDao.listOfAll();
		request.setAttribute("routes", listRoutes);
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspToList);
		dispatcher.forward(request, response);
		//response.sendRedirect("user-list.jsp"); 
	}
	
	private void listRoutesBy(HttpServletRequest request, HttpServletResponse response,String jspToList)
			throws SQLException, IOException, ServletException, ParseException {
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String fromCity = request.getParameter("fromCity");
		String toCity 	= request.getParameter("toCity");	
		String airline 	= request.getParameter("airline");
		Date fromDate = null;
		
		System.out.println (request.getParameter("travelDate"));
		if (!request.getParameter("travelDate").isEmpty() ) {
			   fromDate = dateFormat.parse(request.getParameter("travelDate"));
		}
	
		if (routeDao == null ){
			System.out.println ("routeDao is null");
			routeDao = new RouteDao();
		}
		List<Route> listRoutes = routeDao.listOfAllBy(fromCity,toCity,airline,fromDate);
		request.setAttribute("routes", listRoutes);
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspToList);
		dispatcher.forward(request, response);
		//response.sendRedirect("user-list.jsp"); 
	}

	private void editForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		if (routeDao == null ){
			System.out.println ("routeDao is null");
			routeDao = new RouteDao();
		}
		Route aRoute = routeDao.getByKey(routeId);
		System.out.println(aRoute.toString());
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-route-form.jsp");
		request.setAttribute("route", aRoute);
		dispatcher.forward(request, response);

	}

	private void insertRoute(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
//		String fromCity = request.getParameter("fromCity");
//		String toCity 	= request.getParameter("toCity");	
		
		Date   fromDate = dateFormat.parse(request.getParameter("fromDate"));
		Date   toDate 	= dateFormat.parse(request.getParameter("toDate"));
		Long	price 	= Long.parseLong(request.getParameter("price"));
		
		
		Airline airline	= airlineDao.getByKey(Integer.parseInt(request.getParameter("airlineId")));
		String code 	= airline.getCode();
		
		Place place		= placeDao.getByKey(Integer.parseInt(request.getParameter("placeId")));
		String sCity = place.getSourceCity();
		String tCity = place.getDestinationCity();
				
		Route art = new Route();
	//	art.setFromCity(fromCity);
	//	art.setToCity(toCity);
		art.setFromCity(sCity);
		art.setToCity(tCity);

		art.setAirline(code);
		art.setFromDate(fromDate);
		art.setToDate(toDate);
		art.setPrice(price);
		
		System.out.println(art.toString());
		System.out.println(place.toString());
		System.out.println(airline.toString());
		
		// Route aroute = new Route(fromCity,toCity,airline,capacity,fromDate, toDate,price);
		//routeDao.insertUser(aroute);
		
		if (routeDao == null ){
			System.out.println ("routeDao is null");
			routeDao = new RouteDao();
		}
		routeDao.inser(art);
    	List<Route> listRoutes = routeDao.listOfAll();
    	request.setAttribute("routes", listRoutes);
    	
    	List<Airline> listAirlines = airlineDao.listOfAll();
    	request.setAttribute("airlines", listAirlines);
    	
    	List<Place> listPlaces = placeDao.listOfAll();
    	request.setAttribute("places", listPlaces);
    	
		formtoCall(request, response,"add-route-form.jsp");
		//response.sendRedirect("list");
	}

	private void updateRoute(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		System.out.println (request.getParameter("routeId"));
		
		int routeid = Integer.parseInt(request.getParameter("routeId"));
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String fromCity = request.getParameter("fromCity");
		String toCity 	= request.getParameter("toCity");
		String airline 	= request.getParameter("airline");
		Date   fromDate = dateFormat.parse(request.getParameter("fromDate"));
		Date   toDate 	= dateFormat.parse(request.getParameter("toDate"));
		Long	price 	= Long.parseLong(request.getParameter("price"));
		
		Route art = new Route();
		art.setRouteId(routeid);
		art.setFromCity(fromCity);
		art.setToCity(toCity);
		art.setAirline(airline);
		art.setFromDate(fromDate);
		art.setToDate(toDate);
		art.setPrice(price);
		
		System.out.println(art.toString());
		
		//Route aroute = new Route(routeid,fromCity,toCity,airline,capacity,fromDate, toDate,price);
	
		routeDao.update(art);
    	List<Route> listRoutes = routeDao.listOfAll();
		request.setAttribute("routes", listRoutes);
		formtoCall(request, response,"add-route-form.jsp");
	}

	private void deleteRoute(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		if (routeDao == null ){
			System.out.println ("routeDao is null");
			routeDao = new RouteDao();
		}
		routeDao.delete(routeId);
    	List<Route> listRoutes = routeDao.listOfAll();
		request.setAttribute("routes", listRoutes);
		formtoCall(request, response,"add-route-form.jsp");
		//response.sendRedirect("list");

	}	
	
}
