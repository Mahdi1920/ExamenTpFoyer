package tn.esprit.tpfoyer;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TestChambre {
    @Mock
    private ChambreRepository chambreRepository;
    @InjectMocks
    private ChambreServiceImpl chambreService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testRetrieveAllChambres() {
        // Arrange
        List<Chambre> chambres = Arrays.asList(new Chambre(), new Chambre());
        when(chambreRepository.findAll()).thenReturn(chambres);
        // Act
        List<Chambre> result = chambreService.retrieveAllChambres();
        // Assert
        assertEquals(chambres.size(), result.size());
        verify(chambreRepository, times(1)).findAll();
    }
    @Test
    void testRetrieveChambre() {
        // Arrange
        Long chambreId = 1L;
        Chambre chambre = new Chambre();
        when(chambreRepository.findById(chambreId)).thenReturn(Optional.of(chambre));
        // Act
        Chambre result = chambreService.retrieveChambre(chambreId);
        // Assert
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).findById(chambreId);
    }
    @Test
    void testAddChambre() {
        // Arrange
        Chambre chambre = new Chambre();
        when(chambreRepository.save(chambre)).thenReturn(chambre);
        // Act
        Chambre result = chambreService.addChambre(chambre);
        // Assert
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(chambre);
    }
    @Test
    void testModifyChambre() {
        // Arrange
        Chambre chambre = new Chambre();
        when(chambreRepository.save(chambre)).thenReturn(chambre);
        // Act
        Chambre result = chambreService.modifyChambre(chambre);
        // Assert
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(chambre);
    }
    @Test
    void testRemoveChambre() {
        // Arrange
        Long chambreId = 1L;
        // Act
        chambreService.removeChambre(chambreId);
        // Assert
        verify(chambreRepository, times(1)).deleteById(chambreId);
    }
    @Test
    void testRecupererChambresSelonTyp() {
        // Arrange
        TypeChambre typeChambre = TypeChambre.SIMPLE; // Assuming SIMPLE is an enum value
        List<Chambre> chambres = Arrays.asList(new Chambre(), new Chambre());
        when(chambreRepository.findAllByTypeC(typeChambre)).thenReturn(chambres);
        // Act
        List<Chambre> result = chambreService.recupererChambresSelonTyp(typeChambre);
        // Assert
        assertEquals(chambres.size(), result.size());
        verify(chambreRepository, times(1)).findAllByTypeC(typeChambre);
    }
    @Test
    void testTrouverChambreSelonEtudiant() {
        // Arrange
        long cin = 12345678L;
        Chambre chambre = new Chambre();
        when(chambreRepository.trouverChselonEt(cin)).thenReturn(chambre);
        // Act
        Chambre result = chambreService.trouverchambreSelonEtudiant(cin);
        // Assert
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).trouverChselonEt(cin);
    }
}