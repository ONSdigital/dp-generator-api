package dp.generatorapi.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import dp.generatorapi.exceptions.JsonToObjectException;
import dp.generatorapi.pages.Chart;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class ChartGenerator implements GeneratorType {

    private final List<List<String>> grid = new ArrayList<>();

    @Override
    public String convertPage(final Filter filter) {
        if (filter.getFormat().equals("csv")) {
            return Grid.gridToCsv(grid);
        } else {
            return Grid.gridToXLS(grid);
        }
    }

    public void addPage(final String content, final Filter filter) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Chart chart = mapper.readValue(content, Chart.class);
            addChartDataGrid(chart);
        } catch (IOException e) {
            throw new JsonToObjectException(e);
        }
    }

    private void addChartDataGrid(final Chart chart) {
        grid.add(Grid.rowFromPair(chart.getTitle(), ""));
        grid.add(Grid.rowFromPair(chart.getSubTitle(), ""));
        grid.add(Grid.rowFromPair("", ""));
        grid.add(Grid.rowFromPair("Notes", chart.getNotes()));
        grid.add(Grid.rowFromPair("Unit", chart.getUnit()));
        grid.add(Grid.rowFromPair("", ""));

        grid.add(chart.getHeaders());
        for (Map<String, String> point : chart.getData()) {
            grid.add(Grid.rowFromMap(chart.getHeaders(), point));
        }
    }


}
