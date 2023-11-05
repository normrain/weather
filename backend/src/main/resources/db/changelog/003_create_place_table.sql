CREATE TABLE place(
                     id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                     name VARCHAR not null,
                     forecast_daytime_id uuid not null,
                     phenomenon VARCHAR not null,
                     tempmin SMALLINT not null,
                     tempmax SMALLINT not null
);