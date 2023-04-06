package com.cognixia.jump.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.service.UserService;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    
    private static final String STARTING_URI = "http://localhost:8080/api";


    // Mocks the request and response
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @InjectMocks 
    private UserController userController;

    @Test
    void testCreateUser() throws Exception{

        // ARRANGE 
        String uri = STARTING_URI + "/user";

    
        // User user = new User(1, "albertpaez", "password123", "Albert", "Paez", "root@gmail.com", "1234456789098765", "2093287171", userDietaryRestrictionList, userOrderList);
        User user = new User(1, "albertpaez", "password123", Role.ROLE_ADMIN, true, "Albert", "Paez", "root@gmail.com", "1234456789098765", "2093287171");
        
        
        when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
        System.out.println(user);

        mvc.perform(post(uri).content(user.toJson()) // data sent in body NEEDS to be in JSON format
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }
    
}
