#!/bin/sh
set -eu

: "${MODEL:=llama3.2:3b}"
: "${MODEL_NAME:=character}"

USER_MODELFILE="/config/Modelfile"

if [ ! -f "$USER_MODELFILE" ]; then
  echo "ERROR: No Modelfile provided."
  echo "Mount your Modelfile like this:"
  echo "  - ./Modelfile:/config/Modelfile"
  exit 1
fi

echo "Using base model: $MODEL"
echo "Using user Modelfile: $USER_MODELFILE"

ollama serve &
OLLAMA_PID=$!


i=0
until wget -qO- http://127.0.0.1:11434/api/tags >/dev/null 2>&1; do
  i=$((i+1))
  [ "$i" -ge 60 ] && echo "Ollama did not start in time" && exit 1
  sleep 1
done

ollama pull "$MODEL"

FINAL_MODELFILE="/ollama/Modelfile"
echo "FROM $MODEL" > "$FINAL_MODELFILE"
cat "$USER_MODELFILE" >> "$FINAL_MODELFILE"

ollama create "$MODEL_NAME" -f "$FINAL_MODELFILE" >/dev/null 2>&1 || true

wait "$OLLAMA_PID"
