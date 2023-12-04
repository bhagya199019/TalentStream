package com.bitlabs.App.dto;

import lombok.Data;

@Data
public class NewPasswordRequestDTO {

	private String newPassword;
	private String confirmPassword;
	
}
