package com.reandroid.xml.kxml2;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import defpackage.b78;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import jdk.xml.internal.JdkConstants;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class KXmlParser implements XmlPullParser, Closeable {
    private static final char[] ANY;
    private static final int ATTLISTDECL = 13;
    private static final char[] COMMENT_DOUBLE_DASH;
    private static final Map<String, String> DEFAULT_ENTITIES;
    private static final char[] DOUBLE_QUOTE;
    private static final int ELEMENTDECL = 11;
    private static final char[] EMPTY;
    private static final char[] END_CDATA;
    private static final char[] END_COMMENT;
    private static final char[] END_PROCESSING_INSTRUCTION;
    private static final int ENTITYDECL = 12;
    private static final String FEATURE_RELAXED = "http://xmlpull.org/v1/doc/features.html#relaxed";
    private static final char[] FIXED;
    private static final String ILLEGAL_TYPE = "Wrong event type";
    private static final char[] IMPLIED;
    private static final char[] NDATA;
    private static final char[] NOTATION;
    private static final int NOTATIONDECL = 14;
    private static final int PARAMETER_ENTITY_REF = 15;
    private static final String PROPERTY_LOCATION = "http://xmlpull.org/v1/doc/properties.html#location";
    private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone";
    private static final String PROPERTY_XMLDECL_VERSION = "http://xmlpull.org/v1/doc/properties.html#xmldecl-version";
    private static final char[] PUBLIC;
    private static final char[] REQUIRED;
    private static final char[] SINGLE_QUOTE;
    private static final char[] START_ATTLIST;
    private static final char[] START_CDATA;
    private static final char[] START_COMMENT;
    private static final char[] START_DOCTYPE;
    private static final char[] START_ELEMENT;
    private static final char[] START_ENTITY;
    private static final char[] START_NOTATION;
    private static final char[] START_PROCESSING_INSTRUCTION;
    private static final char[] SYSTEM;
    private static final String UNEXPECTED_EOF = "Unexpected EOF";
    private static final int XML_DECLARATION = 998;
    public static final /* synthetic */ int b = 0;
    private int attributeCount;
    private StringBuilder bufferCapture;
    private int bufferStartColumn;
    private int bufferStartLine;
    private Map<String, Map<String, String>> defaultAttributes;
    private boolean degenerated;
    private int depth;
    private Map<String, char[]> documentEntities;
    private String encoding;
    private String error;
    private boolean isWhitespace;
    private boolean keepNamespaceAttributes;
    private Object location;
    private boolean mClosedWithTag;
    private String name;
    private String namespace;
    private ContentSource nextContentSource;
    private boolean parsedTopLevelStartTag;
    private String prefix;
    private boolean processDocDecl;
    private boolean processNsp;
    private String publicId;
    private Reader reader;
    private boolean relaxed;
    private String rootElementName;
    private Boolean standalone;
    private String systemId;
    private String text;
    private int type;
    private boolean unresolved;
    private String version;
    private String[] elementStack = new String[16];
    private String[] nspStack = new String[8];
    private int[] nspCounts = new int[4];
    private char[] buffer = new char[8192];
    private int position = 0;
    private int limit = 0;
    private String[] attributes = new String[16];

    public static class ContentSource {
        private final char[] buffer;
        private final int limit;
        private final ContentSource next;
        private final int position;

        public ContentSource(ContentSource contentSource, char[] cArr, int i, int i2) {
            this.next = contentSource;
            this.buffer = cArr;
            this.position = i;
            this.limit = i2;
        }
    }

    public enum ValueContext {
        ATTRIBUTE,
        TEXT,
        ENTITY_DECLARATION
    }

    static {
        HashMap map = new HashMap();
        DEFAULT_ENTITIES = map;
        map.put("lt", "<");
        map.put("gt", ">");
        map.put("amp", "&");
        map.put("apos", "'");
        map.put("quot", "\"");
        START_COMMENT = new char[]{'<', '!', LocaleUtility.IETF_SEPARATOR, LocaleUtility.IETF_SEPARATOR};
        END_COMMENT = new char[]{LocaleUtility.IETF_SEPARATOR, LocaleUtility.IETF_SEPARATOR, '>'};
        COMMENT_DOUBLE_DASH = new char[]{LocaleUtility.IETF_SEPARATOR, LocaleUtility.IETF_SEPARATOR};
        START_CDATA = new char[]{'<', '!', '[', 'C', 'D', 'A', 'T', 'A', '['};
        END_CDATA = new char[]{']', ']', '>'};
        START_PROCESSING_INSTRUCTION = new char[]{'<', '?'};
        END_PROCESSING_INSTRUCTION = new char[]{'?', '>'};
        START_DOCTYPE = new char[]{'<', '!', 'D', 'O', 'C', 'T', 'Y', 'P', 'E'};
        SYSTEM = new char[]{'S', 'Y', 'S', 'T', 'E', 'M'};
        PUBLIC = new char[]{'P', 'U', 'B', 'L', 'I', 'C'};
        START_ELEMENT = new char[]{'<', '!', 'E', 'L', 'E', 'M', 'E', 'N', 'T'};
        START_ATTLIST = new char[]{'<', '!', 'A', 'T', 'T', 'L', 'I', 'S', 'T'};
        START_ENTITY = new char[]{'<', '!', 'E', 'N', 'T', 'I', 'T', 'Y'};
        START_NOTATION = new char[]{'<', '!', 'N', 'O', 'T', 'A', 'T', 'I', 'O', 'N'};
        EMPTY = new char[]{'E', 'M', 'P', 'T', 'Y'};
        ANY = new char[]{'A', 'N', 'Y'};
        NDATA = new char[]{'N', 'D', 'A', 'T', 'A'};
        NOTATION = new char[]{'N', 'O', 'T', 'A', 'T', 'I', 'O', 'N'};
        REQUIRED = new char[]{'R', 'E', 'Q', 'U', 'I', 'R', 'E', 'D'};
        IMPLIED = new char[]{'I', 'M', 'P', 'L', 'I', 'E', 'D'};
        FIXED = new char[]{'F', 'I', 'X', 'E', 'D'};
        SINGLE_QUOTE = new char[]{'\''};
        DOUBLE_QUOTE = new char[]{'\"'};
    }

    private boolean adjustNsp() throws XmlPullParserException {
        int i;
        int i2 = 0;
        boolean z = false;
        while (true) {
            i = this.attributeCount;
            String strSubstring = null;
            if (i2 >= (i << 2)) {
                break;
            }
            String str = this.attributes[i2 + 2];
            int iIndexOf = str.indexOf(58);
            if (iIndexOf != -1) {
                String strSubstring2 = str.substring(0, iIndexOf);
                strSubstring = str.substring(iIndexOf + 1);
                str = strSubstring2;
            } else {
                if (str.equals("xmlns")) {
                }
                i2 += 4;
            }
            if (str.equals("xmlns")) {
                int[] iArr = this.nspCounts;
                int i3 = this.depth;
                int i4 = iArr[i3];
                iArr[i3] = i4 + 1;
                int i5 = i4 << 1;
                String[] strArrEnsureCapacity = ensureCapacity(this.nspStack, i5 + 2);
                this.nspStack = strArrEnsureCapacity;
                strArrEnsureCapacity[i5] = strSubstring;
                String[] strArr = this.attributes;
                int i6 = i2 + 3;
                strArrEnsureCapacity[i5 + 1] = strArr[i6];
                if (strSubstring != null && strArr[i6].isEmpty()) {
                    checkRelaxed("illegal empty namespace");
                }
                boolean z2 = this.keepNamespaceAttributes;
                String[] strArr2 = this.attributes;
                if (z2) {
                    strArr2[i2] = "http://www.w3.org/2000/xmlns/";
                    z = true;
                } else {
                    int i7 = this.attributeCount - 1;
                    this.attributeCount = i7;
                    System.arraycopy(strArr2, i2 + 4, strArr2, i2, (i7 << 2) - i2);
                    i2 -= 4;
                }
            } else {
                z = true;
            }
            i2 += 4;
        }
        if (z) {
            for (int i8 = (i << 2) - 4; i8 >= 0; i8 -= 4) {
                int i9 = i8 + 2;
                String str2 = this.attributes[i9];
                int iIndexOf2 = str2.indexOf(58);
                if (iIndexOf2 == 0 && !this.relaxed) {
                    throw new XmlPullParserException("illegal attribute name: ".concat(str2), this, null);
                }
                if (iIndexOf2 != -1) {
                    String strSubstring3 = str2.substring(0, iIndexOf2);
                    String strSubstring4 = str2.substring(iIndexOf2 + 1);
                    String namespace = getNamespace(strSubstring3);
                    if (namespace == null && !this.relaxed) {
                        throw new XmlPullParserException("Undefined Prefix: ".concat(strSubstring3), this, null);
                    }
                    String[] strArr3 = this.attributes;
                    strArr3[i8] = namespace;
                    strArr3[i8 + 1] = strSubstring3;
                    strArr3[i9] = strSubstring4;
                }
            }
        }
        int iIndexOf3 = this.name.indexOf(58);
        if (iIndexOf3 == 0) {
            checkRelaxed("illegal tag name: " + this.name);
        }
        if (iIndexOf3 != -1) {
            this.prefix = this.name.substring(0, iIndexOf3);
            this.name = this.name.substring(iIndexOf3 + 1);
        }
        String namespace2 = getNamespace(this.prefix);
        this.namespace = namespace2;
        if (namespace2 == null) {
            if (this.prefix != null) {
                checkRelaxed("undefined prefix: " + this.prefix);
            }
            this.namespace = "";
        }
        return z;
    }

    private void checkRelaxed(String str) throws XmlPullParserException {
        if (!this.relaxed) {
            b78.a(str, this);
        } else if (this.error == null) {
            this.error = "Error: " + str;
        }
    }

    private void defineAttributeDefault(String str, String str2, String str3) {
        if (this.defaultAttributes == null) {
            this.defaultAttributes = new HashMap();
        }
        Map<String, String> map = this.defaultAttributes.get(str);
        if (map == null) {
            map = new HashMap<>();
            this.defaultAttributes.put(str, map);
        }
        map.put(str2, str3);
    }

    private String[] ensureCapacity(String[] strArr, int i) {
        if (strArr.length >= i) {
            return strArr;
        }
        String[] strArr2 = new String[i + 16];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    private boolean fillBuffer(int i) throws XmlPullParserException, IOException {
        int i2;
        int i3;
        while (this.nextContentSource != null) {
            if (this.position < this.limit) {
                b78.a("Unbalanced entity!", this);
                return false;
            }
            popContentSource();
            if (this.limit - this.position >= i) {
                return true;
            }
        }
        int i4 = 0;
        while (true) {
            i2 = this.position;
            if (i4 >= i2) {
                break;
            }
            if (this.buffer[i4] == '\n') {
                this.bufferStartLine++;
                this.bufferStartColumn = 0;
            } else {
                this.bufferStartColumn++;
            }
            i4++;
        }
        StringBuilder sb = this.bufferCapture;
        if (sb != null) {
            sb.append(this.buffer, 0, i2);
        }
        int i5 = this.limit;
        int i6 = this.position;
        if (i5 != i6) {
            int i7 = i5 - i6;
            this.limit = i7;
            char[] cArr = this.buffer;
            System.arraycopy(cArr, i6, cArr, 0, i7);
        } else {
            this.limit = 0;
        }
        this.position = 0;
        do {
            Reader reader = this.reader;
            char[] cArr2 = this.buffer;
            int i8 = this.limit;
            int i9 = reader.read(cArr2, i8, cArr2.length - i8);
            if (i9 == -1) {
                return false;
            }
            i3 = this.limit + i9;
            this.limit = i3;
        } while (i3 < i);
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:39:0x009f  */
    /* JADX WARN: Code duplicated, block: B:46:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:57:0x00ce A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:61:0x00da  */
    /* JADX WARN: Code duplicated, block: B:82:0x00cd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x00e5 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    private int next(boolean z) throws XmlPullParserException, IOException {
        int i;
        int iPeekType;
        String str;
        int i2;
        if (this.reader == null) {
            b78.a("setInput() must be called first.", this);
            return 0;
        }
        if (this.type == 3) {
            this.depth--;
        }
        if (this.degenerated) {
            this.degenerated = false;
            this.type = 3;
            return 3;
        }
        String str2 = this.error;
        if (str2 != null) {
            if (z) {
                this.text = str2;
                this.type = 9;
                this.error = null;
                return 9;
            }
            this.error = null;
        }
        int iPeekType2 = peekType(false);
        this.type = iPeekType2;
        if (iPeekType2 == 998) {
            readXmlDeclaration();
            this.type = peekType(false);
        }
        this.text = null;
        this.isWhitespace = true;
        this.prefix = null;
        this.name = null;
        this.namespace = null;
        this.attributeCount = -1;
        boolean z2 = !z;
        while (true) {
            int i3 = this.type;
            switch (i3) {
                case 1:
                    return i3;
                case 2:
                    parseStartTag(false, z2);
                    return this.type;
                case 3:
                    readEndTag();
                    return this.type;
                case 4:
                    this.text = readValue('<', !z, z2, ValueContext.TEXT);
                    if (this.depth == 0 && this.isWhitespace) {
                        this.type = 7;
                    }
                    if (this.depth != 0 && ((i2 = this.type) == 6 || i2 == 4 || i2 == 5)) {
                        b78.a("Unexpected token", this);
                        return 0;
                    }
                    i = this.type;
                    if (z) {
                        return i;
                    }
                    if (i == 7) {
                        this.text = null;
                    }
                    iPeekType = peekType(false);
                    str = this.text;
                    if (str == null && !str.isEmpty() && iPeekType < 4) {
                        this.type = 4;
                        return 4;
                    }
                    this.type = iPeekType;
                    break;
                    break;
                case 5:
                    read(START_CDATA);
                    this.text = readUntil(END_CDATA, true);
                    if (this.depth != 0) {
                    }
                    i = this.type;
                    if (z) {
                        return i;
                    }
                    if (i == 7) {
                        this.text = null;
                    }
                    iPeekType = peekType(false);
                    str = this.text;
                    if (str == null) {
                    }
                    this.type = iPeekType;
                    break;
                case 6:
                    if (z) {
                        StringBuilder sb = new StringBuilder();
                        readEntity(sb, true, z2, ValueContext.TEXT);
                        this.text = sb.toString();
                    } else {
                        this.text = readValue('<', !z, z2, ValueContext.TEXT);
                        if (this.depth == 0) {
                            this.type = 7;
                        }
                    }
                    if (this.depth != 0) {
                    }
                    i = this.type;
                    if (z) {
                        return i;
                    }
                    if (i == 7) {
                        this.text = null;
                    }
                    iPeekType = peekType(false);
                    str = this.text;
                    if (str == null) {
                    }
                    this.type = iPeekType;
                    break;
                case 7:
                default:
                    b78.a("Unexpected token", this);
                    return 0;
                case 8:
                    read(START_PROCESSING_INSTRUCTION);
                    String until = readUntil(END_PROCESSING_INSTRUCTION, z);
                    if (z) {
                        this.text = until;
                    }
                    if (this.depth != 0) {
                    }
                    i = this.type;
                    if (z) {
                        return i;
                    }
                    if (i == 7) {
                        this.text = null;
                    }
                    iPeekType = peekType(false);
                    str = this.text;
                    if (str == null) {
                    }
                    this.type = iPeekType;
                    break;
                case 9:
                    String comment = readComment(z);
                    if (z) {
                        this.text = comment;
                    }
                    if (this.depth != 0) {
                    }
                    i = this.type;
                    if (z) {
                        return i;
                    }
                    if (i == 7) {
                        this.text = null;
                    }
                    iPeekType = peekType(false);
                    str = this.text;
                    if (str == null) {
                    }
                    this.type = iPeekType;
                    break;
                case 10:
                    readDoctype(z);
                    if (this.parsedTopLevelStartTag) {
                        b78.a("Unexpected token", this);
                        return 0;
                    }
                    if (this.depth != 0) {
                    }
                    i = this.type;
                    if (z) {
                        return i;
                    }
                    if (i == 7) {
                        this.text = null;
                    }
                    iPeekType = peekType(false);
                    str = this.text;
                    if (str == null) {
                    }
                    this.type = iPeekType;
                    break;
            }
        }
    }

    private void parseStartTag(boolean z, boolean z2) throws XmlPullParserException, IOException {
        Map<String, String> map;
        if (!z) {
            read('<');
        }
        this.name = readName();
        this.attributeCount = 0;
        while (true) {
            skip();
            if (this.position >= this.limit && !fillBuffer(1)) {
                checkRelaxed(UNEXPECTED_EOF);
                return;
            }
            char[] cArr = this.buffer;
            int i = this.position;
            char c = cArr[i];
            if (!z) {
                if (c == '/') {
                    this.degenerated = true;
                    this.position = i + 1;
                    skip();
                    read('>');
                    break;
                }
                if (c == '>') {
                    this.position = i + 1;
                    break;
                }
            } else if (c == '?') {
                this.position = i + 1;
                read('>');
                return;
            }
            String name = readName();
            int i2 = this.attributeCount;
            this.attributeCount = i2 + 1;
            int i3 = i2 * 4;
            String[] strArrEnsureCapacity = ensureCapacity(this.attributes, i3 + 4);
            this.attributes = strArrEnsureCapacity;
            strArrEnsureCapacity[i3] = "";
            strArrEnsureCapacity[i3 + 1] = null;
            strArrEnsureCapacity[i3 + 2] = name;
            skip();
            if (this.position >= this.limit && !fillBuffer(1)) {
                checkRelaxed(UNEXPECTED_EOF);
                return;
            }
            char[] cArr2 = this.buffer;
            int i4 = this.position;
            if (cArr2[i4] == '=') {
                this.position = i4 + 1;
                skip();
                if (this.position >= this.limit && !fillBuffer(1)) {
                    checkRelaxed(UNEXPECTED_EOF);
                    return;
                }
                char[] cArr3 = this.buffer;
                int i5 = this.position;
                char c2 = cArr3[i5];
                if (c2 == '\'' || c2 == '\"') {
                    this.position = i5 + 1;
                } else {
                    if (!this.relaxed) {
                        b78.a("attr value delimiter missing!", this);
                        return;
                    }
                    c2 = ' ';
                }
                this.attributes[i3 + 3] = readValue(c2, true, z2, ValueContext.ATTRIBUTE);
                if (c2 != ' ' && peekCharacter() == c2) {
                    this.position++;
                }
            } else if (this.relaxed) {
                this.attributes[i3 + 3] = name;
            } else {
                checkRelaxed("Attr.value missing f. " + name);
                this.attributes[i3 + 3] = name;
            }
        }
        int i6 = this.depth;
        int i7 = i6 + 1;
        this.depth = i7;
        int i8 = i6 * 4;
        if (i7 == 1) {
            this.parsedTopLevelStartTag = true;
        }
        String[] strArrEnsureCapacity2 = ensureCapacity(this.elementStack, i8 + 4);
        this.elementStack = strArrEnsureCapacity2;
        strArrEnsureCapacity2[i8 + 3] = this.name;
        int i9 = this.depth;
        int[] iArr = this.nspCounts;
        if (i9 >= iArr.length) {
            int[] iArr2 = new int[i9 + 4];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.nspCounts = iArr2;
        }
        int[] iArr3 = this.nspCounts;
        int i10 = this.depth;
        iArr3[i10] = iArr3[i10 - 1];
        if (this.processNsp) {
            adjustNsp();
        } else {
            this.namespace = "";
        }
        Map<String, Map<String, String>> map2 = this.defaultAttributes;
        if (map2 != null && (map = map2.get(this.name)) != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (getAttributeValue(null, entry.getKey()) == null) {
                    int i11 = this.attributeCount;
                    this.attributeCount = i11 + 1;
                    int i12 = i11 * 4;
                    String[] strArrEnsureCapacity3 = ensureCapacity(this.attributes, i12 + 4);
                    this.attributes = strArrEnsureCapacity3;
                    strArrEnsureCapacity3[i12] = "";
                    strArrEnsureCapacity3[i12 + 1] = null;
                    strArrEnsureCapacity3[i12 + 2] = entry.getKey();
                    this.attributes[i12 + 3] = entry.getValue();
                }
            }
        }
        String[] strArr = this.elementStack;
        strArr[i8] = this.namespace;
        strArr[i8 + 1] = this.prefix;
        strArr[i8 + 2] = this.name;
    }

    private int peekCharacter() throws XmlPullParserException, IOException {
        if (this.position < this.limit || fillBuffer(1)) {
            return this.buffer[this.position];
        }
        return -1;
    }

    private int peekType(boolean z) throws XmlPullParserException, IOException {
        this.mClosedWithTag = false;
        if (this.position >= this.limit && !fillBuffer(1)) {
            return 1;
        }
        char[] cArr = this.buffer;
        int i = this.position;
        char c = cArr[i];
        if (c == '%') {
            return z ? 15 : 4;
        }
        if (c == '&') {
            return 6;
        }
        if (c != '<') {
            return 4;
        }
        if (i + 3 >= this.limit && !fillBuffer(4)) {
            b78.a("Dangling <", this);
            return 0;
        }
        char[] cArr2 = this.buffer;
        int i2 = this.position;
        char c2 = cArr2[i2 + 1];
        if (c2 != '!') {
            if (c2 == '/') {
                this.mClosedWithTag = true;
                return 3;
            }
            if (c2 != '?') {
                return 2;
            }
            if (i2 + 5 >= this.limit && !fillBuffer(6)) {
                return 8;
            }
            char[] cArr3 = this.buffer;
            int i3 = this.position;
            if (cArr3[i3 + 2] != 'x' && cArr3[i3 + 2] != 'X') {
                return 8;
            }
            if (cArr3[i3 + 3] == 'm' || cArr3[i3 + 3] == 'M') {
                return ((cArr3[i3 + 4] == 'l' || cArr3[i3 + 4] == 'L') && cArr3[i3 + 5] == ' ') ? 998 : 8;
            }
            return 8;
        }
        char c3 = cArr2[i2 + 2];
        if (c3 == '-') {
            return 9;
        }
        if (c3 == 'A') {
            return 13;
        }
        if (c3 == 'N') {
            return 14;
        }
        if (c3 == '[') {
            return 5;
        }
        if (c3 == 'D') {
            return 10;
        }
        if (c3 == 'E') {
            char c4 = cArr2[i2 + 3];
            if (c4 == 'L') {
                return 11;
            }
            if (c4 == 'N') {
                return 12;
            }
        }
        b78.a("Unexpected <!", this);
        return 0;
    }

    private void popContentSource() {
        this.buffer = this.nextContentSource.buffer;
        this.position = this.nextContentSource.position;
        this.limit = this.nextContentSource.limit;
        this.nextContentSource = this.nextContentSource.next;
    }

    private void pushContentSource(char[] cArr) {
        this.nextContentSource = new ContentSource(this.nextContentSource, this.buffer, this.position, this.limit);
        this.buffer = cArr;
        this.position = 0;
        this.limit = cArr.length;
    }

    private void read(char[] cArr) throws XmlPullParserException, IOException {
        if (this.position + cArr.length > this.limit && !fillBuffer(cArr.length)) {
            checkRelaxed("expected: '" + new String(cArr) + "' but was EOF");
            return;
        }
        for (int i = 0; i < cArr.length; i++) {
            if (this.buffer[this.position + i] != cArr[i]) {
                checkRelaxed("expected: \"" + new String(cArr) + "\" but was \"" + new String(this.buffer, this.position, cArr.length) + "...\"");
            }
        }
        this.position += cArr.length;
    }

    private void readAttributeListDeclaration() throws XmlPullParserException, IOException {
        read(START_ATTLIST);
        skip();
        String name = readName();
        while (true) {
            skip();
            if (peekCharacter() == 62) {
                this.position++;
                return;
            }
            String name2 = readName();
            skip();
            if (this.position + 1 >= this.limit && !fillBuffer(2)) {
                b78.a("Malformed attribute list", this);
                return;
            }
            char[] cArr = this.buffer;
            int i = this.position;
            char c = cArr[i];
            char[] cArr2 = NOTATION;
            if (c == cArr2[0] && cArr[i + 1] == cArr2[1]) {
                read(cArr2);
                skip();
            }
            if (peekCharacter() == 40) {
                this.position++;
                while (true) {
                    skip();
                    readName();
                    skip();
                    int iPeekCharacter = peekCharacter();
                    if (iPeekCharacter == 41) {
                        this.position++;
                        break;
                    } else {
                        if (iPeekCharacter != 124) {
                            b78.a("Malformed attribute type", this);
                            return;
                        }
                        this.position++;
                    }
                }
            } else {
                readName();
            }
            skip();
            int iPeekCharacter2 = peekCharacter();
            if (iPeekCharacter2 == 35) {
                this.position++;
                int iPeekCharacter3 = peekCharacter();
                if (iPeekCharacter3 == 82) {
                    read(REQUIRED);
                } else if (iPeekCharacter3 == 73) {
                    read(IMPLIED);
                } else {
                    if (iPeekCharacter3 != 70) {
                        b78.a("Malformed attribute type", this);
                        return;
                    }
                    read(FIXED);
                }
                skip();
                iPeekCharacter2 = peekCharacter();
            }
            if (iPeekCharacter2 == 34 || iPeekCharacter2 == 39) {
                this.position++;
                String value = readValue((char) iPeekCharacter2, true, true, ValueContext.ATTRIBUTE);
                if (peekCharacter() == iPeekCharacter2) {
                    this.position++;
                }
                defineAttributeDefault(name, name2, value);
            }
        }
    }

    private String readComment(boolean z) throws XmlPullParserException, IOException {
        read(START_COMMENT);
        if (this.relaxed) {
            return readUntil(END_COMMENT, z);
        }
        String until = readUntil(COMMENT_DOUBLE_DASH, z);
        if (peekCharacter() == 62) {
            this.position++;
            return until;
        }
        b78.a("Comments may not contain --", this);
        return null;
    }

    private void readContentSpec() throws XmlPullParserException, IOException {
        skip();
        int iPeekCharacter = peekCharacter();
        int i = 0;
        if (iPeekCharacter != 40) {
            char[] cArr = EMPTY;
            if (iPeekCharacter == cArr[0]) {
                read(cArr);
                return;
            }
            char[] cArr2 = ANY;
            if (iPeekCharacter == cArr2[0]) {
                read(cArr2);
                return;
            } else {
                b78.a("Expected element content spec", this);
                return;
            }
        }
        do {
            if (iPeekCharacter == 40) {
                i++;
            } else if (iPeekCharacter == 41) {
                i--;
            } else if (iPeekCharacter == -1) {
                b78.a("Unterminated element content spec", this);
                return;
            }
            this.position++;
            iPeekCharacter = peekCharacter();
        } while (i > 0);
        if (iPeekCharacter == 42 || iPeekCharacter == 63 || iPeekCharacter == 43) {
            this.position++;
        }
    }

    private void readDoctype(boolean z) throws XmlPullParserException, IOException {
        int i;
        read(START_DOCTYPE);
        if (z) {
            this.bufferCapture = new StringBuilder();
            i = this.position;
        } else {
            i = -1;
        }
        try {
            skip();
            this.rootElementName = readName();
            readExternalId(true, true);
            skip();
            if (peekCharacter() == 91) {
                readInternalSubset();
            }
            skip();
            if (z) {
                this.bufferCapture.append(this.buffer, 0, this.position);
                this.bufferCapture.delete(0, i);
                this.text = this.bufferCapture.toString();
                this.bufferCapture = null;
            }
            read('>');
        } catch (Throwable th) {
            if (z) {
                this.bufferCapture.append(this.buffer, 0, this.position);
                this.bufferCapture.delete(0, i);
                this.text = this.bufferCapture.toString();
                this.bufferCapture = null;
            }
            throw th;
        }
    }

    private void readElementDeclaration() throws XmlPullParserException, IOException {
        read(START_ELEMENT);
        skip();
        readName();
        readContentSpec();
        skip();
        read('>');
    }

    private void readEndTag() throws XmlPullParserException, IOException {
        read('<');
        read('/');
        this.name = readName();
        skip();
        read('>');
        int i = this.depth;
        int i2 = (i - 1) * 4;
        String str = this.name;
        if (i == 0) {
            checkRelaxed("read end tag " + str + " with no tags open");
            this.type = 9;
            return;
        }
        int i3 = i2 + 3;
        if (str.equals(this.elementStack[i3])) {
            String[] strArr = this.elementStack;
            this.namespace = strArr[i2];
            this.prefix = strArr[i2 + 1];
            this.name = strArr[i2 + 2];
            return;
        }
        if (this.relaxed) {
            return;
        }
        throw new XmlPullParserException("expected: /" + this.elementStack[i3] + " read: " + this.name, this, null);
    }

    private void readEntity(StringBuilder sb, boolean z, boolean z2, ValueContext valueContext) throws XmlPullParserException, IOException {
        char[] cArr;
        int length = sb.length();
        char[] cArr2 = this.buffer;
        int i = this.position;
        this.position = i + 1;
        if (cArr2[i] != '&') {
            x1f.a();
            return;
        }
        sb.append('&');
        while (true) {
            int iPeekCharacter = peekCharacter();
            if (iPeekCharacter == 59) {
                sb.append(';');
                this.position++;
                String strSubstring = sb.substring(length + 1, sb.length() - 1);
                if (z) {
                    this.name = strSubstring;
                }
                if (strSubstring.startsWith("#")) {
                    try {
                        int i2 = strSubstring.startsWith("#x") ? Integer.parseInt(strSubstring.substring(2), 16) : Integer.parseInt(strSubstring.substring(1));
                        sb.delete(length, sb.length());
                        sb.appendCodePoint(i2);
                        this.unresolved = false;
                        return;
                    } catch (NumberFormatException unused) {
                        throw new XmlPullParserException("Invalid character reference: &".concat(strSubstring));
                    } catch (IllegalArgumentException unused2) {
                        throw new XmlPullParserException("Invalid character reference: &".concat(strSubstring));
                    }
                }
                if (valueContext == ValueContext.ENTITY_DECLARATION) {
                    return;
                }
                String str = DEFAULT_ENTITIES.get(strSubstring);
                if (str != null) {
                    sb.delete(length, sb.length());
                    this.unresolved = false;
                    sb.append(str);
                    return;
                }
                Map<String, char[]> map = this.documentEntities;
                if (map != null && (cArr = map.get(strSubstring)) != null) {
                    sb.delete(length, sb.length());
                    this.unresolved = false;
                    if (this.processDocDecl) {
                        pushContentSource(cArr);
                        return;
                    } else {
                        sb.append(cArr);
                        return;
                    }
                }
                if (this.systemId != null) {
                    sb.delete(length, sb.length());
                    return;
                }
                this.unresolved = true;
                if (z2) {
                    checkRelaxed("unresolved: &" + strSubstring + ";");
                    return;
                }
                return;
            }
            if (iPeekCharacter < 128 && ((iPeekCharacter < 48 || iPeekCharacter > 57) && ((iPeekCharacter < 97 || iPeekCharacter > 122) && ((iPeekCharacter < 65 || iPeekCharacter > 90) && iPeekCharacter != 95 && iPeekCharacter != 45 && iPeekCharacter != 35)))) {
                if (this.relaxed) {
                    return;
                }
                b78.a("unterminated entity ref", this);
                return;
            }
            this.position++;
            sb.append((char) iPeekCharacter);
        }
    }

    private void readEntityDeclaration() throws XmlPullParserException, IOException {
        boolean z;
        String value;
        read(START_ENTITY);
        skip();
        if (peekCharacter() == 37) {
            this.position++;
            skip();
            z = false;
        } else {
            z = true;
        }
        String name = readName();
        skip();
        int iPeekCharacter = peekCharacter();
        if (iPeekCharacter == 34 || iPeekCharacter == 39) {
            this.position++;
            value = readValue((char) iPeekCharacter, true, false, ValueContext.ENTITY_DECLARATION);
            if (peekCharacter() == iPeekCharacter) {
                this.position++;
            }
        } else {
            if (!readExternalId(true, false)) {
                b78.a("Expected entity value or external ID", this);
                return;
            }
            skip();
            int iPeekCharacter2 = peekCharacter();
            char[] cArr = NDATA;
            if (iPeekCharacter2 == cArr[0]) {
                read(cArr);
                skip();
                readName();
            }
            value = "";
        }
        if (z && this.processDocDecl) {
            if (this.documentEntities == null) {
                this.documentEntities = new HashMap();
            }
            this.documentEntities.put(name, value.toCharArray());
        }
        skip();
        read('>');
    }

    private boolean readExternalId(boolean z, boolean z2) throws XmlPullParserException, IOException {
        int iPeekCharacter;
        skip();
        int iPeekCharacter2 = peekCharacter();
        if (iPeekCharacter2 == 83) {
            read(SYSTEM);
        } else {
            if (iPeekCharacter2 != 80) {
                return false;
            }
            read(PUBLIC);
            skip();
            if (z2) {
                this.publicId = readQuotedId(true);
            } else {
                readQuotedId(false);
            }
        }
        skip();
        if (!z && (iPeekCharacter = peekCharacter()) != 34 && iPeekCharacter != 39) {
            return true;
        }
        if (z2) {
            this.systemId = readQuotedId(true);
        } else {
            readQuotedId(false);
        }
        return true;
    }

    private void readInternalSubset() throws XmlPullParserException, IOException {
        read('[');
        while (true) {
            skip();
            if (peekCharacter() == 93) {
                this.position++;
                return;
            }
            switch (peekType(true)) {
                case 8:
                    read(START_PROCESSING_INSTRUCTION);
                    readUntil(END_PROCESSING_INSTRUCTION, false);
                    break;
                case 9:
                    readComment(false);
                    break;
                case 10:
                default:
                    b78.a("Unexpected token", this);
                    return;
                case 11:
                    readElementDeclaration();
                    break;
                case 12:
                    readEntityDeclaration();
                    break;
                case 13:
                    readAttributeListDeclaration();
                    break;
                case 14:
                    readNotationDeclaration();
                    break;
                case 15:
                    b78.a("Parameter entity references are not supported", this);
                    return;
            }
        }
    }

    private String readName() throws XmlPullParserException, IOException {
        if (this.position >= this.limit && !fillBuffer(1)) {
            checkRelaxed("name expected");
            return "";
        }
        int i = this.position;
        char c = this.buffer[i];
        if ((c < 'a' || c > 'z') && !((c >= 'A' && c <= 'Z') || c == '_' || c == ':' || c >= 192 || this.relaxed)) {
            checkRelaxed("name expected");
            return "";
        }
        this.position = i + 1;
        StringBuilder sb = null;
        while (true) {
            if (this.position >= this.limit) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.buffer, i, this.position - i);
                if (!fillBuffer(1)) {
                    return sb.toString();
                }
                i = this.position;
            }
            char[] cArr = this.buffer;
            int i2 = this.position;
            char c2 = cArr[i2];
            if ((c2 < 'a' || c2 > 'z') && ((c2 < 'A' || c2 > 'Z') && !((c2 >= '0' && c2 <= '9') || c2 == '_' || c2 == '-' || c2 == ':' || c2 == '.' || c2 >= 183))) {
                if (sb == null) {
                    return new String(cArr, i, i2 - i);
                }
                sb.append(cArr, i, i2 - i);
                return sb.toString();
            }
            this.position = i2 + 1;
        }
    }

    private void readNotationDeclaration() throws XmlPullParserException, IOException {
        read(START_NOTATION);
        skip();
        readName();
        if (!readExternalId(false, false)) {
            b78.a("Expected external ID or public ID for notation", this);
        } else {
            skip();
            read('>');
        }
    }

    private String readQuotedId(boolean z) throws XmlPullParserException, IOException {
        char[] cArr;
        int iPeekCharacter = peekCharacter();
        if (iPeekCharacter == 34) {
            cArr = DOUBLE_QUOTE;
        } else {
            if (iPeekCharacter != 39) {
                b78.a("Expected a quoted string", this);
                return null;
            }
            cArr = SINGLE_QUOTE;
        }
        this.position++;
        return readUntil(cArr, z);
    }

    private String readUntil(char[] cArr, boolean z) throws XmlPullParserException, IOException {
        StringBuilder sb;
        int i;
        int i2 = this.position;
        if (!z || this.text == null) {
            sb = null;
        } else {
            sb = new StringBuilder();
            sb.append(this.text);
        }
        while (true) {
            int i3 = this.position;
            if (cArr.length + i3 > this.limit) {
                if (i2 < i3 && z) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(this.buffer, i2, this.position - i2);
                }
                if (!fillBuffer(cArr.length)) {
                    checkRelaxed(UNEXPECTED_EOF);
                    this.type = 9;
                    return null;
                }
                i2 = this.position;
            }
            int i4 = 0;
            while (true) {
                if (i4 >= cArr.length) {
                    int i5 = this.position;
                    this.position = cArr.length + i5;
                    if (!z) {
                        return null;
                    }
                    char[] cArr2 = this.buffer;
                    if (sb == null) {
                        return new String(cArr2, i2, i5 - i2);
                    }
                    sb.append(cArr2, i2, i5 - i2);
                    return sb.toString();
                }
                char[] cArr3 = this.buffer;
                i = this.position;
                if (cArr3[i + i4] != cArr[i4]) {
                    break;
                }
                i4++;
            }
            this.position = i + 1;
        }
    }

    private String readValue(char c, boolean z, boolean z2, ValueContext valueContext) throws XmlPullParserException, IOException {
        StringBuilder sb;
        int i = this.position;
        if (valueContext != ValueContext.TEXT || this.text == null) {
            sb = null;
        } else {
            sb = new StringBuilder();
            sb.append(this.text);
        }
        while (true) {
            int i2 = this.position;
            if (i2 >= this.limit) {
                if (i < i2) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(this.buffer, i, this.position - i);
                }
                if (!fillBuffer(1)) {
                    return sb != null ? sb.toString() : "";
                }
                i = this.position;
            }
            char[] cArr = this.buffer;
            int i3 = this.position;
            char c2 = cArr[i3];
            if (c2 == c || ((c == ' ' && (c2 <= ' ' || c2 == '>')) || (c2 == '&' && !z))) {
                if (sb == null) {
                    return new String(cArr, i, i3 - i);
                }
                sb.append(cArr, i, i3 - i);
                return sb.toString();
            }
            if (c2 == '\r' || ((c2 == '\n' && valueContext == ValueContext.ATTRIBUTE) || c2 == '&' || c2 == '<' || ((c2 == ']' && valueContext == ValueContext.TEXT) || (c2 == '%' && valueContext == ValueContext.ENTITY_DECLARATION)))) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.buffer, i, this.position - i);
                if (c2 == '\r') {
                    if (this.position + 1 < this.limit || fillBuffer(2)) {
                        char[] cArr2 = this.buffer;
                        int i4 = this.position;
                        if (cArr2[i4 + 1] == '\n') {
                            this.position = i4 + 1;
                        }
                    }
                    c2 = valueContext == ValueContext.ATTRIBUTE ? ' ' : '\n';
                } else if (c2 == '\n') {
                    c2 = ' ';
                } else if (c2 == '&') {
                    this.isWhitespace = false;
                    readEntity(sb, false, z2, valueContext);
                    i = this.position;
                } else if (c2 == '<') {
                    if (valueContext == ValueContext.ATTRIBUTE) {
                        checkRelaxed("Illegal: \"<\" inside attribute value");
                    }
                    this.isWhitespace = false;
                } else {
                    if (c2 != ']') {
                        if (c2 == '%') {
                            b78.a("This parser doesn't support parameter entities", this);
                            return null;
                        }
                        x1f.a();
                        return null;
                    }
                    if (this.position + 2 < this.limit || fillBuffer(3)) {
                        char[] cArr3 = this.buffer;
                        int i5 = this.position;
                        if (cArr3[i5 + 1] == ']' && cArr3[i5 + 2] == '>') {
                            checkRelaxed("Illegal: \"]]>\" outside CDATA section");
                        }
                    }
                    this.isWhitespace = false;
                }
                this.position++;
                sb.append(c2);
                i = this.position;
            } else {
                this.isWhitespace &= c2 <= ' ';
                this.position = i3 + 1;
            }
        }
    }

    private void readXmlDeclaration() throws XmlPullParserException, IOException {
        if (this.bufferStartLine != 0 || this.bufferStartColumn != 0 || this.position != 0) {
            checkRelaxed("processing instructions must not start with xml");
        }
        read(START_PROCESSING_INSTRUCTION);
        parseStartTag(true, true);
        int i = 2;
        if (this.attributeCount < 1 || !"version".equals(this.attributes[2])) {
            checkRelaxed("version expected");
        }
        String[] strArr = this.attributes;
        this.version = strArr[3];
        if (1 >= this.attributeCount || !"encoding".equals(strArr[6])) {
            i = 1;
        } else {
            this.encoding = this.attributes[7];
        }
        if (i < this.attributeCount) {
            int i2 = i * 4;
            if (Constants.ATTRNAME_OUTPUT_STANDALONE.equals(this.attributes[i2 + 2])) {
                String str = this.attributes[i2 + 3];
                if (JdkConstants.JDK_YES.equals(str)) {
                    this.standalone = Boolean.TRUE;
                } else if ("no".equals(str)) {
                    this.standalone = Boolean.FALSE;
                } else {
                    checkRelaxed("illegal standalone value: " + str);
                }
                i++;
            }
        }
        if (i != this.attributeCount) {
            checkRelaxed("unexpected attributes in XML declaration");
        }
        this.isWhitespace = true;
        this.text = null;
    }

    private void skip() throws XmlPullParserException, IOException {
        while (true) {
            if (this.position >= this.limit && !fillBuffer(1)) {
                return;
            }
            char[] cArr = this.buffer;
            int i = this.position;
            if (cArr[i] > ' ') {
                return;
            } else {
                this.position = i + 1;
            }
        }
    }

    public void close() throws IOException {
        Reader reader = this.reader;
        if (reader != null) {
            reader.close();
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        if (this.processDocDecl) {
            k2d.a("Entity replacement text may not be defined with DOCTYPE processing enabled.");
        } else {
            if (this.reader == null) {
                k2d.a("Entity replacement text must be defined after setInput()");
                return;
            }
            if (this.documentEntities == null) {
                this.documentEntities = new HashMap();
            }
            this.documentEntities.put(str, str2.toCharArray());
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getAttributeCount() {
        return this.attributeCount;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeName(int i) {
        if (i < this.attributeCount) {
            return this.attributes[(i * 4) + 2];
        }
        qc6.a();
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeNamespace(int i) {
        if (i < this.attributeCount) {
            return this.attributes[i * 4];
        }
        qc6.a();
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributePrefix(int i) {
        if (i < this.attributeCount) {
            return this.attributes[(i * 4) + 1];
        }
        qc6.a();
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeType(int i) {
        return "CDATA";
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(String str, String str2) {
        for (int i = (this.attributeCount * 4) - 4; i >= 0; i -= 4) {
            if (this.attributes[i + 2].equals(str2) && (str == null || this.attributes[i].equals(str))) {
                return this.attributes[i + 3];
            }
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getColumnNumber() {
        int i = this.bufferStartColumn;
        for (int i2 = 0; i2 < this.position; i2++) {
            i = this.buffer[i2] == '\n' ? 0 : i + 1;
        }
        return i + 1;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getDepth() {
        return this.depth;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getEventType() throws XmlPullParserException {
        return this.type;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean getFeature(String str) {
        if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            return this.processNsp;
        }
        if (FEATURE_RELAXED.equals(str)) {
            return this.relaxed;
        }
        if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            return this.processDocDecl;
        }
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getInputEncoding() {
        return this.encoding;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getLineNumber() {
        int i = this.bufferStartLine;
        int i2 = this.position - 1;
        char[] cArr = this.buffer;
        int i3 = 0;
        while (i3 < i2) {
            if (cArr[i3] == '\n') {
                i++;
            }
            i3++;
        }
        while (i3 >= 0) {
            char c = cArr[i3];
            if (c > ' ') {
                break;
            }
            if (c == '\n') {
                i--;
            }
            i3--;
        }
        return i + 1;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getName() {
        return this.name;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace(String str) {
        if ("xml".equals(str)) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if ("xmlns".equals(str)) {
            return "http://www.w3.org/2000/xmlns/";
        }
        for (int namespaceCount = (getNamespaceCount(this.depth) << 1) - 2; namespaceCount >= 0; namespaceCount -= 2) {
            String[] strArr = this.nspStack;
            if (str == null) {
                if (strArr[namespaceCount] == null) {
                    return strArr[namespaceCount + 1];
                }
            } else if (str.equals(strArr[namespaceCount])) {
                return this.nspStack[namespaceCount + 1];
            }
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getNamespaceCount(int i) {
        if (i <= this.depth) {
            return this.nspCounts[i];
        }
        qc6.a();
        return 0;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespacePrefix(int i) {
        return this.nspStack[i * 2];
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespaceUri(int i) {
        return this.nspStack[(i * 2) + 1];
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPositionDescription() {
        StringBuilder sb = new StringBuilder();
        Object obj = this.location;
        if (obj != null) {
            sb.append(" at ");
            sb.append(obj);
            sb.append(' ');
        }
        sb.append('[');
        sb.append(getLineNumber());
        sb.append(":");
        sb.append(getColumnNumber());
        sb.append("]\n");
        int i = this.type;
        String[] strArr = XmlPullParser.TYPES;
        sb.append(i < strArr.length ? strArr[i] : "unknown");
        sb.append(' ');
        int i2 = this.type;
        if (i2 == 2 || i2 == 3) {
            if (this.degenerated) {
                sb.append("(empty) ");
            }
            sb.append('<');
            if (this.type == 3) {
                sb.append('/');
            }
            if (this.prefix != null) {
                sb.append("{");
                sb.append(this.namespace);
                sb.append("}");
                sb.append(this.prefix);
                sb.append(":");
            }
            sb.append(this.name);
            int i3 = this.attributeCount * 4;
            for (int i4 = 0; i4 < i3; i4 += 4) {
                sb.append(' ');
                int i5 = i4 + 1;
                if (this.attributes[i5] != null) {
                    sb.append("{");
                    sb.append(this.attributes[i4]);
                    sb.append("}");
                    sb.append(this.attributes[i5]);
                    sb.append(":");
                }
                sb.append(this.attributes[i4 + 2]);
                sb.append("='");
                sb.append(this.attributes[i4 + 3]);
                sb.append("'");
            }
            sb.append('>');
        } else if (i2 == 7) {
            sb.append("(whitespace)");
        } else if (i2 != 4) {
            sb.append(getText());
        } else if (this.isWhitespace) {
            sb.append("(whitespace)");
        } else {
            String text = getText();
            if (text.length() > 16) {
                text = text.substring(0, 16).concat("...");
            }
            sb.append(text);
        }
        return sb.toString();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPrefix() {
        return this.prefix;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public Object getProperty(String str) {
        if (str.equals(PROPERTY_XMLDECL_VERSION)) {
            return this.version;
        }
        if (str.equals(PROPERTY_XMLDECL_STANDALONE)) {
            return this.standalone;
        }
        if (str.equals(PROPERTY_LOCATION)) {
            return this.location;
        }
        return null;
    }

    public String getPublicId() {
        return this.publicId;
    }

    public String getRootElementName() {
        return this.rootElementName;
    }

    public String getSimplePositionDescription() {
        StringBuilder sb = new StringBuilder();
        Object obj = this.location;
        if (obj != null) {
            sb.append("at ");
            sb.append(obj);
            sb.append(' ');
        }
        sb.append("[line = ");
        sb.append(getLineNumber());
        sb.append(']');
        return sb.toString();
    }

    public String getSystemId() {
        return this.systemId;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getText() {
        int i = this.type;
        if (i < 4) {
            return null;
        }
        if (i == 6 && this.unresolved) {
            return null;
        }
        String str = this.text;
        return str == null ? "" : str;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public char[] getTextCharacters(int[] iArr) {
        String text = getText();
        if (text == null) {
            iArr[0] = -1;
            iArr[1] = -1;
            return null;
        }
        char[] charArray = text.toCharArray();
        iArr[0] = 0;
        iArr[1] = charArray.length;
        return charArray;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isAttributeDefault(int i) {
        return false;
    }

    public boolean isClosedWithTag() {
        return this.mClosedWithTag;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isEmptyElementTag() throws XmlPullParserException {
        if (this.type == 2) {
            return this.degenerated;
        }
        b78.a(ILLEGAL_TYPE, this);
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isWhitespace() throws XmlPullParserException {
        int i = this.type;
        if (i == 4 || i == 7 || i == 5) {
            return this.isWhitespace;
        }
        b78.a(ILLEGAL_TYPE, this);
        return false;
    }

    public void keepNamespaceAttributes() {
        this.keepNamespaceAttributes = true;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextTag() throws XmlPullParserException, IOException {
        next();
        if (this.type == 4 && this.isWhitespace) {
            next();
        }
        int i = this.type;
        if (i == 3 || i == 2) {
            return i;
        }
        b78.a("unexpected type", this);
        return 0;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String nextText() throws XmlPullParserException, IOException {
        String text;
        if (this.type != 2) {
            b78.a("precondition: START_TAG", this);
            return null;
        }
        next();
        if (this.type == 4) {
            text = getText();
            next();
        } else {
            text = "";
        }
        if (this.type == 3) {
            return text;
        }
        b78.a("END_TAG expected", this);
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextToken() throws XmlPullParserException, IOException {
        return next(true);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        if (i == this.type && ((str == null || str.equals(getNamespace())) && (str2 == null || str2.equals(getName())))) {
            return;
        }
        throw new XmlPullParserException("expected: " + XmlPullParser.TYPES[i] + " {" + str + "}" + str2, this, null);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setFeature(String str, boolean z) throws XmlPullParserException {
        if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            this.processNsp = z;
            return;
        }
        if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            this.processDocDecl = z;
        } else if (FEATURE_RELAXED.equals(str)) {
            this.relaxed = z;
        } else {
            throw new XmlPullParserException("unsupported feature: " + str, this, null);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.xmlpull.v1.XmlPullParser
    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        int i;
        this.position = 0;
        this.limit = 0;
        boolean z = str == null;
        if (inputStream == null) {
            w01.a("is == null");
            return;
        }
        String str2 = "UTF-8";
        if (z) {
            int i2 = 0;
            while (this.limit < 4 && (i = inputStream.read()) != -1) {
                try {
                    i2 = (i2 << 8) | i;
                    char[] cArr = this.buffer;
                    int i3 = this.limit;
                    this.limit = i3 + 1;
                    cArr[i3] = (char) i;
                } catch (Exception e) {
                    throw new XmlPullParserException("Invalid stream or encoding: " + e, this, e);
                }
            }
            if (this.limit == 4) {
                switch (i2) {
                    case -131072:
                        this.limit = 0;
                        str = "UTF-32LE";
                        break;
                    case 60:
                        this.buffer[0] = '<';
                        this.limit = 1;
                        str = "UTF-32BE";
                        break;
                    case 65279:
                        this.limit = 0;
                        str = "UTF-32BE";
                        break;
                    case 3932223:
                        char[] cArr2 = this.buffer;
                        cArr2[0] = '<';
                        cArr2[1] = '?';
                        this.limit = 2;
                        str = XMLEntityManager.EncodingInfo.STR_UTF16BE;
                        break;
                    case 1006632960:
                        this.buffer[0] = '<';
                        this.limit = 1;
                        str = "UTF-32LE";
                        break;
                    case 1006649088:
                        char[] cArr3 = this.buffer;
                        cArr3[0] = '<';
                        cArr3[1] = '?';
                        this.limit = 2;
                        str = XMLEntityManager.EncodingInfo.STR_UTF16LE;
                        break;
                    case 1010792557:
                        while (true) {
                            int i4 = inputStream.read();
                            if (i4 == -1) {
                                break;
                            } else {
                                char[] cArr4 = this.buffer;
                                int i5 = this.limit;
                                int i6 = i5 + 1;
                                this.limit = i6;
                                cArr4[i5] = (char) i4;
                                if (i4 == 62) {
                                    String str3 = new String(cArr4, 0, i6);
                                    int iIndexOf = str3.indexOf("encoding");
                                    if (iIndexOf != -1) {
                                        while (str3.charAt(iIndexOf) != '\"' && str3.charAt(iIndexOf) != '\'') {
                                            iIndexOf++;
                                        }
                                        int i7 = iIndexOf + 1;
                                        str = str3.substring(i7, str3.indexOf(str3.charAt(iIndexOf), i7));
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    default:
                        int i8 = (-65536) & i2;
                        if (i8 == -16842752) {
                            char[] cArr5 = this.buffer;
                            cArr5[0] = (char) ((cArr5[2] << '\b') | cArr5[3]);
                            this.limit = 1;
                            str = XMLEntityManager.EncodingInfo.STR_UTF16BE;
                        } else if (i8 == -131072) {
                            char[] cArr6 = this.buffer;
                            cArr6[0] = (char) ((cArr6[3] << '\b') | cArr6[2]);
                            this.limit = 1;
                            str = XMLEntityManager.EncodingInfo.STR_UTF16LE;
                        } else if ((i2 & (-256)) == -272908544) {
                            char[] cArr7 = this.buffer;
                            cArr7[0] = cArr7[3];
                            this.limit = 1;
                            str = "UTF-8";
                        }
                        break;
                }
            }
        }
        if (str != null) {
            str2 = str;
        }
        int i9 = this.limit;
        setInput(new InputStreamReader(inputStream, str2));
        this.encoding = str2;
        this.limit = i9;
        if (z || peekCharacter() != 65279) {
            return;
        }
        int i10 = this.limit - 1;
        this.limit = i10;
        char[] cArr8 = this.buffer;
        System.arraycopy(cArr8, 1, cArr8, 0, i10);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setProperty(String str, Object obj) throws XmlPullParserException {
        if (!str.equals(PROPERTY_LOCATION)) {
            throw new XmlPullParserException("unsupported property: ".concat(str));
        }
        this.location = String.valueOf(obj);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(int i) {
        if (i < this.attributeCount) {
            return this.attributes[(i * 4) + 3];
        }
        qc6.a();
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace() {
        return this.namespace;
    }

    private void read(char c) throws XmlPullParserException, IOException {
        int iPeekCharacter = peekCharacter();
        if (iPeekCharacter != c) {
            checkRelaxed("expected: '" + c + "' actual: '" + ((char) iPeekCharacter) + "'");
            if (iPeekCharacter == -1) {
                return;
            }
        }
        this.position++;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int next() throws XmlPullParserException, IOException {
        return next(false);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setInput(Reader reader) throws XmlPullParserException {
        this.reader = reader;
        this.type = 0;
        this.parsedTopLevelStartTag = false;
        this.name = null;
        this.namespace = null;
        this.degenerated = false;
        this.attributeCount = -1;
        this.encoding = null;
        this.version = null;
        this.standalone = null;
        if (reader == null) {
            return;
        }
        this.position = 0;
        this.limit = 0;
        this.bufferStartLine = 0;
        this.bufferStartColumn = 0;
        this.depth = 0;
        this.documentEntities = null;
    }
}
