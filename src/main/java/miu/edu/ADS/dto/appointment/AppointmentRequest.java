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

    @NotNull(message = "Surgery Id cannot be null")
    private Integer surgeryId;

    @NotNull(message = "Patient Id cannot be null")
    private Integer patientId;

    @NotNull(message = "Dentist Id cannot be null")
    private Integer dentistId;
}
