package org.jetbrains.kotlin.js.parser.antlr;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0004J\u0012\u0010\n\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0004J\u0012\u0010\u000b\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0004J\u0012\u0010\f\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0004J\b\u0010\r\u001a\u00020\u0007H\u0004J\b\u0010\u000e\u001a\u00020\u0007H\u0004J\b\u0010\u000f\u001a\u00020\u0007H\u0004J\b\u0010\u0010\u001a\u00020\u0007H\u0004¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/antlr/JavaScriptParserBase;", "Lorg/antlr/v4/runtime/Parser;", "input", "Lorg/antlr/v4/runtime/TokenStream;", "<init>", "(Lorg/antlr/v4/runtime/TokenStream;)V", "p", "", "str", "", "prev", "n", "next", "notLineTerminator", "notOpenBraceAndNotFunction", "closeBrace", "lineTerminatorAhead", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class JavaScriptParserBase extends Parser {
    public JavaScriptParserBase(TokenStream tokenStream) {
        super(tokenStream);
    }

    public final boolean closeBrace() {
        return ((Parser) this)._input.LT(1).getType() == 11;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0064 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x0067 A[RETURN] */
    public final boolean lineTerminatorAhead() {
        int tokenIndex = getCurrentToken().getTokenIndex() - 1;
        if (tokenIndex < 0) {
            return false;
        }
        Token token = ((Parser) this)._input.get(tokenIndex);
        if (token.getChannel() != 1 && token.getChannel() != 3) {
            return false;
        }
        if (token.getType() == 128) {
            return true;
        }
        if (token.getType() == 127) {
            int tokenIndex2 = getCurrentToken().getTokenIndex() - 2;
            if (tokenIndex2 < 0) {
                return false;
            }
            token = ((Parser) this)._input.get(tokenIndex2);
        }
        String text = token.getText();
        int type = token.getType();
        if (type == 2) {
            text.getClass();
            if (!StringsKt.contains$default(text, "\r", false, 2, (Object) null) && !StringsKt.contains$default(text, "\n", false, 2, (Object) null)) {
                if (type == 128) {
                    return false;
                }
            }
        } else if (type == 128) {
            return false;
        }
        return true;
    }

    public final boolean n(String str) {
        return next(str);
    }

    public final boolean next(String str) {
        return Intrinsics.areEqual(((Parser) this)._input.LT(1).getText(), str);
    }

    public final boolean notLineTerminator() {
        return !lineTerminatorAhead();
    }

    public final boolean notOpenBraceAndNotFunction() {
        int type = ((Parser) this)._input.LT(1).getType();
        return (type == 9 || type == 90) ? false : true;
    }

    public final boolean p(String str) {
        return prev(str);
    }

    public final boolean prev(String str) {
        return Intrinsics.areEqual(((Parser) this)._input.LT(-1).getText(), str);
    }
}
