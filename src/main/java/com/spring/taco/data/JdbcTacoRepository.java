package com.spring.taco.data;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.spring.taco.Taco;



@Repository
public class JdbcTacoRepository implements TacoRepository{

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Taco save(Taco pTacko) {
		long tacoId = saveTacoInfo(pTacko);
		pTacko.setId(tacoId);
		for(String ingredient : pTacko.getIngredients()) {
			saveIngredientToTaco(ingredient,tacoId);
		}
		return pTacko;
	}

	private void saveIngredientToTaco(String ingredient, long tacoId) {
		jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)", 
				tacoId,ingredient);
		
	}

	private long saveTacoInfo(Taco pTacko) {
		pTacko.setCreatedAt(new Date());
		KeyHolder key = new GeneratedKeyHolder();
		// Below code was not retuiong the id of taco hence using the different way
//		PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
//				"insert into Taco (name, createdAt) values (?, ?)",
//				Types.VARCHAR, Types.TIMESTAMP).newPreparedStatementCreator(
//						Arrays.asList(pTacko.getName(), pTacko.getCreatedAt()));
		
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
				"insert into Taco (name, createdAt) values (?, ?)",
				Types.VARCHAR, Types.TIMESTAMP);
		// Default return will be false hecne setting the true.
		factory.setReturnGeneratedKeys(Boolean.TRUE);
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(Arrays.asList(pTacko.getName(),
				pTacko.getCreatedAt()));
		jdbc.update(psc, key);
		return  key.getKey().longValue();
	}

}
