package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Drink;
import com.cognixia.jump.service.DrinkService;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;

@WebMvcTest(DrinkController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DrinkControllerTest {

    private static final String STARTING_URI = "http://localhost:8080/api";
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DrinkService service;

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean 
    private JwtUtil jwtUtil;

    @InjectMocks 
    private DrinkController controller;

    @Test
    void testGetDrinks() throws Exception {

        String uri = STARTING_URI + "/drinks";

        List<Drink> allDrinks = new ArrayList<Drink>();
		allDrinks.add(new Drink(1, "Taro Milk Tea", "Milk tea with taro", 4.00, false, false ));
		allDrinks.add(new Drink(1, "Jasmine Milk Tea", "Milk tea with Jasmine", 4.00, false, false ));

        when(service.getDrinks()).thenReturn(allDrinks);

        mvc.perform(get(uri)) // perform get request
				.andDo(print()) // print request sent/response given
				.andExpect(status().isOk()) // expect a 200 status code
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // checks content type is json
				.andExpect(jsonPath("$.length()").value(allDrinks.size())) // length of the list matches one above

				.andExpect(jsonPath("$[0].id").value(allDrinks.get(0).getId())) // check each column value for the
				.andExpect(jsonPath("$[0].name").value(allDrinks.get(0).getName()))
				.andExpect(jsonPath("$[0].description").value(allDrinks.get(0).getDescription()))
				.andExpect(jsonPath("$[0].price").value(allDrinks.get(0).getPrice()))
				.andExpect(jsonPath("$[0].veganFriendly").value(allDrinks.get(0).getVeganFriendly()))
				.andExpect(jsonPath("$[0].lactoseFriendly").value(allDrinks.get(0).getLactoseFriendly()))
              
				.andExpect(jsonPath("$[1].name").value(allDrinks.get(1).getName()))
				.andExpect(jsonPath("$[1].description").value(allDrinks.get(1).getDescription()))
				.andExpect(jsonPath("$[1].price").value(allDrinks.get(1).getPrice()))
				.andExpect(jsonPath("$[1].veganFriendly").value(allDrinks.get(1).getVeganFriendly()))
				.andExpect(jsonPath("$[1].lactoseFriendly").value(allDrinks.get(1).getLactoseFriendly())); // make sure to match the
																								// string value, won't
																								// work if just compare
																								// the LocalDate object
				

				

		// verify how many times a method was called (how many times methods from
		// service
		// were used)
		verify(service, times(1)).getDrinks(); // getStudents() from service called once
		verifyNoMoreInteractions(service); // after checking above, service is no longer called

    }

    @Test
    void testGetDrinkById() throws Exception{
        int id = 1;
        String uri = STARTING_URI + "/drink/{id}";

        Drink drink = new Drink(1, "Taro Milk Tea", "Milk tea with taro", 4.00, false, false );

        when(service.getDrinkById(id)).thenReturn(drink);

        mvc.perform(get(uri, id)) // create get request and pass id to uri path
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(drink.getId())) // check each column value for the
				.andExpect(jsonPath("$.name").value(drink.getName()))
				.andExpect(jsonPath("$.description").value(drink.getDescription()))
				.andExpect(jsonPath("$.price").value(drink.getPrice()))
				.andExpect(jsonPath("$.veganFriendly").value(drink.getVeganFriendly()))
				.andExpect(jsonPath("$.lactoseFriendly").value(drink.getLactoseFriendly()));

		verify(service, times(1)).getDrinkById(id);
		verifyNoMoreInteractions(service);
    }

    @Test 
    void testCreateDrink() throws Exception {
        String uri = STARTING_URI + "/drink/{id}";

        Drink drink = new Drink(1, "Taro Milk Tea", "Milk tea with taro", 4.00, false, false );

        when(service.createDrink(drink)).thenReturn(drink);

        mvc.perform(post(uri).content(drink.toJson()) // data sent in body NEEDS to be in JSON format
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

}
