package dp.generatorapi.pages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
        private String type;

        public Page() {

        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }