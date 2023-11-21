-- find longest project
select id, extract(year from age(finish_date ,start_date))*12
	+ extract(month from age(finish_date ,start_date)) as month_count
	from project
	where extract(year from age(finish_date ,start_date))*12
	+ extract(month from age(finish_date ,start_date)) = (
		select extract(year from age(finish_date ,start_date))*12
		+ extract(month from age(finish_date ,start_date)) as month_count 
			from project
			order by month_count desc
		limit 1
	);