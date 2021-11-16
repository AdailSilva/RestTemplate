package com.adailsilva.resttemplate.resources;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adailsilva.resttemplate.entities.Category;

@RestController
public class CategoryController {

	// Example call in another API project: LoRaWAN_IoTEnergyMeterAPI
	private final String URI_CATEGORIES = "/categories";
	private final String URI_CATEGORIES_ID = "/categories/{id}";

	@Autowired
	RestTemplate restTemplate;

	// ********** GET APIs **********//

	// 1. getForObject(url, classType)
	@GetMapping("/all-categories_v1")
	public ResponseEntity<List<Category>> getAll_v1() {
		Category[] categoriesArray = restTemplate.getForObject(URI_CATEGORIES, Category[].class);
		return new ResponseEntity<>(Arrays.asList(categoriesArray), HttpStatus.OK);
	}

	@GetMapping("/all-categories_v1/{id}")
	public ResponseEntity<Category> getById_v1(@PathVariable String id) {
		Map<String, String> params = new HashMap<>();
		params.put("id", id);

		Category category = restTemplate.getForObject(URI_CATEGORIES_ID, Category.class, params);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	// 2. getForEntity(url, responseType)
	@GetMapping("/all-categories_v2")
	public ResponseEntity<Category[]> getAll_v2() {
		ResponseEntity<Category[]> responseEntity = restTemplate.getForEntity(URI_CATEGORIES, Category[].class);
		return responseEntity;
	}

	@GetMapping("/all-categories_v2/{id}")
	public ResponseEntity<Category> getById_v2(@PathVariable String id) {
		Map<String, String> params = new HashMap<>();
		params.put("id", id);

		ResponseEntity<Category> responseEntity = restTemplate.getForEntity(URI_CATEGORIES_ID, Category.class, params);
		return responseEntity;
	}

	// 3. exchange(url, method, requestEntity, responseType)
	@GetMapping("/all-categories_v3")
	public ResponseEntity<Category[]> getAll_v3() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("X-HEADER_NAME", "XYZ");

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Category[]> responseEntity = restTemplate.exchange("/categories", HttpMethod.GET, entity,
				Category[].class);
		return responseEntity;
	}

	@GetMapping("/all-categories_v3/{id}")
	public ResponseEntity<Category> getById_v3(@PathVariable String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("X-HEADER_NAME", "XYZ");

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Category> responseEntity = restTemplate.exchange("/categories/" + id, HttpMethod.GET, entity,
				Category.class);
		return responseEntity;
	}

	// ********** POST APIs **********//

	@PostMapping("/categories")
	public Category create(@RequestBody final Category newCategory) {
		Category createdCategory = restTemplate.postForObject(URI_CATEGORIES, newCategory, Category.class);
		return createdCategory;
	}

}
