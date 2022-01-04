CREATE TABLE account (
    id              uuid               NOT NULL,
    email           text               NOT NULL,
    password_hash   text               NOT NULL,
    created         timestamp          NOT NULL,
    modified        timestamp          NOT NULL,
    last_logout     timestamp          NOT NULL
);

ALTER TABLE ONLY account ADD CONSTRAINT PK_account PRIMARY KEY (id);

ALTER TABLE ONLY account ADD CONSTRAINT UC_account__email UNIQUE (email);

CREATE INDEX account_email_index ON account (email);
