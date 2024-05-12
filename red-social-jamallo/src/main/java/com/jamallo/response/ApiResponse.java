package com.jamallo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class ApiResponse {
	
	private String message;
	
	private boolean status;

}
