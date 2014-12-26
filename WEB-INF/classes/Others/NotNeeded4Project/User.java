// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class User{

	String firstName;
	String lastName;
	String birthDate;
	String birthMonth;
	String birthYear;
	String mobilePhone;
	String emailAddress;
	String gender;
	private String password;

	public User(String firstName, String lastName, String birthDate, String birthMonth, String birthYear, String mobilePhone, String emailAddress, String gender, String password){
		this.firstName 	 = firstName;
		this.lastName	 = lastName;
		this.birthDate	 = birthDate;
		this.birthMonth	 = birthMonth;
		this.birthYear	 = birthYear;
		this.mobilePhone = mobilePhone;
		this.emailAddress=emailAddress;
		this.gender		 = gender;
		this.password 	 = password;
	}

	public void userLogin(String emailAddress, String password){
		
	}
}