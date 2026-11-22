package utils;

public final class Vector {
    public final double x;
    public final double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Arithmetic
    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector subtract(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public Vector multiply(double scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public Vector divide(double scalar) {
        return new Vector(x / scalar, y / scalar);
    }

    public Vector componentMultiply(Vector other) {
        return new Vector(x * other.x, y * other.y);
    }

    public Vector componentDivide(Vector other) {
        return new Vector(x / other.x, y / other.y);
    }

    // Magnitude-related
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double magnitudeSquared() {
        return x * x + y * y;
    }

    public Vector normalized() {
        double mag = magnitude();
        return mag == 0 ? new Vector(0, 0) : new Vector(x / mag, y / mag);
    }

    public double distanceTo(Vector other) {
        return this.subtract(other).magnitude();
    }

    public double dot(Vector other) {
        return x * other.x + y * other.y;
    }

   	@Override
   	public boolean equals(Object obj) {
   		if (this == obj) return true;
   		if (obj == null || getClass() != obj.getClass()) return false;
   		Vector other = (Vector) obj;
   		return Double.compare(other.x, x) == 0 && Double.compare(other.y, y) == 0;
   	}

   	@Override
   	public int hashCode() {
   		int result = Double.hashCode(x);
   		result = 31 * result + Double.hashCode(y);
   		return result;
   	}

   	@Override
   	public String toString() {
   		return String.format("Vector(%.2f, %.2f)", x, y);
   	}
   }
