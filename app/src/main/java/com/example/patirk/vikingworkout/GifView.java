package com.example.patirk.vikingworkout;

import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class GifView extends View{

	private InputStream gifInputStream;
	private Movie gifMovie;
	private int movieWidth, movieHeight;
	private long movieDuration;
	private long movieStart;
	
	public GifView(Context context) {
		super(context);
		init(context);
	}

	
	public GifView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public GifView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}
	
	private void init(Context context) {
		setFocusable(true);
		gifInputStream = context.getResources().openRawResource(R.raw.commandos);
		
		gifMovie = Movie.decodeStream(gifInputStream);
		movieWidth = gifMovie.width();
		movieHeight = gifMovie.height();
		movieDuration = gifMovie.duration();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(movieWidth+450, movieHeight+200);
	}
	
	public int getMovieWidth() {
		return movieWidth;
	}
	
	public int getMovieHeight() {
		return movieHeight;
	}
	
	public long getMovieDuration() {
		return movieDuration;
	}

	public void setGifMovie(Movie gif){
		gifMovie = gif;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		long now = SystemClock.uptimeMillis();
		
		if(movieStart == 0) {
			movieStart = now;
		}
		
		if(gifMovie != null) {
			
			int dur = gifMovie.duration();
			if(dur == 0) {
				dur = 10000;
			}
			
			int relTime = (int)((now - movieStart) % dur);
			
			gifMovie.setTime(relTime);
			WindowManager window = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
			int sizeX = window.getDefaultDisplay().getWidth();
			float resize = convertDpToPixel(((float)sizeX), getContext());
			float scaleIndex = ((float)sizeX) / ((float)getMovieWidth());
			canvas.scale(scaleIndex,scaleIndex);
			gifMovie.draw(canvas, 0, 0);
			invalidate();
		}
	}
	public static float convertDpToPixel(float dp, Context context){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}
}
