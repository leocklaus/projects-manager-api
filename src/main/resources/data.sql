INSERT INTO users (id, name, password, email, phone, created_at, updated_at)
VALUES (
    RANDOM_UUID(),      -- Generate a random UUID for the ID
    'Leonardo Klaus',         -- Name
    'password123',      -- Password
    'leocklaus@gmail.com', -- Email
    '1234567890',       -- Phone
    CURRENT_TIMESTAMP,  -- Created_at
    CURRENT_TIMESTAMP   -- Updated_at
);

INSERT INTO users (id, name, password, email, phone, created_at, updated_at)
VALUES (
    RANDOM_UUID(),      -- Generate a random UUID for the ID
    'Ricardo Klaus',         -- Name
    'password123',      -- Password
    'ricklaus@gmail.com', -- Email
    '1239367890',       -- Phone
    CURRENT_TIMESTAMP,  -- Created_at
    CURRENT_TIMESTAMP   -- Updated_at
);