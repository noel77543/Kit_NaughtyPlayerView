package tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.controller.console;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import tw.noel.sung.com.kit_naughtyplayerview.util.ColorUtil;

/**
 * Created by noel on 2018/7/7.
 */

public class NaughtyPlayerConsole extends RelativeLayout implements SeekBar.OnSeekBarChangeListener, Runnable {
    private TextView currentTextView;
    private SeekBar seekBar;
    private TextView totalTextView;
    private Context context;
    private final int _MARGIN = 40;

    public NaughtyPlayerConsole(Context context) {
        super(context);
        this.context = context;
        init();
    }

    //----------
    private void init() {
        addSeekBar();
        addCurrentTextView();
        addTotalTextView();
        seekBar.setOnSeekBarChangeListener(this);
    }

    //--------

    /***
     *  當前時間
     */
    private void addCurrentTextView() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_START);
        params.addRule(ABOVE, seekBar.getId());
        params.leftMargin = _MARGIN;


        currentTextView = new TextView(context);
        currentTextView.setLayoutParams(params);
        currentTextView.setGravity(Gravity.CENTER);
        currentTextView.setTextColor(Color.LTGRAY);
        currentTextView.setText("aaaaaaaaa");
        addView(currentTextView);
        currentTextView.post(this);
    }


    //------------

    /***
     * 進度條
     */
    private void addSeekBar() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_BOTTOM);


        seekBar = new SeekBar(context);
        seekBar.setLayoutParams(params);
        seekBar.setId(generateViewId());
        addView(seekBar);
        seekBar.post(this);
    }


    //------------

    /***
     * 總時間
     */
    private void addTotalTextView() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_END);
        params.addRule(ABOVE, seekBar.getId());
        params.rightMargin = _MARGIN;

        totalTextView = new TextView(context);
        totalTextView.setLayoutParams(params);
        totalTextView.setGravity(Gravity.CENTER);
        totalTextView.setTextColor(Color.LTGRAY);
        totalTextView.setText("aaa");
        addView(totalTextView);
        totalTextView.post(this);
    }

    //---------
    @Override
    public void run() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, seekBar.getHeight() + currentTextView.getHeight());
        params.gravity = Gravity.BOTTOM;
        setLayoutParams(params);
        setBackgroundColor(Color.parseColor(ColorUtil.HEADER_AND_CONSOLE));
    }
    //----------

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        currentTextView.setText();
    }
    //----------

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    //----------

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}
