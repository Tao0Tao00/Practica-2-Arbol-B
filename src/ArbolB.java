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
        if (nodo.hoja()){
            return false;
        }
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

    private int maximo(Nodo nodo){
        while(!nodo.hoja()) nodo = nodo.hijos.get(nodo.hijos.size() - 1);
        return nodo.llaves.get(nodo.llaves.size() - 1);
    }

    private int minimo(Nodo nodo){
        while(!nodo.hoja()) nodo = nodo.hijos.get(0);
        return nodo.llaves.get(0);
    }

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

    private void fusionar(Nodo padre, int indIzq){
        Nodo izq = padre.hijos.get(indIzq);
        Nodo der = padre.hijos.get(indIzq + 1);
        int sep = padre.llaves.remove(indIzq);
        padre.hijos.remove(indIzq + 1);
        izq.llaves.add(sep);
        izq.llaves.addAll(der.llaves);
        izq.hijos.addAll(der.hijos);
    }
    
}