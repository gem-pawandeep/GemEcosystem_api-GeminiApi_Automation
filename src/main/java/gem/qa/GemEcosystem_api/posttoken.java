package gem.qa.GemEcosystem_api;

import com.gemini.apitest.ApiHealthCheckUtils;
import com.gemini.apitest.ProjectSampleJson;
import com.gemini.dataProvider.GemjarDataProvider;
import com.gemini.generic.GemjarAPIBase;
import com.gemini.generic.ProjectProperties;
import com.gemini.generic.GemjarAPIBase;
import com.gemini.apitest.ApiClientConnect;
import com.gemini.apitest.ProjectApiUrl;

import com.gemini.dataProvider.GemjarDataProvider;
import com.gemini.generic.GemjarGlobalVar;
import com.gemini.quartzReporting.GemTestReporter;
import com.gemini.quartzReporting.STATUS;
import com.google.gson.JsonObject;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class posttoken extends GemjarAPIBase {


    String TO;
    String token;
    String bt;


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


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void changetoken(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Change Token API when the JWT is not expired", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("posttoken");

        GemTestReporter.addTestStep("Url for Post  Request", url, STATUS.INFO);
        try {


            String j = token();

            JsonObject res = null;
            Map<String, String> headers = new HashMap<>();

            String jnew = j.replaceAll("^\"|\"$", "");
            ////System.out.println(j);

          //  ////System.out.println(jnew);
            headers.put("Authorization", "Bearer " + jnew);
            try {
                res = ApiClientConnect.postRequest(url, "", "json", headers);
                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
            } catch (Exception e) {
                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
            }
         //   ////System.out.println(res);
            int status = res.get("status").getAsInt();
            JsonObject bodye = res.get("responseBody").getAsJsonObject();
            JsonObject data = bodye.get("data").getAsJsonObject();
            String too = data.get("bridgeToken").getAsString();
            bt = too;


            //  ////System.out.println(ProjectProperties.getProperty("bridgeToken"));


            GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

            if (status == 200) {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.PASS);
                JsonObject body = res.get("responseBody").getAsJsonObject();
                GemTestReporter.addTestStep("Response After hitting the API ", String.valueOf(body), STATUS.INFO);

                //  ////System.out.println(body);
                //  ////System.out.println(res);
                String message = String.valueOf(body.get("message"));
                GemTestReporter.addTestStep("Final Message ", String.valueOf(message), STATUS.PASS);

            } else {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.PASS);

                GemTestReporter.addTestStep("Final response", "No response", STATUS.FAIL);


            }
            String gh = ProjectProperties.getProperty("bt");
        }  catch (Exception e)
        {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();


        }

    }


//////////////////////////////////////////


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void changetoken_header_not_given(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Change Token API when the Auth header is not given", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("posttoken");

        GemTestReporter.addTestStep("Url for Post  Request", url, STATUS.INFO);
        try {


            String j = token();

            JsonObject res = null;
            Map<String, String> headers = new HashMap<>();

            String jnew = j.replaceAll("^\"|\"$", "");
       //     ////System.out.println(j);

          //  ////System.out.println(jnew);
            headers.put("Authorization", "Bearer " + jnew);
            try {
                res = ApiClientConnect.postRequest(url, "", "json");
                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
            } catch (Exception e) {
                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
            }
          //  ////System.out.println(res);
            int status = res.get("status").getAsInt();
            // JsonObject bodye = res.get("responseBody").getAsJsonObject();
            // JsonObject data = bodye.get("data").getAsJsonObject();
            //  String too = data.get("bridgeToken").getAsString();
            //  bt=too;


            //  ////System.out.println(ProjectProperties.getProperty("bridgeToken"));


            GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

            if (status == 200) {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.FAIL);
                JsonObject body = res.get("responseBody").getAsJsonObject();
                GemTestReporter.addTestStep("Response After hitting the API ", String.valueOf(body), STATUS.INFO);

                //  ////System.out.println(body);
                //  ////System.out.println(res);
                String message = String.valueOf(body.get("message"));
                GemTestReporter.addTestStep("Final Message ", String.valueOf(message), STATUS.FAIL);

            } else {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);

                GemTestReporter.addTestStep("Final response", "No response", STATUS.PASS);


            }
            String gh = ProjectProperties.getProperty("bt");
        }  catch (Exception e)
        {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();


        }
    }


    //////////////////////////////////


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void changetoken_Wrong_auth(JsonObject inputData) {

        GemTestReporter.addTestStep("Test Case", "Test to check the Change Token API when the Authorisation is wrong", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("posttoken");

        GemTestReporter.addTestStep("Url for Post  Request", url, STATUS.INFO);

        try {


            String j = token();
            JsonObject res = null;
            Map<String, String> headers = new HashMap<>();

            String jnew = j.replaceAll("^\"|\"$", "");
            ////System.out.println(j);

            ////System.out.println(jnew);
            headers.put("Authorization", "Bearer " + jnew + "lolpas");
            try {
                res = ApiClientConnect.postRequest(url, "", "json");
                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
            } catch (Exception e) {
                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
            }
       //     ////System.out.println(res);
            int status = res.get("status").getAsInt();
            // JsonObject bodye = res.get("responseBody").getAsJsonObject();
            // JsonObject data = bodye.get("data").getAsJsonObject();
            //  String too = data.get("bridgeToken").getAsString();
            //  bt=too;


            //  ////System.out.println(ProjectProperties.getProperty("bridgeToken"));


            GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);

            if (status == 200) {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.FAIL);
                JsonObject body = res.get("responseBody").getAsJsonObject();
                GemTestReporter.addTestStep("Response After hitting the API ", String.valueOf(body), STATUS.INFO);

                //  ////System.out.println(body);
                //  ////System.out.println(res);
                String message = String.valueOf(body.get("message"));
                GemTestReporter.addTestStep("Final Message ", String.valueOf(message), STATUS.FAIL);

            } else {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);

                GemTestReporter.addTestStep("Final response", "No response", STATUS.PASS);


            }
        } catch (Exception e)
        {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();


        }

    }


    ////////////////////////////////////


    public void Gettoken2(JsonObject inputData) {


        String url = ProjectApiUrl.getUrl("Gettoken");




            String j = token();
            JsonObject res = null;
            Map<String, String> headers = new HashMap<>();

            String jnew = j.replaceAll("^\"|\"$", "");
            ////System.out.println(jnew);
            headers.put("Authorization", "Bearer " + jnew);
            try {
                res = ApiClientConnect.createRequest("Get", url, "", headers);
                // GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Executed Successfully", STATUS.PASS);
            } catch (Exception e) {
                //GemTestReporter.addTestStep(" Get Request Verification ", "Get Request Did not Executed Successfully", STATUS.FAIL);
            }
          //  ////System.out.println(res);
            int status = res.get("status").getAsInt();


            if (status == 200) {

                JsonObject body = res.get("responseBody").getAsJsonObject();

                JsonObject bodye = res.get("responseBody").getAsJsonObject();
                JsonObject data = bodye.get("data").getAsJsonObject();
                String too = data.get("bridgeToken").getAsString();
                bt = too;

            }
         }



    ///////////////////////

//    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
//    public void changetoken_jwtexpired(JsonObject inputData) throws InterruptedException {
//
//        GemTestReporter.addTestStep("Test Case", "Test to check the Change Token API when the JWT is expired", STATUS.INFO);
//
//        String url = ProjectApiUrl.getUrl("posttoken");
//
//        GemTestReporter.addTestStep("Url for Post  Request", url, STATUS.INFO);
//        try {
//
//
//            String j = token();
//            JsonObject res = null;
//            Map<String, String> headers = new HashMap<>();
//
//            String jnew = j.replaceAll("^\"|\"$", "");
//            ////System.out.println(jnew);
//            headers.put("Authorization", "Bearer " + jnew);
//            Thread.sleep(400001);
//            try {
//                res = ApiClientConnect.postRequest(url, "", "json", headers);
//                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
//            } catch (Exception e) {
//                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
//            }
//            ////System.out.println(res);
//            int status = res.get("status").getAsInt();
//
//            GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
//
//            if (status == 403) {
//                GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);
//
//                GemTestReporter.addTestStep("Final response", "No response", STATUS.PASS);
//
//
//            } else {
//                GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.FAIL);
//
//                GemTestReporter.addTestStep("Final response", "No response", STATUS.FAIL);
//
//
//            }
//        } catch (Exception e)
//        {
//            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
//            e.printStackTrace();
//
//
//        }
//
//
//    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Insertsuitess(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to insert the suite using Post API  ", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("pospo");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("psuite_sampleJson").getAsJsonObject();

        payload = (JsonObject) ApiHealthCheckUtils.result(payload);

        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        //headers

        //  ////System.out.println(bridgeToken);
        String username = (String) projectProperty.get("username");
        //String bridgeToken = (String) projectProperty.get("bridgeToken");
        Map<String, String> headers = new HashMap<>();
        // \ ////System.out.println(bridgeToken);
        headers.put("username", username);
        headers.put("bridgeToken", bt);
        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
      //  ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 201) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.PASS);

        } else if (status == 500) {
            GemTestReporter.addTestStep("Response Body", "Internal seerver error", STATUS.FAIL);

        } else {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            String me = body.get("message").toString();
            GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);


        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }

    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Insertsuitess_s_run_idpresent(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to insert the suite using Post API when S-run-id is already present", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("pospo");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("psuite1_sampleJson").getAsJsonObject();

        payload = (JsonObject) ApiHealthCheckUtils.result(payload);

        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        //headers
        String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);
        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
      //  ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 400) {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.PASS);
            String me = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message ", me, STATUS.INFO);

        } else if (status == 201) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else if (status == 500) {
            GemTestReporter.addTestStep("Response Body", "Internal seerver error", STATUS.FAIL);

        } else {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            String me = body.get("message").toString();
            GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);


        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }

    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Insertsuitess_s_run_id_not_presentinthepayload(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to insert the suite using Post API when S-run-id is not given in the payload", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("pospo");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("psuite2_sampleJson").getAsJsonObject();
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        //headers
        String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);
        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
      //  ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 201) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.PASS);

        } else if (status == 500) {
            GemTestReporter.addTestStep("Response Body", "Internal seerver error", STATUS.FAIL);

        } else {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            String me = body.get("message").toString();
            GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);


        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }
    }


    ////////////////////////////////////////////////////////


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Insertsuitess_wrong_auth(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to insert the suite using Post API when Wrong Auth is given", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("pospo");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("psuite2_sampleJson").getAsJsonObject();
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        //headers
        //  String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt + "sssss");
        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
      //  ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 201) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else if (status == 500) {
            GemTestReporter.addTestStep("Response Body", "Internal seerver error", STATUS.FAIL);

        } else if (status == 403) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);

            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.PASS);


        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }
    }


////////////////////////////////////////////////////////////////////////////


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Insertsuitess_auth_not_given(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to insert the suite using Post API when Wrong Auth is given", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("pospo");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("psuite2_sampleJson").getAsJsonObject();
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);
        //headers
        //  String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt + "sssss");
        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json");
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
        ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 201) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else if (status == 500) {
            GemTestReporter.addTestStep("Response Body", "Internal seerver error", STATUS.FAIL);

        } else if (status == 403) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);

            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.PASS);


        } else {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.FAIL);

            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.FAIL);

        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }
    }


//////////////////////////////////////////////////////////////////////


///////////////////////////Put Suite//////////////////////////////////////

    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Updatesuite(JsonObject inputData) {
        try {
            Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API  ", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("putu");
        GemTestReporter.addTestStep("Url for Put Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("put_1_sampleJson").getAsJsonObject();

            if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
            {
                payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");
            }

        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        //headers
        //  String bridgeToken= (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);
        JsonObject res = null;

        try {
            res = ApiClientConnect.putRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Did not Executed Successfully", STATUS.FAIL);
        }
        ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.PASS);

        } else {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            String me = body.get("message").toString();
            GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);


        }}
        catch (Exception e)
        {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();


        }

    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Updatesuite_srunidnotpresent(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API and the s-run_id is not present in the database ", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("putu");

        GemTestReporter.addTestStep("Url for Put Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("put_2_sampleJson").getAsJsonObject();


           if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
           {
               payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c14");
           }



        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        //headers
        //   String bridgeToken= (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);

        JsonObject res = null;

        try {
            res = ApiClientConnect.putRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Did not Executed Successfully", STATUS.FAIL);
        }
        ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 400) {

            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.PASS);


            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.PASS);
        } else if (status == 200) {


            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else {

            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.FAIL);


            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);

        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }

    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Updatesuite_wrong_auth(JsonObject inputData) {
        try {
            Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API and Auth is not correct ", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("putu");

        GemTestReporter.addTestStep("Url for Put Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("put_2_sampleJson").getAsJsonObject();

            if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
            {
                payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");
            }


        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        //headers
        //   String bridgeToken= (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt+"er");

        JsonObject res = null;

        try {
            res = ApiClientConnect.putRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Did not Executed Successfully", STATUS.FAIL);
        }
        ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 403) {

            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);


            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.PASS);
        } else if (status == 200) {


            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else {

            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.FAIL);


            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);

        }}
        catch (Exception e)
        {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();


        }

    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Updatesuite_auth_not_given(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to Update the suite using Put API and Auth header is not given ", STATUS.INFO);

        String url = ProjectApiUrl.getUrl("putu");

        GemTestReporter.addTestStep("Url for Put Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("put_2_sampleJson").getAsJsonObject();


           if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
           {
               payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");
           }

        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        //headers
        //   String bridgeToken= (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);

        JsonObject res = null;

        try {
            res = ApiClientConnect.putRequest(url, String.valueOf(payload), "json");
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Did not Executed Successfully", STATUS.FAIL);
        }
        ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 400) {

            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.FAIL);


            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);
        } else if (status == 200) {


            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else if (status == 403) {


            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);


            GemTestReporter.addTestStep("Final Body", "Null", STATUS.PASS);

        } else {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.FAIL);

        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }

    }


    ///////////////////////////////////////Post_exe//////////////////////////////


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Create_new_record_for_test_case(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "To create a new testcase in the database using Post function  ", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("pospos");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("ptest_sampleJson").getAsJsonObject();
       if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
       {
        payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");
       }
        payload = (JsonObject) ApiHealthCheckUtils.result(payload);
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        JsonObject res = null;

        //headers
        String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);

        GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);
        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
        ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 201) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.PASS);

        } else {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            String me = body.get("message").toString();
            GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);


        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }



    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Create_new_record_for_test_case_srunidnotexists(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "To create a new testcase in the database using Post function S_run_id  Does not Exist in the database ", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("pospos");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("ptest1_sampleJson").getAsJsonObject();

           if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
           {
               payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");
           }
        payload = (JsonObject) ApiHealthCheckUtils.result(payload);
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        //headers
        String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);

        GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
        // ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 400) {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.PASS);
            //  String me=body.get("message").toString();
            //  GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.PASS);
        } else if (status == 201) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            //  String me=body.get("message").toString();
            //  GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);


        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }


    }

    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Create_new_record_for_test_case_srunidnotgiven(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "To create a new testcase in the database using Post function S_run_id  is not given by the user ", STATUS.INFO);
        String url = ProjectApiUrl.getUrl("pospos");
        GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

        JsonObject payload = ProjectSampleJson.getSampleData("ptest2_sampleJson").getAsJsonObject();

           if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
           {
               payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");
           }

        payload = (JsonObject) ApiHealthCheckUtils.result(payload);
        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        //headers
        String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);

        GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
        }
        // ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 400) {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.PASS);
            //  String me=body.get("message").toString();
            //  GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.PASS);
        } else if (status == 201) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else {
            JsonObject body = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
            //  String me=body.get("message").toString();
            //  GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
            String l = body.get("operation").toString();
            GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);


        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }


    }

    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)

    public void Create_new_record_for_test_case_tc_run_id_not_given(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

           GemTestReporter.addTestStep("Test Case", "To create a new testcase in the database using Post function when user doesnot give tc run id in the payload  ", STATUS.INFO);
           String url = ProjectApiUrl.getUrl("pospos");
           GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

           JsonObject payload = ProjectSampleJson.getSampleData("ptest3_sampleJson").getAsJsonObject();

           if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
           {
               payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");
           }
           payload = (JsonObject) ApiHealthCheckUtils.result(payload);
           GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

           JsonObject res = null;

           //headers
           String bridgeToken = (String) projectProperty.get("bridgeToken");
           String username = (String) projectProperty.get("username");
           Map<String, String> headers = new HashMap<>();
           headers.put("username", username);
           headers.put("bridgeToken", bt);

           GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);
           try {
               res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json", headers);
               GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
           } catch (Exception e) {
               GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
           }
           ////System.out.println(res);

           int status = res.get("status").getAsInt();
           GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
           if (status == 201) {
               GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.PASS);
               JsonObject body = res.get("responseBody").getAsJsonObject();
               GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
               String message = body.get("message").toString();
               GemTestReporter.addTestStep("Final Message", message, STATUS.PASS);

           } else {
               JsonObject body = res.get("responseError").getAsJsonObject();
               GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
               GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
               String me = body.get("message").toString();
               GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
               String l = body.get("operation").toString();
               GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);


           }
       }
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }

    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)

    public void Create_new_record_for_test_case_Wrong_auth(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

           GemTestReporter.addTestStep("Test Case", "To create a new testcase in the database using Post function when user gives the wrong auth  ", STATUS.INFO);
           String url = ProjectApiUrl.getUrl("pospos");
           GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

           JsonObject payload = ProjectSampleJson.getSampleData("ptest3_sampleJson").getAsJsonObject();
           if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
           {
               payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");
           }
           payload = (JsonObject) ApiHealthCheckUtils.result(payload);
           GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

           JsonObject res = null;

           //headers
           String bridgeToken = (String) projectProperty.get("bridgeToken");
           String username = (String) projectProperty.get("username");
           Map<String, String> headers = new HashMap<>();
           headers.put("username", username);
           headers.put("bridgeToken", bt + "kkll");

           GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);
           try {
               res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json", headers);
               GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
           } catch (Exception e) {
               GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
           }
           ////System.out.println(res);

           int status = res.get("status").getAsInt();
           GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
           if (status == 201) {
               GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
               JsonObject body = res.get("responseBody").getAsJsonObject();
               GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
               String message = body.get("message").toString();
               GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

           } else {
               JsonObject body = res.get("responseError").getAsJsonObject();
               GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
               GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);
               String me = body.get("message").toString();
               GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
               String l = body.get("operation").toString();
               GemTestReporter.addTestStep("Final Message", l, STATUS.PASS);


           }
       }
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }

    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)

    public void Create_new_record_for_test_case_auth_not_given(JsonObject inputData) {
        try {
            Gettoken2(new JsonObject());

            GemTestReporter.addTestStep("Test Case", "To create a new testcase in the database using Post function when user Does not gives auth  ", STATUS.INFO);
            String url = ProjectApiUrl.getUrl("pospos");
            GemTestReporter.addTestStep("Url for Post Request", url, STATUS.INFO);

            JsonObject payload = ProjectSampleJson.getSampleData("ptest3_sampleJson").getAsJsonObject();

            if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
            {
                payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");
            }
            payload = (JsonObject) ApiHealthCheckUtils.result(payload);
            GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

            JsonObject res = null;

            //headers
            String bridgeToken = (String) projectProperty.get("bridgeToken");
            String username = (String) projectProperty.get("username");
            Map<String, String> headers = new HashMap<>();
            headers.put("username", username);
            headers.put("bridgeToken", bt + "kkll");

            GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);
            try {
                res = ApiClientConnect.postRequest(url, String.valueOf(payload), "json");
                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Executed Successfully", STATUS.PASS);
            } catch (Exception e) {
                GemTestReporter.addTestStep(" Post Request Verification ", "Post Request Did not Executed Successfully", STATUS.FAIL);
            }
            ////System.out.println(res);

            int status = res.get("status").getAsInt();
            GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
            if (status == 201) {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 201", STATUS.FAIL);
                JsonObject body = res.get("responseBody").getAsJsonObject();
                GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
                String message = body.get("message").toString();
                GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

            } else if (status == 403) {
                JsonObject body = res.get("responseError").getAsJsonObject();
                GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);
                String me = body.get("message").toString();
                GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
                String l = body.get("operation").toString();
                GemTestReporter.addTestStep("Final Message", l, STATUS.PASS);


            } else {
                JsonObject body = res.get("responseError").getAsJsonObject();
                GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.FAIL);
                String me = body.get("message").toString();
                GemTestReporter.addTestStep("Reason of rejection", me, STATUS.INFO);
                String l = body.get("operation").toString();
                GemTestReporter.addTestStep("Final Message", l, STATUS.FAIL);
            }
        }
        catch (Exception e)
        {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();


        }
    }


    //////////////////////////////////////Put_exe/////////////////


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Update_testcase_data(JsonObject inputData) {
        try {
            Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "Test to check the Put function ", STATUS.INFO);


        String url = ProjectApiUrl.getUrl("putexe");


        GemTestReporter.addTestStep("Url for Put Request", url, STATUS.INFO);


        //headers
        String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);

        GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);

        //payload
        JsonObject payload = ProjectSampleJson.getSampleData("puter1_sampleJson").getAsJsonObject();

            if (GemjarGlobalVar.environment.equalsIgnoreCase("prod"))
            {
                payload.addProperty("tc_run_id","sample_testcase2_58913eb0-be6f-42c4-ab83-916df5782db5");
                payload.addProperty("s_run_id","b2f779e7-a4f2-44d8-a557-b3426ea520c1");

            }

        payload = (JsonObject) ApiHealthCheckUtils.result(payload);

        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.putRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Did not Executed Successfully", STATUS.FAIL);
        }
        ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.PASS);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.PASS);

        } else {
            JsonObject bo = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);


        }}
        catch (Exception e)
        {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();


        }

    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Update_testcase_data_srunidnotgiven(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "To update data of particular testcase using tc_run_id when s run id is not given by the user ", STATUS.INFO);


        String url = ProjectApiUrl.getUrl("putexe");


        GemTestReporter.addTestStep("Url for Put Request", url, STATUS.INFO);

        //headers
        String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);

        GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);

        //payload
        JsonObject payload = ProjectSampleJson.getSampleData("puter2_sampleJson").getAsJsonObject();
        payload = (JsonObject) ApiHealthCheckUtils.result(payload);


        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.putRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Did not Executed Successfully", STATUS.FAIL);
        }
        ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 400) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.PASS);

            JsonObject bo = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.PASS);

        } else if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else {
            JsonObject bo = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);


        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }

    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Update_testcase_data_tcrunidnotgiven(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

        GemTestReporter.addTestStep("Test Case", "To update data of particular testcase using tc_run_id when Tc run id is not given by the user ", STATUS.INFO);


        String url = ProjectApiUrl.getUrl("putexe");


        GemTestReporter.addTestStep("Url for Put Request", url, STATUS.INFO);


        //headers
        String bridgeToken = (String) projectProperty.get("bridgeToken");
        String username = (String) projectProperty.get("username");
        Map<String, String> headers = new HashMap<>();
        headers.put("username", username);
        headers.put("bridgeToken", bt);

        GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);

        //payload
        JsonObject payload = ProjectSampleJson.getSampleData("puter3_sampleJson").getAsJsonObject();
        payload = (JsonObject) ApiHealthCheckUtils.result(payload);


        GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

        JsonObject res = null;

        try {
            res = ApiClientConnect.putRequest(url, String.valueOf(payload), "json", headers);
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Executed Successfully", STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Did not Executed Successfully", STATUS.FAIL);
        }
       // ////System.out.println(res);

        int status = res.get("status").getAsInt();
        GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
        if (status == 400) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.PASS);

            JsonObject bo = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.PASS);

        } else if (status == 200) {
            GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.FAIL);
            JsonObject body = res.get("responseBody").getAsJsonObject();
            GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
            String message = body.get("message").toString();
            GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

        } else {
            JsonObject bo = res.get("responseError").getAsJsonObject();
            GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);


        }}
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }
    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Update_testcase_data_wrong_auth(JsonObject inputData) {
       try {
           Gettoken2(new JsonObject());

           GemTestReporter.addTestStep("Test Case", "To update data of particular testcase using tc_run_id when user goves the wrong authorization ", STATUS.INFO);


           String url = ProjectApiUrl.getUrl("putexe");


           GemTestReporter.addTestStep("Url for Put Request", url, STATUS.INFO);


           //headers
           String bridgeToken = (String) projectProperty.get("bridgeToken");
           String username = (String) projectProperty.get("username");
           Map<String, String> headers = new HashMap<>();
           headers.put("username", username);
           headers.put("bridgeToken", bt + "ddddd");

           GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);

           //payload
           JsonObject payload = ProjectSampleJson.getSampleData("puter3_sampleJson").getAsJsonObject();
           payload = (JsonObject) ApiHealthCheckUtils.result(payload);


           GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

           JsonObject res = null;

           try {
               res = ApiClientConnect.putRequest(url, String.valueOf(payload), "json", headers);
               GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Executed Successfully", STATUS.PASS);
           } catch (Exception e) {
               GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Did not Executed Successfully", STATUS.FAIL);
           }
           ////System.out.println(res);

           int status = res.get("status").getAsInt();
           GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
           if (status == 400) {
               GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.FAIL);

               JsonObject bo = res.get("responseError").getAsJsonObject();
               GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);

           } else if (status == 200) {
               GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.FAIL);
               JsonObject body = res.get("responseBody").getAsJsonObject();
               GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
               String message = body.get("message").toString();
               GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

           } else if (status == 403) {
               GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);

               JsonObject bo = res.get("responseError").getAsJsonObject();
               GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.PASS);


           }
       }
       catch (Exception e)
       {
           GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
           e.printStackTrace();


       }
    }


    @Test(dataProvider = "GemjarDataProvider", dataProviderClass = GemjarDataProvider.class)
    public void Update_testcase_data_auth_not_given(JsonObject inputData) {
        try {
            Gettoken2(new JsonObject());

            GemTestReporter.addTestStep("Test Case", "To update data of particular testcase using tc_run_id when user does not gives the authorization ", STATUS.INFO);


            String url = ProjectApiUrl.getUrl("putexe");


            GemTestReporter.addTestStep("Url for Put Request", url, STATUS.INFO);


            //headers
            String bridgeToken = (String) projectProperty.get("bridgeToken");
            String username = (String) projectProperty.get("username");
            Map<String, String> headers = new HashMap<>();
            headers.put("username", username);
            headers.put("bridgeToken", bt + "ddddd");

            GemTestReporter.addTestStep("Headers", String.valueOf(headers), STATUS.INFO);

            //payload
            JsonObject payload = ProjectSampleJson.getSampleData("puter3_sampleJson").getAsJsonObject();
            payload = (JsonObject) ApiHealthCheckUtils.result(payload);


            GemTestReporter.addTestStep("Payload ", String.valueOf(payload), STATUS.INFO);

            JsonObject res = null;

            try {
                res = ApiClientConnect.putRequest(url, String.valueOf(payload), "json");
                GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Executed Successfully", STATUS.PASS);
            } catch (Exception e) {
                GemTestReporter.addTestStep(" Put Request Verification ", "Put Request Did not Executed Successfully", STATUS.FAIL);
            }
            ////System.out.println(res);

            int status = res.get("status").getAsInt();
            GemTestReporter.addTestStep("Status ", String.valueOf(status), STATUS.INFO);
            if (status == 400) {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 400", STATUS.FAIL);

                JsonObject bo = res.get("responseError").getAsJsonObject();
                GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);

            } else if (status == 200) {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 200", STATUS.FAIL);
                JsonObject body = res.get("responseBody").getAsJsonObject();
                GemTestReporter.addTestStep("Response Body", String.valueOf(body), STATUS.INFO);
                String message = body.get("message").toString();
                GemTestReporter.addTestStep("Final Message", message, STATUS.FAIL);

            } else if (status == 403) {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.PASS);

                JsonObject bo = res.get("responseError").getAsJsonObject();
                GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.PASS);


            } else {
                GemTestReporter.addTestStep("Status Verification", "Expected Status : 403", STATUS.FAIL);

                JsonObject bo = res.get("responseError").getAsJsonObject();
                GemTestReporter.addTestStep("Final response", String.valueOf(bo), STATUS.FAIL);
            }
        }
        catch (Exception e)
        {
            GemTestReporter.addTestStep("Final token", "Some error occured while fetching token", STATUS.FAIL);
            e.printStackTrace();


        }
    }


/////////////////////////////////////////


}


/////////////////////////////////////////////////////////////////





