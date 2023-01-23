package com.prueba.apirest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.apirest.dto.FloresDto;
import com.prueba.apirest.dto.FloresDtoID;
import com.prueba.apirest.entity.Flores;
@AutoConfigureMockMvc
@SpringBootTest
class FloresControllerTest {
	
	@Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private ObjectMapper objectMapper;
	 
	@Test
	void findAll() throws Exception{
		List<FloresDtoID> flores = createFlores1();
		
		mockMvc.perform(
	            MockMvcRequestBuilders.post("/api/flores")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(flores)))
	        .andExpect(status().isOk());
		MvcResult findById = mockMvc.perform(
	            get("/api/floreskometsales").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
		
		List<FloresDtoID> rFlores=objectMapper.readValue(findById.getResponse().getContentAsString(), new TypeReference<List<FloresDtoID>>(){});
		
		assert rFlores.size() == 6;
		
		flores = createFlores2();
		
		mockMvc.perform(
	            MockMvcRequestBuilders.post("/api/flores")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(flores)))
	        .andExpect(status().isOk());
		findById = mockMvc.perform(
	            get("/api/floreskometsales").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
		
		rFlores=objectMapper.readValue(findById.getResponse().getContentAsString(), new TypeReference<List<FloresDtoID>>(){});
		
		assert rFlores.size() == 5;
	}
	@Test
	void findAllkometsales() throws Exception{
		MvcResult findById = mockMvc.perform(
	            get("/api/floreskometsales").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
		
		List<FloresDto> rFlores=objectMapper.readValue(findById.getResponse().getContentAsString(), new TypeReference<List<FloresDto>>(){});
		for(int i=0;i<rFlores.size()-1;i++) {
			String name1=rFlores.get(i).getName();
			String name2=rFlores.get(i+1).getName();
			assert name1.compareTo(name2)>=0;
			assert name1.contains("-kometsales");
			assert name2.contains("-kometsales");
		}
	}
	@Test
	void findAllOverPrice() throws Exception{
		MvcResult findById = mockMvc.perform(
	            get("/api/flores?pPrice=20.0").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
		
		List<FloresDtoID> rFlores=objectMapper.readValue(findById.getResponse().getContentAsString(), new TypeReference<List<FloresDtoID>>(){});
		assert rFlores.size() == 3;
		
		findById = mockMvc.perform(
	            get("/api/flores?pPrice=26.0").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
		
		rFlores=objectMapper.readValue(findById.getResponse().getContentAsString(), new TypeReference<List<FloresDtoID>>(){});
		assert rFlores.size() == 2;
	}
	@Test
	void deleteFlor() throws Exception{
		MvcResult findById = mockMvc.perform(
	            get("/api/floreskometsales").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
		
		List<FloresDto> rFlores=objectMapper.readValue(findById.getResponse().getContentAsString(), new TypeReference<List<FloresDto>>(){});
		assert rFlores.size() == 5;
		
		MvcResult delete = mockMvc.perform(
	            delete("/api/flores/1").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(content().string("Deleted flor id - 1"))
	        .andReturn();
	
		findById = mockMvc.perform(
	            get("/api/floreskometsales").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
		rFlores=objectMapper.readValue(findById.getResponse().getContentAsString(), new TypeReference<List<FloresDto>>(){});
		assert rFlores.size() == 4;
	}
	
	@Test
	void findAllByName() throws Exception{
		MvcResult findById = mockMvc.perform(
	            get("/api/flores?pName=amapola").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
		
		List<FloresDtoID> rFlores=objectMapper.readValue(findById.getResponse().getContentAsString(), new TypeReference<List<FloresDtoID>>(){});
		System.out.println(rFlores.size());
		assert rFlores.size() == 1;
		
		findById = mockMvc.perform(
	            get("/api/flores?pName=petunia").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
		
		rFlores=objectMapper.readValue(findById.getResponse().getContentAsString(), new TypeReference<List<FloresDtoID>>(){});
		System.out.println(rFlores.size());
		assert rFlores.size() == 2;
	}
	private List<FloresDtoID> createFlores2() {
		List<FloresDtoID> flores = new ArrayList<FloresDtoID>();
		FloresDtoID flor1 = new FloresDtoID( 1,"amapola", 5);
		FloresDtoID flor2 = new FloresDtoID( 2,"petunia", 1.3);	
		FloresDtoID flor3 = new FloresDtoID( 3,"violeta", 25);
		FloresDtoID flor4 = new FloresDtoID( 4,"petunia2", 36);
		FloresDtoID flor5 = new FloresDtoID( 5,"hortensia", 42);
		flores.add(flor1);
		flores.add(flor2);
		flores.add(flor3);
		flores.add(flor4);
		flores.add(flor5);
	    return flores;
	  }
	
	private List<FloresDtoID> createFlores1() {
		List<FloresDtoID> flores = new ArrayList<FloresDtoID>();
		FloresDtoID flor1 = new FloresDtoID( 1,"clivias", 55);
		FloresDtoID flor2 = new FloresDtoID( 2,"orquídea", 11.3);	
		FloresDtoID flor3 = new FloresDtoID( 3,"cardo amarillo", 225);
		FloresDtoID flor4 = new FloresDtoID( 4,"jazmín", 31.6);
		FloresDtoID flor5 = new FloresDtoID( 5,"azafrán", 42.6);
		FloresDtoID flor6 = new FloresDtoID( 6,"geranio", 45);
		flores.add(flor1);
		flores.add(flor2);
		flores.add(flor3);
		flores.add(flor4);
		flores.add(flor5);
		flores.add(flor6);
	    return flores;
	  }
}
