package sknictik.com.testik.presentation.movie;

import android.os.Bundle;

import etr.android.reamp.mvp.ReampPresenter;
import sknictik.com.testik.presentation.base.BaseActivity;

public class MovieActivity extends BaseActivity<MoviePresenter, MovieStateModel> {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public MovieStateModel onCreateStateModel() {
        return new MovieStateModel();
    }

    @Override
    public ReampPresenter<MovieStateModel> onCreatePresenter() {
        return new MoviePresenter();
    }
}
