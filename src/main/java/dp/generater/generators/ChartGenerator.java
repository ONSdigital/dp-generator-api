package dp.generater.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import dp.generater.pages.Chart;
import dp.generater.pages.Filter;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class ChartGenerator implements GeneratorType {
    @Override
    public String convertPage(final String content, final Filter filter) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Chart chart = mapper.readValue(content, Chart.class);
            if (filter.getFormat().equals("csv")) {
                return convertToCsv(chart);
            } else {
                return convertToXls(chart);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertToCsv(final Chart chart) {
        final List<List<String>> grid = chartDataGrid(chart);
        return Grid.gridToCsv(grid);
    }

    private String convertToXls(final Chart chart) {
        final List<List<String>> grid = chartDataGrid(chart);
        return Grid.gridToXLS(grid);
    }

    private List<List<String>> chartDataGrid(final Chart chart) {
        final List<List<String>> grid = new ArrayList<>();
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

        return grid;
    }


}
