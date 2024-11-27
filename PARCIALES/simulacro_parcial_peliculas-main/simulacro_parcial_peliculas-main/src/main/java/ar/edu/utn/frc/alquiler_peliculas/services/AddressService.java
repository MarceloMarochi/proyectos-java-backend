package ar.edu.utn.frc.alquiler_peliculas.services;

import ar.edu.utn.frc.alquiler_peliculas.dtos.AddressDTO;
import ar.edu.utn.frc.alquiler_peliculas.models.Address;
import ar.edu.utn.frc.alquiler_peliculas.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<AddressDTO> findAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public AddressDTO findById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(this::convertToDto).orElse(null);
    }

    public AddressDTO save(AddressDTO addressDto) {
        Address address = convertToEntity(addressDto);
        Address savedAddress = addressRepository.save(address);
        return convertToDto(savedAddress);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    public AddressDTO update(Long id, AddressDTO addressDto) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (existingAddress.isPresent()) {
            Address address = convertToEntity(addressDto);
            address.setAddressId(id);
            Address updatedAddress = addressRepository.save(address);
            return convertToDto(updatedAddress);
        } else {
            return null;
        }
    }

    private AddressDTO convertToDto(Address address) {
        AddressDTO addressDto = new AddressDTO();
        addressDto.setAddressId(address.getAddressId());
        addressDto.setAddress(address.getAddress());
        addressDto.setCityId(address.getCity().getCityId());
        addressDto.setPostalCode(address.getPostalCode());
        return addressDto;
    }

    private Address convertToEntity(AddressDTO addressDto) {
        Address address = new Address();
        address.setAddressId(addressDto.getAddressId());
        address.setAddress(addressDto.getAddress());
        address.setPostalCode(addressDto.getPostalCode());
        return address;
    }

}
