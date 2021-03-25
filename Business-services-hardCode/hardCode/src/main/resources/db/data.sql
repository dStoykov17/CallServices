--insert into users(id,username,password,email,phone_numb,login) values (users_seq.nextval,'admin','pass','sfghha','+359456123','admin');
--insert into users(id,username,password,email,phone_numb,login) values (users_seq.nextval,'dada','dsafda2','sfgfhha','+359456122','admin');
--insert into users(id,username,password,email,phone_numb,login) values (users_seq.nextval,'guest','pass','sfgfhha','+359456122','guest');
--insert into users(id,username,password,email,phone_numb,login) values (users_seq.nextval,'dasfaf','dsafda2','sfgfhha','+359456122','guest');
INSERT INTO users(id,username,password) VALUES(users_seq.NEXTVAL,'pesho','pass');
INSERT INTO users(id,username,password,login) VALUES(users_seq.NEXTVAL,'guest','pass','guest');
INSERT INTO sms (id,user_id,status) VALUES(users_seq.NEXTVAL,2,'sending');
INSERT INTO email (id,user_id,status) VALUES(users_seq.NEXTVAL,1,'sending');

insert into privilege ( id,privilege_name, key) values (users_seq.nextval,'CREATE', 'C');
insert into privilege (id, privilege_name, key) values (users_seq.nextval,'READ', 'R');
insert into privilege (id, privilege_name, key) values (users_seq.nextval,'UPDATE', 'U');
insert into privilege (id, privilege_name, key) values (users_seq.nextval,'SMS READ ONLY', 'SMS_R');
insert into privilege (id, privilege_name, key) values (users_seq.nextval,'SMS READ/WRITE ONLY', 'SMS_RW');
insert into privilege (id, privilege_name, key) values (users_seq.nextval,'EMAIL READ ONLY', 'EMAIL_R');
insert into privilege (id, privilege_name, key) values (users_seq.nextval,'EMAIL READ/WRITE ONLY', 'EMAIL_RW');
insert into privilege (id, privilege_name, key) values (users_seq.nextval,'CALL READ ONLY', 'CALL_R');
insert into privilege (id, privilege_name, key) values (users_seq.nextval,'CALL READ/WRITE ONLY', 'CALL_RW');

insert into users_privilege (id_user, id_privilege) (select u.id, p.id
                                                     from users u,
                                                          privilege p
                                                     where u.login = 'admin');
insert into users_privilege (id_user, id_privilege) (select u.id, p.id
                                                     from users u,
                                                          privilege p
                                                     where u.login = 'admin' and p.key = 'RW'
                                                     and p.key ='SMS_RW' and p.key ='EMAIL_RW'
                                                     and p.key ='CALL_RW');--0
insert into users_privilege (id_user, id_privilege) (select u.id, p.id
                                                     from users u,
                                                          privilege p
                                                     where u.login = 'guest' and p.key = 'R');
insert into users_privilege (id_user, id_privilege) (select u.id, p.id
                                                     from users u,
                                                          privilege p
                                                     where u.login = 'guest' and p.key = 'SMS_R');
insert into users_privilege (id_user, id_privilege) (select u.id, p.id
                                                     from users u,
                                                          privilege p
                                                     where u.login = 'guest' and p.key = 'EMAIL_R');
insert into users_privilege (id_user, id_privilege) (select u.id, p.id
                                                     from users u,
                                                          privilege p
                                                     where u.login = 'guest' and p.key = 'CALL_R');