package com.sakare.vasant.reponse;

import java.util.Date;

/**
 * @author vasant_sakre
 *
 */

public class AuthenticateReponse {

	private String message;
	private int statusCode;
	private String firstName;
	private String lastName;
	private String token;
	private Date expiry;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public String toString() {
		return "AuthenticateReponse[firstName=" + firstName + ",lastName=" + lastName + "]";

	}

}
