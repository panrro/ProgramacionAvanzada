package bll;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import dll.DtoDoctor;
import dll.DtoObraSocial;
import dll.DtoRecepcionista;
import dll.DtoTurnos;
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

	public Recepcionista(int id, String nombre, String apellido) {
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
	}

	public Recepcionista(int id, int usuarioId) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static Paciente UltimoPaciente(){
		return Dtopaciente.VerPacientes().getLast();
	}
	
	public void menu() {
        String[] opcionesMenu = {
        	"Agregar Paciente",
            "Ver turnos",
            "Cancelar turnos",
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
                	Turnos.verListadoTurnos();
                    break;
                case 2:
                	int id = DtoTurnos.buscarPorId().getId();
                	DtoTurnos.cancelarTurno(id);
                	
                    break;
                    
                case 3:
                	JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
                                          }
        } while (opcionSeleccionada != 3);
    }
	
	public static boolean agregarPaciente() {

	    String nombre = Validaciones.ValidarString("Ingrese nombre");
	    String apellido = Validaciones.ValidarString("Ingrese apellido");
	    String dni = Validaciones.ValidarString("Ingrese dni");

	    LinkedList<ObraSocial> listaObraSocial = DtoObraSocial.mostrarObraSociales();
	    String[] obrasSociales = new String[listaObraSocial.size()];
	    for (int i = 0; i < obrasSociales.length; i++) {
	        obrasSociales[i] = listaObraSocial.get(i).getNombre();
	    }
	    int indiceObraSocial = JOptionPane.showOptionDialog(
	            null, "Seleccione obra social", null, 0, 0, null, obrasSociales, obrasSociales[0]);
	    int idElegidoObraSocial = listaObraSocial.get(indiceObraSocial).getId();


	    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad"));

	    LinkedList<Doctor> lista = DtoDoctor.VerDoctores();
	    String[] doctores = new String[lista.size()];
	    for (int i = 0; i < doctores.length; i++) {
	        Doctor d = lista.get(i);
	        doctores[i] = "ID: " + d.getIdDoctor() +
	                      " | Usuario: " + d.getNombreUsuario() +
	                      " | Especialidad: " + d.getNombreEspecialidad() +
	                      " | Obra Social: " + d.getNombreObraSocial();
	    }

	    int indiceSeleccionado = JOptionPane.showOptionDialog(
	            null, "Seleccione un doctor", null, 0, 0, null, doctores, doctores[0]);
	    Doctor doctorSeleccionado = lista.get(indiceSeleccionado);
	    int idElegidoDoctor = doctorSeleccionado.getIdDoctor();

	    // Inserto el paciente
	    Dtopaciente.InsertarPaciente(nombre, apellido, dni, idElegidoObraSocial, edad, idElegidoDoctor);

	    // Obtengo el paciente recién creado
	    Paciente nuevoPaciente = Dtopaciente.VerPacientes().getLast();

	    // Creo el turno automáticamente
	    String fecha = Validaciones.ValidarString("Ingrese fecha del turno con este formato: yyyy-MM-dd");
	    LinkedList<Recepcionista> listaRecepcionistas = DtoRecepcionista.VerRecepcionistas();
	    String[] recepcionistas = new String[listaRecepcionistas.size()];
	    for (int i = 0; i < recepcionistas.length; i++) {
	        Recepcionista r = listaRecepcionistas.get(i);
	        recepcionistas[i] = "ID: " + r.getId() +
	                            " | Nombre: " + r.getNombre() +
	                            "Apellido " + r.getApellido();
	    }
	    int indiceRecepcionista = JOptionPane.showOptionDialog(
	            null, "Seleccione recepcionista", null, 0, 0, null, recepcionistas, recepcionistas[0]);
	    int idElegidoRecepcionista = listaRecepcionistas.get(indiceRecepcionista).getId();

	    // Inserto turno
	    DtoTurnos.InsertarTurno(fecha, idElegidoRecepcionista, idElegidoDoctor, nuevoPaciente.getId());

	    JOptionPane.showMessageDialog(null, "Paciente con turno creado correctamente.");
	    return true;
	}

	
	
	public static boolean agregarTurno() {
		
		 String fecha = Validaciones.ValidarString("Ingrese fecha del turno con el siguiente formato: yyyy-MM-dd");
		 
		 LinkedList<Recepcionista> listaRecepcionistas = DtoRecepcionista.VerRecepcionistas();
		 String[] recepcionistas = new String[listaRecepcionistas.size()];
		 for (int i = 0; i < recepcionistas.length; i++) {
		        Recepcionista r = listaRecepcionistas.get(i);
		        recepcionistas[i] = "ID: " + r.getId() +
		                            " | Nombre: " + r.getNombre() +
		                            "Apellido " + r.getApellido();                
		 }
		 
	int indiceRecepcionista = JOptionPane.showOptionDialog(null, "Seleccione quien eres", null, 0, 0, null, recepcionistas, 	recepcionistas[0]);
	Recepcionista recepcionistaSeleccionado = listaRecepcionistas.get(indiceRecepcionista);   
    // 3. Obtener el ID real del doctor
    int idElegidoRecepcionista = recepcionistaSeleccionado.getId();		
 
    LinkedList<Doctor> lista = DtoDoctor.VerDoctores();
    String[] doctores = new String[lista.size()];
    for (int i = 0; i < doctores.length; i++) {
        Doctor d = lista.get(i);
        doctores[i] = "ID: " + d.getIdDoctor() +
                      " | Usuario: " + d.getNombreUsuario() +
                      " | Especialidad: " + d.getNombreEspecialidad() +
                      " | Obra Social: " + d.getNombreObraSocial();
    }
		int indiceSeleccionado = JOptionPane.showOptionDialog(null, "Seleccione un doctor", null, 0, 0, null, doctores, 		doctores[0]);
		Doctor doctorSeleccionado = lista.get(indiceSeleccionado); 
	    
	    // 3. Obtener el ID real del doctor
	    int idElegidoDoctor = doctorSeleccionado.getIdDoctor();	
	   	    
	    LinkedList<Paciente> listaPacientes = Dtopaciente.VerPacientes();
		 String[] pacientes = new String[listaPacientes.size()];
		 for (int i = 0; i < pacientes.length; i++) {
		     Paciente p = listaPacientes.get(i);
		     doctores[i] = "ID: " + p.getId() +
		                   " | Nombre: " + p.getNombre() +
		                   " | Apellido: " + p.getApellido() +
		                   " | Dni: " + p.getDni()+
		                   " | ObraSocialId: " + p.getObrasocial_id() +
		                   " | Edad: " + p.getEdad()+
		                   " | Doctor Id: " + p.getDoctor_id();		 }
		 
	int seleccionadoLista = JOptionPane.showOptionDialog(null, "Seleccione un doctor", null, 0, 0, null, doctores, 		doctores[0]);
	Paciente PacienteSeleccionado = listaPacientes.get(seleccionadoLista); 
   
   // 3. Obtener el ID real del doctor
   int idElegidopaciente = PacienteSeleccionado.getId();
		
		
		DtoTurnos.InsertarTurno(fecha, idElegidoRecepcionista,idElegidoDoctor,idElegidopaciente);
		return true;
	
	}
}
