/**
  * Student Name: Nasim Bidel
  * Student Number: 041*******
  * Course: CST8132 Object Oriented Programming
  * Program: CET-CS-Level 2
*/
import java.util.List;

/**
 * The purpose of Regression is to do linear regression on a list of points.
 * This algorithm is based on the work done by Robert Sedgewick and Kevin Wayne
 * https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/LinearRegression.java.html
 */
public class Regression {
	private final double intercept, slope;
	private final double r2;
	private final double interceptVar, slopeVar;

	/**
	 * Performs a linear regression on the data points.
	 *
	 * @param  points A list of point
	 */
	public Regression(List<Point> points) {
		int n=points.size();
		
		// first pass
		double sumx = 0.0, sumy = 0.0;
		for (Point point : points) {
			sumx  += point.x();
			sumy  += point.y();
		}
		double xbar = sumx / n;
		double ybar = sumy / n;

		// second pass: compute summary statistics
		double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
		for (Point point : points) {
			double x = point.x();
			double y = point.y();
			xxbar += (x - xbar) * (x - xbar);
			yybar += (y - ybar) * (y - ybar);
			xybar += (x - xbar) * (y - ybar);
		}
		slope  = xybar / xxbar;
		intercept = ybar - slope * xbar;

		// more statistical analysis
		double rss = 0.0;      // residual sum of squares
		double ssr = 0.0;      // regression sum of squares
		for (Point point : points) {
			double fit = slope*point.x() + intercept;
			rss += (fit - point.y()) * (fit - point.y());
			ssr += (fit - ybar) * (fit - ybar);
		}

		int degreesOfFreedom = n-2;
		r2    = ssr / yybar;
		double svar  = rss / degreesOfFreedom;
		slopeVar = svar / xxbar;
		interceptVar = svar/n + xbar*xbar*slopeVar;
	}

	/**
	 * Returns the <em>y</em>-intercept &alpha; of the best of the best-fit line <em>y</em> = &alpha; + &beta; <em>x</em>.
	 *
	 * @return the <em>y</em>-intercept &alpha; of the best-fit line <em>y = &alpha; + &beta; x</em>
	 */
	public double intercept() {
		return intercept;
	}

	/**
	 * Returns the slope &beta; of the best of the best-fit line <em>y</em> = &alpha; + &beta; <em>x</em>.
	 *
	 * @return the slope &beta; of the best-fit line <em>y</em> = &alpha; + &beta; <em>x</em>
	 */
	public double slope() {
		return slope;
	}

	/**
	 * Returns the coefficient of determination <em>R</em><sup>2</sup>.
	 *
	 * @return the coefficient of determination <em>R</em><sup>2</sup>,
	 *         which is a real number between 0 and 1
	 */
	public double r2() {
		return r2;
	}

	/**
	 * Returns the standard error of the estimate for the intercept.
	 *
	 * @return the standard error of the estimate for the intercept
	 */
	public double interceptStdErr() {
		return Math.sqrt(interceptVar);
	}

	/**
	 * Returns the standard error of the estimate for the slope.
	 *
	 * @return the standard error of the estimate for the slope
	 */
	public double slopeStdErr() {
		return Math.sqrt(slopeVar);
	}

	/**
	 * Returns the expected response {@code y} given the value of the predictor
	 * variable {@code x}.
	 *
	 * @param  x the value of the predictor variable
	 * @return the expected response {@code y} given the value of the predictor
	 *         variable {@code x}
	 */
	public double predict(double x) {
		return slope*x + intercept;
	}

	/**
	 * Returns a string representation of the simple linear regression model.
	 *
	 * @return a string representation of the simple linear regression model,
	 *         including the best-fit line and the coefficient of determination
	 *         <em>R</em><sup>2</sup>
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(String.format("%.2f n + %.2f", slope(), intercept()));
		s.append("  (R^2 = " + String.format("%.3f", r2()) + ")");
		return s.toString();
	}
}