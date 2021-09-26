import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

import static org.hamcrest.Matchers.is;

public class RestTest {
    @Test
    public void myRestTest(){
        RestAssured.when().get("https://postman-echo.com/get?foo1=bar1&foo2=bar2").then()
                .assertThat().statusCode(200)
                .and().body("args.foo1",is("bar1"))
                .and().body("args.foo2",is("bar2"));
    }
    @Test
    public void  myPostTest(){
        String someRandomString = String.format("%1$TH%1$TM%1$TS",new Date());
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        JSONObject requestBody = new JSONObject();
        requestBody.put("FirstName", someRandomString);
        requestBody.put("LastName", someRandomString);
        requestBody.put("password", someRandomString);
        requestBody.put("UserName", someRandomString);
        requestBody.put("Email",someRandomString+"gmail.com");
        RequestSpecification requestSpecification= RestAssured.given();
        requestSpecification.header("Content-Type","application/json");
        //requestSpecification.body(requestBody.toString());
        //using GSON:
        requestSpecification.body(gson.toJson(requestBody));
        Response response = requestSpecification.post("https://webhook.site/5c0e699e-5489-4858-8c42-44c35dc30286");
        int statusCode = response.getStatusCode();
        response.body();
        Assert.assertEquals(statusCode,200);

    }
    @Test
    public void restPut(){
        int empid=15410;
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/";
        RequestSpecification spec = RestAssured.given();
        JSONObject requestParam = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        requestParam.put("name","Test");
        requestParam.put("age",35);
        requestParam.put("salary",1000);
        //spec.body(requestParam.toString());
        //using GSON:
        spec.body(gson.toJson(requestParam));
        Response response = spec.put("/update/"+empid);
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test
    public  void restDelete(){
        int empid=15410;
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/";
        RequestSpecification spec = RestAssured.given();
        Response response = spec.delete("/delete/"+empid);

        String responseString = response.asString();
        DataDeletedInfo dataDeletedInfo = gson.fromJson( responseString,DataDeletedInfo.class);
        System.out.println(dataDeletedInfo);

    }
    class DataDeletedInfo{
        String status;
        String data;
        String message;

        public DataDeletedInfo(String status, String data, String message) {
            this.status = status;
            this.data = data;
            this.message = message;
        }
        public String toString(){
            return "Response: ["+"status: "+status+", data: "+ data+", message: "+message+"]";
        }
    }
}
