import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

class File_Dialog  implements ActionListener
{
	Frame f1=new Frame();
	Frame ff2=new Frame();
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
	
	

	}

	
		public class WindowCloserNotepad extends WindowAdapter 
		{
			
			public void windowClosing(WindowEvent we1)
			{
				Window w=we1.getWindow();
				
				ff2=new Frame();
				
					
				ff2.setSize(500,100);
				Panel p1=new Panel();
				Label l1=new Label(" DO YOU WANT TO SAVE CHANGES ? ");
				p1.add(l1);
				
				p1.add(S_ave);
				p1.add(not_save);
				ff2.setVisible(true);
				
				ff2.add(p1);
				          
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
			
			add=ff.getDirectory()+ff.getFile();
			//System.out.println(add);
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
				
			}			

		}

		if(e1.getSource()==Find)
		{
			Frame f2=new Frame();

			
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
			String content=ta1.getText();
			this.f1.setAlwaysOnTop(true); 
			content=content.replace("\r","");
			
			System.out.println("length of string is "+content.length()+" and content is "+content);
			Pattern p=Pattern.compile(fword,Pattern.MULTILINE);		
			Matcher m=p.matcher(content);
			boolean t=true;				
			prev=end;
			while(t)
			{
		 		while(m.find(end))
				{	
						
					System.out.println(m.start()+"\t"+m.end()+"\t"+m.group());
					end=m.end();
					next=end;
					System.out.println("start= "+m.start()+"  end= "+end+" and prev= "+prev+" next= "+next);
				
					ta1.select(m.start(),m.end());	
					t=false;						
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

			Frame f2=new Frame();
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
			String content=ta1.getText();
			 
			//content=content.replace("\n","");
			content=content.replace("\r","");
			
			System.out.println("length of string is "+content.length()+" and content is "+content+"\n"+" fword= "+fword);
			Pattern p=Pattern.compile(fword,Pattern.MULTILINE);		
			Matcher m=p.matcher(content);
			boolean t=true;				
			prev=end;
			while(t)
			{
		 		while(m.find(end))
				{	
						
					System.out.println(m.start()+"\t"+m.end()+"\t"+m.group());
					end=m.end();
					next=end;
					System.out.println("start= "+m.start()+"  end= "+end+" and prev= "+prev+" next= "+next);
				
					ta1.select(m.start(),m.end());	
					t=false;						
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

		if(e1.getSource()==replace)
		{ 
			
			 String remove_with=t2.getText();
			String fword=t1.getText();
			String content=ta1.getText();
			System.out.println("length of string is "+content.length()+" and content is "+content+"\n"+" fword= "+fword);
			
			System.out.println(e1.getSource());
			Pattern p=Pattern.compile(fword);		
			Matcher m=p.matcher(content);
			
			ta1.setText(m.replaceFirst(remove_with));
			
			String sell=ta1.getText();
			System.out.println(sell);
			
		}
		if(e1.getSource()==replace_all)
		{
			String remove_with=t2.getText();
			String fword=t1.getText();
			String content=ta1.getText();
			 
			content=content.replace("\r","");
			
			System.out.println("length of string is "+content.length()+" and content is "+content);
			Pattern p=Pattern.compile(fword);		
			Matcher m=p.matcher(content);
			
			ta1.setText(m.replaceAll(remove_with));
			
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
						String content=ta1.getText();
						content=content.replace("\r","");
			
						char cc[]=content.toCharArray();
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
						String content=ta1.getText();
						content=content.replace("\r","");
			
						char cc[]=content.toCharArray();
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
						String content=ta1.getText();
						content=content.replace("\r","");
			
						char cc[]=content.toCharArray();
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

	
						if(e1.getSource()==S_ave)
						{
							
							ff2.setVisible(false);
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
										String content=ta1.getText();
										content=content.replace("\r","");
			
										char cc[]=content.toCharArray();
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
									f1.setVisible(false);
									System.exit(1);

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
								String content=ta1.getText();
								content=content.replace("\r","");
			
								char cc[]=content.toCharArray();
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