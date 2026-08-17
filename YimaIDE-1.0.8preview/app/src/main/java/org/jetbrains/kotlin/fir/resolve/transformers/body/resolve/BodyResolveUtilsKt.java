package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.KtSourceElementOffsetStrategy;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatusKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirSpreadArgumentExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirVarargArgumentsExpressionBuilder;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConePostponedInferenceDiagnostic;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralTypeImplKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001ar\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\n0\tj\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\n`\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00012\"\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n`\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012H\u0000\u001a\u0012\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u0016\u001a\u00020\u0001*\u00020\u00172\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u0018\u001a\u00020\u0014*\u00020\u00192\u0006\u0010\f\u001a\u00020\r\"*\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018À\u0002@À\u0002X\u0080\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u001a"}, d2 = {ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "resultType", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getResultType", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setResultType", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "remapArgumentsWithVararg", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "varargParameter", "varargArrayType", "argumentMapping", "argumentList", Argument.Delimiters.none, "writeResultType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "expectedConeType", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "replaceReturnTypeIfNotExhaustive", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BodyResolveUtilsKt {
    public static final ConeKotlinType expectedConeType(ConstantValueKind constantValueKind, FirSession firSession) {
        constantValueKind.getClass();
        firSession.getClass();
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Null.INSTANCE)) {
            return firSession.getBuiltinTypes().getNullableNothingType().getConeType();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Boolean.INSTANCE)) {
            return firSession.getBuiltinTypes().getBooleanType().getConeType();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Char.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getChar(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Byte.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getByte(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Short.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getShort(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Int.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getInt(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Long.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getLong(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.String.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getString(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Float.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getFloat(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Double.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getDouble(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedByte.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getUByte(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedShort.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getUShort(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedInt.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getUInt(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedLong.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getULong(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.IntegerLiteral.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getInt(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedIntegerLiteral.INSTANCE)) {
            return expectedConeType$constructLiteralType$default(firSession, StandardClassIds.INSTANCE.getUInt(), false, 4, null);
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Error.INSTANCE)) {
            k2d.a("Unexpected error ConstantValueKind");
            return null;
        }
        bu8.a();
        return null;
    }

    private static final ConeKotlinType expectedConeType$constructLiteralType(FirSession firSession, ClassId classId, boolean z) {
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(firSession).getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId != null) {
            return TypeConstructionUtilsKt.constructClassType$default(classLikeSymbolByClassId.getLookupTag(), null, false, null, 7, null);
        }
        return new ConeErrorType(new ConeSimpleDiagnostic("Missing stdlib class: " + classId, DiagnosticKind.MissingStdlibClass), false, null, null, null, null, null, 126, null);
    }

    public static /* synthetic */ ConeKotlinType expectedConeType$constructLiteralType$default(FirSession firSession, ClassId classId, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return expectedConeType$constructLiteralType(firSession, classId, z);
    }

    public static final ConeKotlinType getResultType(FirExpression firExpression) {
        firExpression.getClass();
        return FirTypeUtilsKt.getResolvedType(firExpression);
    }

    public static final LinkedHashMap<FirExpression, FirValueParameter> remapArgumentsWithVararg(FirSession firSession, FirValueParameter firValueParameter, ConeKotlinType coneKotlinType, LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap, List<? extends FirExpression> list) {
        FirExpression firExpressionMo288build;
        ConeKotlinType coneKotlinTypeApproximateIntegerLiteralType$default;
        firSession.getClass();
        firValueParameter.getClass();
        coneKotlinType.getClass();
        linkedHashMap.getClass();
        list.getClass();
        int i = 0;
        ConeKotlinType coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(coneKotlinType, false, 1, null);
        ConeKotlinType coneKotlinTypeRemoveAnnotations = (coneKotlinTypeArrayElementType$default == null || (coneKotlinTypeApproximateIntegerLiteralType$default = ConeIntegerLiteralTypeImplKt.approximateIntegerLiteralType$default(coneKotlinTypeArrayElementType$default, (ConeKotlinType) null, 1, (Object) null)) == null) ? null : TypeUtilsKt.removeAnnotations(coneKotlinTypeApproximateIntegerLiteralType$default);
        final ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
        ConeKotlinType coneKotlinTypeSubstituteOrSelf = new AbstractConeSubstitutor(typeContext) { // from class: org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveUtilsKt$remapArgumentsWithVararg$annotationsRemovingSubstitutor$1
            @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
            public ConeKotlinType substituteType(ConeKotlinType type) {
                ConeTypeProjection coneTypeProjection;
                ConeKotlinType type2;
                ConeKotlinType coneKotlinTypeRemoveAnnotations2;
                type.getClass();
                if (!(type instanceof ConeClassLikeType) || !ConeBuiltinTypeUtilsKt.isNonPrimitiveArray(type) || (coneTypeProjection = (ConeTypeProjection) ArraysKt.singleOrNull(type.getTypeArguments())) == null || (type2 = ConeTypeProjectionKt.getType(coneTypeProjection)) == null || (coneKotlinTypeRemoveAnnotations2 = TypeUtilsKt.removeAnnotations(type2)) == null) {
                    return null;
                }
                return ConeTypeUtilsKt.withArguments((ConeClassLikeType) type, new ConeTypeProjection[]{ConeTypeProjectionKt.replaceType(coneTypeProjection, coneKotlinTypeRemoveAnnotations2)});
            }
        }.substituteOrSelf(coneKotlinType);
        int size = list.size();
        LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap2 = new LinkedHashMap<>();
        FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder = new FirVarargArgumentsExpressionBuilder();
        firVarargArgumentsExpressionBuilder.setConeElementTypeOrNull(coneKotlinTypeRemoveAnnotations);
        firVarargArgumentsExpressionBuilder.setConeTypeOrNull(coneKotlinTypeSubstituteOrSelf);
        KtSourceElement source = null;
        KtSourceElement source2 = null;
        for (FirExpression firExpression : list) {
            int i2 = i + 1;
            FirValueParameter firValueParameter2 = linkedHashMap.get(firExpression);
            if (firValueParameter2 == null) {
                linkedHashMap2.put(firExpression, null);
            } else if (!Intrinsics.areEqual(firValueParameter2, firValueParameter) && (!firValueParameter2.getIsVararg() || (firExpression instanceof FirNamedArgumentExpression))) {
                if (!firVarargArgumentsExpressionBuilder.getArguments().isEmpty()) {
                    size = i;
                    break;
                }
                linkedHashMap2.put(firExpression, firValueParameter2);
            } else {
                List<FirExpression> arguments = firVarargArgumentsExpressionBuilder.getArguments();
                if (firExpression instanceof FirNamedArgumentExpression) {
                    FirSpreadArgumentExpressionBuilder firSpreadArgumentExpressionBuilder = new FirSpreadArgumentExpressionBuilder();
                    FirNamedArgumentExpression firNamedArgumentExpression = (FirNamedArgumentExpression) firExpression;
                    firSpreadArgumentExpressionBuilder.setSource(firNamedArgumentExpression.getSource());
                    firSpreadArgumentExpressionBuilder.setExpression(firNamedArgumentExpression.getExpression());
                    firSpreadArgumentExpressionBuilder.setNamed(true);
                    firSpreadArgumentExpressionBuilder.setFakeSpread(!firNamedArgumentExpression.getIsSpread());
                    firExpressionMo288build = firSpreadArgumentExpressionBuilder.mo288build();
                } else {
                    firExpressionMo288build = firExpression;
                }
                arguments.add(firExpressionMo288build);
                if (source2 == null) {
                    source2 = firExpression.getSource();
                }
                source = firExpression.getSource();
            }
            i = i2;
        }
        firVarargArgumentsExpressionBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement(source2, KtFakeSourceElementKind.VarargArgument.INSTANCE, (source2 == null || source == null) ? KtSourceElementOffsetStrategy.Default.INSTANCE : new KtSourceElementOffsetStrategy.Custom.Delegated(source2, source)) : null);
        linkedHashMap2.put(firVarargArgumentsExpressionBuilder.mo288build(), firValueParameter);
        int size2 = list.size();
        while (size < size2) {
            FirExpression firExpression2 = list.get(size);
            linkedHashMap2.put(firExpression2, linkedHashMap.get(firExpression2));
            size++;
        }
        return linkedHashMap2;
    }

    public static final void replaceReturnTypeIfNotExhaustive(FirWhenExpression firWhenExpression, FirSession firSession) {
        firWhenExpression.getClass();
        firSession.getClass();
        if (ExhaustivenessStatusKt.isProperlyExhaustive(firWhenExpression) || firWhenExpression.getUsedAsExpression()) {
            return;
        }
        firWhenExpression.replaceConeTypeOrNull(firSession.getBuiltinTypes().getUnitType().getConeType());
    }

    public static final void setResultType(FirExpression firExpression, ConeKotlinType coneKotlinType) {
        firExpression.getClass();
        coneKotlinType.getClass();
        firExpression.replaceConeTypeOrNull(coneKotlinType);
    }

    public static final void writeResultType(FirBlock firBlock, FirSession firSession) {
        ConeKotlinType coneTypeOrNull;
        firBlock.getClass();
        firSession.getClass();
        Object objLastOrNull = CollectionsKt.lastOrNull(firBlock.getStatements());
        FirExpression firExpression = objLastOrNull instanceof FirExpression ? (FirExpression) objLastOrNull : null;
        if (firExpression == null) {
            coneTypeOrNull = firSession.getBuiltinTypes().getUnitType().getConeType();
        } else {
            coneTypeOrNull = firExpression.getConeTypeOrNull();
            if (coneTypeOrNull == null) {
                coneTypeOrNull = new ConeErrorType(ConePostponedInferenceDiagnostic.INSTANCE, false, null, null, null, null, null, 126, null);
            }
        }
        firBlock.replaceConeTypeOrNull(coneTypeOrNull);
    }
}
