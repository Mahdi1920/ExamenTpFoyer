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

/**
 * Test unitaire pour la classe BlocServiceImpl.
 * Utilise Mockito pour simuler les interactions avec BlocRepository.
 */
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

    /**
     * 🧪 Test de la méthode retrieveBloc.
     * Vérifie qu'un bloc est correctement retourné par son ID.
     */
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


}
