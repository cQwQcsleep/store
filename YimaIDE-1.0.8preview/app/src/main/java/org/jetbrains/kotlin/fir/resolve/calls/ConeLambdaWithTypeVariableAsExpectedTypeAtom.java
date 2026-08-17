package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.model.LambdaWithTypeVariableAsExpectedTypeMarker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u001f\u001a\u00020 2\u0010\u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\"\u0018\u00010\u001bH\u0016J\u0010\u0010.\u001a\u00020 2\u0006\u0010#\u001a\u00020\"H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R(\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR2\u0010\u001c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u001b2\u0010\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u001b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010#\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0016\u0010*\u001a\u0004\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010%R\"\u0010,\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b-\u0010%¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeLambdaWithTypeVariableAsExpectedTypeAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedAtomWithRevisableExpectedType;", "Lorg/jetbrains/kotlin/resolve/calls/model/LambdaWithTypeVariableAsExpectedTypeMarker;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "initialExpectedTypeType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "candidateOfOuterCall", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "anonymousFunctionIfReturnExpression", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "getCandidateOfOuterCall", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "anonymousFunction", "getAnonymousFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "value", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "subAtom", "getSubAtom", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "setSubAtom", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;)V", Argument.Delimiters.none, "parameterTypesFromDeclaration", "getParameterTypesFromDeclaration", "()Ljava/util/List;", "updateParameterTypesFromDeclaration", Argument.Delimiters.none, "types", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "expectedType", "getExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "inputTypes", Argument.Delimiters.none, "getInputTypes", "()Ljava/util/Collection;", "outputType", "getOutputType", "revisedExpectedType", "getRevisedExpectedType", "reviseExpectedType", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeLambdaWithTypeVariableAsExpectedTypeAtom extends ConePostponedAtomWithRevisableExpectedType implements LambdaWithTypeVariableAsExpectedTypeMarker {
    private final FirAnonymousFunction anonymousFunction;
    private final Candidate candidateOfOuterCall;
    private final FirAnonymousFunctionExpression expression;
    private final ConeKotlinType initialExpectedTypeType;
    private List<? extends ConeKotlinType> parameterTypesFromDeclaration;
    private ConeKotlinType revisedExpectedType;
    private ConeResolvedLambdaAtom subAtom;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeLambdaWithTypeVariableAsExpectedTypeAtom(FirAnonymousFunctionExpression firAnonymousFunctionExpression, ConeKotlinType coneKotlinType, Candidate candidate, FirAnonymousFunction firAnonymousFunction) {
        super(firAnonymousFunction, null);
        firAnonymousFunctionExpression.getClass();
        coneKotlinType.getClass();
        candidate.getClass();
        this.expression = firAnonymousFunctionExpression;
        this.initialExpectedTypeType = coneKotlinType;
        this.candidateOfOuterCall = candidate;
        this.anonymousFunction = getExpression().getAnonymousFunction();
    }

    public final FirAnonymousFunction getAnonymousFunction() {
        return this.anonymousFunction;
    }

    public final Candidate getCandidateOfOuterCall() {
        return this.candidateOfOuterCall;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getExpectedType */
    public ConeKotlinType mo581getExpectedType() {
        ConeKotlinType coneKotlinTypeM583getRevisedExpectedType = m583getRevisedExpectedType();
        return coneKotlinTypeM583getRevisedExpectedType == null ? this.initialExpectedTypeType : coneKotlinTypeM583getRevisedExpectedType;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    public Collection<ConeKotlinType> getInputTypes() {
        return CollectionsKt.listOf(this.initialExpectedTypeType);
    }

    public List<ConeKotlinType> getParameterTypesFromDeclaration() {
        return this.parameterTypesFromDeclaration;
    }

    public final ConeResolvedLambdaAtom getSubAtom() {
        return this.subAtom;
    }

    public void reviseExpectedType(KotlinTypeMarker expectedType) {
        expectedType.getClass();
        if (expectedType instanceof ConeKotlinType) {
            this.revisedExpectedType = (ConeKotlinType) expectedType;
        } else {
            w01.a("Failed requirement.");
        }
    }

    public final void setSubAtom(ConeResolvedLambdaAtom coneResolvedLambdaAtom) {
        if (this.subAtom == null) {
            this.subAtom = coneResolvedLambdaAtom;
        } else {
            w01.a("subAtom already initialized");
        }
    }

    public void updateParameterTypesFromDeclaration(List<? extends KotlinTypeMarker> types) {
        this.parameterTypesFromDeclaration = types;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirAnonymousFunctionExpression getExpression() {
        return this.expression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getOutputType */
    public ConeKotlinType mo582getOutputType() {
        return null;
    }

    /* JADX INFO: renamed from: getRevisedExpectedType, reason: from getter and merged with bridge method [inline-methods] */
    public ConeKotlinType m583getRevisedExpectedType() {
        return this.revisedExpectedType;
    }

    public /* synthetic */ ConeLambdaWithTypeVariableAsExpectedTypeAtom(FirAnonymousFunctionExpression firAnonymousFunctionExpression, ConeKotlinType coneKotlinType, Candidate candidate, FirAnonymousFunction firAnonymousFunction, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firAnonymousFunctionExpression, coneKotlinType, candidate, (i & 8) != 0 ? null : firAnonymousFunction);
    }
}
