package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class EtudiantTest {


    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Préparer les données mockées
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant(1L, "Nom1", "Prenom1", 12345678L, null, null));
        etudiants.add(new Etudiant(2L, "Nom2", "Prenom2", 87654321L, null, null));

        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Appeler la méthode et vérifier le résultat
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveEtudiant() {
        // Préparer un étudiant mocké
        Etudiant etudiant = new Etudiant(1L, "Nom1", "Prenom1", 12345678L, null, null);

        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // Appeler la méthode et vérifier le résultat
        Etudiant result = etudiantService.retrieveEtudiant(1L);

        assertNotNull(result);
        assertEquals("Nom1", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void testAddEtudiant() {
        // Préparer un étudiant mocké
        Etudiant etudiant = new Etudiant(0L, "Nom1", "Prenom1", 12345678L, null, null);

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Appeler la méthode et vérifier le résultat
        Etudiant result = etudiantService.addEtudiant(etudiant);

        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testModifyEtudiant() {
        // Préparer un étudiant mocké
        Etudiant etudiant = new Etudiant(1L, "Nom1", "Prenom1", 12345678L, null, null);

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Appeler la méthode et vérifier le résultat
        Etudiant result = etudiantService.modifyEtudiant(etudiant);

        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testRemoveEtudiant() {
        // Appeler la méthode pour supprimer un étudiant
        etudiantService.removeEtudiant(1L);

        // Vérifier que le repository a bien été appelé
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRecupererEtudiantParCin() {
        // Préparer un étudiant mocké
        Etudiant etudiant = new Etudiant(1L, "Nom1", "Prenom1", 12345678L, null, null);

        when(etudiantRepository.findEtudiantByCinEtudiant(12345678L)).thenReturn(etudiant);

        // Appeler la méthode et vérifier le résultat
        Etudiant result = etudiantService.recupererEtudiantParCin(12345678L);

        assertNotNull(result);
        assertEquals(12345678L, result.getCinEtudiant());
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(12345678L);
    }
}
