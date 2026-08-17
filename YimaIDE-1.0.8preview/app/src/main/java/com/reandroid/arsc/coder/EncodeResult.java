package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.HexUtil;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class EncodeResult {
    public static final EncodeResult RESOURCE_NOT_FOUND = new EncodeResult("RESOURCE NOT FOUND");
    private final String error;
    public final int value;
    public final ValueType valueType;

    public EncodeResult(ValueType valueType, int i, String str) {
        this.valueType = valueType;
        this.value = i;
        this.error = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            EncodeResult encodeResult = (EncodeResult) obj;
            String error = getError();
            if (error != null) {
                return error.equals(encodeResult.getError());
            }
            if (encodeResult.getError() == null && this.value == encodeResult.value && this.valueType == encodeResult.valueType) {
                return true;
            }
        }
        return false;
    }

    public String getError() {
        return this.error;
    }

    public int hashCode() {
        return Objects.hash(this.valueType, Integer.valueOf(this.value), getError());
    }

    public boolean isError() {
        return getError() != null;
    }

    public String toString() {
        String error = getError();
        if (error != null) {
            return error;
        }
        return this.valueType + ": " + HexUtil.toHex8(this.value);
    }

    public EncodeResult(ValueType valueType, int i) {
        this(valueType, i, null);
    }

    public EncodeResult(String str) {
        this(ValueType.NULL, -1, str);
    }
}
