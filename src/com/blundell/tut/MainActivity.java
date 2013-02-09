package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.blundell.tut.service.SearchForSomethingTask;
import com.blundell.tut.widget.TaskWatcher;

/**
 * In this application:
 *  we ask for some user input
 *  do an internet task that returns a result
 *  while this internet task is doing its thing the UI is updated by the SearchTextView
 * @author random
 *
 */
public class MainActivity extends Activity {

	private TaskWatcher searchTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		searchTextView = (TaskWatcher) findViewById(R.id.activity_main_text_view_search);
	}

	public void onSearchButtonClick(View button) {
		new SearchForSomethingTask(searchTextView).execute();
	}
}
