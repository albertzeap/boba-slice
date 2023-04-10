package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
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
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.OrderMenuItem;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.OrderService;
import com.cognixia.jump.util.JwtUtil;


@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest {

    private static final String STARTING_URI = "http://localhost:8080/api";
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService service;

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean 
    private JwtUtil jwtUtil;

    @InjectMocks
    private OrderController controller;

    @Test
    void testGetOrders() throws Exception{

        String uri = STARTING_URI + "/orders";

        List<Order> allOrders = new ArrayList<Order>();
		allOrders.add(new Order(1, 30.00, new Date(), true));
		allOrders.add(new Order(2, 55.00, new Date(), false));

        when(service.getOrders()).thenReturn(allOrders);

        mvc.perform(get(uri)) // perform get request
				.andDo(print()) // print request sent/response given
				.andExpect(status().isOk()) // expect a 200 status code
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // checks content type is json
				.andExpect(jsonPath("$.length()").value(allOrders.size())) // length of the list matches one above

				.andExpect(jsonPath("$[0].id").value(allOrders.get(0).getId())) // check each column value for the
				.andExpect(jsonPath("$[0].totalPrice").value(allOrders.get(0).getTotalPrice()))
				.andExpect(jsonPath("$[0].timeStamp").value(allOrders.get(0).getTimeStamp()))
				.andExpect(jsonPath("$[0].progress").value(allOrders.get(0).getProgress()))
              
				.andExpect(jsonPath("$[1].id").value(allOrders.get(1).getId()))
				.andExpect(jsonPath("$[1].totalPrice").value(allOrders.get(1).getTotalPrice()))
				.andExpect(jsonPath("$[1].timeStamp").value(allOrders.get(1).getTimeStamp()))
				.andExpect(jsonPath("$[1].progress").value(allOrders.get(1).getProgress()));
				
    }

    @Test
    void testGetOrderById() throws Exception{

        String uri = STARTING_URI + "/order/1";
        int id = 1;

        Order order = new Order(1, 30.00, new Date(), true);

        when(service.getOrderById(id)).thenReturn(order);

        mvc.perform(get(uri)) // create get request
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(order.getId())) // check each column value for the
				.andExpect(jsonPath("$.totalPrice").value(order.getTotalPrice()))
				.andExpect(jsonPath("$.timeStamp").value(order.getTimeStamp()))
				.andExpect(jsonPath("$.progress").value(order.getProgress()));

		verify(service, times(1)).getOrderById(id);
		verifyNoMoreInteractions(service);
    }

    @Test 
    void testCreateOrder() throws Exception {
        String uri = STARTING_URI + "/order/1";

        Order order = new Order(1, 30.00, new Date(), true);

        when(service.createOrder(1)).thenReturn(order);

        mvc.perform(post(uri).content(order.toJson())
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void testAddItem() throws Exception {

      String uri = STARTING_URI + "/order/add?menuItemId=1&orderId=1";

      Order order = new Order(1, 30.00, new Date(), true);
      MenuItem menuItem = new MenuItem(1, "Taro Milk Tea", "Milk tea with taro", 4.00, false, false, "Drink" );
      OrderMenuItem orderMenuItem = new OrderMenuItem(1, order, menuItem);

      when(service.addItem(1, 1)).thenReturn(orderMenuItem);

      mvc.perform(post(uri).content(orderMenuItem.toJson())
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    void testRemoveMenuItem() throws Exception {

      String uri = STARTING_URI + "/order/remove?menuItemId=1&orderId=1";

      Order order = new Order(1, 30.00, new Date(), true);
      MenuItem menuItem = new MenuItem(1, "Taro Milk Tea", "Milk tea with taro", 4.00, false, false, "Drink" );
      OrderMenuItem orderMenuItem = new OrderMenuItem(1, order, menuItem);

      when(service.addItem(1, 1)).thenReturn(orderMenuItem);

      mvc.perform(delete(uri).content(orderMenuItem.toJson())
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isOk());
    }
    
    
}
