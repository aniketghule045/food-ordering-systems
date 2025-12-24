package com.foodies.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public class ResponseDto<T> {
	private String status;
	private T data;
	
	public ResponseDto() {
	}

	public ResponseDto(String status, T data) {
		super();
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseDto [status=" + status + ", data=" + data + "]";
	}
	
	
	public static ResponseEntity<?> success(Object data) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", "success");
		
		if(data != null)
			map.put("data", data);
		return ResponseEntity.ok(map);
	}
}
