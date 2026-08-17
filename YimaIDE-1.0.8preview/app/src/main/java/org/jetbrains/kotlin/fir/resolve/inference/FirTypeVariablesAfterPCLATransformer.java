package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\u0006\u0010\n\u001a\u0002H\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirTypeVariablesAfterPCLATransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultTransformer;", Argument.Delimiters.none, "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;)V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Void;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformResolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "processCandidate", Argument.Delimiters.none, "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeVariablesAfterPCLATransformer extends FirDefaultTransformer {
    private final ConeSubstitutor substitutor;

    public FirTypeVariablesAfterPCLATransformer(ConeSubstitutor coneSubstitutor) {
        coneSubstitutor.getClass();
        this.substitutor = coneSubstitutor;
    }

    private final void processCandidate(Candidate candidate) {
        FirExpression expression;
        FirExpression expression2;
        ConeResolutionAtom.Companion companion = ConeResolutionAtom.INSTANCE;
        ConeResolutionAtom dispatchReceiver = candidate.getDispatchReceiver();
        ArrayList arrayList = null;
        candidate.setDispatchReceiver(companion.createRawAtomNullable((dispatchReceiver == null || (expression2 = dispatchReceiver.getExpression()) == null) ? null : (FirExpression) expression2.transform(this, null)));
        ConeResolutionAtom chosenExtensionReceiver = candidate.getChosenExtensionReceiver();
        candidate.setChosenExtensionReceiver(companion.createRawAtomNullable((chosenExtensionReceiver == null || (expression = chosenExtensionReceiver.getExpression()) == null) ? null : (FirExpression) expression.transform(this, null)));
        List<ConeResolutionAtom> contextArguments = candidate.getContextArguments();
        if (contextArguments != null) {
            List<ConeResolutionAtom> list = contextArguments;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(ConeResolutionAtom.INSTANCE.createRawAtom((FirExpression) ((ConeResolutionAtom) it.next()).getExpression().transform(this, null)));
            }
            arrayList = arrayList2;
        }
        candidate.setContextArguments(arrayList);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, Void data) {
        Candidate candidate;
        element.getClass();
        if ((element instanceof FirResolvable) && (candidate = CandidateFactoryKt.candidate((FirResolvable) element)) != null) {
            processCandidate(candidate);
        }
        if ((element instanceof FirExpression) && !(element instanceof FirAnonymousFunctionExpression) && !(element instanceof FirWrappedArgumentExpression) && !(element instanceof FirErrorExpression)) {
            FirExpression firExpression = (FirExpression) element;
            ConeKotlinType coneKotlinTypeSubstituteOrNull = this.substitutor.substituteOrNull(FirTypeUtilsKt.getResolvedType(firExpression));
            if (coneKotlinTypeSubstituteOrNull != null) {
                firExpression.replaceConeTypeOrNull(coneKotlinTypeSubstituteOrNull);
            }
        }
        E e = (E) element.transformChildren(this, null);
        e.getClass();
        return e;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef, Void data) {
        FirResolvedTypeRef firResolvedTypeRefWithReplacedConeType$default;
        resolvedTypeRef.getClass();
        ConeKotlinType coneKotlinTypeSubstituteOrNull = this.substitutor.substituteOrNull(resolvedTypeRef.getConeType());
        return (coneKotlinTypeSubstituteOrNull == null || (firResolvedTypeRefWithReplacedConeType$default = TypeUtilsKt.withReplacedConeType$default(resolvedTypeRef, coneKotlinTypeSubstituteOrNull, null, 2, null)) == null) ? resolvedTypeRef : firResolvedTypeRefWithReplacedConeType$default;
    }
}
