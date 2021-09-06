package com.hoaxify.ws.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import lombok.Data;

//getter setter ve toString metodlarını hazirlamak/
//kod kalabaligi olusturmak yerine Lombokun @Data anotasyonunu kullanabiliriz.
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull(message = "{hoaxify.constraint.username.NotNull.message}")
	@Size(min = 4, max = 255)//hibernate aksini belirtmedigimiz surece string alanin degerini default olarak varchar(255) belirler.
	@Column(unique = true)
	private String username;
	
	@NotNull
	@Size(min = 4, max = 255)
	private String displayName;
	
	@NotNull(message = "{hoaxify.constraint.password.Pattern.message}")
	@Size(min = 8, max = 255)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
	private String password;
	
}
