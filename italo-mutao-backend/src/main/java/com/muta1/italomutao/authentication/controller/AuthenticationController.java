package com.muta1.italomutao.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.muta1.italomutao.api.ApiResponse;
import com.muta1.italomutao.authentication.dto.AuthenticateRequestDTO;
import com.muta1.italomutao.authentication.dto.AuthenticateResponseDTO;
import com.muta1.italomutao.exception.CodeException;
import com.muta1.italomutao.exception.ServiceException;
import com.muta1.italomutao.security.jwt.JwtTokenUtil;
import com.muta1.italomutao.security.service.SecurityService;
import com.muta1.italomutao.user.dto.UserDTO;

@RestController
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse<AuthenticateResponseDTO>> authenticate(
			@RequestBody AuthenticateRequestDTO authenticationRequest) throws Exception {
		ApiResponse<AuthenticateResponseDTO> ret = new ApiResponse<AuthenticateResponseDTO>();
		try {
			Authentication authentication = this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername().toLowerCase(),
							authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = this.securityService.loadUserByUsername(authenticationRequest.getUsername());

//			String plainCredentials = authenticationRequest.getUsername() + ":" + authenticationRequest.getPassword();
//			String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));

			String token = this.jwtTokenUtil.generateToken(userDetails);

			UserDTO user = this.securityService.getLoggedUser();

			AuthenticateResponseDTO dto = new AuthenticateResponseDTO();
			dto.setLogged(true);
			dto.setId(user.getId());
			dto.setUsername(userDetails.getUsername());
			dto.setToken("Bearer "+ token);
			dto.setRole(userDetails.getAuthorities().toString().replace("[", "").replace("]", ""));
			dto.setSessionCookieKey("JSESSIONID");
			dto.setSessionCookieValue(RequestContextHolder.currentRequestAttributes().getSessionId());

			ret.setResponse(dto);

		} catch (BadCredentialsException e) {
			ret.setResponse(new AuthenticateResponseDTO(false));
			ret.setError("Invalid credentials.", CodeException.BAD_CREDENTIALS);
		} catch (ServiceException e) {
			ret.setError(e.getMessage(), e.getCode());
			e.printStackTrace();
		} catch (Exception e) {
			ret.setError("Internal server error. Please try again later.", CodeException.GENERAL);
			e.printStackTrace();
		}

		return ResponseEntity.ok(ret);
	}
}
