package gem.qa.GemEcosystem_api;
import com.gemini.generic.ProjectProperties;
import com.gemini.generic.QuanticAPIBase;
import groovyjarjarantlr.Token;
import io.cucumber.java.ca.Quan;
import com.gemini.apitest.ApiHealthCheckUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.gemini.apitest.ApiClientConnect;
import com.gemini.apitest.ProjectApiUrl;
import com.gemini.apitest.ProjectSampleJson;

import com.gemini.dataProvider.QuanticDataProvider;
import com.gemini.generic.QuanticAPIBase;
import com.gemini.quartzReporting.GemTestReporter;
import com.gemini.quartzReporting.STATUS;
import com.google.gson.JsonObject;

import jdk.jshell.Snippet;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class gettoken extends QuanticAPIBase {

    String TO;

    public String token() {
        String Token;
        String urlss = ProjectApiUrl.getUrl("Login");
        JsonObject payloadss = ProjectSampleJson.getSampleData("Login_sampleJson").getAsJsonObject();
        payloadss = (JsonObject) ApiHealthCheckUtils.result(payloadss);
        JsonObject res = null;
        res = ApiClientConnect.postRequest(urlss, String.valueOf(payloadss), "json");
        JsonObject Boddy = (JsonObject) res.get("responseBody");
        JsonObject to = (JsonObject) Boddy.get("data");
        String tokenss = String.valueOf(to.get("token"));
        Token = tokenss;
        return Token;

    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Gettoken(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Get Token API when the JWT is not expired", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("Gettoken");

        GemTestReporter.addTestStep("Url for Get Request", url, STATUS.INFO);

        String j=token();
        JsonObject res = null;
        Map<String,String> headers=new HashMap<>();

        String jnew= j.replaceAll("^\"|\"$","");
        System.out.println(jnew);
        headers.put("Authorization","Bearer "+jnew);
        try {
            res = ApiClientConnect.createRequest("Get",url,"",headers);
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Did not Executed Successfully", STATUS.FAIL);
        }
        System.out.println(res);
        int status = res.get("status").getAsInt();

        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

        if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response After hitting the API ", String.valueOf(body), STATUS.INFO);

            //  System.out.println(body);
            //  System.out.println(res);
            String message = String.valueOf(body.get("message"));
            GemTestReporter.addTestStep("Final Message ", String.valueOf(message), STATUS.PASS);

        } else {

            GemTestReporter.addTestStep("Final response", "No response", STATUS.FAIL);



        }



    }

//    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
//    public void Gettoken_JWT_expired(JsonObject inputData) throws InterruptedException {
//
//
//        GemTestReporter.addTestStep("Test Case", "Test to check the Get Token API when the JWT is  expired", STATUS.INFO);
//
//        String url = ProjectApiUrl.getUrl("Gettoken");
//
//        GemTestReporter.addTestStep("Url for Get Request", url, STATUS.INFO);
//
//        String j=token();
//
//        JsonObject res = null;
//        Map<String,String> headers=new HashMap<>();
//
//        String jnew= j.replaceAll("^\"|\"$","");
//        System.out.println(jnew);
//        headers.put("Authorization","Bearer "+jnew);
//        Thread.sleep(400001);
//        try {
//            res = ApiClientConnect.createRequest("Get",url,"",headers);
//            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Executed Successfully", STATUS.PASS);
//        } catch (Exception e) {
//            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Did not Executed Successfully", STATUS.FAIL);
//        }
//
//        int status = res.get("status").getAsInt();
//
//        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
//
//        if (status == 403) {
//            GemTestReporter.addTestStep("Status Verification", "Expected Status :403", STATUS.PASS);
//
//            GemTestReporter.addTestStep("Final response", "No response", STATUS.PASS);
//
//
//        } else {
//
//            GemTestReporter.addTestStep("Final response", "No response", STATUS.FAIL);
//
//
//
//        }
//
//
//
//    }
//
//

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Gettoken_wrong_auth(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Get Token API when JMT in the headers is wrong", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("Gettoken");

        GemTestReporter.addTestStep("Url for Get Request", url, STATUS.INFO);

        String j=token();
        JsonObject res = null;
        Map<String,String> headers=new HashMap<>();

        String jnew= j.replaceAll("^\"|\"$","");
        System.out.println(jnew);
        headers.put("Authorization","Bearer "+jnew+"as");
        try {
            res = ApiClientConnect.createRequest("Get",url,"",headers);
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Did not Executed Successfully", STATUS.FAIL);
        }
        System.out.println(res);
        int status = res.get("status").getAsInt();

        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

        if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response After hitting the API ", String.valueOf(body), STATUS.INFO);

            //  System.out.println(body);
            //  System.out.println(res);
            String message = String.valueOf(body.get("message"));
            GemTestReporter.addTestStep("Final Message ", String.valueOf(message), STATUS.FAIL);

        } else {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);

            GemTestReporter.addTestStep("Final response", "No response", STATUS.PASS);



        }



    }










    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Gettoken_Empty_auth(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Get Token API when Auth header is not given", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("Gettoken");

        GemTestReporter.addTestStep("Url for Get Request", url, STATUS.INFO);

        String j=token();
        JsonObject res = null;
        Map<String,String> headers=new HashMap<>();

        String jnew= j.replaceAll("^\"|\"$","");
        System.out.println(jnew);
        headers.put("Authorization","Bearer "+jnew+"as");
        try {
            res = ApiClientConnect.getRequest(url);
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Did not Executed Successfully", STATUS.FAIL);
        }
        System.out.println(res);
        int status = res.get("status").getAsInt();

        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

        if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response After hitting the API ", String.valueOf(body), STATUS.INFO);

            //  System.out.println(body);
            //  System.out.println(res);
            String message = String.valueOf(body.get("message"));
            GemTestReporter.addTestStep("Final Message ", String.valueOf(message), STATUS.FAIL);

        } else {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);

            GemTestReporter.addTestStep("Final response", "No response", STATUS.PASS);



        }



    }








}
