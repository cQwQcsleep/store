package org.jetbrains.kotlin.fir.expressions;

import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.constants.evaluate.CompileTimeType;
import org.jetbrains.kotlin.resolve.constants.evaluate.OperationsMapGeneratedKt;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\"\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0006H\u0002\u001a\u0016\u0010\f\u001a\u00020\r*\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u0006H\u0002\u001a\u001e\u0010\f\u001a\u0004\u0018\u00010\u000f*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\u001a\u000e\u0010\u0018\u001a\u0004\u0018\u00010\u0002*\u00020\u0011H\u0002\u001a\f\u0010\u0018\u001a\u0004\u0018\u00010\u0002*\u00020\u0019\u001a\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\f\u0010\u001c\u001a\u00020\u0013*\u00020\u0001H\u0002\u001a\u001e\u0010\u001d\u001a\u00020\u000f*\u0004\u0018\u00010\u00042\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u001a\u0014\u0010 \u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u001a\u000e\u0010!\u001a\u00020\r*\u0004\u0018\u00010\"H\u0002\"\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014\"\u0018\u0010\u0015\u001a\u00020\u0013*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0014\"\u0018\u0010\u0016\u001a\u00020\u0013*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014\"\u0018\u0010\u0017\u001a\u00020\u0013*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014¨\u0006#"}, d2 = {"toCompileTimeType", "Lorg/jetbrains/kotlin/resolve/constants/evaluate/CompileTimeType;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "evaluateUnary", Argument.Delimiters.none, "arg", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "evaluateBinary", "arg1", "arg2", "adjustTypeAndConvertToLiteral", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "original", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isStringLength", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/name/CallableId;)Z", "isEquals", "isStringPlus", "isCharCode", "toConstantValueKind", "Lorg/jetbrains/kotlin/name/ClassId;", "convertToGivenKind", "value", "isFloatingPoint", "toConstExpression", "kind", "originalExpression", "copy", "wrap", "Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpressionEvaluatorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FirEvaluatorResult adjustTypeAndConvertToLiteral(Object obj, FirExpression firExpression) {
        FirEvaluatorResult firEvaluatorResultWrap;
        if (obj == null) {
            return FirEvaluatorResult.NotEvaluated.INSTANCE;
        }
        if (obj instanceof FirEvaluatorResult) {
            return (FirEvaluatorResult) obj;
        }
        FirLiteralExpression firLiteralExpressionAdjustTypeAndConvertToLiteral = adjustTypeAndConvertToLiteral(obj, firExpression, FirTypeUtilsKt.getResolvedType(firExpression));
        return (firLiteralExpressionAdjustTypeAndConvertToLiteral == null || (firEvaluatorResultWrap = wrap(firLiteralExpressionAdjustTypeAndConvertToLiteral)) == null) ? FirEvaluatorResult.NotEvaluated.INSTANCE : firEvaluatorResultWrap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object convertToGivenKind(ConstantValueKind constantValueKind, Object obj) {
        if (obj == null) {
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Boolean.INSTANCE)) {
            if (obj instanceof Boolean) {
                return (Boolean) obj;
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Char.INSTANCE)) {
            if (obj instanceof Character) {
                return (Character) obj;
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.String.INSTANCE)) {
            if (obj instanceof String) {
                return (String) obj;
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Byte.INSTANCE)) {
            Number number = obj instanceof Number ? (Number) obj : null;
            if (number != null) {
                return Byte.valueOf(number.byteValue());
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Double.INSTANCE)) {
            Number number2 = obj instanceof Number ? (Number) obj : null;
            if (number2 != null) {
                return Double.valueOf(number2.doubleValue());
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Float.INSTANCE)) {
            Number number3 = obj instanceof Number ? (Number) obj : null;
            if (number3 != null) {
                return Float.valueOf(number3.floatValue());
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Int.INSTANCE)) {
            Number number4 = obj instanceof Number ? (Number) obj : null;
            if (number4 != null) {
                return Integer.valueOf(number4.intValue());
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Long.INSTANCE)) {
            Number number5 = obj instanceof Number ? (Number) obj : null;
            if (number5 != null) {
                return Long.valueOf(number5.longValue());
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Short.INSTANCE)) {
            Number number6 = obj instanceof Number ? (Number) obj : null;
            if (number6 != null) {
                return Short.valueOf(number6.shortValue());
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedByte.INSTANCE)) {
            if (obj instanceof UByte) {
                return (UByte) obj;
            }
            Number number7 = obj instanceof Number ? (Number) obj : null;
            if (number7 != null) {
                return UByte.box-impl(UByte.constructor-impl((byte) number7.longValue()));
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedShort.INSTANCE)) {
            if (obj instanceof UShort) {
                return (UShort) obj;
            }
            Number number8 = obj instanceof Number ? (Number) obj : null;
            if (number8 != null) {
                return UShort.box-impl(UShort.constructor-impl((short) number8.longValue()));
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedInt.INSTANCE)) {
            if (obj instanceof UInt) {
                return (UInt) obj;
            }
            Number number9 = obj instanceof Number ? (Number) obj : null;
            if (number9 != null) {
                return UInt.box-impl(UInt.constructor-impl((int) number9.longValue()));
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedLong.INSTANCE)) {
            if (obj instanceof ULong) {
                return (ULong) obj;
            }
            Number number10 = obj instanceof Number ? (Number) obj : null;
            if (number10 != null) {
                return ULong.box-impl(ULong.constructor-impl(number10.longValue()));
            }
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedIntegerLiteral.INSTANCE)) {
            if (obj instanceof UInt) {
                return ULong.box-impl(ULong.constructor-impl(((long) ((UInt) obj).unbox-impl()) & 4294967295L));
            }
            if (obj instanceof ULong) {
                return (ULong) obj;
            }
            Number number11 = obj instanceof Number ? (Number) obj : null;
            if (number11 != null) {
                return ULong.box-impl(ULong.constructor-impl(number11.longValue()));
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirLiteralExpression copy(FirLiteralExpression firLiteralExpression, FirExpression firExpression) {
        return toConstExpression(firLiteralExpression.getValue(), firLiteralExpression.getKind(), firExpression);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object evaluateBinary(FirExpression firExpression, CallableId callableId, FirExpression firExpression2) {
        Object objConvertToGivenKind;
        if (firExpression instanceof FirLiteralExpression) {
            FirLiteralExpression firLiteralExpression = (FirLiteralExpression) firExpression;
            if (firLiteralExpression.getValue() != null && (firExpression2 instanceof FirLiteralExpression)) {
                FirLiteralExpression firLiteralExpression2 = (FirLiteralExpression) firExpression2;
                if (firLiteralExpression2.getValue() != null) {
                    CompileTimeType compileTimeType = (isEquals(callableId) || isStringPlus(callableId)) ? CompileTimeType.ANY : toCompileTimeType(firLiteralExpression2.getKind());
                    CompileTimeType compileTimeType2 = toCompileTimeType(firLiteralExpression.getKind());
                    Object objConvertToGivenKind2 = convertToGivenKind(firLiteralExpression.getKind(), firLiteralExpression.getValue());
                    if (objConvertToGivenKind2 == null || (objConvertToGivenKind = convertToGivenKind(firLiteralExpression2.getKind(), firLiteralExpression2.getValue())) == null) {
                        return null;
                    }
                    String strAsString = callableId.getCallableName().asString();
                    strAsString.getClass();
                    if ((Intrinsics.areEqual(strAsString, "div") || Intrinsics.areEqual(strAsString, "rem")) && !isFloatingPoint(compileTimeType2) && !isFloatingPoint(compileTimeType)) {
                        Number number = objConvertToGivenKind instanceof Number ? (Number) objConvertToGivenKind : null;
                        if (number != null && number.intValue() == 0) {
                            return FirEvaluatorResult.DivisionByZero.INSTANCE;
                        }
                    }
                    if (Intrinsics.areEqual(strAsString, "trimMargin")) {
                        String str = objConvertToGivenKind instanceof String ? (String) objConvertToGivenKind : null;
                        if (str != null && StringsKt.isBlank(str)) {
                            return FirEvaluatorResult.TrimMarginBlankPrefix.INSTANCE;
                        }
                    }
                    return OperationsMapGeneratedKt.evalBinaryOp(strAsString, toCompileTimeType(firLiteralExpression.getKind()), objConvertToGivenKind2, compileTimeType, objConvertToGivenKind);
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object evaluateUnary(FirExpression firExpression, CallableId callableId) {
        Object objConvertToGivenKind;
        if (firExpression instanceof FirLiteralExpression) {
            FirLiteralExpression firLiteralExpression = (FirLiteralExpression) firExpression;
            if (firLiteralExpression.getValue() == null || (objConvertToGivenKind = convertToGivenKind(firLiteralExpression.getKind(), firLiteralExpression.getValue())) == null) {
                return null;
            }
            String strAsString = callableId.getCallableName().asString();
            strAsString.getClass();
            return OperationsMapGeneratedKt.evalUnaryOp(strAsString, toCompileTimeType(firLiteralExpression.getKind()), objConvertToGivenKind);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isCharCode(CallableId callableId) {
        return Intrinsics.areEqual(callableId.getPackageName(), StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE()) && callableId.getClassId() == null && Intrinsics.areEqual(callableId.getCallableName().getIdentifierOrNullIfSpecial(), "code");
    }

    private static final boolean isEquals(CallableId callableId) {
        return Intrinsics.areEqual(callableId.getCallableName(), OperatorNameConventions.EQUALS);
    }

    private static final boolean isFloatingPoint(CompileTimeType compileTimeType) {
        return compileTimeType == CompileTimeType.FLOAT || compileTimeType == CompileTimeType.DOUBLE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isStringLength(CallableId callableId) {
        return Intrinsics.areEqual(callableId.getClassId(), StandardClassIds.INSTANCE.getString()) && Intrinsics.areEqual(callableId.getCallableName().getIdentifierOrNullIfSpecial(), "length");
    }

    private static final boolean isStringPlus(CallableId callableId) {
        return Intrinsics.areEqual(callableId.getClassId(), StandardClassIds.INSTANCE.getString()) && Intrinsics.areEqual(callableId.getCallableName(), OperatorNameConventions.PLUS);
    }

    public static final CompileTimeType toCompileTimeType(ConstantValueKind constantValueKind) {
        constantValueKind.getClass();
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Byte.INSTANCE)) {
            return CompileTimeType.BYTE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Short.INSTANCE)) {
            return CompileTimeType.SHORT;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Int.INSTANCE)) {
            return CompileTimeType.INT;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Long.INSTANCE)) {
            return CompileTimeType.LONG;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedByte.INSTANCE)) {
            return CompileTimeType.UBYTE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedShort.INSTANCE)) {
            return CompileTimeType.USHORT;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedInt.INSTANCE)) {
            return CompileTimeType.UINT;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedLong.INSTANCE)) {
            return CompileTimeType.ULONG;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Double.INSTANCE)) {
            return CompileTimeType.DOUBLE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Float.INSTANCE)) {
            return CompileTimeType.FLOAT;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Char.INSTANCE)) {
            return CompileTimeType.CHAR;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Boolean.INSTANCE)) {
            return CompileTimeType.BOOLEAN;
        }
        return Intrinsics.areEqual(constantValueKind, ConstantValueKind.String.INSTANCE) ? CompileTimeType.STRING : CompileTimeType.ANY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirLiteralExpression toConstExpression(Object obj, ConstantValueKind constantValueKind, FirExpression firExpression) {
        if (obj instanceof UByte) {
            obj = Byte.valueOf(((UByte) obj).unbox-impl());
        } else if (obj instanceof UShort) {
            obj = Short.valueOf(((UShort) obj).unbox-impl());
        } else if (obj instanceof UInt) {
            obj = Integer.valueOf(((UInt) obj).unbox-impl());
        } else if (obj instanceof ULong) {
            obj = Long.valueOf(((ULong) obj).unbox-impl());
        }
        Object obj2 = obj;
        KtSourceElement source = firExpression.getSource();
        List<FirAnnotation> annotations = firExpression.getAnnotations();
        if (annotations.isEmpty()) {
            annotations = null;
        }
        FirLiteralExpression firLiteralExpressionBuildLiteralExpression$default = FirConstExpressionBuilderKt.buildLiteralExpression$default(source, constantValueKind, obj2, annotations != null ? CollectionsKt.toMutableList(annotations) : null, false, null, 32, null);
        firLiteralExpressionBuildLiteralExpression$default.replaceConeTypeOrNull(FirTypeUtilsKt.getResolvedType(firExpression));
        return firLiteralExpressionBuildLiteralExpression$default;
    }

    public static final ConstantValueKind toConstantValueKind(ClassId classId) {
        classId.getClass();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(classId, standardClassIds.getByte())) {
            return ConstantValueKind.Byte.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getDouble())) {
            return ConstantValueKind.Double.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getFloat())) {
            return ConstantValueKind.Float.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getInt())) {
            return ConstantValueKind.Int.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getLong())) {
            return ConstantValueKind.Long.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getShort())) {
            return ConstantValueKind.Short.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getChar())) {
            return ConstantValueKind.Char.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getString())) {
            return ConstantValueKind.String.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getBoolean())) {
            return ConstantValueKind.Boolean.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getUByte())) {
            return ConstantValueKind.UnsignedByte.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getUShort())) {
            return ConstantValueKind.UnsignedShort.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getUInt())) {
            return ConstantValueKind.UnsignedInt.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getULong())) {
            return ConstantValueKind.UnsignedLong.INSTANCE;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirEvaluatorResult wrap(FirElement firElement) {
        return firElement != null ? new FirEvaluatorResult.Evaluated(firElement) : FirEvaluatorResult.NotEvaluated.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirLiteralExpression adjustTypeAndConvertToLiteral(Object obj, FirExpression firExpression, ConeKotlinType coneKotlinType) {
        Object objConvertToGivenKind;
        ConstantValueKind constantValueKind = toConstantValueKind(coneKotlinType);
        if (constantValueKind == null || (objConvertToGivenKind = convertToGivenKind(constantValueKind, obj)) == null) {
            return null;
        }
        return toConstExpression(objConvertToGivenKind, constantValueKind, firExpression);
    }

    private static final ConstantValueKind toConstantValueKind(ConeKotlinType coneKotlinType) {
        ConstantValueKind constantValueKind;
        ClassId classId;
        if (coneKotlinType instanceof ConeErrorType) {
            return null;
        }
        if (coneKotlinType instanceof ConeLookupTagBasedType) {
            ConeClassifierLookupTag lookupTag = ((ConeLookupTagBasedType) coneKotlinType).getLookupTag();
            ConeClassLikeLookupTag coneClassLikeLookupTag = lookupTag instanceof ConeClassLikeLookupTag ? (ConeClassLikeLookupTag) lookupTag : null;
            if (coneClassLikeLookupTag == null || (classId = coneClassLikeLookupTag.getClassId()) == null) {
                return null;
            }
            return toConstantValueKind(classId);
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return toConstantValueKind(((ConeFlexibleType) coneKotlinType).getUpperBound());
        }
        if (coneKotlinType instanceof ConeCapturedType) {
            ConeCapturedType coneCapturedType = (ConeCapturedType) coneKotlinType;
            ConeKotlinType lowerType = coneCapturedType.getConstructor().getLowerType();
            if (lowerType != null && (constantValueKind = toConstantValueKind(lowerType)) != null) {
                return constantValueKind;
            }
            List<ConeKotlinType> supertypes = coneCapturedType.getConstructor().getSupertypes();
            supertypes.getClass();
            return toConstantValueKind((ConeKotlinType) CollectionsKt.first(supertypes));
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return toConstantValueKind(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            return toConstantValueKind((ConeKotlinType) CollectionsKt.first(((ConeIntersectionType) coneKotlinType).getIntersectedTypes()));
        }
        if (!(coneKotlinType instanceof ConeStubType) && !(coneKotlinType instanceof ConeIntegerLiteralType) && !(coneKotlinType instanceof ConeTypeVariableType)) {
            bu8.a();
        }
        return null;
    }
}
