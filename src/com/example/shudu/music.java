package com.example.shudu;

import android.content.Context;
import android.media.MediaPlayer;

public class music {
	private static MediaPlayer mp=null;
	private static MediaPlayer mSound=null;
	
	/*�����������ֲ��ţ�context�����ֲ���ʱ�������Ļ�����
	 * resources��Ҫ���ŵ�������Դ�ļ�*/
	
	public static void play(Context context,int resources){
		stop(context);//����ָ������֮ǰ�Ȱ������������ý��ֹͣ
		if(Prefs.getBackMusic(context)){
			mp=MediaPlayer.create(context, resources);
			//�����Ƿ�ѭ������
			mp.setLooping(true);
			mp.start();			
		}		
	}
    
	//ֹͣ���ֲ���
	public static void stop(Context context) {
		if(mp!=null){
			mp.stop();
			mp.release();
			mp=null;
		}	
	}
	//��������
	private static void playSound(Context context,int resources){
		stop(context);
		if(Prefs.getSoundSet(context)){
				mSound=MediaPlayer.create(context, resources);
			mSound.start();
		}	
	}	
}
