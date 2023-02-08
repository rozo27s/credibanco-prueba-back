package com.credibanco.assessment.framework.util;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ajrozo
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    public static boolean isEmptyOrNull(final String string) {
        return Objects.isNull(string) || string.isBlank();
    }

}
