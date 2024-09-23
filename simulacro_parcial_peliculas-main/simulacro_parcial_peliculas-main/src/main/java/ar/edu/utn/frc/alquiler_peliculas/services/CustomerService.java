package ar.edu.utn.frc.alquiler_peliculas.services;

import ar.edu.utn.frc.alquiler_peliculas.dtos.CustomerDTO;
import ar.edu.utn.frc.alquiler_peliculas.models.Customer;
import ar.edu.utn.frc.alquiler_peliculas.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CustomerDTO findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(this::convertToDto).orElse(null);
    }

    public CustomerDTO save(CustomerDTO customerDto) {
        Customer customer = convertToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDto(savedCustomer);
    }

    public CustomerDTO update(Long id, CustomerDTO customerDto) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = convertToEntity(customerDto);
            customer.setCustomerId(id);
            Customer updatedCustomer = customerRepository.save(customer);
            return convertToDto(updatedCustomer);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO convertToDto(Customer customer) {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setStoreId(customer.getStore().getStoreId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setActive(customer.isActive());
        customerDto.setCreateDate(customer.getCreateDate().toString());
        return customerDto;
    }

    private Customer convertToEntity(CustomerDTO customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setActive(customerDto.isActive());
        customer.setCreateDate(LocalDateTime.parse(customerDto.getCreateDate()));
        return customer;
    }


}
