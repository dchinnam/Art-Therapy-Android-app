package com.example.deepank.art_therapy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;
import android.content.Context;
import android.widget.Button;

import java.text.AttributedCharacterIterator;

public class TouchEventView extends View{

    public Paint paint= new Paint();
    public  Path  path =new Path();


    public TouchEventView(Context context,AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        this.setBackgroundColor(Color.GREEN);



    }

    @Override
    protected void onDraw( Canvas canvas){



        canvas.drawPath(path, paint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float xPos=event.getX();
        float yPos=event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: path.moveTo(xPos,yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos,yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;

        }
        //scedule repaint
        invalidate();
        return true;
    }

    public void clear ()
    {
        path = new Path();
        invalidate();
    }



}

