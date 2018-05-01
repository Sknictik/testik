package sknictik.com.testik.domain.mapper;

import sknictik.com.testik.data.web.model.MovieEntity;
import sknictik.com.testik.domain.data.Movie;

public class MoviesMapper {

    public static Movie mapMovie(MovieEntity movieEntity) {
        if (movieEntity != null) {
            return new Movie(movieEntity.getId(), movieEntity.getTitle(), movieEntity.getOverview(), movieEntity.getVideo(), movieEntity.getPreview());
        }
        return null;
    }

}
