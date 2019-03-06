This application has been implemented using the JDBC temnplate and H2 data base.
JDBC is using the "update" API for saving the Taco and Ingredient data in the H2 data bese.
Order Data will be saved using the "SimpleJdbcInsert"

JdbcTemplate  -> 1 Using Update methdo
				  2- Using the SimpleJdbcInsert wrapper class
				  


If using JPA and data.sql then can't use the schema.sql -> Either keep the sql in schema or do not have that file.
				  