package es.albergonpi.exercises.backuser.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.albergonpi.exercises.backuser.dto.UserDto;
import es.albergonpi.exercises.backuser.exception.NotFoundException;
import es.albergonpi.exercises.backuser.exception.ResourceRepeatedException;
import es.albergonpi.exercises.backuser.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	@ResponseBody
	public List<UserDto> getAllUsers() throws NotFoundException {
		return userService.getAllUsers();
	}
	
	@GetMapping(value="/{id}")
	@ResponseBody
	public UserDto getUser(@PathVariable Integer id) throws NotFoundException {
		return userService.getUserById(id);
	}
	
	@PostMapping
	@ResponseBody
	public UserDto createUser(@Valid @RequestBody UserDto user) throws ResourceRepeatedException {
		return userService.createUser(user);
	}
	
	@PutMapping
	@ResponseBody
	public UserDto updateUser(@Valid @RequestBody UserDto user) throws NotFoundException {
		return userService.updateUser(user);
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable Integer id) throws NotFoundException {
		userService.deleteUserById(id);
	}

}
