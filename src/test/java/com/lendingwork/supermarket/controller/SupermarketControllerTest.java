package com.lendingwork.supermarket.controller;

import com.lendingwork.supermarket.service.SuperMarketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SupermarketControllerTest {

    @InjectMocks
    SuperMarketController superMarketController;

    @Autowired
    SuperMarketService service;
    private MockMvc mockMvc;

    @BeforeAll
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(superMarketController).build();
    }

    @Test
    public void testCalculatePriceAPI_returnSuccess() throws Exception {
        //given
        //given(service.calculatePrice(anyList())).willReturn(100);
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/calculate-price?items=A,B,B")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
