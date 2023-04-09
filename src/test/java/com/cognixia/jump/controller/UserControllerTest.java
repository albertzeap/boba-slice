package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.filter.JwtRequestFilter;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.service.UserService;
import com.cognixia.jump.util.JwtUtil;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    
    private static final String STARTING_URI = "http://localhost:8080/api";


    // Mocks the request and response
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder encoder;

    @MockBean 
    private JwtUtil jwtUtil;

    @MockBean 
    private JwtRequestFilter jwtRequestFilter;

    @InjectMocks 
    private UserController userController;


    @Test 
    void testGetUsers() throws Exception {

        String uri = STARTING_URI + "/users";

        List<User> allUsers = new ArrayList<>();
		allUsers.add(new User(1, "albertpaez", "password123", Role.ROLE_ADMIN, true, "Albert", "Paez", "root@gmail.com", "1234456789098765", "209-328-7171"));
		allUsers.add(new User(2, "andrypaez", "password123", Role.ROLE_ADMIN, true, "Andry", "Paez", "andry@gmail.com", "1234456789098765", "209-328-7172"));

		when(userService.getUsers()).thenReturn(allUsers);
		
		mvc.perform(get(uri)) // perform get request
		.andDo(print()) // print request sent/response given
		.andExpect(status().isOk()) // expect a 200 status code
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // checks content type is json
		.andExpect(jsonPath("$.length()").value(allUsers.size())) // length of the list matches one above

		.andExpect(jsonPath("$[0].id").value(allUsers.get(0).getId())) 
		.andExpect(jsonPath("$[0].username").value(allUsers.get(0).getUsername()))
		.andExpect(jsonPath("$[0].password").value(allUsers.get(0).getPassword()))
		// .andExpect(jsonPath("$[0].role").value(allUsers.get(0).getRole()))
		.andExpect(jsonPath("$[0].enabled").value(allUsers.get(0).isEnabled()))
		.andExpect(jsonPath("$[0].firstName").value(allUsers.get(0).getFirstName()))
		.andExpect(jsonPath("$[0].lastName").value(allUsers.get(0).getLastName()))
		.andExpect(jsonPath("$[0].email").value(allUsers.get(0).getEmail()))
		.andExpect(jsonPath("$[0].paymentCard").value(allUsers.get(0).getPaymentCard()))
		.andExpect(jsonPath("$[0].phoneNumber").value(allUsers.get(0).getPhoneNumber()))

		.andExpect(jsonPath("$[1].id").value(allUsers.get(1).getId())) 
		.andExpect(jsonPath("$[1].username").value(allUsers.get(1).getUsername()))
		.andExpect(jsonPath("$[1].password").value(allUsers.get(1).getPassword()))
		// .andExpect(jsonPath("$[1].role").value(allUsers.get(1).getRole()))
		.andExpect(jsonPath("$[1].enabled").value(allUsers.get(1).isEnabled()))
		.andExpect(jsonPath("$[1].firstName").value(allUsers.get(1).getFirstName()))
		.andExpect(jsonPath("$[1].lastName").value(allUsers.get(1).getLastName()))
		.andExpect(jsonPath("$[1].email").value(allUsers.get(1).getEmail()))
		.andExpect(jsonPath("$[1].paymentCard").value(allUsers.get(1).getPaymentCard()))
		.andExpect(jsonPath("$[1].phoneNumber").value(allUsers.get(1).getPhoneNumber()));

		// verify how many times a method was called
		verify(userService, times(1)).getUsers(); // getUsers() from service called once
		verifyNoMoreInteractions(userService); // after checking above, service is no longer called
    }

    @Test
    void testGetUserById() throws Exception {
    
      String uri = STARTING_URI + "/user/1";

      User user = new User(1, "albertpaez", "password123", Role.ROLE_ADMIN, true, "Albert", "Paez", "root@gmail.com", "1234456789098765", "209-328-7171");

      when(userService.getUserById(1)).thenReturn(user);

      mvc.perform(get(uri)) // perform get request
		.andDo(print()) // print request sent/response given
		.andExpect(status().isOk()) // expect a 200 status code
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id").value(user.getId())) 
		.andExpect(jsonPath("$.username").value(user.getUsername()))
		.andExpect(jsonPath("$.password").value(user.getPassword()))
		// .andExpect(jsonPath("$.role").value(user.getRole()))
		.andExpect(jsonPath("$.enabled").value(user.isEnabled()))
		.andExpect(jsonPath("$.firstName").value(user.getFirstName()))
		.andExpect(jsonPath("$.lastName").value(user.getLastName()))
		.andExpect(jsonPath("$.email").value(user.getEmail()))
		.andExpect(jsonPath("$.paymentCard").value(user.getPaymentCard()))
		.andExpect(jsonPath("$.phoneNumber").value(user.getPhoneNumber()));

        verify(userService, times(1)).getUserById(1); // getUsers() from service called once
		verifyNoMoreInteractions(userService); // after checking above, service is no longer called
    }

    @Test
    void testCreateUser() throws Exception{

        // ARRANGE 
        String uri = STARTING_URI + "/user";

        User user = new User(1, "albertpaez", "password123", Role.ROLE_ADMIN, true, "Albert", "Paez", "root@gmail.com", "1234456789098765", "209-328-7171");
        
        
        when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

        mvc.perform(post(uri).content(user.toJson()) // data sent in body NEEDS to be in JSON format
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    void testInvalidCreateUser() throws Exception {

         // ARRANGE 
         String uri = STARTING_URI + "/user";

         User user = new User(1, "albertpaez", "password123", Role.ROLE_ADMIN, true, "Albert", "Paez", "root@gmail.com", "1234456789098765", "2093287171");
         
         
         when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
         System.out.println(user);
 
         mvc.perform(post(uri).content(user.toJson()) // data sent in body NEEDS to be in JSON format
                 .contentType(MediaType.APPLICATION_JSON_VALUE))
                 .andDo(print())
                 .andExpect(status().isBadRequest());
    }
    
    @Test
    void testUpdatePayment() throws Exception{

        String uri = STARTING_URI + "/user/1";
        String newPayment = "1234456789098766";

        when(userService.updatePayment(1, newPayment)).thenReturn(newPayment);

        mvc.perform(patch(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(newPayment))
				.andExpect(status().isOk());

		verify(userService, times(1)).updatePayment(1,newPayment );
		verifyNoMoreInteractions(userService);
    }
}
