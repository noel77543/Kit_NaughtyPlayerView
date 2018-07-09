package tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.controller.console;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import tw.noel.sung.com.kit_naughtyplayerview.R;
import tw.noel.sung.com.kit_naughtyplayerview.util.time.TimeUtil;

/**
 * Created by noel on 2018/7/7.
 */

public class NaughtyPlayerConsole extends RelativeLayout implements SeekBar.OnSeekBarChangeListener, Runnable {
    private TextView currentTextView;
    private SeekBar seekBar;
    private TextView totalTextView;
    private Context context;
    private final int _MARGIN = 40;
    private TimeUtil timeUtil;

    private int[] totalTimes;
    private int[] currentTimes;
    private OnProgressChangeListener onProgressChangeListener;
    private int progress;

    public NaughtyPlayerConsole(Context context) {
        super(context);
        this.context = context;
        init();
    }

    //----------
    private void init() {
        timeUtil = new TimeUtil();

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
        seekBar.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
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
        addView(totalTextView);
        totalTextView.post(this);
    }

    //---------
    @Override
    public void run() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, seekBar.getHeight() + currentTextView.getHeight());
        params.gravity = Gravity.BOTTOM;
        setLayoutParams(params);
        setBackgroundColor(context.getResources().getColor(R.color.header_and_console_bg));
    }
    //----------

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            Log.e("onProgressChanged", ""+progress);
            this.progress = progress;
        }
    }
    //----------

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    //----------

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.e("onStopTrackingTouch", ""+progress);
        setCurrentTime(progress);
        onProgressChangeListener.onProgressChanged(progress);
    }
    //---------

    /***
     *  設置目前時間
     */
    public void setCurrentTime(int seconds) {
        seekBar.setProgress(seconds);
        currentTimes = timeUtil.getConvertTime(seconds);
        currentTextView.setText(String.format(context.getString(R.string.video_time), currentTimes[0], currentTimes[1], currentTimes[2]));
    }
    //----------

    /***
     * 設置影片總長度
     */
    public void setTotalTime(int seconds) {
        seekBar.setMax(seconds);
        totalTimes = timeUtil.getConvertTime(seconds);
        totalTextView.setText(String.format(context.getString(R.string.video_time), totalTimes[0], totalTimes[1], totalTimes[2]));
    }

    //-----------
    public interface OnProgressChangeListener {
        void onProgressChanged(int second);
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
    }
}
