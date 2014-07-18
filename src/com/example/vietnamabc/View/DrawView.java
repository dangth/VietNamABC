package com.example.vietnamabc.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
	private static final float TOUCH_TOLERANCE = 1.0F;
	private Paint mPaint;
	private Path mPath;
	private float mX;
	private float mY;

	public DrawView(Context paramContext) {
		super(paramContext);
		initialze();
	}

	public DrawView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		initialze();
	}

	private void initialze() {
		this.mPaint = new Paint();
		this.mPaint.setAntiAlias(true);
		this.mPaint.setDither(true);
		this.mPaint.setColor(Color.RED);
		this.mPaint.setStyle(Paint.Style.STROKE);
		this.mPaint.setStrokeJoin(Paint.Join.ROUND);
		this.mPaint.setStrokeCap(Paint.Cap.ROUND);
		this.mPaint.setStrokeWidth(12.0F);
		this.mPath = new Path();
	}

	private void touch_move(float paramFloat1, float paramFloat2) {
		float f2 = Math.abs(paramFloat1 - this.mX);
		float f1 = Math.abs(paramFloat2 - this.mY);
		if ((f2 >= 1.0F) || (f1 >= 1.0F)) {
			this.mPath.quadTo(this.mX, this.mY, (paramFloat1 + this.mX) / 2.0F,
					(paramFloat2 + this.mY) / 2.0F);
			this.mX = paramFloat1;
			this.mY = paramFloat2;
		}
	}

	private void touch_start(float paramFloat1, float paramFloat2) {
		this.mPath.moveTo(paramFloat1, paramFloat2);
		this.mX = paramFloat1;
		this.mY = paramFloat2;
	}

	private void touch_up() {
		this.mPath.lineTo(this.mX, this.mY);
	}

	public void clear() {
		this.mPath.reset();
		invalidate();
	}

	protected void onDraw(Canvas paramCanvas) {
		paramCanvas.drawPath(this.mPath, this.mPaint);
	}

	protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) {
		super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
	}

	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		float f1 = paramMotionEvent.getX();
		float f2 = paramMotionEvent.getY();
		switch (paramMotionEvent.getAction()) {
		case 0:
			touch_start(f1, f2);
			invalidate();
			break;
		case 1:
			touch_up();
			invalidate();
			break;
		case 2:
			touch_move(f1, f2);
			invalidate();
		}
		return true;
	}
}
