package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.util.XML11Char;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11NMTOKENDatatypeValidator extends NMTOKENDatatypeValidator {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator, com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator
    public void validate(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        if (!XML11Char.isXML11ValidNmtoken(str)) {
            throw new InvalidDatatypeValueException("NMTOKENInvalid", new Object[]{str});
        }
    }
}
