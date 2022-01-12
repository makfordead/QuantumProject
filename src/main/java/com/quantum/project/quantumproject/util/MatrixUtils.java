package com.quantum.project.quantumproject.util;

import org.apache.commons.math3.complex.Complex;

public class MatrixUtils {
    public static Complex[][] tensorProduct(final Complex[][] a, final Complex[][] b) {
        int aRowOne = a.length;
        int aColOne = a[0].length;
        int bRowTwo = b.length;
        int bColTwo = b[0].length;

        final Complex[][] finalMatrix = new Complex[aRowOne * bRowTwo][aColOne * bColTwo];

        for (int i = 0; i < aRowOne; i++) {
            for (int j = 0; j < aColOne; j++) {
                for (int k = 0; k < bRowTwo; k++) {
                    for (int l = 0; l < bColTwo; l++) {
                        int row = i * bRowTwo + k, col = j * bColTwo + l;
                        finalMatrix[row][col] = (a[i][j].multiply(b[k][l]));
                    }
                }
            }
        }
        return finalMatrix;
    }
}
