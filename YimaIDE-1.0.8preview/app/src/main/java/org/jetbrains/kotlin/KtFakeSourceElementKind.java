package org.jetbrains.kotlin;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u008c\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\bo\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:k\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrB\u0013\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001¯\u0001stuvwxyz{|}~\u007f\u0080\u0001\u0081\u0001\u0082\u0001\u0083\u0001\u0084\u0001\u0085\u0001\u0086\u0001\u0087\u0001\u0088\u0001\u0089\u0001\u008a\u0001\u008b\u0001\u008c\u0001\u008d\u0001\u008e\u0001\u008f\u0001\u0090\u0001\u0091\u0001\u0092\u0001\u0093\u0001\u0094\u0001\u0095\u0001\u0096\u0001\u0097\u0001\u0098\u0001\u0099\u0001\u009a\u0001\u009b\u0001\u009c\u0001\u009d\u0001\u009e\u0001\u009f\u0001 \u0001¡\u0001¢\u0001£\u0001¤\u0001¥\u0001¦\u0001§\u0001¨\u0001©\u0001ª\u0001«\u0001¬\u0001\u00ad\u0001®\u0001¯\u0001°\u0001±\u0001²\u0001³\u0001´\u0001µ\u0001¶\u0001·\u0001¸\u0001¹\u0001º\u0001»\u0001¼\u0001½\u0001¾\u0001¿\u0001À\u0001Á\u0001Â\u0001Ã\u0001Ä\u0001Å\u0001Æ\u0001Ç\u0001È\u0001É\u0001Ê\u0001Ë\u0001Ì\u0001Í\u0001Î\u0001Ï\u0001Ð\u0001¨\u0006Ñ\u0001"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "Lorg/jetbrains/kotlin/KtSourceElementKind;", "shouldSkipErrorTypeReporting", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Z)V", "getShouldSkipErrorTypeReporting", "()Z", "ImplicitTypeRef", "ImplicitThisReceiverExpression", "ImplicitContextParameterArgument", "ImplicitTypeArgument", "ErroneousTypealiasExpansion", "ImplicitFunctionReturnType", "ClassSelfTypeRef", "ErrorTypeRef", "DefaultAccessor", "DelegatedPropertyAccessor", "ImplicitConstructor", "ConstructorTypeParameter", "DelegatingConstructorCall", "EnumInitializer", "GeneratedLambdaLabel", "DanglingModifierList", "ImplicitReturn", "ImplicitUnit", "WrappedDelegate", "DesugaredForLoop", "ImplicitInvokeCall", "ReferenceInAtomicQualifiedAccess", "EnumGeneratedDeclaration", "EnumSuperTypeRef", "RecordSuperTypeRef", "WhenCondition", "CodeFragment", "UnresolvedWhenConditionSubject", "PropertyFromParameter", "SingleExpressionBlock", "IndexedAssignmentCoercionBlock", "ContractBlock", "DesugaredIncrementOrDecrement", "DesugaredPrefixInc", "DesugaredPrefixDec", "DesugaredPostfixInc", "DesugaredPostfixDec", "DesugaredPrefixSecondGetReference", "DesugaredPrefixIncSecondGetReference", "DesugaredPrefixDecSecondGetReference", "DesugaredInvertedContains", "DataClassGeneratedMembers", "MembersImplementedByDelegation", "ArrayTypeFromVarargParameter", "DestructuringInitializer", "DesugaredComponentFunctionCall", "DesugaredNameBasedDestructuring", "SmartCastedTypeRef", "SmartCastExpression", "DesugaredSafeCallExpression", "GeneratedComparisonExpression", "WhenGeneratedSubject", "ArrayAccessNameReference", "DesugaredAugmentedAssign", "DesugaredPlusAssign", "DesugaredMinusAssign", "DesugaredTimesAssign", "DesugaredDivAssign", "DesugaredRemAssign", "AssignmentPluginAltered", "ArrayIndexExpressionReference", "SuperCallImplicitType", "VarargArgument", "CheckedSafeCallSubject", "ItLambdaParameter", "LambdaContextParameter", "LambdaReceiver", "DestructuringBlock", "LambdaDestructuringBlock", "ImplicitJavaAnnotationConstructor", "Enhancement", "ImplicitAnnotationAnnotationConstructorParameter", "ImplicitJavaRecordConstructor", "ImplicitRecordConstructorParameter", "JavaRecordComponentFunction", "JavaRecordComponentField", "ClassDelegationField", "FromUseSiteTarget", "ParameterNameAnnotationCall", "IntToLongConversion", "ReceiverFromType", "ImplicitReceiver", "AssignmentLValueError", "DesugaredAssignmentLValueSourceIsNull", "ImplicitReturnTypeOfLambdaValueParameter", "SyntheticCall", "PropertyTypeFromGetterReturnType", "ImplicitImport", "ScriptParameter", "ScriptBaseClass", "ReplBaseClass", "ReplEvalFunction", "SamConversion", "FunctionTypeConversion", "SamConstructor", "CastToAnyForStubTypes", "ContextParameterDefaultValue", "PluginGenerated", "ErrorExpressionForTransformedArrayOf", "ErrorExpressionForTopLevelLambda", "ErrorExpressionForTopLevelCollectionLiteral", "ErrorExpression", "QualifierForContextSensitiveResolution", "DesugaredReceiverForOperatorOfCall", "CalleeReferenceForOperatorOfCall", "ContextSensitiveAlternative", "ReferenceForContextSensitiveAlternative", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ArrayAccessNameReference;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ArrayIndexExpressionReference;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ArrayTypeFromVarargParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$AssignmentLValueError;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$AssignmentPluginAltered;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$CalleeReferenceForOperatorOfCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$CastToAnyForStubTypes;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$CheckedSafeCallSubject;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ClassDelegationField;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ClassSelfTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$CodeFragment;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ConstructorTypeParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ContextParameterDefaultValue;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ContextSensitiveAlternative;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ContractBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DanglingModifierList;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DataClassGeneratedMembers;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DefaultAccessor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DelegatedPropertyAccessor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DelegatingConstructorCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DestructuringBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DestructuringInitializer;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAssignmentLValueSourceIsNull;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAugmentedAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredForLoop;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredIncrementOrDecrement;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredInvertedContains;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixSecondGetReference;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredReceiverForOperatorOfCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredSafeCallExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$Enhancement;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$EnumGeneratedDeclaration;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$EnumInitializer;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$EnumSuperTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErroneousTypealiasExpansion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorExpressionForTopLevelCollectionLiteral;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorExpressionForTopLevelLambda;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorExpressionForTransformedArrayOf;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$FromUseSiteTarget;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$FunctionTypeConversion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$GeneratedComparisonExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$GeneratedLambdaLabel;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitAnnotationAnnotationConstructorParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitConstructor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitContextParameterArgument;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitFunctionReturnType;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitImport;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitInvokeCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitJavaAnnotationConstructor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitJavaRecordConstructor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReceiver;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitRecordConstructorParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturn;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturnTypeOfLambdaValueParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitThisReceiverExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitTypeArgument;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$IndexedAssignmentCoercionBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$IntToLongConversion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ItLambdaParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$JavaRecordComponentField;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$JavaRecordComponentFunction;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$LambdaContextParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$LambdaDestructuringBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$LambdaReceiver;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$MembersImplementedByDelegation;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ParameterNameAnnotationCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$PluginGenerated;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$PropertyFromParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$PropertyTypeFromGetterReturnType;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$QualifierForContextSensitiveResolution;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReceiverFromType;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$RecordSuperTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReferenceForContextSensitiveAlternative;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReferenceInAtomicQualifiedAccess;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReplBaseClass;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReplEvalFunction;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SamConstructor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SamConversion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ScriptBaseClass;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ScriptParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SingleExpressionBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SmartCastExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SmartCastedTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SuperCallImplicitType;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SyntheticCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$UnresolvedWhenConditionSubject;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$VarargArgument;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$WhenCondition;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$WhenGeneratedSubject;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$WrappedDelegate;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class KtFakeSourceElementKind extends KtSourceElementKind {
    private final boolean shouldSkipErrorTypeReporting;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ArrayAccessNameReference;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ArrayAccessNameReference extends KtFakeSourceElementKind {
        public static final ArrayAccessNameReference INSTANCE = new ArrayAccessNameReference();

        private ArrayAccessNameReference() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ArrayIndexExpressionReference;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ArrayIndexExpressionReference extends KtFakeSourceElementKind {
        public static final ArrayIndexExpressionReference INSTANCE = new ArrayIndexExpressionReference();

        private ArrayIndexExpressionReference() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ArrayTypeFromVarargParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ArrayTypeFromVarargParameter extends KtFakeSourceElementKind {
        public static final ArrayTypeFromVarargParameter INSTANCE = new ArrayTypeFromVarargParameter();

        private ArrayTypeFromVarargParameter() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$AssignmentLValueError;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class AssignmentLValueError extends KtFakeSourceElementKind {
        public static final AssignmentLValueError INSTANCE = new AssignmentLValueError();

        private AssignmentLValueError() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$AssignmentPluginAltered;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class AssignmentPluginAltered extends KtFakeSourceElementKind {
        public static final AssignmentPluginAltered INSTANCE = new AssignmentPluginAltered();

        private AssignmentPluginAltered() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$CalleeReferenceForOperatorOfCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class CalleeReferenceForOperatorOfCall extends KtFakeSourceElementKind {
        public static final CalleeReferenceForOperatorOfCall INSTANCE = new CalleeReferenceForOperatorOfCall();

        private CalleeReferenceForOperatorOfCall() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$CastToAnyForStubTypes;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class CastToAnyForStubTypes extends KtFakeSourceElementKind {
        public static final CastToAnyForStubTypes INSTANCE = new CastToAnyForStubTypes();

        private CastToAnyForStubTypes() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$CheckedSafeCallSubject;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class CheckedSafeCallSubject extends KtFakeSourceElementKind {
        public static final CheckedSafeCallSubject INSTANCE = new CheckedSafeCallSubject();

        private CheckedSafeCallSubject() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ClassDelegationField;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ClassDelegationField extends KtFakeSourceElementKind {
        public static final ClassDelegationField INSTANCE = new ClassDelegationField();

        private ClassDelegationField() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ClassSelfTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ClassSelfTypeRef extends KtFakeSourceElementKind {
        public static final ClassSelfTypeRef INSTANCE = new ClassSelfTypeRef();

        private ClassSelfTypeRef() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$CodeFragment;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class CodeFragment extends KtFakeSourceElementKind {
        public static final CodeFragment INSTANCE = new CodeFragment();

        private CodeFragment() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ConstructorTypeParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ConstructorTypeParameter extends KtFakeSourceElementKind {
        public static final ConstructorTypeParameter INSTANCE = new ConstructorTypeParameter();

        private ConstructorTypeParameter() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ContextParameterDefaultValue;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ContextParameterDefaultValue extends KtFakeSourceElementKind {
        public static final ContextParameterDefaultValue INSTANCE = new ContextParameterDefaultValue();

        private ContextParameterDefaultValue() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ContextSensitiveAlternative;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ContextSensitiveAlternative extends KtFakeSourceElementKind {
        public static final ContextSensitiveAlternative INSTANCE = new ContextSensitiveAlternative();

        private ContextSensitiveAlternative() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ContractBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ContractBlock extends KtFakeSourceElementKind {
        public static final ContractBlock INSTANCE = new ContractBlock();

        private ContractBlock() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DanglingModifierList;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DanglingModifierList extends KtFakeSourceElementKind {
        public static final DanglingModifierList INSTANCE = new DanglingModifierList();

        private DanglingModifierList() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DataClassGeneratedMembers;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DataClassGeneratedMembers extends KtFakeSourceElementKind {
        public static final DataClassGeneratedMembers INSTANCE = new DataClassGeneratedMembers();

        private DataClassGeneratedMembers() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DefaultAccessor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DefaultAccessor extends KtFakeSourceElementKind {
        public static final DefaultAccessor INSTANCE = new DefaultAccessor();

        private DefaultAccessor() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DelegatedPropertyAccessor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DelegatedPropertyAccessor extends KtFakeSourceElementKind {
        public static final DelegatedPropertyAccessor INSTANCE = new DelegatedPropertyAccessor();

        private DelegatedPropertyAccessor() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DelegatingConstructorCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DelegatingConstructorCall extends KtFakeSourceElementKind {
        public static final DelegatingConstructorCall INSTANCE = new DelegatingConstructorCall();

        private DelegatingConstructorCall() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DestructuringBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DestructuringBlock extends KtFakeSourceElementKind {
        public static final DestructuringBlock INSTANCE = new DestructuringBlock();

        private DestructuringBlock() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAssignmentLValueSourceIsNull;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredAssignmentLValueSourceIsNull extends KtFakeSourceElementKind {
        public static final DesugaredAssignmentLValueSourceIsNull INSTANCE = new DesugaredAssignmentLValueSourceIsNull();

        private DesugaredAssignmentLValueSourceIsNull() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredComponentFunctionCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DestructuringInitializer;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredComponentFunctionCall extends DestructuringInitializer {
        public static final DesugaredComponentFunctionCall INSTANCE = new DesugaredComponentFunctionCall();

        private DesugaredComponentFunctionCall() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredDivAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAugmentedAssign;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredDivAssign extends DesugaredAugmentedAssign {
        public static final DesugaredDivAssign INSTANCE = new DesugaredDivAssign();

        private DesugaredDivAssign() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredForLoop;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredForLoop extends KtFakeSourceElementKind {
        public static final DesugaredForLoop INSTANCE = new DesugaredForLoop();

        private DesugaredForLoop() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredInvertedContains;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredInvertedContains extends KtFakeSourceElementKind {
        public static final DesugaredInvertedContains INSTANCE = new DesugaredInvertedContains();

        private DesugaredInvertedContains() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredMinusAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAugmentedAssign;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredMinusAssign extends DesugaredAugmentedAssign {
        public static final DesugaredMinusAssign INSTANCE = new DesugaredMinusAssign();

        private DesugaredMinusAssign() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredNameBasedDestructuring;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DestructuringInitializer;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredNameBasedDestructuring extends DestructuringInitializer {
        public static final DesugaredNameBasedDestructuring INSTANCE = new DesugaredNameBasedDestructuring();

        private DesugaredNameBasedDestructuring() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPlusAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAugmentedAssign;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredPlusAssign extends DesugaredAugmentedAssign {
        public static final DesugaredPlusAssign INSTANCE = new DesugaredPlusAssign();

        private DesugaredPlusAssign() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPostfixDec;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredIncrementOrDecrement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredPostfixDec extends DesugaredIncrementOrDecrement {
        public static final DesugaredPostfixDec INSTANCE = new DesugaredPostfixDec();

        private DesugaredPostfixDec() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPostfixInc;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredIncrementOrDecrement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredPostfixInc extends DesugaredIncrementOrDecrement {
        public static final DesugaredPostfixInc INSTANCE = new DesugaredPostfixInc();

        private DesugaredPostfixInc() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixDec;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredIncrementOrDecrement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredPrefixDec extends DesugaredIncrementOrDecrement {
        public static final DesugaredPrefixDec INSTANCE = new DesugaredPrefixDec();

        private DesugaredPrefixDec() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixDecSecondGetReference;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixSecondGetReference;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredPrefixDecSecondGetReference extends DesugaredPrefixSecondGetReference {
        public static final DesugaredPrefixDecSecondGetReference INSTANCE = new DesugaredPrefixDecSecondGetReference();

        private DesugaredPrefixDecSecondGetReference() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixInc;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredIncrementOrDecrement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredPrefixInc extends DesugaredIncrementOrDecrement {
        public static final DesugaredPrefixInc INSTANCE = new DesugaredPrefixInc();

        private DesugaredPrefixInc() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixIncSecondGetReference;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixSecondGetReference;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredPrefixIncSecondGetReference extends DesugaredPrefixSecondGetReference {
        public static final DesugaredPrefixIncSecondGetReference INSTANCE = new DesugaredPrefixIncSecondGetReference();

        private DesugaredPrefixIncSecondGetReference() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredReceiverForOperatorOfCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredReceiverForOperatorOfCall extends KtFakeSourceElementKind {
        public static final DesugaredReceiverForOperatorOfCall INSTANCE = new DesugaredReceiverForOperatorOfCall();

        private DesugaredReceiverForOperatorOfCall() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredRemAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAugmentedAssign;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredRemAssign extends DesugaredAugmentedAssign {
        public static final DesugaredRemAssign INSTANCE = new DesugaredRemAssign();

        private DesugaredRemAssign() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredSafeCallExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredSafeCallExpression extends KtFakeSourceElementKind {
        public static final DesugaredSafeCallExpression INSTANCE = new DesugaredSafeCallExpression();

        private DesugaredSafeCallExpression() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredTimesAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAugmentedAssign;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DesugaredTimesAssign extends DesugaredAugmentedAssign {
        public static final DesugaredTimesAssign INSTANCE = new DesugaredTimesAssign();

        private DesugaredTimesAssign() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$Enhancement;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Enhancement extends KtFakeSourceElementKind {
        public static final Enhancement INSTANCE = new Enhancement();

        private Enhancement() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$EnumGeneratedDeclaration;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class EnumGeneratedDeclaration extends KtFakeSourceElementKind {
        public static final EnumGeneratedDeclaration INSTANCE = new EnumGeneratedDeclaration();

        private EnumGeneratedDeclaration() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$EnumInitializer;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class EnumInitializer extends KtFakeSourceElementKind {
        public static final EnumInitializer INSTANCE = new EnumInitializer();

        private EnumInitializer() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$EnumSuperTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class EnumSuperTypeRef extends KtFakeSourceElementKind {
        public static final EnumSuperTypeRef INSTANCE = new EnumSuperTypeRef();

        private EnumSuperTypeRef() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErroneousTypealiasExpansion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ErroneousTypealiasExpansion extends KtFakeSourceElementKind {
        public static final ErroneousTypealiasExpansion INSTANCE = new ErroneousTypealiasExpansion();

        private ErroneousTypealiasExpansion() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ErrorExpression extends KtFakeSourceElementKind {
        public static final ErrorExpression INSTANCE = new ErrorExpression();

        private ErrorExpression() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorExpressionForTopLevelCollectionLiteral;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ErrorExpressionForTopLevelCollectionLiteral extends KtFakeSourceElementKind {
        public static final ErrorExpressionForTopLevelCollectionLiteral INSTANCE = new ErrorExpressionForTopLevelCollectionLiteral();

        private ErrorExpressionForTopLevelCollectionLiteral() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorExpressionForTopLevelLambda;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ErrorExpressionForTopLevelLambda extends KtFakeSourceElementKind {
        public static final ErrorExpressionForTopLevelLambda INSTANCE = new ErrorExpressionForTopLevelLambda();

        private ErrorExpressionForTopLevelLambda() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorExpressionForTransformedArrayOf;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ErrorExpressionForTransformedArrayOf extends KtFakeSourceElementKind {
        public static final ErrorExpressionForTransformedArrayOf INSTANCE = new ErrorExpressionForTransformedArrayOf();

        private ErrorExpressionForTransformedArrayOf() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ErrorTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ErrorTypeRef extends KtFakeSourceElementKind {
        public static final ErrorTypeRef INSTANCE = new ErrorTypeRef();

        private ErrorTypeRef() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$FromUseSiteTarget;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class FromUseSiteTarget extends KtFakeSourceElementKind {
        public static final FromUseSiteTarget INSTANCE = new FromUseSiteTarget();

        private FromUseSiteTarget() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$FunctionTypeConversion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class FunctionTypeConversion extends KtFakeSourceElementKind {
        public static final FunctionTypeConversion INSTANCE = new FunctionTypeConversion();

        private FunctionTypeConversion() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$GeneratedComparisonExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class GeneratedComparisonExpression extends KtFakeSourceElementKind {
        public static final GeneratedComparisonExpression INSTANCE = new GeneratedComparisonExpression();

        private GeneratedComparisonExpression() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$GeneratedLambdaLabel;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class GeneratedLambdaLabel extends KtFakeSourceElementKind {
        public static final GeneratedLambdaLabel INSTANCE = new GeneratedLambdaLabel();

        private GeneratedLambdaLabel() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitAnnotationAnnotationConstructorParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitAnnotationAnnotationConstructorParameter extends KtFakeSourceElementKind {
        public static final ImplicitAnnotationAnnotationConstructorParameter INSTANCE = new ImplicitAnnotationAnnotationConstructorParameter();

        private ImplicitAnnotationAnnotationConstructorParameter() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitConstructor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitConstructor extends KtFakeSourceElementKind {
        public static final ImplicitConstructor INSTANCE = new ImplicitConstructor();

        private ImplicitConstructor() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitContextParameterArgument;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitContextParameterArgument extends KtFakeSourceElementKind {
        public static final ImplicitContextParameterArgument INSTANCE = new ImplicitContextParameterArgument();

        private ImplicitContextParameterArgument() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitFunctionReturnType;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitFunctionReturnType extends KtFakeSourceElementKind {
        public static final ImplicitFunctionReturnType INSTANCE = new ImplicitFunctionReturnType();

        private ImplicitFunctionReturnType() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitImport;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitImport extends KtFakeSourceElementKind {
        public static final ImplicitImport INSTANCE = new ImplicitImport();

        private ImplicitImport() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitInvokeCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitInvokeCall extends KtFakeSourceElementKind {
        public static final ImplicitInvokeCall INSTANCE = new ImplicitInvokeCall();

        private ImplicitInvokeCall() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitJavaAnnotationConstructor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitJavaAnnotationConstructor extends KtFakeSourceElementKind {
        public static final ImplicitJavaAnnotationConstructor INSTANCE = new ImplicitJavaAnnotationConstructor();

        private ImplicitJavaAnnotationConstructor() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitJavaRecordConstructor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitJavaRecordConstructor extends KtFakeSourceElementKind {
        public static final ImplicitJavaRecordConstructor INSTANCE = new ImplicitJavaRecordConstructor();

        private ImplicitJavaRecordConstructor() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReceiver;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitReceiver extends KtFakeSourceElementKind {
        public static final ImplicitReceiver INSTANCE = new ImplicitReceiver();

        private ImplicitReceiver() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitRecordConstructorParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitRecordConstructorParameter extends KtFakeSourceElementKind {
        public static final ImplicitRecordConstructorParameter INSTANCE = new ImplicitRecordConstructorParameter();

        private ImplicitRecordConstructorParameter() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturnTypeOfLambdaValueParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitReturnTypeOfLambdaValueParameter extends KtFakeSourceElementKind {
        public static final ImplicitReturnTypeOfLambdaValueParameter INSTANCE = new ImplicitReturnTypeOfLambdaValueParameter();

        private ImplicitReturnTypeOfLambdaValueParameter() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitThisReceiverExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitThisReceiverExpression extends KtFakeSourceElementKind {
        public static final ImplicitThisReceiverExpression INSTANCE = new ImplicitThisReceiverExpression();

        private ImplicitThisReceiverExpression() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitTypeArgument;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitTypeArgument extends KtFakeSourceElementKind {
        public static final ImplicitTypeArgument INSTANCE = new ImplicitTypeArgument();

        private ImplicitTypeArgument() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ImplicitTypeRef extends KtFakeSourceElementKind {
        public static final ImplicitTypeRef INSTANCE = new ImplicitTypeRef();

        private ImplicitTypeRef() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$IndexedAssignmentCoercionBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class IndexedAssignmentCoercionBlock extends KtFakeSourceElementKind {
        public static final IndexedAssignmentCoercionBlock INSTANCE = new IndexedAssignmentCoercionBlock();

        private IndexedAssignmentCoercionBlock() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$IntToLongConversion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class IntToLongConversion extends KtFakeSourceElementKind {
        public static final IntToLongConversion INSTANCE = new IntToLongConversion();

        private IntToLongConversion() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ItLambdaParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ItLambdaParameter extends KtFakeSourceElementKind {
        public static final ItLambdaParameter INSTANCE = new ItLambdaParameter();

        private ItLambdaParameter() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$JavaRecordComponentField;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class JavaRecordComponentField extends KtFakeSourceElementKind {
        public static final JavaRecordComponentField INSTANCE = new JavaRecordComponentField();

        private JavaRecordComponentField() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$JavaRecordComponentFunction;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class JavaRecordComponentFunction extends KtFakeSourceElementKind {
        public static final JavaRecordComponentFunction INSTANCE = new JavaRecordComponentFunction();

        private JavaRecordComponentFunction() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$LambdaContextParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class LambdaContextParameter extends KtFakeSourceElementKind {
        public static final LambdaContextParameter INSTANCE = new LambdaContextParameter();

        private LambdaContextParameter() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$LambdaDestructuringBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class LambdaDestructuringBlock extends KtFakeSourceElementKind {
        public static final LambdaDestructuringBlock INSTANCE = new LambdaDestructuringBlock();

        private LambdaDestructuringBlock() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$LambdaReceiver;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class LambdaReceiver extends KtFakeSourceElementKind {
        public static final LambdaReceiver INSTANCE = new LambdaReceiver();

        private LambdaReceiver() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$MembersImplementedByDelegation;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class MembersImplementedByDelegation extends KtFakeSourceElementKind {
        public static final MembersImplementedByDelegation INSTANCE = new MembersImplementedByDelegation();

        private MembersImplementedByDelegation() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ParameterNameAnnotationCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ParameterNameAnnotationCall extends KtFakeSourceElementKind {
        public static final ParameterNameAnnotationCall INSTANCE = new ParameterNameAnnotationCall();

        private ParameterNameAnnotationCall() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$PluginGenerated;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class PluginGenerated extends KtFakeSourceElementKind {
        public static final PluginGenerated INSTANCE = new PluginGenerated();

        private PluginGenerated() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$PropertyFromParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class PropertyFromParameter extends KtFakeSourceElementKind {
        public static final PropertyFromParameter INSTANCE = new PropertyFromParameter();

        private PropertyFromParameter() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$PropertyTypeFromGetterReturnType;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class PropertyTypeFromGetterReturnType extends KtFakeSourceElementKind {
        public static final PropertyTypeFromGetterReturnType INSTANCE = new PropertyTypeFromGetterReturnType();

        private PropertyTypeFromGetterReturnType() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$QualifierForContextSensitiveResolution;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class QualifierForContextSensitiveResolution extends KtFakeSourceElementKind {
        public static final QualifierForContextSensitiveResolution INSTANCE = new QualifierForContextSensitiveResolution();

        private QualifierForContextSensitiveResolution() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReceiverFromType;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ReceiverFromType extends KtFakeSourceElementKind {
        public static final ReceiverFromType INSTANCE = new ReceiverFromType();

        private ReceiverFromType() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$RecordSuperTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class RecordSuperTypeRef extends KtFakeSourceElementKind {
        public static final RecordSuperTypeRef INSTANCE = new RecordSuperTypeRef();

        private RecordSuperTypeRef() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReferenceForContextSensitiveAlternative;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ReferenceForContextSensitiveAlternative extends KtFakeSourceElementKind {
        public static final ReferenceForContextSensitiveAlternative INSTANCE = new ReferenceForContextSensitiveAlternative();

        private ReferenceForContextSensitiveAlternative() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReferenceInAtomicQualifiedAccess;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ReferenceInAtomicQualifiedAccess extends KtFakeSourceElementKind {
        public static final ReferenceInAtomicQualifiedAccess INSTANCE = new ReferenceInAtomicQualifiedAccess();

        private ReferenceInAtomicQualifiedAccess() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReplBaseClass;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ReplBaseClass extends KtFakeSourceElementKind {
        public static final ReplBaseClass INSTANCE = new ReplBaseClass();

        private ReplBaseClass() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ReplEvalFunction;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ReplEvalFunction extends KtFakeSourceElementKind {
        public static final ReplEvalFunction INSTANCE = new ReplEvalFunction();

        private ReplEvalFunction() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SamConstructor;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SamConstructor extends KtFakeSourceElementKind {
        public static final SamConstructor INSTANCE = new SamConstructor();

        private SamConstructor() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SamConversion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SamConversion extends KtFakeSourceElementKind {
        public static final SamConversion INSTANCE = new SamConversion();

        private SamConversion() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ScriptBaseClass;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ScriptBaseClass extends KtFakeSourceElementKind {
        public static final ScriptBaseClass INSTANCE = new ScriptBaseClass();

        private ScriptBaseClass() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ScriptParameter;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ScriptParameter extends KtFakeSourceElementKind {
        public static final ScriptParameter INSTANCE = new ScriptParameter();

        private ScriptParameter() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SingleExpressionBlock;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SingleExpressionBlock extends KtFakeSourceElementKind {
        public static final SingleExpressionBlock INSTANCE = new SingleExpressionBlock();

        private SingleExpressionBlock() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SmartCastExpression;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SmartCastExpression extends KtFakeSourceElementKind {
        public static final SmartCastExpression INSTANCE = new SmartCastExpression();

        private SmartCastExpression() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SmartCastedTypeRef;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SmartCastedTypeRef extends KtFakeSourceElementKind {
        public static final SmartCastedTypeRef INSTANCE = new SmartCastedTypeRef();

        private SmartCastedTypeRef() {
            super(true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SuperCallImplicitType;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SuperCallImplicitType extends KtFakeSourceElementKind {
        public static final SuperCallImplicitType INSTANCE = new SuperCallImplicitType();

        private SuperCallImplicitType() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$SyntheticCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SyntheticCall extends KtFakeSourceElementKind {
        public static final SyntheticCall INSTANCE = new SyntheticCall();

        private SyntheticCall() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$UnresolvedWhenConditionSubject;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class UnresolvedWhenConditionSubject extends KtFakeSourceElementKind {
        public static final UnresolvedWhenConditionSubject INSTANCE = new UnresolvedWhenConditionSubject();

        private UnresolvedWhenConditionSubject() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$VarargArgument;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class VarargArgument extends KtFakeSourceElementKind {
        public static final VarargArgument INSTANCE = new VarargArgument();

        private VarargArgument() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$WhenCondition;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class WhenCondition extends KtFakeSourceElementKind {
        public static final WhenCondition INSTANCE = new WhenCondition();

        private WhenCondition() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$WhenGeneratedSubject;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class WhenGeneratedSubject extends KtFakeSourceElementKind {
        public static final WhenGeneratedSubject INSTANCE = new WhenGeneratedSubject();

        private WhenGeneratedSubject() {
            super(false, 1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$WrappedDelegate;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class WrappedDelegate extends KtFakeSourceElementKind {
        public static final WrappedDelegate INSTANCE = new WrappedDelegate();

        private WrappedDelegate() {
            super(false, 1, null);
        }
    }

    public /* synthetic */ KtFakeSourceElementKind(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, null);
    }

    @Override // org.jetbrains.kotlin.KtSourceElementKind
    public final boolean getShouldSkipErrorTypeReporting() {
        return this.shouldSkipErrorTypeReporting;
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DestructuringInitializer;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredComponentFunctionCall;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredNameBasedDestructuring;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class DestructuringInitializer extends KtFakeSourceElementKind {
        private DestructuringInitializer() {
            super(false, 1, null);
        }

        public /* synthetic */ DestructuringInitializer(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0005\u0004\u0005\u0006\u0007\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAugmentedAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredDivAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredMinusAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPlusAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredRemAssign;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredTimesAssign;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class DesugaredAugmentedAssign extends KtFakeSourceElementKind {
        private DesugaredAugmentedAssign() {
            super(false, 1, null);
        }

        public /* synthetic */ DesugaredAugmentedAssign(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\u0004\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredIncrementOrDecrement;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPostfixDec;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPostfixInc;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixDec;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixInc;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class DesugaredIncrementOrDecrement extends KtFakeSourceElementKind {
        private DesugaredIncrementOrDecrement() {
            super(false, 1, null);
        }

        public /* synthetic */ DesugaredIncrementOrDecrement(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixSecondGetReference;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixDecSecondGetReference;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredPrefixIncSecondGetReference;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class DesugaredPrefixSecondGetReference extends KtFakeSourceElementKind {
        private DesugaredPrefixSecondGetReference() {
            super(false, 1, null);
        }

        public /* synthetic */ DesugaredPrefixSecondGetReference(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturn;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "FromExpressionBody", "FromLastStatement", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturn$FromExpressionBody;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturn$FromLastStatement;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class ImplicitReturn extends KtFakeSourceElementKind {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturn$FromExpressionBody;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturn;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class FromExpressionBody extends ImplicitReturn {
            public static final FromExpressionBody INSTANCE = new FromExpressionBody();

            private FromExpressionBody() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturn$FromLastStatement;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitReturn;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class FromLastStatement extends ImplicitReturn {
            public static final FromLastStatement INSTANCE = new FromLastStatement();

            private FromLastStatement() {
                super(null);
            }
        }

        private ImplicitReturn() {
            super(false, 1, null);
        }

        public /* synthetic */ ImplicitReturn(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "ForEmptyLambda", "Return", "IndexedAssignmentCoercion", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit$ForEmptyLambda;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit$IndexedAssignmentCoercion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit$Return;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class ImplicitUnit extends KtFakeSourceElementKind {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit$ForEmptyLambda;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class ForEmptyLambda extends ImplicitUnit {
            public static final ForEmptyLambda INSTANCE = new ForEmptyLambda();

            private ForEmptyLambda() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit$IndexedAssignmentCoercion;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class IndexedAssignmentCoercion extends ImplicitUnit {
            public static final IndexedAssignmentCoercion INSTANCE = new IndexedAssignmentCoercion();

            private IndexedAssignmentCoercion() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit$Return;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$ImplicitUnit;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Return extends ImplicitUnit {
            public static final Return INSTANCE = new Return();

            private Return() {
                super(null);
            }
        }

        private ImplicitUnit() {
            super(false, 1, null);
        }

        public /* synthetic */ ImplicitUnit(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private KtFakeSourceElementKind(boolean z) {
        super(null);
        this.shouldSkipErrorTypeReporting = z;
    }

    public /* synthetic */ KtFakeSourceElementKind(boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(z);
    }
}
