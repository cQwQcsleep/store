package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeAtomWithCandidate;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeAtomWithCandidate extends ConeResolutionAtom {
    private final Candidate candidate;
    private final FirExpression expression;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeAtomWithCandidate(FirExpression firExpression, Candidate candidate) {
        super(null);
        firExpression.getClass();
        candidate.getClass();
        this.expression = firExpression;
        this.candidate = candidate;
    }

    public final Candidate getCandidate() {
        return this.candidate;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirExpression getExpression() {
        return this.expression;
    }
}
