#Простой список дел

С добавлением, удалением, пометкой "выполнено"

###Тестовое задание для JavaRush

Использованные технологии:
Spring MVC-Hibernate-Maven

Локальная конфигурация для тестирования Tomcat 7.0+, MySQL

Жестко прописано три пользователя
user, guest, admin

Пароль для admin: admin
Пароль для user и guest - пустая строка


SQL-скрипт для создания БД test с таблицей todo несколькими тестовыми строками
Пользователь БД: root, пароль root


CREATE TABLE test.todo (
  id int(11) NOT NULL AUTO_INCREMENT,
  user varchar(255) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  targetdate date DEFAULT NULL,
  isdone tinyint(1) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

INSERT 
	INTO test.todo 
		(user, description, targetdate, isdone)
	VALUES
		("guest", "important task No 1", NOW(), 0);
INSERT 
	INTO test.todo 
		(user, description, targetdate, isdone)
	VALUES
		("guest", "important task No 2", NOW(), 0);
INSERT 
	INTO test.todo 
		(user, description, targetdate, isdone)
	VALUES
		("guest", "important task No 3", NOW(), 0);
INSERT 
	INTO test.todo 
		(user, description, targetdate, isdone)
	VALUES
		("user", "important task No 1", NOW(), 0);
INSERT 
	INTO test.todo 
		(user, description, targetdate, isdone)
	VALUES
		("user", "important task No 2", NOW(), 0);
