@StoreOrder
Feature: Pruebas de Apis en Order

  @RegistrarOrder
  Scenario Outline: Registar Pedidos utilizando el Servicio
    Given el servicio URL "https://petstore.swagger.io/v2/"
    When se registra los datos del Pedido: <id>, <petId> y <quantity>
    Then valida que el ID de la Mascota sea <petId>
    And valida que la Cantidad sea <quantity>
    And valida que el Status es 200
    Examples:
      | id  | petId | quantity |
      | 210 | 3     |  4       |
      | 211 | 1     |  7       |

  @ConsultaOrder
  Scenario Outline: Consultar Pedido utilizando el Servicio
    Given el servicio URL "https://petstore.swagger.io/v2/"
    When se consulta el servicio con el <id>
    Then valida que el ID de la Mascota sea <petId>
    And valida que la Cantidad sea <quantity>
    And valida que el Status es 200
    Examples:
      | id  | petId | quantity |
      | 210 | 3     |  4       |
      | 211 | 1     |  7       |