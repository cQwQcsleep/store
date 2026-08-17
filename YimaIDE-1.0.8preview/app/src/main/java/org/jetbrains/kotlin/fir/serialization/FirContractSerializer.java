package org.jetbrains.kotlin.fir.serialization;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.contracts.description.KtBinaryLogicExpression;
import org.jetbrains.kotlin.contracts.description.KtCallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalReturnsDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConstantReference;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtHoldsInEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtIsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.KtIsNullPredicate;
import org.jetbrains.kotlin.contracts.description.KtLogicalNot;
import org.jetbrains.kotlin.contracts.description.KtReturnsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtReturnsResultOfDeclaration;
import org.jetbrains.kotlin.contracts.description.KtValueParameterReference;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.fir.contracts.ContractUtilsKt;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.description.ConeContractConstantValues;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirContractSerializer;", Argument.Delimiters.none, "<init>", "()V", "serializeContractOfFunctionIfAny", Argument.Delimiters.none, "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", "parentSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "buildAccessorContractProtoIfAny", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Contract$Builder;", "accessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "ContractSerializerWorker", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContractSerializer {

    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ(\u0010\n\u001a\u00020\u000b2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rj\u0002`\u00102\u0006\u0010\b\u001a\u00020\tH\u0002J0\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rj\u0002`\u00102\u0006\u0010\b\u001a\u00020\tH\u0002J(\u0010\u0014\u001a\u00020\u00152\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0017j\u0002`\u00182\u0006\u0010\b\u001a\u00020\tH\u0002J\u0014\u0010\u0019\u001a\u00020\u0012*\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\"\u0010 \u001a\u0004\u0018\u00010!2\u0016\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0#j\u0002`$H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirContractSerializer$ContractSerializerWorker;", Argument.Delimiters.none, "parentSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "<init>", "(Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;)V", "contractProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Contract$Builder;", "contractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "effectProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Effect$Builder;", "effectDeclaration", "Lorg/jetbrains/kotlin/contracts/description/KtEffectDeclaration;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeEffectDeclaration;", "fillEffectProto", Argument.Delimiters.none, "builder", "contractExpressionProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Expression$Builder;", "contractDescriptionElement", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractDescriptionElement;", "writeFlags", "newFlagsValue", Argument.Delimiters.none, "invocationKindProtobufEnum", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Effect$InvocationKind;", "kind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "constantValueProtobufEnum", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Expression$ConstantValue;", "constantReference", "Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeConstantReference;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ContractSerializerWorker {
        private final FirElementSerializer parentSerializer;

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[EventOccurrencesRange.values().length];
                try {
                    iArr[EventOccurrencesRange.AT_MOST_ONCE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[EventOccurrencesRange.EXACTLY_ONCE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[EventOccurrencesRange.AT_LEAST_ONCE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public ContractSerializerWorker(FirElementSerializer firElementSerializer) {
            firElementSerializer.getClass();
            this.parentSerializer = firElementSerializer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ProtoBuf.Expression.ConstantValue constantValueProtobufEnum(KtConstantReference<ConeKotlinType, ConeDiagnostic> constantReference) {
            ConeContractConstantValues coneContractConstantValues = ConeContractConstantValues.INSTANCE;
            if (Intrinsics.areEqual(constantReference, coneContractConstantValues.getTRUE())) {
                return ProtoBuf.Expression.ConstantValue.TRUE;
            }
            if (Intrinsics.areEqual(constantReference, coneContractConstantValues.getFALSE())) {
                return ProtoBuf.Expression.ConstantValue.FALSE;
            }
            if (Intrinsics.areEqual(constantReference, coneContractConstantValues.getNULL())) {
                return ProtoBuf.Expression.ConstantValue.NULL;
            }
            if (Intrinsics.areEqual(constantReference, coneContractConstantValues.getNOT_NULL())) {
                k2d.a("Internal error during serialization of function contract: NOT_NULL constant isn't denotable in protobuf format. Its serialization should be handled at higher level");
                return null;
            }
            if (Intrinsics.areEqual(constantReference, coneContractConstantValues.getWILDCARD())) {
                return null;
            }
            aca.a("Unknown constant: ", constantReference);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ProtoBuf.Expression.Builder contractExpressionProto(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> contractDescriptionElement, final FirContractDescription contractDescription) {
            return (ProtoBuf.Expression.Builder) contractDescriptionElement.accept(new KtContractDescriptionVisitor<ProtoBuf.Expression.Builder, Unit, ConeKotlinType, ConeDiagnostic>() { // from class: org.jetbrains.kotlin.fir.serialization.FirContractSerializer$ContractSerializerWorker$contractExpressionProto$1
                private final ProtoBuf.Expression.Builder visitLogicalAnd(KtBinaryLogicExpression<ConeKotlinType, ConeDiagnostic> logicalAnd, Unit data) {
                    ProtoBuf.Expression.Builder builder = (ProtoBuf.Expression.Builder) logicalAnd.getLeft().accept(this, data);
                    if (builder.getOrArgumentCount() == 0) {
                        builder.addAndArgument(this.this$0.contractExpressionProto(logicalAnd.getRight(), contractDescription));
                        return builder;
                    }
                    ProtoBuf.Expression.Builder builderNewBuilder = ProtoBuf.Expression.newBuilder();
                    FirContractSerializer.ContractSerializerWorker contractSerializerWorker = this.this$0;
                    FirContractDescription firContractDescription = contractDescription;
                    builderNewBuilder.addAndArgument(builder);
                    builderNewBuilder.addAndArgument(contractSerializerWorker.contractExpressionProto(logicalAnd.getRight(), firContractDescription));
                    return builderNewBuilder;
                }

                private final ProtoBuf.Expression.Builder visitLogicalOr(KtBinaryLogicExpression<ConeKotlinType, ConeDiagnostic> logicalOr, Unit data) {
                    ProtoBuf.Expression.Builder builder = (ProtoBuf.Expression.Builder) logicalOr.getLeft().accept(this, data);
                    if (builder.getAndArgumentCount() == 0) {
                        builder.addOrArgument(this.this$0.contractExpressionProto(logicalOr.getRight(), contractDescription));
                        return builder;
                    }
                    ProtoBuf.Expression.Builder builderNewBuilder = ProtoBuf.Expression.newBuilder();
                    FirContractSerializer.ContractSerializerWorker contractSerializerWorker = this.this$0;
                    FirContractDescription firContractDescription = contractDescription;
                    builderNewBuilder.addOrArgument(builder);
                    builderNewBuilder.addOrArgument(contractSerializerWorker.contractExpressionProto(logicalOr.getRight(), firContractDescription));
                    return builderNewBuilder;
                }

                @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
                public ProtoBuf.Expression.Builder visitConstantDescriptor(KtConstantReference<ConeKotlinType, ConeDiagnostic> constantReference, Unit data) {
                    constantReference.getClass();
                    data.getClass();
                    ProtoBuf.Expression.Builder builderNewBuilder = ProtoBuf.Expression.newBuilder();
                    ProtoBuf.Expression.ConstantValue constantValueConstantValueProtobufEnum = this.this$0.constantValueProtobufEnum(constantReference);
                    if (constantValueConstantValueProtobufEnum != null) {
                        builderNewBuilder.setConstantValue(constantValueConstantValueProtobufEnum);
                    }
                    builderNewBuilder.getClass();
                    return builderNewBuilder;
                }

                @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
                public ProtoBuf.Expression.Builder visitIsInstancePredicate(KtIsInstancePredicate<ConeKotlinType, ConeDiagnostic> isInstancePredicate, Unit data) {
                    isInstancePredicate.getClass();
                    data.getClass();
                    ProtoBuf.Expression.Builder builderVisitValueParameterReference = visitValueParameterReference(isInstancePredicate.getArg(), data);
                    builderVisitValueParameterReference.setIsInstanceTypeId(FirElementSerializer.typeId$default(this.this$0.parentSerializer, isInstancePredicate.getType(), false, false, 6, null));
                    this.this$0.writeFlags(builderVisitValueParameterReference, Flags.getContractExpressionFlags(isInstancePredicate.getIsNegated(), false));
                    return builderVisitValueParameterReference;
                }

                @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
                public ProtoBuf.Expression.Builder visitIsNullPredicate(KtIsNullPredicate<ConeKotlinType, ConeDiagnostic> isNullPredicate, Unit data) {
                    isNullPredicate.getClass();
                    data.getClass();
                    ProtoBuf.Expression.Builder builderVisitValueParameterReference = visitValueParameterReference(isNullPredicate.getArg(), data);
                    this.this$0.writeFlags(builderVisitValueParameterReference, Flags.getContractExpressionFlags(isNullPredicate.getIsNegated(), true));
                    return builderVisitValueParameterReference;
                }

                @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
                public ProtoBuf.Expression.Builder visitLogicalBinaryOperationContractExpression(KtBinaryLogicExpression<ConeKotlinType, ConeDiagnostic> binaryLogicExpression, Unit data) {
                    binaryLogicExpression.getClass();
                    data.getClass();
                    return binaryLogicExpression.getKind() == LogicOperationKind.AND ? visitLogicalAnd(binaryLogicExpression, data) : visitLogicalOr(binaryLogicExpression, data);
                }

                @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
                public ProtoBuf.Expression.Builder visitLogicalNot(KtLogicalNot<ConeKotlinType, ConeDiagnostic> logicalNot, Unit data) {
                    logicalNot.getClass();
                    data.getClass();
                    ProtoBuf.Expression.Builder builder = (ProtoBuf.Expression.Builder) logicalNot.getArg().accept(this, data);
                    this.this$0.writeFlags(builder, Flags.IS_NEGATED.invert(builder.getFlags()));
                    return builder;
                }

                @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
                public ProtoBuf.Expression.Builder visitValueParameterReference(KtValueParameterReference<ConeKotlinType, ConeDiagnostic> valueParameterReference, Unit data) {
                    valueParameterReference.getClass();
                    data.getClass();
                    ProtoBuf.Expression.Builder builderNewBuilder = ProtoBuf.Expression.newBuilder();
                    builderNewBuilder.setValueParameterReference(valueParameterReference.getParameterIndex() + 1);
                    return builderNewBuilder;
                }
            }, Unit.INSTANCE);
        }

        private final ProtoBuf.Effect.Builder effectProto(KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effectDeclaration, FirContractDescription contractDescription) {
            ProtoBuf.Effect.Builder builderNewBuilder = ProtoBuf.Effect.newBuilder();
            builderNewBuilder.getClass();
            fillEffectProto(builderNewBuilder, effectDeclaration, contractDescription);
            builderNewBuilder.getClass();
            return builderNewBuilder;
        }

        private final void fillEffectProto(ProtoBuf.Effect.Builder builder, KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effectDeclaration, FirContractDescription contractDescription) {
            if (effectDeclaration instanceof KtConditionalEffectDeclaration) {
                KtConditionalEffectDeclaration ktConditionalEffectDeclaration = (KtConditionalEffectDeclaration) effectDeclaration;
                builder.setConclusionOfConditionalEffect(contractExpressionProto(ktConditionalEffectDeclaration.getCondition(), contractDescription));
                fillEffectProto(builder, ktConditionalEffectDeclaration.getEffect(), contractDescription);
                Unit unit = Unit.INSTANCE;
                return;
            }
            if (effectDeclaration instanceof KtReturnsEffectDeclaration) {
                KtReturnsEffectDeclaration ktReturnsEffectDeclaration = (KtReturnsEffectDeclaration) effectDeclaration;
                KtConstantReference value = ktReturnsEffectDeclaration.getValue();
                ConeContractConstantValues coneContractConstantValues = ConeContractConstantValues.INSTANCE;
                if (Intrinsics.areEqual(value, coneContractConstantValues.getNOT_NULL())) {
                    builder.setEffectType(ProtoBuf.Effect.EffectType.RETURNS_NOT_NULL);
                    return;
                } else if (Intrinsics.areEqual(value, coneContractConstantValues.getWILDCARD())) {
                    builder.setEffectType(ProtoBuf.Effect.EffectType.RETURNS_CONSTANT);
                    return;
                } else {
                    builder.setEffectType(ProtoBuf.Effect.EffectType.RETURNS_CONSTANT);
                    builder.addEffectConstructorArgument(contractExpressionProto(ktReturnsEffectDeclaration.getValue(), contractDescription));
                    return;
                }
            }
            if (effectDeclaration instanceof KtCallsEffectDeclaration) {
                builder.setEffectType(ProtoBuf.Effect.EffectType.CALLS);
                KtCallsEffectDeclaration ktCallsEffectDeclaration = (KtCallsEffectDeclaration) effectDeclaration;
                builder.addEffectConstructorArgument(contractExpressionProto(ktCallsEffectDeclaration.getValueParameterReference(), contractDescription));
                ProtoBuf.Effect.InvocationKind invocationKindInvocationKindProtobufEnum = invocationKindProtobufEnum(ktCallsEffectDeclaration.getKind());
                if (invocationKindInvocationKindProtobufEnum != null) {
                    builder.setKind(invocationKindInvocationKindProtobufEnum);
                }
                Unit unit2 = Unit.INSTANCE;
                return;
            }
            if (effectDeclaration instanceof KtConditionalReturnsDeclaration) {
                builder.setConditionKind(ProtoBuf.Effect.EffectConditionKind.RETURNS_CONDITION);
                KtConditionalReturnsDeclaration ktConditionalReturnsDeclaration = (KtConditionalReturnsDeclaration) effectDeclaration;
                builder.setConclusionOfConditionalEffect(contractExpressionProto(ktConditionalReturnsDeclaration.getArgumentsCondition(), contractDescription));
                fillEffectProto(builder, ktConditionalReturnsDeclaration.getReturnsEffect(), contractDescription);
                Unit unit3 = Unit.INSTANCE;
                return;
            }
            if (effectDeclaration instanceof KtHoldsInEffectDeclaration) {
                builder.setConditionKind(ProtoBuf.Effect.EffectConditionKind.HOLDSIN_CONDITION);
                KtHoldsInEffectDeclaration ktHoldsInEffectDeclaration = (KtHoldsInEffectDeclaration) effectDeclaration;
                builder.setConclusionOfConditionalEffect(contractExpressionProto(ktHoldsInEffectDeclaration.getArgumentsCondition(), contractDescription));
                builder.addEffectConstructorArgument(contractExpressionProto(ktHoldsInEffectDeclaration.getValueParameterReference(), contractDescription));
                return;
            }
            if (!(effectDeclaration instanceof KtReturnsResultOfDeclaration)) {
                sle.a("Unsupported effect type: ", Reflection.getOrCreateKotlinClass(effectDeclaration.getClass()).getSimpleName());
            } else {
                builder.setEffectType(ProtoBuf.Effect.EffectType.RETURNS_RESULT_OF);
                builder.addEffectConstructorArgument(contractExpressionProto(((KtReturnsResultOfDeclaration) effectDeclaration).getValueParameterReference(), contractDescription));
            }
        }

        private final ProtoBuf.Effect.InvocationKind invocationKindProtobufEnum(EventOccurrencesRange kind) {
            int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
            if (i == 1) {
                return ProtoBuf.Effect.InvocationKind.AT_MOST_ONCE;
            }
            if (i == 2) {
                return ProtoBuf.Effect.InvocationKind.EXACTLY_ONCE;
            }
            if (i != 3) {
                return null;
            }
            return ProtoBuf.Effect.InvocationKind.AT_LEAST_ONCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void writeFlags(ProtoBuf.Expression.Builder builder, int i) {
            if (builder.getFlags() != i) {
                builder.setFlags(i);
            }
        }

        public final ProtoBuf.Contract.Builder contractProto(FirContractDescription contractDescription) {
            contractDescription.getClass();
            ProtoBuf.Contract.Builder builderNewBuilder = ProtoBuf.Contract.newBuilder();
            List<FirEffectDeclaration> effects = ContractUtilsKt.getEffects(contractDescription);
            if (effects != null) {
                Iterator<T> it = effects.iterator();
                while (it.hasNext()) {
                    builderNewBuilder.addEffect(effectProto(((FirEffectDeclaration) it.next()).getEffect(), contractDescription));
                }
            }
            builderNewBuilder.getClass();
            return builderNewBuilder;
        }
    }

    public final ProtoBuf.Contract.Builder buildAccessorContractProtoIfAny(FirPropertyAccessor accessor, FirElementSerializer parentSerializer) {
        List<FirEffectDeclaration> effects;
        accessor.getClass();
        parentSerializer.getClass();
        FirContractDescription contractDescription = accessor.getContractDescription();
        if (contractDescription == null || (effects = ContractUtilsKt.getEffects(contractDescription)) == null || effects.isEmpty()) {
            return null;
        }
        return new ContractSerializerWorker(parentSerializer).contractProto(contractDescription);
    }

    public final void serializeContractOfFunctionIfAny(FirFunction function, ProtoBuf.Function.Builder proto, FirElementSerializer parentSerializer) {
        List<FirEffectDeclaration> effects;
        function.getClass();
        proto.getClass();
        parentSerializer.getClass();
        FirNamedFunction firNamedFunction = function instanceof FirNamedFunction ? (FirNamedFunction) function : null;
        FirContractDescription contractDescription = firNamedFunction != null ? firNamedFunction.getContractDescription() : null;
        if (contractDescription == null || (effects = ContractUtilsKt.getEffects(contractDescription)) == null || effects.isEmpty()) {
            return;
        }
        proto.setContract(new ContractSerializerWorker(parentSerializer).contractProto(contractDescription));
    }
}
