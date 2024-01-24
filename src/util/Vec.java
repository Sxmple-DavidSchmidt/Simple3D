package util;

public interface Vec {
    public int size();
    public double length();
    public Vec normalize();
    public Vec divide(double divisor);
    public Vec scale(double factor);
    public Vec add(Vec vec);
    public Vec subtract(Vec vec);
}
