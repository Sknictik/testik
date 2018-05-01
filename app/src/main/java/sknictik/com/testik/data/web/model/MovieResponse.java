package sknictik.com.testik.data.web.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<MovieEntity> results;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;

    public List<MovieEntity> getResults() {
        return results;
    }
}
