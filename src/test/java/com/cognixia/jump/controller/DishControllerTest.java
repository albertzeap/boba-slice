package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Dish;
import com.cognixia.jump.service.DishService;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(DishController.class)
public class DishControllerTest {
	
	private static final String STARTING_URI = "http://localhost:8080/api";
    
	// mocking the request/response
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private DishService service;
	
	@MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@MockBean
	private JwtUtil jwtUtil;
	
	@InjectMocks
	private DishController controller;
	
	@Test 
	void testGetDishes() throws Exception {
		String uri = STARTING_URI + "/pizza/menu"; // uri for the request
		
		List<Dish> allDishes = new ArrayList<>();
		allDishes.add(new Dish(1, "Pepperoni Pizza", "American pizza variety which includes one of the country's most beloved toppings", 8.99, false));
		allDishes.add(new Dish(2, "Sausage Pizza", "American pizza variety which includes one of the country's most beloved toppings", 7.99, false));

		when(service.getDishes()).thenReturn(allDishes);
		
		mvc.perform(get(uri)) // perform get request
		.andDo(print()) // print request sent/response given
		.andExpect(status().isOk()) // expect a 200 status code
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // checks content type is json
		.andExpect(jsonPath("$.length()").value(allDishes.size())) // length of the list matches one above

		.andExpect(jsonPath("$[0].id").value(allDishes.get(0).getId())) // check each column value for the
																			// students in list
		.andExpect(jsonPath("$[0].name").value(allDishes.get(0).getName()))
		.andExpect(jsonPath("$[0].description").value(allDishes.get(0).getDescription()))
		.andExpect(jsonPath("$[0].price").value(allDishes.get(0).getPrice()))
		.andExpect(jsonPath("$[0].veganFriendly").value(allDishes.get(0).getVeganFriendly()))


		.andExpect(jsonPath("$[1].id").value(allDishes.get(1).getId()))
		.andExpect(jsonPath("$[1].name").value(allDishes.get(1).getName()))
		.andExpect(jsonPath("$[1].description").value(allDishes.get(1).getDescription()))
		.andExpect(jsonPath("$[1].price").value(allDishes.get(1).getPrice()))
		.andExpect(jsonPath("$[1].veganFriendly").value(allDishes.get(1).getVeganFriendly()));
		
		// verify how many times a method was called (how many times methods from
		// service
		// were used)
		verify(service, times(1)).getDishes(); // getDishes() from service called once
		verifyNoMoreInteractions(service); // after checking above, service is no longer called
	}
	
	@Test
	void testGetDishById() throws Exception {
		int id = 1;
		String uri = STARTING_URI + "/dish/{id}";

		Dish dish = new Dish(id, "Pepperoni Pizza", "American pizza variety which includes one of the country's most beloved toppings", 8.99, false);

		when(service.getDishById(id)).thenReturn(dish);

		mvc.perform(get(uri, id)) // create get request and pass id to uri path
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(dish.getId()))
				.andExpect(jsonPath("$.name").value(dish.getName()))
				.andExpect(jsonPath("$.description").value(dish.getDescription()))
				.andExpect(jsonPath("$.price").value(dish.getPrice()))
				.andExpect(jsonPath("$.veganFriendly").value(dish.getVeganFriendly()));

		verify(service, times(1)).getDishById(id);
		verifyNoMoreInteractions(service);
	}
	
	// Change this when we have custom Exception
//	@Test
//	void testDishByIdNotFound() throws Exception {
//
//		int id = 1;
//		String uri = STARTING_URI + "/dish/{id}";
//
//		// when this method gets called, this time an exception will be thrown
//		when(service.getDishById(id)).thenThrow(new Exception("Dish with id " + id + " not found"));
//
//		// because of the exception, we're only checking to make sure we got the
//		// expected 404 status code
//		mvc.perform(get(uri, id)).andDo(print()).andExpect(status().isNotFound());
//
//		// verify the calls made
//		verify(service, times(1)).getDishById(id);
//		verifyNoMoreInteractions(service);
//	}
	
//	@Test
//	void testCreateDish() throws Exception {
//
//		String uri = STARTING_URI + "/dish";
//
//		Dish dish = new Dish(1, "Pepperoni Pizza", "American pizza variety which includes one of the country's most beloved toppings", 8.99, false);
//
//		// when we attempt to add the student, we don't care what kind of student was
//		// sent
//		// through the request or to the service, what we care about is the student
//		// returned
//		// as a response
//		when(service.createDish(Mockito.any(Dish.class))).thenReturn(dish);
//
//
//		mvc.perform(post(uri).content(dish.toJson()) // data sent in body NEEDS to be in JSON format
//				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isCreated())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
//
//	}
	
	
}
