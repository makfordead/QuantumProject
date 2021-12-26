package com.quantum.project.quantumproject.circuit;

import org.apache.commons.math3.complex.Complex;

public class QCircuit {
    private QState qState;

    public QCircuit() {
        this.qState = new QState();
    }

    public QCircuit applyGate(final Complex[][] gate) {
        final Complex[] state = qState.getState();
        final Complex[] newState = new Complex[2];

        for (int i = 0; i < gate.length; i++) {
            newState[i] = computeDotProduct(gate[i], state);
        }
        qState.setState(newState);
        return this;
    }

    private Complex computeDotProduct(final Complex[] a, final Complex[] b) {
        Complex res = Complex.ZERO;
        for (int i = 0; i < a.length; i++) {
            final Complex multiplied = a[i].multiply(b[i]);
            res = res.add(multiplied);
        }
        return res;
    }

    public QCircuit prettyPrint() {
        System.out.println("Current state of the system");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\n" );
        for (int i = 0; i < qState.getState().length; i++) {
            stringBuilder.append(i + "\t");
            stringBuilder.append(" " + qState.getState()[i] + ((i < qState.getState().length - 1) ? ",\n" : " \n"));
        }
        stringBuilder.append("]\n");
        System.out.println(stringBuilder);
        return this;
    }
}
