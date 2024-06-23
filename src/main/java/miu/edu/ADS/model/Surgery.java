package miu.edu.ADS.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Surgery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String surgeryName;

	private String phoneNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address location;
	
	@OneToOne(mappedBy = "surgery", cascade = CascadeType.ALL)
	private Appointment appointment;
}
