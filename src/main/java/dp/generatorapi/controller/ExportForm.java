package dp.generatorapi.controller;

import java.util.List;

public class ExportForm {

    private String format;

    private List<String> uri;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getUri() {
        return uri;
    }

    public void setUri(List<String> uri) {
        this.uri = uri;
    }
}
