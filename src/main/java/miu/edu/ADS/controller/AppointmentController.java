package miu.edu.ADS.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.ADS.adapter.AppointmentAdapter;
import miu.edu.ADS.dto.appointment.AppointmentRequest;
import miu.edu.ADS.dto.appointment.AppointmentResponse;
import miu.edu.ADS.model.Appointment;
import miu.edu.ADS.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentAdapter appointmentAdapter;

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        Optional<List<Appointment>> optionalAppointments = appointmentService.findAllAppointments();
        return getListResponseEntity(optionalAppointments);
    }

    @GetMapping("{appointmentId}")
    public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable Integer appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentService.findAppointmentById(appointmentId);
        if (optionalAppointment.isPresent()) {
            AppointmentResponse appointmentResponse = appointmentAdapter.appointmentToResponse(optionalAppointment.get());
            return ResponseEntity.ok(appointmentResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{dentistId}")
    public ResponseEntity<List<AppointmentResponse>> findDentistAppointmentsById(@PathVariable Integer dentistId) {
        Optional<List<Appointment>> optionalAppointment = appointmentService.findDentistAppointmentsById(dentistId);
        return getListResponseEntity(optionalAppointment);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<AppointmentResponse>> findPatientAppointmentsById(@PathVariable Integer patientId) {
        Optional<List<Appointment>> optionalAppointment = appointmentService.findPatientAppointmentsById(patientId);
        return getListResponseEntity(optionalAppointment);
    }

    private ResponseEntity<List<AppointmentResponse>> getListResponseEntity(Optional<List<Appointment>> optionalAppointment) {
        if (optionalAppointment.isPresent() && !optionalAppointment.get().isEmpty()) {
            List<Appointment> appointments = optionalAppointment.get();
            List<AppointmentResponse> appointmentResponses = appointments.stream()
                    .map(appointmentAdapter::appointmentToResponse)
                    .toList();
            return ResponseEntity.ok(appointmentResponses);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        Optional<Appointment> appointment = appointmentService.createNewAppointment(appointmentRequest);
        return ResponseEntity.ok().body(appointmentAdapter.appointmentToResponse(appointment.get()));
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable Integer appointmentId, @RequestBody AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentAdapter.requestToAppointment(appointmentRequest);
        Optional<Appointment> updatedAppointment = appointmentService.updateAppointment(appointmentId, appointment);
        if (updatedAppointment.isPresent()) {
            return ResponseEntity.ok().body(appointmentAdapter.appointmentToResponse(updatedAppointment.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Integer appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }
}
