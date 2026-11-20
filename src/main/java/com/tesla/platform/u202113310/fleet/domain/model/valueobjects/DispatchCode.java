package com.tesla.platform.u202113310.fleet.domain.model.valueobjects;

import java.util.regex.Pattern;

/**
 * Dispatch code value object. Format: DSP- followed by 6 digits.
 *
 * @param code the dispatch code
 * @author Fabrizio Rafael Tello Palacios
 */
public record DispatchCode(String code) {

    private static final Pattern FORMAT = Pattern.compile("^DSP-[0-9]{6}$");

    public DispatchCode {
        if (code == null || code.isBlank() || !FORMAT.matcher(code).matches()) {
            throw new IllegalArgumentException("dispatchCode.invalid");
        }
    }
}
