package org.jetbrains.kotlin.load.kotlin;

import kotlin.Metadata;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"shouldUseUnderlyingType", "", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "inlineClassType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "org.jetbrains.kotlin:backend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class InlineClassMappingKt {
    public static final boolean shouldUseUnderlyingType(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        typeSystemCommonBackendContext.getClass();
        kotlinTypeMarker.getClass();
        SimpleTypeMarker unsubstitutedUnderlyingType = typeSystemCommonBackendContext.getUnsubstitutedUnderlyingType(kotlinTypeMarker);
        if (unsubstitutedUnderlyingType == null) {
            return false;
        }
        if (typeSystemCommonBackendContext.isMarkedNullable(kotlinTypeMarker)) {
            return (typeSystemCommonBackendContext.isNullableType(unsubstitutedUnderlyingType) || ((unsubstitutedUnderlyingType instanceof SimpleTypeMarker) && typeSystemCommonBackendContext.isPrimitiveType(unsubstitutedUnderlyingType))) ? false : true;
        }
        return true;
    }
}
