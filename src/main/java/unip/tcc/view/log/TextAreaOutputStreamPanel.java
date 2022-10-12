package unip.tcc.view.log;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.springframework.scheduling.annotation.Async;

@SuppressWarnings("serial")
public class TextAreaOutputStreamPanel extends JPanel {

   private JTextArea textArea = new JTextArea(15, 30);
   private TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
         textArea, "Log");

   public TextAreaOutputStreamPanel() {
      setLayout(new BorderLayout());
      add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
      System.setOut(new PrintStream(taOutputStream));

      int timerDelay = 1000;
      new Timer(timerDelay , new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {

            // though this outputs via System.out.println, it actually displays
            // in the JTextArea:
         }
      }).start();
   }

   private static void createAndShowGui() {
      JFrame frame = new JFrame("Log");
      frame.getContentPane().add(new TextAreaOutputStreamPanel());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   
   public static void load() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}
