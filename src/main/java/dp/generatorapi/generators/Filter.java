package dp.generatorapi.generators;

public class Filter {

    private String format;

    private String fromYear;

    private String toYear;

    private String fromQuarter;

    private String toQuarter;

    private String fromMonth;

    private String toMonth;

    private String frequency;

    public Filter(String format, String fromYear, String toYear, String fromQuarter,
                  String toQuarter, String fromMonth, String toMonth, String frequency) {
        this.format = format;
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.fromQuarter = fromQuarter;
        this.toQuarter = toQuarter;
        this.fromMonth = fromMonth;
        this.toMonth = toMonth;
        this.frequency = frequency;
    }

    public Filter() {

    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public String getFromQuarter() {
        return fromQuarter;
    }

    public void setFromQuarter(String fromQuarter) {
        this.fromQuarter = fromQuarter;
    }

    public String getToQuarter() {
        return toQuarter;
    }

    public void setToQuarter(String toQuarter) {
        this.toQuarter = toQuarter;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
