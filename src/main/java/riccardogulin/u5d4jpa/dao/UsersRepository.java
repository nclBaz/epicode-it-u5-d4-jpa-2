package riccardogulin.u5d4jpa.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import riccardogulin.u5d4jpa.entities.User;

@Repository // Specializzazione di @Component
public interface UsersRepository extends JpaRepository<User, Integer> {

	// "SELECT u FROM User WHERE name= :name"
	Optional<User> findByName(String name);

	// "SELECT u FROM User WHERE name= :name AND surname = :surname"
	List<User> findByNameAndSurname(String name, String surname);

	// "SELECT u FROM User WHERE LOWER(u.name) LIKE CONCAT(LOWER(:name), '%')
	List<User> findByNameStartingWithIgnoreCase(String name);

	// DOCS https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
}
