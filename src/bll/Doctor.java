package bll;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
// Importa las clases necesarias, como Especialidad
// import bll.Especialidad;

import dll.DtoDoctor;
import repository.Validaciones;

public class Doctor extends Usuario {
    
    private List<String> especialidades; 
    // private List<Turno> turnos; 

    public Doctor(int id, String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
        super(id, nombre, apellido, mail, contrasenia, dni, tipo);
        this.especialidades = new ArrayList<>();
        // this.turnos = new ArrayList<>();
    }
    
    public Doctor(String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
        super(nombre, apellido, mail, contrasenia, dni, tipo);
        this.especialidades = new ArrayList<>();
        // this.turnos = new ArrayList<>();
    }
    
    
    public List<String> getEspecialidades() {
        return especialidades;
    }
    
    // Método para mostrar el menú del Doctor
    @Override
    public void menu() {
        String[] opcionesMenu = {
            "Ver mis turnos",
            "Ver mi perfil",
            "Agregar nueva especialidad a mi perfil",
            "Cerrar Sesión"
        };
        
        int opcionSeleccionada;
        
        do {
            opcionSeleccionada = JOptionPane.showOptionDialog(null, "Elija una opcion", null, 0, 0, null, opcionesMenu, 			opcionesMenu);
         

            switch (opcionSeleccionada) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    this.agregarEspecialidad();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
                default:
                    
            }
        } while (opcionSeleccionada !=3);
    }

    
    private void agregarEspecialidad() {
        String nuevaEspecialidad = Validaciones.ValidarString("Ingrese la nueva especialidad:");       
        boolean flag = DtoDoctor.agregarNuevaEspecialidad(this.getId(), nuevaEspecialidad);
            
        if (flag) {
            this.especialidades.add(nuevaEspecialidad);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: No se pudo guardar la especialidad. Revise si ya existe.");
        }
    }
    
    
    
}