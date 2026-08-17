package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.datatypes.XSQName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class QNameDV extends TypeValidator {
    private static final String EMPTY_STRING = "".intern();

    public static final class XQName extends QName implements XSQName {
        public XQName(String str, String str2, String str3, String str4) {
            setValues(str, str2, str3, str4);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.QName
        public boolean equals(Object obj) {
            if (obj instanceof QName) {
                QName qName = (QName) obj;
                if (this.uri == qName.uri && this.localpart == qName.localpart) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSQName
        public javax.xml.namespace.QName getJAXPQName() {
            return new javax.xml.namespace.QName(this.uri, this.localpart, this.prefix);
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSQName
        public QName getXNIQName() {
            return this;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.QName
        public synchronized String toString() {
            return this.rawname;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        String symbol;
        String strSubstring;
        int iIndexOf = str.indexOf(":");
        if (iIndexOf > 0) {
            symbol = validationContext.getSymbol(str.substring(0, iIndexOf));
            strSubstring = str.substring(iIndexOf + 1);
        } else {
            symbol = EMPTY_STRING;
            strSubstring = str;
        }
        if (symbol.length() > 0 && !XMLChar.isValidNCName(symbol)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_QNAME});
        }
        if (!XMLChar.isValidNCName(strSubstring)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_QNAME});
        }
        String uri = validationContext.getURI(symbol);
        if (symbol.length() <= 0 || uri != null) {
            return new XQName(symbol, validationContext.getSymbol(strSubstring), validationContext.getSymbol(str), uri);
        }
        throw new InvalidDatatypeValueException("UndeclaredPrefix", new Object[]{str, symbol});
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public short getAllowedFacets() {
        return (short) 2079;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public int getDataLength(Object obj) {
        return ((XQName) obj).rawname.length();
    }
}
