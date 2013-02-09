package com.blundell.tut.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.*;
import android.widget.TextView;

import java.util.Random;

/**
 * This TextView will react to a long running task and show a jumbled set of characters on the UI
 * When the task has finished it will animate and display the result.
 * 
 * You can customise the feel of this by changing:
 * WORD_SPEED this is the rate it changes from jumbled word to word
 * WORD_LENGTH this is the number of characters in each jumbled word
 * 
 * @author Blundell
 *
 */
public class SearchTextView extends TextView implements TaskWatcher {

	private static final int WORD_SPEED = 75;
	private static final int WORD_LENGTH = 10;

	private static final char[] items = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	private static final int datasetSize = items.length;
	private final Random random;
	private final char[] word;

	public SearchTextView(Context context) {
		this(context, null, 0);
	}

	public SearchTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SearchTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		random = new Random();
		word = new char[WORD_LENGTH];
	}

	@Override
	public void onTaskStarted() {
		startToJumbleWord();
	}

	private void startToJumbleWord() {
		handler.post(runnable);
	}

	private final Handler handler = new Handler();
	private final Runnable runnable = new Runnable() {
		@Override
		public void run() {
			generateJumbledWord();
			setText(word, 0, WORD_LENGTH);
			handler.postDelayed(runnable, WORD_SPEED);
		}
	};

	private void generateJumbledWord() {
		for (int i = 0; i < WORD_LENGTH; i++) {
			for (int x = 0; x < datasetSize; x++) {
				word[i] = items[random.nextInt(datasetSize)];
			}
		}
	}

	@Override
	public void onTaskFinished(String result) {
		stopJumblingWord();
		setText(result);
		startFinishedAnimation();
	}

	private void stopJumblingWord() {
		handler.removeCallbacks(runnable);
	}

	private void startFinishedAnimation() {
		Animation shake = new TranslateAnimation(0, 5, 0, 0);
		shake.setInterpolator(new CycleInterpolator(5));
		shake.setDuration(300);
		startAnimation(shake);
	}

	@Override
	public void onTaskCancelled() {
		stopJumblingWord();
	}

}
