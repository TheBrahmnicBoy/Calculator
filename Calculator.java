import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.util.Arrays;
import java.util.Stack;
import javax.swing.*;

class EvaluateString {
  public static double evaluate(String expression) {
    char[] tokens = expression.toCharArray();
     // Stack for numbers: 'values'
    Stack<Double> values = new Stack<Double>();
    // Stack for Operators: 'ops'
    Stack<Character> ops = new Stack<Character>();

    for (int i = 0; i < tokens.length; i++) {
      // Current token is a
      // whitespace, skip it
      if (tokens[i] == ' ') {
				continue;
			}
      // Current token is a number,
      // push it to stack for numbers
      if (((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
        StringBuffer sbuf = new StringBuffer();

        // There may be more than one digits in number
        while (i < tokens.length && ((tokens[i] >= '0' &&
                          						tokens[i] <= '9') ||
																			tokens[i] == '.')) {
					sbuf.append(tokens[i++]);
				}
        values.push(Double.parseDouble(sbuf.toString()));

        /* right now the i points to the character next to the digit, since the
				for loop also increases the i, we would skip one token position;
				we need to decrease the value of i by 1 to correct the offset. */
        i--;
      }
      // Current token is an opening brace,
      // push it to 'ops'
      else if (tokens[i] == '(') {
				ops.push(tokens[i]);
			}
      // Closing brace encountered,
      // solve entire brace
      else if (tokens[i] == ')') {
        while (ops.peek() != '(')
          values.push(applyOp(ops.pop(),values.pop(),values.pop()));
        ops.pop();
      }

      // Current token is an operator.
      else if ( tokens[i] == '+' ||
              	tokens[i] == '-' ||
              	tokens[i] == '*' ||
              	tokens[i] == '/') {
        // While top of 'ops' has same
        // or greater precedence to current
        // token, which is an operator.
        // Apply operator on top of 'ops'
        // to top two elements in values stack
        while (!ops.empty() &&
               hasPrecedence(tokens[i],
                            ops.peek()))
          values.push(applyOp(ops.pop(),
                           values.pop(),
                         values.pop()));

        // Push current token to 'ops'.
        ops.push(tokens[i]);
      }
    }

    // Entire expression has been
    // parsed at this point, apply remaining
    // ops to remaining values
    while (!ops.empty())
        values.push(applyOp(ops.pop(),
                         values.pop(),
                       values.pop()));

    // Top of 'values' contains
    // result, return it
    return values.pop();
  }

  // Returns true if 'op2' has higher
  // or same precedence as 'op1',
  // otherwise returns false.
  public static boolean hasPrecedence(char op1, char op2) {
    if (op2 == '(' || op2 == ')')
      return false;
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
      return false;
    else
      return true;
  }

  // A utility method to apply an
  // operator 'op' on operands 'a'
  // and 'b'. Return the result.
  public static double applyOp(char op, double b, double a) {
    switch (op) {
		  case '+':
		    return a + b;
		  case '-':
		    return a - b;
		  case '*':
		    return a * b;
		  case '/':
	      if (b == 0) {
					throw new UnsupportedOperationException("Cannot divide by zero");
				}
		    return a / b;
    }
    return 0;
  }
}

public class Calculator extends JFrame implements ActionListener {

	JTextField textField;
	JFrame frame;
	JButton zero = new JButton("0");
	JButton one = new JButton("1");
	JButton two = new JButton("2");
	JButton three = new JButton("3");
	JButton four = new JButton("4");
	JButton five = new JButton("5");
	JButton six = new JButton("6");
	JButton seven = new JButton("7");
	JButton eight = new JButton("8");
	JButton nine = new JButton("9");
	JButton dot = new JButton(".");
	JButton add = new JButton("+");
	JButton subtract = new JButton("-");
	JButton divide = new JButton("/");
	JButton equals = new JButton("=");
	JButton multiply = new JButton("*");
	JButton plusMinus = new JButton("+/-");
	JButton squareRoot = new JButton("SQ");
	JButton onex = new JButton("1/x");
	JButton percentage = new JButton("%");
	JButton CE = new JButton("CE");
	JButton backspace = new JButton("<-");
	JButton C = new JButton("C");
	JButton MC = new JButton("MC");
	JButton MR = new JButton("MR");
	JButton MS = new JButton("MS");
	JButton mPlus = new JButton("M+");
	JButton mMinus = new JButton("M-");
	JPanel main = new JPanel();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	double memory = 0.0d;

	public Calculator() {

		frame = new JFrame("Calculator");
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		textField = new JTextField("0", 8);
		textField.setFont(new Font("Courier", Font.PLAIN, 16));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
		p1.setBackground(Color.lightGray);
		p2.setBackground(Color.lightGray);
		p1.setLayout(new GridLayout(1, 5, 2, 2));
		p2.setLayout(new GridLayout(6, 5, 2, 2));

		zero.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		dot.addActionListener(this);
		add.addActionListener(this);
		subtract.addActionListener(this);
		divide.addActionListener(this);
		equals.addActionListener(this);
		multiply.addActionListener(this);
		plusMinus.addActionListener(this);
		squareRoot.addActionListener(this);
		onex.addActionListener(this);
		percentage.addActionListener(this);
		CE.addActionListener(this);
		backspace.addActionListener(this);
		C.addActionListener(this);
		MC.addActionListener(this);
		MR.addActionListener(this);
		MS.addActionListener(this);
		mPlus.addActionListener(this);
		mMinus.addActionListener(this);

		main.add(p1);
		main.add(p2);

		p1.add(textField);
		p2.add(MC);
		p2.add(MR);
		p2.add(MS);
		p2.add(mPlus);
		p2.add(mMinus);
		p2.add(backspace);
		p2.add(CE);
		p2.add(C);
		p2.add(plusMinus);
		p2.add(squareRoot);
		p2.add(seven);
		p2.add(eight);
		p2.add(nine);
		p2.add(divide);
		p2.add(percentage);
		p2.add(four);
		p2.add(five);
		p2.add(six);
		p2.add(multiply);
		p2.add(onex);
		p2.add(one);
		p2.add(two);
		p2.add(three);
		p2.add(subtract);
		p2.add(equals);
		p2.add(zero);
		p2.add(dot);
		p2.add(add);

		zero.setPreferredSize(new Dimension(300, 100));

		p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		p2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frame.add(main);
		frame.pack();
		frame.setSize(350, 400);
		frame.setResizable(true);
		frame.setVisible(true);

	}

	public void clearZero() {
		if(textField.getText().equals("0")) {
			textField.setText("");
		}
	}

	public boolean isNumber(String expression) {
		try {
			Double.parseDouble(expression);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void actionPerformed (ActionEvent event) {
		if (event.getSource() == zero) {
			clearZero();
			textField.setText(textField.getText() + "0");
		} else if (event.getSource() == one) {
			clearZero();
			textField.setText(textField.getText() + "1");
		} else if (event.getSource() == two) {
			clearZero();
			textField.setText(textField.getText() + "2");
		} else if (event.getSource() == three) {
			clearZero();
			textField.setText(textField.getText() + "3");
		} else if (event.getSource() == four) {
			clearZero();
			textField.setText(textField.getText() + "4");
		} else if (event.getSource() == five) {
			clearZero();
			textField.setText(textField.getText() + "5");
		} else if (event.getSource() == six) {
			clearZero();
			textField.setText(textField.getText() + "6");
		} else if (event.getSource() == seven) {
			clearZero();
			textField.setText(textField.getText() + "7");
		} else if (event.getSource() == eight) {
			clearZero();
			textField.setText(textField.getText() + "8");
		} else if (event.getSource() == nine) {
			clearZero();
			textField.setText(textField.getText() + "9");
		} else if (event.getSource() == dot) {
			textField.setText(textField.getText() + ".");
		} else if (event.getSource() == add) {
			textField.setText(textField.getText() + "+");
		} else if (event.getSource() == subtract) {
			textField.setText(textField.getText() + "-");
		} else if (event.getSource() == divide) {
			textField.setText(textField.getText() + "/");
		} else if (event.getSource() == multiply) {
			textField.setText(textField.getText() + "*");
		} else if (event.getSource() == plusMinus) {
			if(textField.getText().charAt(0) != '-') {
				textField.setText("-" + textField.getText());
			} else {
				String str = textField.getText();
				textField.setText(str.substring(1, str.length()));
			}
		} else if (event.getSource() == CE || event.getSource() == backspace) {
			if(textField.getText().length() == 1) {
				textField.setText("0");
			} else {
				String str = textField.getText();
				textField.setText(str.substring(0,str.length()-1));
			}
		} else if (event.getSource() == C ) {
			textField.setText("0");
		} else if (event.getSource() == onex ) {
			String expression = textField.getText();
			if(isNumber(expression)) {
				double x = Double.parseDouble(expression);
				textField.setText(Double.toString(1.0/x));
			} else {
				textField.setText("ERROR - PRESS C");
			}
		} else if (event.getSource() == squareRoot) {
			String expression = textField.getText();
			if(isNumber(expression)) {
				double x = Double.parseDouble(expression);
				textField.setText(Double.toString(Math.sqrt(x)));
			} else {
				textField.setText("ERROR - PRESS C");
			}
		} else if (event.getSource() == percentage) {
			String expression = textField.getText();
			if(isNumber(expression)) {
				double x = Double.parseDouble(expression);
				textField.setText(Double.toString(x/100));
			} else {
				textField.setText("ERROR - PRESS C");
			}
		} else if (event.getSource() == equals) {
			String expression = textField.getText();

			if(isNumber(expression)) {
				textField.setText(expression);
			} else {
				EvaluateString evstr = new EvaluateString();
				double d = evstr.evaluate(expression);
				if ((d % 1) == 0) {
					int t = (int)d;
					expression = Integer.toString(t);
				} else {
					expression = Double.toString(d);
				}
				textField.setText(expression);
			}
		} else if (event.getSource() == MC) {
			memory = 0;
		} else if (event.getSource() == MR) {
			String expression;
			if ((memory % 1) == 0) {
				int t = (int) memory;
				expression = Integer.toString(t);
			} else {
				expression = Double.toString(memory);
			}
			textField.setText(expression);
		} else if (event.getSource() == MS) {
			String expression = textField.getText();
			if(isNumber(expression)) {
				memory = Double.parseDouble(expression);
			} else {
				textField.setText("ERROR - PRESS C");
			}
		} else if (event.getSource() == mPlus) {
			String expression = textField.getText();
			if(isNumber(expression)) {
				memory += Double.parseDouble(expression);
			} else {
				textField.setText("ERROR - PRESS C");
			}
		} else if (event.getSource() == mMinus) {
			String expression = textField.getText();
			if(isNumber(expression)) {
				memory -= Double.parseDouble(expression);
			} else {
				textField.setText("ERROR - PRESS C");
			}
		}
	}

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new Calculator();
			}
		});
	}
}

// <applet code="Calculator.class" width="300" height="300"> </applet>
