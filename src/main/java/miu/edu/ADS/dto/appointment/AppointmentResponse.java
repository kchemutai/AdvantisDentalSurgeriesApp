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
public class AppointmentResponse {
    private LocalDate date;
    private LocalTime time;
    private AppointmentStatus status;
    private Surgery surgery;
    private Patient patient;
    private Dentist dentist;
}
