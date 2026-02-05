FROM ollama/ollama:latest

WORKDIR /ollama

COPY Modelfile /ollama/Modelfile

RUN ollama serve & \
    sleep 6 && \
    ollama pull qwen2.5:7b && \
    ollama create freak-pierrot -f /ollama/Modelfile


EXPOSE 11434

