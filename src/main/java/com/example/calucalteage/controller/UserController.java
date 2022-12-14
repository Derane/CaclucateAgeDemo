package com.example.calucalteage.controller;

import com.example.calucalteage.responseDto.UserDto;
import com.example.calucalteage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable("id") Integer id) {
		return status(OK).body(this.userService.findById(id));
	}

}
