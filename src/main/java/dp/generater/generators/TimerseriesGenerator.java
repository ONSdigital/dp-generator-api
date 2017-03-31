package dp.generater.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import dp.generater.pages.Filter;
import dp.generater.pages.TimeserieValue;
import dp.generater.pages.Timeseries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimerseriesGenerator implements GeneratorType {

    @Override
    public String convertPage(final String content, final Filter filter) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            final Timeseries timeseries = mapper.readValue(content, Timeseries.class);
            if (filter.getFormat().equals("csv")) {
                return convertToCsv(timeseries, filter);
            } else {
                return convertToXls(timeseries, filter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertToCsv(final Timeseries timeseries, final Filter filter) {

        final List<List<String>> grid  = getGridFromFilter(timeseries, filter);

        return Grid.gridToCsv(grid);
    }

    private String convertToXls(final Timeseries timeseries, final Filter filter) {
        final List<List<String>> grid  = getGridFromFilter(timeseries, filter);
        return Grid.gridToXLS(grid);
    }

    private List<List<String>> getGridFromFilter(final Timeseries timeseries, final Filter filter) {
        final String freqency = filter.getFrequency()!=null?filter.getFrequency():"years";
        final List<List<String>> grid = new ArrayList<>();
        grid.add(Grid.rowFromPair("Title", timeseries.getDescription().getTitle()));
        grid.add(Grid.rowFromPair("CDID", timeseries.getDescription().getCDID()));
        grid.add(Grid.rowFromPair("Source dataset ID", timeseries.getDescription().getDatasetId()));
        grid.add(Grid.rowFromPair("PreUnit", timeseries.getDescription().getPreUnit()));
        grid.add(Grid.rowFromPair("Unit", timeseries.getDescription().getUnit()));
        grid.add(Grid.rowFromPair("Release date", timeseries.getDescription().getReleaseDate()));
        grid.add(Grid.rowFromPair("Next release", timeseries.getDescription().getNextRelease()));
        grid.add(Grid.rowFromPair("Important notes", timeseries.getNotes()));
        final List<TimeserieValue> data;
        switch (freqency) {
            case "years":
                data = timeseries.getYears();
                break;
            case "months":
                data = timeseries.getMonths();
                break;
            case "quarters":
                data = timeseries.getQuarters();
                break;
            default:
                throw new RuntimeException("Invalid command");
        }
        data.forEach(e -> grid.add(Grid.rowFromPair(e.getDate(), e.getValue())));
        return grid;
    }
}
