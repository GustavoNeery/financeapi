CREATE TABLE monthly_expenses(
    id UUID not null,
    month_year date not null,
    total_spent numeric(18, 2) not null
);