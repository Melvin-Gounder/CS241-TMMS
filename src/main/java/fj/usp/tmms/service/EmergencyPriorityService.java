package fj.usp.tmms.service;

import fj.usp.tmms.model.Junction;
import fj.usp.tmms.model.TrafficControlLog;
import fj.usp.tmms.repository.TrafficControlLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Service
public class EmergencyPriorityService {
    private static final Pattern VEHICLE_ID = Pattern.compile("^[A-Z]{2}[0-9]{3}$");
    private static final Set<String> VEHICLE_TYPES = Set.of("AMBULANCE", "POLICE", "FIRE");

    private final Map<Long, LocalDateTime> emergencyUntil = new ConcurrentHashMap<>();
    private final Map<Long, LocalDateTime> overrideUntil = new ConcurrentHashMap<>();
    private final TrafficControlLogRepository logs;

    public EmergencyPriorityService(TrafficControlLogRepository logs) {
        this.logs = logs;
    }

    public boolean validVehicleIdentifier(String id) {
        return id != null && VEHICLE_ID.matcher(id.trim()).matches();
    }

    public void startEmergency(Junction junction, String type, String vehicleId, String user) {
        if (!validVehicleIdentifier(vehicleId)) {
            throw new IllegalArgumentException("Vehicle identifier must contain exactly two uppercase letters followed by three digits, for example AB123.");
        }
        String normalizedType = type == null ? "" : type.trim().toUpperCase(Locale.ROOT);
        if (!VEHICLE_TYPES.contains(normalizedType)) {
            throw new IllegalArgumentException("Unsupported emergency vehicle type.");
        }
        emergencyUntil.put(junction.getId(), LocalDateTime.now().plusSeconds(30));
        saveLog(junction, "EMERGENCY_PRIORITY", normalizedType + " " + vehicleId.trim() + " priority for 30 seconds", user);
    }

    public void startManualOverride(Junction junction, String user) {
        if (isEmergencyActive(junction.getId())) {
            throw new IllegalStateException("Manual override is blocked while emergency priority is active.");
        }
        overrideUntil.put(junction.getId(), LocalDateTime.now().plusSeconds(60));
        saveLog(junction, "MANUAL_OVERRIDE", "Manual signal override for 60 seconds", user);
    }

    public boolean isEmergencyActive(Long junctionId) {
        return emergencyUntil.getOrDefault(junctionId, LocalDateTime.MIN).isAfter(LocalDateTime.now());
    }

    public boolean isOverrideActive(Long junctionId) {
        return overrideUntil.getOrDefault(junctionId, LocalDateTime.MIN).isAfter(LocalDateTime.now());
    }

    private void saveLog(Junction junction, String action, String details, String user) {
        TrafficControlLog log = new TrafficControlLog();
        log.setJunction(junction);
        log.setActionType(action);
        log.setDetails(details);
        log.setPerformedBy(user);
        logs.save(log);
    }
}
