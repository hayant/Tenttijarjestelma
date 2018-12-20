package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
@Entity
public class Opettaja {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private @Version @JsonIgnore Long version;

	@JsonIgnore
	private String password;

	// "Käyttäjätunnus"
	private String name;
	private String etunimi;
	private String sukunimi;

	private String[] roles;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	protected Opettaja() {

	}

	public Opettaja(String etunimi, String sukunimi, String name, String password, String... roles) {

		this.name = name;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.setPassword(password);
		this.roles = roles;
	}

}
