package com.example.calucalteage.controller;

import com.example.calucalteage.configuration.UserControllerAdvice;
import com.example.calucalteage.responseDto.UserDto;
import com.example.calucalteage.service.UserService;
import com.example.calucalteage.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	private final UserService gameService = mock(UserServiceImpl.class);
	private final UserController gameController = new UserController(gameService);

	@Test
	@SneakyThrows
	public void calculateAgeRequest() {

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(gameController)
				.setControllerAdvice(new UserControllerAdvice()).build();

		UserDto userDto = new UserDto("Name", "Lastname", 18);
		when(gameService.findById(eq(2))).thenReturn(userDto);
		mockMvc.perform(get("/users/2"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{" +
						"name:Name," +
						"lastname:Lastname," +
						"age:18" +
						"}"));
	}
}
