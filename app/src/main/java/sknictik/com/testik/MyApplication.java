package sknictik.com.testik;

import com.google.gson.Gson;

import android.app.Application;

import sknictik.com.testik.data.web.WebServiceImpl;
import sknictik.com.testik.domain.CommandFactory;

public class MyApplication extends Application {

    private CommandFactory commandFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        commandFactory = new CommandFactory(new WebServiceImpl(this, new Gson()));
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

}
