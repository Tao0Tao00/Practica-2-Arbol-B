
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

    
    public void eliminar(int x){
        if(raiz == null || !busqueda(x))
            return;
        eliminarMet(raiz, x);
        if(raiz.llaves.isEmpty() && !raiz.hoja()){
            raiz = raiz.hijos.get(0);
        }
    }

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
            }else if (der.llaves.size() >1){
                int suc = minimo(der);
                nodo.llaves.set(i, suc);
            }else{
                eliminarMet(nodo.hijos.get(i), x);
            }
        }else{
            if(nodo.hoja())
                return;
            eliminarMet(nodo.hijos.get(i), x);
        }
    }

    private int maximo(Nodo nodo){
        while(!nodo.hoja()) nodo = nodo.hijos.get(nodo.hijos.size() - 1);
        return nodo.llaves.get(nodo.llaves.size() - 1);
    }

    private int minimo(Nodo nodo){
        while(!nodo.hoja()) nodo = nodo.hijos.get(0);
        return nodo.llaves.get(0);
    }


    
}