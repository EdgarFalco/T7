import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class T7_1App {	
	
	static ArrayList<String> listaAlumnos = new ArrayList<String>();
	static ArrayList<Integer> listaNotas = new ArrayList<Integer>();
	static ArrayList<Integer> listaNotaMedia = new ArrayList<Integer>();
	static int numAsignaturas;	
	static Hashtable <String, Integer> contenedor = new Hashtable<String, Integer>();
	
	public static void main(String[] args) {		
		introducirAlumnos();
		introducirNotas();
		mediaDeNotas();
		mostrarDiccionario();
	}
	
	//Alumnos
	public static void introducirAlumnos() {
		String nombre;
		String seguir;
		
		do {
			nombre = JOptionPane.showInputDialog("Introduce el nombre del Alumno: ");
			listaAlumnos.add(nombre);
			seguir = JOptionPane.showInputDialog("Quieres introducir otro alumno si/no:");
			
		} while (seguir.equals("si"));	
		
		System.out.println("Lista Alumnos:");
		for (int i = 0; i < listaAlumnos.size(); i++) {
			System.out.print(listaAlumnos.get(i) + " ");
		}
	}	
	
	//Notas de los alumnos
	public static void introducirNotas() {
		int nota;			
		
		numAsignaturas = Integer.parseInt(JOptionPane.showInputDialog("Introduce el numero de asignaturas: "));
		for (int i = 0; i < listaAlumnos.size(); i++) {
			for (int j = 0; j < numAsignaturas; j++) {
				nota = Integer.parseInt(JOptionPane.showInputDialog("Introduce la nota de: " + listaAlumnos.get(i)));
				listaNotas.add(nota);
			}
		}
		//Mostrar Notas
		System.out.println("\nLista Notas:");
		for (int i = 0; i < listaNotas.size(); i++) {
			System.out.print(listaNotas.get(i) + " ");
		}			
	}
	
	//Calcular Media
	public static void mediaDeNotas() {
		int num = 0;
		int contador = 0;
		
		for (int i = 0; i < listaNotas.size(); i++) {
			num += listaNotas.get(i);
			contador++;
			if(contador == numAsignaturas) {
				num = num/numAsignaturas; //Hacemos la media
				listaNotaMedia.add(num);
				contador = 0;
				num = 0;
			}
		}		
		
		//Mostrar Lista media notas
		System.out.println("\nLista media de notas:");
		for (int i = 0; i < listaNotaMedia.size(); i++) {
			System.out.print(listaNotaMedia.get(i) + " ");
		}			
	}
	
	public static void mostrarDiccionario() {
		for (int i = 0; i < listaAlumnos.size(); i++) {
			contenedor.put(listaAlumnos.get(i), listaNotaMedia.get(i));
		}
		//Mostrar hastable
		JOptionPane.showMessageDialog(null, "\nNOTAS FINALES: " + contenedor);		
	}

}
