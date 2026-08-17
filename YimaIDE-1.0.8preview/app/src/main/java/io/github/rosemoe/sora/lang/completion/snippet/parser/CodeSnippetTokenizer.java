package io.github.rosemoe.sora.lang.completion.snippet.parser;

import android.util.SparseArray;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class CodeSnippetTokenizer {
    private static SparseArray<TokenType> staticTypes = new SparseArray<>();
    private final String value;
    private TokenType token = TokenType.EOF;
    private int length = 0;
    private int index = 0;

    static {
        for (TokenType tokenType : TokenType.values()) {
            if (tokenType.getTargetCharacter() != 0) {
                staticTypes.put(tokenType.getTargetCharacter(), tokenType);
            }
        }
    }

    public CodeSnippetTokenizer(String str) {
        Objects.requireNonNull(str);
        this.value = str;
    }

    private static boolean isDigitChar(char c) {
        return Character.isDigit(c);
    }

    private static boolean isVariableChar(char c) {
        if (c < 'a' || c > 'z') {
            return (c >= 'A' && c <= 'Z') || c == '_';
        }
        return true;
    }

    private TokenType nextTokenInternal() {
        int i = this.index + this.length;
        this.index = i;
        this.length = 0;
        if (i >= this.value.length()) {
            return TokenType.EOF;
        }
        char cCharAt = this.value.charAt(this.index);
        TokenType tokenType = staticTypes.get(cCharAt);
        if (tokenType != null) {
            this.length = 1;
            return tokenType;
        }
        if (isDigitChar(cCharAt)) {
            this.length = 1;
            while (this.index + this.length < this.value.length() && isDigitChar(this.value.charAt(this.index + this.length))) {
                this.length++;
            }
            return TokenType.Int;
        }
        if (isVariableChar(cCharAt)) {
            this.length = 1;
            while (this.index + this.length < this.value.length()) {
                char cCharAt2 = this.value.charAt(this.index + this.length);
                if (!isVariableChar(cCharAt2) && !isDigitChar(cCharAt2)) {
                    break;
                }
                this.length++;
            }
            return TokenType.VariableName;
        }
        while (this.index + this.length < this.value.length() && !isDigitChar(cCharAt) && !isVariableChar(cCharAt) && staticTypes.get(cCharAt) == null) {
            int i2 = this.length + 1;
            this.length = i2;
            if (this.index + i2 < this.value.length()) {
                cCharAt = this.value.charAt(this.index + this.length);
            }
        }
        return TokenType.Format;
    }

    public TokenType getToken() {
        return this.token;
    }

    public int getTokenEndIndex() {
        return this.index + this.length;
    }

    public int getTokenLength() {
        return this.length;
    }

    public int getTokenStartIndex() {
        return this.index;
    }

    public String getTokenText() {
        String str = this.value;
        int i = this.index;
        return str.substring(i, this.length + i);
    }

    public void moveTo(int i) {
        this.index = i;
        this.length = 0;
    }

    public Token nextToken() {
        TokenType tokenTypeNextTokenInternal = nextTokenInternal();
        this.token = tokenTypeNextTokenInternal;
        return new Token(this.index, this.length, tokenTypeNextTokenInternal);
    }
}
