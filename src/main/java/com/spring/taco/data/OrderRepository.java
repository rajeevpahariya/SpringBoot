package com.spring.taco.data;

import com.spring.taco.Order;

public interface OrderRepository {
	Order save(Order pOrder);
}
