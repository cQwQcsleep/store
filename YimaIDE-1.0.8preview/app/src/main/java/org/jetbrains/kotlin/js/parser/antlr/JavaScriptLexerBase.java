package org.jetbrains.kotlin.js.parser.antlr;

import java.util.ArrayDeque;
import java.util.Deque;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u000e\b&\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0015\u001a\u00020\bJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\bJ\u0006\u0010\u0018\u001a\u00020\bJ\u0006\u0010\u0019\u001a\u00020\bJ\u0016\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fJ\b\u0010\u001d\u001a\u00020\nH\u0016J\b\u0010\u001e\u001a\u00020\u0017H\u0004J\b\u0010\u001f\u001a\u00020\u0017H\u0004J\b\u0010 \u001a\u00020\u0017H\u0004J\b\u0010!\u001a\u00020\u0017H\u0004J\b\u0010\"\u001a\u00020\u0017H\u0004J\b\u0010#\u001a\u00020\bH\u0004J\b\u0010$\u001a\u00020\u0017H\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/antlr/JavaScriptLexerBase;", "Lorg/antlr/v4/runtime/Lexer;", "input", "Lorg/antlr/v4/runtime/CharStream;", "<init>", "(Lorg/antlr/v4/runtime/CharStream;)V", "scopeStrictModes", "Ljava/util/Deque;", "", "lastToken", "Lorg/antlr/v4/runtime/Token;", "offsetLine", "", "offsetColumn", "value", "strictDefault", "getStrictDefault", "()Z", "useStrictCurrent", "currentDepth", "templateDepthStack", "isStartOfFile", "setUseStrictDefault", "", "isStrictMode", "isInTemplateString", "setTokenOffset", "line", "column", "nextToken", "processOpenBrace", "processCloseBrace", "processTemplateOpenBrace", "processTemplateCloseBrace", "processStringLiteral", "isRegexPossible", "reset", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class JavaScriptLexerBase extends Lexer {
    private int currentDepth;
    private Token lastToken;
    private int offsetColumn;
    private int offsetLine;
    private final Deque<Boolean> scopeStrictModes;
    private boolean strictDefault;
    private Deque<Integer> templateDepthStack;
    private boolean useStrictCurrent;

    public JavaScriptLexerBase(CharStream charStream) {
        super(charStream);
        this.scopeStrictModes = new ArrayDeque();
        this.templateDepthStack = new ArrayDeque();
    }

    public final boolean getStrictDefault() {
        return this.strictDefault;
    }

    public final boolean isInTemplateString() {
        if (this.templateDepthStack.isEmpty()) {
            return false;
        }
        Integer numPeek = this.templateDepthStack.peek();
        return numPeek != null && numPeek.intValue() == this.currentDepth;
    }

    public final boolean isRegexPossible() {
        Token token = this.lastToken;
        if (token == null) {
            return true;
        }
        token.getClass();
        int type = token.getType();
        if (type == 6 || type == 8 || type == 91 || type == 20 || type == 21 || type == 124 || type == 125) {
            return false;
        }
        switch (type) {
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
                return false;
            default:
                return true;
        }
    }

    public final boolean isStartOfFile() {
        return this.lastToken == null;
    }

    /* JADX INFO: renamed from: isStrictMode, reason: from getter */
    public final boolean getUseStrictCurrent() {
        return this.useStrictCurrent;
    }

    public Token nextToken() {
        CommonToken commonTokenNextToken = super.nextToken();
        if (commonTokenNextToken.getChannel() == 0) {
            this.lastToken = commonTokenNextToken;
        }
        if (commonTokenNextToken instanceof CommonToken) {
            CommonToken commonToken = commonTokenNextToken;
            if (commonToken.getType() != -1) {
                commonToken.setLine(commonToken.getLine() + this.offsetLine);
                commonToken.setCharPositionInLine(commonToken.getCharPositionInLine() + this.offsetColumn);
            }
        }
        return commonTokenNextToken;
    }

    public final void processCloseBrace() {
        Boolean boolPop = !this.scopeStrictModes.isEmpty() ? this.scopeStrictModes.pop() : Boolean.valueOf(this.strictDefault);
        boolPop.getClass();
        this.useStrictCurrent = boolPop.booleanValue();
        this.currentDepth--;
    }

    public final void processOpenBrace() {
        this.currentDepth++;
        boolean z = (this.scopeStrictModes.isEmpty() || !Intrinsics.areEqual(this.scopeStrictModes.peek(), Boolean.TRUE)) ? this.strictDefault : true;
        this.useStrictCurrent = z;
        this.scopeStrictModes.push(Boolean.valueOf(z));
    }

    public final void processStringLiteral() {
        Token token = this.lastToken;
        if (token != null) {
            token.getClass();
            if (token.getType() != 9) {
                return;
            }
        }
        String text = getText();
        if (Intrinsics.areEqual(text, "\"use strict\"") || Intrinsics.areEqual(text, "'use strict'")) {
            if (this.scopeStrictModes.size() > 0) {
                this.scopeStrictModes.pop();
            }
            this.useStrictCurrent = true;
            this.scopeStrictModes.push(true);
        }
    }

    public final void processTemplateCloseBrace() {
        this.templateDepthStack.pop();
        this.currentDepth--;
    }

    public final void processTemplateOpenBrace() {
        int i = this.currentDepth + 1;
        this.currentDepth = i;
        this.templateDepthStack.push(Integer.valueOf(i));
    }

    public void reset() {
        this.scopeStrictModes.clear();
        this.lastToken = null;
        this.strictDefault = false;
        this.useStrictCurrent = false;
        this.currentDepth = 0;
        this.templateDepthStack = new ArrayDeque();
        super.reset();
    }

    public final void setTokenOffset(int line, int column) {
        this.offsetLine = line;
        this.offsetColumn = column;
    }

    public final void setUseStrictDefault(boolean value) {
        this.strictDefault = value;
        this.useStrictCurrent = value;
    }
}
