-- find client foth max projects
select * from client
	where id = (
		select client_id
			from project
				group by client_id
				having count(client_id) = (
					select count(client_id) as project_count 
						from project 
							group by client_id 
							order by project_count desc
					limit 1
				)
	); 
		
	