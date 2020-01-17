select e.id id,e.username username,e.email email,e.created_date created_date,e.fullname fullName,e.is_actived is_actived,
       e.last_access last_access,e.phone_number phone_number,e.user_type userType,
       r.role_name role_name,
       d.department_name department_name,
       t.leader_id leader_id

from employee e
         inner join employee_role er on(er.employee_id = e.id)
         inner join role r on(er.role_id = r.id)
         INNER JOIN department d on(e.department_id = d.id)
         INNER join team t on(t.id = e.team_id)
where 1 = 1
