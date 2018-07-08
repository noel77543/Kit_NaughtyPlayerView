package tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.popupwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



public class ControllerPopupWindow extends PopupWindow {

    //聲音
    public final static int CONTROLLER_VOLUME = 100;
    //明亮度
    public final static int CONTROLLER_BRIGHTNESS = 101;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CONTROLLER_VOLUME, CONTROLLER_BRIGHTNESS})
    public @interface ControllerType {
    }

    private Context context;

    public ControllerPopupWindow(Context context) {
        this.context = context;
//        setContentView(LayoutInflater.from(context).inflate(R.layout.popup_window_tool_box, null));
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setOutsideTouchable(true);
        setFocusable(true);
    }
    //-----------

    /***
     *  顯示 window
     * @param layoutId
     */
    public void showPopupWindow(int layoutId, View parent, int x, int y) {
        showAsDropDown(parent, x, y);

    }
}
