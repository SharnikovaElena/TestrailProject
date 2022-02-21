package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCaseForApi {
    int id;
    int section_id;
    String title;
    int template_id;
    int type_id;
    int priority_id;
    String estimate;
    String refs;
}
