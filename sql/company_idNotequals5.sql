select person.name, company.name
from person 
join company
on person.company_id = company.id where company.id != 5