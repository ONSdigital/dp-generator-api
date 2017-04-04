package dp.generatorapi;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class Utils {

    public static MultiValueMap getHeaders(final String format) {
        final MultiValueMap headers = new LinkedMultiValueMap();
        if (format.equals("csv")) {
            headers.add("Content-Disposition", "attachment; filename=data.csv");
            headers.add("Content-Type", "text/csv");
        } else {
            headers.add("Content-Disposition", "attachment; filename=data.xls");
            headers.add("Content-Type", "application/vnd.ms-excel");
        }
        return headers;
    }
}
