package dp.generatorapi.generators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PageGeneratorFactoryTest {

    private PageGeneratorFactory factory  = new PageGeneratorFactory();

    @Test
    public void checkPageGenerator() {
        GeneratorType timeseries = factory.get("{ \"type\":\"timeseries\" }");
        GeneratorType chart = factory.get("{ \"type\":\"chart\" }");
        GeneratorType nullObjec = factory.get("{ \"type\":\"sdfsdf\" }");

        assertThat(timeseries).isInstanceOf(TimeseriesGenerator.class);
        assertThat(chart).isInstanceOf(ChartGenerator.class);
        assertThat(nullObjec).isInstanceOf(NullGenerator.class);

    }
}
