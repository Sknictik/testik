package sknictik.com.testik.data.web;

import java.util.List;

import rx.Observable;
import sknictik.com.testik.data.web.model.MovieEntity;

public interface WebService {
    Observable<List<MovieEntity>> getMovieList();
}
