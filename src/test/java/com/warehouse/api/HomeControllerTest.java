package com.warehouse.api;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class HomeControllerTest {

    @Test
    public void getHome() throws Exception {
        //given
        HomeController homeController=new HomeController();
        MockMvc mockMvc=standaloneSetup(homeController).build();

        //when
        mockMvc.perform(get("/"))
                //then
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("name"));
    }
}
