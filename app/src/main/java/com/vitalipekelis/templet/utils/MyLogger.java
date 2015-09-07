/**
 * 
 */
package com.vitalipekelis.templet.utils;

import android.util.Log;

/**
 * 
 * Helper Class to post logs to device logcat
 * @author Galit.Feller
 *
 */
public class MyLogger {

	/**
	 * True if to enable debug type logs
	 */
	private static final boolean ENABLE_LOGS = true;
	
	/**
	 * True if to enable error type logs
	 */
	private static final boolean ENABLE_ERROR_LOGS = true;

	/**
	 *  post debug type logs to device logcat
	 * @param tag - represents "Tag" field in device logcat. Usually - class/function name.
	 * @param msg - represents "Text" field in device logcat. Can't be null
	 */
	public static void print(String tag, String msg) {

		if (ENABLE_LOGS && msg != null) {
			
			if(tag == null){
				tag = "";
			}

			String postMsg = "  ==========> " + msg;

			Log.d(tag, postMsg);
		}
	}

	/**
	 *  post debug type logs to device logcat with empty tag
	 * @param msg - represents "Text" field in device logcat. Can't be null
	 */
	public static void print(String msg) {

		print("", msg);
	}

	public static void printError(String tag, String error) {

		if (ENABLE_ERROR_LOGS && error != null) {
			
			if(tag == null){
				tag = "";
			}

			String postMsg = " !!!  ==========> " + error;

			Log.e(tag, postMsg);
		}
	}

	public static void printlongInfo(String tag, String str) {
		if(str.length() > 4000) {
			print(tag, str.substring(0, 4000));
			printlongInfo(str.substring(4000), tag);
		} else
			print(tag, str);
	}
}
