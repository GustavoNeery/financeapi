CREATE TABLE IF NOT EXISTS month_costs(
    id UUID not null,
    period date not null,
    total_spent numeric(18, 2) not null
);