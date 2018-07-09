package tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


import tw.noel.sung.com.kit_naughtyplayerview.R;
import tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.controller.NaughtyPlayerController;
import tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.controller.console.NaughtyPlayerConsole;
import tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.controller.header.NaughtyPlayerHeader;
import tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.videoview.NaughtyPlayer;

/**
 * Created by noel on 2018/7/5.
 */

public class NaughtyPlayerView extends FrameLayout implements NaughtyPlayer.OnPlayerStateChangeListener, NaughtyPlayerConsole.OnProgressChangeListener {

    //上方標題
    private NaughtyPlayerHeader naughtyPlayerHeader;
    //下方控制台
    private NaughtyPlayerConsole naughtyPlayerConsole;

    private Context context;

    private NaughtyPlayer naughtyPlayer;
    private MediaPlayer mediaPlayer;
    //影片總時間  (  秒)
    private int totalSecond;
    //影片當前時間  ( 秒)
    private int currentSecond;
    private NaughtyPlayerController naughtyPlayerController;
    /***
     *  計時器
     */
    private Runnable runnable;
    private Handler handler;
    private final int TIMER_UPDATE_TIME = 1000;


    //----------
    public NaughtyPlayerView(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }
    //----------

    public NaughtyPlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    //----------

    public NaughtyPlayerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    //----------
    private void init() {
        View view =  LayoutInflater.from(context).inflate(R.layout.view_window_volume, this,false);
        naughtyPlayer = new NaughtyPlayer(context);
        naughtyPlayerHeader = new NaughtyPlayerHeader(context);
        naughtyPlayerConsole = new NaughtyPlayerConsole(context);
        naughtyPlayerConsole.setOnProgressChangeListener(this);
        naughtyPlayerController = new NaughtyPlayerController(context, naughtyPlayerHeader, naughtyPlayerConsole,view);
        initTimer();

        addView(naughtyPlayer);
        addView(naughtyPlayerController);
        addView(naughtyPlayerHeader);
        addView(naughtyPlayerConsole);
        addView(view);
        naughtyPlayer.setOnPlayerStateChangeListener(this);
    }

    //-------

    /***
     *  初始化計時器
     */
    private void initTimer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentSecond <= totalSecond) {
                    naughtyPlayerConsole.setCurrentTime(currentSecond);
                    currentSecond++;
                }
                handler.postDelayed(runnable, TIMER_UPDATE_TIME);
            }
        };
    }


    //------------

    /***
     *  帶入連結後播放
     */
    public void start(String videoUrlString, String headerTitle) {
        naughtyPlayerHeader.setText(headerTitle);
        naughtyPlayer.setVideoURI(Uri.parse(videoUrlString));
        naughtyPlayer.start();
    }
    //------------

    /***
     *  當影片載入完成準備播放
     * @param mediaPlayer
     */
    @Override
    public void onPlayerReady(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        totalSecond = mediaPlayer.getDuration() / 1000;
        naughtyPlayerConsole.setTotalTime(totalSecond);
        naughtyPlayerController.isReady = true;
        naughtyPlayerConsole.setCurrentTime(0);
    }
    //--------

    /***
     * 當播放
     */
    @Override
    public void onPlayerPlay() {
        naughtyPlayerController.isPlaying = true;
        startTiming();
    }
    //--------

    /***
     *  當暫停
     */
    @Override
    public void onPlayerPause() {
        naughtyPlayerController.isPlaying = false;

    }
    //---------

    /***
     *
     * @param second
     */
    @Override
    public void onProgressChanged(int second) {
        currentSecond = second;
        mediaPlayer.seekTo(currentSecond);
    }
    //-------

    /***
     * 開始計時
     */
    private void startTiming() {
        handler.postDelayed(runnable, 0);
    }

    //----------
    /***
     * 暫停
     */

}
