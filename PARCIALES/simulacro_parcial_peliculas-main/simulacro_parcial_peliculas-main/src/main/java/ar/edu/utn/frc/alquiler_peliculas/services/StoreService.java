package ar.edu.utn.frc.alquiler_peliculas.services;

import ar.edu.utn.frc.alquiler_peliculas.dtos.StoreDTO;
import ar.edu.utn.frc.alquiler_peliculas.models.Store;
import ar.edu.utn.frc.alquiler_peliculas.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<StoreDTO> findAll() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public StoreDTO findById(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(this::convertToDto).orElse(null);
    }

    public StoreDTO save(StoreDTO storeDto) {
        Store store = convertToEntity(storeDto);
        Store savedStore = storeRepository.save(store);
        return convertToDto(savedStore);
    }

    public void deleteById(Long id) {
        storeRepository.deleteById(id);
    }

    public StoreDTO update(Long id, StoreDTO storeDto) {
        Optional<Store> existingStore = storeRepository.findById(id);
        if (existingStore.isPresent()) {
            Store store = convertToEntity(storeDto);
            store.setStoreId(id);
            Store updatedStore = storeRepository.save(store);
            return convertToDto(updatedStore);
        } else {
            return null;
        }
    }

    private StoreDTO convertToDto(Store store) {
        StoreDTO storeDto = new StoreDTO();
        storeDto.setStoreId(store.getStoreId());
        storeDto.setManagerStaffId(store.getManagerStaffId());
        storeDto.setAddressId(store.getAddress().getAddressId());
        return storeDto;
    }

    private Store convertToEntity(StoreDTO storeDto) {
        Store store = new Store();
        store.setStoreId(storeDto.getStoreId());
        store.setManagerStaffId(storeDto.getManagerStaffId());
        return store;
    }

}
