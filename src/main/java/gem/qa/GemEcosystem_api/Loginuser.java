package gem.qa.GemEcosystem_api;
import com.gemini.generic.ProjectProperties;
import com.gemini.generic.QuanticAPIBase;
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


public class Loginuser extends QuanticAPIBase {




    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Loginuser(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Login User Api  ", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("Login");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("Login_sampleJson").getAsJsonObject();

        payload = (JsonObject) ApiHealthCheckUtils.result(payload);

        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json");

            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
        System.out.println(res);
        JsonObject Boddy= (JsonObject) res.get("responseBody");
        System.out.println(Boddy);
        JsonObject to= (JsonObject) Boddy.get("data");
        String tokenss= String.valueOf(to.get("token"));
        System.out.println(tokenss);

        ProjectProperties.setProperty("JMT",tokenss);
        int status = res.get("status").getAsInt();

        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

        if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.PASS);

            JsonObject body = res.get("responseBody").getAsJsonObject();

            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);

            JsonObject token=body.get("data").getAsJsonObject();
            String Token=token.get("token").getAsString();
            GemTestReporter.addTestStep("Bridge Token", String.valueOf(token), STATUS.INFO);

            String message = body.get("message").toString();

            GemTestReporter.addTestStep("Final Message", message, STATUS.PASS);

        } else {
            JsonObject bo=res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);



        }

    }
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Loginuser_wrongcredentials(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Login User Api by wrong credentials ", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("Login");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("Login2_sampleJson").getAsJsonObject();

        payload = (JsonObject) ApiHealthCheckUtils.result(payload);

        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json");

            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
        // System.out.println(res);

        int status = res.get("status").getAsInt();

        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

        if (status==400){
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.PASS);
            JsonObject bo=res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.PASS);



        }
        else {
            JsonObject bo=res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);

        }

    }


    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Loginuser_fieldsnotpresent(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Login User Api when some fields are not present ", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("Login");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("Login3_sampleJson").getAsJsonObject();

        payload = (JsonObject) ApiHealthCheckUtils.result(payload);

        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json");

            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
        // System.out.println(res);

        int status = res.get("status").getAsInt();

        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

        if (status==500){
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 500", STATUS.PASS);
            JsonObject bo=res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.PASS);



        }
        else if(status==200)
        {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 500", STATUS.FAIL);

            JsonObject body = res.get("responseBody").getAsJsonObject();

            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);

            JsonObject token=body.get("data").getAsJsonObject();
            String Token=token.get("token").getAsString();
            GemTestReporter.addTestStep("Bridge Token", String.valueOf(token), STATUS.INFO);

            String message = body.get("message").toString();

            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);
        }
        else {
            JsonObject bo=res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);

        }

    }





    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Loginuser_empty_body(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Login User Api when body is empty ", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("Login");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("Postgemini4_sampleJson").getAsJsonObject();

        payload = (JsonObject) ApiHealthCheckUtils.result(payload);

        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json");

            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
        // System.out.println(res);

        int status = res.get("status").getAsInt();

        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

        if (status==500){
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 500", STATUS.PASS);
            JsonObject bo=res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.PASS);



        }
        else if(status==200)
        {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 500", STATUS.FAIL);

            JsonObject body = res.get("responseBody").getAsJsonObject();

            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);

            JsonObject token=body.get("data").getAsJsonObject();
            String Token=token.get("token").getAsString();
            GemTestReporter.addTestStep("Bridge Token", String.valueOf(token), STATUS.INFO);

            String message = body.get("message").toString();

            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);
        }
        else {
            JsonObject bo=res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);

        }

    }







}
