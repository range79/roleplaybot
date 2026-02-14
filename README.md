## Discord Role Play Bot

Hii  

This is a role play bot for Discord.  
Built with **Spring Boot + Ollama**. Runs AI locally.

## Features

- AI-powered replies using Ollama
- Responds when you @mention the bot
- Role play style conversations
- Mood detection system
- Stores chat history in database

---

## Mood System

Moods:
HAPPY, SAD, ANGRY, TIRED, CALM, ANXIOUS

Commands:



/mood → checks the mentioned user's mood
/make-happy → gives tips to make the client user feel better

---

## Important

You can run it without Docker,  
but trust me, it’s a headache.

Just use Docker.

---

## .env File

Create `.env` and fill this:

```env
DISCORD_USERNAME=WHO-WANT-BE-REPLIED
DISCORD_TOKEN=
DISCORD_OWNER=WHO-WANT-CHECK-USER-HEALT

POSTGRES_DB=
POSTGRES_USER=
POSTGRES_PASSWORD=
````

Don’t leak your token.

---

## Run

```bash
docker compose up -d
```

Bot is online.



