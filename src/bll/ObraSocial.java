package bll;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import dll.DtoObraSocial;
import dll.DtoUsuario;
import repository.Validaciones;

public class ObraSocial {
	int id;
	private String nombre;
	private double descuento;
	
	public ObraSocial(int id, String nombre, double descuento) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.descuento = descuento;
	}

	public ObraSocial(String nombre, double descuento) {
		// TODO Auto-generated constructor stub
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

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	@Override
	public String toString() {
	    return "Obra Social [Nombre=" + nombre + ", Descuento=" + descuento + "]";
	}
	
	public static boolean agregarObraSocial() {
        String nombre = Validaciones.ValidarString("Ingrese nombre de la Obra Social");
        if (nombre == null) return false;

        String descuentoStr;
        double descuento;

        try {
            descuentoStr = Validaciones.ValidarString("Ingrese el descuento");
            if (descuentoStr == null) return false;
            
            descuento = Double.parseDouble(descuentoStr);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El descuento debe ser un valor numérico válido.");
            return false;
        }

        ObraSocial nuevaObraSocial = new ObraSocial(nombre, descuento);

        return DtoObraSocial.agregarObraSocial(nuevaObraSocial);
    }
	
	public static LinkedList<ObraSocial> mostrarObraSociales(){
		return DtoObraSocial.mostrarObraSociales();
	}
	
	public static void verListadoObraSociales() { 
	    List<ObraSocial> listaObraSociales = mostrarObraSociales(); 
	    
	    if (listaObraSociales.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay obras sociales registradas en el sistema.");
	        return;
	    }

	    StringBuilder sb = new StringBuilder(" Listado de Obras Sociales \n");
	    for (ObraSocial u : listaObraSociales) {
	        sb.append(u.toString()).append("\n"); 
	    }

	    JOptionPane.showMessageDialog(null, sb.toString());
	}
	
	
	
		public static boolean EditarObraSocial() {
		
		ObraSocial encontrado = DtoObraSocial.buscarPorId();
		String  nombre; double descuento;
		 nombre =  Validaciones.ValidarString("Ingrese nombre");
		 descuento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese nuevo precio de descuento"));
		ObraSocial nueva = new ObraSocial(encontrado.getId(),nombre,descuento);
		  	return DtoObraSocial.EditarObraSocial(nueva);
    
	}

	
	

}
