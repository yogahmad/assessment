package com.example.tic_tac_toe.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AIClient {
    public static int[] getMove(String board, String playingAs) throws RuntimeException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + OpenAIConfig.getApiKey());

            System.out.println("Request Body: " + OpenAIConfig.getApiKey());

            String requestBody = String.format(OpenAIConfig.REQUEST_TEMPLATE, playingAs, board);

            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            String response = restTemplate.postForObject(OpenAIConfig.API_URL, request, String.class);

            JsonNode rootNode = mapper.readTree(response);
            String content = rootNode.path("choices").get(0).path("message").path("content").asText();
            JsonNode moveNode = mapper.readTree(content);

            int row = moveNode.get("row").asInt();
            int col = moveNode.get("col").asInt();

            return new int[] { row, col };

        } catch (Exception e) {
            throw new RuntimeException("Failed to get AI move: " + e.getMessage(), e);
        }
    }
}
