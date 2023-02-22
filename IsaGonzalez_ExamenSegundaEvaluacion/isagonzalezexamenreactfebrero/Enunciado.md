# DESARROLLO DE INTERFACES DE USUARIO

## 2ª Evaluación - Curso 2022-2023

### 22 de febrero de 2023

Desarrollar una aplicación que gestione un catálogo de productos de una Ferretería.
Usa React. Para el back se hará uso del microservicio 'Productos'. La API se describe
más adelante. Descargar el archivo 'docker-compose.yml' y ejecutar el comando `$ sudo docker-compose up`
desde la carpeta donde se haya descargado el archivo, para arrancar el microservicio.

Como mínimo habrá dos componentes:

- Un componente mostrará, tabulado, un listado de todos los productos en el catálogo. Para cada producto se mostrará su
  nombre y su precio, cada uno en su columna.
- Otro componente tendrá dos cajas de texto, 'unidades' y 'total' y una etiqueta (un texto) 'Unidades de stock' que
  mostrará el contenido del atributo 'stock' del producto seleccionado en el componente.
    - Cuando el usuario introduzca una cantidad en 'unidades', automáticamente en 'total' aparecerá el cálculo de
      multiplicar ese número de unidades (que nunca debe superar la cantidad en stock) por el precio del producto
      seleccionado en ese momento.
    - El componente tendrá, además, una etiqueta y un botón. Si el número de unidades en stock del producto seleccionado
      es menor que 10, la etiqueta tendrá la leyenda 'Unidades bajas', en rojo. Si es >= 10 el texto mostrado será '
      Unidades OK', en verde. Usar renderizado condicional. Cuando se pulse el botón, se incrementará el stock del
      producto en 10 unidades, y se actualizará la BBDD y el componente.
    - Las funcionalidades del componente, sólo se activarán si el atributo 'active' del producto seleccionado es
      verdadero (true). Si no, o no ha sido seleccionado un producto, aparecerán deshabilitados las cajas de texto y el
      botón.

El usuario debe ser informado del resultado y estado de las operaciones.

Debe comprobarse el formato adecuado de los datos introducidos en los campos de los formularios (caja de texto '
unidades' del componente: número > 0 y <= 'stock').

## Microservicio Productos
### Descripción del modelo (ProductosVO)

- id: String
- stock: Integer // actúa como cantidad
- name: String
- brand: String // actúa como descripción
- price: Double
- active: Boolean

### Descripción del Repository y API request
http://localhost:8080/api/v1/products/{id}

GET HTTP request: @GetMapping("/products")
- findAll():
  - getAllProducts(): returns List of Products
  - getProductById(): returns Product by given id

POST HTTP request: @PostMapping("/products")
A new Product will be created by Repository.save() method.

PUT HTTP request: @PutMapping("/products")
- updateProduct(): receives id and a Product payload

DELETE HTTP request: @DeleteMapping("/products/{id}")
- deleteProduct(): delete a Product document with given id