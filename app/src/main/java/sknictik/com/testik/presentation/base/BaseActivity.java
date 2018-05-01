package sknictik.com.testik.presentation.base;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;

import etr.android.reamp.mvp.ReampAppCompatActivity;
import etr.android.reamp.mvp.ReampPresenter;
import etr.android.reamp.mvp.ReampStateModel;
import sknictik.com.testik.MyApplication;
import sknictik.com.testik.R;
import sknictik.com.testik.presentation.base.resourceMessage.ResourceMessage;
import sknictik.com.testik.presentation.base.resourceMessage.ResourceMessageFormatter;

public abstract class BaseActivity<P extends BasePresenter<SM>, SM extends BaseStateModel> extends ReampAppCompatActivity<P, SM> {

    protected MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }

    @Override
    public void onStateChanged(SM stateModel) {
        if (stateModel.errorMessage.hasAction()) {
            showError(stateModel.errorMessage.get());
        }
    }

    protected void showError(ResourceMessage message) {
        showAlertDialog(getString(R.string.common_error), message);
    }

    protected Dialog showAlertDialog(String title, ResourceMessage message) {
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(new ResourceMessageFormatter(this).format(message))
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

}
