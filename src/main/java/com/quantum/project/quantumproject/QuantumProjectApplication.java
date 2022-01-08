package com.quantum.project.quantumproject;

import com.quantum.project.quantumproject.circuit.QCircuit;

import static com.quantum.project.quantumproject.gates.Gate.H_GATE;
import static com.quantum.project.quantumproject.gates.Gate.rotateBy;

public class QuantumProjectApplication {
    public static void main(String[] args) {
        System.out.println("Note : (1.0, 0.0) means 1.0 is real part and 0.0 is imaginary part");
        final QCircuit qCircuit = new QCircuit(1)
                .applyGate(H_GATE, 0)
                .applyGate(rotateBy(6.28), 0)
				.prettyPrint();
    }
}
