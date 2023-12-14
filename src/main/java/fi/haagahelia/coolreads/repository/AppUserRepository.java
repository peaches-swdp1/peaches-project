package fi.haagahelia.coolreads.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.coolreads.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	AppUser findByUsername(String username);
	
	List<AppUser> findById(long id);
}