package toong.vn.androidtwowaybindingcustomview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

/**
 * Created by PhanVanLinh on 05/02/2018.
 * phanvanlinh.94vn@gmail.com
 */

public class MainViewModel extends BaseObservable {
    String TAG = getClass().getSimpleName();
    private int color;

    public void setColor(int color) {
        this.color = color;

        Log.i(TAG, "setColor:" + color);
    }

    @Bindable
    public int getColor() {
        Log.i(TAG, "getColor:" + color);
        return color;
    }
}
