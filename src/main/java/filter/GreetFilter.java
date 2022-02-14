package filter;


import constant.SchoolConstants;
import org.openqa.selenium.WebElement;
import utils.ElementUtils;

import java.util.List;

public class GreetFilter {
    public static boolean isAllowedSchool(WebElement element) {
        List<String> schoolInfos = ElementUtils.getSchoolNames(element);
        return schoolInfos.stream().anyMatch(SchoolConstants.normalSchoolNames::contains);
    }
}
