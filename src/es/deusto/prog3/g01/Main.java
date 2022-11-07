package es.deusto.prog3.g01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	GestorBD gestorBD = new GestorBD();		
		
		//CREATE DATABASE: Se crea la BBDD
		gestorBD.crearBBDD();
		
		

	}
}
	
