package com.cognixia.jump.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    //mock the repo methods
    //@Mock
    //private UserRepository repo;

    // don't actually autowire the repository that's in the service
	// instead use the "mocked" repo that we created above
	//@InjectMocks
	//private StudentService service;

    @Test
	void testGetUsers() throws Exception {
    //     //ARRANGE
    //     List<User> allUsers = new ArrayList<>();
	// 	allUsers.add(new User(1, "Peppa", "Pig", "peppa@gmail.com", 3.4, LocalDate.of(2004, 5, 30), "Music"));
	// 	allUsers.add(new User(2, "Sharpay", "Evans", "sharpay@gmail.com", 3.8, LocalDate.of(1990, 6, 1), "Theater"));

	// 	when( repo.findAll() ).thenReturn(allUsers);

    //     //ACT

    //     List<User> result = service.getUsers();

    //     //ASSERT
    //     for(int i = 0; i < allUsers.size(); i++) {
			
	// 		User expected = allUsers.get(i);
	// 		User actual = result.get(i);
			
	// 		if( expected.equals(actual) ) {
	// 			System.out.println("Equals");
	// 		}
	// 		else {
	// 			// will always fail a test
	// 			fail();
	// 		}
    }

    // @Test
	// void testGetUserById() throws Exception {
		
	// 	// ARRANGE
	// 	int id = 1;
	// 	User student = new User(id, "Peppa", "Pig", "peppa@gmail.com", 3.4, LocalDate.of(2004, 5, 30), "Music");
		
	// 	when( repo.findById(id) ).thenReturn( Optional.of(student) );
		
	// 	// ACT
	// 	Student result = service.getStudentById(id);
		
	// 	// ASSERT
	// 	assertEquals(student, result);
	// }
}
