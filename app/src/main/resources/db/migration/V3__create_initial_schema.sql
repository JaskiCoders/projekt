SET DB_CLOSE_DELAY -1;
CREATE USER IF NOT EXISTS SA SALT '120da73dba932d49' HASH '5b059dd5f5f5e653345977408ec4edda390da4052e2e3027e1d6bdad6ddaf0d7' ADMIN;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_A80A8FA9_D80C_42F2_ACCF_31D2B04B3E2E START WITH 161;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_94784089_4C5C_4C09_AA03_9AD7DA6D5292 START WITH 4;
CREATE CACHED TABLE PUBLIC.ROLE(
  ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_94784089_4C5C_4C09_AA03_9AD7DA6D5292) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_94784089_4C5C_4C09_AA03_9AD7DA6D5292,
  NAME VARCHAR(255) NOT NULL,
  TYPE INTEGER NOT NULL
);
ALTER TABLE PUBLIC.ROLE ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.ROLE;
CREATE CACHED TABLE PUBLIC.USER(
  ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_A80A8FA9_D80C_42F2_ACCF_31D2B04B3E2E) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_A80A8FA9_D80C_42F2_ACCF_31D2B04B3E2E,
  LOGIN VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  ROLE_ID BIGINT NOT NULL,
  ENABLED BOOLEAN NOT NULL,
  USERNAME VARCHAR(255) NOT NULL
);
ALTER TABLE PUBLIC.USER ADD CONSTRAINT PUBLIC.CONSTRAINT_27 PRIMARY KEY(ID);
-- 8 +/- SELECT COUNT(*) FROM PUBLIC.USER;
ALTER TABLE PUBLIC.USER ADD CONSTRAINT PUBLIC.FKN82HA3CCDEBHOKX3A8FGDQEYY FOREIGN KEY(ROLE_ID) REFERENCES PUBLIC.ROLE(ID) NOCHECK;


insert into role values (3,'Admin',0);
insert into user values (0,'test','test',3,true,'test.test')