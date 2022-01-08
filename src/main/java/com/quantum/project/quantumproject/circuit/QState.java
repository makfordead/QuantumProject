package com.quantum.project.quantumproject.circuit;

import lombok.Data;
import org.apache.commons.math3.complex.Complex;

@Data
public class QState {
    private Complex[] state;
    private int qBits;

    public QState(final int qBits) {
        this.qBits = qBits;
        int possibleStates = (int) Math.pow(2, qBits);
        state = new Complex[possibleStates];

        state[0] = Complex.ONE;

        for (int i = 1; i < possibleStates; i++) {
            state[i] = Complex.ZERO;
        }
    }

    public int getqBits() {
        return qBits;
    }

    public Complex[] getState() {
        return state;
    }

    public void setState(Complex[] state) {
        this.state = state;
    }
}
