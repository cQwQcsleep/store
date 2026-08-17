package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u001e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeSimpleNameForContextSensitiveResolution;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "containingCallCandidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "fallbackSubAtom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "getExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getContainingCallCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "getFallbackSubAtom", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "inputTypes", Argument.Delimiters.none, "getInputTypes", "()Ljava/util/Collection;", "outputType", "getOutputType", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeSimpleNameForContextSensitiveResolution extends ConePostponedResolvedAtom {
    private final Candidate containingCallCandidate;
    private final ConeKotlinType expectedType;
    private final FirPropertyAccessExpression expression;
    private final ConeResolutionAtom fallbackSubAtom;
    private final Collection<ConeKotlinType> inputTypes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeSimpleNameForContextSensitiveResolution(FirPropertyAccessExpression firPropertyAccessExpression, ConeKotlinType coneKotlinType, Candidate candidate, ConeResolutionAtom coneResolutionAtom) {
        super(null);
        firPropertyAccessExpression.getClass();
        coneKotlinType.getClass();
        candidate.getClass();
        coneResolutionAtom.getClass();
        this.expression = firPropertyAccessExpression;
        this.expectedType = coneKotlinType;
        this.containingCallCandidate = candidate;
        this.fallbackSubAtom = coneResolutionAtom;
        this.inputTypes = CollectionsKt.listOf(mo581getExpectedType());
    }

    public final Candidate getContainingCallCandidate() {
        return this.containingCallCandidate;
    }

    public final ConeResolutionAtom getFallbackSubAtom() {
        return this.fallbackSubAtom;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    public Collection<ConeKotlinType> getInputTypes() {
        return this.inputTypes;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getExpectedType, reason: from getter and merged with bridge method [inline-methods] */
    public ConeKotlinType mo581getExpectedType() {
        return this.expectedType;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirPropertyAccessExpression getExpression() {
        return this.expression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getOutputType */
    public ConeKotlinType mo582getOutputType() {
        return null;
    }
}
