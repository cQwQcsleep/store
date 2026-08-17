package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.resolve.DoubleColonLHS;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.model.PostponedCallableReferenceMarker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001:B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010'\u001a\u00020(2\u0006\u0010$\u001a\u00020#J\u0010\u00108\u001a\u00020(2\u0006\u00104\u001a\u000209H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010 R\u0014\u0010!\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010 R\"\u0010$\u001a\u0004\u0018\u00010#2\b\u0010\u0013\u001a\u0004\u0018\u00010#@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u001c\u0010)\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00060/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0016\u00102\u001a\u0004\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010+R\u0016\u00104\u001a\u0004\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010+R$\u00106\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00068V@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b7\u0010+¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedCallableReferenceAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedAtomWithRevisableExpectedType;", "Lorg/jetbrains/kotlin/resolve/calls/model/PostponedCallableReferenceMarker;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "initialExpectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "lhs", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "anonymousFunctionIfReturnExpression", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "getLhs", "()Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", "value", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeAtomWithCandidate;", "subAtom", "getSubAtom", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeAtomWithCandidate;", "state", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedCallableReferenceAtom$State;", "getState", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedCallableReferenceAtom$State;", "setState", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedCallableReferenceAtom$State;)V", "isPostponedBecauseOfAmbiguity", Argument.Delimiters.none, "()Z", "needsResolution", "getNeedsResolution", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "resultingReference", "getResultingReference", "()Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "initializeResultingReference", Argument.Delimiters.none, "resultingTypeForCallableReference", "getResultingTypeForCallableReference", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setResultingTypeForCallableReference", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "inputTypes", Argument.Delimiters.none, "getInputTypes", "()Ljava/util/Collection;", "outputType", "getOutputType", "expectedType", "getExpectedType", "revisedExpectedType", "getRevisedExpectedType", "reviseExpectedType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "State", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeResolvedCallableReferenceAtom extends ConePostponedAtomWithRevisableExpectedType implements PostponedCallableReferenceMarker {
    private final FirCallableReferenceAccess expression;
    private final ConeKotlinType initialExpectedType;
    private final DoubleColonLHS lhs;
    private FirNamedReference resultingReference;
    private ConeKotlinType resultingTypeForCallableReference;
    private ConeKotlinType revisedExpectedType;
    private final FirSession session;
    private State state;
    private ConeAtomWithCandidate subAtom;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedCallableReferenceAtom$State;", Argument.Delimiters.none, "needsResolution", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;IZ)V", "getNeedsResolution", "()Z", "NOT_RESOLVED_YET", "POSTPONED_BECAUSE_OF_AMBIGUITY", "RESOLVED", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum State {
        NOT_RESOLVED_YET(true),
        POSTPONED_BECAUSE_OF_AMBIGUITY(true),
        RESOLVED(false);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final boolean needsResolution;

        State(boolean z) {
            this.needsResolution = z;
        }

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }

        public final boolean getNeedsResolution() {
            return this.needsResolution;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeResolvedCallableReferenceAtom(FirCallableReferenceAccess firCallableReferenceAccess, ConeKotlinType coneKotlinType, DoubleColonLHS doubleColonLHS, FirSession firSession, FirAnonymousFunction firAnonymousFunction) {
        super(firAnonymousFunction, null);
        firCallableReferenceAccess.getClass();
        firSession.getClass();
        this.expression = firCallableReferenceAccess;
        this.initialExpectedType = coneKotlinType;
        this.lhs = doubleColonLHS;
        this.session = firSession;
        this.state = State.NOT_RESOLVED_YET;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getExpectedType */
    public ConeKotlinType mo581getExpectedType() {
        ConeKotlinType coneKotlinTypeM584getRevisedExpectedType;
        return (isPostponedBecauseOfAmbiguity() && (coneKotlinTypeM584getRevisedExpectedType = m584getRevisedExpectedType()) != null) ? coneKotlinTypeM584getRevisedExpectedType : this.initialExpectedType;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    public Collection<ConeKotlinType> getInputTypes() {
        List<ConeKotlinType> inputTypes;
        if (this.state == State.NOT_RESOLVED_YET) {
            return CollectionsKt.emptyList();
        }
        InputOutputTypes inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType = ConeResolutionAtomsKt.extractInputOutputTypesFromCallableReferenceExpectedType(mo581getExpectedType(), this.session);
        return (inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType == null || (inputTypes = inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType.getInputTypes()) == null) ? CollectionsKt.listOfNotNull(mo581getExpectedType()) : inputTypes;
    }

    public final DoubleColonLHS getLhs() {
        return this.lhs;
    }

    public boolean getNeedsResolution() {
        return this.state.getNeedsResolution();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getOutputType */
    public ConeKotlinType mo582getOutputType() {
        InputOutputTypes inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType;
        if (this.state == State.NOT_RESOLVED_YET || (inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType = ConeResolutionAtomsKt.extractInputOutputTypesFromCallableReferenceExpectedType(mo581getExpectedType(), this.session)) == null) {
            return null;
        }
        return inputOutputTypesExtractInputOutputTypesFromCallableReferenceExpectedType.getOutputType();
    }

    public final FirNamedReference getResultingReference() {
        return this.resultingReference;
    }

    public final ConeKotlinType getResultingTypeForCallableReference() {
        return this.resultingTypeForCallableReference;
    }

    /* JADX INFO: renamed from: getRevisedExpectedType, reason: merged with bridge method [inline-methods] */
    public ConeKotlinType m584getRevisedExpectedType() {
        return isPostponedBecauseOfAmbiguity() ? this.revisedExpectedType : mo581getExpectedType();
    }

    public final State getState() {
        return this.state;
    }

    public final ConeAtomWithCandidate getSubAtom() {
        return this.subAtom;
    }

    public final void initializeResultingReference(FirNamedReference resultingReference) {
        resultingReference.getClass();
        if (this.resultingReference != null) {
            w01.a("resultingReference already initialized");
            return;
        }
        this.resultingReference = resultingReference;
        this.state = State.RESOLVED;
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = resultingReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) resultingReference : null;
        Candidate candidate = firNamedReferenceWithCandidate != null ? firNamedReferenceWithCandidate.getCandidate() : null;
        if (candidate != null) {
            this.subAtom = new ConeAtomWithCandidate(getExpression(), candidate);
        }
    }

    public final boolean isPostponedBecauseOfAmbiguity() {
        return this.state == State.POSTPONED_BECAUSE_OF_AMBIGUITY;
    }

    public void reviseExpectedType(KotlinTypeMarker expectedType) {
        expectedType.getClass();
        if (expectedType instanceof ConeKotlinType) {
            this.revisedExpectedType = (ConeKotlinType) expectedType;
        } else {
            w01.a("Failed requirement.");
        }
    }

    public final void setResultingTypeForCallableReference(ConeKotlinType coneKotlinType) {
        this.resultingTypeForCallableReference = coneKotlinType;
    }

    public final void setState(State state) {
        state.getClass();
        this.state = state;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirCallableReferenceAccess getExpression() {
        return this.expression;
    }

    public /* synthetic */ ConeResolvedCallableReferenceAtom(FirCallableReferenceAccess firCallableReferenceAccess, ConeKotlinType coneKotlinType, DoubleColonLHS doubleColonLHS, FirSession firSession, FirAnonymousFunction firAnonymousFunction, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firCallableReferenceAccess, coneKotlinType, doubleColonLHS, firSession, (i & 16) != 0 ? null : firAnonymousFunction);
    }
}
