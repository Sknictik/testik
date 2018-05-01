package sknictik.com.testik.presentation.home;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import sknictik.com.testik.R;
import sknictik.com.testik.databinding.ActivityHomeBinding;
import sknictik.com.testik.domain.data.Movie;
import sknictik.com.testik.presentation.base.BaseActivity;

public class HomeActivity extends BaseActivity<HomePresenter, HomeStateModel> implements MoviesAdapter.OnMovieClickListener {

    private ActivityHomeBinding binding;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.movies.setAdapter(new MoviesAdapter(this));
        binding.movies.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(binding.toolbar);
    }

    @Override
    public HomeStateModel onCreateStateModel() {
        return new HomeStateModel();
    }

    @Override
    public HomePresenter onCreatePresenter() {
        return new HomePresenter(getMyApplication().getCommandFactory().getMoviesCommand());
    }

    @Override
    public void onStateChanged(HomeStateModel stateModel) {
        super.onStateChanged(stateModel);

        showMovies(stateModel.movieList);
        showLoading(stateModel.loadingInProgress);
    }

    private void showMovies(List<Movie> movies) {
        MoviesAdapter moviesAdapter = ((MoviesAdapter) (binding.movies.getAdapter()));
        //Fill adapter only once. This is not very good condition since we can't empty list like
        //this and also providing empty list will call notifiyDatasetChanged.
        //In production i would do something more sophisticated
        if (moviesAdapter.isEmpty()) {
            moviesAdapter.setMovieList(movies);
        }
    }

    private void showLoading(boolean showProgress) {
        if (showProgress) {
            if ((progressDialog == null || !progressDialog.isShowing())) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.setMessage(getString(R.string.common_loading));
                progressDialog.show();
            }
        } else if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onMovieClick(Movie movie) {
        getPresenter().onMovieClick(movie);
    }
}
