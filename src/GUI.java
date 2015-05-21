import java.awt.Container;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

/*
 * Created by JFormDesigner on Thu Apr 23 19:05:47 MSK 2015
 */

/**
 * @author Arseniy
 */
public class GUI extends JFrame {
	CustomPlayer player = new CustomPlayer();
	private int j;
	private int n;
	static JFileChooser choose;
	static String[] list;
	private boolean isPause = false;
	DefaultListModel listModel = new DefaultListModel();

	public GUI() {
		choose = new JFileChooser();
		choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		choose.setAcceptAllFileFilterUsed(false);
		initComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void playActionPerformed(ActionEvent e) {
		player.setPath(choose.getSelectedFile() + "/" + list[n]);
		if (!isPause) {
			player.play(-1);
			isPause = false;
		} else {
			player.resume();
		}
	}

	private void pauseActionPerformed(ActionEvent e) {
		player.pause();
		isPause = true;
	}

	private void OpenActionPerformed(ActionEvent e) {

		choose.showOpenDialog(choose);
		choose.addChoosableFileFilter(new FileFilter() {

			public String getDescription() {
				return "Music (*.mp3)";
			}
		
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					return f.getName().toLowerCase().endsWith(".mp3");
				}
			}
		});
		choose.setAcceptAllFileFilterUsed(true);
		list = new File(choose.getSelectedFile() + ".").list();
		j = list.length - 1;
		n = 0;
		list1.setModel(listModel);
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
			listModel.addElement(list[i]);
		}
	}

	private void nextActionPerformed(ActionEvent e) {
		if (n == j) {
			n = 0;
			player.pause();
			player.setPath(choose.getSelectedFile() + "/" + list[n]);
			player.play(-1);
			list1.setSelectedIndex(n);
		} else {
			n = n + 1;
			player.pause();
			player.setPath(choose.getSelectedFile() + "/" + list[n]);
			player.play(-1);
			list1.setSelectedIndex(n);
		}
	}

	private void previousActionPerformed(ActionEvent e) {
		if (n == 0) {
			n = j;
			player.pause();
			player.setPath(choose.getSelectedFile() + "/" + list[n]);
			player.play(-1);
			list1.setSelectedIndex(n);
		} else {
			n = n - 1;
			player.pause();
			player.setPath(choose.getSelectedFile() + "/" + list[n]);
			player.play(-1);
			list1.setSelectedIndex(n);
		}
	}

	private void list1MouseClicked(MouseEvent e) {
		player.pause();
		player.setPath(choose.getSelectedFile() + "/" + list1.getSelectedValue());
		System.out.println(choose.getSelectedFile() + "/" + list1.getSelectedValue());
		player.play(-1);
		n = list1.getSelectedIndex();
	}

	private void list1ValueChanged(ListSelectionEvent e) {
		// TODO add your code here
	}

	private void exitActionPerformed(ActionEvent e) {
		dispose();
	}

	private void DeleteActionPerformed(ActionEvent e) {
		
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		Menu = new JMenuBar();
		File = new JMenu();
		Open = new JMenuItem();
		exit = new JMenuItem();
		previous = new JButton();
		play = new JButton();
		pause = new JButton();
		next = new JButton();
		scrollPane1 = new JScrollPane();
		list1 = new JList();

		//======== this ========
		Container contentPane = getContentPane();

		//======== Menu ========
		{

			//======== File ========
			{
				File.setText("\u0424\u0430\u0439\u043b");

				//---- Open ----
				Open.setText("\u041e\u0442\u043a\u0440\u044b\u0442\u044c");
				Open.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						OpenActionPerformed(e);
					}
				});
				File.add(Open);
			}
			Menu.add(File);

			//---- exit ----
			exit.setText("\u0412\u044b\u0445\u043e\u0434");
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exitActionPerformed(e);
				}
			});
			Menu.add(exit);
		}
		setJMenuBar(Menu);

		//---- previous ----
		previous.setText("\u041d\u0430\u0437\u0430\u0434");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousActionPerformed(e);
			}
		});

		//---- play ----
		play.setText("\u0418\u0433\u0440\u0430\u0442\u044c");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playActionPerformed(e);
			}
		});

		//---- pause ----
		pause.setText("\u041f\u0430\u0443\u0437\u0430");
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pauseActionPerformed(e);
			}
		});

		//---- next ----
		next.setText("\u0412\u043f\u0435\u0440\u0435\u0434");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextActionPerformed(e);
			}
		});

		//======== scrollPane1 ========
		{

			//---- list1 ----
			list1.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					list1ValueChanged(e);
				}
			});
			list1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					list1MouseClicked(e);
				}
			});
			scrollPane1.setViewportView(list1);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.add(GroupLayout.TRAILING, contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.add(contentPaneLayout.createParallelGroup(GroupLayout.LEADING, false)
						.add(previous, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(play, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.add(contentPaneLayout.createParallelGroup(GroupLayout.LEADING, false)
						.add(contentPaneLayout.createSequentialGroup()
							.add(12, 12, 12)
							.add(next))
						.add(contentPaneLayout.createSequentialGroup()
							.addPreferredGap(LayoutStyle.UNRELATED)
							.add(pause, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(LayoutStyle.RELATED, 128, Short.MAX_VALUE)
					.add(scrollPane1, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.add(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.add(contentPaneLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(next)
						.add(previous))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(contentPaneLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(play)
						.add(pause))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.add(contentPaneLayout.createSequentialGroup()
					.add(scrollPane1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.add(0, 20, Short.MAX_VALUE))
		);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JMenuBar Menu;
	private JMenu File;
	private JMenuItem Open;
	private JMenuItem exit;
	private JButton previous;
	private JButton play;
	private JButton pause;
	private JButton next;
	private JScrollPane scrollPane1;
	private JList list1;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
