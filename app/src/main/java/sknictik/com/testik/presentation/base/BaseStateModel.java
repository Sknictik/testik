package sknictik.com.testik.presentation.base;


import etr.android.reamp.mvp.Action;
import etr.android.reamp.mvp.ReampStateModel;
import sknictik.com.testik.presentation.base.resourceMessage.ResourceMessage;

public class BaseStateModel extends ReampStateModel {
    public Action<ResourceMessage> errorMessage = new Action<>();
}
