package es.albergonpi.exercises.backuser.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

public class UserDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1407151231460188336L;

	@NotNull
	@Positive 
	protected Integer id;
	
	@NotBlank
	protected String name;
	
	@Past
	protected Date birthdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
