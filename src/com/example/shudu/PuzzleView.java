package com.example.shudu;



import com.example.shudu.ui.Game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

@SuppressLint("ResourceAsColor")
public class PuzzleView extends View {
	private final Rect selRect = new Rect();
	private Game new_game;
	private int i, j, k = 0;
	private float width, height;
	private int selX, selY;
	public String[][] puzzle = new String[9][9];
	private String easy[][] = new String[][] {
			{ "3", "6", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", "4", "2", "3", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", "4", "2", " ", " " },
			{ " ", "7", " ", "4", "6", " ", " ", " ", "3" },
			{ "8", "2", " ", " ", " ", " ", " ", "1", "4" },
			{ "5", " ", " ", " ", "1", "3", " ", "2", " " },
			{ " ", " ", "1", "9", " ", " ", " ", " ", " " },
			{ " ", " ", "7", " ", "4", "8", "3", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", "4", "5" }, };
	private String medium[][] = new String[][] {
			{ "6", "5", " ", " ", " ", " ", " ", "7", " " },
			{ " ", " ", " ", "5", " ", "6", " ", " ", " " },
			{ " ", "1", "4", " ", " ", " ", " ", " ", "5" },
			{ " ", "7", " ", "4", "6", " ", " ", " ", "3" },
			{ " ", " ", "2", "3", "1", "4", "7", " ", " " },
			{ " ", " ", " ", "7", " ", " ", "8", " ", " " },
			{ "5", " ", " ", " ", " ", " ", "6", "3", " " },
			{ " ", "9", " ", "3", " ", "1", " ", "8", " " },
			{ " ", " ", " ", " ", " ", " ", "6", " ", " " }, };
	private String hard[][] = new String[][] {
			{ "1", "2", "3", "4", "5", "6", "7", "8", "9" },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " " }, };

	public PuzzleView(Context context, int diff) {
		super(context);
		// 取得绘图的上下文的环境
		new_game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		for (i = 0; i < 9; i++) 
			for (j = 0; j < 9; j++) {
				switch (diff) {
				case 0:
					puzzle[i][j] = easy[i][j];

					break;

				case 1:
					puzzle[i][j] = medium[i][j];

					break;
				case 2:
					puzzle[i][j] = hard[i][j];
					break;

				case -1:
					puzzle[i][j]=new_game.continueString.substring(k,k+1);
					System.out.print(puzzle[i][j]);
					k++;
					break;
				}
			}
		}
		
		/*OnSizeChanged在屏幕发生改变的时候调用，在初始化一个屏幕时，在OnCreat方法之前调用
		 *通过重写该方法，在其内部获取屏幕的宽度，从而获取游戏中矩形的宽度和高度
		 * */
	    @Override
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	    	super.onSizeChanged(w, h, oldw, oldh);
	    	width=w/9f;
	    	height=h/9f;
	    	getRect(selX,selY,selRect);
	    	
	    }

	    //绘制9*9的格子，利用线条颜色的不同把81个格子形成九宫格
	    
		@Override
	    protected void onDraw(Canvas canvas) {
	    	super.onDraw(canvas);
	    	//绘制背景颜色
	    	Paint backgroundpaint=new Paint();
	    	backgroundpaint.setColor(getResources().getColor(R.color.game_background));
	    	canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundpaint);
	    	
	    	//定义颜色
	    	Paint dark=new Paint();
	    	dark.setColor(getResources().getColor(R.color.puzzle_dark));
	    	Paint light=new Paint();
	        light.setColor(getResources().getColor(R.color.puzzle_light));
	        Paint hilight = new Paint();
			hilight.setColor(getResources().getColor(R.color.puzzle_hilite));
			Paint line = new Paint();
			line.setColor(R.color.line);
			
			//绘制线条
			for(i=0;i<=9;i++){
				canvas.drawLine(0, i*height, getWidth(),i*height, light);
				//canvas.drawLine(0, i*height+1, getWidth(),i*height+1, hilight);
				canvas.drawLine(i*width,0 , i*width, getHeight(), light);
				
			}
			for(i=0;i<=9;i=i+3){
				canvas.drawLine(0, i*height,getWidth(),i*height,line);
				canvas.drawLine(0, i*height+1, getWidth(),i*height+1,line);
				canvas.drawLine(i*width, 0,i*width,getHeight(), line);
				canvas.drawLine(i*width+1,0,i*width+1,getHeight(),line);
	    }
			//绘制九宫格内数字
			Paint frontPaint=new Paint();
			frontPaint.setColor(Color.RED);
			frontPaint.setTextSize(18);
			for(i=0;i<9;i++){
				for(j=1;j<=9;j++){
					canvas.drawText(puzzle[i][j-1], i*width+(width*0.3f), j*height-(height*0.3f),frontPaint);
				}
			}
			//设置初始的矩形(选中时改变矩形的颜色)
			/*Paint selPaint=new Paint();
			selPaint.setColor(getResources().getColor(R.color.puzzle_selected));
			canvas.drawRect(selRect,selPaint);*/
			//调用方法
		}
	    
	  //根据传入的selX和selY重新设置矩形的，长、宽和初始为主
		private void getRect(float selX2, float selY2, Rect r) {
			r.set((int)(selX*width+1), (int)(selY*height+1), (int)(selX*width+width-1), (int)(selY*height+height-1));	
			
		}
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if(event.getAction()!=MotionEvent.ACTION_DOWN){
				return super.onTouchEvent(event);	
			}
			//计算触屏的位置在哪个矩形上
			int x=(int)(event.getX()/width);
			int y=(int)(event.getY()/height);
			selectXY(x,y);
			new_game.showKeyPad();
			return false;
			
		}

		private void selectXY(int x, int y) {
		invalidate(selRect);
		if(y==-1){
			selY=8;
		}else if(y==9){
			selY=0;	
		}else selY=y;
		
		if(x==-1) {selX=8;
		}else if(x==9){selX=0;
		}else selX=x;
		//更新矩形的长。宽。左下角坐标等属性
		getRect(selX, selY, selRect);
		//重新绘制矩形
		invalidate(selRect);
		}
		
		public void setSelectTitle(String d){
			int row,col;
			int finishflag=0;
			String info="";
			//1.检查同一列是否有相同的数字
			for(row=0;row<9;row++){
				if (puzzle[selX][row].equals(d)) {
					
					info="同一列有相同的数字";
					break;
				}
			}
			
			//检查同一行是否有相同的数字
			for(col=0;col<9;col++){
				if (puzzle[selY].equals(d)) {
					info="同一行有相同的数字";
				 break;
				}
			}
			//检查所在的九宫格是否有相同的数字
			int x=selX/3;
			int y=selY/3;
			for(col = x*3;col<x*3+3;col++)
				for(row=y*3;row<y*3+3;row++){
				
				  if(puzzle[col][row].equals(d))
				  {
					info = "所在的九宫格有相同数字";						
					break;
				  }
				}
			
			//4.判断输入的数字是否为0，如果为0则将其重置为空，如果info不为空则显示info内容
			  //为空则将该矩形框的puzzle数组的内容重置为d。并重新绘制图形
				if(info.equals(""))
				{
					if(d.equals("0")) 
						d ="";
					puzzle[selX][selY] = d;
					invalidate(selRect);
				}
				else
				{
				Toast.makeText(this.new_game.getApplicationContext(), info, Toast.LENGTH_SHORT).show();
				}
				
			//5.利用双重循环验证puzzle数组内是否存在空值” ”，如果存在将finishflag赋值为1
				for(col=0;col<9;col++)
					for(row=0;row<9;row++)
						if(puzzle[col][row].equals(" "))
						{
							finishflag=1;
							info="九宫格内有未填的数字";break;
						}
			//6.根据finishflag的值判断游戏是否结束，如果为0则通过，否则继续游戏
				if(finishflag==0)
				{
					info="恭喜您通过游戏!";
				}
				else info="请继续游戏!";
			}
			
			public boolean onKeyDown(int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				switch(keyCode){
				case KeyEvent.KEYCODE_DPAD_UP:
					selectXY(selX,selY-1);
					break;
				
				default:
					return super.onKeyDown(keyCode, event);
				}
				return true;
			}

}
