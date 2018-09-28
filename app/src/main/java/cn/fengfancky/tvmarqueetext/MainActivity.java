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
        button = findViewById(R.id.button);
        tvMarqueeTextView.setTVMarqueeText("Android是一种基于Linux的自由及开放源代码的操作系统，主要使用于移动设备，如智能手机和平板电脑，由Google公司和开放手机联盟领导及开发。尚未有统一中文名称，中国大陆地区较多人使用“安卓”或“安致”。Android操作系统最初由Andy Rubin开发，主要支持手机。");
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
