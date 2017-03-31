package dp.generater.pages;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeseriesDescription {

    private String title;

    private String CDID;

    private String preUnit;

    private String unit;

    private String releaseDate;

    private String nextRelease;

    private String datasetId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCDID() {
        return CDID;
    }

    public void setCDID(String CDID) {
        this.CDID = CDID;
    }

    public String getPreUnit() {
        return preUnit;
    }

    public void setPreUnit(String preUnit) {
        this.preUnit = preUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseData) {
        this.releaseDate = releaseData;
    }

    public String getNextRelease() {
        return nextRelease;
    }

    public void setNextRelease(String nextRelease) {
        this.nextRelease = nextRelease;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }
}
