CREATE TABLE role
(
    id   INTEGER      NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE account
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username   VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255),
    email      VARCHAR(255),
    role_id    INTEGER,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_ROLE FOREIGN KEY (role_id) REFERENCES role (id);
CREATE TABLE appointment
(
    id                INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    vaccine_center_id INTEGER,
    patient_id        INTEGER,
    vaccine_id        INTEGER,
    time              VARCHAR(255),
    status            INTEGER,
    CONSTRAINT pk_appointment PRIMARY KEY (id)
);
CREATE TABLE disease
(
    id          INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_disease PRIMARY KEY (id)
);
CREATE TABLE slot
(
    id                INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    time              VARCHAR(255),
    vaccine_center_id INTEGER,
    enable            BOOLEAN,
    CONSTRAINT pk_slot PRIMARY KEY (id)
);
CREATE TABLE vaccination
(
    id INTEGER NOT NULL,
    CONSTRAINT pk_vaccination PRIMARY KEY (id)
);
CREATE TABLE vaccine
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name          VARCHAR(255),
    description   VARCHAR(255),
    instruction   VARCHAR(255),
    dose_required INTEGER,
    disease_id    INTEGER                                  NOT NULL,
    CONSTRAINT pk_vaccine PRIMARY KEY (id)
);

ALTER TABLE vaccine
    ADD CONSTRAINT FK_VACCINE_ON_DISEASE FOREIGN KEY (disease_id) REFERENCES disease (id);
CREATE TABLE vaccine_center
(
    id      INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name    VARCHAR(255)                             NOT NULL,
    address VARCHAR(255),
    contact VARCHAR(255),
    CONSTRAINT pk_vaccine_center PRIMARY KEY (id)
);
ALTER TABLE slot
    ADD CONSTRAINT FK_SLOT_ON_VACCINE_CENTER FOREIGN KEY (vaccine_center_id) REFERENCES vaccine_center (id);