#!/bin/sh
set -eu

: "${MODEL:=llama3.2:3b}"
: "${MODEL_NAME:=character}"


ollama serve &
OLLAMA_PID=$!


i=0
until curl -fsS http://127.0.0.1:11434/api/tags >/dev/null 2>&1; do
  i=$((i+1))
  [ "$i" -ge 60 ] && echo "Ollama did not start in time" && exit 1
  sleep 1
done


ollama pull "$MODEL"
ollama pull nomic-embed-text


MODELFILE=/ollama/Modelfile
echo "FROM $MODEL" > "$MODELFILE"
cat /ollama/models/Modelfile.template >> "$MODELFILE"


ollama create "$MODEL_NAME" -f "$MODELFILE" >/dev/null 2>&1 || true


wait "$OLLAMA_PID"
