package riccardogulin.u5d4jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import riccardogulin.u5d4jpa.dao.IUsersDAO;
import riccardogulin.u5d4jpa.entities.User;
import riccardogulin.u5d4jpa.exceptions.ItemNotFoundException;

@Component
@Slf4j
public class UsersRunner implements CommandLineRunner {
//
	@Autowired // Spring hai un Bean di tipo UsersDAO nel tuo "scatolone" (application
				// context)?
	private IUsersDAO uDAO;
	@Override
	public void run(String... args) throws Exception {
		User aldo = User.builder().name("Giacomo").surname("Poretti").email("aldo@baglio.it").build();
		// User aldo = new User("Aldo", "Baglio", "aldo@baglio.it");

		// uDAO.save(aldo);

		try {
			User giovanni = User.builder().name("Giovanni").surname("Storti").email("aldo@baglio.it").build();
			// uDAO.findByIdAndUpdate(1, giovanni);

		} catch (ItemNotFoundException e) {
			log.error(e.getMessage());
		}
		// log.info("Numero cancellati: " + numeroModificati);

		try {

			uDAO.findByIdAndDelete(102);
			// log.info("Numero cancellati: " + numeroCancellati);
		} catch (ItemNotFoundException e) {
			log.error(e.getMessage());
		}
		log.info("********************* COUNT ********************");
		log.info("Count --> " + uDAO.count());
		try {
			log.info("********************* FIND BY ID ********************");
			log.info(uDAO.findById(52).toString());

		} catch (ItemNotFoundException e) {
			log.error(e.getMessage());
		}
		log.info("********************* FIND ALL ********************");
		uDAO.findAll().forEach(user -> log.info(user.toString()));

		log.info("********************* FIND ALL IGNORE CASE ********************");
		uDAO.findByPartialNameIgnoreCase("g").forEach(user -> log.info(user.toString()));
	}

}