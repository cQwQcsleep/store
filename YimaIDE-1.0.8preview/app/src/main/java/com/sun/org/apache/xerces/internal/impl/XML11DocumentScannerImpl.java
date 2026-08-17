package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11DocumentScannerImpl extends XMLDocumentScannerImpl {
    private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
    private final XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
    private final XMLStringBuffer fStringBuffer3 = new XMLStringBuffer();

    public String getVersionNotSupportedKey() {
        return "VersionNotSupported11";
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public boolean isInvalid(int i) {
        return XML11Char.isXML11Invalid(i);
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

    public boolean scanAttributeValue(XMLString xMLString, XMLString xMLString2, String str, boolean z, String str2, boolean z2) throws IOException, XNIException {
        int iIsUnchangedByNormalization;
        int iPeekChar = this.fEntityScanner.peekChar();
        if (iPeekChar != 39 && iPeekChar != 34) {
            reportFatalError("OpenQuoteExpected", new Object[]{str2, str});
        }
        XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
        XMLScanner.NameType nameType = XMLScanner.NameType.ATTRIBUTE;
        xMLEntityScanner.scanChar(nameType);
        int i = this.fEntityDepth;
        int iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString, z2);
        boolean z3 = false;
        if (iScanLiteral == iPeekChar) {
            iIsUnchangedByNormalization = isUnchangedByNormalization(xMLString);
            if (iIsUnchangedByNormalization == -1) {
                xMLString2.setValues(xMLString);
                if (this.fEntityScanner.scanChar(nameType) != iPeekChar) {
                    reportFatalError("CloseQuoteExpected", new Object[]{str2, str});
                }
                return true;
            }
        } else {
            iIsUnchangedByNormalization = 0;
        }
        this.fStringBuffer2.clear();
        this.fStringBuffer2.append(xMLString);
        normalizeWhitespace(xMLString, iIsUnchangedByNormalization);
        if (iScanLiteral != iPeekChar) {
            this.fScanningAttribute = true;
            this.fStringBuffer.clear();
            while (true) {
                this.fStringBuffer.append(xMLString);
                if (iScanLiteral == 38) {
                    XMLEntityScanner xMLEntityScanner2 = this.fEntityScanner;
                    XMLScanner.NameType nameType2 = XMLScanner.NameType.REFERENCE;
                    xMLEntityScanner2.skipChar(38, nameType2);
                    if (i == this.fEntityDepth) {
                        this.fStringBuffer2.append('&');
                    }
                    if (this.fEntityScanner.skipChar(35, nameType2)) {
                        if (i == this.fEntityDepth) {
                            this.fStringBuffer2.append('#');
                        }
                        scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2);
                    } else {
                        String strScanName = this.fEntityScanner.scanName(nameType2);
                        if (strScanName == null) {
                            reportFatalError("NameRequiredInReference", null);
                        } else if (i == this.fEntityDepth) {
                            this.fStringBuffer2.append(strScanName);
                        }
                        if (!this.fEntityScanner.skipChar(59, nameType2)) {
                            reportFatalError("SemicolonRequiredInReference", new Object[]{strScanName});
                        } else if (i == this.fEntityDepth) {
                            this.fStringBuffer2.append(';');
                        }
                        if (resolveCharacter(strScanName, this.fStringBuffer)) {
                            checkEntityLimit(z3, this.fEntityScanner.fCurrentEntity.name, 1);
                        } else if (this.fEntityManager.isExternalEntity(strScanName)) {
                            reportFatalError("ReferenceToExternalEntity", new Object[]{strScanName});
                        } else {
                            if (!this.fEntityManager.isDeclaredEntity(strScanName)) {
                                if (!z) {
                                    reportFatalError("EntityNotDeclared", new Object[]{strScanName});
                                } else if (this.fValidation) {
                                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[]{strScanName}, (short) 1);
                                }
                            }
                            this.fEntityManager.startEntity(true, strScanName, true);
                        }
                    }
                } else if (iScanLiteral == 60) {
                    reportFatalError("LessthanInAttValue", new Object[]{str2, str});
                    this.fEntityScanner.scanChar(null);
                    if (i == this.fEntityDepth) {
                        this.fStringBuffer2.append((char) iScanLiteral);
                    }
                } else if (iScanLiteral == 37 || iScanLiteral == 93) {
                    this.fEntityScanner.scanChar(null);
                    char c = (char) iScanLiteral;
                    this.fStringBuffer.append(c);
                    if (i == this.fEntityDepth) {
                        this.fStringBuffer2.append(c);
                    }
                } else {
                    int i2 = -1;
                    if (iScanLiteral != -1) {
                        if (XMLChar.isHighSurrogate(iScanLiteral)) {
                            this.fStringBuffer3.clear();
                            if (scanSurrogates(this.fStringBuffer3)) {
                                this.fStringBuffer.append(this.fStringBuffer3);
                                if (i == this.fEntityDepth) {
                                    this.fStringBuffer2.append(this.fStringBuffer3);
                                }
                            }
                        } else {
                            i2 = -1;
                        }
                    }
                    if (iScanLiteral != i2 && isInvalidLiteral(iScanLiteral)) {
                        reportFatalError("InvalidCharInAttValue", new Object[]{str2, str, Integer.toString(iScanLiteral, 16)});
                        this.fEntityScanner.scanChar(null);
                        if (i == this.fEntityDepth) {
                            this.fStringBuffer2.append((char) iScanLiteral);
                        }
                    }
                }
                iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString, z2);
                if (i == this.fEntityDepth) {
                    this.fStringBuffer2.append(xMLString);
                }
                normalizeWhitespace(xMLString);
                if (iScanLiteral == iPeekChar && i == this.fEntityDepth) {
                    break;
                }
                z3 = false;
            }
            this.fStringBuffer.append(xMLString);
            xMLString.setValues(this.fStringBuffer);
            this.fScanningAttribute = false;
        }
        xMLString2.setValues(this.fStringBuffer2);
        if (this.fEntityScanner.scanChar(null) != iPeekChar) {
            reportFatalError("CloseQuoteExpected", new Object[]{str2, str});
        }
        return xMLString2.equals(xMLString.ch, xMLString.offset, xMLString.length);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl
    public int scanContent(XMLStringBuffer xMLStringBuffer) throws IOException, XNIException {
        XMLString xMLString = this.fTempString;
        xMLString.length = 0;
        int iScanContent = this.fEntityScanner.scanContent(xMLString);
        xMLStringBuffer.append(this.fTempString);
        if (iScanContent == 13 || iScanContent == 133 || iScanContent == 8232) {
            this.fEntityScanner.scanChar(null);
            xMLStringBuffer.append((char) iScanContent);
            iScanContent = -1;
        }
        if (iScanContent != 93) {
            return iScanContent;
        }
        xMLStringBuffer.append((char) this.fEntityScanner.scanChar(null));
        this.fInScanContent = true;
        if (this.fEntityScanner.skipChar(93, null)) {
            xMLStringBuffer.append(']');
            while (this.fEntityScanner.skipChar(93, null)) {
                xMLStringBuffer.append(']');
            }
            if (this.fEntityScanner.skipChar(62, null)) {
                reportFatalError("CDEndInContent", null);
            }
        }
        this.fInScanContent = false;
        return -1;
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
