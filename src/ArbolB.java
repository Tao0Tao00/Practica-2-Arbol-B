import java.util.Queue;
import java.util.LinkedList;


/**
 * Clase que representa un Arbol B de orden 4, con metodos para insertar, eliminar y buscar llaves
 */
public class ArbolB {

    private Nodo raiz;

    /**
     * Metodo recursivo que busca una llave en el arbol B
     * @param nodo nodo actual del arbol
     * @param x llave a buscar
     * @return true si la llave se encuentra en el arbol, false en caso contrario
     */
    private boolean buscar(Nodo nodo, int x){
        if(nodo == null)
            return false;

        int i = 0;
        while(i < nodo.llaves.size() && x > nodo.llaves.get(i))
            i++;
        if(i < nodo.llaves.size() && nodo.llaves.get(i) == x)
            return true;
        if (nodo.hoja()){
            return false;
        }
        return buscar(nodo.hijos.get(i), x);
        
    }

    /**
     * Metodo auxiliar que busca una llave en el arbol B
     * @param x llave a buscar
     * @return true si la llave se encuentra en el arbol, false en caso contrario
     */
    public boolean busqueda(int x){
        return  buscar(raiz, x);
    }


    /**
     * Metodo que elimina una llave del arbol B
     * @param x llave a eliminar
     */    
    public void eliminar(int x){
        if(raiz == null || !busqueda(x))
            return;
        eliminarMet(raiz, x);
        if(raiz.llaves.isEmpty() && !raiz.hoja()){
            raiz = raiz.hijos.get(0);
        }
    }
    
    /**
     * Metodo auxiliar que elimina una llave del arbol B
     * @param nodo nodo actual del arbol
     * @param x llave a eliminar
     */
    private void eliminarMet(Nodo nodo, int x){
        int i = 0;
        while(i < nodo.llaves.size() && x > nodo.llaves.get(i))
            i++;
        boolean enc = i < nodo.llaves.size() && nodo.llaves.get(i) == x;

        if(enc){
            if(nodo.hoja()){
                nodo.llaves.remove(i);
                return;
            }
            Nodo izq = nodo.hijos.get(i), der = nodo.hijos.get(i + 1);
            if(izq.llaves.size() > 1){
                int pred = maximo(izq);
                nodo.llaves.set(i, pred);
                eliminarMet(izq, pred);
                noHayLlaves(nodo, i);
            }else if (der.llaves.size() >1){
                int suc = minimo(der);
                nodo.llaves.set(i, suc);
                eliminarMet(der, suc);
                noHayLlaves(nodo, i + 1);
            }else{
                fusionar(nodo, i);
                eliminarMet(nodo.hijos.get(i), x);
                noHayLlaves(nodo, i);
            }
        }else{
            if(nodo.hoja())
                return;
            eliminarMet(nodo.hijos.get(i), x);
            noHayLlaves(nodo, i);
        }
    }

    /**
     * Metodo auxiliar que encuentra el valor maximo de un nodo
     * @param nodo nodo actual del arbol
     * @return valor maximo del nodo
     */
    private int maximo(Nodo nodo){
        while(!nodo.hoja()) nodo = nodo.hijos.get(nodo.hijos.size() - 1);
        return nodo.llaves.get(nodo.llaves.size() - 1);
    }

    /**
     * Metodo auxiliar que encuentra el valor minimo de un nodo
     * @param nodo nodo actual del arbol
     * @return valor minimo del nodo
     */
    private int minimo(Nodo nodo){
        while(!nodo.hoja()) nodo = nodo.hijos.get(0);
        return nodo.llaves.get(0);
    }

    /**
     * Metodo auxiliar que verifica si un nodo tiene llaves y si no las tiene, realiza las 
     * operaciones necesarias para mantener el arbol B balanceado
     * @param padre nodo padre del nodo actual
     * @param indice indice del nodo actual en el arreglo de hijos del nodo padre
     */
    private void noHayLlaves(Nodo padre, int indice){
        Nodo hijo = padre.hijos.get(indice);
        if(!hijo.estaSubocupado())
            return;
        Nodo izqHermano = indice > 0 ? padre.hijos.get(indice - 1) : null;
        Nodo derHermano = indice < padre.hijos.size() - 1 ? padre.hijos.get(indice + 1) : null;

        if(izqHermano != null && izqHermano.llaves.size() > 1){
            int sep = padre.llaves.get(indice - 1);
            hijo.llaves.add(0, sep);
            padre.llaves.set(indice - 1, izqHermano.llaves.remove(izqHermano.llaves.size() -1));
            if(!izqHermano.hoja())
                hijo.hijos.add(0, izqHermano.hijos.remove(izqHermano.hijos.size() - 1));
        }else if(derHermano != null && derHermano.llaves.size() > 1){
            int sep = padre.llaves.get(indice);
            hijo.llaves.add(sep);
            padre.llaves.set(indice, derHermano.llaves.remove(0));
            if(!derHermano.hoja())
                hijo.hijos.add(derHermano.hijos.remove(0));
        }else if(izqHermano != null){
            fusionar(padre, indice - 1);
        }else if(derHermano != null){
            fusionar(padre, indice);
        }
    }

    /**
     * Metodo auxiliar que fusiona dos nodos hijos de un nodo padre
     * @param padre nodo padre de los nodos a fusionar
     * @param indIzq indice del nodo izquierdo en el arreglo de hijos del nodo padre
     */
    private void fusionar(Nodo padre, int indIzq){
        Nodo izq = padre.hijos.get(indIzq);
        Nodo der = padre.hijos.get(indIzq + 1);
        int sep = padre.llaves.remove(indIzq);
        padre.hijos.remove(indIzq + 1);
        izq.llaves.add(sep);
        izq.llaves.addAll(der.llaves);
        izq.hijos.addAll(der.hijos);
    }

    
    /**
     * Metodo que inserta una llave en el arbol B
     * @param k llave a insertar
     */
    public void insertar (int k) {
        if (busqueda(k)) {
            return;
        }
    
        if (raiz == null) {
            Nodo n = new Nodo();
            n.llavesOrdenadas(k);
            raiz = n;
            return;
        }

        Split split = insertarAux(raiz, k);
        if (split != null) {
            Nodo nuevaRaiz = new Nodo();
            nuevaRaiz.llaves.add(split.llavePromovida);
            nuevaRaiz.hijos.add(raiz);
            nuevaRaiz.hijos.add(split.nuevoHijo);
            raiz = nuevaRaiz;
        }
    }

    /**
     * Metodo auxiliar que inserta una llave en el arbol B
     * @param nodo nodo actual del arbol
     * @param k llave a insertar
     * @return un objeto Split si el nodo se divide, null en caso contrario
     */
    private Split insertarAux (Nodo nodo, int k) {
        if (nodo.hoja()) {
            nodo.llavesOrdenadas(k);
            if (nodo.llaves.size() > 3) {
                int promovida = nodo.llaves.get(2);

                Nodo nuevoDer = new Nodo ();
                nuevoDer.llaves.add(nodo.llaves.remove(3));
                nodo.llaves.remove(2);
                return new Split(promovida, nuevoDer);
            }
            return null;
        } else {
            int i = 0;
            while (i < nodo.llaves.size() && k > nodo.llaves.get(i)) {
                i ++;
            }
            Split split = insertarAux(nodo.hijos.get(i), k);

            if (split == null) {
                return null;
            }

            nodo.llaves.add(i, split.llavePromovida);
            nodo.hijos.add(i + 1, split.nuevoHijo);

            if (nodo.llaves.size() > 3) {
                int promovida = nodo.llaves.get(2);
                Nodo nuevoDer = new Nodo();
                nuevoDer.llaves.add(nodo.llaves.remove(3));

                nuevoDer.hijos.add(nodo.hijos.remove(3));
                nuevoDer.hijos.add(nodo.hijos.remove(3));
                nodo.llaves.remove(2);
                return new Split(promovida, nuevoDer);
            }
            return null;
        }
    }

    /**
     * Clase auxiliar que representa el resultado de una división de un nodo
     * Contiene la llave promovida y el nuevo nodo hijo creado
     */
    private class Split {
        int llavePromovida;
        Nodo nuevoHijo;

        public Split(int llavePromovida, Nodo nuevoHijo) {
            this.llavePromovida = llavePromovida;
            this.nuevoHijo = nuevoHijo;
        }
    }

    /**
     * Metodo que imprime el arbol B en niveles, mostrando las llaves de cada nodo
     * y separando los niveles con un salto de linea
     */
    public void imprimirArbol() {
        if (raiz == null) {
            return;
        }
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        int numNivel = 0;
        while (!cola.isEmpty()) {
            int nivel = cola.size();
            System.out.print("Nivel " + numNivel + ": ");
            for (int i = 0; i < nivel; i++) {
                Nodo actual = cola.poll();
                System.out.print("[");
                for (int j = 0; j < actual.llaves.size(); j++) {
                    System.out.print(actual.llaves.get(j));
                    if (j < actual.llaves.size() - 1) System.out.print("|");
                }
                System.out.print("] ");
                for (Nodo hijo : actual.hijos) {
                    cola.add(hijo);
                }
            }
            System.out.println();
            numNivel++;
        }
    }
}