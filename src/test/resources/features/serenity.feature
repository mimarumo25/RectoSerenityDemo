#language: es
#Authos:miguel Rubide


Característica: Inicio de sesion con usuario y password en la aplicacion serenity.is, crear nuevos clientes y pedidos
  Como usuario, Quiero iniciar sesion en serenity.is con usuario y password
  Para crear nuevos clientes y crear ordenes de pedido con sus respectivos productos


  Antecedentes: Inicio de sección con usuario y password genéricos
    Dado que miguel quiere iniciar sesion con  "admin" y "serenity" en serenity.is

  @Clientes @Regresion
  Esquema del escenario: Crear un nuevo cliente exitoso con todos los campos disponibles
    Dado que miguel quiere crear un nuevo cliente
    Cuando miguel ingresa los datos del nuevo cliente y da clic en el boton guardar
      | id   | empresa   | nContacto   | tContacto   | representante   | direccion   | pais   | ciudad   | region   | codigoPostal   | telefono   | fax   | fContacto   | uContactoPor   | correo   |
      | <id> | <empresa> | <nContacto> | <tContacto> | <representante> | <direccion> | <pais> | <ciudad> | <region> | <codigoPostal> | <telefono> | <fax> | <fContacto> | <uContactoPor> | <correo> |
    Entonces miguel debera ver el nuevo cliente en la lista general de cliente registrados en el sistema <id>
    Ejemplos:
      | id    | empresa          | nContacto    | tContacto       | representante | direccion       | pais      | ciudad       | region    | codigoPostal | telefono  | fax        | fContacto  | uContactoPor   | correo           |
      | ABJDM | Banco Torre Alta | Juan Antomio | Gerente General | Laura Call    | Cra 14 # 14 -20 | Argentina | Buenos Aires | Sueroeste | 123456       | 321456789 | 6017458596 | 11/15/2022 | Michael Suyama | correo@gmail.com |


  @Pedidos @Regresion
  Esquema del escenario: Crear un nuevo pedido exitoso en el sistema
    Dado que miguel quiere crear un nuevo pedido ingresa los datos del pedido
      | cliente   | fPedido   | fEnvio   | empleado   |
      | <cliente> | <fPedido> | <fEnvio> | <empleado> |
    Cuando miguel ingresa los productos del pedido y da clic en el boton guardarm
      | producto   | precio   | cantidad   |
      | <producto> | <precio> | <cantidad> |
    Entonces miguel debera ver el nuevo pedido en la lista general de pedidos registrados en el sistema
    Ejemplos:
      | cliente                                    | fPedido    | fEnvio     | empleado       | producto          | precio | cantidad |
      | Ana Trujillo Emparedados y helados [ANATR] | 11/23/2022 | 11/26/2022 | Michael Suyama | Camembert Pierrot | 19.00  | 15       |

