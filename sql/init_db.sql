-- create  tables
create table worker(
	id bigint generated always as identity (start with 1) primary key,
	name varchar not null 
		check (length(name) >=1 and length(name) <=1000),
	birthday date 
		check (birthday > '1900-01-01'),
	level varchar not null 
		check (level in ('Trainee', 'Junior', 'Middle', 'Senior')),
	salary int 
		check (salary >= 100 and salary <= 100000)
);

create table client (
	id bigint generated always as identity (start with 1) primary key,
	name varchar not null 
		check (length(name) >= 2 and length(name) <= 1000)
);

create table project (
	id bigint generated always as identity (start with 1) primary key,
	client_id bigint,
	start_date date,
	finish_date date
);
	
create table project_worker (
	project_id bigint,
	worker_id bigint,
	primary key (project_id, worker_id)
);

-- create relations
alter table project
add constraint project_client_fk
	foreign key (client_id)
	references client(id);

alter table project_worker
add constraint project_worker__project_fk
	foreign key (project_id)
	references project(id),
add constraint project_worker__worker_fk
	foreign key (worker_id)
	references worker(id);



