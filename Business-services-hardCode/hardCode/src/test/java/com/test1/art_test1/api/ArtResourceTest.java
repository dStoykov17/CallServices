package com.test1.art_test1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test1.art_test1.Credentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArtResourceTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testGet() throws Exception {
        Credentials c = new Credentials();
        c.setUserName("admin");
        c.setUserPass("pass");

        final String token = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(c)))
                .andExpect(header().exists("X-AUTH-TOKEN"))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader("X-AUTH-TOKEN");

        mockMvc.perform(get("/svc/get")
                .header("X-AUTH-TOKEN", token))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }

    @Test
    void testGetForbidden() throws Exception {
        Credentials c = new Credentials();
        c.setUserName("guest");
        c.setUserPass("pass");

        final String token = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(c)))
                .andExpect(header().exists("X-AUTH-TOKEN"))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getHeader("X-AUTH-TOKEN");

        mockMvc.perform(get("/svc/get")
                .header("X-AUTH-TOKEN", token))
                .andExpect(status().isForbidden())
                .andDo(print())
        ;
    }

}
