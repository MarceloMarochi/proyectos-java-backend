package ar.edu.utn.frc.alquiler_peliculas.dtos;

import lombok.Data;

@Data
public class InventoryDTO {

    private long inventoryId;
    private long filmId;
    private long storeId;

}
