package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.resolve.calls.inference.model.MultiLambdaBuilderInferenceRestriction;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/AnonymousFunctionBasedMultiLambdaBuilderInferenceRestriction;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/MultiLambdaBuilderInferenceRestriction;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "anonymous", "typeParameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;)V", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnonymousFunctionBasedMultiLambdaBuilderInferenceRestriction extends MultiLambdaBuilderInferenceRestriction<FirAnonymousFunction> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnonymousFunctionBasedMultiLambdaBuilderInferenceRestriction(FirAnonymousFunction firAnonymousFunction, TypeParameterMarker typeParameterMarker) {
        super(firAnonymousFunction, typeParameterMarker);
        firAnonymousFunction.getClass();
        typeParameterMarker.getClass();
    }
}
