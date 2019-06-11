select * from users

select email, reason
from users u, cancellation_reasons cr
where u.id_user = cr.id_user

select email, name 
from users u, categories c, user_category uc 
where u.id_user = uc.id_user and c.id_category = uc.id_category