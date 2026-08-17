package com.fasterxml.aalto.in;

import com.fasterxml.aalto.WFCException;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class InputBootstrapper {
    final ReaderConfig _config;
    protected int _inputProcessed = 0;
    protected int _inputRow = 0;
    protected int _inputRowStart = 0;
    int mDeclaredXmlVersion = 0;
    String mFoundEncoding;
    final char[] mKeyword;
    String mStandalone;

    public InputBootstrapper(ReaderConfig readerConfig) {
        this._config = readerConfig;
        this.mKeyword = readerConfig.allocSmallCBuffer(60);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int getWsOrChar(int i) throws XMLStreamException, IOException {
        int next = getNext();
        if (next == i) {
            return next;
        }
        if (next > 32) {
            reportUnexpectedChar(next, "; expected either '" + ((char) i) + "' or white space");
        }
        if (next == 10 || next == 13) {
            pushback();
        }
        return getNextAfterWs(false);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int handleEq(String str) throws XMLStreamException, IOException {
        int nextAfterWs = getNextAfterWs(false);
        if (nextAfterWs != 61) {
            reportUnexpectedChar(nextAfterWs, "; expected '=' after '" + str + "'");
        }
        int nextAfterWs2 = getNextAfterWs(false);
        if (nextAfterWs2 != 34 && nextAfterWs2 != 39) {
            reportUnexpectedChar(nextAfterWs2, "; expected a quote character enclosing value for '" + str + "'");
        }
        return nextAfterWs2;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final String readXmlEncoding() throws XMLStreamException, IOException {
        int iCheckKeyword = checkKeyword("encoding");
        if (iCheckKeyword != 0) {
            reportUnexpectedChar(iCheckKeyword, "encoding");
        }
        int quotedValue = readQuotedValue(this.mKeyword, handleEq("encoding"));
        if (quotedValue == 0) {
            reportPseudoAttrProblem("encoding", null, null, null);
        }
        char[] cArr = this.mKeyword;
        return quotedValue < 0 ? new String(cArr) : new String(cArr, 0, quotedValue);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final String readXmlStandalone() throws XMLStreamException, IOException {
        String str;
        int iCheckKeyword = checkKeyword("standalone");
        if (iCheckKeyword != 0) {
            reportUnexpectedChar(iCheckKeyword, "standalone");
        }
        int quotedValue = readQuotedValue(this.mKeyword, handleEq("standalone"));
        if (quotedValue == 2) {
            char[] cArr = this.mKeyword;
            if (cArr[0] == 'n' && cArr[1] == 'o') {
                return "no";
            }
        } else if (quotedValue == 3) {
            char[] cArr2 = this.mKeyword;
            if (cArr2[0] == 'y' && cArr2[1] == 'e' && cArr2[2] == 's') {
                return "yes";
            }
        }
        if (quotedValue < 0) {
            str = "'" + new String(this.mKeyword) + "[..]'";
        } else if (quotedValue == 0) {
            str = "<empty>";
        } else {
            str = "'" + new String(this.mKeyword, 0, quotedValue) + "'";
        }
        reportPseudoAttrProblem("standalone", str, "yes", "no");
        return str;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int readXmlVersion() throws XMLStreamException, IOException {
        String str;
        int iCheckKeyword = checkKeyword("version");
        if (iCheckKeyword != 0) {
            reportUnexpectedChar(iCheckKeyword, "version");
        }
        int quotedValue = readQuotedValue(this.mKeyword, handleEq("version"));
        if (quotedValue == 3) {
            char[] cArr = this.mKeyword;
            if (cArr[0] == '1' && cArr[1] == '.') {
                char c = cArr[2];
                if (c == '0') {
                    return Fcntl.S_IRUSR;
                }
                if (c == '1') {
                    return 272;
                }
            }
        }
        if (quotedValue < 0) {
            str = "'" + new String(this.mKeyword) + "[..]'";
        } else if (quotedValue == 0) {
            str = "<empty>";
        } else {
            str = "'" + new String(this.mKeyword, 0, quotedValue) + "'";
        }
        reportPseudoAttrProblem("version", str, "1.0", "1.1");
        return 0;
    }

    public abstract XmlScanner bootstrap() throws XMLStreamException;

    public abstract int checkKeyword(String str) throws XMLStreamException, IOException;

    public abstract Location getLocation();

    public abstract int getNext() throws XMLStreamException, IOException;

    public abstract int getNextAfterWs(boolean z) throws XMLStreamException, IOException;

    public abstract void pushback();

    public abstract int readQuotedValue(char[] cArr, int i) throws XMLStreamException, IOException;

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void readXmlDeclaration() throws XMLStreamException, IOException {
        int nextAfterWs = getNextAfterWs(false);
        if (nextAfterWs != 118) {
            reportUnexpectedChar(nextAfterWs, "; expected keyword 'version'");
        } else {
            this.mDeclaredXmlVersion = readXmlVersion();
            nextAfterWs = getWsOrChar(63);
        }
        if (nextAfterWs == 101) {
            this.mFoundEncoding = readXmlEncoding();
            nextAfterWs = getWsOrChar(63);
        }
        if (nextAfterWs == 115) {
            this.mStandalone = readXmlStandalone();
            nextAfterWs = getWsOrChar(63);
        }
        if (nextAfterWs != 63) {
            reportUnexpectedChar(nextAfterWs, "; expected \"?>\" end marker");
        }
        int next = getNext();
        if (next != 62) {
            reportUnexpectedChar(next, "; expected \"?>\" end marker");
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportEof() throws XMLStreamException {
        reportXmlProblem("Unexpected end-of-input in xml declaration");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportNull() throws XMLStreamException {
        reportXmlProblem("Illegal null byte/char in input stream");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void reportPseudoAttrProblem(String str, String str2, String str3, String str4) throws XMLStreamException {
        String str5;
        if (str3 == null) {
            str5 = XmlPullParser.NO_NAMESPACE;
        } else {
            str5 = "; expected \"" + str3 + "\" or \"" + str4 + "\"";
        }
        if (str2 == null || str2.length() == 0) {
            reportXmlProblem("Missing XML pseudo-attribute '" + str + "' value" + str5);
        }
        reportXmlProblem("Invalid XML pseudo-attribute '" + str + "' value " + str2 + str5);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportUnexpectedChar(int i, String str) throws XMLStreamException {
        String str2;
        char c = (char) i;
        if (Character.isISOControl(c)) {
            str2 = "Unexpected character (CTRL-CHAR, code " + i + ")" + str;
        } else {
            str2 = "Unexpected character '" + c + "' (code " + i + ")" + str;
        }
        reportXmlProblem(str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportXmlProblem(String str) throws XMLStreamException {
        throw new WFCException(str, getLocation());
    }
}
