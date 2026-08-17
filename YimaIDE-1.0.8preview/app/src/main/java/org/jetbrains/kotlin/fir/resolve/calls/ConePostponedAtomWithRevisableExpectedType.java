package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.resolve.calls.model.PostponedAtomWithRevisableExpectedType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\b\u0004\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedAtomWithRevisableExpectedType;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeFunctionTypeRelatedPostponedResolvedAtom;", "Lorg/jetbrains/kotlin/resolve/calls/model/PostponedAtomWithRevisableExpectedType;", "anonymousFunctionIfReturnExpression", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "getAnonymousFunctionIfReturnExpression", "()Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeLambdaWithTypeVariableAsExpectedTypeAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedCallableReferenceAtom;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConePostponedAtomWithRevisableExpectedType extends ConeFunctionTypeRelatedPostponedResolvedAtom implements PostponedAtomWithRevisableExpectedType {
    private final FirAnonymousFunction anonymousFunctionIfReturnExpression;

    private ConePostponedAtomWithRevisableExpectedType(FirAnonymousFunction firAnonymousFunction) {
        super(null);
        this.anonymousFunctionIfReturnExpression = firAnonymousFunction;
    }

    public final FirAnonymousFunction getAnonymousFunctionIfReturnExpression() {
        return this.anonymousFunctionIfReturnExpression;
    }

    public /* synthetic */ ConePostponedAtomWithRevisableExpectedType(FirAnonymousFunction firAnonymousFunction, DefaultConstructorMarker defaultConstructorMarker) {
        this(firAnonymousFunction);
    }
}
