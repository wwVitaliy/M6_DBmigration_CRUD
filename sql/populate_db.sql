insert into worker (name, birthday, "level", salary)
values 
	('Bruce Wayne', '1939-03-30', 'Senior', 6000 ),
	('Diana Prince', '1942-01-01', 'Senior', 6000 ),
	('Barry Allen', '1956-10-01', 'Senior', 5000 ),
	('Wally West', '1986-03-01', 'Middle', 3500 ),
	('Arthur Curry', '1941-11-01', 'Middle', 3000),
	('Oliver Queen', '2012-10-10', 'Middle', 2500),
	('John Constantine', '1985-06-06', 'Junior', 1500),
	('Billy Batson', '1940-02-02', 'Junior', 1000),
	('Kara Zor-El', '1959-05-05', 'Trainee', 900),
	('Kyle Rayner', '1994-01-10', 'Trainee', 500);

insert into client (name)
values 
	('Wayne Enterprises'),
	('Allen Limited'),
	('Prince Co.'),
	('Star City'),
	('Emerald Twilight'),
	('Shazam');

insert into project (client_id, start_date, finish_date)
values 
	(1, '2021-03-03', '2025-01-30'),
	(2, '2022-02-02', '2024-05-30'),
	(2, '2022-04-01', '2023-12-30'),
	(2, '2020-03-03', '2024-08-01'),
	(3, '2019-11-10', '2025-12-01'),
	(3, '2018-05-05', '2025-11-01'),
	(3, '2021-06-14', '2026-07-01'),
	(3, '2023-05-03', '2027-05-01'),
	(4, '2020-08-28', '2024-08-01'),
	(4, '2021-07-12', '2025-07-12');

insert into project_worker (project_id, worker_id)
values 
	(1, 1),
	(1, 3),
	(1, 9),
	(2, 2),
	(3, 1),
	(3, 2),
	(3, 3),
	(3, 4),
	(4, 1),
	(4, 4),
	(5,1),
	(5,2),
	(6,3),
	(6,4),
	(6,5),
	(6,6),
	(7,3),
	(8,5),
	(8,6),
	(8,7),
	(8,8),
	(8,9),
	(9,5),
	(9,6),
	(10,7),
	(10,8),
	(10,9);

	