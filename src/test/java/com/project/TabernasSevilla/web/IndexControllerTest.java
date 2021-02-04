package com.project.TabernasSevilla.web;

import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TabernasSevilla.configuration.SecurityConfiguration;
import com.project.TabernasSevilla.controller.DishController;
import com.project.TabernasSevilla.controller.IndexController;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.Seccion;
import com.project.TabernasSevilla.repository.*;
import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.AuthorityRepository;
import com.project.TabernasSevilla.security.AuthorityService;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;
import com.project.TabernasSevilla.service.ActorService;
import com.project.TabernasSevilla.service.DishService;
import com.project.TabernasSevilla.service.EstablishmentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@WebAppConfiguration
@WebMvcTest(controllers = IndexController.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class, 
	includeFilters = {@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })
//@MockBean(JpaMetamodelMappingContext.class) //para que evite buscar la database
public class IndexControllerTest {
	
	private static final int TEST_DISH_ID = 1;

	//@Autowired
	//private DishController dishController;

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

	@BeforeEach
	void setup() { // inicializar establishment y dish
		
//		Dish d = new Dish("Mi plato", "Mi descripción",
//				"https://international-experience.es/wp-content/uploads/2019/08/comidas-mundo.jpg", 20.0, 4.0, Seccion.CARNES, true,
//				null);
//
//		d.setId(1);
//		System.out.println("%%%%%%%%%%%% la id del plato "+d.getId());
//		List<Dish> ls = new ArrayList<Dish>();
//		ls.add(d);
//
//		Establishment est = new Establishment();
//		est.setId(1);
//		est.setTitle("prueba");
//		est.setAddress("calle ");
//		est.setCapacity(10);
//		est.setCurrentCapacity(10);
//		est.setOpeningHours("24/7");
//		est.setScore(2);
//		est.setDish(ls);
//		establishmentRepository.save(est);
//		System.out.println("############ todos los establecimientos: " + establishmentService.findAll());
//		
//		given(this.dishService.findById(TEST_DISH_ID)).willReturn(Optional.of(new Dish())); //importantisimo
//		
	}
	@WithMockUser(value = "spring")
	@Test
	void testMainView() throws Exception {
		mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testGetLoginPage() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testLoginError() throws Exception {
		mockMvc.perform(get("/login-error")).andExpect(status().isOk()).andExpect(view().name("login_error"));
	}
	

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error, Model model) {
//
//		if (error == true) {
//			// Assign an error message
//			model.addAttribute("error", "You have entered an invalid username or password!");
//		} else {
//			model.addAttribute("error", "");
//		}
//		return "login";
//	}
//
//	@RequestMapping(value = "/login-error")
//	public String loginError(Model model) {
//		model.addAttribute("loginError", true);
//		return "login_error";
//	}
	
	
}