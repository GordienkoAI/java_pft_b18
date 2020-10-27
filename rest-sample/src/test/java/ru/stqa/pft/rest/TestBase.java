package ru.stqa.pft.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import jdk.nashorn.internal.parser.JSONParser;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;


public class TestBase {


    @BeforeClass
    public void initTest(){
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
    }

    public boolean isIssueOpen(Object issueId)  {

        String json = RestAssured.given().pathParam("issue_id", issueId)
                .get("https://bugify.stqa.ru/api/issues/{issue_id}.json").asString();
        JsonObject parsed = JsonParser.parseString(json).getAsJsonObject();
        JsonObject issues = parsed.getAsJsonArray("issues").get(0).getAsJsonObject();
        String state = issues.get("state_name").getAsString();
        return !state.equals("Resolved");
    }


    public void skipIfNotFixed(int issueId){
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
