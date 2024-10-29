package com.EmployeeApp.resources;
//
//import com.tngtech.java.junit.dataprovider.*;
//
//import io.restassured.http.ContentType;
//
//import org.junit.jupiter.api.Nested;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//
//import java.sql.Date;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTests {
	
//	@Test
//	public void shouldCreateEmployee(){
//	  given().contentType(ContentType.JSON)
//	    .body("""
//	              {
//					    "fname": "Tiffany",
//					    "mname": "Marie",
//					    "lname": "Cross",
//					    "dob": "1992-06-04",
//					    "position": "developer"
//	    		  }
//	            """
//	    )
//	    //.header("Authorization", "Bearer " + token)
//	    .when()
//	    	.post("/employee")
//	    .then()
//	    	.statusCode(200)
//	    //.log().all()
//	    	.and()
////	    	.body(matchesJsonSchema("example_schema.json"));
//	    .body("id", notNullValue()) 
//	    .body("fname", equalTo("Tiffany"))
//	    .body("mname", equalTo("Marie"))
//	    .body("lname", equalTo("Cross"))
//	    .body("dob", equalTo("1992-06-04"))
//	    .body("position", equalTo("developer"));
//	  
//	  
//	}
	
//	
//	@RunWith(DataProviderRunner.class)
//	@Nested
//	class TestEmp {
//		
//		@DataProvider
//		public static Object[][] employeeInfo() {
//			return new Object[][] {
//				{1, "Tiffany", "Marie", "Cross", "1992-06-04", "Developer"},
//				{2, "Tiffany", "Marie", "Cross", "1992-06-04", "Developer"}
//			};
//		}
//		
//		
//		@UseDataProvider("employeeInfo")
//		@Test
//		public void requestIDsFromCollection_checkFirstNameInResponseBody_expectPerson(Integer id, String fname, String mname, String lname, String dob, String position) {
//			System.out.println(id);
//			given()
//				.pathParam("id", id)
//			.when()
//				.get("/employee/{id}")
//			.then()
//				//.assertThat()
//				//.log().all();
//				.body("'fname'", equalTo("Tiffany"));
//		}
//		
////		@DataProvider
////		public static Object[][] employeeInfo() {
////			return new Object[][] {
////				{1, "Tiffany", "Marie"},
////				{2, "Tiffany", "Cross"}
////			};
////		}
////		
////		
////		@UseDataProvider("employeeInfo")
////		@Test
////		public void requestIDsFromCollection_checkFirstNameInResponseBody_expectPerson(Integer id, String first_name, String last_name) {
////			System.out.println(id);
////			given()
////				.pathParam("id", id)
////			.when()
////				.get("https://reqres.in/api/users/{id}")
////			.then()
////				//.assertThat()
////				.log().all();
////				//.body("fname[0].'fname'", equalTo("Tiffany"));
////		}
//	}

	}
	
