import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

class File_Dialog  implements ActionListener
{
	Frame f1=new Frame();  //  main frame 
	Frame f2=new Frame();
	Frame ff2=new Frame();
	int fcount=0;
	MenuBar m1=new MenuBar();
	Menu files=new Menu("FILES");
	Menu Edit=new Menu("EDIT");
	TextField t1;
	Button b1;
	Button b2;
	Window closee;
	Button  S_ave=new Button("save changes");	
	Button not_save=new Button("Don't save ");
	TextField t2;
		Button replace;
			Button replace_all;

		int prev=0,next=0;
			int end=0;
		String content;
		String new_content;

		String add=null;

	//EDIT MENU_ITEMS
		
	MenuItem Find=new MenuItem("Find");
	MenuItem Find_and_Replace=new MenuItem("Find and Replace");

	//FILES MENU_ITEMS 
	MenuItem New=new MenuItem("NEW");
	MenuItem OPEN=new MenuItem("OPEN");
	MenuItem SAVE_AS=new MenuItem("SAVE AS");
	MenuItem Save=new MenuItem("SAVE");
		
	TextArea ta1=new TextArea("",40,40,TextArea.SCROLLBARS_BOTH);
	int StartingIndex = 0;
	int EndingIndex = 0;
	
	File_Dialog()
	{

		f1.setSize(400,400);
		WindowCloserNotepad w=new WindowCloserNotepad();
		f1.addWindowListener(w);		
		f1.add(ta1);
		
			 t1=new TextField(30);
			 b1=new Button("FIND");
			 b2=new Button("CANCEL");
			b1.addActionListener(this);
			b2.addActionListener(this);
			S_ave.addActionListener(this);
			not_save.addActionListener(this);
			
		New.addActionListener(this);	
		OPEN.addActionListener(this);
		SAVE_AS.addActionListener(this);
		Save.addActionListener(this);		

		//ADDING FILES MENU_ITEMS
		files.add(New);
		files.add(OPEN);
		files.add(SAVE_AS);	
		files.add(Save);

		//ADDING EDIT MENU_ITEMS
		Edit.add(Find);
		Edit.add(Find_and_Replace);
		
		Find.addActionListener(this);
		Find_and_Replace.addActionListener(this);

		m1.add(files);
		m1.add(Edit);

		f1.setMenuBar(m1);
			f1.setVisible(true);
			 MyKeyListener key=new MyKeyListener();

		
		ta1.addKeyListener(key);
		

	}

	
		public class WindowCloserNotepad extends WindowAdapter 
		{
			
			public void windowClosing(WindowEvent we1)
			{
				System.out.println("content under windowClosingNotepad is  "+content+" and new_content is "+new_content);

				if(add==null)
				{
						
					Window w=we1.getWindow();
					//w.setVisible(false);
					ff2.setAlwaysOnTop(true);
					ff2=new Frame();
					WindowCloser op=new WindowCloser();
					ff2.setSize(500,100);
					Panel p1=new Panel();
					Label l1=new Label(" DO YOU WANT TO SAVE CHANGES ? ");
					p1.add(l1);
					
					p1.add(S_ave);
					p1.add(not_save);
					ff2.setVisible(true);
					ff2.setAlwaysOnTop(true);
					ff2.addWindowListener(op);
					
					ff2.add(p1);
					//destroy's frame
				}
				else if(!(content.equals(new_content)) && new_content!=null)
				{
					Window w=we1.getWindow();
					//w.setVisible(false);
					ff2.setAlwaysOnTop(true);
					ff2=new Frame();
					WindowCloser op=new WindowCloser();
					ff2.setSize(500,100);
					Panel p1=new Panel();
					Label l1=new Label(" DO YOU WANT TO SAVE CHANGES ? ");
					p1.add(l1);
					
					p1.add(S_ave);
					p1.add(not_save);
					ff2.setVisible(true);
					ff2.setAlwaysOnTop(true);
					ff2.addWindowListener(op);
					
					ff2.add(p1);
					//destroy's frame

				}
				else
				{
						System.exit(1);
				}
				if( new_content==null)
				{
					System.exit(1);
				}
			}  
		
		}
		public class MyKeyListener extends Frame implements KeyListener 
		{
			public void keyPressed(KeyEvent e) 
			{    
    			}   
			 public void keyReleased(KeyEvent e)
			{	
			}
				
			public void keyTyped(KeyEvent e)
			{
				System.out.println("some key is pressed");   

				new_content=ta1.getText(); 	
		
			}
			
		}
		
	
	public void actionPerformed(ActionEvent e1)
	{
		FileDialog ff=null;
		WindowCloser ww=new WindowCloser();
		ff2.addWindowListener(ww);

		if(e1.getSource()==New)
		{
			ta1.setText("");
			add=null;
		}
		if(e1.getSource()==OPEN)
		{
			 ff=new FileDialog(f1,"OPEN dialogbox");
			ff.setFile("*.txt");
 			ff.setVisible(true);
			if(ff.getDirectory()!=null)
			{
				add=ff.getDirectory()+ff.getFile();
			}
			System.out.println("address under open is "+add);
			if(add!=null)
			{
				System.out.println(add);
				try
				{
					FileInputStream fis=new FileInputStream(add);
					int ch=0;
					char c;
					String pp="";
					while((ch=fis.read())!=-1)
					{
						pp=pp+(char)ch;
					}
					ta1.setText(pp);
				}
				catch(Exception e)
				{

				}
				content=ta1.getText();
			}			
			if(add=="nullnull")
			{
				ta1.setText("");
			}
					
		}

		if(e1.getSource()==Find)
		{
			f2.dispose();
			f2=new Frame();
			//f2.setAlwaysOnTop(false);
			f2.setAlwaysOnTop(true);			
			f2.setSize(300,200);
			Label l1=new Label("Enter word below which you want to find ");
			WindowCloser w=new WindowCloser();
			Panel p1=new Panel();
			f2.addWindowListener(w);
			p1.add(l1); p1.add(t1);  p1.add(b1); p1.add(b2);		
			f2.add(p1);
		
			f2.setVisible(true);			
		}
		
		if(e1.getSource()==b1)
		{
			
			this.f1.setAlwaysOnTop(false); 
			String fword=t1.getText();
			 new_content=ta1.getText();		
			//this.f1.setAlwaysOnTop(true); 
			f2.setAlwaysOnTop(true);
			new_content=new_content.replace("\r","");
			
			System.out.println("length of string is "+new_content.length()+" and new_content is "+new_content);
			Pattern p=Pattern.compile(fword);		
			Matcher m=p.matcher(new_content);
			boolean t=true;				
			prev=end;
			while(t)
			{
				if(!new_content.contains(fword))
				{
					t=false;
				}
				
		 		while(m.find(end))
				{	
					StartingIndex = m.start();
					EndingIndex = m.end();
					System.out.println("starting index is "+ StartingIndex + " and ending index is " + EndingIndex );
					System.out.println(m.start()+"\t"+m.end()+"\t"+m.group());
					end=m.end();
					next=end;
					System.out.println("start= "+m.start()+"  end= "+end+" and prev= "+prev+" next= "+next);
					this.f1.setAlwaysOnTop(true); 
					ta1.select(m.start(),m.end());	
					t=false;	
					this.f1.setAlwaysOnTop(false); 
					f2.setAlwaysOnTop(true);					
					break;
				}
				if(prev==next)
				{
					System.out.println("terminateddddd");
					end=0;
					
					continue;
				}	
			}		
		}

		if(e1.getSource()==Find_and_Replace)
		{
			this.f2.dispose();
			this.f2=new Frame();
			t1.setText("");
			f2.setSize(350,200);
			Label l1=new Label("Enter word below which you want to find and replace");
			WindowCloser w=new WindowCloser();
			 replace=new Button("Replace");
			 replace_all=new Button("Replace ALL");
			replace.addActionListener(this);
			replace_all.addActionListener(this);		
			
			Label l2=new Label("FIND ::");
			Label l3=new Label("REPLACE WITH :: ");
			t2=new TextField(20);
			Panel p1=new Panel();
			
			f2.addWindowListener(w);
			p1.add(l1); p1.add(l2);p1.add(t1); p1.add(l3); p1.add(t2); p1.add(b1); p1.add(replace); p1.add(replace_all);p1.add(b2);		
			f2.add(p1);
		
			f2.setVisible(true);		

			System.out.println(e1.getSource()+"  is clicked");		
			String fword=t1.getText();
			 new_content=ta1.getText();
			 
			new_content=new_content.replace("\r","");
			
			System.out.println("length of string is "+new_content.length()+" and new_content is "+new_content+"\n"+" fword= "+fword);
		}

		if(e1.getSource()==replace)
		{ 
			String remove_with=t2.getText();
	
			String fword=t1.getText();
			 new_content=ta1.getText();
			System.out.println("length of string is "+new_content.length()+" and new_content is "+new_content+"\n"+" fword= "+fword);
			StringBuffer buf = new StringBuffer(new_content);
      			 buf.replace(StartingIndex, EndingIndex, remove_with); 
			System.out.println(e1.getSource());
			Pattern p=Pattern.compile(fword);		
			Matcher m=p.matcher(new_content);
			ta1.setText(buf.toString());
			new_content=ta1.getText();
			String sell=ta1.getText();
			System.out.println(sell);	
		}
		if(e1.getSource()==replace_all)
		{
			String remove_with=t2.getText();
			String fword=t1.getText();
			new_content=ta1.getText();
			 
			new_content=new_content.replace("\r","");
			
			System.out.println("length of string is "+new_content.length()+" and new_content is "+new_content);
			Pattern p=Pattern.compile(fword);		
			Matcher m=p.matcher(new_content);
			
			ta1.setText(m.replaceAll(remove_with));
			new_content=ta1.getText();
		}
	
		if(e1.getActionCommand()=="SAVE AS")
		{
			System.out.println(e1.getSource());
				 ff=new FileDialog(f1,"OPEN dialogbox",FileDialog.SAVE);
					ff.setFile("*.txt");
 				ff.setVisible(true);
				
				if(ff.getDirectory()!=null)
				{
				 	add=ff.getDirectory()+ff.getFile();
				}
				if(add!=null)
				{
				
				
					System.out.println("address in save as "+add);
					try
					{
						File f1=new File(add);
						f1.createNewFile();	
						FileOutputStream FOS=new FileOutputStream(f1);
						 new_content=ta1.getText();
						new_content=new_content.replace("\r","");
			
						char cc[]=new_content.toCharArray();
						RandomAccessFile RAF=new RandomAccessFile(f1,"rw");
						RAF.seek(0);
						int i=0;
						while(i<cc.length)
						{
							FOS.write(cc[i]);
							i++;
						}
					
					}
					catch(Exception e)
					{
		
					}
				}
				
		}

		if(e1.getSource()==Save)
		{
			if(add==null)
			{
				 ff=new FileDialog(f1,"OPEN dialogbox",FileDialog.SAVE);
					ff.setFile("*.txt");
 				ff.setVisible(true);
				
				if(ff.getDirectory()!=null)
				{
				 	add=ff.getDirectory()+ff.getFile();
				}
				if(add!=null)
				{
				
				
					System.out.println("address in save as "+add);
					try
					{
						File f1=new File(add);
						f1.createNewFile();	
						FileOutputStream FOS=new FileOutputStream(f1);
						new_content=ta1.getText();
						new_content=new_content.replace("\r","");
			
						char cc[]=new_content.toCharArray();
						RandomAccessFile RAF=new RandomAccessFile(f1,"rw");
						RAF.seek(0);
						int i=0;
						while(i<cc.length)
						{
							FOS.write(cc[i]);
							i++;
						}
					
					}
					catch(Exception e)
					{
		
					}
				}
		
				
			}
			if(add!=null)
			{
				System.out.println("their is some addresss "+add);
		
					try
					{
						File f1=new File(add);
						f1.createNewFile();	
						FileOutputStream FOS=new FileOutputStream(f1);
						new_content=ta1.getText();
						new_content=new_content.replace("\r","");
			
						char cc[]=new_content.toCharArray();
						RandomAccessFile RAF=new RandomAccessFile(f1,"rw");
						RAF.seek(0);
						int i=0;
						while(i<cc.length)
						{
							FOS.write(cc[i]);
							i++;
						}
					
					}
					catch(Exception e)
					{
		
					}
				
			}
			content=new_content;
		}

						if(e1.getSource()==S_ave)
						{
							
							ff2.setVisible(false);
							ff2.dispose();
							if(add==null)
							{
								 ff=new FileDialog(f1,"OPEN dialogbox",FileDialog.SAVE);
								ff.setFile("*.txt");
 								ff.setVisible(true);
				
								if(ff.getDirectory()!=null)
								{
								 	add=ff.getDirectory()+ff.getFile();
								}
								if(add!=null)
								{
				
				
									System.out.println("address in save as "+add);
									try
									{
										File f1=new File(add);
										f1.createNewFile();	
										FileOutputStream FOS=new FileOutputStream(f1);
										new_content=ta1.getText();
										new_content=new_content.replace("\r","");
			
										char cc[]=new_content.toCharArray();
										RandomAccessFile RAF=new RandomAccessFile(f1,"rw");
										RAF.seek(0);
										int i=0;
										while(i<cc.length)
										{
											FOS.write(cc[i]);
											i++;
										}
								
									}
									catch(Exception e)
									{
		
									}
									
								}
							}
		
				
						
						    if(add!=null)
						    {
							System.out.println("their is some addresss "+add);
				
							try
							{
								File f1=new File(add);
								f1.createNewFile();	
								FileOutputStream FOS=new FileOutputStream(f1);
								new_content=ta1.getText();
								new_content=new_content.replace("\r","");
			
								char cc[]=new_content.toCharArray();
								RandomAccessFile RAF=new RandomAccessFile(f1,"rw");
								RAF.seek(0);
								int i=0;
								while(i<cc.length)
								{
									FOS.write(cc[i]);
									i++;
								}
								
							}
							
							catch(Exception e)
							{
		
							}	
								
									
						 }
						this.f1.setVisible(false);
									this.f1.dispose();	
					}
					if(e1.getSource()==not_save)	
					{
			
						System.exit(1);
							
					}	
		
	}

}


class dialog
{
	public static void main(String g[])
	{
		File_Dialog f=new File_Dialog();
	}
}
