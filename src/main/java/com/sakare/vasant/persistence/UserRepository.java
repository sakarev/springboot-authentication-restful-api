/**
 * 
 */
package com.sakare.vasant.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakare.vasant.domain.User;

/**
 * @author vasant_sakre
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
