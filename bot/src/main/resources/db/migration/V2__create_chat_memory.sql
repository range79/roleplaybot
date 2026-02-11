CREATE
EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS public.chat_memory
(
    id
    UUID
    PRIMARY
    KEY
    DEFAULT
    gen_random_uuid
(
),
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now
(
),
    is_bot BOOLEAN NOT NULL DEFAULT FALSE
    );

CREATE INDEX IF NOT EXISTS idx_chat_memory_created_at
    ON public.chat_memory (created_at DESC);
