package gem.qa.GemEcosystem_api;

import com.gemini.generic.QuanticAPIBase;
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

import io.cucumber.java.ca.Quan;
import jdk.jshell.Snippet;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import org.testng.annotations.Test;

public class get_suite extends QuanticAPIBase {
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Get_data_of_suites(JsonObject inputData) {
        GemTestReporter.addTestStep("Test Case", "Test to check the Get Suite API ", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("Gettt");


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
            String message = String.valueOf(body.get("message"));
            GemTestReporter.addTestStep("Final Message ", String.valueOf(message), STATUS.PASS);

        } else {
            JsonObject bo = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);


        }


    }


    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void Get_data_of_suite_srunidnotpresent(JsonObject inputData) {
        GemTestReporter.addTestStep("Test Case", "Test to check the Get Suite API when S-run-id not present ", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("Gettte");

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
        if (status == 400) {
            JsonObject bo = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.PASS);
            String Message = bo.get("message").getAsString();
            GemTestReporter.addTestStep("Final Message", String.valueOf(Message), STATUS.PASS);
        } else if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response After hitting the API ", String.valueOf(body), STATUS.INFO);



        } else {
            JsonObject bo = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);


        }


    }


}
