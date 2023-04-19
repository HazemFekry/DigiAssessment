select td.user_id , u.username ,td.training_id , Date(td.training_date), count(td.training_id)
from training_details td inner join "user" u on td .user_id =u.user_id  
group by  td.user_id, u.username ,training_id ,td.training_date having count(td.training_id)>1 order by td.training_date  desc