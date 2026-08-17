package org.jetbrains.kotlin.parsing;

import com.intellij.lang.WhitespacesAndCommentsBinder;
import com.intellij.psi.tree.IElementType;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/parsing/BindAll;", "Lcom/intellij/lang/WhitespacesAndCommentsBinder;", "isTrailing", "", "<init>", "(Z)V", "()Z", "getEdgePosition", "", "tokens", "", "Lcom/intellij/psi/tree/IElementType;", "atStreamEdge", "getter", "Lcom/intellij/lang/WhitespacesAndCommentsBinder$TokenTextGetter;", "org.jetbrains.kotlin:parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class BindAll implements WhitespacesAndCommentsBinder {
    private final boolean isTrailing;

    public BindAll(boolean z) {
        this.isTrailing = z;
    }

    public int getEdgePosition(List<? extends IElementType> tokens, boolean atStreamEdge, WhitespacesAndCommentsBinder.TokenTextGetter getter) {
        tokens.getClass();
        getter.getClass();
        if (this.isTrailing) {
            return tokens.size();
        }
        return 0;
    }

    /* JADX INFO: renamed from: isTrailing, reason: from getter */
    public final boolean getIsTrailing() {
        return this.isTrailing;
    }
}
