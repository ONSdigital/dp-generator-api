package dp.generatorapi.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import dp.generatorapi.exceptions.JsonToObjectException;
import dp.generatorapi.pages.TimeserieValue;
import dp.generatorapi.pages.Timeseries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimeseriesGenerator implements GeneratorType {

    private final List<List<String>> grid = new ArrayList<>();

    @Override
    public String convertPage(final Filter filter) {
        if (filter.getFormat().equals("csv")) {
            return Grid.gridToCsv(grid);
        } else {
            return Grid.gridToXLS(grid);
        }
    }

    @Override
    public void addPage(final String content, final Filter filter) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final Timeseries timeseries = mapper.readValue(content, Timeseries.class);
            addGridFromFilter(timeseries, filter);
        } catch (IOException e) {
            throw new JsonToObjectException(e);
        }
    }

    private void addGridFromFilter(final Timeseries timeseries, final Filter filter) {
        final String freqency = filter.getFrequency() != null ? filter.getFrequency() : "years";
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
                if (filter.getFromYear() != null && filter.getToYear() != null) {
                    data = filterOnYears(timeseries, filter);
                } else {
                    data = timeseries.getYears();
                }
                break;
            case "months":
                if (filter.getFromMonth() != null && filter.getToMonth() != null) {
                    data = filterOnMonths(timeseries, filter);
                } else {
                    data = timeseries.getMonths();
                }
                break;
            case "quarters":
                if (filter.getFromQuarter() != null && filter.getToQuarter() != null) {
                    data = filterOnQuarter(timeseries, filter);
                } else {
                    data = timeseries.getQuarters();
                }
                break;
            default:
                throw new RuntimeException("Invalid command");
        }
        data.forEach(e -> grid.add(Grid.rowFromPair(e.getDate(), e.getValue())));
    }

    private List<TimeserieValue> filterOnYears(final Timeseries timeseries, final Filter filter) {
        final List<TimeserieValue> filtedList = new ArrayList<>();
        final int fromYear = Integer.valueOf(filter.getFromYear());
        final int toYear = Integer.valueOf(filter.getToYear());
        timeseries.getYears().forEach(v -> {
            final int currentYear = Integer.valueOf(v.getYear());
            if (currentYear >= fromYear && currentYear <= toYear) {
                filtedList.add(v);
            }
        });
        return filtedList;
    }

    private List<TimeserieValue> filterOnQuarter(final Timeseries timeseries, final Filter filter) {
        final List<TimeserieValue> filtedList = new ArrayList<>();
        final int fromQuarter = Integer.valueOf(filter.getFromQuarter().charAt(1));
        final int toQuarter = Integer.valueOf(filter.getToQuarter().charAt(1));
        final int fromYear = Integer.valueOf(filter.getFromYear());
        final int toYear = Integer.valueOf(filter.getToYear());

        timeseries.getQuarters().forEach(v -> {
            final int currentYear = Integer.valueOf(v.getYear());
            final int currentQuarter = Integer.valueOf(v.getQuarter().charAt(1));
            if (currentYear > fromYear && currentYear < toYear) {
                filtedList.add(v);
            } else if (currentYear == fromYear && currentQuarter >= fromQuarter) {
                filtedList.add(v);
            } else if (currentYear == toYear && currentQuarter <= toQuarter) {
                filtedList.add(v);
            }
        });

        return filtedList;
    }

    private List<TimeserieValue> filterOnMonths(final Timeseries timeseries, final Filter filter) {
        final List<TimeserieValue> filtedList = new ArrayList<>();
        final int fromMonth = Integer.valueOf(filter.getFromMonth());
        final int toMonth = Integer.valueOf(filter.getToMonth());
        final int fromYear = Integer.valueOf(filter.getFromYear());
        final int toYear = Integer.valueOf(filter.getToYear());
        timeseries.getMonths().forEach(v -> {
            final int currentYear = Integer.valueOf(v.getYear());
            final int currentMonth = monthToNumber(v.getMonth());
            if (currentYear > fromYear && currentYear < toYear) {
                filtedList.add(v);
            } else if (currentYear == fromYear && currentMonth >= fromMonth) {
                filtedList.add(v);
            } else if (currentYear == toYear && currentMonth <= toMonth) {
                filtedList.add(v);
            }
        });
        return filtedList;
    }

    private int monthToNumber(final String month) {
        switch (month.toLowerCase()) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
        }
        return 0;
    }
}
