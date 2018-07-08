package tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.videoview;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;

public class NaughtyPlayer extends VideoView implements MediaPlayer.OnPreparedListener {

    private Context context;
    private OnPlayerStateChangeListener onPlayerStateChangeListener;

    public NaughtyPlayer(Context context) {
        super(context);
        this.context = context;
        init();
    }

    //--------
    private void init() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        setOnPreparedListener(this);

    }
    //--------

    /***
     *  當影片家載完成即將開始播放
     * @param mediaPlayer
     */
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        onPlayerStateChangeListener.onPlayerReady(mediaPlayer);
    }

    //--------------

    public void setOnPlayerStateChangeListener(OnPlayerStateChangeListener onPlayerStateChangeListener) {
        this.onPlayerStateChangeListener = onPlayerStateChangeListener;
    }
    //--------------

    @Override
    public void pause() {
        super.pause();
        if (onPlayerStateChangeListener != null) {
            onPlayerStateChangeListener.onPlayerPause();
        }
    }
    //--------------

    @Override
    public void start() {
        super.start();
        if (onPlayerStateChangeListener != null) {
            onPlayerStateChangeListener.onPlayerPlay();
        }
    }
    //--------------

    public interface OnPlayerStateChangeListener {
        void onPlayerReady(MediaPlayer mediaPlayer);
        void onPlayerPlay();
        void onPlayerPause();
    }

}
