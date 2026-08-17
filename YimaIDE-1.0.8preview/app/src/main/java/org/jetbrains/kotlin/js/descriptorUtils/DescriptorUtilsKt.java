package org.jetbrains.kotlin.js.descriptorUtils;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Function;
import defpackage.ywd;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.Named;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.js.descriptorUtils.DescriptorUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\"\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0007b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u001a\u0012\u0010\f\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\r"}, d2 = {"nameIfStandardType", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/types/KotlinType;", "getNameIfStandardType", "(Lorg/jetbrains/kotlin/types/KotlinType;)Lorg/jetbrains/kotlin/name/Name;", "getJetTypeFqName", "", "printTypeArguments", "", "Lkotlin/Deprecated;", "message", "Use getKotlinTypeFqName(Boolean) instead", "getKotlinTypeFqName", "org.jetbrains.kotlin:js.frontend"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class DescriptorUtilsKt {
    public static String a(Function1 function1, Object obj) {
        return (String) function1.invoke(obj);
    }

    public static String b(TypeProjection typeProjection) {
        KotlinType type = typeProjection.getType();
        type.getClass();
        return getKotlinTypeFqName(type, false);
    }

    public static String c(boolean z, KotlinType kotlinType) {
        kotlinType.getClass();
        return getKotlinTypeFqName(kotlinType, z);
    }

    public static String d(Function1 function1, Object obj) {
        return (String) function1.invoke(obj);
    }

    @Deprecated(message = "Use getKotlinTypeFqName(Boolean) instead")
    public static final String getJetTypeFqName(KotlinType kotlinType, boolean z) {
        kotlinType.getClass();
        return getKotlinTypeFqName(kotlinType, z);
    }

    public static final String getKotlinTypeFqName(KotlinType kotlinType, final boolean z) {
        String str;
        kotlinType.getClass();
        TypeParameterDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            StringBuilder sb = new StringBuilder("declarationDescriptor is null for constructor = ");
            sb.append(kotlinType.getConstructor());
            ywd.a(sb, " with ", kotlinType.getConstructor().getClass());
            return null;
        }
        if (declarationDescriptor instanceof TypeParameterDescriptor) {
            List upperBounds = declarationDescriptor.getUpperBounds();
            final Function1 function1 = new Function1() { // from class: zn3
                public final Object invoke(Object obj) {
                    return DescriptorUtilsKt.c(z, (KotlinType) obj);
                }
            };
            String strJoin = StringUtil.join(upperBounds, new Function() { // from class: co3
                public final Object fun(Object obj) {
                    return DescriptorUtilsKt.d(function1, obj);
                }
            }, "&");
            strJoin.getClass();
            return strJoin;
        }
        List arguments = kotlinType.getArguments();
        if (!z || arguments.isEmpty()) {
            str = "";
        } else {
            final Function1 function2 = new Function1() { // from class: eo3
                public final Object invoke(Object obj) {
                    return DescriptorUtilsKt.b((TypeProjection) obj);
                }
            };
            String strJoin2 = StringUtil.join(arguments, new Function() { // from class: go3
                public final Object fun(Object obj) {
                    return DescriptorUtilsKt.a(function2, obj);
                }
            }, ", ");
            strJoin2.getClass();
            str = "<" + strJoin2 + '>';
        }
        return DescriptorUtils.getFqName((DeclarationDescriptor) declarationDescriptor).getFqName() + str;
    }

    public static final Name getNameIfStandardType(KotlinType kotlinType) {
        kotlinType.getClass();
        Named declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor != null) {
            if (!KotlinBuiltIns.isBuiltIn((DeclarationDescriptor) declarationDescriptor)) {
                declarationDescriptor = null;
            }
            if (declarationDescriptor != null) {
                return declarationDescriptor.getName();
            }
        }
        return null;
    }
}
