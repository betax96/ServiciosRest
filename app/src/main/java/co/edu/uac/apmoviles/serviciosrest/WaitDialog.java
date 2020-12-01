package co.edu.uac.apmoviles.serviciosrest;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatDialog;

public class WaitDialog extends AppCompatDialog {

    private ProgressBar progressBar;
    private int color = -1;

    public WaitDialog(Activity activity) {
        super(activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_wait);
        progressBar = findViewById(R.id.progressBar);
        if(color!=-1){
            progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.setCancelable(false);
        super.setCanceledOnTouchOutside(false);
    }

    public void setColor(int color) {
        this.color = color;
    }
}
