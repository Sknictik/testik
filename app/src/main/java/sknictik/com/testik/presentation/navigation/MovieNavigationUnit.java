package sknictik.com.testik.presentation.navigation;

import android.content.Intent;

import etr.android.reamp.navigation.Navigation;
import etr.android.reamp.navigation.NavigationUnit;
import sknictik.com.testik.domain.data.Movie;
import sknictik.com.testik.presentation.movie.MovieActivity;

public class MovieNavigationUnit extends NavigationUnit<Movie> {

    private static final String MOVIE_ARGS = "args:movie";

    private Movie movie;

    public MovieNavigationUnit(Movie movie) {
        this.movie = movie;
    }

    public MovieNavigationUnit() {}

    @Override
    protected void navigate(Navigation navigation) {
        Intent intent = new Intent(navigation.getActivity(), MovieActivity.class);
        intent.putExtra(MOVIE_ARGS, movie);
        navigation.getActivity().startActivity(intent);
    }

    @Override
    protected Movie getNavigationData(Navigation navigation) {
        return (Movie) navigation.getActivity().getIntent().getSerializableExtra(MOVIE_ARGS);
    }
}
