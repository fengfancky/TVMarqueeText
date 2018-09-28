package cn.fengfancky.tvmarqueetext;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by office on 2018/9/26.
 */

public class TVMarqueeTextView extends FrameLayout {

    private TextView firstText,secondText;
    private TranslateAnimation mMoveTextOut, mMoveTextIn;
    private Runnable mRunnable;
    private boolean isStop  = false;//动画是否停止
    private int mDelayedTime = 1000;//动画延后时间
    private float mSpace = 100;//前后文本间隔距离
    private float mSpeed = 0.1f;//动画运动
    private float mTextSize = 14; //字体大小
    private int mTextColor; //字体颜色

    public TVMarqueeTextView(Context context) {
        super(context);
        initView(context);
    }

    public TVMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs,context);
    }

    public TVMarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs,context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.marquee_layout,this,true);
        firstText = view.findViewById(R.id.first_text);
        secondText = view.findViewById(R.id.second_text);

    }

    private void initView( AttributeSet attrs,Context context){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TVMarqueeTextView);
        mTextColor = a.getColor(R.styleable.TVMarqueeTextView_mTextColor,0xFF333333);
        mTextSize = a.getDimension(R.styleable.TVMarqueeTextView_mTextSize,14);
        mDelayedTime = a.getInt(R.styleable.TVMarqueeTextView_mDelayedTime,1000);
        mSpace = a.getDimension(R.styleable.TVMarqueeTextView_mSpace,100);
        mSpeed = a.getFloat(R.styleable.TVMarqueeTextView_mSpeed,0.1f);
        a.recycle();

        View view = LayoutInflater.from(context).inflate(R.layout.marquee_layout,this,true);
        firstText = view.findViewById(R.id.first_text);
        secondText = view.findViewById(R.id.second_text);

        firstText.setTextSize(mTextSize);
        secondText.setTextSize(mTextSize);
        firstText.setTextColor(mTextColor);
        secondText.setTextColor(mTextColor);
    }

    public void setTVMarqueeText(String string){
        firstText.setText(string);
        secondText.setText(string);

    }

    private void initAnim(){
        mMoveTextOut = new TranslateAnimation(0, -(firstText.getMeasuredWidth()+dip2px(mSpace,getContext())), 0, 0);
        mMoveTextOut.setDuration((int)((firstText.getMeasuredWidth()+dip2px(mSpace,getContext()))/mSpeed));
        mMoveTextOut.setInterpolator(new LinearInterpolator());
        mMoveTextOut.setFillAfter(true);
        mMoveTextOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isStop){
                    return;
                }
                removeCallbacks(mRunnable);
                postDelayed(mRunnable, mDelayedTime);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mMoveTextIn = new TranslateAnimation(firstText.getMeasuredWidth()+dip2px(mSpace,getContext()), 0, 0, 0);
        mMoveTextIn.setDuration((int)((firstText.getMeasuredWidth()+dip2px(mSpace,getContext()))/mSpeed));
        mMoveTextIn.setInterpolator(new LinearInterpolator());
        mMoveTextIn.setFillAfter(true);
    }

    public void startMarquee(){
        isStop = false;
        if (mRunnable == null) {
            initRunnable();
        }
        postDelayed(mRunnable, mDelayedTime);

    }

    public void stopMarquee(){
        isStop = true;
        firstText.clearAnimation();
        secondText.clearAnimation();
        removeCallbacks(mRunnable);
        secondText.setVisibility(GONE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        if (getWidth() < firstText.getWidth() +getPaddingRight()+getPaddingLeft()){
            initAnim();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initRunnable() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (getWidth() < (firstText.getWidth()+getPaddingLeft()+getPaddingRight())&&mMoveTextIn !=null &&mMoveTextOut != null) {
                    secondText.setVisibility(VISIBLE);
                    firstText.startAnimation(mMoveTextOut);
                    secondText.startAnimation(mMoveTextIn);
                }
            }
        };
    }

    public void setmTextSize(float mTextSize) {
        firstText.setTextSize(mTextSize);
        secondText.setTextSize(mTextSize);
    }

    public void setmTextColor(int mTextColor) {
        firstText.setTextColor(mTextColor);
        secondText.setTextColor(mTextColor);
    }

    private int dip2px(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
