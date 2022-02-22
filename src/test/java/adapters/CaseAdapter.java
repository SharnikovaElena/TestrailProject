package adapters;

import models.ResponseStatusNegative;
import models.ResponseStatusPositive;
import models.TestCaseForApi;

public class CaseAdapter extends BaseCaseAdapter {


    public ResponseStatusNegative postCreateTestCaseNegative(TestCaseForApi testCaseForApi, int statusCode) {
        String response = super.post(gson.toJson(testCaseForApi, TestCaseForApi.class), statusCode, "add_case/3");
        return gson.fromJson(response, ResponseStatusNegative.class);
    }

    public ResponseStatusPositive postCreateTestCasePositive(TestCaseForApi testCaseForApi, int statusCode) {
        String response = super.post(gson.toJson(testCaseForApi, TestCaseForApi.class), statusCode, "add_case/3");
        return gson.fromJson(response, ResponseStatusPositive.class);
    }

    public int postDeleteTestCaseByCorrectCode(int statusCode, int case_id) {
        return super.delete(statusCode, case_id);
    }

    public ResponseStatusPositive postUpdateTestCasePositive(TestCaseForApi testCaseForApi, int statusCode, int case_id) {
        String response = super.update(gson.toJson(testCaseForApi, TestCaseForApi.class), statusCode, case_id);
        return gson.fromJson(response, ResponseStatusPositive.class);
    }

    public ResponseStatusNegative postUpdateTestCaseNegative(TestCaseForApi testCaseForApi, int statusCode, int case_id) {
        String response = super.update(gson.toJson(testCaseForApi, TestCaseForApi.class), statusCode, case_id);
        return gson.fromJson(response, ResponseStatusNegative.class);
    }


    public ResponseStatusNegative getTestCaseNegative(int statusCode, int case_id) {
        return gson.fromJson(get(statusCode, case_id), ResponseStatusNegative.class);
    }


    public ResponseStatusPositive getTestCasePositive(int statusCode, int case_id) {
        return gson.fromJson(get(statusCode, case_id), ResponseStatusPositive.class);
    }
}


