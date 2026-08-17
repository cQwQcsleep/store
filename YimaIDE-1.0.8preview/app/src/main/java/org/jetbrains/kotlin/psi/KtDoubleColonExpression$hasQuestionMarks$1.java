package org.jetbrains.kotlin.psi;

import com.intellij.lang.ASTNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class KtDoubleColonExpression$hasQuestionMarks$1 extends FunctionReferenceImpl implements Function1<ASTNode, ASTNode> {
    public static final KtDoubleColonExpression$hasQuestionMarks$1 INSTANCE = new KtDoubleColonExpression$hasQuestionMarks$1();

    public KtDoubleColonExpression$hasQuestionMarks$1() {
        super(1, ASTNode.class, "getTreeNext", "getTreeNext()Lcom/intellij/lang/ASTNode;", 0);
    }

    public final ASTNode invoke(ASTNode aSTNode) {
        aSTNode.getClass();
        return aSTNode.getTreeNext();
    }
}
