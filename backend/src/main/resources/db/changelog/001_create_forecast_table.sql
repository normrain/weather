CREATE TABLE forecast(
                     id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                     day_id uuid not null,
                     night_id uuid not null,
                     date DATE not null,
                     text VARCHAR
);