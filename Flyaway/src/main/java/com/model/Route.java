package com.model;

import java.util.Date;

public class Route {
	
	private int 	routeId;
	private String 	fromCity;
	private String 	toCity;
	private String 	airline;
	private int		capacity;
	private Date	fromDate;
	private Date	toDate;
	private long 	price;
	
	public Route() {
		
	}
	
	public Route(int routeId, String fromCity, String toCity, String airline, int capacity, Date fromDate, Date toDate,
			long price) {
		super();
		this.routeId = routeId;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.airline = airline;
		this.capacity = capacity;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.price = price;
	}
	
	
	public Route(String fromCity, String toCity, String airline, int capacity, Date fromDate, Date toDate, long price) {
		super();
		System.out.println("Route => constr ");
		System.out.println(fromCity);
		System.out.println(toCity);
		System.out.println(airline);
		System.out.println(capacity);
		System.out.println(fromDate);
		System.out.println(toDate);
		System.out.println(price);
		
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.airline = airline;
		this.capacity = capacity;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.price = price;
		
		System.out.println(this.fromCity);
		System.out.println(this.toCity);
		System.out.println(this.airline);
		System.out.println(this.capacity);
		System.out.println(this.fromDate);
		System.out.println(this.toDate);
		System.out.println(this.price);
		
	}



	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", fromCity=" + fromCity + ", toCity=" + toCity + ", airline=" + airline
				+ ", capacity=" + capacity + ", fromDate=" + fromDate + ", toDate=" + toDate + ", price=" + price + "]";
	}

}
