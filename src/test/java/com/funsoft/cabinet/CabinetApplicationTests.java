package com.funsoft.cabinet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Speciality;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class CabinetApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	ObjectMapper om = new ObjectMapper(); //convertir les objets en json

	@Test
public 	void contextLoads() {
	}
	@Test
public 	void addDoctor() throws Exception {
		//creation de l'objet
		Doctor doctor = new Doctor();
		doctor.setFirstname("Hasna");
		doctor.setLastname("mraidi");
		doctor.setAddress("Ariana");
		doctor.setEmail("maraidi11@gmail.com");
		doctor.setSpeciality(Speciality.gastro);
		//pour transformer l'objet en format json
		String objectinjson = om.writeValueAsString(doctor);
		//envoyer une requete d'ajout(requete http)
		MvcResult queryresponse =
				mockMvc.perform(post("/doctors").
						content(objectinjson)
						.contentType(MediaType.APPLICATION_JSON_VALUE)).
						andExpect(status().isOk()).andReturn();

		//récuperer le contenu de la réponse sous forme chaine de caractères
		String resultContent = queryresponse.getResponse().getContentAsString();

		Doctor response = om.readValue(resultContent, Doctor.class);

		Assert.assertTrue(response.getId()>0);
		Assert.assertTrue(response.getFirstname().equals(doctor.getFirstname()));
		Assert.assertTrue(response.getLastname().equals(doctor.getLastname()));
		Assert.assertTrue(response.getAddress().equals(doctor.getAddress()));
		Assert.assertTrue(response.getSpeciality().equals(doctor.getSpeciality()));
		Assert.assertTrue(response.getEmail().equals(doctor.getEmail()));

	}

}
