package Ui;
import javax.swing.JOptionPane;

import bll.Admin;
import bll.Doctor;
import bll.Usuario;
import dll.Conexion;

public class Main {

    public static void main(String[] args) {
        Conexion.getInstance();
        
        Usuario Admin = new Admin ("Franco", "Colotta", "franco@gmail.com", "47297832");
        
        String[] opciones = { "Menu Admin", "Acceder como Doctor", "Acceder Recepcionista", "Salir" };
		
		int opcion;
		do {
			opcion = JOptionPane.showOptionDialog(null, "elija opción", null, 0, 0, null, opciones, opciones);
			switch (opcion) {
			case 0:
				Admin.menu();
				break;
			case 1:
				Usuario encontrado = Doctor.login();
				if (encontrado != null) {
				    encontrado.menu(); 
				}
				break;

			case 2:
				JOptionPane.showMessageDialog(null, "completar");
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Saliendo");
				break;
			default:
				break;
			}

		} while (opcion != 3);

    
   
    }
}