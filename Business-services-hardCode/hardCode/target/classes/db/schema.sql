create TABLE users (
id INT PRIMARY KEY,
username VARCHAR(150),
password VARCHAR(20),
login VARCHAR(20)
);
CREATE TRIGGER login_update
AFTER INSERT  ON users
BEGIN
    UPDATE users
        SET login = username;
END;


create sequence users_seq
minvalue 1
maxvalue 99999999999999
start with 1
increment by 1;

--DROP TABLE users;
--DROP SEQUENCE users_seq;


create TABLE SMS(
    sms_id INT ,
    id INT,
    reciever VARCHAR2(50),
    text VARCHAR2(256),
    status VARCHAR(30),
    PRIMARY KEY(sms_id),
    FOREIGN KEY(id) REFERENCES users(id),
    CONSTRAINT chkStatusSMS  CHECK(status IN('send','sent','error' ))
    );
    
 -- DROP TABLE SMS;
    
create TABLE email (
    email_id INT ,
    id INT,
    reciever VARCHAR2(50),
    text VARCHAR2(256),
    status VARCHAR(30),
    PRIMARY KEY(email_id),
    FOREIGN KEY(id) REFERENCES users(id),
    CONSTRAINT chkStatusEmail  CHECK(status IN('send','sent','error' ))
  
      );
 --DROP TABLE email;
    
    
create TABLE call (
    call_id INT ,
    id INT,
    status VARCHAR(30),
    reciever VARCHAR2(50),
    PRIMARY KEY(call_id),
    FOREIGN KEY(id) REFERENCES users(id),
    CONSTRAINT chkStatusCall  CHECK(status IN('call','in_call','error' )),
    CONSTRAINT chkReciever CHECK(LENGTH(reciever)=10 OR LENGTH(reciever)=13)
    );
    
   --DROP TABLE call;
 create TABLE privilege (
     id INT PRIMARY KEY ,
     privilege_name VARCHAR(150), 
     key VARCHAR2(10)
 );
--DROP TABLE privilege;

create TABLE users_privilege(
    id           INT GENERATED ALWAYS AS IDENTITY,
    id_user      INT,
    id_privilege INT,
    PRIMARY KEY (id),
    FOREIGN KEY (ID_USER) REFERENCES users (id),
    FOREIGN KEY (ID_PRIVILEGE) REFERENCES privilege (id)
);
--DROP TABLE users_privilege;