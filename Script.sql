--<ScriptOptions statementTerminator=";"/>

CREATE TABLE train_route (
	id INT NOT NULL,
	source VARCHAR(20),
	destination VARCHAR(20),
	time INT,
	cost INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

