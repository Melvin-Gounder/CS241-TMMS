package fj.usp.tmms.service;

import fj.usp.tmms.model.Junction;
import fj.usp.tmms.repository.TrafficControlLogRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EmergencyPriorityServiceTest {
    private EmergencyPriorityService service() { return new EmergencyPriorityService(mock(TrafficControlLogRepository.class)); }

    @Test void validIdentifierPasses() { assertTrue(service().validVehicleIdentifier("AB123")); }
    @Test void lowercaseIdentifierFails() { assertFalse(service().validVehicleIdentifier("ab123")); }
    @Test void threeLettersFails() { assertFalse(service().validVehicleIdentifier("ABC12")); }
    @Test void fourDigitsFails() { assertFalse(service().validVehicleIdentifier("A1234")); }
    @Test void sixCharactersFails() { assertFalse(service().validVehicleIdentifier("AB1234")); }
    @Test void unsupportedVehicleTypeRejected() {
        Junction j = new Junction("Nausori",1,-18.0,178.5); j.setId(1L);
        assertThrows(IllegalArgumentException.class, () -> service().startEmergency(j,"Taxi","AB123","tester"));
    }
}
