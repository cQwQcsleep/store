package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xml.internal.utils.XMLCharacterRecognizer;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XStringForFSB extends XString {
    static final long serialVersionUID = -1533039186550674548L;
    protected int m_hash;
    int m_length;
    int m_start;
    protected String m_strCache;

    public XStringForFSB(FastStringBuffer fastStringBuffer, int i, int i2) {
        super(fastStringBuffer);
        this.m_strCache = null;
        this.m_hash = 0;
        this.m_start = i;
        this.m_length = i2;
        if (fastStringBuffer != null) {
            return;
        }
        w01.a(XPATHMessages.createXPATHMessage("ER_FASTSTRINGBUFFER_CANNOT_BE_NULL", null));
        throw null;
    }

    private static boolean isSpace(char c) {
        return XMLCharacterRecognizer.isWhiteSpace(c);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public void appendToFsb(FastStringBuffer fastStringBuffer) {
        fastStringBuffer.append(str());
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public char charAt(int i) {
        return fsb().charAt(this.m_start + i);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public int compareTo(XMLString xMLString) {
        int i = this.m_length;
        int length = xMLString.length();
        int iMin = Math.min(i, length);
        FastStringBuffer fastStringBufferFsb = fsb();
        int i2 = this.m_start;
        int i3 = 0;
        while (true) {
            int i4 = iMin - 1;
            if (iMin == 0) {
                return i - length;
            }
            char cCharAt = fastStringBufferFsb.charAt(i2);
            char cCharAt2 = xMLString.charAt(i3);
            if (cCharAt != cCharAt2) {
                return cCharAt - cCharAt2;
            }
            i2++;
            i3++;
            iMin = i4;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public int compareToIgnoreCase(XMLString xMLString) {
        int i = this.m_length;
        int length = xMLString.length();
        int iMin = Math.min(i, length);
        FastStringBuffer fastStringBufferFsb = fsb();
        int i2 = this.m_start;
        int i3 = 0;
        while (true) {
            int i4 = iMin - 1;
            if (iMin == 0) {
                return i - length;
            }
            char lowerCase = Character.toLowerCase(fastStringBufferFsb.charAt(i2));
            char lowerCase2 = Character.toLowerCase(xMLString.charAt(i3));
            if (lowerCase != lowerCase2) {
                return lowerCase - lowerCase2;
            }
            i2++;
            i3++;
            iMin = i4;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString concat(String str) {
        return new XString(str().concat(str));
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public void dispatchAsComment(LexicalHandler lexicalHandler) throws SAXException {
        fsb().sendSAXComment(lexicalHandler, this.m_start, this.m_length);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xpath.internal.objects.XObject
    public void dispatchCharactersEvents(ContentHandler contentHandler) throws SAXException {
        fsb().sendSAXcharacters(contentHandler, this.m_start, this.m_length);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xpath.internal.objects.XObject
    public boolean equals(XObject xObject) {
        if (this == xObject) {
            return true;
        }
        if (xObject.getType() == 2) {
            return xObject.equals((XObject) this);
        }
        String str = xObject.str();
        int i = this.m_length;
        if (i != str.length()) {
            return false;
        }
        FastStringBuffer fastStringBufferFsb = fsb();
        int i2 = this.m_start;
        int i3 = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
                return true;
            }
            if (fastStringBufferFsb.charAt(i2) != str.charAt(i3)) {
                return false;
            }
            i2++;
            i3++;
            i = i4;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equalsIgnoreCase(String str) {
        if (this.m_length == str.length()) {
            return str().equalsIgnoreCase(str);
        }
        return false;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString fixWhiteSpace(boolean z, boolean z2, boolean z3) {
        boolean z4;
        char c;
        int i = this.m_length;
        int i2 = this.m_start + i;
        char[] cArr = new char[i];
        FastStringBuffer fastStringBufferFsb = fsb();
        int i3 = this.m_start;
        int i4 = 0;
        int i5 = 0;
        boolean z5 = false;
        boolean z6 = false;
        while (true) {
            z4 = true;
            if (i3 >= i2) {
                break;
            }
            char cCharAt = fastStringBufferFsb.charAt(i3);
            if (!isSpace(cCharAt)) {
                cArr[i5] = cCharAt;
                i5++;
                z5 = false;
            } else if (z5) {
                z5 = true;
                z6 = true;
            } else {
                if (' ' != cCharAt) {
                    z6 = true;
                }
                int i6 = i5 + 1;
                cArr[i5] = ' ';
                if (!z3 || i6 == 0 || ((c = cArr[i5]) != '.' && c != '!' && c != '?')) {
                    z5 = true;
                }
                i5 = i6;
            }
            i3++;
        }
        if (z2 && 1 <= i5 && ' ' == cArr[i5 - 1]) {
            i5--;
            z6 = true;
        }
        if (z && i5 > 0 && ' ' == cArr[0]) {
            i4 = 1;
        } else {
            z4 = z6;
        }
        return z4 ? XMLStringFactoryImpl.getFactory().newstr(cArr, i4, i5 - i4) : this;
    }

    public FastStringBuffer fsb() {
        return (FastStringBuffer) this.m_obj;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public void getChars(int i, int i2, char[] cArr, int i3) {
        int length = i2 - i;
        int i4 = this.m_length;
        if (length > i4) {
            length = i4;
        }
        if (length > cArr.length - i3) {
            length = cArr.length - i3;
        }
        int i5 = this.m_start + i + length;
        FastStringBuffer fastStringBufferFsb = fsb();
        int i6 = i + this.m_start;
        while (i6 < i5) {
            cArr[i3] = fastStringBufferFsb.charAt(i6);
            i6++;
            i3++;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public boolean hasString() {
        return this.m_strCache != null;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(int i, int i2) {
        int i3 = this.m_start + this.m_length;
        FastStringBuffer fastStringBufferFsb = fsb();
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.m_length) {
            return -1;
        }
        for (int i4 = this.m_start + i2; i4 < i3; i4++) {
            if (fastStringBufferFsb.charAt(i4) == i) {
                return i4 - this.m_start;
            }
        }
        return -1;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public int length() {
        return this.m_length;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public Object object() {
        return str();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(XMLString xMLString, int i) {
        FastStringBuffer fastStringBufferFsb = fsb();
        int i2 = this.m_start + i;
        int length = xMLString.length();
        if (i < 0 || i > this.m_length - length) {
            return false;
        }
        int i3 = 0;
        while (true) {
            length--;
            if (length < 0) {
                return true;
            }
            if (fastStringBufferFsb.charAt(i2) != xMLString.charAt(i3)) {
                return false;
            }
            i2++;
            i3++;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xpath.internal.objects.XObject
    public String str() {
        if (this.m_strCache == null) {
            this.m_strCache = fsb().getString(this.m_start, this.m_length);
        }
        return this.m_strCache;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString substring(int i, int i2) {
        int i3 = i2 - i;
        int i4 = this.m_length;
        if (i3 > i4) {
            i3 = i4;
        }
        if (i3 <= 0) {
            return XString.EMPTYSTRING;
        }
        return new XStringForFSB(fsb(), this.m_start + i, i3);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public double toDouble() {
        if (this.m_length == 0) {
            return Double.NaN;
        }
        String string = fsb().getString(this.m_start, this.m_length);
        int i = 0;
        while (i < this.m_length && XMLCharacterRecognizer.isWhiteSpace(string.charAt(i))) {
            i++;
        }
        if (i == this.m_length) {
            return Double.NaN;
        }
        if (string.charAt(i) == '-') {
            i++;
        }
        while (i < this.m_length) {
            char cCharAt = string.charAt(i);
            if (cCharAt == '.') {
                i++;
            } else {
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i++;
            }
        }
        while (i < this.m_length && XMLCharacterRecognizer.isWhiteSpace(string.charAt(i))) {
            i++;
        }
        if (i != this.m_length) {
            return Double.NaN;
        }
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException unused) {
            return Double.NaN;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString trim() {
        return fixWhiteSpace(true, true, false);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString substring(int i) {
        int i2 = this.m_length - i;
        if (i2 <= 0) {
            return XString.EMPTYSTRING;
        }
        return new XStringForFSB(fsb(), this.m_start + i, i2);
    }

    private XStringForFSB(String str) {
        super(str);
        this.m_strCache = null;
        this.m_hash = 0;
        throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_FSB_CANNOT_TAKE_STRING", null));
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(int i) {
        return indexOf(i, 0);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(XMLString xMLString) {
        return startsWith(xMLString, 0);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equals(XMLString xMLString) {
        if (this == xMLString) {
            return true;
        }
        int i = this.m_length;
        if (i != xMLString.length()) {
            return false;
        }
        FastStringBuffer fastStringBufferFsb = fsb();
        int i2 = this.m_start;
        int i3 = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
                return true;
            }
            if (fastStringBufferFsb.charAt(i2) != xMLString.charAt(i3)) {
                return false;
            }
            i2++;
            i3++;
            i = i4;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equals(String str) {
        int i = this.m_length;
        if (i != str.length()) {
            return false;
        }
        FastStringBuffer fastStringBufferFsb = fsb();
        int i2 = this.m_start;
        int i3 = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
                return true;
            }
            if (fastStringBufferFsb.charAt(i2) != str.charAt(i3)) {
                return false;
            }
            i2++;
            i3++;
            i = i4;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof XNumber) {
            return obj.equals(this);
        }
        if (obj instanceof XNodeSet) {
            return obj.equals(this);
        }
        if (obj instanceof XStringForFSB) {
            return equals((XMLString) obj);
        }
        return equals(obj.toString());
    }
}
