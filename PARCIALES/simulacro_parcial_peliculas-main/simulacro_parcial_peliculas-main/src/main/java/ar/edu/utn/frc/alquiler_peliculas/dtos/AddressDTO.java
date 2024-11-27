package ar.edu.utn.frc.alquiler_peliculas.dtos;

import lombok.Data;

@Data
public class AddressDTO {

    private long addressId;
    private String address;
    private long cityId;
    private String postalCode;

}
