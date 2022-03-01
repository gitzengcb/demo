package com.example.server;

import com.alibaba.fastjson.JSONObject;
import com.example.server.utils.RestAssuredUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class DemoApplicationTests {

//	@Autowired(required = false)
//	IReportService reportService;

	@Test
	public void contextLoads() {
		String url="kapi/hbos-doctor-station/labTest/query";
		String s="{\"healthcareRecordId\":\"377518\",\"patientId\":\"27178\",\"patientName\":\"花锐\",\"applyTime\":false}";
		JSONObject json = JSONObject.parseObject(s);
		Response response=
				given().
						body(json.toJSONString()).
						when().
						post(url).
						then().body("code", equalTo(200)).
						spec(RestAssuredUtil.getDefaultResponseSpecification()).
						extract().response();

		JsonPath path = response.body().jsonPath();
		String id = path.getString("data[0].id");
		System.out.println(id);
	}

}

