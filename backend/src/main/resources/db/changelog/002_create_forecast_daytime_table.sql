CREATE TABLE forecast_daytime(
                     id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                     phenomenon VARCHAR not null,
                     tempmin SMALLINT not null,
                     tempmax SMALLINT not null,
                     text VARCHAR,
                     type VARCHAR(5) not null
);