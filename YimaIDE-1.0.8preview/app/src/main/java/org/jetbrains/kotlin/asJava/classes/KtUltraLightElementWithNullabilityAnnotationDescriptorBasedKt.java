package org.jetbrains.kotlin.asJava.classes;

import com.intellij.psi.PsiPrimitiveType;
import com.intellij.psi.PsiType;
import kotlin.Metadata;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.KotlinTypeKt;
import org.jetbrains.kotlin.types.TypeUtils;
import org.jetbrains.kotlin.types.typeUtil.TypeNullability;
import org.jetbrains.kotlin.types.typeUtil.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0006"}, d2 = {"computeNullabilityQualifier", "", "kotlinType", "Lorg/jetbrains/kotlin/types/KotlinType;", "psiType", "Lcom/intellij/psi/PsiType;", "org.jetbrains.kotlin:light-classes"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class KtUltraLightElementWithNullabilityAnnotationDescriptorBasedKt {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TypeNullability.values().length];
            try {
                iArr[TypeNullability.NOT_NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TypeNullability.NULLABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TypeNullability.FLEXIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String computeNullabilityQualifier(KotlinType kotlinType, PsiType psiType) {
        if (psiType != null && !(psiType instanceof PsiPrimitiveType) && kotlinType != null) {
            if (KotlinTypeKt.isError(kotlinType)) {
                kotlinType = null;
            }
            if (kotlinType != null) {
                if (TypeUtilsKt.isTypeParameter(kotlinType)) {
                    if (!TypeUtils.hasNullableSuperType(kotlinType)) {
                        return JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION.asString();
                    }
                    if (!kotlinType.isMarkedNullable()) {
                        return null;
                    }
                }
                int i = WhenMappings.$EnumSwitchMapping$0[TypeUtilsKt.nullability(kotlinType).ordinal()];
                if (i == 1) {
                    return JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION.asString();
                }
                if (i == 2) {
                    return JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION.asString();
                }
                if (i == 3) {
                    return null;
                }
                bu8.a();
                return null;
            }
        }
        return null;
    }
}
