package utils;

import java.util.List;


public class ListUtils {

    public static <T> boolean isEmptyList(List<T> list) {
        return list == null || list.isEmpty();
    }
}
