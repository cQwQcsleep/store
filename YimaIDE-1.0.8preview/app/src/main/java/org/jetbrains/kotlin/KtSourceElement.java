package org.jetbrains.kotlin;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0014\u001a\u00020\u0015H&J\n\u0010\u0016\u001a\u00020\u0017H¦\u0080\u0004J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH¦\u0082\u0004R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u0082\u0001\u0002\u001c\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/KtSourceElement;", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "elementType", "Lcom/intellij/psi/tree/IElementType;", "getElementType", "()Lcom/intellij/psi/tree/IElementType;", "kind", "Lorg/jetbrains/kotlin/KtSourceElementKind;", "getKind", "()Lorg/jetbrains/kotlin/KtSourceElementKind;", "lighterASTNode", "Lcom/intellij/lang/LighterASTNode;", "getLighterASTNode", "()Lcom/intellij/lang/LighterASTNode;", "treeStructure", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "getTreeStructure", "()Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "getElementTextInContextForDebug", "", "hashCode", "", "equals", "", "other", "", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class KtSourceElement extends AbstractKtSourceElement {
    private KtSourceElement() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.AbstractKtSourceElement
    public abstract boolean equals(Object other);

    public abstract String getElementTextInContextForDebug();

    public abstract IElementType getElementType();

    public abstract KtSourceElementKind getKind();

    public abstract LighterASTNode getLighterASTNode();

    public abstract FlyweightCapableTreeStructure<LighterASTNode> getTreeStructure();

    @Override // org.jetbrains.kotlin.AbstractKtSourceElement
    public abstract int hashCode();

    public /* synthetic */ KtSourceElement(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
