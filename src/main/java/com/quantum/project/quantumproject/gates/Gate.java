package com.quantum.project.quantumproject.gates;

import org.apache.commons.math3.complex.Complex;

public class Gate {
    public static final Complex[][] NOT_GATE = {
            {Complex.ZERO, Complex.ONE},
            {Complex.ONE, Complex.ZERO}
    };

    public static final Complex[][] H_GATE = {
            {new Complex(1/Math.sqrt(2)),new Complex(1/Math.sqrt(2))},
            {new Complex(1/Math.sqrt(2)),new Complex(-1/Math.sqrt(2))}
    };

    public static final Complex[][] IDENTITY_GATE = {
            {Complex.ONE, Complex.ZERO},
            {Complex.ZERO, Complex.ONE}
    };
}
