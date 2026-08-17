package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.util.XML11Char;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11IDDatatypeValidator extends IDDatatypeValidator {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.dtd.IDDatatypeValidator, com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator
    public void validate(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        if (validationContext.useNamespaces()) {
            if (!XML11Char.isXML11ValidNCName(str)) {
                throw new InvalidDatatypeValueException("IDInvalidWithNamespaces", new Object[]{str});
            }
        } else if (!XML11Char.isXML11ValidName(str)) {
            throw new InvalidDatatypeValueException("IDInvalid", new Object[]{str});
        }
        if (validationContext.isIdDeclared(str)) {
            throw new InvalidDatatypeValueException("IDNotUnique", new Object[]{str});
        }
        validationContext.addId(str);
    }
}
