# EXAMEN DIU - 30/11/2022

Desarrollar un conversor de Euros a distintas monedas, y viceversa.
Cuando se carga la aplicación, se verá la ventana principal del conversor y un menú con una sola opción.
**Dicha opción de menú** carga en una ventana diferente NO MODAL (permitiendo que varias estén abiertas a la vez)
la imagen que se anexa en el classroom.
En esa misma ventana, deberá tener también una etiqueta que refleja el número total de monedas disponibles para
convertir
en cada momento y que se actualiza automáticamente con cada nueva moneda eliminada.

La interfaz principal del conversor constará de **tres etiquetas, dos cajas de texto, una lista desplegable, un botón**
con el texto 'convertir', y **otro** para eliminar.
La lista de monedas disponibles a convertir en/desde Euros, que se carga en la lista desplegable, se obtendrá de una
base
de datos llamada **conversor**, con una tabla llamada **monedas**, cuyos campos serán **codigo** (AUTO_INCREMENT int(
11)),
**nombre** (varchar(50) latin1_spanish_ci), **multiplicador** (float).

El texto de la etiqueta asociada a la caja de texto de la moneda que se usará para la conversión, será el de la moneda
seleccionada en la lista desplegable en cada momento.
Las otras dos etiquetas serán fijas.
Una tendrá el texto 'Euros' asociada a la caja de texto donde se introducen los Euros, y la otra tendrá el texto 'Lista
de monedas' asociada a la lista desplegable.
La conversión se ejecutará bien pulsando el **botón 'convertir'**, o pulsando **la tecla 'enter'** una vez se ha
introducido una cantidad.

Dependiendo de si la caja de texto en la que se escribe es la de Euros o la de otras monedas, se hará la conversión en
un sentido u otro, poniendo el resultado en caja de texto de la moneda destino de la conversión.

El algoritmo o lógica de conversión es el/la siguiente:

- Para una moneda **'Y'**, la conversión Euro a esa moneda ('Y') será Cantidad en Euros multiplicada por el
  multiplicador de 'Y', y la conversión inversa será Cantidad **Y** x (2-multiplicador).
- **Ejemplo. €-$**. Moneda Dólar Americano. Multiplicador 1,15. 50€ = 50 x 1,15 = 57,5$
- **Ejemplo. $-€**. Moneda Dólar Americano. Multiplicador 1,15. 60$ = 60 x (2 - 1,15) = 51€
- **Ejemplo. €-F**. Moneda Libra. Multiplicador 0,65. 60€ = 60 x 0,65 = 39F.
- **Ejemplo. F-€**. Moneda Libra esterlina. Multiplicador 0,65. 55F = 55 x (2 - 0,65) = 74,25€

La tabla **monedas** de la BBDD **conversor** tendrá los siguientes datos:
- codigo 1 - nombre Yen - multiplicador 1
- codigo 2 - nombre Lira - multiplicador 0,5

Usa un archivo CSS que de color azul a todos los botones.

Programar la aplicación de forma que permita fácilmente la creación de conversores de otro tipo de, por ejemplo, temperaturas.
Todos estos conversores tendrán como mínimo dos métodos.
Uno para convertir de una cantidad A a otra B en función de un algoritmo, y vicerversa.
Siendo A y B float.

Realizar la implementación considerando el modelo MVC y respetando principios S.O.L.I.D.

### NOTAS:
Imprescindible llamar a la BBDD, tabla y sus campos como se establece en el enunciado.
El proyecto debe entregarse en un archivo.
El multiplicador será un número mayor que 0 y menor que 2.
Debe controlarse.

Las cantidades a convertir deben ser positivas.
Debe controlarse que sean positivas.

El acceso a la BBDD se proporciona a través del .jar que el alumno debe descargar y añadir al BuildPath del proyecto.
Este .jar contiene las siguientes clases, ya implementadas.
- ExcepcionMoneda(package Modelo)
  - public ExcepcionMoneda(String s)
  - public String imprimirMensaje()
- MonedaVO(package Modelo)
  - String nombre;
  - Integer codigo;
  - float multiplicador;
  - public MonedaVO(String nombre, float multiplicador)
  - public MonedaVO(String nombre, float multiplicador, Integer codigo)
- MonedaRepository(package Modelo.repository)
  - public ArrayList<MonedaVO> ObtenerListaMonedas() throws ExcepcionMoneda;
  - public void addMoneda(MonedaVO moneda) throws ExcepcionMoneda;
  - public void deleteMoneda(MonedaVO moneda) throws ExcepcionMoneda;
  - public void editMoneda(MonedaVO moneda) throws ExcepcionMoneda;
  - public int lastId() throws ExcepcionMoneda;
- MonedaRepositoryImpl(package Modelo.repository.impl)
- ConexionJDBC(package Modelo.repository.impl)