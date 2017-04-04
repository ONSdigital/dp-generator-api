package dp.generatorapi.generators;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeseriesGeneratorTest {

    private final TimeseriesGenerator generator = new TimeseriesGenerator();

    private String testdata;

    @Before
    public void setup() {
        try (InputStream file = TimeseriesGeneratorTest.class.getResourceAsStream("timeseries.json")) {
            testdata = IOUtils.toString(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void timeseriesConvertedToCsv() {
        final Filter filter = new Filter();
        filter.setFrequency("years");
        filter.setFormat("csv");
        generator.addPage(testdata, filter);
        final String results = generator.convertPage(filter);
        assertThat(results)
                .isNotBlank()
                .contains("OS visits to EU:All visits Thousands-NSA")
                .contains("21 August 2015")
                .contains("GMAA");
    }

    @Test
    public void timeseriesConvertedToxls() {
        final Filter filter = new Filter();
        filter.setFrequency("years");
        filter.setFormat("xls");
        generator.addPage(testdata, filter);
        final String results = generator.convertPage(filter);
        assertThat(results)
                .isNotBlank()
                .contains("OS visits to EU:All visits Thousands-NSA")
                .contains("21 August 2015")
                .contains("GMAA");
    }

    @Test
    public void timeseriesFilterOnYear() {
        final Filter filter = new Filter();
        filter.setFrequency("years");
        filter.setFormat("csv");
        filter.setFromYear("2011");
        filter.setToYear("2013");
        generator.addPage(testdata, filter);
        final String results = generator.convertPage(filter);
        final String[] rows = results.split("\n");
        assertThat(rows).hasSize(11);
        assertThat(results)
                .doesNotContain("2010")
                .doesNotContain("2014");
    }

    @Test
    public void timeseriesFilterOnQuarters() {
        final Filter filter = new Filter();
        filter.setFrequency("quarters");
        filter.setFormat("csv");
        filter.setFromYear("2011");
        filter.setToYear("2013");
        filter.setFromQuarter("Q2");
        filter.setToQuarter("Q3");
        generator.addPage(testdata, filter);
        final String results = generator.convertPage(filter);
        final String[] rows = results.split("\n");
        assertThat(rows).hasSize(18);
        assertThat(results)
                .doesNotContain("2011 Q1")
                .doesNotContain("2013 Q4");
    }

    @Test
    public void timeseriesFilterOnMonths() {
        final Filter filter = new Filter();
        filter.setFrequency("months");
        filter.setFormat("csv");
        filter.setFromYear("2011");
        filter.setToYear("2013");
        filter.setFromMonth("08");
        filter.setToMonth("11");
        generator.addPage(testdata, filter);
        final String results = generator.convertPage(filter);
        final String[] rows = results.split("\n");
        assertThat(rows).hasSize(36);
        assertThat(results)
                .doesNotContain("2011 JUL")
                .doesNotContain("2013 DEC");
    }
}