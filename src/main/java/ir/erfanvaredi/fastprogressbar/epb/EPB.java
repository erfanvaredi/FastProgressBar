package ir.erfanvaredi.fastprogressbar.epb;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by erfanvaredi on 3/23/17.
 */

public class EPB {

    private Context ctx;

    private static EPBDialog dialog;

    private boolean isCancelable = false;

    private IEPBCallback iepbCallback;
    private long waitingTime = 0;
    private int step = 1;
//    private EPBTimerTask epbTimerTask;


    public static EPB with(Context context) {
        EPB epb = new EPB();
        epb.ctx = context;
        return epb;
    }

    public EPB setCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        return this;
    }

    public EPB setTimer(long waitingTimeMilliSecond, IEPBCallback callback) {
        this.iepbCallback = callback;
        if (waitingTimeMilliSecond > 0) {
            this.waitingTime = waitingTimeMilliSecond;
        }
        this.step = 1;
        return this;
    }

    public EPB setTimer(long waitingTimeMilliSecond, int step, IEPBCallback callback) {
        this.iepbCallback = callback;
        if (step > 0) {
            this.step = step;
        }
        this.waitingTime = waitingTimeMilliSecond;
        return this;
    }

    public void show(String message) {
        //** show dialog
        dialog = new EPBDialog(ctx);
        dialog.setCancelable(isCancelable);
        dialog.show();
        dialog.setMsg(message);

        //** timer
        if (waitingTime > 0) {
            Timer timer = new Timer();
            EPBTimerTask epbTimerTask = new EPBTimerTask();
            timer.schedule(epbTimerTask, waitingTime);
        }
    }


    public static void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    private class EPBTimerTask extends TimerTask {

        @Override
        public void run() {
            step = step - 1;
            iepbCallback.onEPBPeriodFinished(step);
            if (step == 0){
                dismiss();
            }
        }
    }


}
