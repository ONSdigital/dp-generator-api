package dp.generater.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import dp.generater.pages.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PageGeneratorFactory {

    private final Map<String, GeneratorType> generators;

    private final NullGenerator nullGenerator;

    public PageGeneratorFactory() {
        this.generators = new HashMap<>();
        this.nullGenerator = new NullGenerator();
        generators.put("chart", new ChartGenerator());
        generators.put("timeseries", new TimerseriesGenerator());
    }

    public GeneratorType get(String content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Page page = mapper.readValue(content, Page.class);
            return generators.getOrDefault(page.getType(), nullGenerator);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
