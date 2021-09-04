package com.hoaxify.ws.user;

import lombok.Data;

//getter setter ve toString metodlarını hazirlamak/
//kod kalabaligi olusturmak yerine Lombokun @Data anotasyonunu kullanabiliriz.
@Data
public class User {
	
	private String username;
	private String displayName;
	private String password;
	
}
