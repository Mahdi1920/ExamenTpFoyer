package tn.esprit.tpfoyer;

		import org.junit.jupiter.api.Test;
		import org.junit.jupiter.api.extension.ExtendWith;
		import org.mockito.InjectMocks;
		import org.mockito.Mock;
		import org.mockito.junit.jupiter.MockitoExtension;
		import tn.esprit.tpfoyer.entity.Universite;
		import tn.esprit.tpfoyer.repository.UniversiteRepository;
		import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

		import java.util.Arrays;
		import java.util.List;
		import java.util.Optional;

		import static org.junit.jupiter.api.Assertions.*;
		import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniversiteTest {

	@Mock
	private UniversiteRepository universiteRepository;

	@InjectMocks
	private UniversiteServiceImpl universiteService;

	@Test
	void testRetrieveAllUniversites() {
		// Arrange
		Universite u1 = new Universite(1L, "Universite A", "Adresse A", null);
		Universite u2 = new Universite(2L, "Universite B", "Adresse B", null);
		when(universiteRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

		// Act
		List<Universite> universites = universiteService.retrieveAllUniversites();

		// Assert
		assertNotNull(universites);
		assertEquals(2, universites.size());
		verify(universiteRepository, times(1)).findAll();
	}

	@Test
	void testRetrieveUniversite() {
		// Arrange
		Universite u = new Universite(1L, "Universite A", "Adresse A", null);
		when(universiteRepository.findById(1L)).thenReturn(Optional.of(u));

		// Act
		Universite retrievedUniversite = universiteService.retrieveUniversite(1L);

		// Assert
		assertNotNull(retrievedUniversite);
		assertEquals("Universite A", retrievedUniversite.getNomUniversite());
		verify(universiteRepository, times(1)).findById(1L);
	}

	@Test
	void testAddUniversite() {
		// Arrange
		Universite u = new Universite(0L, "Universite A", "Adresse A", null);
		when(universiteRepository.save(u)).thenReturn(new Universite(1L, "Universite A", "Adresse A", null));

		// Act
		Universite savedUniversite = universiteService.addUniversite(u);

		// Assert
		assertNotNull(savedUniversite);
		assertEquals(1L, savedUniversite.getIdUniversite());
		verify(universiteRepository, times(1)).save(u);
	}

	@Test
	void testModifyUniversite() {
		// Arrange
		Universite u = new Universite(1L, "Universite Updated", "Adresse Updated", null);
		when(universiteRepository.save(u)).thenReturn(u);

		// Act
		Universite updatedUniversite = universiteService.modifyUniversite(u);

		// Assert
		assertNotNull(updatedUniversite);
		assertEquals("Universite Updated", updatedUniversite.getNomUniversite());
		verify(universiteRepository, times(1)).save(u);
	}

	@Test
	void testRemoveUniversite() {
		// Arrange
		Long universiteId = 1L;
		doNothing().when(universiteRepository).deleteById(universiteId);

		// Act
		universiteService.removeUniversite(universiteId);

		// Assert
		verify(universiteRepository, times(1)).deleteById(universiteId);
	}
}
