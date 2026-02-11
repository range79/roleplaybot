CREATE TABLE IF NOT EXISTS public.vector_store
(
    id
    TEXT
    PRIMARY
    KEY,
    content
    TEXT,
    metadata
    JSONB,
    embedding
    vector
(
    768
)
    );
