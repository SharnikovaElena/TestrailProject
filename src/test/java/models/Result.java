package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    int created_by;
    String created_on;
    String estimate;
    int id;
    int priority_id;
    int milestone_id;
    String refs;
    int section_id;
    int suite_id;
    int template_id;
    String title;
    int type_id;
    int updated_by;
    String updated_on;
}
