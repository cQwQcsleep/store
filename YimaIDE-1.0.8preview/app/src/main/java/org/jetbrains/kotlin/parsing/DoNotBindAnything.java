package org.jetbrains.kotlin.parsing;

import com.intellij.lang.WhitespacesAndCommentsBinder;
import com.intellij.psi.tree.IElementType;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/parsing/DoNotBindAnything;", "Lcom/intellij/lang/WhitespacesAndCommentsBinder;", "<init>", "()V", "getEdgePosition", "", "tokens", "", "Lcom/intellij/psi/tree/IElementType;", "atStreamEdge", "", "getter", "Lcom/intellij/lang/WhitespacesAndCommentsBinder$TokenTextGetter;", "org.jetbrains.kotlin:parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DoNotBindAnything implements WhitespacesAndCommentsBinder {
    public static final DoNotBindAnything INSTANCE = new DoNotBindAnything();

    private DoNotBindAnything() {
    }

    public int getEdgePosition(List<? extends IElementType> tokens, boolean atStreamEdge, WhitespacesAndCommentsBinder.TokenTextGetter getter) {
        tokens.getClass();
        getter.getClass();
        return 0;
    }
}
