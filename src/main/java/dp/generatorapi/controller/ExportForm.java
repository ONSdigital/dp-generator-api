package dp.generatorapi.controller;

import java.util.List;

public class ExportForm {

    private String format;

    private List<String> url;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}
