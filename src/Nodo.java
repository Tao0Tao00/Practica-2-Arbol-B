import java.util.ArrayList;

public class Nodo{

	ArrayList<Nodo> hijos;
	ArrayList<Integer> llaves;
	boolean esHoja;

	 /**
     * Constructor de la clase Nodo,inicializa los atributos;
     */
	public Nodo(){
		this.hijos = new ArrayList<Nodo>();
		this.llaves = new ArrayList<Integer>();
		this.esHoja = true;
	}

	/**
     * Metodo que verifica si es hoja un Nodo
     * @return true si es hoja
     */
	public boolean hoja(){
		return hijos.isEmpty();
	}

	/**
     * Metodo que ordena las llaves por cada nodo
     * @
     */
	public void llavesOrdenadas(int nuevaLlave){
		int posicion = llaves.size();

		for (int i = llaves.size() - 1; i >= 0; i--) {
			if (nuevaLlave < llaves.get(i)) {
				posicion = i;
			}
		}

		llaves.add(posicion, nuevaLlave);
	}

	/**
     * Metodo que muestra si un Nodo esta lleno
     * @return true si esta lleno
     */
	public boolean estaLleno(){
		return llaves.size() == 3;
	}

	/**
     * Metodo que se encarga de ver si un nodo esta subocupado 
     * es decir cuando las llaves es = 1
     */
	public boolean estaSubocupado(){
		return llaves.size() <= 1;
	}
}