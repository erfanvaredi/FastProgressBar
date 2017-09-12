package ir.erfanvaredi.fastprogressbar.epb;

import android.content.Context;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by erfanvaredi on 3/23/17.
 */

public class FPB implements IFPB{

    private static final int TIMER_TYPE_AFTER_FINISHED_DO = 11;
    private final int TIMER_TYPE_ASYNC_STEPPER = 12;

    private Context ctx;

    private static EPBDialog dialog;

    private int dialogViewId = 0;

    private boolean isCancelable = true;

    private IFPBCallback iFpbCallback;
    private IAsyncFPBCallback iAsyncFpbCallback;
    private long waitingTime = 0;
    private int step = 1;

    private int timerType;
    private static Timer timer;
    private boolean delayFirstStep = false;
//    private EPBTimerTask epbTimerTask;


    public static FPB with(Context context) {
        FPB epb = new FPB();
        epb.ctx = context;
        return epb;
    }

    @Override
    public FPB setView(int resId) {
        this.dialogViewId = resId;
        return this;
    }

    @Override
    public FPB setCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        return this;
    }

    @Override
    public FPB setTimer(long waitingTimeMilliSecond, IFPBCallback callback) {
        this.iFpbCallback = callback;
        this.timerType = TIMER_TYPE_AFTER_FINISHED_DO;
        if (waitingTimeMilliSecond > 0) {
            this.waitingTime = waitingTimeMilliSecond;
        }
        this.step = 1;
        return this;
    }

    @Override
    public FPB setAsyncStepperTimer(long waitingTimeMilliSecond, int step, IAsyncFPBCallback callback) {
        this.iAsyncFpbCallback = callback;
        this.timerType = TIMER_TYPE_ASYNC_STEPPER;
        if (step > 0) {
            this.step = step;
        }
        this.waitingTime = waitingTimeMilliSecond;
        return this;
    }

    @Override
    public FPB setUIStepperTimer(long waitingTimeMilliSecond, int step, IAsyncFPBCallback callback) {
        this.iAsyncFpbCallback = callback;
        this.timerType = TIMER_TYPE_ASYNC_STEPPER;
        if (step > 0) {
            this.step = step;
        }
        this.waitingTime = waitingTimeMilliSecond;
        return this;
    }

    @Override
    public FPB setAsyncStepperTimer(long waitingTimeMilliSecond, int step, boolean delayFirstStep, IAsyncFPBCallback callback) {
        this.iAsyncFpbCallback = callback;
        this.timerType = TIMER_TYPE_ASYNC_STEPPER;
        this.delayFirstStep = delayFirstStep;
        if (step > 0) {
            this.step = step;
        }
        this.waitingTime = waitingTimeMilliSecond;
        return this;
    }

    @Override
    public void show(String message) {
        //** show dialog
        if (dialogViewId != 0) {
            dialog = new EPBDialog(ctx, dialogViewId, true);
        } else {
            dialog = new EPBDialog(ctx);
        }
        dialog.setCancelable(isCancelable);
        dialog.show();
        dialog.setMsg(message);

        //** For Single step timers
        if (timerType == TIMER_TYPE_AFTER_FINISHED_DO) {
            doAfter(waitingTime, new Runnable() {
                @Override
                public void run() {
                    iFpbCallback.onFPBPeriodFinished();
                    dismiss();
                }
            });
        } else {

            //** timer
            if (waitingTime > 0) {
                timer = new Timer();
                EPBTimerTask epbTimerTask = new EPBTimerTask();
                timer.scheduleAtFixedRate(epbTimerTask, 0, waitingTime);
            }
        }
    }


    public static void dismiss() {
        if (dialog != null) {
            if (timer != null) {
                timer.cancel();
            }
            dialog.dismiss();
        }
    }

    private void doAfter(long delayTime, Runnable job) {

        final Handler handler = new Handler();
        handler.postDelayed(job, delayTime);
    }

    private class EPBTimerTask extends TimerTask {

        @Override
        public void run() {

            if (delayFirstStep) {
                delayFirstStep = false;
//                step = step - 1;
                return;
            } else {
                step = step - 1;
            }
            if (timerType == TIMER_TYPE_ASYNC_STEPPER) {
                iAsyncFpbCallback.onFPBPeriodFinished(step);
            }
            if (step == 0) {
                dismiss();
            }
//            step = step - 1;
        }

    }


}
