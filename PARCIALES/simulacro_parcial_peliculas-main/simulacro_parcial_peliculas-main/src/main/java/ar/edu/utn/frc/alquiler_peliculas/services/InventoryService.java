package ar.edu.utn.frc.alquiler_peliculas.services;

import ar.edu.utn.frc.alquiler_peliculas.dtos.InventoryDTO;
import ar.edu.utn.frc.alquiler_peliculas.models.Inventory;
import ar.edu.utn.frc.alquiler_peliculas.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryDTO> findAll() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public InventoryDTO findById(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory.map(this::convertToDto).orElse(null);
    }

    public InventoryDTO save(InventoryDTO inventoryDto) {
        Inventory inventory = convertToEntity(inventoryDto);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return convertToDto(savedInventory);
    }

    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }

    public InventoryDTO update(Long id, InventoryDTO inventoryDto) {
        Optional<Inventory> existingInventory = inventoryRepository.findById(id);
        if (existingInventory.isPresent()) {
            Inventory inventory = convertToEntity(inventoryDto);
            inventory.setInventoryId(id);
            Inventory updatedInventory = inventoryRepository.save(inventory);
            return convertToDto(updatedInventory);
        } else {
            return null;
        }
    }

    private InventoryDTO convertToDto(Inventory inventory) {
        InventoryDTO inventoryDto = new InventoryDTO();
        inventoryDto.setInventoryId(inventory.getInventoryId());
        inventoryDto.setFilmId(inventory.getFilm().getFilmId());
        inventoryDto.setStoreId(inventory.getStore().getStoreId());
        return inventoryDto;
    }

    private Inventory convertToEntity(InventoryDTO inventoryDto) {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryDto.getInventoryId());
        return inventory;
    }

}
