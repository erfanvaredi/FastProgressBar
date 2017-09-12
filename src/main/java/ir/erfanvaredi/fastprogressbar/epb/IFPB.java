package ir.erfanvaredi.fastprogressbar.epb;

/**
 * Created by erfanvaredi on 9/12/17.
 */

public interface IFPB {

    FPB setView(int resId);
    FPB setCancelable(boolean cancelable);
    FPB setTimer(long waitingTimeMilliSecond, IFPBCallback callback);
    FPB setAsyncStepperTimer(long waitingTimeMilliSecond, int step, IAsyncFPBCallback callback);
    FPB setUIStepperTimer(long waitingTimeMilliSecond, int step, IAsyncFPBCallback callback);
    FPB setAsyncStepperTimer(long waitingTimeMilliSecond, int step, boolean delayFirstStep, IAsyncFPBCallback callback);

    void show(String message);
}
