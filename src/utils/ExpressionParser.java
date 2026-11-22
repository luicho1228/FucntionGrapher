package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

/**
 * Parses a text expression in the variable x (e.g. "2sin(x) + x^2/10") into a
 * {@link DoubleUnaryOperator}. The expression is parsed once; evaluating the
 * returned function is fast.
 *
 * Supported: numbers, x, pi, e, + - * / ^, parentheses, unary minus,
 * implicit multiplication (2x, 3(x+1), 2sin(x)), and the functions
 * sin cos tan asin acos atan sinh cosh tanh sqrt cbrt abs exp ln log floor ceil sign.
 * "log" is base 10, "ln" is the natural logarithm. An optional "y =" or "f(x) =" prefix is ignored.
 *
 * Note: scientific notation such as 2e3 is not supported ("e" is Euler's number).
 */
public final class ExpressionParser {

	private static final Map<String, DoubleUnaryOperator> FUNCTIONS = new HashMap<>();

	static {
		FUNCTIONS.put("sin", Math::sin);
		FUNCTIONS.put("cos", Math::cos);
		FUNCTIONS.put("tan", Math::tan);
		FUNCTIONS.put("asin", Math::asin);
		FUNCTIONS.put("acos", Math::acos);
		FUNCTIONS.put("atan", Math::atan);
		FUNCTIONS.put("sinh", Math::sinh);
		FUNCTIONS.put("cosh", Math::cosh);
		FUNCTIONS.put("tanh", Math::tanh);
		FUNCTIONS.put("sqrt", Math::sqrt);
		FUNCTIONS.put("cbrt", Math::cbrt);
		FUNCTIONS.put("abs", Math::abs);
		FUNCTIONS.put("exp", Math::exp);
		FUNCTIONS.put("ln", Math::log);
		FUNCTIONS.put("log", Math::log10);
		FUNCTIONS.put("floor", Math::floor);
		FUNCTIONS.put("ceil", Math::ceil);
		FUNCTIONS.put("sign", Math::signum);
	}

	private final String src;
	private int pos = 0;

	private ExpressionParser(String src) {
		this.src = src;
	}

	/**
	 * Parses the text into a function of x.
	 *
	 * @throws IllegalArgumentException with a readable message if the text is not a valid expression
	 */
	public static DoubleUnaryOperator parse(String text) {
		if (text == null) {
			throw new IllegalArgumentException("Empty expression");
		}
		int eq = text.indexOf('=');
		if (eq >= 0) {
			text = text.substring(eq + 1);
		}
		ExpressionParser p = new ExpressionParser(text);
		p.skipSpaces();
		if (p.atEnd()) {
			throw new IllegalArgumentException("Empty expression");
		}
		DoubleUnaryOperator f = p.parseExpression();
		p.skipSpaces();
		if (!p.atEnd()) {
			throw p.error("Unexpected '" + p.peek() + "'");
		}
		return f;
	}

	// expression = term { ('+' | '-') term }
	private DoubleUnaryOperator parseExpression() {
		DoubleUnaryOperator left = parseTerm();
		while (true) {
			if (match('+')) {
				final DoubleUnaryOperator a = left;
				final DoubleUnaryOperator b = parseTerm();
				left = x -> a.applyAsDouble(x) + b.applyAsDouble(x);
			} else if (match('-')) {
				final DoubleUnaryOperator a = left;
				final DoubleUnaryOperator b = parseTerm();
				left = x -> a.applyAsDouble(x) - b.applyAsDouble(x);
			} else {
				return left;
			}
		}
	}

	// term = unary { ('*' | '/' | <implicit multiplication>) unary }
	private DoubleUnaryOperator parseTerm() {
		DoubleUnaryOperator left = parseUnary();
		while (true) {
			if (match('*')) {
				final DoubleUnaryOperator a = left;
				final DoubleUnaryOperator b = parseUnary();
				left = x -> a.applyAsDouble(x) * b.applyAsDouble(x);
			} else if (match('/')) {
				final DoubleUnaryOperator a = left;
				final DoubleUnaryOperator b = parseUnary();
				left = x -> a.applyAsDouble(x) / b.applyAsDouble(x);
			} else if (startsOperand()) {
				final DoubleUnaryOperator a = left;
				final DoubleUnaryOperator b = parseUnary();
				left = x -> a.applyAsDouble(x) * b.applyAsDouble(x);
			} else {
				return left;
			}
		}
	}

	// unary = ('-' | '+') unary | power      (so -x^2 means -(x^2))
	private DoubleUnaryOperator parseUnary() {
		if (match('-')) {
			final DoubleUnaryOperator f = parseUnary();
			return x -> -f.applyAsDouble(x);
		}
		if (match('+')) {
			return parseUnary();
		}
		return parsePower();
	}

	// power = primary [ '^' unary ]          (right-associative: 2^3^2 = 2^(3^2))
	private DoubleUnaryOperator parsePower() {
		final DoubleUnaryOperator base = parsePrimary();
		if (match('^')) {
			final DoubleUnaryOperator exponent = parseUnary();
			return x -> Math.pow(base.applyAsDouble(x), exponent.applyAsDouble(x));
		}
		return base;
	}

	// primary = number | '(' expression ')' | name
	private DoubleUnaryOperator parsePrimary() {
		skipSpaces();
		if (atEnd()) {
			throw error("Unexpected end of expression");
		}
		char c = peek();
		if (Character.isDigit(c) || c == '.') {
			return parseNumber();
		}
		if (c == '(') {
			pos++;
			DoubleUnaryOperator f = parseExpression();
			if (!match(')')) {
				throw error("Missing ')'");
			}
			return f;
		}
		if (Character.isLetter(c)) {
			return parseName();
		}
		throw error("Unexpected '" + c + "'");
	}

	private DoubleUnaryOperator parseNumber() {
		int start = pos;
		while (!atEnd() && (Character.isDigit(peek()) || peek() == '.')) {
			pos++;
		}
		String s = src.substring(start, pos);
		try {
			final double value = Double.parseDouble(s);
			return x -> value;
		} catch (NumberFormatException e) {
			pos = start;
			throw error("Bad number '" + s + "'");
		}
	}

	private DoubleUnaryOperator parseName() {
		int start = pos;
		while (!atEnd() && Character.isLetter(peek())) {
			pos++;
		}
		String name = src.substring(start, pos).toLowerCase();

		final DoubleUnaryOperator fn = FUNCTIONS.get(name);
		if (fn != null) {
			if (!match('(')) {
				throw error("Function '" + name + "' needs parentheses, e.g. " + name + "(x)");
			}
			final DoubleUnaryOperator arg = parseExpression();
			if (!match(')')) {
				throw error("Missing ')'");
			}
			return x -> fn.applyAsDouble(arg.applyAsDouble(x));
		}

		switch (name) {
			case "x":
				return x -> x;
			case "pi":
				return x -> Math.PI;
			case "e":
				return x -> Math.E;
			default:
				pos = start;
				throw error("Unknown name '" + name + "'");
		}
	}

	// --- helpers ---

	/** True if the next token can start an operand, which means an implicit multiplication. */
	private boolean startsOperand() {
		skipSpaces();
		if (atEnd()) {
			return false;
		}
		char c = peek();
		return Character.isLetter(c) || Character.isDigit(c) || c == '.' || c == '(';
	}

	private boolean match(char c) {
		skipSpaces();
		if (!atEnd() && peek() == c) {
			pos++;
			return true;
		}
		return false;
	}

	private void skipSpaces() {
		while (!atEnd() && Character.isWhitespace(peek())) {
			pos++;
		}
	}

	private boolean atEnd() {
		return pos >= src.length();
	}

	private char peek() {
		return src.charAt(pos);
	}

	private IllegalArgumentException error(String message) {
		return new IllegalArgumentException(message + " (position " + (pos + 1) + ")");
	}
}
