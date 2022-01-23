CREATE TABLE account (
    id              uuid               NOT NULL,
    account_id      uuid               NOT NULL,
    email           text               NOT NULL,
    password_hash   text               NOT NULL,
    first_name      text,
    last_name       text,
    description     text,
    git_hub_url     text,
    created         timestamp          NOT NULL,
    modified        timestamp          NOT NULL,
    is_account      boolean
);

ALTER TABLE ONLY account ADD CONSTRAINT PK_account PRIMARY KEY (id);

ALTER TABLE ONLY account ADD CONSTRAINT UC_account__email UNIQUE (email);

CREATE INDEX account_email_index ON account (email);
