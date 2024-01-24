package util;

public interface Vec {
    int size();
    double length();
    Vec normalize();
    Vec divide(double divisor);
    Vec scale(double factor);
    Vec add(Vec vec);
    Vec subtract(Vec vec);
}
