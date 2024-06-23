package miu.edu.ADS.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dentists")
public class Dentist{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = true)
	private String specialization;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	@OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL)
	private List<Appointment> appointments = new ArrayList<>();

	public Dentist(String specialization, User user, List<Appointment> appointments) {
		this.specialization = specialization;
		this.user = user;
		this.appointments.addAll(appointments);
	}

	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
	}
}
