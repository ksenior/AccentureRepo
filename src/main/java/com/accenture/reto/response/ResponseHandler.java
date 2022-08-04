package com.accenture.reto.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static String SUCCESS = "EXITOSO";
	public static String NOTFOUND = "INFORMACION NO ENCONTRADA";
	public static String TECHNICAL = "ERROR TECNICO";
	public static String VALIDATION = "ERROR VALIDACION DE DATOS";
	
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", message);
            map.put("statusCode", status.value());
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,status);
    }
}
