package com.ppandroid.ppdemo.widget;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.ppandroid.ppdemo.R;

/**
 * 两张图片对比
 */
public class NewOldImageView extends View {
    public NewOldImageView(Context context) {
        super(context);
        init();
    }


    public NewOldImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewOldImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Bitmap first;
    Bitmap second;
    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速

        first= BitmapFactory.decodeResource(getResources(), R.drawable.first);
        second= BitmapFactory.decodeResource(getResources(), R.drawable.second);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.w=w;
        this.h=h;
    }

    int w,h;
    Paint p=new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 指定图片绘制区域
        Rect srcFirst = new Rect(0,0,first.getWidth(),first.getHeight());
        Rect dstFirst = new Rect(0,0,w,h);
        canvas.drawBitmap(first,srcFirst,dstFirst,p);

        int pp= (int) (second.getWidth()*posX/w);//比例变化
        // 指定图片绘制区域
        Rect srcSecond = new Rect(pp,0,second.getWidth(),second.getHeight());
        Rect dstSecond = new Rect((int) posX,0,w,h);
        canvas.drawBitmap(second,srcSecond,dstSecond,p);

        canvas.drawLine(posX,0,posX,h,getPaint());

    }
    Paint paint;

    private Paint getPaint() {
        if (paint==null){
            paint=new Paint();
            paint.setAntiAlias(true);
            paint.setColor(paintColor);
            paint.setStrokeWidth(flagWidth);
        }
        return paint;
    }
     int paintColor=Color.RED;

    float posX=0;//x轴默认位置
    float flagWidth=10;//指示器宽度
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("yeqinfu","====="+event.getAction()+"===dd====posX"+posX);

        if (event.getAction()==MotionEvent.ACTION_MOVE){//移动
            posX=event.getX();
            Log.d("yeqinfu","============posX"+posX);
            invalidate();

        }
        return true;
    }
}
