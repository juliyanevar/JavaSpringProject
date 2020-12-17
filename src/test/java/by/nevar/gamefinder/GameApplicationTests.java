package by.nevar.gamefinder;

import by.nevar.gamefinder.models.Championship;
import by.nevar.gamefinder.models.Request;
import by.nevar.gamefinder.models.User;
import by.nevar.gamefinder.repository.IChampionshipRepository;
import by.nevar.gamefinder.repository.IRequestRepository;
import by.nevar.gamefinder.repository.IUserRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TestApplicationTests {

	@MockBean
	private IChampionshipRepository championshipRepository;

	@MockBean
	private IUserRepository userRepository;

	@MockBean
	private IRequestRepository requestRepository;

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testAddChampionship() {
		List<Championship> championships = Arrays.asList(
				new Championship("champ 1"),
				new Championship("champ 2")
		);
		when(championshipRepository.findAll()).thenReturn(championships);

		Assert.assertEquals(championshipRepository.findAll(), championships);
	}

	@Test
	public void testGetChampionships() throws Exception {
		setUp();
		List<Championship> championships = Arrays.asList(
				new Championship("champ 1"),
				new Championship("champ 2")
		);

		when(championshipRepository.findAll()).thenReturn(championships);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/champs"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[*].name", Matchers.containsInAnyOrder("champ 1", "champ 2")));
	}

	@Test
	public void testAddUser() {
		List<User> users = Arrays.asList(
				new User("user1","user1"),
				new User("user2","user2")
		);
		when(userRepository.findAll()).thenReturn(users);

		Assert.assertEquals(userRepository.findAll(), users);
	}

	@Test
	public void testGetRequests() throws Exception {
		setUp();

		Championship ch = new Championship("champ1");
		Championship ch2 = new Championship("champ2");
		User us = new User("Hello","Test");

		List<Request> requests = Arrays.asList(
				new Request("","Pending",us,ch),
				new Request("","Pending",us,ch2)
		);

		when(requestRepository.findAll()).thenReturn(requests);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/reques"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[*].status", Matchers.containsInAnyOrder("Pending","Pending")));
	}
}
