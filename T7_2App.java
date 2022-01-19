import java.util.ArrayList;
import javax.swing.JOptionPane;

public class T7_2App {

	static ArrayList<String> listaArticulos = new ArrayList<String>();
	static ArrayList<Double> listaPrecios = new ArrayList<Double>();
	static ArrayList<Double> listaTipoIva = new ArrayList<Double>();
	static double cantidadTotal;
	
	public static void main(String[] args) {
		introducirProducto();
		mostrarNumeroArticulosComprados();
		cantidadAPagar();
		calcularPrecioBrutoDeCadaArticulo();

	}
	
	//Introducir Producto y Precio
	public static void introducirProducto() {
		String articulo;
		double precio;
		String seguir;
		
		do {
			articulo = JOptionPane.showInputDialog("Introduce el articulo ");
			listaArticulos.add(articulo);
			precio = Double.parseDouble(JOptionPane.showInputDialog("Introduce el precio de " + articulo + ":"));
			listaPrecios.add(precio);
			introducirIva();
			seguir = JOptionPane.showInputDialog("Quieres introducir otro articulo: si/no");
			
		} while (seguir.equals("si"));
		//MOSTRAR LISTAS POR CONSOLA
		
		/*System.out.println("Lista Articulos:");
		for (int i = 0; i < listaArticulos.size(); i++) {
			System.out.print(listaArticulos.get(i) + " ");
		}
		
		System.out.println("\nLista de precios:");
		for (int i = 0; i < listaPrecios.size(); i++) {
			System.out.print(listaPrecios.get(i) + " ");
		}
		
		System.out.println("\nLista tipo de IVA:");
		for (int i = 0; i < listaTipoIva.size(); i++) {
			System.out.print(listaTipoIva.get(i) + " ");
		}*/
	}
	
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
	
	public static void cantidadAPagar() {
		String txtCantidad;
		double cantidad = 0;
		double cambio = 0;
		//Pide usuario cantidad para pagar
		txtCantidad = JOptionPane.showInputDialog("Introduce la cantidad de pago:");
		cantidad = Double.parseDouble(txtCantidad);
		for (int i = 0; i < listaPrecios.size(); i++) {
			cantidadTotal += listaPrecios.get(i);			
		}
		//Cambio a devolver
		if(cantidad < cantidadTotal) {
			JOptionPane.showMessageDialog(null, "No tienes dinero suficiente");
		} else {
			cambio = cantidad - cantidadTotal;
			JOptionPane.showMessageDialog(null, "Has pagado: " + cantidad + "\nTu cambio es de: " + cambio);			
		}
	}
	
	public static void mostrarNumeroArticulosComprados() {
		int num = 0;
		//Numero articulos comprados
		num = listaArticulos.size();
		JOptionPane.showMessageDialog(null, "Has comprado " + num + " articulo/s");		
	}
	
	public static void calcularPrecioBrutoDeCadaArticulo() {
		double precioIva = 0;
		double bruto = 0;
		double totalBruto = 0;
		double totalConIva = 0;
		String result ="";
		//Precio de cada articulo, total Bruto y precio mas IVA
		for (int i = 0; i < listaPrecios.size(); i++) {
			totalConIva += listaPrecios.get(i);
			precioIva = listaPrecios.get(i) * (listaTipoIva.get(i) / 100);
			bruto = listaPrecios.get(i) - precioIva;
			totalBruto += bruto;
			result += listaArticulos.get(i) + ":" + "\nPrecio bruto: " + bruto + "\nprecio + IVA: " + listaPrecios.get(i) + "\n";
		}		
		JOptionPane.showMessageDialog(null, result);
		JOptionPane.showMessageDialog(null, "El precio total burto es de: " + totalBruto);
		JOptionPane.showMessageDialog(null, "El precio total con IVA es de: " + totalConIva);
	}
}
