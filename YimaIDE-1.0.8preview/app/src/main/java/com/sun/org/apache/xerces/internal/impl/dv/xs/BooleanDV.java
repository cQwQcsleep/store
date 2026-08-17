package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BooleanDV extends TypeValidator {
    private static final String[] fValueSpace = {"false", "true", "0", "1"};

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        String[] strArr = fValueSpace;
        if (str.equals(strArr[0]) || str.equals(strArr[2])) {
            return Boolean.FALSE;
        }
        if (str.equals(strArr[1]) || str.equals(strArr[3])) {
            return Boolean.TRUE;
        }
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, "boolean"});
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public short getAllowedFacets() {
        return (short) 24;
    }
}
