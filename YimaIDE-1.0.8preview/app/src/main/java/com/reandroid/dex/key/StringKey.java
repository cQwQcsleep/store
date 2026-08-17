package com.reandroid.dex.key;

import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.xml.internal.serializer.CharInfo;
import defpackage.l78;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringKey implements Key {
    public static final StringKey EMPTY = new StringKey(StringsUtil.EMPTY);
    private final String text;

    public StringKey(String str) {
        this.text = str;
    }

    public static StringKey create(String str) {
        if (str == null) {
            return null;
        }
        return str.length() == 0 ? EMPTY : new StringKey(str);
    }

    public static String decodeEscapedString(String str, int i) {
        int length = str.length();
        StringBuilder sb = null;
        int i2 = i;
        boolean z = false;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            if (z) {
                if (sb == null) {
                    sb = new StringBuilder();
                    sb.append((CharSequence) str, i, i2 - 1);
                }
                if (cCharAt == 'u') {
                    Character chDecodeNextHex = decodeNextHex(str, i2 + 1);
                    if (chDecodeNextHex != null) {
                        sb.append(chDecodeNextHex.charValue());
                        i2 += 4;
                    } else {
                        sb.append(cCharAt);
                    }
                } else {
                    sb.append(getEscaped(cCharAt));
                }
                z = false;
            } else if (cCharAt == '\\') {
                z = true;
            } else if (sb != null) {
                sb.append(cCharAt);
            }
            i2++;
        }
        return sb == null ? str : sb.toString();
    }

    private static Character decodeNextHex(String str, int i) {
        int i2 = i + 4;
        if (i2 > str.length()) {
            return null;
        }
        int i3 = 0;
        while (i < i2) {
            int iDecodeHexChar = HexUtil.decodeHexChar(str.charAt(i));
            if (iDecodeHexChar == -1) {
                return null;
            }
            i3 = (i3 << 4) | iDecodeHexChar;
            i++;
        }
        return Character.valueOf((char) i3);
    }

    public static String encodeSimpleName(String str) {
        int length = str.length();
        StringBuilder sb = null;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            String strEncodeSimpleName = encodeSimpleName(cCharAt);
            if (strEncodeSimpleName != null) {
                if (sb == null) {
                    sb = new StringBuilder();
                    sb.append((CharSequence) str, 0, i);
                }
                sb.append(strEncodeSimpleName);
            } else if (sb != null) {
                sb.append(cCharAt);
            }
        }
        return sb == null ? str : sb.toString();
    }

    private static char getEscaped(char c) {
        if (c == 'n') {
            return '\n';
        }
        if (c == 'b') {
            return '\b';
        }
        if (c == 'f') {
            return '\f';
        }
        if (c == 'r') {
            return CharInfo.S_CARRIAGERETURN;
        }
        if (c == 't') {
            return '\t';
        }
        return c;
    }

    private static boolean isInvalidSimpleName(char c) {
        return c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' || c == ';' || c == '/' || c == '\\' || c == ':' || c == '.' || c == '*' || c == '%' || c == '|' || c == '^';
    }

    public static StringKey parseQuotedString(String str) {
        if (str != null && str.length() >= 2) {
            SmaliReader smaliReaderOf = SmaliReader.of(str);
            if (smaliReaderOf.get() != 34) {
                return null;
            }
            try {
                smaliReaderOf.skip(1);
                String escapedString = smaliReaderOf.readEscapedString('\"');
                if (smaliReaderOf.available() == 1 && smaliReaderOf.get() == 34) {
                    return create(escapedString);
                }
            } catch (IOException unused) {
            }
        }
        return null;
    }

    public static StringKey read(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        SmaliParseException.expect(smaliReader, '\"');
        String escapedString = smaliReader.readEscapedString('\"');
        SmaliParseException.expect(smaliReader, '\"');
        return create(escapedString);
    }

    public static StringKey readSimpleName(SmaliReader smaliReader, char c) throws IOException {
        int iPosition = smaliReader.position();
        String escapedString = smaliReader.readEscapedString(c);
        SmaliParseException.expect(smaliReader, c);
        smaliReader.skip(-1);
        String strValidateSimpleName = validateSimpleName(escapedString);
        if (strValidateSimpleName == null) {
            return create(escapedString);
        }
        smaliReader.position(iPosition);
        l78.a(strValidateSimpleName, smaliReader);
        return null;
    }

    private static String validateSimpleName(String str) {
        int length = str.length();
        if (length == 0) {
            return "Invalid name";
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (isInvalidSimpleName(cCharAt)) {
                return "Invalid name character '" + cCharAt + "'";
            }
        }
        return null;
    }

    public void append(SmaliWriter smaliWriter, boolean z) throws IOException {
        smaliWriter.append('\"');
        boolean zEncodeString = DexUtils.encodeString(smaliWriter, getString());
        smaliWriter.append('\"');
        if (z && zEncodeString && smaliWriter.isCommentUnicodeStrings()) {
            String string = getString();
            if (string.length() > 250) {
                string = string.substring(0, Const.CHOP_FRAME_MAX);
            }
            smaliWriter.appendComment("'" + string + "'");
        }
    }

    public void appendSimpleName(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getAsSimpleName());
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        return !(obj instanceof StringKey) ? StringsUtil.compareToString(this, obj) : CompareUtil.compare(getString(), ((StringKey) obj).getString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StringKey) {
            return ObjectsUtil.equals(getString(), ((StringKey) obj).getString());
        }
        return false;
    }

    public String getAsSimpleName() {
        return encodeSimpleName(getString());
    }

    @Override // com.reandroid.dex.key.Key
    public TypeKey getDeclaring() {
        return null;
    }

    public String getEncodedString() {
        return DexUtils.encodeString(getString());
    }

    public String getQuoted() {
        return DexUtils.quoteString(getString());
    }

    public String getString() {
        return this.text;
    }

    public int hashCode() {
        return getString().hashCode();
    }

    @Override // com.reandroid.dex.key.Key
    public Iterator<Key> mentionedKeys() {
        return CombiningIterator.singleOne(getDeclaring(), SingleIterator.of(this));
    }

    @Override // com.reandroid.dex.key.Key
    public Key replaceKey(Key key, Key key2) {
        return key.equals(this) ? key2 : this;
    }

    public String toString() {
        return getQuoted();
    }

    public static StringKey readSimpleName(SmaliReader smaliReader) throws IOException {
        return create(smaliReader.readSimpleNameIgnoreWhitespaces());
    }

    private static String encodeSimpleName(char c) {
        if (c == ' ') {
            return HexUtil.toHex("\\u", (int) c, 4);
        }
        if (c == '\n') {
            return "\\n";
        }
        if (c == '\r') {
            return "\\r";
        }
        if (c == '\t') {
            return "\\t";
        }
        if (c == '\b') {
            return "\\b";
        }
        if (c == '\f') {
            return "\\f";
        }
        return null;
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        append(smaliWriter, false);
    }

    public static String decodeEscapedString(String str) {
        return decodeEscapedString(str, 0);
    }
}
