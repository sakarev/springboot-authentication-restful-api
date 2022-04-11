/**
 * 
 */
package com.sakare.vasant.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakare.vasant.domain.Token;

/**
 * @author vasant_sakre
 *
 */
public interface TokenRepository extends JpaRepository<Token, Long> {

	Token findByUsername(String username);

}
