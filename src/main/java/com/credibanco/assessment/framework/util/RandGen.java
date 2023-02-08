package com.credibanco.assessment.framework.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ajrozo
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandGen {

    public static int randNum() {
        double random = Math.random();
        double x = random * 100;
        return (int) x + 1;
    }
}
