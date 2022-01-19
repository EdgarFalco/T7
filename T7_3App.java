import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class T7_3App {

	static Hashtable <String, Double> contenedor = new Hashtable<String, Double>();
	static Enumeration<Double> enumerationElements;
	static Enumeration<String> enumerationKeys;
	static String articulo;
	static double cantidad;
	
	public static void main(String[] args) {
		crearBD();
		interfaz();		
	}
	
	//Crear base de datos 10 articulos
	public static void crearBD() {
		contenedor.put("leche", 2.0);
		contenedor.put("agua", 0.50);
		contenedor.put("coca", 2.2);
		contenedor.put("naranja", 0.45);
		contenedor.put("pera", 0.35);
		contenedor.put("manzana", 0.30);
		contenedor.put("platano", 1.0);
		contenedor.put("sandia", 4.0);
		contenedor.put("melon", 3.0);
		contenedor.put("fanta", 2.20);		
	}
	
	public static void interfaz() {
		String txtNum = "";
		int num = 0;
		
		txtNum = JOptionPane.showInputDialog("TIENDA:\n1- Añadir un Articulo\n2- Conultar Articulo\n3- Mostrar Tienda\n4- Salir\nIntroduce una opcion 1-4:");
		num = Integer.parseInt(txtNum);
		
		switch (num) {
		case 1:
			anadirArticulos();
			interfaz();
			break;
		case 2:
			consultarInfoArticuloConcreto();
			interfaz();
			break;
		
		case 3:
			consultarTodo();
			interfaz();
			break;
			
		case 4:
			break;

		default:
			JOptionPane.showMessageDialog(null, "No has introducido una opcion correcta");
			interfaz();
			break;
		}		
	}
	
	//Añadir articulos nuevos
	public static void anadirArticulos() {
		enumerationElements = contenedor.elements();
		enumerationKeys = contenedor.keys();
		boolean existe = false;		
		
		articulo = JOptionPane.showInputDialog("Introduce el articulo que quieres añadir: ");		
		
		while (enumerationKeys.hasMoreElements()) {			
			if(enumerationKeys.nextElement().equals(articulo)){				
				existe = true;
				JOptionPane.showMessageDialog(null, "Este producto ya existe");
				cantidad= Double.parseDouble(JOptionPane.showInputDialog("Introduce la cantidad para añadir al articulo existente: "));
				cantidad = cantidad + enumerationElements.nextElement();
				contenedor.put(articulo, cantidad);
			}			 
		}		
		
		if(!existe) {			
			//Añadimos un nuevo articulo y cantidad al hashtable
			cantidad = Double.parseDouble(JOptionPane.showInputDialog("Introduce la cantidad:"));	
			contenedor.put(articulo, cantidad);
		}		
	}
	
	//Consultar un articulo
	public static void consultarInfoArticuloConcreto() {
		enumerationKeys = contenedor.keys();
		boolean existe = false;		
		
		articulo = JOptionPane.showInputDialog("Que articulo quieres consultar: ");
		
		while (enumerationKeys.hasMoreElements()) {	
			if(enumerationKeys.nextElement().equals(articulo)) {
				existe = true;
				JOptionPane.showMessageDialog(null, articulo + ": " + contenedor.get(articulo));	
			}								 
		}
		if(!existe) {
			JOptionPane.showMessageDialog(null, "El articulo que quieres consultar no existe");
		} 		
	}
	
	//Consultar todo el contenido de hashtable
	public static void consultarTodo() {
		JOptionPane.showMessageDialog(null, contenedor);		
	}	
}
