import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.*;

public class JavaIDE extends JFrame implements ActionListener {

	JTextArea jta,jtaConsole,ln_jta;
	JTabbedPane jtp;
	JScrollPane jsp,jspConsole;
	JMenu mnuFile,mnuEdit,mnuBuild,mnuCloseTab;
	JMenuBar mbar;
	JMenuItem mitNew,mitOpen,mitSave,mitSaveas,mitExit,mitCut,mitCopy,mitPaste,mitFind,mitReplace,mitCompile,mitRun,mitCurrenttab,mitAlltab;
	File curFile;
	JSplitPane sp;
	JavaIDE()
	{
		mnuFile=new JMenu("File");
		mitNew=new JMenuItem("New");
		mitOpen=new JMenuItem("Open...");
		mitSave=new JMenuItem("Save");
		mitSaveas=new JMenuItem("Save As");
		mitExit=new JMenuItem("Exit");
		
		mnuFile.setMnemonic(KeyEvent.VK_F);
		mnuFile.add(mitNew);
		mnuFile.add(mitOpen);
		mnuFile.add(mitSave);
		mnuFile.add(mitSaveas);
		mnuFile.addSeparator();
		mnuFile.add(mitExit);
		mitNew.setMnemonic(KeyEvent.VK_N);
		mitOpen.setMnemonic(KeyEvent.VK_O);
		mitSave.setMnemonic(KeyEvent.VK_S);
		mitSaveas.setMnemonic(KeyEvent.VK_A);
		mitExit.setMnemonic(KeyEvent.VK_X);

		
		mnuEdit=new JMenu("Edit");
		mitCut=new JMenuItem("Cut");
		mitCopy=new JMenuItem("Copy");
		mitPaste=new JMenuItem("Paste");
		mitFind=new JMenuItem("Find...");
		mitReplace=new JMenuItem("Replace");
		
		mnuEdit.setMnemonic(KeyEvent.VK_E);
		mnuEdit.add(mitCut);
		mnuEdit.add(mitCopy);
		mnuEdit.add(mitPaste);
		mnuEdit.addSeparator();
		mnuEdit.add(mitFind);
		mnuEdit.add(mitReplace);
		mitCut.setMnemonic(KeyEvent.VK_T);
		mitCopy.setMnemonic(KeyEvent.VK_C);
		mitPaste.setMnemonic(KeyEvent.VK_P);
		mitFind.setMnemonic(KeyEvent.VK_F);
		mitReplace.setMnemonic(KeyEvent.VK_R);
		
		mnuBuild=new JMenu("Build");
		mitCompile=new JMenuItem("Compile");
		mitRun=new JMenuItem("Run");
		
		mnuBuild.add(mitCompile);
		mnuBuild.add(mitRun);
		
		mnuCloseTab=new JMenu("Close Tab");
		mitCurrenttab=new JMenuItem("Current Tab");
		mitAlltab=new JMenuItem("All Tab");
		
		mnuCloseTab.add(mitCurrenttab);
		mnuCloseTab.add(mitAlltab);
		
		mbar=new JMenuBar();
		mbar.add(mnuFile);
		mbar.add(mnuEdit);
		mbar.add(mnuBuild);
		mbar.add(mnuCloseTab);
		
		mitNew.addActionListener(this);
		mitNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		mitOpen.addActionListener(this);
		mitOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		mitSave.addActionListener(this);
		mitSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		mitSaveas.addActionListener(this);
		mitExit.addActionListener(this);
		mitCut.addActionListener(this);
		mitCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		mitCopy.addActionListener(this);
		mitCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		mitPaste.addActionListener(this);
		mitPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		mitFind.addActionListener(this);
		mitReplace.addActionListener(this);
		mitCompile.addActionListener(this);
		mitRun.addActionListener(this);
		mitCurrenttab.addActionListener(this);
		mitAlltab.addActionListener(this);
		
		setJMenuBar(mbar);
		setBounds(150,100,400,400);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Java IDE");
		
		jtaConsole=new JTextArea();
		jspConsole=new JScrollPane(jtaConsole);
		jtp=new JTabbedPane();
		jtp.setTabPlacement(JTabbedPane.BOTTOM);
		jtp.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		sp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jtp,jspConsole);
		add(sp);
		
	
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		String g=ae.getActionCommand();
		if(g.equalsIgnoreCase("new"))
		{
			JFileChooser jfc=new JFileChooser("f://JAVA PRACTICE");
		   	FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Text Files", "txt");
	    	FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Image Files", "jpg","gif");
			jfc.addChoosableFileFilter(filter1);
			jfc.addChoosableFileFilter(filter2);
			jfc.setFileFilter(filter1);
			int code=jfc.showSaveDialog(this);
			if(code == JFileChooser.APPROVE_OPTION) 
			{
				jta=new JTextArea();
				ln_jta=new JTextArea("1");
				ln_jta.setEditable(false);
				ln_jta.setBackground(Color.LIGHT_GRAY);
				jsp=new JScrollPane(jta);
				jsp.setRowHeaderView(ln_jta);
				jta.getDocument().addDocumentListener(new DocumentListener() {
					String getText() {
						int lines=jta.getLineCount();
						String str="1";
						for(int i=2;i<=lines;i++)
							str+="\n"+i;
						return str;
					}
					public void changedUpdate(DocumentEvent de)
					{
						ln_jta.setText(getText());
					}
					public void insertUpdate(DocumentEvent de)
					{
						ln_jta.setText(getText());
					}
					public void removeUpdate(DocumentEvent de)
					{
						ln_jta.setText(getText());
					}
					
				});

				curFile=jfc.getSelectedFile();
				jtp.addTab(curFile.getName(),jsp);
			}
		}
		
	}

	public static void main(String[] args) {
	 new JavaIDE();
	}

}
