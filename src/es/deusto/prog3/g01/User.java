package es.deusto.prog3.g01;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class User extends  Persona{
	private String userName;
	private int id_usuario;
	private String userContraseña;
	
	// CONSTRUCTOR
	/*
	public User(String nombre, String apellido, LocalDateTime fechaNacimiento, String userName, int id_usuario) {
		super(nombre, apellido, fechaNacimiento);
		this.userName = userName;
		this.id_usuario = id_usuario;
	}
	*/
	//CONSTRUCTOR SIN PARAMETROS
	
	//GETTERS Y SETTERS

	public String getUserContraseña() {
		return userContraseña;
	}

	public void setUserContraseña(String userContraseña) {
		this.userContraseña = userContraseña;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}


	
	
	//TOSTRING
	@Override
	public String toString() {
		return "User [userName=" + userName + ", id_usuario=" + id_usuario + "]";
	}
	
	
	
	
	
	
	

}
