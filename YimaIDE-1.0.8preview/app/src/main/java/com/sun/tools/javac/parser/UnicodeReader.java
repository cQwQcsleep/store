package com.sun.tools.javac.parser;

import com.sun.org.apache.xml.internal.serializer.CharInfo;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.LayoutCharacters;
import com.sun.tools.javac.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnicodeReader {
    private final char[] buffer;
    private char character;
    private int codepoint;
    private final int length;
    private final Log log;
    private final int offset;
    private int position;
    private boolean wasBackslash;
    private boolean wasUnicodeEscape;
    private int width;

    public static class PositionTrackingReader extends UnicodeReader {
        private int column;

        public PositionTrackingReader(UnicodeReader unicodeReader, int i, int i2) {
            super(unicodeReader.log, unicodeReader.getRawCharacters(i, i2), unicodeReader.offset + i, 0, i2 - i);
            this.column = 0;
        }

        public int column() {
            return this.column;
        }

        @Override // com.sun.tools.javac.parser.UnicodeReader
        public char next() {
            super.next();
            if (isOneOf('\n', CharInfo.S_CARRIAGERETURN, '\f')) {
                this.column = 0;
            } else {
                boolean zIs = is('\t');
                int i = this.column;
                if (zIs) {
                    this.column = LayoutCharacters.tabulate(i);
                } else {
                    this.column = i + 1;
                }
            }
            return get();
        }
    }

    public enum UnicodeEscapeResult {
        BACKSLASH,
        VALID_ESCAPE,
        BROKEN_ESCAPE
    }

    public UnicodeReader(Log log, char[] cArr, int i, int i2, int i3) {
        this.buffer = cArr;
        this.length = i3;
        this.offset = i;
        this.position = i2;
        this.width = 0;
        this.character = (char) 0;
        this.codepoint = 0;
        this.wasBackslash = false;
        this.wasUnicodeEscape = false;
        this.log = log;
        nextCodePoint();
    }

    private void nextCodePoint() {
        nextUnicodeInputCharacter();
        if (isASCII() || !Character.isHighSurrogate(this.character)) {
            return;
        }
        char c = this.character;
        int i = this.position;
        int i2 = this.width;
        nextUnicodeInputCharacter();
        char c2 = this.character;
        if (Character.isLowSurrogate(c2)) {
            this.position = i;
            this.width += i2;
            this.codepoint = Character.toCodePoint(c, c2);
        } else {
            this.position = i;
            this.width = i2;
            this.character = c;
            this.codepoint = c;
        }
    }

    private void nextCodeUnit() {
        int i = this.position;
        int i2 = this.width;
        int i3 = i + i2;
        if (this.length <= i3) {
            this.character = (char) 26;
        } else {
            this.character = this.buffer[i3];
            this.width = i2 + 1;
        }
    }

    private void nextUnicodeInputCharacter() {
        this.position += this.width;
        boolean z = false;
        this.width = 0;
        nextCodeUnit();
        if (this.character != '\\' || (this.wasBackslash && !this.wasUnicodeEscape)) {
            this.wasBackslash = false;
            this.wasUnicodeEscape = false;
        } else {
            int iOrdinal = unicodeEscape().ordinal();
            if (iOrdinal == 0) {
                this.wasUnicodeEscape = false;
                this.wasBackslash = !this.wasBackslash;
            } else if (iOrdinal == 1) {
                this.wasUnicodeEscape = true;
                if (this.character == '\\' && !this.wasBackslash) {
                    z = true;
                }
                this.wasBackslash = z;
            } else if (iOrdinal == 2) {
                nextUnicodeInputCharacter();
            }
        }
        this.codepoint = this.character;
    }

    private UnicodeEscapeResult unicodeEscape() {
        int i = this.position + this.width;
        this.character = '\\';
        this.width = 1;
        int i2 = i;
        while (i2 < this.length && this.buffer[i2] == 'u') {
            i2++;
        }
        if (i2 == i) {
            return UnicodeEscapeResult.BACKSLASH;
        }
        int i3 = i2;
        int iDigit = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            iDigit = (iDigit << 4) | (i3 < this.length ? Character.digit(this.buffer[i3], 16) : -1);
            if (iDigit < 0) {
                break;
            }
            i3++;
        }
        this.width = i3 - this.position;
        if (iDigit >= 0) {
            this.character = (char) iDigit;
            return UnicodeEscapeResult.VALID_ESCAPE;
        }
        this.log.error(i3, CompilerProperties.Errors.IllegalUnicodeEsc);
        return UnicodeEscapeResult.BROKEN_ESCAPE;
    }

    public boolean accept(String str) {
        if (str.length() == 0 || !is(str.charAt(0))) {
            return false;
        }
        int iPosition = position();
        nextCodePoint();
        for (int i = 1; i < str.length(); i++) {
            if (!is(str.charAt(i))) {
                reset(iPosition);
                return false;
            }
            nextCodePoint();
        }
        return true;
    }

    public boolean acceptOneOf(char c, char c2) {
        if (!isOneOf(c, c2)) {
            return false;
        }
        next();
        return true;
    }

    public char[] buffer() {
        return this.buffer;
    }

    public int digit(int i, int i2) {
        if (inRange('0', '9')) {
            int i3 = this.character - '0';
            if (i3 < i2) {
                return i3;
            }
            return -1;
        }
        int iDigit = isSurrogate() ? Character.digit(this.codepoint, i2) : Character.digit(this.character, i2);
        if (iDigit >= 0 && !isASCII()) {
            this.log.error(position(), CompilerProperties.Errors.IllegalNonasciiDigit);
            this.character = "0123456789abcdef".charAt(iDigit);
        }
        return iDigit;
    }

    public char get() {
        return this.character;
    }

    public int getCodepoint() {
        return this.codepoint;
    }

    public char[] getRawCharacters() {
        int i = this.length;
        char[] cArr = this.buffer;
        return i == cArr.length ? cArr : Arrays.copyOf(cArr, i);
    }

    public String getRawString(int i, int i2) {
        return new String(this.buffer, i, i2 - i);
    }

    public boolean inRange(char c, char c2) {
        char c3 = this.character;
        return c <= c3 && c3 <= c2;
    }

    public boolean is(char c) {
        return this.character == c;
    }

    public boolean isASCII() {
        return this.character <= 127;
    }

    public boolean isAvailable() {
        return this.position < this.length;
    }

    public boolean isEOLN() {
        return isOneOf(CharInfo.S_CARRIAGERETURN, '\n');
    }

    public boolean isOneOf(char c, char c2, char c3, char c4, char c5, char c6) {
        return is(c) || is(c2) || is(c3) || is(c4) || is(c5) || is(c6);
    }

    public boolean isSurrogate() {
        return 65535 < this.codepoint;
    }

    public boolean isWhitespace() {
        return isOneOf(' ', '\t', '\f');
    }

    public int length() {
        return this.length;
    }

    public UnicodeReader lineReader() {
        int i = this.position;
        skipToEOLN();
        int i2 = this.position;
        accept(CharInfo.S_CARRIAGERETURN);
        accept('\n');
        return new UnicodeReader(this.log, this.buffer, this.offset, i, i2);
    }

    public char next() {
        nextCodePoint();
        return this.character;
    }

    public int position() {
        return this.offset + this.position;
    }

    public void reset(int i) {
        this.position = i - this.offset;
        this.width = 0;
        this.wasBackslash = false;
        this.wasUnicodeEscape = false;
        nextCodePoint();
    }

    public int skip(char c) {
        int i = 0;
        while (accept(c)) {
            i++;
        }
        return i;
    }

    public void skipToEOLN() {
        while (isAvailable() && !isEOLN()) {
            next();
        }
    }

    public void skipWhitespace() {
        while (acceptOneOf(' ', '\t', '\f')) {
        }
    }

    public String getRawString() {
        return getRawString(this.position, this.length);
    }

    public boolean acceptOneOf(char c, char c2, char c3) {
        if (!isOneOf(c, c2, c3)) {
            return false;
        }
        next();
        return true;
    }

    public char[] getRawCharacters(int i, int i2) {
        return Arrays.copyOfRange(this.buffer, i, i2);
    }

    public UnicodeReader(Log log, char[] cArr, int i) {
        this(log, cArr, 0, 0, i);
    }

    public UnicodeReader(ScannerFactory scannerFactory, char[] cArr, int i) {
        this(scannerFactory.log, cArr, i);
    }

    public UnicodeReader lineReader(int i, int i2) {
        Log log = this.log;
        char[] cArr = this.buffer;
        int i3 = this.offset;
        return new UnicodeReader(log, cArr, i3, i - i3, i2 - i3);
    }

    public boolean isOneOf(char c, char c2, char c3) {
        return is(c) || is(c2) || is(c3);
    }

    public boolean isOneOf(char c, char c2, char c3, char c4) {
        return is(c) || is(c2) || is(c3) || is(c4);
    }

    public boolean isOneOf(char c, char c2) {
        return is(c) || is(c2);
    }

    public boolean accept(char c) {
        if (!is(c)) {
            return false;
        }
        next();
        return true;
    }
}
