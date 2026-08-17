package org.jetbrains.kotlin.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.load.kotlin.TypeMappingMode;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b\u001a\u0019\u0010\t\u001a\u0004\u0018\u00010\u0007*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"updateArgumentModeFromAnnotations", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "type", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "typeSystem", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "suppressWildcardsByContainingDeclaration", "", "(Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;Ljava/lang/Boolean;)Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "suppressWildcardsMode", "(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;)Ljava/lang/Boolean;", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class TypeMappingUtil {
    public static final Boolean suppressWildcardsMode(KotlinTypeMarker kotlinTypeMarker, TypeSystemCommonBackendContext typeSystemCommonBackendContext) {
        kotlinTypeMarker.getClass();
        typeSystemCommonBackendContext.getClass();
        JvmStandardClassIds jvmStandardClassIds = JvmStandardClassIds.INSTANCE;
        if (!typeSystemCommonBackendContext.hasAnnotation(kotlinTypeMarker, jvmStandardClassIds.getJVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME())) {
            return null;
        }
        Object annotationFirstArgumentValue = typeSystemCommonBackendContext.getAnnotationFirstArgumentValue(kotlinTypeMarker, jvmStandardClassIds.getJVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME());
        Boolean bool = annotationFirstArgumentValue instanceof Boolean ? (Boolean) annotationFirstArgumentValue : null;
        return Boolean.valueOf(bool != null ? bool.booleanValue() : true);
    }

    public static final TypeMappingMode updateArgumentModeFromAnnotations(TypeMappingMode typeMappingMode, KotlinTypeMarker kotlinTypeMarker, TypeSystemCommonBackendContext typeSystemCommonBackendContext, Boolean bool) {
        typeMappingMode.getClass();
        kotlinTypeMarker.getClass();
        typeSystemCommonBackendContext.getClass();
        Boolean boolSuppressWildcardsMode = suppressWildcardsMode(kotlinTypeMarker, typeSystemCommonBackendContext);
        if (boolSuppressWildcardsMode != null) {
            return TypeMappingMode.Companion.createWithConstantDeclarationSiteWildcardsMode$default(TypeMappingMode.INSTANCE, boolSuppressWildcardsMode.booleanValue(), typeMappingMode.getIsForAnnotationParameter(), typeMappingMode.getNeedInlineClassWrapping(), typeMappingMode.getMapTypeAliases(), typeMappingMode.getIgnoreTypeArgumentsBounds(), null, 32, null);
        }
        if (typeSystemCommonBackendContext.hasAnnotation(kotlinTypeMarker, JvmStandardClassIds.INSTANCE.getJVM_WILDCARD_ANNOTATION_FQ_NAME())) {
            return TypeMappingMode.INSTANCE.createWithConstantDeclarationSiteWildcardsMode(false, typeMappingMode.getIsForAnnotationParameter(), typeMappingMode.getNeedInlineClassWrapping(), typeMappingMode.getMapTypeAliases(), typeMappingMode.getIgnoreTypeArgumentsBounds(), typeMappingMode);
        }
        if (bool == null) {
            return typeMappingMode;
        }
        return TypeMappingMode.Companion.createWithConstantDeclarationSiteWildcardsMode$default(TypeMappingMode.INSTANCE, bool.booleanValue(), typeMappingMode.getIsForAnnotationParameter(), typeMappingMode.getNeedInlineClassWrapping(), typeMappingMode.getMapTypeAliases(), typeMappingMode.getIgnoreTypeArgumentsBounds(), null, 32, null);
    }

    public static /* synthetic */ TypeMappingMode updateArgumentModeFromAnnotations$default(TypeMappingMode typeMappingMode, KotlinTypeMarker kotlinTypeMarker, TypeSystemCommonBackendContext typeSystemCommonBackendContext, Boolean bool, int i, Object obj) {
        if ((i & 4) != 0) {
            bool = null;
        }
        return updateArgumentModeFromAnnotations(typeMappingMode, kotlinTypeMarker, typeSystemCommonBackendContext, bool);
    }
}
