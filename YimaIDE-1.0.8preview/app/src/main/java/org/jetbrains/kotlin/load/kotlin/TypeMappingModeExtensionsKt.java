package org.jetbrains.kotlin.load.kotlin;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.types.ExpandedTypeUtilsKt;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u001c\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0007H\u0002¨\u0006\n"}, d2 = {"getOptimalModeForValueParameter", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "type", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "getOptimalModeForReturnType", "isAnnotationMethod", "", "getOptimalModeForSignaturePart", "canBeUsedInSupertypePosition", "org.jetbrains.kotlin:backend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class TypeMappingModeExtensionsKt {
    public static final TypeMappingMode getOptimalModeForReturnType(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker, boolean z) {
        typeSystemCommonBackendContext.getClass();
        kotlinTypeMarker.getClass();
        return z ? TypeMappingMode.VALUE_FOR_ANNOTATION : getOptimalModeForSignaturePart(typeSystemCommonBackendContext, kotlinTypeMarker, false);
    }

    private static final TypeMappingMode getOptimalModeForSignaturePart(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker, boolean z) {
        KotlinTypeMarker kotlinTypeMarkerComputeExpandedTypeForInlineClass;
        if (typeSystemCommonBackendContext.argumentsCount(kotlinTypeMarker) == 0) {
            return TypeMappingMode.DEFAULT;
        }
        boolean zIsInlineClass = typeSystemCommonBackendContext.isInlineClass(typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker));
        if (!zIsInlineClass || !InlineClassMappingKt.shouldUseUnderlyingType(typeSystemCommonBackendContext, kotlinTypeMarker) || (kotlinTypeMarkerComputeExpandedTypeForInlineClass = ExpandedTypeUtilsKt.computeExpandedTypeForInlineClass(typeSystemCommonBackendContext, kotlinTypeMarker)) == null || Intrinsics.areEqual(kotlinTypeMarkerComputeExpandedTypeForInlineClass, kotlinTypeMarker)) {
            return new TypeMappingMode(false, !zIsInlineClass, false, !z, true, null, false, !z ? new TypeMappingMode(false, false, false, false, true, null, false, null, null, false, false, 2023, null) : null, z ? getOptimalModeForSignaturePart(typeSystemCommonBackendContext, kotlinTypeMarker, false) : null, false, false, 1637, null);
        }
        return getOptimalModeForSignaturePart(typeSystemCommonBackendContext, kotlinTypeMarkerComputeExpandedTypeForInlineClass, z).dontWrapInlineClassesMode();
    }

    public static final TypeMappingMode getOptimalModeForValueParameter(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        typeSystemCommonBackendContext.getClass();
        kotlinTypeMarker.getClass();
        return getOptimalModeForSignaturePart(typeSystemCommonBackendContext, kotlinTypeMarker, true);
    }
}
