--find project cost
select project_id, sum(salary* 
	extract(year from age(finish_date ,start_date))*12
	+ extract(month from age(finish_date ,start_date))) as price
	from project_worker
	join worker on worker_id = worker.id
	join project on project_id = project.id
	group by project_id
	order by price desc;	