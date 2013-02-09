package com.blundell.tut.widget;

/**
 * This is the interface for each callback that the SearchTextView needs to complete its lifecycle
 * 
 * @author Blundell
 * 
 */
public interface TaskWatcher {
	void onTaskStarted();

	void onTaskFinished(String result);

	void onTaskCancelled();
}