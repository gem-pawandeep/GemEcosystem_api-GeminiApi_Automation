package gem.qa.GemEcosystem_api;
import com.gemini.apitest.ApiHealthCheckUtils;
import com.gemini.dataProvider.GemjarDataProvider;
import com.gemini.generic.GemjarAPIBase;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.gemini.apitest.ApiClientConnect;
import com.gemini.apitest.ProjectApiUrl;
import com.gemini.apitest.ProjectSampleJson;

import com.gemini.quartzReporting.GemTestReporter;
import com.gemini.quartzReporting.STATUS;
import com.google.gson.JsonObject;

import io.cucumber.java.ca.Quan;
import jdk.jshell.Snippet;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import org.testng.annotations.Test;
public class GetUser extends GemjarAPIBase {




    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Validate_Username(JsonObject inputData) {
        GemTestReporter.addTestStep("Test Case", "Test to check the Get API ", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("Gett");

        GemTestReporter.addTestStep("Url for Get Request", url, STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.getRequest(url);
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Did not Executed Successfully", STATUS.FAIL);
        }

        int status = res.get("status").getAsInt();

        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

        if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response After hitting the API ", String.valueOf(body), STATUS.INFO);

            //  System.out.println(body);
            //  System.out.println(res);
            String message = String.valueOf(res.get("responseMessage"));
            GemTestReporter.addTestStep("Final Message ", String.valueOf(message), STATUS.PASS);

        } else {
            JsonObject bo=res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);



        }



    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Validate_Username_username_doesnotexists(JsonObject inputData) {
        GemTestReporter.addTestStep("Test Case", "Test to check the Get API when the username doesnot exists", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("Gett2");

        GemTestReporter.addTestStep("Url for Get  Request", url, STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.getRequest(url);
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Did not Executed Successfully", STATUS.FAIL);
        }

        int status = res.get("status").getAsInt();

        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

        if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response After hitting the API ", String.valueOf(body), STATUS.INFO);

            //  System.out.println(body);
            //System.out.println(res);
            String message = String.valueOf(res.get("responseMessage"));
            GemTestReporter.addTestStep("Final Message ", String.valueOf(message), STATUS.PASS);

        } else {
            JsonObject bo=res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);



        }



    }



}


