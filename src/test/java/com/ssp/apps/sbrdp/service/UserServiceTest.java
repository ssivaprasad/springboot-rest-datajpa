package com.ssp.apps.sbrdp.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ssp.apps.sbrdp.dao.UserRepository;
import com.ssp.apps.sbrdp.dto.User;
import com.ssp.apps.sbrdp.exception.DuplicateUserException;
import com.ssp.apps.sbrdp.exception.UserNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	private UserService userService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setup() {
		userService = new UserService(userRepository);
	}

	@Test
	public void createUser() {
		when(userRepository.findByName(any(String.class))).thenReturn(Optional.empty());
		when(userRepository.save(any(User.class))).thenReturn(new User());
		User user = userService.createUser(new User("123", "dummy", "dummy@test.com"));
		assertNotNull(user);
	}

	@Test(expected = DuplicateUserException.class)
	public void createUser_should_throw_DuplicateUserException() {
		when(userRepository.findByName(any(String.class))).thenReturn(Optional.of(new User()));
		userService.createUser(new User("133", "dummy", "dummy@test.com"));
	}

	@Test
	public void updateUser() {
		when(userRepository.findById(any(String.class))).thenReturn(Optional.of(new User()));
		userService.updateUser(new User("133", "dummy", "dummy@test.com"));

		verify(userRepository).save(any(User.class));

	}

	@Test(expected = UserNotFoundException.class)
	public void updateUser_should_throw_UserNotFoundException() {
		when(userRepository.findById(any(String.class))).thenReturn(Optional.empty());
		userService.updateUser(new User("133", "dummy", "dummy@test.com"));
	}

	@Test
	public void deleteUser() {
		when(userRepository.findById(any(String.class))).thenReturn(Optional.of(new User()));
		userService.deleteUser("123");

		verify(userRepository).deleteById(any(String.class));
	}

	@Test(expected = UserNotFoundException.class)
	public void deleteUser_should_throw_UserNotFoundException() {
		when(userRepository.findById(any(String.class))).thenReturn(Optional.empty());
		userService.deleteUser("21312");
	}

	@Test
	public void getUser() {
		when(userRepository.findById(any(String.class))).thenReturn(Optional.of(new User()));
		User user = userService.getUser("1212");
		assertNotNull(user);
		assertThat(user, is(notNullValue()));
	}

	@Test(expected = UserNotFoundException.class)
	public void getUser_should_throw_UserNotFoundException() {
		when(userRepository.findById(any(String.class))).thenReturn(Optional.empty());
		userService.getUser("1212");
	}

	@Test
	@Ignore
	public void checkExceptionMessage() {
		thrown.expect(UserNotFoundException.class);
		thrown.expectMessage("User Not Exists in DB");

		when(userRepository.findById(any(String.class))).thenReturn(Optional.empty());
		userService.getUser("1212");
	}

}
