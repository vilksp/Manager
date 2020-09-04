package lt.management.oms.crude;


import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lt.management.oms.dto.PasswordChange;
import lt.management.oms.dto.UserDto;
import lt.management.oms.model.User;
import lt.management.oms.service.UserService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/api/v1/")
public class UserRestControllerV1 {


	@Autowired
	private UserService userService;

	@GetMapping("test")
	public String testMethod() {
		return "User OK";
	}

	@GetMapping("/users/{username}")
	public User getUsersByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public UserDetails currentUserName() {
		return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@PutMapping("users/{username}")
	public User updateUser(@PathVariable String username, @RequestBody UserDto user) {
		return userService.update(username, user.toUser());
	}
	
	
	@PostMapping("/changePassword")
	public ResponseEntity postEditUseChangePassword(@Valid @RequestBody PasswordChange form, Errors errors) {
		try {
			if( errors.hasErrors()) {
				String result = errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(""));

				throw new Exception(result);
			}
			userService.changePassword(form);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Success");
	}
	
	


}
