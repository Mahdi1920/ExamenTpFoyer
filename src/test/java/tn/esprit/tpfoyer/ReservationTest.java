package tn.esprit.tpfoyer;

import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class ReservationTest {
	@InjectMocks
	ReservationServiceImpl reservationService;

	@Mock
	ReservationRepository reservationRepository;

	Reservation reservation = Reservation.builder()
			.idReservation("res")
			.anneeUniversitaire(new Date())
			.estValide(true)
			.etudiants(new HashSet<>())
			.build();


	List<Reservation> litReservations = new ArrayList<>() {
		{
			add(Reservation.builder()
					.idReservation("res1")
					.anneeUniversitaire(new Date(2025, Calendar.JANUARY,15))
					.estValide(true)
					.etudiants(new HashSet<>())
					.build());
			add(Reservation.builder()
					.idReservation("res2")
					.anneeUniversitaire(new Date(2024, Calendar.DECEMBER,10))
					.estValide(false)
					.etudiants(new HashSet<>())
					.build());
		}
	};
	@Test
	void testRetrieveReservation() {
		// Mock repository response
		Mockito.when(reservationRepository.findById("res")).thenReturn(Optional.of(reservation));

		// Call service method
		Reservation result = reservationService.retrieveReservation("res");

		// Assertions
		Assertions.assertNotNull(result);
		Assertions.assertEquals("res", result.getIdReservation());
		Mockito.verify(reservationRepository, Mockito.times(1)).findById("res");
	}
}
