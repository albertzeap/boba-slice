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

import com.cognixia.jump.model.MenuItem;
import com.cognixia.jump.service.MenuItemService;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;

@WebMvcTest(MenuItemController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MenuItemControllerTest {
    
    private static final String STARTING_URI = "http://localhost:8080/api";
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService service;

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean 
    private JwtUtil jwtUtil;

    @InjectMocks 
    private MenuItemController controller;

    @Test
    void testGetDrinks() throws Exception {

        String uri = STARTING_URI + "/menu/drinks";

        List<MenuItem> allMenuItems = new ArrayList<MenuItem>();
		allMenuItems.add(new MenuItem(1, "Taro Milk Tea", "Milk tea with taro", 4.00, false, false, "Drink" ));
		allMenuItems.add(new MenuItem(1, "Jasmine Milk Tea", "Milk tea with Jasmine", 4.00, false, false, "Drink" ));

        when(service.getDrinks()).thenReturn(allMenuItems);

        mvc.perform(get(uri)) // perform get request
				.andDo(print()) // print request sent/response given
				.andExpect(status().isOk()) // expect a 200 status code
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // checks content type is json
				.andExpect(jsonPath("$.length()").value(allMenuItems.size())) // length of the list matches one above

				.andExpect(jsonPath("$[0].id").value(allMenuItems.get(0).getId())) // check each column value for the
				.andExpect(jsonPath("$[0].name").value(allMenuItems.get(0).getName()))
				.andExpect(jsonPath("$[0].description").value(allMenuItems.get(0).getDescription()))
				.andExpect(jsonPath("$[0].price").value(allMenuItems.get(0).getPrice()))
				.andExpect(jsonPath("$[0].veganFriendly").value(allMenuItems.get(0).getVeganFriendly()))
				.andExpect(jsonPath("$[0].lactoseFriendly").value(allMenuItems.get(0).getLactoseFriendly()))
				.andExpect(jsonPath("$[0].type").value(allMenuItems.get(0).getType()))
              
				.andExpect(jsonPath("$[1].name").value(allMenuItems.get(1).getName()))
				.andExpect(jsonPath("$[1].description").value(allMenuItems.get(1).getDescription()))
				.andExpect(jsonPath("$[1].price").value(allMenuItems.get(1).getPrice()))
				.andExpect(jsonPath("$[1].veganFriendly").value(allMenuItems.get(1).getVeganFriendly()))
				.andExpect(jsonPath("$[1].lactoseFriendly").value(allMenuItems.get(1).getLactoseFriendly()))
				.andExpect(jsonPath("$[1].type").value(allMenuItems.get(1).getType()));
																								
		verify(service, times(1)).getDrinks();
		verifyNoMoreInteractions(service); // after checking above, service is no longer called

    }
	@Test
    void testGetDishes() throws Exception {

        String uri = STARTING_URI + "/menu/dishes";

        List<MenuItem> allMenuItems = new ArrayList<MenuItem>();
		allMenuItems.add(new MenuItem(1, "Cheese Pizza", "Just cheese", 4.00, false, false, "Drink" ));
		allMenuItems.add(new MenuItem(1, "Pepperoni Pizza", "Add some pepperoni now", 4.00, false, false, "Drink" ));

        when(service.getDishes()).thenReturn(allMenuItems);

        mvc.perform(get(uri)) // perform get request
				.andDo(print()) // print request sent/response given
				.andExpect(status().isOk()) // expect a 200 status code
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // checks content type is json
				.andExpect(jsonPath("$.length()").value(allMenuItems.size())) // length of the list matches one above

				.andExpect(jsonPath("$[0].id").value(allMenuItems.get(0).getId())) // check each column value for the
				.andExpect(jsonPath("$[0].name").value(allMenuItems.get(0).getName()))
				.andExpect(jsonPath("$[0].description").value(allMenuItems.get(0).getDescription()))
				.andExpect(jsonPath("$[0].price").value(allMenuItems.get(0).getPrice()))
				.andExpect(jsonPath("$[0].veganFriendly").value(allMenuItems.get(0).getVeganFriendly()))
				.andExpect(jsonPath("$[0].lactoseFriendly").value(allMenuItems.get(0).getLactoseFriendly()))
				.andExpect(jsonPath("$[0].type").value(allMenuItems.get(0).getType()))
              
				.andExpect(jsonPath("$[1].name").value(allMenuItems.get(1).getName()))
				.andExpect(jsonPath("$[1].description").value(allMenuItems.get(1).getDescription()))
				.andExpect(jsonPath("$[1].price").value(allMenuItems.get(1).getPrice()))
				.andExpect(jsonPath("$[1].veganFriendly").value(allMenuItems.get(1).getVeganFriendly()))
				.andExpect(jsonPath("$[1].lactoseFriendly").value(allMenuItems.get(1).getLactoseFriendly()))
				.andExpect(jsonPath("$[1].type").value(allMenuItems.get(1).getType()));
																								
		verify(service, times(1)).getDishes();
		verifyNoMoreInteractions(service); // after checking above, service is no longer called

    }

    @Test
    void testGetMenuItemById() throws Exception{
        int id = 1;
        String uri = STARTING_URI + "/menu/1";

        MenuItem drink = new MenuItem(1, "Taro Milk Tea", "Milk tea with taro", 4.00, false, false, "Drink" );

        when(service.getMenuItemById(id)).thenReturn(drink);

        mvc.perform(get(uri)) // create get request
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(drink.getId())) // check each column value for the
				.andExpect(jsonPath("$.name").value(drink.getName()))
				.andExpect(jsonPath("$.description").value(drink.getDescription()))
				.andExpect(jsonPath("$.price").value(drink.getPrice()))
				.andExpect(jsonPath("$.veganFriendly").value(drink.getVeganFriendly()))
				.andExpect(jsonPath("$.lactoseFriendly").value(drink.getLactoseFriendly()))
				.andExpect(jsonPath("$.type").value(drink.getType()));

		verify(service, times(1)).getMenuItemById(id);
		verifyNoMoreInteractions(service);
    }

	

    @Test 
    void testCreateMenuItem() throws Exception {
        String uri = STARTING_URI + "/menu";

        MenuItem drink = new MenuItem(1, "Taro Milk Tea", "Milk tea with taro", 4.00, false, false, "Drink" );

        when(service.createMenuItem(Mockito.any(MenuItem.class))).thenReturn(drink);

        mvc.perform(post(uri).content(drink.toJson())
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
    

}
