package dp.generater.pages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timeseries {

    private List<TimeserieValue> months;

    private List<TimeserieValue> quarters;

    private List<TimeserieValue> years;

    private TimeseriesDescription description;

    private String notes;


    public List<TimeserieValue> getMonths() {
        return months;
    }

    public void setMonths(List<TimeserieValue> months) {
        this.months = months;
    }

    public List<TimeserieValue> getQuarters() {
        return quarters;
    }

    public void setQuarters(List<TimeserieValue> quarters) {
        this.quarters = quarters;
    }

    public List<TimeserieValue> getYears() {
        return years;
    }

    public void setYears(List<TimeserieValue> years) {
        this.years = years;
    }

    public TimeseriesDescription getDescription() {
        return description;
    }

    public void setDescription(TimeseriesDescription description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
