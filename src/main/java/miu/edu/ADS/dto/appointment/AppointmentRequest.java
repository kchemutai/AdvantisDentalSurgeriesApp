package miu.edu.ADS.dto.appointment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.ADS.model.AppointmentStatus;
import miu.edu.ADS.model.Dentist;
import miu.edu.ADS.model.Patient;
import miu.edu.ADS.model.Surgery;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequest {

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Time cannot be null")
    private LocalTime time;

    @NotNull(message = "Status cannot be null")
    private AppointmentStatus status;

    @NotNull(message = "Surgery cannot be null")
    private Surgery surgery;

    @NotNull(message = "Patient cannot be null")
    private Patient patient;

    @NotNull(message = "Dentist cannot be null")
    private Dentist dentist;
}
