CREATE TABLE account_role
(
    id         uuid        NOT NULL PRIMARY KEY,
    account_id uuid        NOT NULL,
    name       varchar(16) NOT NULL,
    CONSTRAINT account_id_fk FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE INDEX account_role_account_id_index ON account_role (account_id);
