import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The {@code Calculator} class provides a simple GUI-based calculator
 * capable of performing basic arithmetic operations such as addition,
 * subtraction, multiplication, and division.
 *
 * The calculator supports:
 * <ul>
 *   <li>Basic arithmetic operations (+, -, *, /)</li>
 *   <li>Decimal numbers</li>
 *   <li>Negation of numbers</li>
 *   <li>Clearing and deleting input</li>
 * </ul>
 *
 * The user interacts with the calculator through a graphical user interface
 * created using Swing components.
 *
 * @author Peter
 */
public class Calculator implements ActionListener {

	/** The main frame containing calculator components. */
	JFrame frame;

	/** Text field for displaying input and results. */
	JTextField textfield;

	/** Array to store number buttons (0-9). */
	JButton[] numberButtons = new JButton[10];

	/** Array to store function buttons (+, -, *, /, ., =, Del, Clr, (-)). */
	JButton[] functionButtons = new JButton[9];

	/** Button for addition operation. */
	JButton addButton;

	/** Button for subtraction operation. */
	JButton subButton;

	/** Button for multiplication operation. */
	JButton mulButton;

	/** Button for division operation. */
	JButton divButton;

	/** Button for decimal point input. */
	JButton decButton;

	/** Button to evaluate the expression. */
	JButton equButton;

	/** Button to delete last input character. */
	JButton delButton;

	/** Button to clear the text field. */
	JButton clrButton;

	/** Button to negate the number. */
	JButton negButton;

	/** Panel to organize number and operation buttons. */
	JPanel panel;

	/** Font used for buttons and text field. */
	Font myFont = new Font("Ink Free", Font.BOLD, 30);

	/** First operand in the calculation. */
	double num1 = 0;

	/** Second operand in the calculation. */
	double num2 = 0;

	/** Result of the calculation. */
	double result = 0;

	/** Operator used for the calculation. */
	char operator;

	/**
	 * Constructs a new {@code Calculator} and initializes the GUI components.
	 */
	public Calculator() {
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setLayout(null);

		textfield = new JTextField();
		textfield.setBounds(50, 25, 300, 50);
		textfield.setFont(myFont);
		textfield.setEditable(false);

		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Del");
		clrButton = new JButton("Clr");
		negButton = new JButton("(-)");

		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;

		for (int i = 0; i < functionButtons.length; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}

		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}

		negButton.setBounds(50, 430, 100, 50);
		delButton.setBounds(150, 430, 100, 50);
		clrButton.setBounds(250, 430, 100, 50);

		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 10, 10));

		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);

		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textfield);
		frame.setVisible(true);
	}

	/**
	 * Main method to launch the calculator application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		new Calculator();
	}

	/**
	 * Handles action events for the calculator buttons.
	 * Performs calculations and updates the display based on
	 * the button pressed.
	 *
	 * @param e The action event triggered by button press
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < numberButtons.length; i++) {
			if (e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}

		if (e.getSource() == decButton) {
			textfield.setText(textfield.getText().concat("."));
		}
		if (e.getSource() == addButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '+';
			textfield.setText("");
		}
		if (e.getSource() == subButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '-';
			textfield.setText("");
		}
		if (e.getSource() == mulButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '*';
			textfield.setText("");
		}
		if (e.getSource() == divButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '/';
			textfield.setText("");
		}
		if (e.getSource() == equButton) {
			num2 = Double.parseDouble(textfield.getText());

			switch (operator) {
				case '+' -> result = num1 + num2;
				case '-' -> result = num1 - num2;
				case '*' -> result = num1 * num2;
				case '/' -> result = num1 / num2;
			}
			textfield.setText(String.valueOf(result));
			num1 = result;
		}
		if (e.getSource() == clrButton) {
			textfield.setText("");
		}
		if (e.getSource() == delButton) {
			String string = textfield.getText();
			textfield.setText(string.substring(0, string.length() - 1));
		}
		if (e.getSource() == negButton) {
			double temp = Double.parseDouble(textfield.getText());
			temp *= -1;
			textfield.setText(String.valueOf(temp));
		}
	}
}
