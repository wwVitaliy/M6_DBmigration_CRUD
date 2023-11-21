-- find client with max projects
select name, count(c.id) as project_count
	from client c join project p on c.id = p.client_id
	group by c.id
	having count(c.id) = (
					select count(client_id) as project_count
						from project
							group by client_id
							order by project_count desc
					limit 1
					);
		
	