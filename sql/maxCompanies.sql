select company.name, count
from company
join (select company_id, count(company_id) as count 
	  from person group by company_id) as table_1
on company_id = company.id 
where count = (select max(count) as max from 
			   (select company_id, count(company_id) as count 
				from person 
				group by company_id) as table_2)
group by company.name, count