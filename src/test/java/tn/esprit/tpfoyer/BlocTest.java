package tn.esprit.tpfoyer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class BlocServiceTest {

    @InjectMocks
    BlocServiceImpl blocService;

    @Mock
    BlocRepository blocRepository;

    Bloc bloc;
    List<Bloc> listBlocs;

    @BeforeEach
    void setUp() {
        bloc = Bloc.builder()
                .idBloc(1L)
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .build();

        listBlocs = Arrays.asList(
                Bloc.builder().idBloc(1L).nomBloc("Bloc A").capaciteBloc(100).build(),
                Bloc.builder().idBloc(2L).nomBloc("Bloc B").capaciteBloc(200).build()
        );
    }


    @Test
    void testRetrieveBlocById() {
        //Simule la réponse du repository pour l'ID 1
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

        // Récupère le bloc via le service.
        Bloc result = blocService.retrieveBloc(1L);

        // Vérifie que le bloc retourné est non nul
        assertNotNull(result);
        assertEquals(1L, result.getIdBloc());
        assertEquals("Bloc A", result.getNomBloc());

        // Vérifie que la méthode findById a été appelée une fois
        verify(blocRepository, times(1)).findById(1L);
    }

    @Test
    void testAddBloc() {
        //  Create a Bloc object that you want to add.
        Bloc newBloc = Bloc.builder()
                .idBloc(3L)
                .nomBloc("Bloc C")
                .capaciteBloc(200)
                .build();

        // Mock the repository to simulate saving a new Bloc.
        when(blocRepository.save(any(Bloc.class))).thenReturn(newBloc);

        // Call the service method to add the new bloc.
        Bloc result = blocService.addBloc(newBloc);

        // Verify that the result is not null and matches the expected values.
        assertNotNull(result);
        assertEquals("Bloc C", result.getNomBloc());
        assertEquals(200, result.getCapaciteBloc());
        assertEquals(3L, result.getIdBloc());

        // Verify that the repository save method was called once with the correct object.
        verify(blocRepository, times(1)).save(newBloc);
    }

    @Test
    void testModifyBloc() {
        // Arrange: Create an existing Bloc object and its updated version.
        Bloc existingBloc = Bloc.builder()
                .idBloc(1L)
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .build();

        Bloc updatedBloc = Bloc.builder()
                .idBloc(1L)
                .nomBloc("Bloc A Updated")
                .capaciteBloc(150)
                .build();

        // Mock repository behavior.
        when(blocRepository.save(any(Bloc.class))).thenReturn(updatedBloc);

        // Act: Call the modifyBloc method.
        Bloc result = blocService.modifyBloc(updatedBloc);

        // Assert: Verify that the returned Bloc has the updated properties.
        assertNotNull(result);
        assertEquals(1L, result.getIdBloc());
        assertEquals("Bloc A Updated", result.getNomBloc());
        assertEquals(150, result.getCapaciteBloc());

        // Verify repository interaction.
        verify(blocRepository, times(1)).save(updatedBloc);
    }




}
