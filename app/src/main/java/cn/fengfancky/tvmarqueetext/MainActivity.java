package cn.fengfancky.tvmarqueetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TVMarqueeTextView tvMarqueeTextView;
    private Button button;
    private boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMarqueeTextView = findViewById(R.id.text);
        tvMarqueeTextView.setmTextColor(getResources().getColor(R.color.colorAccent));
        tvMarqueeTextView.setmTextSize(24);
        button = findViewById(R.id.button);
        tvMarqueeTextView.setTVMarqueeText("Android是一种基于Linux的自由及开放源代码的操作系统");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isScrolling = !isScrolling;
                if (isScrolling){
                    tvMarqueeTextView.startMarquee();
                    button.setText("停止");
                }else {
                    tvMarqueeTextView.stopMarquee();
                    button.setText("开始");
                }
            }
        });

    }
}
