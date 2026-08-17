package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ListDatatypeValidator implements DatatypeValidator {
    DatatypeValidator fItemValidator;

    public ListDatatypeValidator(DatatypeValidator datatypeValidator) {
        this.fItemValidator = datatypeValidator;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator
    public void validate(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
        if (stringTokenizer.countTokens() == 0) {
            throw new InvalidDatatypeValueException("EmptyList", null);
        }
        while (stringTokenizer.hasMoreTokens()) {
            this.fItemValidator.validate(stringTokenizer.nextToken(), validationContext);
        }
    }
}
