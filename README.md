# Practica-2-Arbol-B

## 1. lenguaje utilizado;<br>
JAVA<br>

## 2. instrucciones para ejecutar el programa;<br>
_javac -d src/bin -cp src/bin src/*.java tests/*.java_<br>
_java -cp src/bin PruebaArbolB_<br>

## 3. instrucciones para ejecutar los casos de prueba;
_javac -d src/bin -cp src/bin src/*.java tests/*.java_<br>
_java -cp src/bin PruebaArbolB_<br>

## 4. explicación breve de la representación de un nodo;<br>
Es la **agrupación de llaves ordenadas en una lista**, con referencias a los nodos hijos y un indicador booleano de si es hoja.<br>

## 5. explicación de qué significa m = 4 y por qué cada nodo admite máximo tres llaves;<br>
m = 4 define **un orden en los arboles B delimitando** que cada codo tengas a lo mas 4 hijos y hasta 3 llaves por nodo.<br>

## 6. explicación de cómo se decide qué hijo seguir durante una búsqueda;<br>
Se van **comparando** las llaves de izquierda a derecha respecto a la llave buscada, hasta encontrar la primera que sea mayor a ella, lo cual indica la posición exacta del hijo por el que se debe descender.<br>

## 7. explicación de qué ocurre cuando un nodo alcanza cuatro llaves;
El nodo **se desborda** y se divide en dos nodos hermanos, promoviendo la llave mediana hacia el nodo padre para conservar el equilibrio(haciendo un split)<br>

## 8. explicación de la convención de promoción usada en la práctica;<br>
Se selecciona como llave promovida el elemento ubicado en el indice 2 del nodo desbordado, dejando las llaves de los índices 0 y 1 en el nodo original y moviendo la llave del índice 3 al nuevo hermano derecho.<br>

## 9. explicación breve de redistribución y fusión;<br>
La redistribución **toma prestada** una llave de un nodo hermano que tenga llaves de sobra cuando un nodo queda subocupado, mientras que la fusión **une el nodo** subocupado con su hermano y la llave separadora del padre en un solo nodo cuando ningun hermano puede prestar.<br>

## 10. respuesta a las preguntas marcadas en esta guía.<br>
 ### 10.1.-¿Por qué al insertar una llave nueva no podemos decidir el hijo únicamente comparando con la primera llave del nodo?<br>
No se puede decidir comparando solo con la primera llave porque las distintas llaves del nodo **se dividen hasta cuatro hijos distintos**, por lo que una primera comparación solo indica si el valor es menor a la primera llave o si esta a su derecha, sin decir en cual de los demas hijos corresponden a insertar<br>

documentar la solución en README.md. **

### 10.2. ¿Por qué una búsqueda no debe recorrer todos los hijos de un nodo?<br>
Ya que todos los hijos tienen el **orden estricto** de las llaves el cual determina un único camino válido, perservando igual su tiempo logaritmico.<br>