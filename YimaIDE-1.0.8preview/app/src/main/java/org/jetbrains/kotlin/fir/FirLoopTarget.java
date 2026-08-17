package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirLoop;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0002X\u0094.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirLoopTarget;", "Lorg/jetbrains/kotlin/fir/FirAbstractTarget;", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "labelName", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "_labeledElement", "get_labeledElement", "()Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "set_labeledElement", "(Lorg/jetbrains/kotlin/fir/expressions/FirLoop;)V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLoopTarget extends FirAbstractTarget<FirLoop> {
    protected FirLoop _labeledElement;

    public FirLoopTarget(String str) {
        super(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.FirAbstractTarget
    public FirLoop get_labeledElement() throws UninitializedPropertyAccessException {
        FirLoop firLoop = this._labeledElement;
        if (firLoop != null) {
            return firLoop;
        }
        Intrinsics.throwUninitializedPropertyAccessException("_labeledElement");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.FirAbstractTarget
    public void set_labeledElement(FirLoop firLoop) {
        firLoop.getClass();
        this._labeledElement = firLoop;
    }
}
