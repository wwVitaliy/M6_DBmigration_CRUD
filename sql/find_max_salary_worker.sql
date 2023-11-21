-- find workers with max salary
select * from worker
	where salary = (
		select salary from worker w 
			order by salary desc
			limit 1
	);