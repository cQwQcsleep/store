package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.xml.internal.stream.Entity;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11EntityScanner extends XMLEntityScanner {
    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public int peekChar() throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        char c = scannedEntity2.ch[scannedEntity2.position];
        if (scannedEntity2.isExternal() && (c == '\r' || c == 133 || c == 8232)) {
            return 10;
        }
        return c;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0076  */
    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public int scanChar(XMLScanner.NameType nameType) throws IOException {
        boolean zIsExternal;
        Entity.ScannedEntity scannedEntity;
        int i;
        Entity.ScannedEntity scannedEntity2;
        Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
        int i2 = 0;
        if (scannedEntity3.position == scannedEntity3.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
        int i3 = scannedEntity4.position;
        char[] cArr = scannedEntity4.ch;
        scannedEntity4.position = i3 + 1;
        char c = cArr[i3];
        if (c != '\n') {
            if ((c == '\r' || c == 133 || c == 8232) && (zIsExternal = scannedEntity4.isExternal())) {
            }
            scannedEntity2 = this.fCurrentEntity;
            scannedEntity2.columnNumber++;
            if (!this.detectingVersion) {
                checkEntityLimit(nameType, scannedEntity2, i3, scannedEntity2.position - i3);
            }
            return c;
        }
        zIsExternal = false;
        Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
        scannedEntity5.lineNumber++;
        scannedEntity5.columnNumber = 1;
        if (scannedEntity5.position == scannedEntity5.count) {
            invokeListeners(1);
            this.fCurrentEntity.ch[0] = c;
            load(1, true, false);
        } else {
            i2 = i3;
        }
        if (c == '\r' && zIsExternal && (i = (scannedEntity = this.fCurrentEntity).position) < scannedEntity.count) {
            char[] cArr2 = scannedEntity.ch;
            scannedEntity.position = i + 1;
            char c2 = cArr2[i];
            if (c2 != '\n' && c2 != 133) {
                scannedEntity.position = i;
            }
        }
        i3 = i2;
        c = '\n';
        scannedEntity2 = this.fCurrentEntity;
        scannedEntity2.columnNumber++;
        if (!this.detectingVersion) {
            checkEntityLimit(nameType, scannedEntity2, i3, scannedEntity2.position - i3);
        }
        return c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [int] */
    /* JADX WARN: Type inference failed for: r11v4, types: [char] */
    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public int scanContent(XMLString xMLString) throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        int i = scannedEntity.position;
        int i2 = scannedEntity.count;
        if (i == i2) {
            load(0, true, true);
        } else if (i == i2 - 1) {
            invokeListeners(1);
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            char[] cArr = scannedEntity2.ch;
            cArr[0] = cArr[scannedEntity2.count - 1];
            load(1, false, false);
            Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
            scannedEntity3.position = 0;
            scannedEntity3.startPosition = 0;
        }
        boolean zNormalizeNewlines = normalizeNewlines((short) 2, xMLString, false, false, null);
        char c = -1;
        if (zNormalizeNewlines) {
            return -1;
        }
        boolean zIsExternal = this.fCurrentEntity.isExternal();
        if (!zIsExternal) {
            while (true) {
                Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                int i3 = scannedEntity4.position;
                if (i3 >= scannedEntity4.count) {
                    break;
                }
                char[] cArr2 = scannedEntity4.ch;
                scannedEntity4.position = i3 + 1;
                if (!XML11Char.isXML11InternalEntityContent(cArr2[i3])) {
                    this.fCurrentEntity.position--;
                    break;
                }
            }
        } else {
            while (true) {
                Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                int i4 = scannedEntity5.position;
                if (i4 >= scannedEntity5.count) {
                    break;
                }
                char[] cArr3 = scannedEntity5.ch;
                scannedEntity5.position = i4 + 1;
                char c2 = cArr3[i4];
                if (!XML11Char.isXML11Content(c2) || c2 == 133 || c2 == 8232) {
                    this.fCurrentEntity.position--;
                    break;
                }
            }
        }
        Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
        int i5 = scannedEntity6.position;
        int i6 = this.offset;
        int i7 = i5 - i6;
        scannedEntity6.columnNumber += i7 - this.newlines;
        if (!this.counted) {
            checkEntityLimit(null, scannedEntity6, i6, i7);
        }
        xMLString.setValues(this.fCurrentEntity.ch, this.offset, i7);
        Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
        int i8 = scannedEntity7.position;
        if (i8 == scannedEntity7.count || !(((c = scannedEntity7.ch[i8]) == 13 || c == 133 || c == 8232) && zIsExternal)) {
            return c;
        }
        return 10;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public boolean scanData(String str, XMLStringBuffer xMLStringBuffer, int i) throws IOException {
        Entity.ScannedEntity scannedEntity;
        int i2;
        int i3;
        int length = str.length();
        char cCharAt = str.charAt(0);
        boolean zIsExternal = this.fCurrentEntity.isExternal();
        boolean z = false;
        do {
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            if (scannedEntity2.position == scannedEntity2.count) {
                load(0, true, false);
            }
            boolean zLoad = false;
            while (true) {
                scannedEntity = this.fCurrentEntity;
                i2 = scannedEntity.position;
                i3 = scannedEntity.count;
                if (i2 < i3 - length || zLoad) {
                    break;
                }
                char[] cArr = scannedEntity.ch;
                System.arraycopy(cArr, i2, cArr, 0, i3 - i2);
                Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                zLoad = load(scannedEntity3.count - scannedEntity3.position, false, false);
                Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                scannedEntity4.position = 0;
                scannedEntity4.startPosition = 0;
            }
            if (i2 >= i3 - length) {
                int i4 = i3 - i2;
                checkEntityLimit(XMLScanner.NameType.COMMENT, scannedEntity, i2, i4);
                Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                xMLStringBuffer.append(scannedEntity5.ch, scannedEntity5.position, i4);
                Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
                int i5 = scannedEntity6.columnNumber;
                int i6 = scannedEntity6.count;
                scannedEntity6.columnNumber = i5 + i6;
                scannedEntity6.baseCharOffset += scannedEntity6.position - scannedEntity6.startPosition;
                scannedEntity6.position = i6;
                scannedEntity6.startPosition = i6;
                load(0, true, false);
                return false;
            }
            if (normalizeNewlines((short) 2, xMLStringBuffer, true, false, XMLScanner.NameType.COMMENT)) {
                return true;
            }
            while (true) {
                Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
                int i7 = scannedEntity7.position;
                if (i7 >= scannedEntity7.count) {
                    break;
                }
                char[] cArr2 = scannedEntity7.ch;
                scannedEntity7.position = i7 + 1;
                char c = cArr2[i7];
                if (c == cCharAt) {
                    for (int i8 = 1; i8 < length; i8++) {
                        Entity.ScannedEntity scannedEntity8 = this.fCurrentEntity;
                        int i9 = scannedEntity8.position;
                        if (i9 == scannedEntity8.count) {
                            scannedEntity8.position = i9 - i8;
                            break;
                        }
                        char[] cArr3 = scannedEntity8.ch;
                        scannedEntity8.position = i9 + 1;
                        if (str.charAt(i8) != cArr3[i9]) {
                            this.fCurrentEntity.position--;
                            break;
                        }
                    }
                    if (this.fCurrentEntity.position == i7 + length) {
                        z = true;
                        break;
                    }
                    if (i <= 0 && (xMLStringBuffer.length + this.fCurrentEntity.position) - this.offset >= i) {
                        break;
                    }
                } else {
                    if ((zIsExternal && (c == '\n' || c == '\r' || c == 133 || c == 8232)) || (!zIsExternal && c == '\n')) {
                        scannedEntity7.position = i7;
                        break;
                    }
                    if ((zIsExternal && !XML11Char.isXML11ValidLiteral(c)) || (!zIsExternal && !XML11Char.isXML11Valid(c))) {
                        Entity.ScannedEntity scannedEntity9 = this.fCurrentEntity;
                        int i10 = scannedEntity9.position - 1;
                        scannedEntity9.position = i10;
                        int i11 = this.offset;
                        int i12 = i10 - i11;
                        scannedEntity9.columnNumber += i12 - this.newlines;
                        checkEntityLimit(XMLScanner.NameType.COMMENT, scannedEntity9, i11, i12);
                        xMLStringBuffer.append(this.fCurrentEntity.ch, this.offset, i12);
                        return true;
                    }
                    if (i <= 0) {
                    }
                }
            }
            Entity.ScannedEntity scannedEntity10 = this.fCurrentEntity;
            int i13 = scannedEntity10.position;
            int i14 = this.offset;
            int i15 = i13 - i14;
            scannedEntity10.columnNumber += i15 - this.newlines;
            checkEntityLimit(XMLScanner.NameType.COMMENT, scannedEntity10, i14, i15);
            if (z) {
                i15 -= length;
            }
            xMLStringBuffer.append(this.fCurrentEntity.ch, this.offset, i15);
            if ((i > 0 && xMLStringBuffer.length >= i) || z) {
                break;
            }
        } while (i == 0);
        return !z;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public int scanLiteral(int i, XMLString xMLString, boolean z) throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        int i2 = scannedEntity.position;
        int i3 = scannedEntity.count;
        if (i2 == i3) {
            load(0, true, true);
        } else if (i2 == i3 - 1) {
            invokeListeners(1);
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            char[] cArr = scannedEntity2.ch;
            cArr[0] = cArr[scannedEntity2.count - 1];
            load(1, false, false);
            Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
            scannedEntity3.startPosition = 0;
            scannedEntity3.position = 0;
        }
        if (normalizeNewlines((short) 2, xMLString, false, true, null)) {
            return -1;
        }
        if (!this.fCurrentEntity.isExternal()) {
            while (true) {
                Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                int i4 = scannedEntity4.position;
                if (i4 >= scannedEntity4.count) {
                    break;
                }
                char[] cArr2 = scannedEntity4.ch;
                scannedEntity4.position = i4 + 1;
                char c = cArr2[i4];
                if ((c == i && !scannedEntity4.literal) || c == '%' || !XML11Char.isXML11InternalEntityContent(c) || c == '\r') {
                    this.fCurrentEntity.position--;
                    break;
                }
            }
        } else {
            while (true) {
                Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                int i5 = scannedEntity5.position;
                if (i5 >= scannedEntity5.count) {
                    break;
                }
                char[] cArr3 = scannedEntity5.ch;
                scannedEntity5.position = i5 + 1;
                char c2 = cArr3[i5];
                if (c2 == i || c2 == '%' || !XML11Char.isXML11Content(c2) || c2 == 133 || c2 == 8232) {
                    this.fCurrentEntity.position--;
                    break;
                }
            }
        }
        Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
        int i6 = scannedEntity6.position;
        int i7 = this.offset;
        int i8 = i6 - i7;
        scannedEntity6.columnNumber += i8 - this.newlines;
        checkEntityLimit(null, scannedEntity6, i7, i8);
        if (z) {
            checkLimit(XMLSecurityManager.Limit.MAX_NAME_LIMIT, this.fCurrentEntity, this.offset, i8);
        }
        xMLString.setValues(this.fCurrentEntity.ch, this.offset, i8);
        Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
        int i9 = scannedEntity7.position;
        if (i9 == scannedEntity7.count) {
            return -1;
        }
        char c3 = scannedEntity7.ch[i9];
        if (c3 == i && scannedEntity7.literal) {
            return -1;
        }
        return c3;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:41:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:47:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:49:0x010a  */
    /* JADX WARN: Code duplicated, block: B:51:0x0115  */
    /* JADX WARN: Code duplicated, block: B:52:0x0121  */
    /* JADX WARN: Code duplicated, block: B:56:0x0137  */
    /* JADX WARN: Code duplicated, block: B:57:0x0138 A[PHI: r1
      0x0138: PHI (r1v14 int) = (r1v9 int), (r1v20 int) binds: [B:48:0x0108, B:56:0x0137] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:59:0x0146  */
    /* JADX WARN: Code duplicated, block: B:75:0x0191  */
    /* JADX WARN: Code duplicated, block: B:77:0x019a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:80:0x00f9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x00d7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x00cc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0184 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x012a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x017d A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x00be A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00f5 -> B:13:0x0046). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x017a -> B:13:0x0046). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:85:0x017d
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public java.lang.String scanNCName() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.org.apache.xerces.internal.impl.XML11EntityScanner.scanNCName():java.lang.String");
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:45:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:48:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:49:0x00fc A[PHI: r1
      0x00fc: PHI (r1v14 int) = (r1v9 int), (r1v20 int) binds: [B:44:0x00e6, B:48:0x00fb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:51:0x010a  */
    /* JADX WARN: Code duplicated, block: B:63:0x0138  */
    /* JADX WARN: Code duplicated, block: B:65:0x014d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:68:0x00da A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x00d1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:70:0x00c9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x012b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0124 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x00bb A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00d5 -> B:40:0x00d8). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0121 -> B:40:0x00d8). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:68:0x00da
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public java.lang.String scanName(com.sun.org.apache.xerces.internal.impl.XMLScanner.NameType r9) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.org.apache.xerces.internal.impl.XML11EntityScanner.scanName(com.sun.org.apache.xerces.internal.impl.XMLScanner$NameType):java.lang.String");
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public String scanNmtoken() throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        int i = 0;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        int i2 = this.fCurrentEntity.position;
        while (true) {
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            char c = scannedEntity2.ch[scannedEntity2.position];
            if (!XML11Char.isXML11Name(c)) {
                if (XML11Char.isXML11NameHighSurrogate(c)) {
                    Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                    int i3 = scannedEntity3.position + 1;
                    scannedEntity3.position = i3;
                    if (i3 == scannedEntity3.count) {
                        int i4 = i3 - i2;
                        invokeListeners(i4);
                        char[] cArr = this.fCurrentEntity.ch;
                        if (i4 == cArr.length) {
                            char[] cArr2 = new char[cArr.length << 1];
                            System.arraycopy(cArr, i2, cArr2, 0, i4);
                            this.fCurrentEntity.ch = cArr2;
                        } else {
                            System.arraycopy(cArr, i2, cArr, 0, i4);
                        }
                        if (load(i4, false, false)) {
                            Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                            scannedEntity4.startPosition--;
                            scannedEntity4.position--;
                            break;
                        }
                        i2 = 0;
                    }
                    Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                    char c2 = scannedEntity5.ch[scannedEntity5.position];
                    if (XMLChar.isLowSurrogate(c2) && XML11Char.isXML11Name(XMLChar.supplemental(c, c2))) {
                        Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
                        int i5 = scannedEntity6.position + 1;
                        scannedEntity6.position = i5;
                        if (i5 == scannedEntity6.count) {
                            int i6 = i5 - i2;
                            invokeListeners(i6);
                            char[] cArr3 = this.fCurrentEntity.ch;
                            if (i6 == cArr3.length) {
                                char[] cArr4 = new char[cArr3.length << 1];
                                System.arraycopy(cArr3, i2, cArr4, 0, i6);
                                this.fCurrentEntity.ch = cArr4;
                            } else {
                                System.arraycopy(cArr3, i2, cArr3, 0, i6);
                            }
                            if (load(i6, false, false)) {
                                break;
                            }
                            i2 = 0;
                        } else {
                            continue;
                        }
                    } else {
                        this.fCurrentEntity.position--;
                    }
                }
                i = i2;
                break;
            }
            Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
            int i7 = scannedEntity7.position + 1;
            scannedEntity7.position = i7;
            if (i7 == scannedEntity7.count) {
                int i8 = i7 - i2;
                invokeListeners(i8);
                char[] cArr5 = this.fCurrentEntity.ch;
                if (i8 == cArr5.length) {
                    char[] cArr6 = new char[cArr5.length << 1];
                    System.arraycopy(cArr5, i2, cArr6, 0, i8);
                    this.fCurrentEntity.ch = cArr6;
                } else {
                    System.arraycopy(cArr5, i2, cArr5, 0, i8);
                }
                if (load(i8, false, false)) {
                    break;
                }
                i2 = 0;
            } else {
                continue;
            }
        }
        Entity.ScannedEntity scannedEntity8 = this.fCurrentEntity;
        int i9 = scannedEntity8.position - i;
        scannedEntity8.columnNumber += i9;
        if (i9 > 0) {
            return this.fSymbolTable.addSymbol(scannedEntity8.ch, i, i9);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public boolean scanQName(QName qName, XMLScanner.NameType nameType) throws IOException {
        boolean z;
        String strAddSymbol;
        String strAddSymbol2;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        boolean z2 = true;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        int i = scannedEntity2.position;
        char c = scannedEntity2.ch[i];
        if (!XML11Char.isXML11NCNameStart(c)) {
            if (XML11Char.isXML11NameHighSurrogate(c)) {
                Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                int i2 = scannedEntity3.position + 1;
                scannedEntity3.position = i2;
                if (i2 == scannedEntity3.count) {
                    invokeListeners(1);
                    this.fCurrentEntity.ch[0] = c;
                    if (load(1, false, false)) {
                        Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                        scannedEntity4.startPosition--;
                        scannedEntity4.position--;
                        return false;
                    }
                    i = 0;
                }
                Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                char c2 = scannedEntity5.ch[scannedEntity5.position];
                if (XMLChar.isLowSurrogate(c2) && XML11Char.isXML11NCNameStart(XMLChar.supplemental(c, c2))) {
                    Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
                    int i3 = scannedEntity6.position + 1;
                    scannedEntity6.position = i3;
                    if (i3 == scannedEntity6.count) {
                        invokeListeners(2);
                        char[] cArr = this.fCurrentEntity.ch;
                        cArr[0] = c;
                        cArr[1] = c2;
                        if (load(2, false, false)) {
                            Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
                            scannedEntity7.columnNumber += 2;
                            String strAddSymbol3 = this.fSymbolTable.addSymbol(scannedEntity7.ch, 0, 2);
                            qName.setValues(null, strAddSymbol3, strAddSymbol3, null);
                            checkEntityLimit(nameType, this.fCurrentEntity, 0, 2);
                            return true;
                        }
                        i = 0;
                    }
                } else {
                    this.fCurrentEntity.position--;
                }
            }
            return false;
        }
        Entity.ScannedEntity scannedEntity8 = this.fCurrentEntity;
        int i4 = scannedEntity8.position + 1;
        scannedEntity8.position = i4;
        if (i4 == scannedEntity8.count) {
            invokeListeners(1);
            this.fCurrentEntity.ch[0] = c;
            if (load(1, false, false)) {
                Entity.ScannedEntity scannedEntity9 = this.fCurrentEntity;
                scannedEntity9.columnNumber++;
                String strAddSymbol4 = this.fSymbolTable.addSymbol(scannedEntity9.ch, 0, 1);
                qName.setValues(null, strAddSymbol4, strAddSymbol4, null);
                checkEntityLimit(nameType, this.fCurrentEntity, 0, 1);
                return true;
            }
            i = 0;
        }
        int i5 = -1;
        while (true) {
            Entity.ScannedEntity scannedEntity10 = this.fCurrentEntity;
            char c3 = scannedEntity10.ch[scannedEntity10.position];
            if (!XML11Char.isXML11Name(c3)) {
                if (!XML11Char.isXML11NameHighSurrogate(c3)) {
                    z = false;
                    break;
                }
                int iCheckBeforeLoad = checkBeforeLoad(this.fCurrentEntity, i, i5);
                if (iCheckBeforeLoad > 0) {
                    if (i5 != -1) {
                        i5 -= i;
                    }
                    if (load(iCheckBeforeLoad, false, false)) {
                        Entity.ScannedEntity scannedEntity11 = this.fCurrentEntity;
                        scannedEntity11.startPosition--;
                        scannedEntity11.position--;
                        z = true;
                        i = 0;
                        break;
                    }
                    i = 0;
                }
                Entity.ScannedEntity scannedEntity12 = this.fCurrentEntity;
                char c4 = scannedEntity12.ch[scannedEntity12.position];
                if (!XMLChar.isLowSurrogate(c4) || !XML11Char.isXML11Name(XMLChar.supplemental(c3, c4))) {
                    this.fCurrentEntity.position--;
                    z = true;
                    break;
                }
                int iCheckBeforeLoad2 = checkBeforeLoad(this.fCurrentEntity, i, i5);
                if (iCheckBeforeLoad2 <= 0) {
                    continue;
                } else {
                    if (i5 != -1) {
                        i5 -= i;
                    }
                    if (load(iCheckBeforeLoad2, false, false)) {
                        i = 0;
                        z = false;
                        break;
                    }
                    i = 0;
                }
            } else {
                if (c3 == ':') {
                    if (i5 != -1) {
                        z = false;
                        break;
                    }
                    Entity.ScannedEntity scannedEntity13 = this.fCurrentEntity;
                    int i6 = scannedEntity13.position;
                    checkLimit(XMLSecurityManager.Limit.MAX_NAME_LIMIT, scannedEntity13, i, i6 - i);
                    i5 = i6;
                }
                int iCheckBeforeLoad3 = checkBeforeLoad(this.fCurrentEntity, i, i5);
                if (iCheckBeforeLoad3 <= 0) {
                    continue;
                } else {
                    if (i5 != -1) {
                        i5 -= i;
                    }
                    if (load(iCheckBeforeLoad3, false, false)) {
                        i = 0;
                        z = false;
                        break;
                    }
                    i = 0;
                }
            }
        }
        Entity.ScannedEntity scannedEntity14 = this.fCurrentEntity;
        int i7 = scannedEntity14.position - i;
        scannedEntity14.columnNumber += i7;
        if (i7 <= 0) {
            return false;
        }
        String strAddSymbol5 = this.fSymbolTable.addSymbol(scannedEntity14.ch, i, i7);
        if (i5 != -1) {
            int i8 = i5 - i;
            XMLSecurityManager.Limit limit = XMLSecurityManager.Limit.MAX_NAME_LIMIT;
            checkLimit(limit, this.fCurrentEntity, i, i8);
            strAddSymbol2 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i, i8);
            int i9 = (i7 - i8) - 1;
            int i10 = i5 + 1;
            if (!XML11Char.isXML11NCNameStart(this.fCurrentEntity.ch[i10]) && (!XML11Char.isXML11NameHighSurrogate(this.fCurrentEntity.ch[i10]) || z)) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "IllegalQName", new Object[]{strAddSymbol5}, (short) 2);
            }
            checkLimit(limit, this.fCurrentEntity, i10, i9);
            strAddSymbol = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i10, i9);
        } else {
            z2 = true;
            checkLimit(XMLSecurityManager.Limit.MAX_NAME_LIMIT, this.fCurrentEntity, i, i7);
            strAddSymbol = strAddSymbol5;
            strAddSymbol2 = null;
        }
        qName.setValues(strAddSymbol2, strAddSymbol, strAddSymbol5, null);
        checkEntityLimit(nameType, this.fCurrentEntity, i, i7);
        return z2;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public boolean skipChar(int i, XMLScanner.NameType nameType) throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        int i2 = scannedEntity2.position;
        char c = scannedEntity2.ch[i2];
        if (c == i) {
            int i3 = i2 + 1;
            scannedEntity2.position = i3;
            if (i == 10) {
                scannedEntity2.lineNumber++;
                scannedEntity2.columnNumber = 1;
            } else {
                scannedEntity2.columnNumber++;
            }
            checkEntityLimit(nameType, scannedEntity2, i2, i3 - i2);
            return true;
        }
        if (i != 10 || ((c != 8232 && c != 133) || !scannedEntity2.isExternal())) {
            return false;
        }
        Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
        int i4 = scannedEntity3.position + 1;
        scannedEntity3.position = i4;
        scannedEntity3.lineNumber++;
        scannedEntity3.columnNumber = 1;
        checkEntityLimit(nameType, scannedEntity3, i2, i4 - i2);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:59:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:62:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:75:0x0105 A[SYNTHETIC] */
    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public boolean skipSpaces() throws IOException {
        boolean zLoad;
        Entity.ScannedEntity scannedEntity;
        boolean zLoad2;
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        if (scannedEntity2.position == scannedEntity2.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
        if (scannedEntity3 == null) {
            return false;
        }
        char[] cArr = scannedEntity3.ch;
        int i = scannedEntity3.position;
        char c = cArr[i];
        int i2 = i - 1;
        if (scannedEntity3.isExternal()) {
            if (XML11Char.isXML11Space(c)) {
                do {
                    if (c == '\n' || c == '\r' || c == 133 || c == 8232) {
                        Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                        scannedEntity4.lineNumber++;
                        scannedEntity4.columnNumber = 1;
                        if (scannedEntity4.position == scannedEntity4.count - 1) {
                            invokeListeners(1);
                            this.fCurrentEntity.ch[0] = c;
                            zLoad2 = load(1, true, false);
                            Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                            if (!zLoad2) {
                                scannedEntity5.startPosition = 0;
                                scannedEntity5.position = 0;
                            } else if (scannedEntity5 == null) {
                                return true;
                            }
                        } else {
                            zLoad2 = false;
                        }
                        if (c == '\r') {
                            Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
                            char[] cArr2 = scannedEntity6.ch;
                            int i3 = scannedEntity6.position;
                            int i4 = i3 + 1;
                            scannedEntity6.position = i4;
                            char c2 = cArr2[i4];
                            if (c2 != '\n' && c2 != 133) {
                                scannedEntity6.position = i3;
                            }
                        }
                    } else {
                        this.fCurrentEntity.columnNumber++;
                        zLoad2 = false;
                    }
                    Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
                    checkEntityLimit(null, scannedEntity7, i2, scannedEntity7.position - i2);
                    Entity.ScannedEntity scannedEntity8 = this.fCurrentEntity;
                    i2 = scannedEntity8.position;
                    if (!zLoad2) {
                        scannedEntity8.position = i2 + 1;
                    }
                    if (scannedEntity8.position == scannedEntity8.count) {
                        load(0, true, true);
                        if (this.fCurrentEntity == null) {
                            return true;
                        }
                    }
                    Entity.ScannedEntity scannedEntity9 = this.fCurrentEntity;
                    c = scannedEntity9.ch[scannedEntity9.position];
                } while (XML11Char.isXML11Space(c));
                return true;
            }
        } else if (XMLChar.isSpace(c)) {
            do {
                Entity.ScannedEntity scannedEntity10 = this.fCurrentEntity;
                if (c == '\n') {
                    scannedEntity10.lineNumber++;
                    scannedEntity10.columnNumber = 1;
                    if (scannedEntity10.position == scannedEntity10.count - 1) {
                        invokeListeners(1);
                        this.fCurrentEntity.ch[0] = c;
                        zLoad = load(1, true, false);
                        Entity.ScannedEntity scannedEntity11 = this.fCurrentEntity;
                        if (!zLoad) {
                            scannedEntity11.startPosition = 0;
                            scannedEntity11.position = 0;
                        } else if (scannedEntity11 == null) {
                            return true;
                        }
                    }
                    Entity.ScannedEntity scannedEntity12 = this.fCurrentEntity;
                    checkEntityLimit(null, scannedEntity12, i2, scannedEntity12.position - i2);
                    scannedEntity = this.fCurrentEntity;
                    i2 = scannedEntity.position;
                    if (!zLoad) {
                        scannedEntity.position = i2 + 1;
                    }
                    if (scannedEntity.position == scannedEntity.count) {
                        load(0, true, true);
                        if (this.fCurrentEntity == null) {
                            return true;
                        }
                    }
                    Entity.ScannedEntity scannedEntity13 = this.fCurrentEntity;
                    c = scannedEntity13.ch[scannedEntity13.position];
                } else {
                    scannedEntity10.columnNumber++;
                }
                zLoad = false;
                Entity.ScannedEntity scannedEntity14 = this.fCurrentEntity;
                checkEntityLimit(null, scannedEntity14, i2, scannedEntity14.position - i2);
                scannedEntity = this.fCurrentEntity;
                i2 = scannedEntity.position;
                if (!zLoad) {
                    scannedEntity.position = i2 + 1;
                }
                if (scannedEntity.position == scannedEntity.count) {
                    load(0, true, true);
                    if (this.fCurrentEntity == null) {
                        return true;
                    }
                }
                Entity.ScannedEntity scannedEntity15 = this.fCurrentEntity;
                c = scannedEntity15.ch[scannedEntity15.position];
            } while (XMLChar.isSpace(c));
            return true;
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityScanner
    public boolean skipString(String str) throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        int length = str.length();
        int i = this.fCurrentEntity.position;
        int i2 = 0;
        while (true) {
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            if (i2 >= length) {
                scannedEntity2.columnNumber += length;
                if (!this.detectingVersion) {
                    checkEntityLimit(null, scannedEntity2, i, length);
                }
                return true;
            }
            char[] cArr = scannedEntity2.ch;
            int i3 = scannedEntity2.position;
            scannedEntity2.position = i3 + 1;
            if (cArr[i3] != str.charAt(i2)) {
                this.fCurrentEntity.position -= i2 + 1;
                return false;
            }
            if (i2 < length - 1) {
                Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                if (scannedEntity3.position == scannedEntity3.count) {
                    invokeListeners(0);
                    Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                    char[] cArr2 = scannedEntity4.ch;
                    int i4 = i2 + 1;
                    System.arraycopy(cArr2, (scannedEntity4.count - i2) - 1, cArr2, 0, i4);
                    if (load(i4, false, false)) {
                        Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                        scannedEntity5.startPosition -= i4;
                        scannedEntity5.position -= i4;
                        return false;
                    }
                } else {
                    continue;
                }
            }
            i2++;
        }
    }
}
