import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class gui {
	// declaration of gui objects
	private JFrame frame;
	private JFrame frame1;
	private JLabel[][] slots;
	private JButton[] buttons;
	private JButton yellow;
	private JButton green;
	private JButton pink;
	private JPanel panel;
	private JTextField sizeXField;
	private JTextField sizeYField;
	// variables used in grid
	private int xsize = 7;
	private int ysize = 6;
	private int currentPlayer = 1;
	// game variables to communicate with top program
	private boolean hasWon = false;
	private boolean hasDraw = false;
	private boolean quit = false;
	private boolean newGame = false;
	// making of grid and logic
	Grid my_grid = new Grid();
	MechanicsOfGame my_logic = new MechanicsOfGame(my_grid); // create game
																// logic

	/**
	 * construction gui for populating instance variables
	 */
	public gui() {
		frame1 = new JFrame("Options");// this frame adjusts the size of the
										// game board

		sizeXField = new JTextField(8); // sets the text fields 8 characters
		sizeYField = new JTextField(8);

		JLabel sizeXLabel = new JLabel("Size for x");// the label sizes
		JLabel sizeYLabel = new JLabel("Size for y");

		// ------------------------------------------------------------------------------

		/**
		 * the different colors to change the background of board: yellow green and pink
		 */
		yellow = new JButton("yellow");
		yellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.YELLOW);
			}
		});

		green = new JButton("green");
		green.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.GREEN);
			}
		});

		pink = new JButton("pink");
		pink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.PINK);
			}
		});
		/**
		 * converting button that changes the size of the board
		 */
		JButton convertBtn = new JButton("Convert");
		convertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize((int) Double.parseDouble(sizeXField.getText()),
						(int) Double.parseDouble(sizeYField.getText()));
			}
		});

		/**
		 * adding functions to the frame
		 */
		frame1.add(sizeXLabel);
		frame1.add(sizeXField);
		frame1.add(sizeYLabel);
		frame1.add(sizeYField);
		frame1.add(convertBtn);
		frame1.add(yellow);
		frame1.add(green);
		frame1.add(pink);

		// jframe show
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setLayout(new FlowLayout());
		frame1.pack();
		frame1.setVisible(true);

		// ------------------------------------------------------------------

		/**
		 * makes another frame for the game
		 */
		frame = new JFrame("connect four");
		panel = (JPanel) frame.getContentPane();

		panel.setLayout(new GridLayout(xsize, ysize + 1));

		slots = new JLabel[xsize][ysize];
		buttons = new JButton[xsize];

		/**
		 * makes an the buttons and give them functions
		 */
		for (int i = 0; i < xsize; i++) {
			buttons[i] = new JButton("" + (i + 1));
			buttons[i].setActionCommand("" + i);
			buttons[i].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int a = Integer.parseInt(e.getActionCommand());
					int y = my_grid.find_y(a);// check for space in column
					if (y != -1) {
						// sets a place to current player
						if (my_logic.set_and_check(a, y, currentPlayer)) {
							hasWon = true;
						} else if (my_logic.draw_game()) {// checks for draw
															// game
							hasDraw = true;
						} else {
							// change player
							currentPlayer = my_grid.changeplayer(currentPlayer, 2);
							frame.setTitle("connect four - player " + currentPlayer + "'s turn");
						}
					} else {
						JOptionPane.showMessageDialog(null, "choose another one", "column is filled",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			panel.add(buttons[i]);

			/**
			 * adds the buttons into the connect 4
			 */
		}
		for (int column = 0; column < ysize; column++) {
			for (int row = 0; row < xsize; row++) {
				slots[row][column] = new JLabel();
				slots[row][column].setHorizontalAlignment(SwingConstants.CENTER);
				slots[row][column].setBorder(new LineBorder(Color.black));
				panel.add(slots[row][column]);
			}
		}

		// jframe show
		frame.setContentPane(panel);
		frame.setSize(700, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void updateBoard() {// keeps the gui in sync with the logic and grid
		for (int row = 0; row < xsize; row++) {
			for (int column = 0; column < ysize; column++) {
				if (my_grid.matrix_equals(row, column, 1)) {
					slots[row][column].setOpaque(true);
					slots[row][column].setBackground(Color.RED);
				}
				if (my_grid.matrix_equals(row, column, 2)) {
					slots[row][column].setOpaque(true);
					slots[row][column].setBackground(Color.BLUE);
				}
			}
		}
	}

	/**
	 * shows the win
	 */
	public void showWon() {
		String winner = "player " + currentPlayer + " wins";
		int n = JOptionPane.showConfirmDialog(frame, "new game?", winner, JOptionPane.YES_NO_OPTION);
		if (n < 1) {
			frame.dispose();
			newGame = true;
		} else {
			frame.dispose();
			quit = true;
		}
	}

	/**
	 * shows the draw
	 */
	public void showDraw() {
		String winner = "draw game";
		int n = JOptionPane.showConfirmDialog(frame, "new game?", winner, JOptionPane.YES_NO_OPTION);
		if (n < 1) {
			frame.dispose();
			newGame = true;
		} else {
			frame.dispose();
			quit = true;
		}
	}

	public boolean getHasWon() {
		return hasWon;
	}

	public boolean getHasDraw() {
		return hasDraw;
	}

	public boolean getQuit() {
		return quit;
	}

	public boolean getNewGame() {
		return newGame;
	}

	public static void main(String[] args) {
		gui Gui = new gui();
	}

}
