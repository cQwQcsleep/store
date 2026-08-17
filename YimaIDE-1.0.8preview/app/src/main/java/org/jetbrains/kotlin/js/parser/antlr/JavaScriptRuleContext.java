package org.jetbrains.kotlin.js.parser.antlr;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\t\b\u0016¢\u0006\u0004\b\u0005\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/antlr/JavaScriptRuleContext;", "Lorg/antlr/v4/runtime/ParserRuleContext;", "parent", "invokingStateNumber", "", "<init>", "(Lorg/antlr/v4/runtime/ParserRuleContext;I)V", "()V", "commentsBefore", "", "Lorg/antlr/v4/runtime/Token;", "getCommentsBefore", "()Ljava/util/List;", "commentsAfter", "getCommentsAfter", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class JavaScriptRuleContext extends ParserRuleContext {
    private final List<Token> commentsAfter;
    private final List<Token> commentsBefore;

    public JavaScriptRuleContext(ParserRuleContext parserRuleContext, int i) {
        super(parserRuleContext, i);
        this.commentsBefore = new ArrayList();
        this.commentsAfter = new ArrayList();
    }

    public final List<Token> getCommentsAfter() {
        return this.commentsAfter;
    }

    public final List<Token> getCommentsBefore() {
        return this.commentsBefore;
    }

    public JavaScriptRuleContext() {
        this.commentsBefore = new ArrayList();
        this.commentsAfter = new ArrayList();
    }
}
