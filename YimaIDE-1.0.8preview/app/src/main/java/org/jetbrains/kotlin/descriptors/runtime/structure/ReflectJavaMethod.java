package org.jetbrains.kotlin.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaMethod;
import org.jetbrains.kotlin.load.java.structure.JavaValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\rR\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaMethod;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaMember;", "Lorg/jetbrains/kotlin/load/java/structure/JavaMethod;", "member", "Ljava/lang/reflect/Method;", "<init>", "(Ljava/lang/reflect/Method;)V", "getMember", "()Ljava/lang/reflect/Method;", "valueParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaValueParameter;", "getValueParameters", "()Ljava/util/List;", "returnType", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaType;", "getReturnType", "()Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaType;", "annotationParameterDefaultValue", "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotationArgument;", "getAnnotationParameterDefaultValue", "()Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotationArgument;", "typeParameters", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaTypeParameter;", "getTypeParameters", "isNative", Argument.Delimiters.none, "()Z", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReflectJavaMethod extends ReflectJavaMember implements JavaMethod {
    private final Method member;

    public ReflectJavaMethod(Method method) {
        method.getClass();
        this.member = method;
    }

    public JavaAnnotationArgument getAnnotationParameterDefaultValue() {
        Object defaultValue = getMember().getDefaultValue();
        if (defaultValue != null) {
            return ReflectJavaAnnotationArgument.INSTANCE.create(defaultValue, null);
        }
        return null;
    }

    /* JADX INFO: renamed from: getReturnType, reason: merged with bridge method [inline-methods] */
    public ReflectJavaType m185getReturnType() {
        ReflectJavaType.Companion companion = ReflectJavaType.INSTANCE;
        Type genericReturnType = getMember().getGenericReturnType();
        genericReturnType.getClass();
        return companion.create(genericReturnType);
    }

    public List<ReflectJavaTypeParameter> getTypeParameters() {
        TypeVariable<Method>[] typeParameters = getMember().getTypeParameters();
        typeParameters.getClass();
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable<Method> typeVariable : typeParameters) {
            arrayList.add(new ReflectJavaTypeParameter(typeVariable));
        }
        return arrayList;
    }

    public List<JavaValueParameter> getValueParameters() {
        Type[] genericParameterTypes = getMember().getGenericParameterTypes();
        genericParameterTypes.getClass();
        Annotation[][] parameterAnnotations = getMember().getParameterAnnotations();
        parameterAnnotations.getClass();
        return getValueParameters(genericParameterTypes, parameterAnnotations, getMember().isVarArgs());
    }

    public boolean isNative() {
        return Modifier.isNative(getMember().getModifiers());
    }

    @Override // org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaMember
    public Method getMember() {
        return this.member;
    }
}
