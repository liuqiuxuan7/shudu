package com.example.shudu.ui;

import com.example.shudu.Prefs;
import com.example.shudu.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button btnBegin, btnAbout, btnContinue, btnExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findview();

	}

	private void findview() {
		btnBegin = (Button) findViewById(R.id.btn_begin);
		btnAbout = (Button) findViewById(R.id.btn_about);
		btnContinue = (Button) findViewById(R.id.btn_continue);
		btnExit = (Button) findViewById(R.id.btn_exit);

		btnBegin.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
		btnContinue.setOnClickListener(this);
		btnExit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_begin:
           openNewGame();
			break;
		case R.id.btn_about:
       Intent intent=new Intent(MainActivity.this,AboutActivity.class);
       startActivity(intent);
			break;
		case R.id.btn_continue:
             startNewGame(-1);
			break;
		case R.id.btn_exit:
			exitAlert("真的要退出吗？");

			break;
		default:
			break;
		}
	}

	private void openNewGame() {
		AlertDialog.Builder new_game=new AlertDialog.Builder(this);
		final String study[]={"容易","中等","困难"};
		new_game.setTitle("请选择难度");
		new_game.setItems(study, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Toast.makeText(MainActivity.this, study[arg1], Toast.LENGTH_LONG).show();
				startNewGame(arg1);
				
			}
		});
		AlertDialog alert=new_game.create();
		alert.show();
	}
	
	private void startNewGame(int diff){
		Intent intent=new Intent(MainActivity.this,Game.class);
		intent.putExtra("difficulty", diff);
		startActivity(intent);
	}
    //显示对话框
	private void exitAlert(String string) {
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setMessage(string).setCancelable(false)
		.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
				
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				return;
			}
		});
		
		AlertDialog alert=builder.create();
		alert.show();		
	}
	/*从xml定义的菜单资源中生成一个菜单*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 super.onCreateOptionsMenu(menu);
		 MenuInflater inflater=getMenuInflater();
		 inflater.inflate(R.menu.main_conture, menu);
		 return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.setting:
			Intent intent=new Intent(this,Prefs.class);
					startActivity(intent);		
			break;
		case R.id.honor:
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return false;
		
		
	}
	
	
}
