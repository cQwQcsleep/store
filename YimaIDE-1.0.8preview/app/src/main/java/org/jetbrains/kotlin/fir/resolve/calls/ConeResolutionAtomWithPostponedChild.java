package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR(\u0010\f\u001a\u0004\u0018\u00010\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtomWithPostponedChild;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "fallbackSubAtom", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getFallbackSubAtom", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "value", "subAtom", "getSubAtom", "setSubAtom", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;)V", "setPostponedSubAtom", Argument.Delimiters.none, "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "useFallbackSubAtom", "useFallbackForDisabledCollectionLiterals", "makeFreshCopy", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeResolutionAtomWithPostponedChild extends ConeResolutionAtom {
    private final FirExpression expression;
    private final ConeResolutionAtom fallbackSubAtom;
    private ConeResolutionAtom subAtom;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeResolutionAtomWithPostponedChild(FirExpression firExpression, ConeResolutionAtom coneResolutionAtom) {
        super(null);
        firExpression.getClass();
        this.expression = firExpression;
        this.fallbackSubAtom = coneResolutionAtom;
    }

    private final void setSubAtom(ConeResolutionAtom coneResolutionAtom) {
        if (this.subAtom == null) {
            this.subAtom = coneResolutionAtom;
        } else {
            w01.a("subAtom already initialized");
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirExpression getExpression() {
        return this.expression;
    }

    public final ConeResolutionAtom getFallbackSubAtom() {
        return this.fallbackSubAtom;
    }

    public final ConeResolutionAtom getSubAtom() {
        return this.subAtom;
    }

    public final ConeResolutionAtomWithPostponedChild makeFreshCopy() {
        return new ConeResolutionAtomWithPostponedChild(getExpression(), this.fallbackSubAtom);
    }

    public final void setPostponedSubAtom(ConePostponedResolvedAtom atom) {
        atom.getClass();
        setSubAtom(atom);
    }

    public final void useFallbackForDisabledCollectionLiterals() {
        if (getExpression() instanceof FirCollectionLiteral) {
            setSubAtom(new ConeSimpleLeafResolutionAtom(getExpression(), false));
        } else {
            rza.a("expected atom with ", Reflection.getOrCreateKotlinClass(FirCollectionLiteral.class).getSimpleName(), ", got ", Reflection.getOrCreateKotlinClass(getExpression().getClass()).getSimpleName());
        }
    }

    public final void useFallbackSubAtom() {
        setSubAtom(this.fallbackSubAtom);
    }

    public /* synthetic */ ConeResolutionAtomWithPostponedChild(FirExpression firExpression, ConeResolutionAtom coneResolutionAtom, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firExpression, (i & 2) != 0 ? null : coneResolutionAtom);
    }
}
