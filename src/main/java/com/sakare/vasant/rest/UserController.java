/**
 * 
 */
package com.sakare.vasant.rest;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakare.vasant.domain.Token;
import com.sakare.vasant.domain.User;
import com.sakare.vasant.persistence.TokenRepository;
import com.sakare.vasant.persistence.UserRepository;
import com.sakare.vasant.reponse.AuthenticateReponse;
import com.sakare.vasant.rest.body.CredentialsBody;
import com.sakare.vasant.util.TokenUtil;

/**
 * @author vasant_sakre
 *
 */

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TokenRepository tokenRepository;
	Token token = new Token();

	@PostMapping(path = "/authenticate", produces = "application/json", consumes = "application/json")
	public ResponseEntity<AuthenticateReponse>  authenticateuser(@RequestBody CredentialsBody credentials,
			HttpServletResponse  response) {

		AuthenticateReponse authResp = new AuthenticateReponse();
		authResp.setMessage("Bad Request");
		authResp.setStatusCode(1);
		
		
		String username = credentials.getUsername().trim();
		String password = credentials.getPassword().trim();

		User user = userRepository.findByUsername(username);

		Timestamp expires = new Timestamp(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));

		if (user != null && user.getUsername() != null) {
			try {
				if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
					authResp.setFirstName(user.getFirst_name());
					authResp.setLastName(user.getLast_name());
					String tkn = TokenUtil.createToken(user);
					authResp.setToken(tkn);
					authResp.setExpiry(expires);
					token = tokenRepository.findByUsername(username);
					if (token == null) {
						token = new Token();
					}
					token.setToken(tkn);
					token.setUsername(username);
					token.setCreateDate(expires);
					tokenRepository.save(token);
					authResp.setMessage("Success");
					authResp.setStatusCode(0);
					return new ResponseEntity<AuthenticateReponse>(authResp, HttpStatus.OK);
				}
			} catch (UnsupportedEncodingException e) {
				authResp.setMessage("Internal Server Error");
				authResp.setStatusCode(-1);
				e.printStackTrace();
				return new ResponseEntity<AuthenticateReponse>(authResp, HttpStatus.INTERNAL_SERVER_ERROR);

			}

		}
		return new ResponseEntity<AuthenticateReponse>(authResp, HttpStatus.BAD_REQUEST);

	}
}
