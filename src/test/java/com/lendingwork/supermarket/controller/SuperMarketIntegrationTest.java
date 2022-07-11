package com.lendingwork.supermarket.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperMarketITTest {

    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    public void calculateAmountTest_Success() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=A,B")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("110");
    }

    @Test
    public void calculateAmount_WhenNoOffer() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=A,B,A,A,D")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("360");
    }

    @Test
    public void calculateAmount_WhenBuy2For1Offer() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=B,B")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("100");
    }

    @Test
    public void calculateAmount_WhenBuy3Get1FreeOffer() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=C,C,C,C")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("75");
    }

    @Test
    public void calculateAmount_When_OnlyMultiBuyOffer() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=D,E")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("300");
    }

    @Test
    public void calculateAmount_WhenMultiBuyOffer_NoOffer() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=D,E,A,B")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("410");
    }

    @Test
    public void calculateAmount_When_MultiBuyOffer_And_2for1_Offer() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=D,E,B,B")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("400");
    }

    @Test
    public void calculateAmount_2for1_Offer_ItemD() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=D,D,B,B")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("400");
    }

    @Test
    public void calculateAmount_All_Offers() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=D,D,E,C,C,C,E,B,B")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("775");
    }

    @Test
    public void calculateAmount_DisOrder() throws Exception {

        //When
        MockHttpServletResponse mockResponse = mockMvc.perform(MockMvcRequestBuilders.get("/calculate-price?items=B,A,D,C,E,C,C,A,C,E,B,B")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        //then
        assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockResponse.getContentAsString()).contains("860");
    }
    
}
