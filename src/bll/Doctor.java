package bll;

import java.util.LinkedList;

import javax.swing.JOptionPane;
import bll.Turnos;
import dll.DtoDoctor;
import dll.DtoEspecialidad;
import dll.DtoHistoriaClinica;
import dll.DtoObraSocial;
import dll.DtoRecepcionista;
import dll.DtoTurnos;
import dll.DtoUsuario;
import dll.Dtopaciente;
import repository.Validaciones;

public class Doctor extends Usuario {

	    private int idDoctor; // ID real de la tabla doctor
	    private String nombreUsuario;
	    private String nombreEspecialidad;
	    private String nombreObraSocial;

	    // Constructor para login
	    public Doctor( String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
	        super( nombre, apellido, mail, contrasenia, dni, tipo);
	    }

	    // Constructor para mostrar doctores con especialidad y obra social
	    public Doctor(int idDoctor, String nombreUsuario, String nombreEspecialidad, String nombreObraSocial) {
	        super();
	        this.idDoctor = idDoctor;
	        this.nombreUsuario = nombreUsuario;
	        this.nombreEspecialidad = nombreEspecialidad;
	        this.nombreObraSocial = nombreObraSocial;
	    }

	    public Doctor(int doctorId, int especialidadId, int usuarioId, int obraSocialId) {
	    	this.idDoctor = doctorId;
	    }

		public int getIdDoctor() {
	        return idDoctor;
	    }

	    public void setIdDoctor(int idDoctor) {
	        this.idDoctor = idDoctor;
	    }

	    public String getNombreUsuario() {
	        return nombreUsuario;
	    }

	    public String getNombreEspecialidad() {
	        return nombreEspecialidad;
	    }

	    public String getNombreObraSocial() {
	        return nombreObraSocial;
	    }


	@Override
    public void menu() {
        String[] opcionesMenu = {
        	"Gestionar perfil",
            "Ver turnos",          
            "Cerrar Sesión"             
        };
        
        int opcionSeleccionada;
        
        do {
            opcionSeleccionada = JOptionPane.showOptionDialog(null, "Elija una opcion", null, 0,0, null,opcionesMenu,  opcionesMenu[0]);

            switch (opcionSeleccionada) {
                case 0:
                		String[] opcionesDesplegable = {"Atender", "Agregar obraSocial","Editar Perfil", "Ver 						Perfil"};
                		String elegida = (String) JOptionPane.showInputDialog(null, "Ingrese opcion", "", 0, null, 						opcionesDesplegable,opcionesDesplegable[0]);
                		
                		switch (elegida) {
						case "Atender":
							
							atenderPaciente();
						       break;
							
						case "Agregar obraSocial":
							break;

						case "Editar Perfil":
							
							break;
							
						case "Ver Perfil":
							verMiPerfil();
							break;
							
						
						} //Fin del submenu              			
                    break;
                case 1:

            		String[] opciones2 = {"Ver Turnos"};
            		String elegida2 = (String) JOptionPane.showInputDialog(null, "Ingrese opcion", "", 0, null, 					opciones2,opciones2[0]);
            		
                	switch (elegida2) {
					case "Ver Turnos":
						verMisTurnos();
						
						break;
					}
                	
                	
                    break;
                case 2:
                	
                	JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
                    
                
                                          }
        } while (opcionSeleccionada != 2);
    }
	
	public void verMiPerfil() {
	    Doctor perfil = DtoDoctor.VerPerfilDoctor(this.getIdDoctor());
	    if (perfil != null) {
	        JOptionPane.showMessageDialog(null,
	            "👨‍⚕️ PERFIL DEL DOCTOR\n\n" +
	            "Nombre: " + perfil.getNombreUsuario() + "\n" +
	            "Especialidad: " + perfil.getNombreEspecialidad() + "\n" +
	            "Obra Social: " + perfil.getNombreObraSocial(),
	            "Perfil del Doctor",
	            JOptionPane.INFORMATION_MESSAGE
	        );
	    } else {
	        JOptionPane.showMessageDialog(null, "No se encontró el perfil del doctor.");
	    }
	}
	
	
	public void verMisTurnos() {
	    LinkedList<Turnos> turnos = DtoTurnos.VerTurnosPorDoctor(this.getIdDoctor());
	    if (turnos.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No tenés turnos asignados.");
	    } else {
	        StringBuilder sb = new StringBuilder("TUS TURNOS:\n\n");
	        for (Turnos t : turnos) {
	            sb.append("ID: ").append(t.getId())
	              .append(" | Paciente: ").append(t.getApellidoPaciente())
	              .append(" | Fecha: ").append(t.getFecha())
	              .append("\n");
	        }
	        JOptionPane.showMessageDialog(null, sb.toString());
	    }
	}
	
	public void atenderPaciente() {
	    // 1️⃣ Seleccionamos el paciente
	    LinkedList<Paciente> listaPacientes = Dtopaciente.VerPacientesPorDoctor(this.getIdDoctor());
	    if (listaPacientes.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No tenés pacientes asignados.");
	        return;
	    }

	    String[] pacientes = new String[listaPacientes.size()];
	    for (int i = 0; i < listaPacientes.size(); i++) {
	        Paciente p = listaPacientes.get(i);
	        pacientes[i] = "ID: " + p.getId() +
	                       " | Nombre: " + p.getNombre() +
	                       " | Apellido: " + p.getApellido();
	    }

	    int seleccionado = JOptionPane.showOptionDialog(null, "Seleccione un paciente", "Atender Paciente",
	            0, 0, null, pacientes, pacientes[0]);


	    Paciente pacienteSeleccionado = listaPacientes.get(seleccionado);

	    String descripcion = Validaciones.ValidarString("Ingrese la descripción de la historia clínica");

	    String fecha = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd"));

	    boolean exito = DtoHistoriaClinica.InsertarHistoriaClinica( descripcion, fecha,this.getIdDoctor(),pacienteSeleccionado.getId()
	    );

	    if (exito) {
	        JOptionPane.showMessageDialog(null, "Historia clínica agregada correctamente al paciente.");
	    } else {
	        JOptionPane.showMessageDialog(null, "Ocurrió un error al agregar la historia clínica.");
	    }
	}
}