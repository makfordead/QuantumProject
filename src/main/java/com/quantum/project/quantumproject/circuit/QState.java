package com.quantum.project.quantumproject.circuit;

import lombok.Data;
import org.apache.commons.math3.complex.Complex;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
public class QState {
    private Complex[] state;

    public QState (){
        state = new Complex[2];
        state[0] = Complex.ONE;
        state[1] = Complex.ZERO;
    }

    public Complex[] getState() {
        return state;
    }

    public void setState(Complex[] state) {
        this.state = state;
    }
}
