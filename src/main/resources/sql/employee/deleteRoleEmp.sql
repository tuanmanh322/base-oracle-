DELETE  FROM employee_role er
WHERE EXISTS(
SELECT
    1
FROM employee e
WHERE e.id = er.employee_id )
