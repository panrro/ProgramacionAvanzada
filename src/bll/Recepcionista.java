package bll;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import dll.DtoDoctor;
import dll.DtoObraSocial;
import dll.DtoRecepcionista;
import dll.DtoUsuario;
import dll.Dtopaciente;
import repository.Validaciones;

public class Recepcionista extends Usuario{

	protected int id;

	public Recepcionista(int id) {
		this.id = id;
	}

	public Recepcionista(int id,String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
		super(id,nombre, apellido, mail, contrasenia, dni, tipo);
	}
	public Recepcionista(String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
		super(nombre, apellido, mail, contrasenia, dni, tipo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static boolean agregarRecepcionista() {
		
		Usuario.agregarUsuario();
		DtoRecepcionista.agregarRecepcionista();
		
		return true;
	}
	
	public void menu() {
        String[] opcionesMenu = {
        	"Agregar Paciente",
            "Ver turnos",          
            "Cerrar Sesión"             
        };
        
        int opcionSeleccionada;
        
        do {
            opcionSeleccionada = JOptionPane.showOptionDialog(null, "Elija una opcion", null, 0,0, null,opcionesMenu,  			opcionesMenu[0]);

            switch (opcionSeleccionada) {
                case 0:
                		Recepcionista.agregarPaciente();            			
                    break;
                case 1:
                	
                	
                    break;
                case 2:
                	
                    break;
                    
                case 3:
                	JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
                                          }
        } while (opcionSeleccionada != 2);
    }
	
	public static boolean agregarPaciente() {
		
		 String nombre = Validaciones.ValidarString("Ingrese nombre");
		 String apellido = Validaciones.ValidarString("Ingrese apellido");
		 String dni = Validaciones.ValidarString("Ingrese dni");
		 
		 LinkedList<ObraSocial> listaObraSocial = DtoObraSocial.mostrarObraSociales();
			String [] obrasSociales = new String [listaObraSocial.size()];
			for (int i = 0; i < obrasSociales.length; i++) {
				obrasSociales[i] = listaObraSocial.get(i).getNombre();
			}
			int idElegidoObraSocial = JOptionPane.showOptionDialog(null, "", null, 0, 0, null, obrasSociales, obrasSociales[0]);
			idElegidoObraSocial++;
			
			 int edad= Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"));
			
	
			 
			 LinkedList<Doctor> lista = DtoDoctor.VerDoctores();
			 String[] doctores = new String[lista.size()];
			 for (int i = 0; i < doctores.length; i++) {
			     Doctor d = lista.get(i);
			     doctores[i] = "ID: " + d.getId2() +
			                   " | Usuario: " + d.getUsuario_id() +
			                   " | Especialidad: " + d.getEspecialidad_id() +
			                   " | Obra Social: " + d.getObrasocial_id();
			 }
			 
		int indiceSeleccionado = JOptionPane.showOptionDialog(null, "Seleccione un doctor", null, 0, 0, null, doctores, doctores[0]);
		Doctor doctorSeleccionado = lista.get(indiceSeleccionado); 
	    
	    // 3. Obtener el ID real del doctor
	    int idElegidoDoctor = doctorSeleccionado.getId2();		
		
		
		Dtopaciente.InsertarPaciente(nombre, apellido, dni,idElegidoObraSocial, edad,idElegidoDoctor);
		return true;
	
	}
}
