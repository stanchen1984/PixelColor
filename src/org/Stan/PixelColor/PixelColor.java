package org.Stan.PixelColor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class PixelColor extends JFrame {
	// Scrol pane to store grid panel
	JScrollPane drawingArea;
	// Panel store grid
	JPanel drawingPanel;
	// color list stores different colors
	Color[] colors = { Color.red, Color.green, Color.blue, Color.black,
			Color.white, Color.gray };

	public static void main(String[] args) {
		new PixelColor().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public PixelColor() {
		super("Using JPanels with Borders");
		Container content = getContentPane();
		content.setPreferredSize(new Dimension(900, 500));
		content.setBackground(Color.lightGray);
		JPanel controlArea = new JPanel(new GridLayout(3, 1));
		controlArea.add(new ColorPanel("Color", colors));
		controlArea.add(new EditPanel("Edit"));
		content.add(controlArea, BorderLayout.EAST);
		drawingArea = new JScrollPane();
		drawingPanel = new JPanel();
		drawingArea.getViewport().add(drawingPanel);
		content.add(drawingArea, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	/**
	 * Panel display different colors used here.
	 * 
	 * @author Stan
	 * 
	 */
	public class ColorPanel extends JPanel {
		public ColorPanel(String title, Color[] colors) {
			super(new GridLayout(3, 2));
			setBorder(BorderFactory.createTitledBorder(title));
			for (int i = 0; i < colors.length; i++) {
				JPanel jPanel = new JPanel();
				jPanel.setBackground(colors[i]);
				add(jPanel);
			}
		}
	}

	JTextField row;
	JTextField column;

	/**
	 * Panel for edit row and column number
	 * 
	 * @author Stan
	 * 
	 */
	public class EditPanel extends JPanel {
		public EditPanel(String title) {
			super(new GridLayout(3, 2));
			setBorder(BorderFactory.createTitledBorder(title));

			row = new JTextField("10");
			row.setSize(100, 1);
			add(new JLabel("Row"));
			add(row);
			column = new JTextField("10");
			column.setSize(100, 1);
			add(new JLabel("column"));
			add(column);
			add(new JLabel(" "));
			JButton jButton = new JButton("Generate");
			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					generate(Integer.valueOf(row.getText()),
							Integer.valueOf(column.getText()));
				}
			});
			add(jButton);
		}
	}

	/**
	 * generate new grid view
	 * 
	 * @param row
	 *            new grid row number
	 * @param column
	 *            new grid column number
	 */
	private void generate(int row, int column) {
		drawingPanel.removeAll();
		drawingPanel.setLayout(new GridLayout(row, column));
		for (int i = 0; i < (row * column); i++) {
			int row_n = i / row + 1;
			int column_n = i % row + 1;
			String text = String.valueOf(row_n) + " | " + column_n;
			JLabel jLabel = new JLabel(text);
			JPanel jPanel = new JPanel();
			jPanel.add(jLabel);
			jPanel.setBackground(randomColor(colors));
			drawingPanel.add(jPanel);
		}
		getContentPane().revalidate();
	}

	/**
	 * Generate random colors
	 * 
	 * @param colors
	 *            input the color list which stories different colors.
	 * @return
	 */
	private Color randomColor(Color[] colors) {
		Random r = new Random();
		return colors[r.nextInt(colors.length)];
	}
}