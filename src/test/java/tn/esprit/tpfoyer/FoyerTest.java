package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FoyerTest {

    @InjectMocks
    FoyerServiceImpl foyerService;

    @Mock
    FoyerRepository foyerRepository;

    @Test
    void testRetrieveAllFoyers() {
        // Mock data
        List<Foyer> foyers = new ArrayList<>();
        foyers.add(new Foyer(1L, "Foyer A", 100, null, null));
        foyers.add(new Foyer(2L, "Foyer B", 200, null, null));

        Mockito.when(foyerRepository.findAll()).thenReturn(foyers);

        // Test service method
        List<Foyer> result = foyerService.retrieveAllFoyers();

        // Assertions
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Mockito.verify(foyerRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testRetrieveFoyer() {
        // Mock data
        Foyer foyer = new Foyer(1L, "Foyer A", 100, null, null);
        Mockito.when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        // Test service method
        Foyer result = foyerService.retrieveFoyer(1L);

        // Assertions
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Foyer A", result.getNomFoyer());
        Mockito.verify(foyerRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testAddFoyer() {
        // Mock data
        Foyer foyer = new Foyer(1L, "Foyer A", 100, null, null);
        Mockito.when(foyerRepository.save(foyer)).thenReturn(foyer);

        // Test service method
        Foyer result = foyerService.addFoyer(foyer);

        // Assertions
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Foyer A", result.getNomFoyer());
        Mockito.verify(foyerRepository, Mockito.times(1)).save(foyer);
    }

    @Test
    void testModifyFoyer() {
        // Mock data
        Foyer foyer = new Foyer(1L, "Foyer A", 100, null, null);
        Foyer modifiedFoyer = new Foyer(1L, "Foyer B", 200, null, null);

        Mockito.when(foyerRepository.save(foyer)).thenReturn(modifiedFoyer);

        // Test service method
        Foyer result = foyerService.modifyFoyer(foyer);

        // Assertions
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Foyer B", result.getNomFoyer());
        Mockito.verify(foyerRepository, Mockito.times(1)).save(foyer);
    }

    @Test
    void testRemoveFoyer() {
        // Test service method
        foyerService.removeFoyer(1L);

        // Verify repository interaction
        Mockito.verify(foyerRepository, Mockito.times(1)).deleteById(1L);
    }
}
