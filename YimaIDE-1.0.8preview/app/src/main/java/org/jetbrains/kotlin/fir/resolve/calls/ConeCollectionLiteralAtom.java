package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.model.CollectionLiteralAtomMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000eR(\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "Lorg/jetbrains/kotlin/resolve/calls/model/CollectionLiteralAtomMarker;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "containingCallCandidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "getExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getContainingCallCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "inputTypes", Argument.Delimiters.none, "getInputTypes", "()Ljava/util/Collection;", "outputType", "getOutputType", "value", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeAtomWithCandidate;", "subAtom", "getSubAtom", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeAtomWithCandidate;", "setSubAtom", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeAtomWithCandidate;)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeCollectionLiteralAtom extends ConePostponedResolvedAtom implements CollectionLiteralAtomMarker {
    private final Candidate containingCallCandidate;
    private final ConeKotlinType expectedType;
    private final FirCollectionLiteral expression;
    private final Collection<ConeKotlinType> inputTypes;
    private ConeAtomWithCandidate subAtom;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeCollectionLiteralAtom(FirCollectionLiteral firCollectionLiteral, ConeKotlinType coneKotlinType, Candidate candidate) {
        super(null);
        firCollectionLiteral.getClass();
        candidate.getClass();
        this.expression = firCollectionLiteral;
        this.expectedType = coneKotlinType;
        this.containingCallCandidate = candidate;
        this.inputTypes = CollectionsKt.listOfNotNull(mo581getExpectedType());
    }

    public final Candidate getContainingCallCandidate() {
        return this.containingCallCandidate;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    public Collection<ConeKotlinType> getInputTypes() {
        return this.inputTypes;
    }

    public final ConeAtomWithCandidate getSubAtom() {
        return this.subAtom;
    }

    public final void setSubAtom(ConeAtomWithCandidate coneAtomWithCandidate) {
        if (this.subAtom == null) {
            this.subAtom = coneAtomWithCandidate;
        } else {
            w01.a("subAtom already initialized");
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getExpectedType, reason: from getter and merged with bridge method [inline-methods] */
    public ConeKotlinType mo581getExpectedType() {
        return this.expectedType;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirCollectionLiteral getExpression() {
        return this.expression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getOutputType */
    public ConeKotlinType mo582getOutputType() {
        return null;
    }
}
