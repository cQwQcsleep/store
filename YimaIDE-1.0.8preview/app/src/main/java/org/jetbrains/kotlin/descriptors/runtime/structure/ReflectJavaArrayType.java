package org.jetbrains.kotlin.descriptors.runtime.structure;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.java.structure.JavaArrayType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaArrayType;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaType;", "Lorg/jetbrains/kotlin/load/java/structure/JavaArrayType;", "reflectType", "Ljava/lang/reflect/Type;", "<init>", "(Ljava/lang/reflect/Type;)V", "getReflectType", "()Ljava/lang/reflect/Type;", "componentType", "getComponentType", "()Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaType;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotation;", "getAnnotations", "()Ljava/util/Collection;", "isDeprecatedInJavaDoc", Argument.Delimiters.none, "()Z", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReflectJavaArrayType extends ReflectJavaType implements JavaArrayType {
    private final Collection<JavaAnnotation> annotations;
    private final ReflectJavaType componentType;
    private final boolean isDeprecatedInJavaDoc;
    private final Type reflectType;

    public ReflectJavaArrayType(Type type) {
        ReflectJavaType reflectJavaTypeCreate;
        type.getClass();
        this.reflectType = type;
        Type reflectType = getReflectType();
        if (!(reflectType instanceof GenericArrayType)) {
            if (reflectType instanceof Class) {
                Class cls = (Class) reflectType;
                if (cls.isArray()) {
                    ReflectJavaType.Companion companion = ReflectJavaType.INSTANCE;
                    Class<?> componentType = cls.getComponentType();
                    componentType.getClass();
                    reflectJavaTypeCreate = companion.create(componentType);
                }
            }
            StringBuilder sb = new StringBuilder("Not an array type (");
            sb.append(getReflectType().getClass());
            qk5.a(sb, "): ", getReflectType());
            throw null;
        }
        ReflectJavaType.Companion companion2 = ReflectJavaType.INSTANCE;
        Type genericComponentType = ((GenericArrayType) reflectType).getGenericComponentType();
        genericComponentType.getClass();
        reflectJavaTypeCreate = companion2.create(genericComponentType);
        this.componentType = reflectJavaTypeCreate;
        this.annotations = CollectionsKt.emptyList();
    }

    public Collection<JavaAnnotation> getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaType
    public Type getReflectType() {
        return this.reflectType;
    }

    /* JADX INFO: renamed from: isDeprecatedInJavaDoc, reason: from getter */
    public boolean getIsDeprecatedInJavaDoc() {
        return this.isDeprecatedInJavaDoc;
    }

    /* JADX INFO: renamed from: getComponentType, reason: from getter and merged with bridge method [inline-methods] */
    public ReflectJavaType m176getComponentType() {
        return this.componentType;
    }
}
