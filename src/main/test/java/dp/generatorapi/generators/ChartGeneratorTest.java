package dp.generatorapi.generators;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ChartGeneratorTest {

    private final ChartGenerator generator = new ChartGenerator();

    private String testdata;

    @Before
    public void setup() {
        try (InputStream file = ChartGeneratorTest.class.getResourceAsStream("chart.json")) {
            testdata = IOUtils.toString(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void chartConvertToCsv() {
        final Filter filter = new Filter();
        filter.setFormat("csv");
        generator.addPage(testdata, filter);
        final String results = generator.convertPage(filter);
        final String[] rows = results.split("\n");
        assertThat(rows).hasSize(19);

        assertThat(results)
                .contains("Figure 4: National identity, England and Wales, 2011")
                .contains("England and Wales")
                .contains("East of England")
                .contains("South West");
    }

    @Test
    public void chartConvertToXls() {
        final Filter filter = new Filter();
        filter.setFormat("xls");
        generator.addPage(testdata, filter);
        final String results = generator.convertPage(filter);

        assertThat(results)
                .contains("Figure 4: National identity, England and Wales, 2011")
                .contains("England and Wales")
                .contains("East of England")
                .contains("South West");
    }
}
