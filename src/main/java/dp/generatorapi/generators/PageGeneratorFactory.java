package dp.generatorapi.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import dp.generatorapi.pages.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PageGeneratorFactory {
    public GeneratorType get(String content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Page page = mapper.readValue(content, Page.class);
            switch (page.getType()) {
                case "chart":
                    return new ChartGenerator();
                case "timeseries":
                    return new TimeseriesGenerator();
                default:
                    return new NullGenerator();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
