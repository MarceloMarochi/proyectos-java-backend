package ar.edu.utn.frc.alquiler_peliculas.dtos;

import lombok.Data;

@Data
public class CustomerDTO {

    private long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private long storeId;
    private boolean active;
    private String createDate;

}
