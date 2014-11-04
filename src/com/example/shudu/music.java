package com.example.shudu;

import android.content.Context;
import android.media.MediaPlayer;

public class music {
	private static MediaPlayer mp=null;
	private static MediaPlayer mSound=null;
	
	/*控制启动音乐播放，context是音乐播放时的上下文环境，
	 * resources是要播放的音乐资源文件*/
	
	public static void play(Context context,int resources){
		stop(context);//播放指定音乐之前先把上下文任意多媒体停止
		if(Prefs.getBackMusic(context)){
			mp=MediaPlayer.create(context, resources);
			//设置是否循环播放
			mp.setLooping(true);
			mp.start();			
		}		
	}
    
	//停止音乐播放
	public static void stop(Context context) {
		if(mp!=null){
			mp.stop();
			mp.release();
			mp=null;
		}	
	}
	//播放音乐
	private static void playSound(Context context,int resources){
		stop(context);
		if(Prefs.getSoundSet(context)){
				mSound=MediaPlayer.create(context, resources);
			mSound.start();
		}	
	}	
}
