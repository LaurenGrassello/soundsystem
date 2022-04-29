package com.laurenodalen.lookify.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laurenodalen.lookify.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findAll();
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    
    @Query(value="SELECT * FROM users WHERE username=(:searchString)", nativeQuery=true)
    List<User> searchfriends(@Param("searchString")String searchString);
    
}
