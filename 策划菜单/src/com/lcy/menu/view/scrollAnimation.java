package com.lcy.menu.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class scrollAnimation extends Animation
{
	private int targetX;
	private View view;
	private int totleV;
    private int startX;
	

	public scrollAnimation(int targetX, View view) {
		super();
		this.targetX = targetX;
		this.view = view;
		startX=view.getScrollX();
		totleV=this.targetX-startX;
	    setDuration(400);
	}




	@Override
	protected void applyTransformation(float interpolatedTime,
			Transformation t) {
		super.applyTransformation(interpolatedTime, t);
		int currentX=(int) (startX+totleV*interpolatedTime);
		view.scrollTo(currentX, 0);
	}
	
}
