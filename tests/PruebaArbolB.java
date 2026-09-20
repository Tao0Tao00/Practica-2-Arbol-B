public class PruebaArbolB {

    public static void main(String[] args) {
        ArbolB arbol = new ArbolB();

        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(10);
        arbol.insertar(30);
        arbol.insertar(50);
        arbol.insertar(60);
        arbol.insertar(70);
        arbol.insertar(5);
        arbol.insertar(15);
        arbol.insertar(25);
        arbol.insertar(35);
        arbol.insertar(45);

        arbol.imprimirArbol();
        mostrarBusqueda(arbol, 35);
        mostrarBusqueda(arbol, 99);

        arbol.eliminar(25);
        arbol.eliminar(10);
        arbol.eliminar(70);
        arbol.eliminar(5);

        arbol.imprimirArbol();
        mostrarBusqueda(arbol, 25);
        mostrarBusqueda(arbol, 35);
    }

    private static void mostrarBusqueda(ArbolB arbol, int x) {
        String resultado;
        if (arbol.busqueda(x)) {
            resultado = "FOUND";
        } else {
            resultado = "NOT_FOUND";
        }
        System.out.println("buscar(" + x + ") -> " + resultado);
    }
}