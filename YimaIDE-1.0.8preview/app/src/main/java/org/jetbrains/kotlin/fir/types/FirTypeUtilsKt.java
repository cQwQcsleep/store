package org.jetbrains.kotlin.fir.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.symbols.SymbolInternals;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001a1\u0010\u0005\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0087\bb\u0002\b\u0007\u0082\u0002\n\n\b\b\u0002\u001a\u0004\u0010\u0000(\u0000¢\u0006\u0002\u0010\u0004\u001a\u001b\u0010=\u001a\u00020!*\u00020\u001b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0001\u001a\u001c\u0010C\u001a\u00020!*\u00020\u00032\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020!H\u0002\u001a\u0014\u0010L\u001a\u00020!*\u00020M2\u0006\u0010D\u001a\u00020EH\u0002\u001a\u0016\u0010P\u001a\b\u0012\u0004\u0012\u00020M0Q*\b\u0012\u0004\u0012\u00020M0Q\u001a\f\u0010R\u001a\u0004\u0018\u00010S*\u00020T\u001a\n\u0010U\u001a\u00020V*\u00020W\u001a\u0016\u0010X\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\b\b\u0002\u0010Y\u001a\u00020!\u001a\u0016\u0010Z\u001a\u0004\u0018\u00010V*\u00020\u00022\b\b\u0002\u0010Y\u001a\u00020!\"\u0015\u0010\b\u001a\u00020\u0002*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0004\"\u0017\u0010\n\u001a\u0004\u0018\u00010\u0002*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0004\"X\u0010\n\u001a\u0004\u0018\u00010\u0002*\u00020\u00068FX\u0087\u0004r6\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u001c\b\u0012\u0012\u0018\b\u000bB\u0014\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0006\b\u0016\u0012\u0002\b\f\u0012\n\b\u0017\u0012\u0006\b\n0\u00188\u0019¢\u0006\f\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000b\u0010\u000e\"\u001b\u0010\u001a\u001a\u00020\u0002*\u00020\u001b8F¢\u0006\f\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u001b\u0010 \u001a\u00020!*\u00020\u001b8F¢\u0006\f\u0012\u0004\b\"\u0010\u001d\u001a\u0004\b#\u0010$\"\"\u0010%\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b&\u0010'\u001a\u0004\b%\u0010(\"\"\u0010*\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b+\u0010'\u001a\u0004\b*\u0010(\"\"\u0010,\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b-\u0010'\u001a\u0004\b,\u0010(\"\"\u0010.\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b/\u0010'\u001a\u0004\b.\u0010(\"\"\u00100\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b1\u0010'\u001a\u0004\b0\u0010(\"\"\u00102\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b3\u0010'\u001a\u0004\b2\u0010(\"\"\u00104\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b5\u0010'\u001a\u0004\b4\u0010(\"\"\u00106\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b7\u0010'\u001a\u0004\b6\u0010(\"\"\u00108\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b9\u0010'\u001a\u0004\b8\u0010(\"\"\u0010:\u001a\u00020!*\u00020\u00038FX\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b;\u0010'\u001a\u0004\b:\u0010(\"\u0015\u0010<\u001a\u00020!*\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b<\u0010$\"\u001a\u0010?\u001a\u0004\u0018\u00010@*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bA\u0010B\"\u0015\u0010G\u001a\u00020H*\u00020I8F¢\u0006\u0006\u001a\u0004\bJ\u0010K\"\u0015\u0010N\u001a\u00020!*\u00020M8F¢\u0006\u0006\u001a\u0004\bN\u0010Oò\u0001\b\n\u00020\u0006\n\u00020>¨\u0006["}, d2 = {"coneTypeUnsafe", "T", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "coneTypeSafe", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/symbols/SymbolInternals;", "coneType", "getConeType", "coneTypeOrNull", "getConeTypeOrNull", "getConeTypeOrNull$annotations", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lkotlin/Deprecated;", "message", "This type ref already resolved, use `.coneType` instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "this.coneType", "imports", "level", "Lkotlin/DeprecationLevel;", "ERROR", "resolvedType", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getResolvedType$annotations", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getResolvedType", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "hasResolvedType", Argument.Delimiters.none, "getHasResolvedType$annotations", "getHasResolvedType", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Z", "isAny", "isAny$annotations", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)Z", "Lorg/jetbrains/kotlin/fir/types/UnexpandedTypeCheck;", "isNullableAny", "isNullableAny$annotations", "isNothing", "isNothing$annotations", "isNullableNothing", "isNullableNothing$annotations", "isUnit", "isUnit$annotations", "isBoolean", "isBoolean$annotations", "isInt", "isInt$annotations", "isString", "isString$annotations", "isEnum", "isEnum$annotations", "isArrayType", "isArrayType$annotations", "isNullLiteral", "isStableSmartcast", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "lookupTagBasedOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "getLookupTagBasedOrNull", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "isBuiltinType", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "isNullable", "parametersCount", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "getParametersCount", "(Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;)I", "isOfType", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "isExtensionFunctionAnnotationCall", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Z", "dropExtensionFunctionAnnotation", Argument.Delimiters.none, "toConstKind", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "toConeTypeProjection", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "arrayElementType", "checkUnsignedArrays", "arrayElementTypeArgument", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeUtilsKt {
    public static final ConeKotlinType arrayElementType(ConeKotlinType coneKotlinType, boolean z) {
        coneKotlinType.getClass();
        ConeKotlinTypeProjection coneKotlinTypeProjectionArrayElementTypeArgument = arrayElementTypeArgument(coneKotlinType, z);
        if (coneKotlinTypeProjectionArrayElementTypeArgument instanceof ConeKotlinTypeProjection) {
            return coneKotlinTypeProjectionArrayElementTypeArgument.getType();
        }
        return null;
    }

    public static /* synthetic */ ConeKotlinType arrayElementType$default(ConeKotlinType coneKotlinType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return arrayElementType(coneKotlinType, z);
    }

    public static final ConeTypeProjection arrayElementTypeArgument(ConeKotlinType coneKotlinType, boolean z) {
        ClassId classId;
        coneKotlinType.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        if (!(coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType)) {
            return null;
        }
        ClassId classId2 = ((ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible).getLookupTag().getClassId();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(classId2, standardClassIds.getArray())) {
            return (ConeTypeProjection) ArraysKt.first(coneRigidTypeLowerBoundIfFlexible.getTypeArguments());
        }
        ClassId classId3 = (ClassId) standardClassIds.getElementTypeByPrimitiveArrayType().get(classId2);
        if (classId3 != null) {
            classId = classId3;
        } else if (z) {
            classId3 = (ClassId) standardClassIds.getElementTypeByUnsignedArrayType().get(classId2);
            classId = classId3;
        } else {
            classId = null;
        }
        if (classId != null) {
            return TypeConstructionUtilsKt.constructClassLikeType$default(classId, null, false, null, 7, null);
        }
        return null;
    }

    public static /* synthetic */ ConeTypeProjection arrayElementTypeArgument$default(ConeKotlinType coneKotlinType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return arrayElementTypeArgument(coneKotlinType, z);
    }

    @SymbolInternals
    public static final /* synthetic */ <T extends ConeKotlinType> T coneTypeSafe(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        T t = null;
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        if (firResolvedTypeRef != null) {
            t = (T) firResolvedTypeRef.getConeType();
        }
        Intrinsics.reifiedOperationMarker(2, "T");
        return t;
    }

    public static final /* synthetic */ <T extends ConeKotlinType> T coneTypeUnsafe(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        T t = (T) ((FirResolvedTypeRef) firTypeRef).getConeType();
        Intrinsics.reifiedOperationMarker(1, "T");
        return t;
    }

    public static final List<FirAnnotation> dropExtensionFunctionAnnotation(List<? extends FirAnnotation> list) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!isExtensionFunctionAnnotationCall((FirAnnotation) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final ConeKotlinType getConeType(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (coneType == null) {
            coneType = null;
        }
        if (coneType != null) {
            return coneType;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Expected " + Reflection.getOrCreateKotlinClass(FirResolvedTypeRef.class).getSimpleName() + " with " + Reflection.getOrCreateKotlinClass(ConeKotlinType.class).getSimpleName() + " but was " + Reflection.getOrCreateKotlinClass(firTypeRef.getClass()).getSimpleName(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "typeRef", firTypeRef);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static final ConeKotlinType getConeTypeOrNull(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (coneType == null) {
            return null;
        }
        return coneType;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This type ref already resolved, use `.coneType` instead", replaceWith = @ReplaceWith(expression = "this.coneType", imports = {}))
    public static /* synthetic */ void getConeTypeOrNull$annotations(FirResolvedTypeRef firResolvedTypeRef) {
    }

    public static final boolean getHasResolvedType(FirExpression firExpression) {
        firExpression.getClass();
        return firExpression.getConeTypeOrNull() != null;
    }

    public static /* synthetic */ void getHasResolvedType$annotations(FirExpression firExpression) {
    }

    private static final ConeLookupTagBasedType getLookupTagBasedOrNull(FirTypeRef firTypeRef) {
        if (firTypeRef instanceof FirImplicitBuiltinTypeRef) {
            return ((FirImplicitBuiltinTypeRef) firTypeRef).getConeType();
        }
        if (firTypeRef instanceof FirResolvedTypeRef) {
            ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(((FirResolvedTypeRef) firTypeRef).getConeType());
            if (coneRigidTypeLowerBoundIfFlexible instanceof ConeLookupTagBasedType) {
                return (ConeLookupTagBasedType) coneRigidTypeLowerBoundIfFlexible;
            }
        }
        return null;
    }

    public static final int getParametersCount(FirFunctionTypeRef firFunctionTypeRef) {
        firFunctionTypeRef.getClass();
        return firFunctionTypeRef.getReceiverTypeRef() != null ? firFunctionTypeRef.getParameters().size() + firFunctionTypeRef.getContextParameterTypeRefs().size() + 1 : firFunctionTypeRef.getParameters().size() + firFunctionTypeRef.getContextParameterTypeRefs().size();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final ConeKotlinType getResolvedType(FirExpression firExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        firExpression.getClass();
        ConeKotlinType coneTypeOrNull = firExpression.getConeTypeOrNull();
        if (coneTypeOrNull != null) {
            return coneTypeOrNull;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Expected expression '" + Reflection.getOrCreateKotlinClass(firExpression.getClass()).getSimpleName() + "' to be resolved", (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "expression", firExpression);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static /* synthetic */ void getResolvedType$annotations(FirExpression firExpression) {
    }

    public static final boolean isAny(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return isBuiltinType(firTypeRef, StandardClassIds.INSTANCE.getAny(), false);
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isAny$annotations(FirTypeRef firTypeRef) {
    }

    public static final boolean isArrayType(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (isBuiltinType(firTypeRef, standardClassIds.getArray(), false)) {
            return true;
        }
        Collection collectionValues = standardClassIds.getPrimitiveArrayTypeByElementType().values();
        if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
            Iterator it = collectionValues.iterator();
            while (it.hasNext()) {
                if (isBuiltinType(firTypeRef, (ClassId) it.next(), false)) {
                    return true;
                }
            }
        }
        Collection collectionValues2 = StandardClassIds.INSTANCE.getUnsignedArrayTypeByElementType().values();
        if (!(collectionValues2 instanceof Collection) || !collectionValues2.isEmpty()) {
            Iterator it2 = collectionValues2.iterator();
            while (it2.hasNext()) {
                if (isBuiltinType(firTypeRef, (ClassId) it2.next(), false)) {
                    return true;
                }
            }
        }
        return false;
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isArrayType$annotations(FirTypeRef firTypeRef) {
    }

    public static final boolean isBoolean(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return isBuiltinType(firTypeRef, StandardClassIds.INSTANCE.getBoolean(), false);
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isBoolean$annotations(FirTypeRef firTypeRef) {
    }

    private static final boolean isBuiltinType(FirTypeRef firTypeRef, ClassId classId, boolean z) {
        ConeLookupTagBasedType lookupTagBasedOrNull = getLookupTagBasedOrNull(firTypeRef);
        if (lookupTagBasedOrNull == null) {
            return false;
        }
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(lookupTagBasedOrNull);
        return Intrinsics.areEqual(classLikeLookupTagIfAny != null ? classLikeLookupTagIfAny.getClassId() : null, classId) && lookupTagBasedOrNull.getIsMarkedNullable() == z;
    }

    public static final boolean isEnum(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return isBuiltinType(firTypeRef, StandardClassIds.INSTANCE.getEnum(), false);
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isEnum$annotations(FirTypeRef firTypeRef) {
    }

    public static final boolean isExtensionFunctionAnnotationCall(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        return isOfType(firAnnotation, StandardClassIds$Annotations.INSTANCE.getExtensionFunctionType());
    }

    public static final boolean isInt(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return isBuiltinType(firTypeRef, StandardClassIds.INSTANCE.getInt(), false);
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isInt$annotations(FirTypeRef firTypeRef) {
    }

    public static final boolean isNothing(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return isBuiltinType(firTypeRef, StandardClassIds.INSTANCE.getNothing(), false);
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isNothing$annotations(FirTypeRef firTypeRef) {
    }

    public static final boolean isNullLiteral(FirExpression firExpression) {
        firExpression.getClass();
        if (!(firExpression instanceof FirLiteralExpression)) {
            return false;
        }
        FirLiteralExpression firLiteralExpression = (FirLiteralExpression) firExpression;
        return Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.Null.INSTANCE) && firLiteralExpression.getValue() == null && firLiteralExpression.getSource() != null;
    }

    public static final boolean isNullableAny(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return isBuiltinType(firTypeRef, StandardClassIds.INSTANCE.getAny(), true);
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isNullableAny$annotations(FirTypeRef firTypeRef) {
    }

    public static final boolean isNullableNothing(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return isBuiltinType(firTypeRef, StandardClassIds.INSTANCE.getNothing(), true);
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isNullableNothing$annotations(FirTypeRef firTypeRef) {
    }

    private static final boolean isOfType(FirAnnotation firAnnotation, ClassId classId) {
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        if (firResolvedTypeRef == null) {
            return false;
        }
        ConeKotlinType coneType = firResolvedTypeRef.getConeType();
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        return Intrinsics.areEqual(coneClassLikeType != null ? Boolean.valueOf(Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), classId)) : null, Boolean.TRUE);
    }

    public static final boolean isStableSmartcast(FirExpression firExpression) {
        firExpression.getClass();
        return (firExpression instanceof FirSmartCastExpression) && ((FirSmartCastExpression) firExpression).isStable();
    }

    public static final boolean isString(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return isBuiltinType(firTypeRef, StandardClassIds.INSTANCE.getString(), false);
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isString$annotations(FirTypeRef firTypeRef) {
    }

    public static final boolean isUnit(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return isBuiltinType(firTypeRef, StandardClassIds.INSTANCE.getUnit(), false);
    }

    @UnexpandedTypeCheck
    public static /* synthetic */ void isUnit$annotations(FirTypeRef firTypeRef) {
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final ConeTypeProjection toConeTypeProjection(FirTypeProjection firTypeProjection) throws KotlinIllegalArgumentExceptionWithAttachments {
        firTypeProjection.getClass();
        if (firTypeProjection instanceof FirStarProjection) {
            return ConeStarProjection.INSTANCE;
        }
        if (firTypeProjection instanceof FirTypeProjectionWithVariance) {
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = (FirTypeProjectionWithVariance) firTypeProjection;
            return ConeTypeUtilsKt.toTypeProjection(getConeType(firTypeProjectionWithVariance.getTypeRef()), firTypeProjectionWithVariance.getVariance());
        }
        if (!(firTypeProjection instanceof FirPlaceholderProjection)) {
            bu8.a();
            return null;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Placeholder projection cannot be mapped. Placeholders are replaced during analysis. If the containing element was already analyzed and a placeholder is still present, it indicates a bug.", (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "projection", firTypeProjection);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static final ConstantValueKind toConstKind(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        ClassId classId = coneClassLikeType.getLookupTag().getClassId();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(classId, standardClassIds.getByte())) {
            return ConstantValueKind.Byte.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getShort())) {
            return ConstantValueKind.Short.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getInt())) {
            return ConstantValueKind.Int.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getLong())) {
            return ConstantValueKind.Long.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getUInt())) {
            return ConstantValueKind.UnsignedInt.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getULong())) {
            return ConstantValueKind.UnsignedLong.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getUShort())) {
            return ConstantValueKind.UnsignedShort.INSTANCE;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getUByte())) {
            return ConstantValueKind.UnsignedByte.INSTANCE;
        }
        return null;
    }

    public static final ConeKotlinType getConeTypeOrNull(FirResolvedTypeRef firResolvedTypeRef) {
        firResolvedTypeRef.getClass();
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (coneType == null) {
            return null;
        }
        return coneType;
    }
}
