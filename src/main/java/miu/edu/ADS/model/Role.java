package miu.edu.ADS.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@RequiredArgsConstructor
public enum Role {

	PATIENT(
			Set.of(Permission.PATIENT_WRITE, Permission.PATIENT_READ)
    ),

	DENTIST(
			Set.of(Permission.DENTIST_WRITE, Permission.DENTIST_READ)
    ),
	MANAGER(
			Set.of(Permission.MANAGER_WRITE, Permission.MANAGER_READ)
    );

	@Getter
	private final Set<Permission> permissions;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		var authorities = new ArrayList<GrantedAuthority>();
		for (var permission : permissions) {
			authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
		return authorities;
	}
}
