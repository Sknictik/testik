package sknictik.com.testik.presentation.home;

import java.util.List;

import sknictik.com.testik.domain.data.Movie;
import sknictik.com.testik.presentation.base.BaseStateModel;

public class HomeStateModel extends BaseStateModel {

    boolean loadingInProgress;

    List<Movie> movieList;

}
