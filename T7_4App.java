import java.util.ArrayList;

import javax.swing.JOptionPane;

public class T7_4App {

	static ArrayList<String> listaArticulos = new ArrayList<String>();
	static ArrayList<Double> listaUnidades = new ArrayList<Double>();
	static ArrayList<Double> listaPrecios = new ArrayList<Double>();
	static ArrayList<Double> listaTipoIva = new ArrayList<Double>();	
	static double cantidadTotal;
	
	public static void main(String[] args) {
		introducirProducto();
		interfaz();
	}
	
	//Interfaz visual. Permite elegir la opcion para interactuar con la tienda.
	public static void interfaz() {
		String txtNum = "";
		int num = 0;
		
		txtNum = JOptionPane.showInputDialog("TIENDA:\n1- Añadir un Articulo\n2- Conultar Articulo\n3- Mostrar Tienda\n4- Comprar\n5- Salir\nIntroduce una opcion 1-5");
		num = Integer.parseInt(txtNum);
		
		switch (num) {
		case 1:
			introducirProducto();
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
			comprar();
			interfaz();
			break;
			
		case 5:
			//Salir
			break;		

		default:
			JOptionPane.showMessageDialog(null, "No has introducido una opcion correcta");
			interfaz();
			break;
		}		
	}
	
	//Permite introducir articulos a las listas
	public static void introducirProducto() {
		String articulo = "";
		double unidades = 0;
		double precio = 0;
		String seguir = "";
		boolean existe = false;		
		
		//Si la lista esta vacia, mostrara que no hay articulos
		if(listaArticulos.size()<= 0) {
			JOptionPane.showMessageDialog(null, "La tienda esta vacia");
		}		
		
		do {			
			articulo = JOptionPane.showInputDialog("Introduce el articulo: ");	//Introduce el articulo		
			
			for (int i = 0; i < listaArticulos.size(); i++) {
				if(listaArticulos.get(i).equals(articulo)) {
					JOptionPane.showMessageDialog(null, "Es articulo ya existe"); //No se puede añadir articulos con el mismo nombre
					existe = true;
				}
			}			
			
			if(!existe) {//Si existe el articulo, este codigo no se ejecutara
				listaArticulos.add(articulo);//Añadimos el articulo a la lista de articulos
				unidades = Double.parseDouble(JOptionPane.showInputDialog("Introduce el cantidad de articulos:"));
				listaUnidades.add(unidades);//añadimos las unidades del articulo en la lista
				
				precio = Double.parseDouble(JOptionPane.showInputDialog("Introduce el precio de " + articulo + ":"));
				listaPrecios.add(precio);//añadimos el precio del articulo a la lista
				
				introducirIva();//validamos el iva introducido
				seguir = JOptionPane.showInputDialog("Quieres introducir otro articulo: si/no");//para seguir introduciendo articulos o parar de introducir
			}			
			
		} while (seguir.equals("si"));	//seguira preguntando, mientras pongamos si	
	}	
	//Especifica el articulo y mostrara el nombre precio y unidades de este
	public static void consultarInfoArticuloConcreto() {
		String result = "";
		String articulo = "";
		boolean existe = false;
		
		articulo = JOptionPane.showInputDialog("Introduce el articulo: ");
		for (int i = 0; i < listaArticulos.size(); i++) {
			if(listaArticulos.get(i).equals(articulo)) {
				//Guardamos el resultado del articulo si lo encontramos en la listaArticulos
				result = "Articulo: " + listaArticulos.get(i) + " Precio: " + listaPrecios.get(i) + " euros " + listaUnidades.get(i) + " unidad/es\n";
				existe = true;				
			}
		}
		// Si encuentra el articulo mostrara el resultado si no lo encuentra saldra un aviso
		if(!existe) {
			JOptionPane.showMessageDialog(null, "No existe el articulo que buscas");
		} else {
			JOptionPane.showMessageDialog(null, result);
		}		
	}
	
	//Consulta El articulo Precio y Unidades de todos los articulos
	public static void consultarTodo() {	
		String result = "";
		for (int i = 0; i < listaArticulos.size(); i++) {
			result += "Articulo: " + listaArticulos.get(i) + " Precio: " + listaPrecios.get(i) + " euros " + listaUnidades.get(i) + " unidad/es\n";
		}	
		//Mostraremos el resultado
		JOptionPane.showMessageDialog(null, result);
	}
	
	//Permite comprar articulos especificando el nombre del articulo
	public static void comprar(){
		String articulo = "";
		double unidades = 0;				
		boolean existe = false;
		int indice = 0;
		double valor;		
		double precioIva = 0;
		double bruto = 0;
		double totalBruto = 0;
		double totalConIva = 0;		
		String txtCantidad;
		double cantidad = 0;
		double cambio = 0;
					
		articulo = JOptionPane.showInputDialog("Introduce el articulo para comprar: ");			
			
		for (int i = 0; i < listaArticulos.size(); i++) {
			if(listaArticulos.get(i).equals(articulo)) {					
				existe = true;
				indice = i;
			}
		}			
			
		if(existe) {
			JOptionPane.showMessageDialog(null, "El precio es de: " + listaPrecios.get(indice));				
			unidades = Double.parseDouble(JOptionPane.showInputDialog("Introduce el cantidad de articulos:"));	
			//Añadimos el nuevo valor de unidades, restamos las unidades que ha comprado
			valor = listaUnidades.get(indice) - unidades;
			listaUnidades.set(indice, valor);				
			
			introducirIva();						
			
			JOptionPane.showMessageDialog(null, "Has comprado " + unidades + " " + articulo + "/s" );
			
			totalConIva += listaPrecios.get(indice);
			precioIva = listaPrecios.get(indice) * (listaTipoIva.get(indice) / 100);
			bruto = listaPrecios.get(indice) - precioIva;
			totalBruto += bruto;			
			
			JOptionPane.showMessageDialog(null, "El precio total bruto es de: " + totalBruto * unidades);
			JOptionPane.showMessageDialog(null, "El precio total con IVA es de: " + totalConIva * unidades );			
			
			//Pide usuario cantidad para pagar
			txtCantidad = JOptionPane.showInputDialog("Introduce la cantidad de pago:");
			cantidad = Double.parseDouble(txtCantidad);
			
			cantidadTotal = listaPrecios.get(indice) * unidades;			
			
			//Cambio a devolver
			if(cantidad < cantidadTotal) {
				JOptionPane.showMessageDialog(null, "No tienes dinero suficiente");
			} else {
				cambio = cantidad - cantidadTotal;
				JOptionPane.showMessageDialog(null, "Has pagado: " + cantidad + "\nTu cambio es de: " + cambio);			
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Este producto no existe para comprar");
		}		
	}
	
	//Introduce el tipo de IVA y lo validamos
	public static void introducirIva() {		
		String txtIva = "";
		double iva = 0;		
		
		txtIva = JOptionPane.showInputDialog("Introduce el IVA: 21/4 ");
		//Validacion IVA
		while (!txtIva.equals("21") && !txtIva.equals("4")) {
			JOptionPane.showMessageDialog(null, "No has introducido el iva correcto");
			txtIva = JOptionPane.showInputDialog("Introduce el IVA: 21/4 ");
		}		
		
		iva = Double.parseDouble(txtIva);
		listaTipoIva.add(iva);		
	}
}
