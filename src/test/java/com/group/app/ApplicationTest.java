package com.group.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

   @Test
    public void testSearchLocationWithIncorrectParam() throws Exception {

        this.mockMvc.perform(get("/searchlocation").param("ll", "4000000000.7337621,-74.0095604"))
                .andDo(print()).andExpect(status().isBadRequest());
    }

   @Test
   public void testSearchLocationWithCorrectParam() throws Exception {

       this.mockMvc.perform(get("/searchlocation").param("ll", "40.7337621,-74.0095604"))
               .andDo(print()).andExpect(status().isOk());
   }
   
   @Test
   public void testCategorySearchWithIncorrectParam() throws Exception {

       this.mockMvc.perform(get("/filterbycategory").param("name", "pune1").param("category", "mobile"))
               .andDo(print()).andExpect(status().isBadRequest());

   }

   @Test
   public void testCategorySearchWithCorrectParam() throws Exception {

       this.mockMvc.perform(get("/filterbycategory").param("name", "pune").param("category", "mobile"))
               .andDo(print()).andExpect(status().isOk());

   }
}
