package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtomWithSingleChild;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "subAtom", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getSubAtom", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeResolutionAtomWithSingleChild extends ConeResolutionAtom {
    private final FirExpression expression;
    private final ConeResolutionAtom subAtom;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeResolutionAtomWithSingleChild(FirExpression firExpression, ConeResolutionAtom coneResolutionAtom) {
        super(null);
        firExpression.getClass();
        this.expression = firExpression;
        this.subAtom = coneResolutionAtom;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirExpression getExpression() {
        return this.expression;
    }

    public final ConeResolutionAtom getSubAtom() {
        return this.subAtom;
    }
}
