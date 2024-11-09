package com.example.demo;

import com.example.demo.api.controller.ReceiptController;
import com.example.demo.api.model.Receipt;
import com.example.demo.api.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FetchApplicationTests {

    private MockMvc mockMvc;

    @InjectMocks
    private ReceiptController receiptController;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(receiptController).build();
    }

    @Test
    void testReadReceipt109() throws Exception {
        String receiptJson = "{ \"retailer\": \"M&M Corner Market\", \"purchaseDate\": \"2022-03-20\", \"purchaseTime\": \"14:33\", " +
                "\"items\": [{\"shortDescription\": \"Gatorade\", \"price\": 2.25}, {\"shortDescription\": \"Gatorade\", \"price\": 2.25}, " +
                "{\"shortDescription\": \"Gatorade\", \"price\": 2.25}, {\"shortDescription\": \"Gatorade\", \"price\": 2.25}], " +
                "\"total\": 9.00 }";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/receipts/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(receiptJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        String id = responseContent.split(":")[1].split("\"")[1];

        mockMvc.perform(MockMvcRequestBuilders.get("/receipts/" + id + "/points"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.points").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.points").value(109));
    }
    
    @Test
    void testReadReceipt28() throws Exception {
        String receiptJson = "{ \"retailer\": \"Target\", \"purchaseDate\": \"2022-01-01\", \"purchaseTime\": \"13:01\", " +
                "\"items\": [{\"shortDescription\": \"Mountain Dew 12P\", \"price\": 6.49}, {\"shortDescription\": \"Emils Cheese Pizza\", \"price\": 12.25}, " +
                "{\"shortDescription\": \"Knorr Creamy Chicken\", \"price\": 1.26}, {\"shortDescription\": \"Doritos Nacho Cheese\", \"price\": 3.35}, {\"shortDescription\": \"   Klarbrunn 12-PK 12 FL OZ  \", \"price\": 12.00}], " +
                "\"total\": 35.35 }";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/receipts/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(receiptJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        String id = responseContent.split(":")[1].split("\"")[1];

        mockMvc.perform(MockMvcRequestBuilders.get("/receipts/" + id + "/points"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.points").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.points").value(28));
    }
}
