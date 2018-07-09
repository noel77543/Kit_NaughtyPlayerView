package tw.noel.sung.com.kit_naughtyplayerview;

import android.app.Activity;
import android.os.Bundle;

import tw.noel.sung.com.kit_naughtyplayerview.util.naughtyplayer.NaughtyPlayerView;

public class MainActivity extends Activity {

    private NaughtyPlayerView naughtyPlayerView;
    private final String URL_VIDEO = "http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        naughtyPlayerView = (NaughtyPlayerView) findViewById(R.id.naughty_player_view);
        init();
    }
    //---------

    private void init() {
        naughtyPlayerView.start(URL_VIDEO,"測試撥放器");
    }
}
