package sknictik.com.testik.domain;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import sknictik.com.testik.data.web.WebService;
import sknictik.com.testik.data.web.model.MovieEntity;
import sknictik.com.testik.domain.data.Movie;
import sknictik.com.testik.domain.mapper.MoviesMapper;

//Really should implement an interface
public class MoviesCommand {

    private WebService webService;

    public MoviesCommand(WebService webService) {
        this.webService = webService;
    }

    //Depending on how often list refreshed i may have decided to add a cache to this request
    public Observable<List<Movie>> loadMovieList() {
        return webService.getMovieList().map(new Func1<List<MovieEntity>, List<Movie>>() {
            @Override
            public List<Movie> call(List<MovieEntity> movieEntities) {
                List<Movie> movieList = new ArrayList<>();
                for (MovieEntity movieEntity : movieEntities) {
                    movieList.add(MoviesMapper.mapMovie(movieEntity));
                }

                return movieList;
            }
        });
    }

}
