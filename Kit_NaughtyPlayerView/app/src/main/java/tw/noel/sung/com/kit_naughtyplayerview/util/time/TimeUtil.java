package tw.noel.sung.com.kit_naughtyplayerview.util.time;

/**
 * Created by noel on 2018/7/8.
 */

public class TimeUtil {
    //每小時幾秒
    private final int HOUR_TO_SECOND = 3600;
    //每分鐘幾秒
    private final int MINUTE_TO_SECOND = 60;

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
        times[0] = (time / HOUR_TO_SECOND);
        times[1] = (time % HOUR_TO_SECOND) / MINUTE_TO_SECOND;
        times[2] = (time % HOUR_TO_SECOND) % MINUTE_TO_SECOND;
        return times;
    }


}
