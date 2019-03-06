package com.spring.taco.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.taco.Order;
import com.spring.taco.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository{

	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert ordertacoInserter;
	private ObjectMapper mapper;
	
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		
		this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order")
				.usingGeneratedKeyColumns("id");
		this.ordertacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");
		this.mapper = new ObjectMapper();
	}


	@Override
	public Order save(Order pOrder) {
		pOrder.setCreatedAt(new Date());
		long orderId = saveOrderDetails(pOrder);
		List<Taco> tacos = pOrder.getTacos();
		for(Taco lTaco : tacos) {
			saveTacoToOrder(lTaco , orderId);
		}
		return pOrder;
	}


	private void saveTacoToOrder(Taco lTaco, long orderId) {
		Map<String , Object> convertValue = new HashMap<String, Object>();
		convertValue.put("tacoOrder", orderId);
		convertValue.put("taco", lTaco.getId());
		ordertacoInserter.execute(convertValue);
		
	}


	private long saveOrderDetails(Order pOrder) {
		@SuppressWarnings("unchecked")
		Map<String , Object> convertValue = mapper.convertValue(pOrder, Map.class);
		convertValue.put("createdAt", pOrder.getCreatedAt());
		return orderInserter.executeAndReturnKey(convertValue).longValue();
	}

}
