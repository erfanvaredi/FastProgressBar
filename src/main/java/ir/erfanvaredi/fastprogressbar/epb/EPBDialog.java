package ir.erfanvaredi.fastprogressbar.epb;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import ir.erfanvaredi.fastprogressbar.R;
import ir.erfanvaredi.fastprogressbar.views.smoothpb.ESmoothProgressBar;

/**
 * Created by erfanvaredi on 3/23/17.
 */
//// TODO: 3/27/17  
public class EPBDialog extends Dialog {

    ESmoothProgressBar smoothProgressBar;
    TextView textViewMsg;

    private Context mContext;
    private int layoutId = 0;

    public EPBDialog(Context context) {
        super(context);
        this.mContext = context;

    }

    public EPBDialog(Context context, int resId, boolean customView) {
        super(context);
        this.mContext = context;
        this.layoutId = resId;
    }

    public EPBDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    protected EPBDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (layoutId != 0) {
            setContentView(layoutId);
        } else {
            setContentView(R.layout.fpb_layout);
        }
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewMsg = (TextView) findViewById(R.id.fpb_textView_msg);
        smoothProgressBar = (ESmoothProgressBar) findViewById(R.id.fpb_smoothProgressBar);
    }

    public void setMsg(String msg) {
        textViewMsg.setText(msg);
    }


//    @Override
//    public void onBackPressed() {
//        Toast.makeText(getContext(), getContext().getString(R.string.epb_back_press), Toast.LENGTH_SHORT).show();
//    }
}