package es.albergonpi.exercises.backuser.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.albergonpi.exercises.backuser.dto.UserDto;
import es.albergonpi.exercises.backuser.entity.User;
import es.albergonpi.exercises.backuser.exception.NotFoundException;
import es.albergonpi.exercises.backuser.exception.ResourceRepeatedException;
import es.albergonpi.exercises.backuser.repository.UserRepository;

@Service
public class UserService {
	
	public final static String USER_NOT_FOUND = "User with id %s not found";
	public final static String USERS_EMPTY = "There're no users in the system";
	public final static String USER_REPEATED = "A user with id %s already exists";
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public UserDto getUserById(Integer userId) throws NotFoundException {
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isPresent()) {
			return mapper.map(optUser.get(), UserDto.class);
		} else {
			throw new NotFoundException(String.format(USER_NOT_FOUND, userId));
		}
	}
	
	public List<UserDto> getAllUsers() throws NotFoundException {
		List<User> databaseUserList = userRepository.findAll();
		if(databaseUserList == null  ||  databaseUserList.size() == 0) {
			throw new NotFoundException(USERS_EMPTY);
		} else {			
			return databaseUserList.stream().map(dbUser -> mapper.map(dbUser, UserDto.class)).collect(Collectors.toList());
		}
	}
	
	@Transactional
	public UserDto createUser(UserDto userToCreate) throws ResourceRepeatedException {
		Optional<User> optUser = userRepository.findById(userToCreate.getId());
		if(optUser.isPresent()) {
			throw new ResourceRepeatedException(String.format(USER_REPEATED, optUser.get().getId()));
		}
		User newUser = mapper.map(userToCreate, User.class);
		User storedUser = userRepository.save(newUser);
		return mapper.map(storedUser, UserDto.class);
	}

	@Transactional
	public UserDto updateUser(UserDto userToUpdate) throws NotFoundException {
		Optional<User> optUser = userRepository.findById(userToUpdate.getId());
		if(!optUser.isPresent()) {
			throw new NotFoundException(String.format(USER_NOT_FOUND, userToUpdate.getId()));
		}
		User storedUser = optUser.get();
		storedUser.setName(userToUpdate.getName());
		storedUser.setBirthdate(userToUpdate.getBirthdate());
		storedUser = userRepository.save(storedUser);
		return mapper.map(storedUser, UserDto.class);
	}

	public void deleteUserById(Integer id) throws NotFoundException {
		Optional<User> optUser = userRepository.findById(id);
		if(!optUser.isPresent()) {
			throw new NotFoundException(String.format(USER_NOT_FOUND, id));
		}
		userRepository.delete(optUser.get());
	}

}
