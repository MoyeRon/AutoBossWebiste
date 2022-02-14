package filter;

import lombok.Data;

import java.util.List;

@Data
public class FilterEntity {
    private String jobName;
    private List<Integer> ageArea;
    private String sex;
    private String recentlyExchange;
    private String switchRate;
    private String activity;
    private String recentlyLook;
    private List<String> school;
    private List<String> majors;
    private List<String> expRequired;
    private List<String> degrees;
    private String salaryRequired;
    private List<String> wanted;
}
