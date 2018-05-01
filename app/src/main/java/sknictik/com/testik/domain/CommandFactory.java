package sknictik.com.testik.domain;

import sknictik.com.testik.data.web.WebService;

public class CommandFactory {

    private WebService webService;

    public CommandFactory(WebService webService) {
        this.webService = webService;
    }

    public MoviesCommand getMoviesCommand() {
        return new MoviesCommand(webService);
    }

}
