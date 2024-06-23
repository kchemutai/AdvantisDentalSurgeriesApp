package miu.edu.ADS.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

	PATIENT_WRITE("patient:write"),
	PATIENT_READ("patient:read"),
	DENTIST_WRITE("dentist:write"),
	DENTIST_READ("dentist:read"),
	MANAGER_WRITE("manager:write"),
	MANAGER_READ("manager:read");

	@Getter
	private final String permission;
}
