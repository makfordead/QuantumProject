package com.quantum.project.quantumproject.circuit;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        stringBuilder.append("[\n");

        final List<String> stateNames = new ArrayList<>();
        generateQStates(1, stateNames);

        for (int i = 0; i < qState.getState().length; i++) {
            stringBuilder.append("\t" + stateNames.get(i) + "\t");
            stringBuilder.append(" " + qState.getState()[i] + ((i < qState.getState().length - 1) ? ",\n" : " \n"));
        }
        stringBuilder.append("]\n");
        System.out.println(stringBuilder);
        return this;
    }

    private void generateQStates(int numberOfBits, final List<String> codes) {

        if (numberOfBits <= 0)
            return;

        ArrayList<String> arr = new ArrayList<String>();

        arr.add("0");
        arr.add("1");

        int i, j;
        for (i = 2; i < (1 << numberOfBits); i = i << 1) {
            // Enter the prviously generated codes again in arr[] in reverse
            // order. Nor arr[] has double number of codes.
            for (j = i - 1; j >= 0; j--)
                arr.add(arr.get(j));

            // append 0 to the first half
            for (j = 0; j < i; j++)
                arr.set(j, "0" + arr.get(j));

            // append 1 to the second half
            for (j = i; j < 2 * i; j++)
                arr.set(j, "1" + arr.get(j));
        }

        // print contents of arr[]
        for (i = 0; i < arr.size(); i++)
            codes.add(arr.get(i));
    }

    private void printMatrix() {

    }
}
