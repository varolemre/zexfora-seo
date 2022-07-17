CREATE TABLE "user"
(
    ID                      UUID      DEFAULT uuid_in((md5((random())::TEXT))::CSTRING),
    CREATE_DATE        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    UPDATE_DATE        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    OPERATION_TIPE             VARCHAR(48)                        NOT NULL,
    MAIL             VARCHAR(48)                        NOT NULL,
    PASSWORD             VARCHAR(48)                        NOT NULL,
)