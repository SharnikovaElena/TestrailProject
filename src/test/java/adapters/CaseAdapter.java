package adapters;

import models.ResponseStatusNegative;
import models.ResponseStatusPositive;
import models.TestCaseForApi;

public class CaseAdapter extends BaseAdapter{

        public ResponseStatusNegative postCreateTestCaseNegative(TestCaseForApi testCaseForApi, int statusCode) {
        String response = super.post(gson.toJson(testCaseForApi, TestCaseForApi.class), statusCode, "add_case/2767");
        return gson.fromJson(response, ResponseStatusNegative.class);
    }

    public ResponseStatusPositive postCreateTestCasePositive(TestCaseForApi testCaseForApi, int statusCode) {
        String response = super.post(gson.toJson(testCaseForApi, TestCaseForApi.class), statusCode, "add_case/2767");
        return gson.fromJson(response, ResponseStatusPositive.class);
    }
}


