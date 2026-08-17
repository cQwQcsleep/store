package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.util.XMLChar;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IDDV extends TypeValidator {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public void checkExtraRules(Object obj, ValidationContext validationContext) throws InvalidDatatypeValueException {
        String str = (String) obj;
        if (validationContext.isIdDeclared(str)) {
            throw new InvalidDatatypeValueException("cvc-id.2", new Object[]{str});
        }
        validationContext.addId(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        if (XMLChar.isValidNCName(str)) {
            return str;
        }
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_NCNAME});
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public short getAllowedFacets() {
        return (short) 2079;
    }
}
