package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xml.internal.utils.XMLCharacterRecognizer;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import java.util.Locale;
import javax.xml.transform.TransformerException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XString extends XObject implements XMLString {
    public static final XString EMPTYSTRING = new XString("");
    static final long serialVersionUID = 2020470518395094525L;

    public XString(Object obj) {
        super(obj);
    }

    private static boolean isSpace(char c) {
        return XMLCharacterRecognizer.isWhiteSpace(c);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean bool() {
        return str().length() > 0;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xpath.internal.XPathVisitable
    public void callVisitors(ExpressionOwner expressionOwner, XPathVisitor xPathVisitor) {
        xPathVisitor.visitStringLiteral(expressionOwner, this);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public char charAt(int i) {
        return str().charAt(i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int compareTo(XMLString xMLString) {
        int length = length();
        int length2 = xMLString.length();
        int iMin = Math.min(length, length2);
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = iMin - 1;
            if (iMin == 0) {
                return length - length2;
            }
            char cCharAt = charAt(i);
            char cCharAt2 = xMLString.charAt(i2);
            if (cCharAt != cCharAt2) {
                return cCharAt - cCharAt2;
            }
            i++;
            i2++;
            iMin = i3;
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int compareToIgnoreCase(XMLString xMLString) {
        throw new WrappedRuntimeException(new NoSuchMethodException("Java 1.2 method, not yet implemented"));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString concat(String str) {
        return new XString(str().concat(str));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public void dispatchAsComment(LexicalHandler lexicalHandler) throws SAXException {
        String str = str();
        lexicalHandler.comment(str.toCharArray(), 0, str.length());
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public void dispatchCharactersEvents(ContentHandler contentHandler) throws SAXException {
        String str = str();
        contentHandler.characters(str.toCharArray(), 0, str.length());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean endsWith(String str) {
        return str().endsWith(str);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean equals(XObject xObject) {
        int type = xObject.getType();
        try {
            if (4 == type) {
                return xObject.equals((XObject) this);
            }
            if (1 == type) {
                return xObject.bool() == bool();
            }
            if (2 == type) {
                return xObject.num() == num();
            }
            return xstr().equals(xObject.xstr());
        } catch (TransformerException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equalsIgnoreCase(String str) {
        return str().equalsIgnoreCase(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString fixWhiteSpace(boolean z, boolean z2, boolean z3) {
        boolean z4;
        char c;
        int length = length();
        char[] cArr = new char[length];
        int i = 0;
        getChars(0, length, cArr, 0);
        int i2 = 0;
        while (i2 < length && !isSpace(cArr[i2])) {
            i2++;
        }
        boolean z5 = false;
        boolean z6 = false;
        int i3 = i2;
        while (true) {
            z4 = true;
            if (i2 >= length) {
                break;
            }
            char c2 = cArr[i2];
            if (!isSpace(c2)) {
                cArr[i3] = c2;
                i3++;
                z5 = false;
            } else if (z5) {
                z5 = true;
                z6 = true;
            } else {
                if (' ' != c2) {
                    z6 = true;
                }
                int i4 = i3 + 1;
                cArr[i3] = ' ';
                if (!z3 || i2 == 0 || ((c = cArr[i2 - 1]) != '.' && c != '!' && c != '?')) {
                    z5 = true;
                }
                i3 = i4;
            }
            i2++;
        }
        if (z2 && 1 <= i3 && ' ' == cArr[i3 - 1]) {
            i3--;
            z6 = true;
        }
        if (z && i3 > 0 && ' ' == cArr[0]) {
            i = 1;
        } else {
            z4 = z6;
        }
        return z4 ? XMLStringFactoryImpl.getFactory().newstr(new String(cArr, i, i3 - i)) : this;
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public void getChars(int i, int i2, char[] cArr, int i3) {
        str().getChars(i, i2, cArr, i3);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public int getType() {
        return 3;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String getTypeString() {
        return "#STRING";
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean hasString() {
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int hashCode() {
        return str().hashCode();
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(XMLString xMLString) {
        return str().indexOf(xMLString.toString());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int lastIndexOf(int i) {
        return str().lastIndexOf(i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int length() {
        return str().length();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public double num() {
        return toDouble();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public int rtf(XPathContext xPathContext) {
        DTM dtmCreateDocumentFragment = xPathContext.createDocumentFragment();
        dtmCreateDocumentFragment.appendTextChild(str());
        return dtmCreateDocumentFragment.getDocument();
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(XMLString xMLString, int i) {
        int length = length();
        int length2 = xMLString.length();
        if (i < 0 || i > length - length2) {
            return false;
        }
        int i2 = 0;
        while (true) {
            length2--;
            if (length2 < 0) {
                return true;
            }
            if (charAt(i) != xMLString.charAt(i2)) {
                return false;
            }
            i++;
            i2++;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String str() {
        Object obj = this.m_obj;
        return obj != null ? (String) obj : "";
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString substring(int i) {
        return new XString(str().substring(i));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public double toDouble() {
        XMLString xMLStringTrim = trim();
        for (int i = 0; i < xMLStringTrim.length(); i++) {
            char cCharAt = xMLStringTrim.charAt(i);
            if (cCharAt != '-' && cCharAt != '.' && (cCharAt < '0' || cCharAt > '9')) {
                return Double.NaN;
            }
        }
        try {
            return Double.parseDouble(xMLStringTrim.toString());
        } catch (NumberFormatException unused) {
            return Double.NaN;
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString toLowerCase(Locale locale) {
        return new XString(str().toLowerCase(locale));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString toUpperCase(Locale locale) {
        return new XString(str().toUpperCase(locale));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString trim() {
        return new XString(str().trim());
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public XMLString xstr() {
        return this;
    }

    public XString(String str) {
        super(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int lastIndexOf(int i, int i2) {
        return str().lastIndexOf(i, i2);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int lastIndexOf(String str) {
        return str().lastIndexOf(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int lastIndexOf(String str, int i) {
        return str().lastIndexOf(str, i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(int i, int i2) {
        return str().indexOf(i, i2);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(String str) {
        return str().indexOf(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString substring(int i, int i2) {
        return new XString(str().substring(i, i2));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString toLowerCase() {
        return new XString(str().toLowerCase());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString toUpperCase() {
        return new XString(str().toUpperCase());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(int i) {
        return str().indexOf(i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(String str, int i) {
        return str().indexOf(str, i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(String str) {
        return startsWith(str, 0);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(String str, int i) {
        return str().startsWith(str, i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(XMLString xMLString) {
        return startsWith(xMLString, 0);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equals(String str) {
        return str().equals(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equals(XMLString xMLString) {
        if (xMLString == null) {
            return false;
        }
        if (!xMLString.hasString()) {
            return xMLString.equals(str());
        }
        return str().equals(xMLString.toString());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof XNodeSet) {
            return obj.equals(this);
        }
        if (obj instanceof XNumber) {
            return obj.equals(this);
        }
        return str().equals(obj.toString());
    }
}
