# Tareas

1. Completar las siguientes clases

   * `FilmController` y `FilmServiceImpl`
   * `InvetoryController` y `InventoryServiceImpl`
    
2. Usar herencia con las clases `Staff` y `Customer` (busquen sobre `@MappedSuperclass`)
3. Realizar validación de datos al momento de crear o actualizar (usen las columnas de la BD como referencia)
    * Por ejemplo `firstName` y `lastName` no pueden tener mas de 45 caracteres.


#### Notas
Para probar el API `/api/customers/{id}/local_stores?filmId=1` tienen que crear un customer con `cityId` 300.