package com.blundell.tut.service;

import android.os.AsyncTask;

import com.blundell.tut.widget.TaskWatcher;

/**
 * This is an example of a task that is calling the internet. 
 * **You don't have to use AsyncTasks**, the below just clearly shows how you should hook in the
 * TaskWatcher (SearchTextView) with the lifecycle of your task
 * 
 * @author Blundell
 * 
 */
public class SearchForSomethingTask extends AsyncTask<Void, Void, String> {

	private final TaskWatcher taskWatcher;

	public SearchForSomethingTask(TaskWatcher taskWatcher) {
		this.taskWatcher = taskWatcher;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		taskWatcher.onTaskStarted();
	}

	@Override
	protected String doInBackground(Void... params) {
		try {
			Thread.sleep(5000); // Do some long running internet task with a text result
		} catch (InterruptedException ignore) {
		}
		return "56 million";
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		taskWatcher.onTaskFinished(result);
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		taskWatcher.onTaskCancelled();
	}
}
