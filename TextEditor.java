import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {
    // Creating Frame
    JFrame frame;
    // Creating text area
    JTextArea textarea;
    // Creating constructor
    JMenuBar jMenuBar;
    TextEditor()
    {
        frame=new JFrame("Text Editor");  // Initializing the frame
        textarea=new JTextArea();  // Initializing the text arae
        jMenuBar=new JMenuBar();
        JMenu file=new JMenu("File");
        JMenu edit=new JMenu("Edit");

        JMenuItem openFile=new JMenuItem("Open File");
        JMenuItem saveFile=new JMenuItem("Save File");
        JMenuItem printFile=new JMenuItem("Print File");
        JMenuItem newFile=new JMenuItem("New File");

        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        newFile.addActionListener(this);

        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(newFile);

        JMenuItem cut=new JMenuItem("Cut");
        JMenuItem copy=new JMenuItem("Copy");
        JMenuItem paste=new JMenuItem("Paste");
        JMenuItem close=new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(close);

        jMenuBar.add(file);
        jMenuBar.add(edit);

        frame.setJMenuBar(jMenuBar);   // So menu bar is added in text area
        frame.add(textarea);
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String []args)
    {
        TextEditor object=new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call=e.getActionCommand();
        if(call=="New File")
        {
            textarea.setText("");
        }
        else if(call=="Cut")
        {
            textarea.cut();
        }
        else if(call=="Copy")
        {
            textarea.copy();
        }
        else if(call=="Paste")
        {
            textarea.paste();
        }
        else if(call=="Close")
        {
            frame.setVisible(false);
        }
        else if(call=="Save File")
        {
            JFileChooser jFileChooser=new JFileChooser("E:");
            int ans=jFileChooser.showOpenDialog(null);  //open dialog give 0 or 1 i.e. store in int  ,, Chosed file is 0
            if(ans==jFileChooser.APPROVE_OPTION);  // equal to 0
            {
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;

                try
                {
                    writer = new BufferedWriter(new FileWriter(file, false));
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }

                try
                {
                    writer.write(textarea.getText());
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }

                try
                {
                    writer.flush();
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }

                try
                {
                    writer.close();
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(call=="Open File")
        {
            JFileChooser jFileChooser1=new JFileChooser("E:");
            int ans=jFileChooser1.showOpenDialog(null);
            if(ans==jFileChooser1.APPROVE_OPTION)
            {
                File file=new File(jFileChooser1.getSelectedFile().getAbsolutePath());

                try
                {
                    String s1="", s2="";
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";
                    }
                    textarea.setText(s2);
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(call=="Print File")
        {
            try
            {   // when some machines does not have a connection with printer
                textarea.print();
            }
            catch (PrinterException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    }
}
