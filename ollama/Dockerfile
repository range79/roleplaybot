FROM ollama/ollama:latest

WORKDIR /ollama
COPY Modelfile /ollama/Modelfile

RUN ollama serve & \
    sleep 6 && \
    ollama pull llama3.2:3b && \
    ollama pull nomic-embed-text && \
    ollama create freak-pierrot -f /ollama/Modelfile

EXPOSE 11434
