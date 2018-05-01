package sknictik.com.testik.domain.data;

import java.io.Serializable;

public class Movie implements Serializable {

    private long id;
    private String title;
    private String overview;
    private String video;
    private String preview;

    public Movie(long id, String title, String overview, String video, String preview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.video = video;
        this.preview = preview;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getVideo() {
        return video;
    }

    public String getPreview() {
        return preview;
    }
}
