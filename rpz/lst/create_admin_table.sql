CREATE TABLE IF not EXISTS admins
(
    adminID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	login VARCHAR(32),
	password_ VARCHAR(32)
);
