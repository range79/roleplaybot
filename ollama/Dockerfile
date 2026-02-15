FROM ollama/ollama:latest

WORKDIR /app



COPY models/ /ollama/models/
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 11434 8080

ENTRYPOINT ["/entrypoint.sh"]
