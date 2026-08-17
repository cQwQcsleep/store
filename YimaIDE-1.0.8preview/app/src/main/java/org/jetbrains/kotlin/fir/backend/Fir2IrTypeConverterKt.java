package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.ir.types.IrErrorType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.impl.IrErrorTypeImpl;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.error.ErrorTypeKind;
import org.jetbrains.kotlin.types.error.ErrorUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006R\u00020\u0007j\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\t\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\n2\b\b\u0002\u0010\u0005\u001a\u00020\u0006R\u00020\u0007j\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\u000b\u001a\u001f\u0010\f\u001a\u0004\u0018\u00010\n*\u00020\nH\u0000R\u00020\u0007j\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u000e\u001a\u00020\n*\u00020\nH\u0000R\u00020\u0007j\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\r\u001a\u001c\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0000\u001a\u001b\u0010\u0015\u001a\u00020\n*\u00020\nR\u00020\u0007j\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\r¨\u0006\u0016"}, d2 = {"toIrType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "typeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;)Lorg/jetbrains/kotlin/ir/types/IrType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;)Lorg/jetbrains/kotlin/ir/types/IrType;", "approximateForIrOrNull", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "approximateForIrOrSelf", "createErrorType", "Lorg/jetbrains/kotlin/ir/types/IrErrorType;", "message", Argument.Delimiters.none, "isMarkedNullable", Argument.Delimiters.none, "approximateFunctionTypeInputs", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrTypeConverterKt {
    public static final ConeKotlinType approximateForIrOrNull(Fir2IrComponents fir2IrComponents, ConeKotlinType coneKotlinType) {
        fir2IrComponents.getClass();
        coneKotlinType.getClass();
        return TypeComponentsKt.getTypeApproximator(fir2IrComponents.getSession()).approximateToSuperType(coneKotlinType, TypeApproximatorConfiguration.FrontendToBackendTypesApproximation.INSTANCE);
    }

    public static final ConeKotlinType approximateForIrOrSelf(Fir2IrComponents fir2IrComponents, ConeKotlinType coneKotlinType) {
        fir2IrComponents.getClass();
        coneKotlinType.getClass();
        ConeKotlinType coneKotlinTypeApproximateForIrOrNull = approximateForIrOrNull(fir2IrComponents, coneKotlinType);
        return coneKotlinTypeApproximateForIrOrNull == null ? coneKotlinType : coneKotlinTypeApproximateForIrOrNull;
    }

    public static final ConeKotlinType approximateFunctionTypeInputs(Fir2IrComponents fir2IrComponents, ConeKotlinType coneKotlinType) {
        ConeKotlinType type;
        ConeKotlinType coneKotlinTypeApproximateForIrOrNull;
        ConeTypeProjection typeProjection;
        fir2IrComponents.getClass();
        coneKotlinType.getClass();
        if (!(coneKotlinType instanceof ConeClassLikeType)) {
            return coneKotlinType;
        }
        ConeTypeProjection[] typeArguments = coneKotlinType.getTypeArguments();
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneKotlinType;
        int length = typeArguments.length;
        ConeTypeProjection[] coneTypeProjectionArr = new ConeTypeProjection[length];
        for (int i = 0; i < length; i++) {
            ConeTypeProjection coneTypeProjection = typeArguments[i];
            if (i < ArraysKt.getLastIndex(typeArguments) && (type = ConeTypeProjectionKt.getType(coneTypeProjection)) != null && (coneKotlinTypeApproximateForIrOrNull = approximateForIrOrNull(fir2IrComponents, type)) != null && (typeProjection = ConeTypeUtilsKt.toTypeProjection(coneKotlinTypeApproximateForIrOrNull, coneTypeProjection.getKind())) != null) {
                coneTypeProjection = typeProjection;
            }
            coneTypeProjectionArr[i] = coneTypeProjection;
        }
        return ConeTypeUtilsKt.withArguments(coneClassLikeType, coneTypeProjectionArr);
    }

    public static final IrErrorType createErrorType(String str, boolean z) {
        str.getClass();
        return new IrErrorTypeImpl(ErrorUtils.createErrorType(ErrorTypeKind.UNRESOLVED_TYPE, new String[]{str}), CollectionsKt.emptyList(), Variance.INVARIANT, z);
    }

    public static /* synthetic */ IrErrorType createErrorType$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "<error>";
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return createErrorType(str, z);
    }

    public static final IrType toIrType(Fir2IrComponents fir2IrComponents, ConeKotlinType coneKotlinType, ConversionTypeOrigin conversionTypeOrigin) {
        fir2IrComponents.getClass();
        coneKotlinType.getClass();
        conversionTypeOrigin.getClass();
        return Fir2IrTypeConverter.toIrType$default(fir2IrComponents.getTypeConverter(), coneKotlinType, conversionTypeOrigin, null, false, false, false, false, 62, null);
    }

    public static /* synthetic */ IrType toIrType$default(FirTypeRef firTypeRef, Fir2IrTypeConverter fir2IrTypeConverter, ConversionTypeOrigin conversionTypeOrigin, int i, Object obj) {
        if ((i & 2) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        return toIrType(firTypeRef, fir2IrTypeConverter, conversionTypeOrigin);
    }

    public static /* synthetic */ IrType toIrType$default(Fir2IrComponents fir2IrComponents, FirTypeRef firTypeRef, ConversionTypeOrigin conversionTypeOrigin, int i, Object obj) {
        if ((i & 2) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        return toIrType(fir2IrComponents, firTypeRef, conversionTypeOrigin);
    }

    public static /* synthetic */ IrType toIrType$default(Fir2IrComponents fir2IrComponents, ConeKotlinType coneKotlinType, ConversionTypeOrigin conversionTypeOrigin, int i, Object obj) {
        if ((i & 2) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        return toIrType(fir2IrComponents, coneKotlinType, conversionTypeOrigin);
    }

    public static final IrType toIrType(Fir2IrComponents fir2IrComponents, FirTypeRef firTypeRef, ConversionTypeOrigin conversionTypeOrigin) {
        fir2IrComponents.getClass();
        firTypeRef.getClass();
        conversionTypeOrigin.getClass();
        return fir2IrComponents.getTypeConverter().toIrType(firTypeRef, conversionTypeOrigin);
    }

    public static final IrType toIrType(FirTypeRef firTypeRef, Fir2IrTypeConverter fir2IrTypeConverter, ConversionTypeOrigin conversionTypeOrigin) {
        firTypeRef.getClass();
        fir2IrTypeConverter.getClass();
        conversionTypeOrigin.getClass();
        return fir2IrTypeConverter.toIrType(firTypeRef, conversionTypeOrigin);
    }
}
