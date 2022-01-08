package com.quantum.project.quantumproject;

import com.quantum.project.quantumproject.circuit.QCircuit;

import static com.quantum.project.quantumproject.gates.Gate.H_GATE;
import static com.quantum.project.quantumproject.gates.Gate.NOT_GATE;

public class QuantumProjectApplication {
    public static void main(String[] args) {
        final QCircuit qCircuit = new QCircuit()
				.applyGate(NOT_GATE)
				.prettyPrint();
    }
}
