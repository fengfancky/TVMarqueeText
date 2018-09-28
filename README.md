# TVMarqueeText
用于电视显示和操作的滚动文本控件

#### 使用如下：

     <cn.fengfancky.tvmarqueetext.TVMarqueeTextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="20dp"
        app:mDelayedTime="500"
        app:mSpace="200dp"
        app:mSpeed="0.1"
        app:mTextColor="#FF333333" />
        
#### 开始滚动动画：（当文本长度小于可显示区域时，文本不会滚动）

    tvMarqueeTextView.startMarquee();
    
#### 停止滚动动画：

    tvMarqueeTextView.stopMarquee();
    
#### 动态设置字体颜色：

    tvMarqueeTextView.setmTextColor(getResources().getColor(R.color.colorAccent));
    
#### 动态设置字体大小：

    tvMarqueeTextView.setmTextSize(24);
    
     
#### 自定义属性说明：

    mDelayedTime：动画延后时间，毫秒
    
    mSpace：滚动前后文本间隔距离
    
    mSpeed：滚动文本的速度，float
    
    mTextColor：字体颜色
    
    mTextSize：字体大小
    
    mGravity:文本显示位置，enum；
    
        left,居左；
        right，居右；
        center，居中；
    
