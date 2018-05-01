package sknictik.com.testik.presentation.home;

import android.util.Log;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sknictik.com.testik.R;
import sknictik.com.testik.domain.MoviesCommand;
import sknictik.com.testik.domain.data.Movie;
import sknictik.com.testik.presentation.base.BasePresenter;
import sknictik.com.testik.presentation.base.resourceMessage.ResourceMessage;
import sknictik.com.testik.presentation.navigation.MovieNavigationUnit;

public class HomePresenter extends BasePresenter<HomeStateModel> {

    private MoviesCommand moviesCommand;

    private Subscription loadMovieListSubscription;

    HomePresenter(MoviesCommand moviesCommand) {
        this.moviesCommand = moviesCommand;
    }

    @Override
    public void onPresenterCreated() {
        super.onPresenterCreated();

        loadMovieList();
    }

    @Override
    public void onDestroyPresenter() {
        super.onDestroyPresenter();

        if (loadMovieListSubscription != null) {
            loadMovieListSubscription.unsubscribe();
        }
    }

    private void loadMovieList() {
        if (loadMovieListSubscription != null) {
            loadMovieListSubscription = null;
        }

        getStateModel().loadingInProgress = true;
        sendStateModel();

        loadMovieListSubscription = moviesCommand.loadMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                        loadMovieListSubscription = null;
                        getStateModel().loadingInProgress = false;
                        sendStateModel();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(HomePresenter.class.getSimpleName(), "Failed to load movie list", e);

                        loadMovieListSubscription = null;
                        getStateModel().loadingInProgress = false;
                        getStateModel().errorMessage.set(new ResourceMessage(R.string.home_movie_load_error, e.getLocalizedMessage()));
                        sendStateModel();
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        getStateModel().movieList = movies;
                    }
                });
    }

    void onMovieClick(Movie movie) {
        getNavigation().open(new MovieNavigationUnit(movie));
    }


}
