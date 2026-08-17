package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11DTDScannerImpl extends XMLDTDScannerImpl {
    private XMLStringBuffer fStringBuffer;

    public XML11DTDScannerImpl() {
        this.fStringBuffer = new XMLStringBuffer();
    }

    public String getVersionNotSupportedKey() {
        return "VersionNotSupported11";
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public boolean isInvalid(int i) {
        return !XML11Char.isXML11Valid(i);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public boolean isInvalidLiteral(int i) {
        return !XML11Char.isXML11ValidLiteral(i);
    }

    public int isUnchangedByNormalization(XMLString xMLString) {
        int i = xMLString.offset;
        int i2 = xMLString.length + i;
        while (i < i2) {
            if (XMLChar.isSpace(xMLString.ch[i])) {
                return i - xMLString.offset;
            }
            i++;
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public boolean isValidNCName(int i) {
        return XML11Char.isXML11NCName(i);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public boolean isValidNameChar(int i) {
        return XML11Char.isXML11Name(i);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public boolean isValidNameStartChar(int i) {
        return XML11Char.isXML11NameStart(i);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public boolean isValidNameStartHighSurrogate(int i) {
        return XML11Char.isXML11NameHighSurrogate(i);
    }

    public void normalizeWhitespace(XMLString xMLString, int i) {
        int i2 = xMLString.offset;
        int i3 = xMLString.length + i2;
        for (int i4 = i2 + i; i4 < i3; i4++) {
            if (XMLChar.isSpace(xMLString.ch[i4])) {
                xMLString.ch[i4] = ' ';
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public boolean scanPubidLiteral(XMLString xMLString) throws IOException, XNIException {
        int iScanChar = this.fEntityScanner.scanChar(null);
        if (iScanChar != 39 && iScanChar != 34) {
            reportFatalError("QuoteRequiredInPublicID", null);
            return false;
        }
        this.fStringBuffer.clear();
        boolean z = true;
        boolean z2 = true;
        while (true) {
            int iScanChar2 = this.fEntityScanner.scanChar(null);
            if (iScanChar2 == 32 || iScanChar2 == 10 || iScanChar2 == 13 || iScanChar2 == 133 || iScanChar2 == 8232) {
                if (!z) {
                    this.fStringBuffer.append(' ');
                    z = true;
                }
            } else {
                if (iScanChar2 == iScanChar) {
                    if (z) {
                        this.fStringBuffer.length--;
                    }
                    xMLString.setValues(this.fStringBuffer);
                    return z2;
                }
                if (XMLChar.isPubid(iScanChar2)) {
                    this.fStringBuffer.append((char) iScanChar2);
                    z = false;
                } else {
                    if (iScanChar2 == -1) {
                        reportFatalError("PublicIDUnterminated", null);
                        return false;
                    }
                    reportFatalError("InvalidCharInPublicID", new Object[]{Integer.toHexString(iScanChar2)});
                    z2 = false;
                }
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public boolean versionSupported(String str) {
        return str.equals(SerializerConstants.XMLVERSION11) || str.equals("1.0");
    }

    public XML11DTDScannerImpl(SymbolTable symbolTable, XMLErrorReporter xMLErrorReporter, XMLEntityManager xMLEntityManager) {
        super(symbolTable, xMLErrorReporter, xMLEntityManager);
        this.fStringBuffer = new XMLStringBuffer();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public void normalizeWhitespace(XMLString xMLString) {
        int i = xMLString.offset;
        int i2 = xMLString.length + i;
        while (i < i2) {
            if (XMLChar.isSpace(xMLString.ch[i])) {
                xMLString.ch[i] = ' ';
            }
            i++;
        }
    }
}
