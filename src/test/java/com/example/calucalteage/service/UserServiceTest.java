package com.example.calucalteage.service;

import com.example.calucalteage.entity.User;
import com.example.calucalteage.exception.NotFoundUserException;
import com.example.calucalteage.repository.UserRepository;
import com.example.calucalteage.responseDto.UserDto;
import com.example.calucalteage.service.impl.UserServiceImpl;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Optional.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UserServiceTest {

	private final UserRepository userRepository = mock(UserRepository.class);
	private final UserService gameController = new UserServiceImpl(userRepository);

	@Test
	public void calculateAge() {
		Optional<User> user = of(new User(1, "Name", "Lastname", LocalDate.of(2001, 3, 3)));
		when(userRepository.findById(eq(1))).thenReturn(user);
		UserDto byId = gameController.findById(1);
		assertThat(byId.age()).isEqualTo(21);
	}

	@Test(expected = NotFoundUserException.class)
	public void calculateAgeWithNonExistUser() {
		Optional<User> user = empty();
		when(userRepository.findById(any())).thenReturn(user);
		gameController.findById(1);
	}
}
