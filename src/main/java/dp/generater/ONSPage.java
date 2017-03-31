package dp.generater;

import javax.persistence.*;

@Table(name = "metadata")
@Entity
public class ONSPage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String collection_id;

    private String uri;

    private String content;

    public ONSPage(long id, String collectionId, String uri, String content) {
        this.id = id;
        this.collection_id = collectionId;
        this.uri = uri;
        this.content = content;
    }

    public ONSPage(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getcollectionId() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
