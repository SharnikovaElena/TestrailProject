package adapters;

import models.ResponseStatusNegative;
import models.ResponseStatusPositive;
import models.TestCase;

public class CaseAdapter extends BaseAdapter{

        public ResponseStatusNegative postCreateTestCaseNegative(TestCase testCase, int statusCode) {
        String response = super.post(gson.toJson(testCase, TestCase.class), statusCode, "add_case/2767");
        return gson.fromJson(response, ResponseStatusNegative.class);
    }

    public ResponseStatusPositive postCreateTestCasePositive(TestCase testCase, int statusCode) {
        String response = super.post(gson.toJson(testCase, TestCase.class), statusCode, "add_case/2767");
        return gson.fromJson(response, ResponseStatusPositive.class);
    }
}


//
//    public ResponseStatusNegative getProjectNegative (int statusCode, String codeProject){
//        return gson.fromJson(get(statusCode,"project/" + codeProject), ResponseStatusNegative.class);
//    }
//
//    public ResponseStatusPositive getProjectPositive (int statusCode, String codeProject){
//        return gson.fromJson(get(statusCode,"project/" + codeProject), ResponseStatusPositive.class);
//    }


