package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.util.XMLChar;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IDREFDatatypeValidator implements DatatypeValidator {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator
    public void validate(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        if (validationContext.useNamespaces()) {
            if (!XMLChar.isValidNCName(str)) {
                throw new InvalidDatatypeValueException("IDREFInvalidWithNamespaces", new Object[]{str});
            }
        } else if (!XMLChar.isValidName(str)) {
            throw new InvalidDatatypeValueException("IDREFInvalid", new Object[]{str});
        }
        validationContext.addIdRef(str);
    }
}
