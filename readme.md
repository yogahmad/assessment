# Tic-Tac-Toe

Features:

- Local multiplayer
- Game vs AI

Deployed on AWS: [Play Game](https://vypcepjcye.ap-southeast-1.awsapprunner.com/)

# Vs AI
Vs AI algorithm feature is implemented with OpenAI's GPT-4o.

*It turns out that GPT-4o is not good at tic-tac-toe*

You can find the OpenAI integration [here](https://github.com/yogahmad/assessment/blob/main/src/main/java/com/example/tic_tac_toe/client/OpenAIConfig.java) and [here](https://github.com/yogahmad/assessment/blob/main/src/main/java/com/example/tic_tac_toe/client/AIClient.java)

Tried these LLMs with similar or worse result:
- `mistral-large-latest`
- `deepseek V3`

Haven't tried LLMs with reasoning capability yet:
- `deepseek R1` (Because of unavailability of `deepseek R1`)
- `GPT-o3`

# How to run

- Clone the repository
- `cp .env.example .env`
- run `docker compose up --build`
