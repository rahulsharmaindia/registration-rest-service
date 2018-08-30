package com.skivvy.register.repo;

import com.skivvy.register.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

	Optional<User> findById(String id);
	List<User> findByName(String name);
}
