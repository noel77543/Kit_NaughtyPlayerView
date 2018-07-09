package tw.noel.sung.com.kit_naughtyplayerview.util.time;

/**
 * Created by noel on 2018/7/8.
 */

public class TimeUtil {
    //每分鐘幾秒
    private final int MINUTE_TO_SECOND = 60;
    //每小時幾秒
    private final int HOUR_TO_SECOND = MINUTE_TO_SECOND * 60;


    public TimeUtil() {

    }

    //--------

    /***
     *  將秒數轉成 幾時幾分幾秒
     * @param time
     * @return
     */
    public int[] getConvertTime(int time) {
        int[] times = new int[3];
        //時
        times[0] = (time / HOUR_TO_SECOND);
        //分
        times[1] = (time % HOUR_TO_SECOND) / MINUTE_TO_SECOND;
        //秒
        times[2] = (time % HOUR_TO_SECOND) % MINUTE_TO_SECOND;
        return times;
    }


}
