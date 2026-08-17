package org.jetbrains.kotlin.contracts.parsing;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.ValueArgument;
import org.jetbrains.kotlin.resolve.ContractsDslNames;
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedValueArgument;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.typeUtil.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\f\u001a\u0004\u0018\u00010\r*\u0006\u0012\u0002\b\u00030\u000eH\u0000\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¨\u0006\u0012"}, d2 = {"isFromContractDsl", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "isContractCallDescriptor", "isImpliesCallDescriptor", "isReturnsEffectDescriptor", "isReturnsNotNullDescriptor", "isReturnsWildcardDescriptor", "isEffectDescriptor", "isCallsInPlaceEffectDescriptor", "isInvocationKindEnum", "isEqualsDescriptor", "firstArgumentAsExpressionOrNull", "Lorg/jetbrains/kotlin/psi/KtExpression;", "Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedCall;", "equalsDslDescriptor", "dslName", "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiContractsUtilsKt {
    private static final boolean equalsDslDescriptor(DeclarationDescriptor declarationDescriptor, Name name) {
        return Intrinsics.areEqual(declarationDescriptor.getName(), name) && isFromContractDsl(declarationDescriptor);
    }

    public static final KtExpression firstArgumentAsExpressionOrNull(ResolvedCall<?> resolvedCall) {
        ValueArgument valueArgument;
        resolvedCall.getClass();
        List valueArgumentsByIndex = resolvedCall.getValueArgumentsByIndex();
        ResolvedValueArgument resolvedValueArgument = valueArgumentsByIndex != null ? (ResolvedValueArgument) CollectionsKt.firstOrNull(valueArgumentsByIndex) : null;
        ExpressionValueArgument expressionValueArgument = resolvedValueArgument instanceof ExpressionValueArgument ? (ExpressionValueArgument) resolvedValueArgument : null;
        if (expressionValueArgument == null || (valueArgument = expressionValueArgument.getValueArgument()) == null) {
            return null;
        }
        return valueArgument.getArgumentExpression();
    }

    public static final boolean isCallsInPlaceEffectDescriptor(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return equalsDslDescriptor(declarationDescriptor, ContractsDslNames.INSTANCE.getCALLS_IN_PLACE().getCallableName());
    }

    public static final boolean isContractCallDescriptor(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return equalsDslDescriptor(declarationDescriptor, ContractsDslNames.INSTANCE.getCONTRACT().getCallableName());
    }

    public static final boolean isEffectDescriptor(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return equalsDslDescriptor(declarationDescriptor, ContractsDslNames.INSTANCE.getEFFECT().getCallableName());
    }

    public static final boolean isEqualsDescriptor(DeclarationDescriptor declarationDescriptor) {
        KotlinType returnType;
        KotlinType type;
        declarationDescriptor.getClass();
        if (!(declarationDescriptor instanceof FunctionDescriptor)) {
            return false;
        }
        FunctionDescriptor functionDescriptor = (FunctionDescriptor) declarationDescriptor;
        if (!Intrinsics.areEqual(functionDescriptor.getName(), Name.identifier("equals")) || functionDescriptor.getDispatchReceiverParameter() == null || (returnType = functionDescriptor.getReturnType()) == null || !TypeUtilsKt.isBoolean(returnType)) {
            return false;
        }
        List<ValueParameterDescriptor> valueParameters = functionDescriptor.getValueParameters();
        valueParameters.getClass();
        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) CollectionsKt.singleOrNull(valueParameters);
        return (valueParameterDescriptor == null || (type = valueParameterDescriptor.getType()) == null || !TypeUtilsKt.isNullableAny(type)) ? false : true;
    }

    public static final boolean isFromContractDsl(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return declarationDescriptor.getAnnotations().hasAnnotation(ContractsDslNames.INSTANCE.getCONTRACTS_DSL_ANNOTATION_FQN());
    }

    public static final boolean isImpliesCallDescriptor(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return equalsDslDescriptor(declarationDescriptor, ContractsDslNames.INSTANCE.getIMPLIES().getCallableName());
    }

    public static final boolean isInvocationKindEnum(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return equalsDslDescriptor(declarationDescriptor, ContractsDslNames.INSTANCE.getINVOCATION_KIND_ENUM().getCallableName());
    }

    public static final boolean isReturnsEffectDescriptor(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return equalsDslDescriptor(declarationDescriptor, ContractsDslNames.INSTANCE.getRETURNS().getCallableName());
    }

    public static final boolean isReturnsNotNullDescriptor(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return equalsDslDescriptor(declarationDescriptor, ContractsDslNames.INSTANCE.getRETURNS_NOT_NULL().getCallableName());
    }

    public static final boolean isReturnsWildcardDescriptor(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return equalsDslDescriptor(declarationDescriptor, ContractsDslNames.INSTANCE.getRETURNS().getCallableName()) && (declarationDescriptor instanceof FunctionDescriptor) && ((FunctionDescriptor) declarationDescriptor).getValueParameters().isEmpty();
    }
}
