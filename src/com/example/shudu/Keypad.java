package com.example.shudu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class Keypad extends Dialog implements OnClickListener{
	private PuzzleView fatherView;
	private Button[] key=new Button[9];
	public Keypad(Context context, PuzzleView father) {
		super(context);
		fatherView=father;
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("–°º¸≈Ã ‰»Î");
		setContentView(R.layout.keypad);
		findButtonId();
		for(int i=0;i<9;i++){
			key[i].setOnClickListener(this);
		}
	}
	private void findButtonId(){
		key[0]=(Button)findViewById(R.id.keypad_1);
		key[1]=(Button)findViewById(R.id.keypad_2);
		key[2]=(Button)findViewById(R.id.keypad_3);
		key[3]=(Button)findViewById(R.id.keypad_4);
		key[4]=(Button)findViewById(R.id.keypad_5);
		key[5]=(Button)findViewById(R.id.keypad_6);
		key[6]=(Button)findViewById(R.id.keypad_7);
		key[7]=(Button)findViewById(R.id.keypad_8);
		key[8]=(Button)findViewById(R.id.keypad_9);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.keypad_1:
			fatherView.setSelectTitle("1");break;
		case R.id.keypad_2:
			fatherView.setSelectTitle("2");break;
		case R.id.keypad_3:
			fatherView.setSelectTitle("3");break;
		case R.id.keypad_4:
			fatherView.setSelectTitle("4");break;
		case R.id.keypad_5:
			fatherView.setSelectTitle("5");break;
		case R.id.keypad_6:
			fatherView.setSelectTitle("6");break;
		case R.id.keypad_7:
			fatherView.setSelectTitle("7");break;
		case R.id.keypad_8:
			fatherView.setSelectTitle("8");break;
		case R.id.keypad_9:
			fatherView.setSelectTitle("9");break;
		default:
			fatherView.setSelectTitle(" ");break;			
		}
		dismiss();
	}

}
