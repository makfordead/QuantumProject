package com.quantum.project.quantumproject.circuit;

import com.quantum.project.quantumproject.MatrixUtils;
import com.quantum.project.quantumproject.gates.Gate;
import org.apache.commons.math3.complex.Complex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QCircuit {
    private QState qState;

    public QCircuit(final int qBits) {
        this.qState = new QState(qBits);
    }

    public QCircuit applyGate(Complex[][] gate, int qubitNumber) {
        final Complex[] state = qState.getState();
        final Complex[] newState = new Complex[(int) Math.pow(2, qState.getqBits())];

        if (qState.getqBits() > 1) {
            gate = computeGateForMoreThanOneQubit(gate, qubitNumber);
            System.out.println("==============");
            System.out.println("Applying gate");
            for (int i = 0; i < gate.length; i++) {
                for (int j = 0; j < gate[i].length; j++) {
                    System.out.print(gate[i][j]);
                }
                System.out.println();
            }
            System.out.println("==============");
        }

        for (int i = 0; i < gate.length; i++) {
            newState[i] = computeDotProduct(gate[i], state);
        }
        qState.setState(newState);
        return this;
    }

    private Complex[][] computeGateForMoreThanOneQubit(Complex[][] gate, int qubitNumber) {
        if (qubitNumber >= qState.getqBits())
            throw new RuntimeException("Invalid qubit number");

        if (qubitNumber == 0) {
            for (int i = 0; i < qState.getqBits() - 1; i++) {
                gate = MatrixUtils.tensorProduct(gate, Gate.IDENTITY_GATE);
            }
            return gate;
        }

        if (qubitNumber == 1) {
            for (int i = 0; i < qState.getqBits() - 1; i++) {
                gate = MatrixUtils.tensorProduct(Gate.IDENTITY_GATE, gate);
            }
            return gate;
        }
        return null;
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

        generateQStates(qState.getqBits(), stateNames);

        Collections.sort(stateNames, (Comparator.comparingInt(Integer::parseInt)));
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
            for (j = i - 1; j >= 0; j--)
                arr.add(arr.get(j));

            for (j = 0; j < i; j++)
                arr.set(j, "0" + arr.get(j));

            for (j = i; j < 2 * i; j++)
                arr.set(j, "1" + arr.get(j));
        }

        for (i = 0; i < arr.size(); i++)
            codes.add(arr.get(i));
    }
}
