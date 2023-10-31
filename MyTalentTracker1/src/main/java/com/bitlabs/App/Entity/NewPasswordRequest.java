package com.bitlabs.App.Entity;

import lombok.Data;

@Data
public class NewPasswordRequest {

	private String newPassword;
	private String confirmPassword;
	private String email;
}
