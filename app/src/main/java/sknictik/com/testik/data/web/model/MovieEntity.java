package sknictik.com.testik.data.web.model;

import com.google.gson.annotations.SerializedName;

public class MovieEntity {

    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("video")
    private String video;
    @SerializedName("poster_path")
    private String preview;

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
