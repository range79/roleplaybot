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

# how to downland and use?

- Download the latest release bundle automatically
 
```shell
curl -s https://api.github.com/repos/range79/roleplaybot/releases/latest \
| grep browser_download_url \
| grep bundle \
| cut -d '"' -f 4 \
| xargs curl -L -o bundle.zip
```

-  create folder , copy files to folder and change directory


```shell
mkdir roleplay-bundle
unzip bundle.zip -d roleplay-bundle
cd roleplay-bundle
```

- .env File

Create `.env` file like env.example

- creating character modelfile


---

## Run

```bash
docker compose up -d
```

4. Create AI Character (Modelfile)

Go to models folder:

```bash
cd ollama/models
````

Copy example:

```bash
cp Modelfile.example Modelfile
```

Edit file:

```bash
nano Modelfile
```

Example:

```txt
FROM llama3.2:3b

SYSTEM """
You are a friendly roleplay character.
You talk in a fun and emotional way.
You care about the user.
"""
```
- docker compose up -d 



Bot is online.



