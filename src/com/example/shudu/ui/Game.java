package com.example.shudu.ui;


import com.example.shudu.Keypad;
import com.example.shudu.PuzzleView;
import com.example.shudu.R;
import com.example.shudu.music;


import android.app.Activity;


import android.os.Bundle;

public class Game extends Activity {
	protected PuzzleView puzzleview;
	public String continueString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		continueString=getPreferences(MODE_PRIVATE).getString("puzzle", "");
		int diff=getIntent().getIntExtra("difficulty", 0);
		
		puzzleview=new PuzzleView(this,diff);
		//setContentView就是设置一个Activity的显示界面,设置该Activity的显示界面为puzzleview
		setContentView(puzzleview);
		//请求puzzleview获得焦点
		puzzleview.requestFocus();
	}
	public void showKeyPad(){
		Keypad keypad=new Keypad(this, puzzleview);
		keypad.show();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();	
		continueString=getPreferences(MODE_PRIVATE).getString("puzzle", "");
		music.play(this, R.raw.yue);
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		music.stop(this);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		getPreferences(MODE_PRIVATE).edit().putString("puzzle", arraytoString()).commit();
		music.stop(this);
	}
	/* 
	 * 把 puzzle数组中的9*9字符串
	 * */
	private String arraytoString(){
		String s1="";
		int j,k;
		for(j=0;j<9;j++)
			for(k=0;k<9;k++){
				s1+=puzzleview.puzzle[j][k];
			}
		return s1;
	}
}
