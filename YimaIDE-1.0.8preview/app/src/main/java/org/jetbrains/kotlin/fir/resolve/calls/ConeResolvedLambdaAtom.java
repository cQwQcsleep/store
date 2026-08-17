package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.resolve.inference.ConeTypeVariableForLambdaReturnType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.utils.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u0016\u00108\u001a\u0002092\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010:\u001a\u00020;J\u000e\u0010<\u001a\u0002092\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u001a\u0010\f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010 R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\"\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010)\u001a\u0004\u0018\u00010\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010)\u001a\u0004\u0018\u00010\u0005@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR \u0010-\u001a\b\u0012\u0004\u0012\u00020/0.X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\u00050.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00101R\u0014\u00106\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u001a¨\u0006="}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeFunctionTypeRelatedPostponedResolvedAtom;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "expectedFunctionTypeKind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "receiverType", "contextParameterTypes", Argument.Delimiters.none, "parameterTypes", "returnType", "typeVariableForLambdaReturnType", "Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeVariableForLambdaReturnType;", "coerceFirstParameterToExtensionReceiver", Argument.Delimiters.none, "sourceForFunctionExpression", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeVariableForLambdaReturnType;ZLorg/jetbrains/kotlin/KtSourceElement;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "getExpectedFunctionTypeKind", "()Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "getReceiverType$org_jetbrains_kotlin_resolve", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getContextParameterTypes$org_jetbrains_kotlin_resolve", "()Ljava/util/List;", "getParameterTypes$org_jetbrains_kotlin_resolve", "getReturnType", "setReturnType", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getCoerceFirstParameterToExtensionReceiver", "()Z", "getSourceForFunctionExpression", "()Lorg/jetbrains/kotlin/KtSourceElement;", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "getAnonymousFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "value", "getTypeVariableForLambdaReturnType", "()Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeVariableForLambdaReturnType;", "getExpectedType", "returnStatements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "getReturnStatements", "()Ljava/util/Collection;", "setReturnStatements", "(Ljava/util/Collection;)V", "inputTypes", "getInputTypes", "outputType", "getOutputType", "replaceExpectedType", Argument.Delimiters.none, "newReturnType", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableType;", "replaceTypeVariableForLambdaReturnType", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeResolvedLambdaAtom extends ConeFunctionTypeRelatedPostponedResolvedAtom {
    private final FirAnonymousFunction anonymousFunction;
    private final boolean coerceFirstParameterToExtensionReceiver;
    private final List<ConeKotlinType> contextParameterTypes;
    private final FunctionTypeKind expectedFunctionTypeKind;
    private ConeKotlinType expectedType;
    private final FirAnonymousFunctionExpression expression;
    private final List<ConeKotlinType> parameterTypes;
    private final ConeKotlinType receiverType;
    public Collection<? extends ConeResolutionAtom> returnStatements;
    private ConeKotlinType returnType;
    private final KtSourceElement sourceForFunctionExpression;
    private ConeTypeVariableForLambdaReturnType typeVariableForLambdaReturnType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeResolvedLambdaAtom(FirAnonymousFunctionExpression firAnonymousFunctionExpression, ConeKotlinType coneKotlinType, FunctionTypeKind functionTypeKind, ConeKotlinType coneKotlinType2, List<? extends ConeKotlinType> list, List<? extends ConeKotlinType> list2, ConeKotlinType coneKotlinType3, ConeTypeVariableForLambdaReturnType coneTypeVariableForLambdaReturnType, boolean z, KtSourceElement ktSourceElement) {
        super(null);
        firAnonymousFunctionExpression.getClass();
        list.getClass();
        list2.getClass();
        coneKotlinType3.getClass();
        this.expression = firAnonymousFunctionExpression;
        this.expectedFunctionTypeKind = functionTypeKind;
        this.receiverType = coneKotlinType2;
        this.contextParameterTypes = list;
        this.parameterTypes = list2;
        this.returnType = coneKotlinType3;
        this.coerceFirstParameterToExtensionReceiver = z;
        this.sourceForFunctionExpression = ktSourceElement;
        this.anonymousFunction = getExpression().getAnonymousFunction();
        this.typeVariableForLambdaReturnType = coneTypeVariableForLambdaReturnType;
        this.expectedType = coneKotlinType;
    }

    public final FirAnonymousFunction getAnonymousFunction() {
        return this.anonymousFunction;
    }

    public final boolean getCoerceFirstParameterToExtensionReceiver() {
        return this.coerceFirstParameterToExtensionReceiver;
    }

    public final List<ConeKotlinType> getContextParameterTypes$org_jetbrains_kotlin_resolve() {
        return this.contextParameterTypes;
    }

    public final FunctionTypeKind getExpectedFunctionTypeKind() {
        return this.expectedFunctionTypeKind;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    public Collection<ConeKotlinType> getInputTypes() {
        if (this.receiverType == null && this.contextParameterTypes.isEmpty()) {
            return this.parameterTypes;
        }
        ArrayList arrayList = new ArrayList(this.parameterTypes.size() + this.contextParameterTypes.size() + (this.receiverType != null ? 1 : 0));
        arrayList.addAll(this.parameterTypes);
        CollectionsKt.addIfNotNull(arrayList, this.receiverType);
        arrayList.addAll(this.contextParameterTypes);
        return arrayList;
    }

    public final List<ConeKotlinType> getParameterTypes$org_jetbrains_kotlin_resolve() {
        return this.parameterTypes;
    }

    /* JADX INFO: renamed from: getReceiverType$org_jetbrains_kotlin_resolve, reason: from getter */
    public final ConeKotlinType getReceiverType() {
        return this.receiverType;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Collection<ConeResolutionAtom> getReturnStatements() throws UninitializedPropertyAccessException {
        Collection collection = this.returnStatements;
        if (collection != null) {
            return collection;
        }
        Intrinsics.throwUninitializedPropertyAccessException("returnStatements");
        return null;
    }

    public final ConeKotlinType getReturnType() {
        return this.returnType;
    }

    public final KtSourceElement getSourceForFunctionExpression() {
        return this.sourceForFunctionExpression;
    }

    public final ConeTypeVariableForLambdaReturnType getTypeVariableForLambdaReturnType() {
        return this.typeVariableForLambdaReturnType;
    }

    public final void replaceExpectedType(ConeKotlinType expectedType, ConeTypeVariableType newReturnType) {
        expectedType.getClass();
        newReturnType.getClass();
        this.expectedType = expectedType;
        this.returnType = newReturnType;
    }

    public final void replaceTypeVariableForLambdaReturnType(ConeTypeVariableForLambdaReturnType typeVariableForLambdaReturnType) {
        typeVariableForLambdaReturnType.getClass();
        this.typeVariableForLambdaReturnType = typeVariableForLambdaReturnType;
    }

    public final void setReturnStatements(Collection<? extends ConeResolutionAtom> collection) {
        collection.getClass();
        this.returnStatements = collection;
    }

    public final void setReturnType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        this.returnType = coneKotlinType;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getExpectedType, reason: from getter and merged with bridge method [inline-methods] */
    public ConeKotlinType mo581getExpectedType() {
        return this.expectedType;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirAnonymousFunctionExpression getExpression() {
        return this.expression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getOutputType, reason: from getter and merged with bridge method [inline-methods] */
    public ConeKotlinType mo582getOutputType() {
        return this.returnType;
    }
}
