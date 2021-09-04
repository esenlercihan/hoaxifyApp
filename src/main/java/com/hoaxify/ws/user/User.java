package com.hoaxify.ws.user;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

import lombok.Data;

//getter setter ve toString metodlarını hazirlamak/
//kod kalabaligi olusturmak yerine Lombokun @Data anotasyonunu kullanabiliriz.
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	private String username;
	private String displayName;
	private String password;
	
}
