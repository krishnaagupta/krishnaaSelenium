package RestAssuredAutomation;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredOperations {
	

	



		String sHost="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		String sURI="/login";
		String sURILogOut="/logout";
		String sURIGetData="/getdata";
		String SURIAddData="/addData";
		String sURIUpdateData="/updateData";
		String sURIdeleteData="/deleteData";
		String sURL=null;
		String sToken=null;
		@BeforeSuite
		@Test(priority=1)
		public void logInApi() {
			sURL=sHost+sURI;
			RestAssured.baseURI=sURL;
			Response res=RestAssured.given().contentType("application/json").body(" {\"username\":\"krishnaa.gupta@gmail.com\",\"password\":\"krishnaa123\"} ").post();
			res.prettyPrint();
			System.out.println(res.jsonPath().getString("token[0]"));
			System.out.println("log-in"+res.getStatusCode()+res.statusLine());
			sToken=res.jsonPath().getString("token[0]");
		}
		@BeforeMethod
		public void setDependencyBeforeTheMethod() {
			sURL=null;
		}
	@Test(priority=2)
		public  void getData() {
		
		sURL=sHost+sURIGetData;
		Map<String,String>newMap=new HashMap();
		newMap.put("token",sToken);	
		RestAssured.baseURI=sURL;
		Response res=RestAssured.given().contentType("application/json").headers(newMap).get();
		res.prettyPrint();
		System.out.println(res.jsonPath().getString("accountno").toString());
		//System.out.println(res.jsonPath().getString("userid").toString());
		//System.out.println(res.jsonPath().getString("id").toString());
		System.out.println("Get-Data"+res.getStatusCode()+res.statusLine());
	}
	//Add data
	@Test(priority=3)
	public void addData() {
		sURL=sHost+SURIAddData;
		Map<String,String>newMap=new HashMap();
		newMap.put("token",sToken);	
		RestAssured.baseURI=sURL;
		// adding a data the json will have only four new data 
		Response res=RestAssured.given().contentType("application/json").headers(newMap).body("{\"accountno\": \"TA-4491001\", \"departmentno\": \"7\", \"salary\": \"9000\", \"pincode\": \"908760\"}").post();
		System.out.println("Add data"+res.getStatusCode()+res.statusLine());
	}

	//Edit data
	@Test(priority=5)
	public void editData() {
		sURL=sHost+sURIUpdateData;
		Map<String,String>newMap=new HashMap();
		newMap.put("token",sToken);	
		RestAssured.baseURI=sURL;
		// adding a data the json will have only four new data 
		Response res=RestAssured.given().contentType("application/json").headers(newMap).body("{\"accountno\":\"TA-4491001\",\"departmentno\":16,\"salary\":9000,\"pincode\":908767,\"userid\":\"xfvwphFcCCoaYVEl5Zb6\",\"id\":\"TXkShPdKhxIaiBhMrUGo\"}").put();
		System.out.println("Edit data"+res.getStatusCode()+res.statusLine());
	}
//delete
	@Test(priority=6)
	public void deleteData() {
		sURL=sHost+sURIdeleteData;
		Map<String,String>newMap=new HashMap();
		newMap.put("token",sToken);	
		RestAssured.baseURI=sURL;
		// adding a data the json will have only four new data 
		Response res=RestAssured.given().contentType("application/json").headers(newMap).body("{\"id\":\"TXkShPdKhxIaiBhMrUGo\",\"userid\":\"xfvwphFcCCoaYVEl5Zb6\"}").delete();
		System.out.println("Delete data"+res.getStatusCode()+res.statusLine());
	}
//logout
	@Test(priority=7)
	public  void logOut() {
		
		sURL=sHost+sURILogOut;	
		Map<String,String>newMap=new HashMap();
		newMap.put("token",sToken);
		RestAssured.baseURI=sURL;
		Response res=RestAssured.given().contentType("application/json").headers(newMap).body("{\"token\":null} ").post();
		System.out.println("Log-out"+res.getStatusCode()+res.statusLine());

	}
}

