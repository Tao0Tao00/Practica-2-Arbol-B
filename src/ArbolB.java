
public class ArbolB {

    private Nodo raiz;
    
    public boolean busqueda(int x){
        return  buscar(raiz, x);
    }

    private boolean buscar(Nodo nodo, int x){
        if(nodo == null)
            return false;

        int i = 0;
        while(i < nodo.llaves.size() && x > nodo.llaves.get(i))
            i++;
        if(i < nodo.llaves.size() && nodo.llaves.get(i) == x)
            return true;
        return buscar(nodo.hijos.get(i), x);
        
    }

    
}