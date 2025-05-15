  CREATE TABLE IF NOT EXISTS expenses (
      id UUID not null primary key,
      name varchar not null,
      transaction_date date not null,
      installments_count int,
      purchase_value numeric(18, 2) not null,
      category varchar(30) not null,
      created_at timestamp,
      updated_at timestamp,
      constraint chk_category check (category in ('food', 'transportation', 'health', 'entertainment', 'shopping', 'education', 'subscriptions'))
  );