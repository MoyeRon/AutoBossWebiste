package filter;

import lombok.Data;

import java.util.List;

@Data
public class SchoolEntity {
    private String schoolName;
    private List<String> majors;
}
