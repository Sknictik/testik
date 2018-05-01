package sknictik.com.testik.presentation.movie;

import android.databinding.DataBindingUtil;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;

import etr.android.reamp.mvp.ReampPresenter;
import sknictik.com.testik.R;
import sknictik.com.testik.databinding.ActivityMovieBinding;
import sknictik.com.testik.domain.data.Movie;
import sknictik.com.testik.presentation.base.BaseActivity;

public class MovieActivity extends BaseActivity<MoviePresenter, MovieStateModel> implements TextureView.SurfaceTextureListener {

    private ActivityMovieBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        binding.playbackView.setSurfaceTextureListener(this);
        binding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onStartVideo(binding.playbackView.getSurfaceTexture());
                binding.startBtn.setEnabled(false);
            }
        });

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public MovieStateModel onCreateStateModel() {
        return new MovieStateModel();
    }

    @Override
    public ReampPresenter<MovieStateModel> onCreatePresenter() {
        return new MoviePresenter();
    }

    @Override
    public void onStateChanged(MovieStateModel stateModel) {
        super.onStateChanged(stateModel);

        binding.progress.setVisibility(stateModel.isPreparingVideo ? View.VISIBLE : View.GONE);
        showMovie(stateModel.movie);
    }

    private void showMovie(Movie movie) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(movie.getTitle());
        }

        binding.videoDescription.setText(movie.getOverview());
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        binding.startBtn.setEnabled(true);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
        //Do nothing
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        //Do nothing
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        //Do nothing
    }
}
