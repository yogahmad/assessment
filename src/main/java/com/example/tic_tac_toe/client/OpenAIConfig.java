package com.example.tic_tac_toe.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenAIConfig {
    private static String apiKey;

    public static final String API_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api.key}")
    public void setApiKey(String key) {
        apiKey = key;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static final String REQUEST_TEMPLATE = """
            {
                "model": "gpt-4o",
                "messages": [
                    {
                        "role": "system",
                        "content": "You are playing tic-tac-toe as an AI. you are playing as %s and will be given a board status. return the next move in the following JSON format. Use 0-based index for row and column. Make sure your next move is valid (in an empty cell). Play as optimally as possible. A player wins in Tic-Tac-Toe when they place three of their marks (either X or O) in a row, column, or diagonal."
                    },
                    {
                        "role": "user",
                        "content": "Board status: %s with . is an empty cell"
                    }
                ],
                "response_format": {
                    "type": "json_schema",
                    "json_schema": {
                        "name": "NextMove",
                        "strict": true,
                        "schema": {
                            "type": "object",
                            "properties": {
                                "row": { "type": "integer" },
                                "col": { "type": "integer" }
                            },
                            "required": ["row", "col"],
                            "additionalProperties": false
                        }
                    }
                }
            }""";
}