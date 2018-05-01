package sknictik.com.testik.data.web;

import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import sknictik.com.testik.R;
import sknictik.com.testik.data.web.model.MovieEntity;
import sknictik.com.testik.data.web.model.MovieResponse;

/**
 * Emulating web service
 */
public class WebServiceImpl implements WebService {

    //Obviously web service should have no need to access context manually, but since we're emulating network behaviour - that's fine.
    private Context context;
    private Gson gson;

    public WebServiceImpl(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    @Override
    public Observable<List<MovieEntity>> getMovieList() {
        InputStream is = context.getResources().openRawResource(R.raw.movies);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            Log.e(WebServiceImpl.class.getSimpleName(), "Failed to read file", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(WebServiceImpl.class.getSimpleName(), "Failed to close stream", e);
            }
        }

        String jsonString = writer.toString();
        MovieResponse movieResponse = gson.fromJson(jsonString, MovieResponse.class);

        //Let's pretend our network request works 2 seconds
        return Observable.just(movieResponse.getResults()).delay(2, TimeUnit.SECONDS);
    }
}
