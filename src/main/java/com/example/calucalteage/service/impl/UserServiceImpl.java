package com.example.calucalteage.service.impl;

import com.example.calucalteage.exception.NotFoundUserException;
import com.example.calucalteage.repository.UserRepository;
import com.example.calucalteage.responseDto.UserDto;
import com.example.calucalteage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static java.time.Period.between;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public UserDto findById(Integer id) {
		return userRepository.findById(id).map(user ->
						new UserDto(user.getName(), user.getLastname(), calculateAge(user.getBirthDate()))
				)
				.orElseThrow(NotFoundUserException::new);
	}

	private Integer calculateAge(LocalDate birthDate) {
		return between(birthDate, now()).getYears();
	}

}
