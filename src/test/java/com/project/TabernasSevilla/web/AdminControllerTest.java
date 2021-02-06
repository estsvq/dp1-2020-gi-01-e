package com.project.TabernasSevilla.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import com.project.TabernasSevilla.configuration.SecurityConfiguration;
import com.project.TabernasSevilla.controller.AdminController;
import com.project.TabernasSevilla.repository.AdminRepository;
import com.project.TabernasSevilla.repository.BookingRepository;
import com.project.TabernasSevilla.repository.CookRepository;
import com.project.TabernasSevilla.repository.CurriculumRepository;
import com.project.TabernasSevilla.repository.CustomerRepository;
import com.project.TabernasSevilla.repository.EstablishmentRepository;
import com.project.TabernasSevilla.repository.ManagerRepository;
import com.project.TabernasSevilla.repository.OrderCancellationRepository;
import com.project.TabernasSevilla.repository.OrderLogRepository;
import com.project.TabernasSevilla.repository.OrderRepository;
import com.project.TabernasSevilla.repository.RegKeyRepository;
import com.project.TabernasSevilla.repository.ReviewRepository;
import com.project.TabernasSevilla.repository.TableRepository;
import com.project.TabernasSevilla.repository.WaiterRepository;
import com.project.TabernasSevilla.security.AuthorityRepository;
import com.project.TabernasSevilla.security.AuthorityService;
import com.project.TabernasSevilla.security.UserService;
import com.project.TabernasSevilla.service.ActorService;
import com.project.TabernasSevilla.service.DishService;
import com.project.TabernasSevilla.service.EstablishmentService;

@WebMvcTest(controllers = AdminController.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class, 
	includeFilters = {@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })

public class AdminControllerTest {
	

	@MockBean
	private UserService userService;

	@MockBean
	private ActorService actorService;

	@MockBean
	private DishService dishService;

	@MockBean
	private EstablishmentService establishmentService;
	
	@MockBean
	private AuthorityService authService;

	@MockBean
	private ReviewRepository reviewRepository;

	@MockBean
	private AuthorityRepository authRepository;

	@MockBean
	private AdminRepository adminRepository;

	@MockBean
	private BookingRepository bookingRepository;

	@MockBean
	private EstablishmentRepository establishmentRepository;

	@MockBean
	private TableRepository tableRepository;

	@MockBean
	private CurriculumRepository cur;

	@MockBean
	private CookRepository cook;

	@MockBean
	private CustomerRepository cus;

	@MockBean
	private OrderRepository ord;

	@MockBean
	private RegKeyRepository reg;

	@MockBean
	private WaiterRepository wait;

	@MockBean
	private ManagerRepository man;

	@MockBean
	private OrderCancellationRepository canc;

	@MockBean
	private OrderLogRepository logga;

	// POR ALGUN MOTIVO HE TENIDO QUE CREAR TODOS ESTOS MOCKBEANS PARA QUE FUNCIONE
	// EL TEST SIMPLE DE HTTPRESPONSE

	@Autowired
	private MockMvc mockMvc;

	
	@WithMockUser(value = "spring")
	@Test
	void testControlPanel() throws Exception {
		mockMvc.perform(get("/admin/control")).andExpect(status().isOk()).andExpect(view().name("admin/controlpanel"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCreateKey() throws Exception {
		mockMvc.perform(get("/admin/employees/key")).andExpect(status().isOk()).andExpect(view().name("admin/employees/keys"));
	}
	
	@WithMockUser(value = "spring", roles = "ADMIN")
	@Test
	void testCreateManagerKey() throws Exception {
		mockMvc.perform(get("/admin/employees/key/manager")).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCreateCookKey() throws Exception {
		mockMvc.perform(get("/admin/employees/key/cook")).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCreateWaiterKey() throws Exception {
		mockMvc.perform(get("/admin//employees/key/waiter")).andExpect(status().isOk());
	}
	
}
