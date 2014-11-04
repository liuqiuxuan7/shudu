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
		// ȡ�û�ͼ�������ĵĻ���
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
		
		/*OnSizeChanged����Ļ�����ı��ʱ����ã��ڳ�ʼ��һ����Ļʱ����OnCreat����֮ǰ����
		 *ͨ����д�÷����������ڲ���ȡ��Ļ�Ŀ�ȣ��Ӷ���ȡ��Ϸ�о��εĿ�Ⱥ͸߶�
		 * */
	    @Override
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	    	super.onSizeChanged(w, h, oldw, oldh);
	    	width=w/9f;
	    	height=h/9f;
	    	getRect(selX,selY,selRect);
	    	
	    }

	    //����9*9�ĸ��ӣ�����������ɫ�Ĳ�ͬ��81�������γɾŹ���
	    
		@Override
	    protected void onDraw(Canvas canvas) {
	    	super.onDraw(canvas);
	    	//���Ʊ�����ɫ
	    	Paint backgroundpaint=new Paint();
	    	backgroundpaint.setColor(getResources().getColor(R.color.game_background));
	    	canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundpaint);
	    	
	    	//������ɫ
	    	Paint dark=new Paint();
	    	dark.setColor(getResources().getColor(R.color.puzzle_dark));
	    	Paint light=new Paint();
	        light.setColor(getResources().getColor(R.color.puzzle_light));
	        Paint hilight = new Paint();
			hilight.setColor(getResources().getColor(R.color.puzzle_hilite));
			Paint line = new Paint();
			line.setColor(R.color.line);
			
			//��������
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
			//���ƾŹ���������
			Paint frontPaint=new Paint();
			frontPaint.setColor(Color.RED);
			frontPaint.setTextSize(18);
			for(i=0;i<9;i++){
				for(j=1;j<=9;j++){
					canvas.drawText(puzzle[i][j-1], i*width+(width*0.3f), j*height-(height*0.3f),frontPaint);
				}
			}
			//���ó�ʼ�ľ���(ѡ��ʱ�ı���ε���ɫ)
			/*Paint selPaint=new Paint();
			selPaint.setColor(getResources().getColor(R.color.puzzle_selected));
			canvas.drawRect(selRect,selPaint);*/
			//���÷���
		}
	    
	  //���ݴ����selX��selY�������þ��εģ�������ͳ�ʼΪ��
		private void getRect(float selX2, float selY2, Rect r) {
			r.set((int)(selX*width+1), (int)(selY*height+1), (int)(selX*width+width-1), (int)(selY*height+height-1));	
			
		}
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if(event.getAction()!=MotionEvent.ACTION_DOWN){
				return super.onTouchEvent(event);	
			}
			//���㴥����λ�����ĸ�������
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
		//���¾��εĳ��������½����������
		getRect(selX, selY, selRect);
		//���»��ƾ���
		invalidate(selRect);
		}
		
		public void setSelectTitle(String d){
			int row,col;
			int finishflag=0;
			String info="";
			//1.���ͬһ���Ƿ�����ͬ������
			for(row=0;row<9;row++){
				if (puzzle[selX][row].equals(d)) {
					
					info="ͬһ������ͬ������";
					break;
				}
			}
			
			//���ͬһ���Ƿ�����ͬ������
			for(col=0;col<9;col++){
				if (puzzle[selY].equals(d)) {
					info="ͬһ������ͬ������";
				 break;
				}
			}
			//������ڵľŹ����Ƿ�����ͬ������
			int x=selX/3;
			int y=selY/3;
			for(col = x*3;col<x*3+3;col++)
				for(row=y*3;row<y*3+3;row++){
				
				  if(puzzle[col][row].equals(d))
				  {
					info = "���ڵľŹ�������ͬ����";						
					break;
				  }
				}
			
			//4.�ж�����������Ƿ�Ϊ0�����Ϊ0��������Ϊ�գ����info��Ϊ������ʾinfo����
			  //Ϊ���򽫸þ��ο��puzzle�������������Ϊd�������»���ͼ��
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
				
			//5.����˫��ѭ����֤puzzle�������Ƿ���ڿ�ֵ�� ����������ڽ�finishflag��ֵΪ1
				for(col=0;col<9;col++)
					for(row=0;row<9;row++)
						if(puzzle[col][row].equals(" "))
						{
							finishflag=1;
							info="�Ź�������δ�������";break;
						}
			//6.����finishflag��ֵ�ж���Ϸ�Ƿ���������Ϊ0��ͨ�������������Ϸ
				if(finishflag==0)
				{
					info="��ϲ��ͨ����Ϸ!";
				}
				else info="�������Ϸ!";
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
