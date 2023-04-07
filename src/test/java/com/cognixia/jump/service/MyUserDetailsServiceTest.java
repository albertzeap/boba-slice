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

import com.cognixia.jump.model.User;
import com.cognixia.jump.model.UserOrder;
import com.cognixia.jump.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class MyUserDetailsServiceTest {

    //mock the repo methods
    @Mock
    private UserRepository repo;

    // don't actually autowire the repository that's in the service
	// instead use the "mocked" repo that we created above
	@InjectMocks
	private MyUserDetailsService service;

    @Test
	void testGetUsers() throws Exception {
        //ARRANGE
        List<User> allUsers = new ArrayList<>();

		List<UserOrder> order1 = new ArrayList<>();
		//need to add orders here

		List<UserOrder> order2 = new ArrayList<>();
		//need to add orders here

		//add users
		//allUsers.add(new User(Integer id, String username, String password, String firstName, String lastName, String email, Integer paymentCard, Integer phoneNumber, List<UserDietaryRestriction> dietaryRestriction, List<UserOrder> user_order));

		// allUsers.add(new User(1, "user1", "password", "first-name1", "last-name1", "a@gmail.com", "123456789", "1112223333", null , null));


		when( repo.findAll() ).thenReturn(allUsers);

        //ACT

        // List<User> result = service.getUsers();

        //ASSERT
        // for(int i = 0; i < allUsers.size(); i++) {
			
		// 	User expected = allUsers.get(i);
		// 	User actual = result.get(i);
			
		// 	if( expected.equals(actual) ) {
		// 		System.out.println("Equals");
		// 	}
		// 	else {
		// 		// will always fail a test
		// 		fail();
		// 	}
		// }
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
