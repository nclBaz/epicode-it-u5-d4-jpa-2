package riccardogulin.u5d4jpa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import riccardogulin.u5d4jpa.entities.User;
import riccardogulin.u5d4jpa.exceptions.ItemNotFoundException;

@Service // Specializzazione di @Component
@Slf4j
public class UsersService implements IUsersDAO {
	@Autowired
	private UsersRepository usersRepo;

	public void save(User user) {
		// eventuale logica addizionale custom...
		usersRepo.save(user);
		log.info(user.getName() + " salvato!");
	}

	public List<User> findAll() {
		return usersRepo.findAll();
	}

	public User findById(int id) throws ItemNotFoundException {
//		Optional<User> user = usersRepo.findById(id);
//
//		if (user.isPresent()) {
//			return user.get();
//		} else {
//			throw new ItemNotFoundException(id);
//		}
		return usersRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
	}

	public void findByIdAndUpdate(int id, User user) throws ItemNotFoundException {
		User found = this.findById(id); // Lo cerco nel db
		// poi aggiorno i suoi dati con quelli passati come parametro
		found.setId(id);
		found.setName(user.getName());
		found.setSurname(user.getSurname());
		found.setEmail(user.getEmail());
		// risalvo su db l'utente modificato
		usersRepo.save(found);
	}

	public void findByIdAndDelete(int id) throws ItemNotFoundException {
		User found = this.findById(id);
		usersRepo.delete(found);
	}

	public long count() {
		return usersRepo.count();
	}

	public List<User> findByPartialNameIgnoreCase(String name) {
		return usersRepo.findByNameStartingWithIgnoreCase(name);
	}
}
