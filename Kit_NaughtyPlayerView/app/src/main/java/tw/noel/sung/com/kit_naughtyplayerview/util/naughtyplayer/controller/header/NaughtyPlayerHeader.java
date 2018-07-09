package tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.controller.header;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import tw.noel.sung.com.kit_naughtyplayerview.R;

/**
 * Created by noel on 2018/7/7.
 */

public class NaughtyPlayerHeader extends TextView {
    private Context context;

    public NaughtyPlayerHeader(Context context) {
        super(context);
        this.context = context;
        init();
    }
    //---------

    private void init() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.TOP;
        setLayoutParams(params);
        setPadding(30, 5, 30, 5);
        setBackgroundColor(context.getResources().getColor(R.color.header_and_console_bg));
        setTextColor(Color.WHITE);
        setTextSize(20);
    }
}
