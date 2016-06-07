package com.lcy.menu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;

public class slideMenu extends FrameLayout{

	private View menuView,mainView;
	private int menuWidth;
	public slideMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public slideMenu(Context context) {
		super(context);
		init();
	}
 
	private void init(){
		
		
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		menuView=getChildAt(0);
		mainView=getChildAt(1);
		 menuWidth = menuView.getLayoutParams().width;
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		
		switch (ev.getAction()) {
         case MotionEvent.ACTION_UP:
			downX=(int) ev.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			int dx=(int)(ev.getX()-downX);
			if(Math.abs(dx)>8)
			{
				return true;
			}
			break;
			
		
		}
		
		return super.onInterceptTouchEvent(ev);
		
	}
	@Override
	protected void onLayout(boolean arg0, int l, int t, int r, int b) {
		menuView.layout(-menuWidth, 0, 0, b);
		mainView.layout(0, 0, r, b);
		
	}
	private int downX;
	private scrollAnimation animation; 
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX=(int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX=(int) event.getX();
			int dx=moveX-downX;
			int scrollx=getScrollX()-dx;
			if(scrollx<-menuWidth)scrollx=-menuWidth;
			if(scrollx>0)scrollx=0;
			scrollTo(scrollx, 0);
			downX=moveX;
			break;
		case MotionEvent.ACTION_UP:
			if(getScrollX()>-menuWidth/2)
			{
				animation = new scrollAnimation(0, this);
			}else
			{
				animation = new scrollAnimation(-menuWidth, this);
			}
			startAnimation(animation);
			break;

		default:
			break;
		}
		return true;
	}


	
}
