Feature: PetStore Example

  @testPet
  Scenario: Consulta de Mascota
    Given dato el servicio URL "https://petstore.swagger.io/v2/"
    When se consulta el servicio con el ID 6
    Then validar el codigo de respuesta sea 200
    And validar el nombre de la mascota "doggie"