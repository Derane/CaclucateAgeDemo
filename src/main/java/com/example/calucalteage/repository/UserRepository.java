package com.example.calucalteage.repository;

import com.example.calucalteage.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Override
	Optional<User> findById(Integer integer);
}
