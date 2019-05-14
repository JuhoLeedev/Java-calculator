
/*
 * UTF-8-encoded
 * Java Swing Calculator app
 * version 1.0.1
 * */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class Calculator extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JPanel panel_1;
	private JLabel lblString;
	private JLabel lblValue;
	private String num1;
	private String num2;
	private BigDecimal n1;
	private BigDecimal n2;
	private String operator;
	private BigDecimal result;
	private String value;
	private boolean numInput; // checking numbaer input mode, true: delete number on lblValue and input new
								// number, false: add numbers on lblValue
	private boolean opInput; // checking operator input mode, true: delete previous operator and input new
								// operator, false: add operator on lblValue
	private boolean exception; // checking exception ArithmeticException

	JButton btnPlus;
	JButton btnSub;
	JButton btnMul;
	JButton btnDiv;
	JButton btnDot;
	JButton btnPos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator() {
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)));
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		lblString = new JLabel("New label");
		lblString.setFont(new Font("Arial", Font.PLAIN, 20));
		lblString.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblString);

		lblValue = new JLabel("New label");
		lblValue.setFont(new Font("Arial", Font.PLAIN, 40));
		lblValue.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblValue);
		panel_1.setLayout(new GridLayout(5, 4, 1, 1));

		JButton btnCe = new JButton("CE");
		btnCe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (exception) {// Exception called
					exception = false;
					exceptionHandler();
				}
				clearEntry();
			}
		});
		btnCe.addKeyListener(this);
		btnCe.setBackground(new Color(220, 220, 220));
		btnCe.setFont(new Font("Arial", Font.PLAIN, 24));
		panel_1.add(btnCe);

		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (exception) {// Exception called
					exception = false;
					exceptionHandler();
				}
				clearAll();
			}
		});
		btnC.addKeyListener(this);
		btnC.setBackground(new Color(220, 220, 220));
		btnC.setFont(new Font("Arial", Font.PLAIN, 24));
		panel_1.add(btnC);

		JButton btnBspc = new JButton("\u2190");
		btnBspc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (exception) {// Exception called
					clearAll();
					exceptionHandler();
				}
				backspace();
			}
		});
		btnBspc.addKeyListener(this);
		btnBspc.setBackground(new Color(220, 220, 220));
		btnBspc.setFont(new Font("Arial", Font.PLAIN, 24));
		panel_1.add(btnBspc);

		btnDiv = new JButton("÷");
		btnDiv.setBackground(new Color(220, 220, 220));
		btnDiv.setFont(new Font("Arial", Font.PLAIN, 24));
		btnDiv.addActionListener(this);
		btnDiv.addKeyListener(this);
		panel_1.add(btnDiv);

		JButton btn7 = new JButton("7");
		btn7.setBackground(new Color(255, 255, 255));
		btn7.setFont(new Font("Arial", Font.PLAIN, 24));
		btn7.addActionListener(this);
		btn7.addKeyListener(this);
		panel_1.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.setBackground(new Color(255, 255, 255));
		btn8.setFont(new Font("Arial", Font.PLAIN, 24));
		btn8.addActionListener(this);
		btn8.addKeyListener(this);
		panel_1.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.setBackground(new Color(255, 255, 255));
		btn9.setFont(new Font("Arial", Font.PLAIN, 24));
		btn9.addActionListener(this);
		btn9.addKeyListener(this);
		panel_1.add(btn9);

		btnMul = new JButton("*");
		btnMul.setBackground(new Color(220, 220, 220));
		btnMul.setFont(new Font("Arial", Font.PLAIN, 24));
		btnMul.addActionListener(this);
		btnMul.addKeyListener(this);
		panel_1.add(btnMul);

		JButton btn4 = new JButton("4");
		btn4.setBackground(new Color(255, 255, 255));
		btn4.setFont(new Font("Arial", Font.PLAIN, 24));
		btn4.addActionListener(this);
		btn4.addKeyListener(this);
		panel_1.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.setBackground(new Color(255, 255, 255));
		btn5.setFont(new Font("Arial", Font.PLAIN, 24));
		btn5.addActionListener(this);
		btn5.addKeyListener(this);
		panel_1.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.setBackground(new Color(255, 255, 255));
		btn6.setFont(new Font("Arial", Font.PLAIN, 24));
		btn6.addActionListener(this);
		btn6.addKeyListener(this);
		panel_1.add(btn6);

		btnSub = new JButton("-");
		btnSub.setBackground(new Color(220, 220, 220));
		btnSub.setFont(new Font("Arial", Font.PLAIN, 24));
		btnSub.addActionListener(this);
		btnSub.addKeyListener(this);
		panel_1.add(btnSub);

		JButton btn1 = new JButton("1");
		btn1.setBackground(new Color(255, 255, 255));
		btn1.setFont(new Font("Arial", Font.PLAIN, 24));
		btn1.addActionListener(this);
		btn1.addKeyListener(this);
		panel_1.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setBackground(new Color(255, 255, 255));
		btn2.setFont(new Font("Arial", Font.PLAIN, 24));
		btn2.addActionListener(this);
		btn2.addKeyListener(this);
		panel_1.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setBackground(new Color(255, 255, 255));
		btn3.setFont(new Font("Arial", Font.PLAIN, 24));
		btn3.addActionListener(this);
		btn3.addKeyListener(this);
		panel_1.add(btn3);

		btnPlus = new JButton("+");
		btnPlus.setBackground(new Color(220, 220, 220));
		btnPlus.setFont(new Font("Arial", Font.PLAIN, 24));
		btnPlus.addActionListener(this);
		btnPlus.addKeyListener(this);
		panel_1.add(btnPlus);

		btnPos = new JButton("\u00B1");
		btnPos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posAction();
			}
		});
		btnPos.addKeyListener(this);
		btnPos.setBackground(new Color(220, 220, 220));
		btnPos.setFont(new Font("Arial", Font.PLAIN, 24));
		panel_1.add(btnPos);

		JButton btn0 = new JButton("0");
		btn0.setBackground(new Color(255, 255, 255));
		btn0.setFont(new Font("Arial", Font.PLAIN, 24));
		btn0.addActionListener(this);
		btn0.addKeyListener(this);
		panel_1.add(btn0);

		btnDot = new JButton(".");
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDot();
			}
		});
		btnDot.addKeyListener(this);
		btnDot.setBackground(new Color(220, 220, 220));
		btnDot.setFont(new Font("Arial", Font.BOLD, 24));
		panel_1.add(btnDot);

		JButton btnCal = new JButton("=");
		btnCal.setBackground(new Color(220, 220, 220));
		btnCal.setFont(new Font("Arial", Font.PLAIN, 24));
		btnCal.addActionListener(this);
		btnCal.addKeyListener(this);
		panel_1.add(btnCal);

		btnCal.requestFocus();

		contentPane.setLayout(gl_contentPane);
		clearAll();

	}

	// number 0~9 and operator button actionListner calls this method
	@Override
	public void actionPerformed(ActionEvent e) {
		if (exception) {// Exception called
			clearAll();
			exceptionHandler();
		}
		String s = e.getActionCommand();
		inputHandler(s);
	}

	// Key press handling
	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		// System.out.println(e.getKeyCode() + " " + keycode); // I checked keycode by
		// this code
		switch (keycode) {
		case 27:
			clearAll();
			break;
		case 8:
			backspace();
			break;
		case 46:
			addDot();
			break;
		case KeyEvent.VK_0:
			inputHandler("0");
			break;
		case KeyEvent.VK_1:
			inputHandler("1");
			break;
		case KeyEvent.VK_2:
			inputHandler("2");
			break;
		case KeyEvent.VK_3:
			inputHandler("3");
			break;
		case KeyEvent.VK_4:
			inputHandler("4");
			break;
		case KeyEvent.VK_5:
			inputHandler("5");
			break;
		case KeyEvent.VK_6:
			inputHandler("6");
			break;
		case KeyEvent.VK_7:
			inputHandler("7");
			break;
		case KeyEvent.VK_8:
			if ((e.getModifiers() & 1) != 0) // shift pressed
				inputHandler("*");
			else // no shift
				inputHandler("8");
			break;
		case KeyEvent.VK_9:
			inputHandler("9");
			break;
		case 96:
			inputHandler("0");
			break;
		case 97:
			inputHandler("1");
			break;
		case 98:
			inputHandler("2");
			break;
		case 99:
			inputHandler("3");
			break;
		case 100:
			inputHandler("4");
			break;
		case 101:
			inputHandler("5");
			break;
		case 102:
			inputHandler("6");
			break;
		case 103:
			inputHandler("7");
			break;
		case 104:
			inputHandler("8");
			break;
		case 105:
			inputHandler("9");
			break;
		case 61:
			if ((e.getModifiers() & 1) != 0) // shift pressed
				inputHandler("+");
			else // no shift
				inputHandler("=");
			break;
		case 107:
			inputHandler("+");
			break;
		case 109:
			inputHandler("-");
			break;
		case 45:
			inputHandler("-");
			break;
		case 106:
			inputHandler("*");
			break;
		case 47:
			inputHandler("÷");
			break;
		case 111:
			inputHandler("÷");
			break;
		case 10:
			inputHandler("=");
			break;
		case 110:
			addDot();
			break;
		case 127:
			clearEntry();
			break;
		case 120:
			posAction();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	// clear all variables and JLabels, C method
	public void clearAll() {
		lblString.setText("");
		lblValue.setText("0");
		num1 = null;
		num2 = null;
		operator = "0";
		value = null;
		numInput = true;
		opInput = false;
		exception = false;
	}

	// CE method
	public void clearEntry() {
		lblValue.setText("0");
	}

	// backspace method, delete right to left
	private void backspace() {

		if (lblValue.getText().length() <= 1) {
			lblValue.setText("0");
		} else {
			lblValue.setText(lblValue.getText().substring(0, lblValue.getText().length() - 1));
		}
	}

	// change plus minus
	private void posAction() {
		char c = lblValue.getText().charAt(0);
		if (c == '-') {
			lblValue.setText(lblValue.getText().substring(1));
		} else {
			if (lblValue.getText().equals("0")) {
			} else {
				lblValue.setText("-" + lblValue.getText());
			}
		}
		opInput = false;
		numInput = false;
	}

	// add dot, real number
	private void addDot() {
		if (lblValue.getText().contains(".")) {
		} else {
			lblValue.setText(lblValue.getText() + ".");
			numInput = false;
		}
	}

	// handling numbers and operator button
	private void inputHandler(String s) {
		if (s.compareToIgnoreCase("0") >= 0 && s.compareToIgnoreCase("9") <= 0) { // number pressed
			if (lblValue.getText().equals("0") || numInput) {
				lblValue.setText(s);
				numInput = false;
				opInput = false;
			} else {
				lblValue.setText(lblValue.getText() + s);
				opInput = false;
			}
		} else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("÷")) { // operator pressed
			if (opInput) {
				operator = s;
				lblString.setText(lblString.getText().substring(0, lblString.getText().length() - 1));
				lblString.setText(lblString.getText() + s);
			} else if (num1 == null) {
				num1 = lblValue.getText();
				operator = s;
				lblString.setText(lblString.getText() + num1 + s);
				opInput = true;
			} else if (num1 != null) {
				num2 = lblValue.getText();
				calculate(num1, operator, num2);
				lblValue.setText(value);
				num1 = value;
				operator = s;
				lblString.setText(lblString.getText() + num2 + s);
				opInput = true;
			}
			numInput = true;
		} else if (s.equals("=")) { // = or enter pressed
			lblString.setText("");
			if (opInput && operator == null) {
			} else if (numInput == false && operator.equals("0")) {
			} else if (opInput) {
				num2 = lblValue.getText();
				calculate(num1, operator, num2);
				lblValue.setText(value);
				num1 = null;
				opInput = false;
			} else if (numInput == false) {
				num2 = lblValue.getText();
				calculate(num1, operator, num2);
				lblValue.setText(value);
				num1 = null;
				numInput = true;
			}
		}
	}

	// calculating method, I used BigDecimal library to calculate real number
	// correctly.
	// If use '+' instead of BigDecimal it makes error. ex) 0.1 + 0.2 =
	// 0.30000000000000004
	private void calculate(String num1, String op, String num2) {
		n1 = new BigDecimal(num1);
		n2 = new BigDecimal(num2);

		switch (op) {
		case "+":
			result = n1.add(n2);
			value = result.toString();

			break;
		case "-":
			result = n1.subtract(n2);
			value = result.toString();

			break;
		case "*":
			result = n1.multiply(n2);
			value = result.toString();

			break;
		case "÷":
			try {
				result = n1.divide(n2, MathContext.DECIMAL64);
				value = result.toString();

				break;
			} catch (ArithmeticException e) // exception handling, divided by zero
			{
				exception = true;
				exceptionHandler();
				value = "Can't devide by zero";
			}
		}
	}

	public void exceptionHandler() {
		if (exception) {				// if exception true disable buttons
			btnPlus.setEnabled(false);
			btnSub.setEnabled(false);
			btnMul.setEnabled(false);
			btnDiv.setEnabled(false);
			btnPos.setEnabled(false);
			btnDot.setEnabled(false);
		} else {						// if exception false enable the buttons
			btnPlus.setEnabled(true);
			btnSub.setEnabled(true);
			btnMul.setEnabled(true);
			btnDiv.setEnabled(true);
			btnPos.setEnabled(true);
			btnDot.setEnabled(true);
		}
	}
}
