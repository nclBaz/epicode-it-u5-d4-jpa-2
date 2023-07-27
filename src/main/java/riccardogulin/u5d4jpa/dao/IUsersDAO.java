package riccardogulin.u5d4jpa.dao;

import java.util.List;

import riccardogulin.u5d4jpa.entities.User;

public interface IUsersDAO {
	public void save(User user);

	public void findByIdAndUpdate(int id, User user);

	public void findByIdAndDelete(int id);

	public User findById(int id);

	public List<User> findAll();

	public long count();

	public List<User> findByPartialNameIgnoreCase(String name);
}

