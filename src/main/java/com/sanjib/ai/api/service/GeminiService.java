package com.sanjib.ai.api.service;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
public class GeminiService {

	@Value("${gemini.api.key}")
	private String apiKey;

	
	@PostConstruct

	public void test() {

		System.out.println("Loaded Key: " + (apiKey != null));

		System.out.println("Key Length: " + apiKey.length());

	}
	
	public String askAI(String resumeText, String question) {

		RestTemplate restTemplate = new RestTemplate();

		String prompt =

				"Resume:\n" + resumeText + "\n\nQuestion:\n" + question;

		String body = """
				{
				  "contents": [
				    {
				      "parts": [
				        {
				          "text": "%s"
				        }
				      ]
				    }
				  ]
				}
				""".formatted(prompt.replace("\"", "\\\""));

		HttpHeaders headers = new HttpHeaders();

		//headers.setBearerAuth(apiKey);
		//headers.set("x-goog-api-key", apiKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<>(body, headers);

		String url =

				"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
						+ apiKey;

		try {

			return restTemplate.postForObject(url, request, String.class);

		} catch (HttpClientErrorException e) {

		    System.out.println("Status: " + e.getStatusCode());

		    System.out.println("Response: " +
		            e.getResponseBodyAsString());

		    return e.getResponseBodyAsString();
		}
	}
}