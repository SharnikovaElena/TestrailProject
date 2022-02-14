package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseStatusPositive {
    boolean status;
    Result result;
}
