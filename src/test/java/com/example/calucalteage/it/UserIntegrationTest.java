package com.example.calucalteage.it;

import com.example.calucalteage.controller.UserController;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class UserIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@SneakyThrows
	public void calucaleAgeTest() {
		this.mockMvc.perform(get("/users/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{" +
						"name:Valerii," +
						"lastname:Sierhieiev," +
						"age:21" +
						"}"));
	}

	@Test
	@SneakyThrows
	public void receiveExceptionWhenIdDoNotExist() {
		this.mockMvc.perform(get("/users/4241"))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().json("{" +
						"\"message\":\"User with entered ID do not exist!\"" +
						"}"));
	}
}