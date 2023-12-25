-- find oldest and youngest workers
select 'youngest' as type, name, birthday  from worker
	where birthday = (select max(birthday) from worker)
union 
select 'eldest' as type, name, birthday   from worker
	where birthday = (select min(birthday) from worker);
	

