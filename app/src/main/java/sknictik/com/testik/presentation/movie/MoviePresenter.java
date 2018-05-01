package sknictik.com.testik.presentation.movie;

import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Surface;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import sknictik.com.testik.R;
import sknictik.com.testik.presentation.base.BasePresenter;
import sknictik.com.testik.presentation.base.resourceMessage.ResourceMessage;
import sknictik.com.testik.presentation.navigation.MovieNavigationUnit;

public class MoviePresenter extends BasePresenter<MovieStateModel> {

    private MediaPlayer mediaPlayer;

    @Override
    public void onPresenterCreated() {
        super.onPresenterCreated();

        getStateModel().movie = getNavigation().getData(new MovieNavigationUnit());
    }

    @Override
    public void onDisconnect() {
        super.onDisconnect();

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    void onStartVideo(SurfaceTexture surfaceTexture) {
        getStateModel().isPreparingVideo = true;
        sendStateModel();

        preparePlayback(surfaceTexture, getStateModel().movie.getVideo());
    }

    private void preparePlayback(SurfaceTexture surfaceTexture, final String url) {
        Surface s = new Surface(surfaceTexture);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setSurface(s);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        getStateModel().isPreparingVideo = true;
        sendStateModel();

        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();

                return null;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        getStateModel().isPreparingVideo = false;
                        sendStateModel();
                        mediaPlayer.start();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(MoviePresenter.class.getSimpleName(), "Failed to prepare video", e);
                        getStateModel().isPreparingVideo = false;
                        getStateModel().errorMessage.set(new ResourceMessage(R.string.movie_failed_to_show, e.getLocalizedMessage()));
                        sendStateModel();
                    }

                    @Override
                    public void onNext(Object o) {
                        //Do nothing
                    }
                });
    }
}
