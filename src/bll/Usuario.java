package bll;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dll.Conexion;
import dll.DtoUsuario;
import repository.Validaciones;

public class Usuario {

	//Atributos
	protected int id;
	protected String nombre;
	protected String apellido;
	protected String mail;
	protected String dni;
	protected String tipo;
	protected String contrasenia;
	
	
	//Este com que es estatico viene desde el singleton Conexion y una vez que tengo la conxeion creada abre el puerto
	private static Connection con = Conexion.getInstance().getConnection();
	
	//constructores
	public Usuario (int id, String nombre,String apellido, String mail,String contrasenia, String dni,String tipo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido=apellido;
		this.mail = mail;
		this.contrasenia = contrasenia;
		this.dni = dni;
		this.tipo=tipo;
	};
	
	public Usuario (String nombre,String apellido, String mail,String contrasenia, String dni, String tipo) {
		this.id=id;
		this.nombre = nombre;
		this.apellido=apellido;
		this.mail = mail;
		this.contrasenia = contrasenia;
		this.dni = dni;
		this.tipo=tipo;
	};
	
	public Usuario(String nombre, String apellido, String mail, String dni) {
	    this.nombre = nombre;
	    this.apellido = apellido;
	    this.mail = mail;
	    this.dni = dni;
	}
	
	public Usuario() {
	   
	}

	
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", contrasenia=" + contrasenia
				+ ", dni=" + dni + ", tipo=" + tipo + "]";
	}
	
	public void menu() {
        
    }


	public static Doctor login() {
	    String mail, contrasenia;
	    
	        mail = JOptionPane.showInputDialog("Ingrese mail");
	        contrasenia = JOptionPane.showInputDialog("Ingrese contraseña");
	        if (mail.isEmpty() || contrasenia.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error al ingrear datos");
				return null;
			} else {
				return DtoUsuario.login(mail, contrasenia);
			}
		}
	
	public static Recepcionista loginRecepcionista() {
	    String mail, contrasenia;
	    
	        mail = JOptionPane.showInputDialog("Ingrese mail");
	        contrasenia = JOptionPane.showInputDialog("Ingrese contraseña");
	        if (mail.isEmpty() || contrasenia.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error al ingrear datos");
				return null;
			} else {
				return DtoUsuario.loginRecepcionista(mail, contrasenia);
			}
		}
	

	public static boolean agregarUsuario() {
		 String mail;
		 do {
			mail=  JOptionPane.showInputDialog("Ingrese mail");
			if (Validaciones.validate(mail)==false) {
				JOptionPane.showMessageDialog(null, "Mail incorrecto");
			}
		} while (Validaciones.validate(mail)==false);
		 
		 String contrasenia = Validaciones.ValidarString("Ingrese contraseña");
		 String nombre = Validaciones.ValidarString("Ingrese nombre");
		 String apellido = Validaciones.ValidarString("Ingrese apellido");
		 String dni = Validaciones.ValidarString("Ingrese dni");
		 String [] roles = {"Recepcionista", "Doctor"};
		 String tipo = (String)JOptionPane.showInputDialog(null, "Ingrese tipo de usuario", "", 0, null, roles, roles[0]);
		 
		 Usuario nuevo = new Usuario(nombre, apellido, mail, contrasenia, dni, tipo);
		 DtoUsuario.agregarUsuario(nuevo);
		
		return true;
	}
	
	
	public static boolean EditarUsuario() {
		
		Usuario encontrado = DtoUsuario.buscarPorId();
		String  apellido, mail,contr, dni,tipo,nombre;
		 nombre =  Validaciones.ValidarString("Ingrese nombre");
		 apellido = Validaciones.ValidarString("Ingrese apellido");
		do {
			mail = Validaciones.ValidarString("Ingrese mail");
			if (Validaciones.validate(mail)==false) {
				JOptionPane.showMessageDialog(null, "Mail incorrecto");
			}
		} while (Validaciones.validate(mail)==false);
		
		  contr = Validaciones.ValidarString("Ingrese contraseña");
		  dni = Validaciones.ValidarString("Ingrese dni");
		  String[] roles = {"Doctor","Recepcionista", "Administrador"};
		 tipo = (String) JOptionPane.showInputDialog(null, "Ingrese tipo", "", 0, null, roles,roles[0]);
		 
		  Usuario nuevo = new Usuario(encontrado.getId(),nombre,apellido,mail,contr, dni,tipo);
		  	return DtoUsuario.EditarUsuario(nuevo);
    
	}
	
	public static LinkedList<Usuario> mostrarUsuarios(){
		return DtoUsuario.mostrarUsuarios();
	}
	
	public static Usuario UltimoUsuario(){
		return DtoUsuario.mostrarUsuarios().getLast();
	}
	
	public static void verListadoUsuarios() { 
	    List<Usuario> listaUsuarios = mostrarUsuarios(); 
	    
	    if (listaUsuarios.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay usuarios registrados en el sistema.");
	        return;
	    }

	    StringBuilder sb = new StringBuilder(" Listado de Usuarios \n");
	    for (Usuario u : listaUsuarios) {
	        sb.append(u.toString()).append("\n"); 
	    }

	    JOptionPane.showMessageDialog(null, sb.toString());
	}

	
}
