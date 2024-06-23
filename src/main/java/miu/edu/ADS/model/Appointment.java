package miu.edu.ADS.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate date;

	private LocalTime time;

	private AppointmentStatus status;

	@OneToOne(cascade = CascadeType.ALL)
	private Surgery surgery;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Patient patient;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Dentist dentist;

	public Appointment(LocalDate date, LocalTime time, AppointmentStatus status, Patient patient, Surgery surgery, Dentist dentist) {
		this.date = date;
		this.time = time;
		this.status = status;
		this.patient = patient;
		this.surgery = surgery;
		this.dentist = dentist;
	}
}
