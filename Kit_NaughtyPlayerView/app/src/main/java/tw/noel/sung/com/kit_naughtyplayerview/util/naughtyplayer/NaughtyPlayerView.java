package tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.controller.NaughtyPlayerController;
import tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.controller.console.NaughtyPlayerConsole;
import tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.controller.header.NaughtyPlayerHeader;
import tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.videoview.NaughtyPlayer;

/**
 * Created by noel on 2018/7/5.
 */

public class NaughtyPlayerView extends FrameLayout implements  NaughtyPlayer.OnPlayerStateChangeListener {

    //上方標題
    private NaughtyPlayerHeader naughtyPlayerHeader;
    //下方控制台
    private NaughtyPlayerConsole naughtyPlayerConsole;

    private Context context;

    private NaughtyPlayer naughtyPlayer;
    private MediaPlayer mediaPlayer;
    //影片總時間  ( 1/1000 秒)
    private int totalMillis;
    //影片當前時間  ( 1/1000 秒)
    private int currentMillis;
    private NaughtyPlayerController naughtyPlayerController;


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
        naughtyPlayer = new NaughtyPlayer(context);
        naughtyPlayerHeader = new NaughtyPlayerHeader(context);
        naughtyPlayerConsole = new NaughtyPlayerConsole(context);
        naughtyPlayerController = new NaughtyPlayerController(context, naughtyPlayerHeader, naughtyPlayerConsole);


        addView(naughtyPlayer);
        addView(naughtyPlayerController);
        addView(naughtyPlayerHeader);
        addView(naughtyPlayerConsole);
        naughtyPlayer.setOnPlayerStateChangeListener(this);
    }


    //------------

    /***
     *  帶入連結後播放
     */
    public void startVideoWithUrlString(String videoUrlString) {
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
        totalMillis = mediaPlayer.getDuration();
        currentMillis = mediaPlayer.getCurrentPosition();

    }
    //--------

    @Override
    public void onPlayerPlay() {
        naughtyPlayerController.isPlaying = true;
    }
    //--------

    @Override
    public void onPlayerPause() {
        naughtyPlayerController.isPlaying = false;
    }
    //--------



}
