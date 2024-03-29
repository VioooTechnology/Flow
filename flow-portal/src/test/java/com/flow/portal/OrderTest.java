package com.flow.portal;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flow.pub.util.MD5Util;
import com.flow.pub.util.PageUtil;
import com.flow.system.model.Order;
import com.flow.system.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/applicationContext.xml",})
public class OrderTest {

	@Autowired
	private OrderService orderService;
	

	/**
	 * 下游订单接口，订单初始化
	 */
	@Test
	public void testUrl() {
		try {
			String appkey = "1234567890";
			String phone = "18601018888";
			String product_id = "200"; // 500M
			String order_id = "20160728000006";
			long time = System.currentTimeMillis();
			String secret = "222222";
			String sign = MD5Util.EncodeString(String.format("phone=%s&product_id=%s&order_id=%s&time=%d&secret=%s",
					phone, product_id, order_id, time, secret));
			System.out.println(sign + "--->" + time + "appkey" + appkey);
			// http://localhost:8080/api/order?appkey=1234567890&phone=18610009898&product_id=500&order_id=20160727000012&time=1469635034816&sign=18E4065F8480B597ED422411572565C1
//			String url = "http://localhost:8080/api/order?appkey=1234567890&phone=18610009898&product_id=500&order_id=20160727000012&time=1469635034816&sign=18E4065F8480B597ED422411572565C1";
			System.out.println(String.format(
					"http://localhost:8080/api/order?appkey=1234567890&phone=%s&product_id=%s&order_id=%s&time=%d&sign=%s",
					phone, product_id, order_id, time, sign));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testListPage(){
		Map<String,Object> map = new HashMap<String,Object>();
		PageUtil<Order> orderList = orderService.listPage(map);
		System.out.println(orderList);
	}
}
