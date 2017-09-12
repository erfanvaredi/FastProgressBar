package ir.erfanvaredi.fastprogressbar.epb;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import ir.erfanvaredi.fastprogressbar.R;
import ir.erfanvaredi.fastprogressbar.views.smoothpb.SmoothProgressBar;

/**
 * Created by erfanvaredi on 3/23/17.
 */
//// TODO: 3/27/17  
public class EPBDialog extends Dialog {

    SmoothProgressBar smoothProgressBar;
    TextView textViewMsg;

    private Context mContext;

    public EPBDialog(Context context) {
        super(context);
        this.mContext = context;

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
        setContentView(R.layout.epb_layout);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewMsg = (TextView) findViewById(R.id.epb_textView_msg);
        smoothProgressBar = (SmoothProgressBar) findViewById(R.id.epb_smoothProgressBar);
    }

    public void setMsg(String msg){
        textViewMsg.setText(msg);
    }


//    @Override
//    public void onBackPressed() {
//        Toast.makeText(getContext(), getContext().getString(R.string.epb_back_press), Toast.LENGTH_SHORT).show();
//    }
}