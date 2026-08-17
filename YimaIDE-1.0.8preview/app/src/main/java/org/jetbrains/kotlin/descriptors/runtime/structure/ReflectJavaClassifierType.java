package org.jetbrains.kotlin.descriptors.runtime.structure;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.java.structure.JavaClassifier;
import org.jetbrains.kotlin.load.java.structure.JavaClassifierType;
import org.jetbrains.kotlin.load.java.structure.JavaType;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010 \u001a\u0004\u0018\u00010\u001d2\u0006\u0010!\u001a\u00020\"H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010#\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0015¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaClassifierType;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaType;", "Lorg/jetbrains/kotlin/load/java/structure/JavaClassifierType;", "reflectType", "Ljava/lang/reflect/Type;", "<init>", "(Ljava/lang/reflect/Type;)V", "getReflectType", "()Ljava/lang/reflect/Type;", "classifier", "Lorg/jetbrains/kotlin/load/java/structure/JavaClassifier;", "getClassifier", "()Lorg/jetbrains/kotlin/load/java/structure/JavaClassifier;", "classifierQualifiedName", Argument.Delimiters.none, "getClassifierQualifiedName", "()Ljava/lang/String;", "presentableText", "getPresentableText", "isRaw", Argument.Delimiters.none, "()Z", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaType;", "getTypeArguments", "()Ljava/util/List;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotation;", "getAnnotations", "()Ljava/util/Collection;", "findAnnotation", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "isDeprecatedInJavaDoc", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReflectJavaClassifierType extends ReflectJavaType implements JavaClassifierType {
    private final JavaClassifier classifier;
    private final Type reflectType;

    public ReflectJavaClassifierType(Type type) {
        JavaClassifier reflectJavaClass;
        type.getClass();
        this.reflectType = type;
        Type reflectType = getReflectType();
        if (reflectType instanceof Class) {
            reflectJavaClass = new ReflectJavaClass((Class) reflectType);
        } else if (reflectType instanceof TypeVariable) {
            reflectJavaClass = new ReflectJavaTypeParameter((TypeVariable) reflectType);
        } else {
            if (!(reflectType instanceof ParameterizedType)) {
                co4.a("Not a classifier type (", reflectType.getClass(), "): ", reflectType);
                throw null;
            }
            Type rawType = ((ParameterizedType) reflectType).getRawType();
            rawType.getClass();
            reflectJavaClass = new ReflectJavaClass((Class) rawType);
        }
        this.classifier = reflectJavaClass;
    }

    public JavaAnnotation findAnnotation(FqName fqName) {
        fqName.getClass();
        return null;
    }

    public Collection<JavaAnnotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    public JavaClassifier getClassifier() {
        return this.classifier;
    }

    public String getClassifierQualifiedName() {
        throw new UnsupportedOperationException("Type not found: " + getReflectType());
    }

    public String getPresentableText() {
        return getReflectType().toString();
    }

    @Override // org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaType
    public Type getReflectType() {
        return this.reflectType;
    }

    public List<JavaType> getTypeArguments() {
        List parameterizedTypeArguments = ReflectClassUtilKt.getParameterizedTypeArguments(getReflectType());
        ReflectJavaType.Companion companion = ReflectJavaType.INSTANCE;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameterizedTypeArguments, 10));
        Iterator it = parameterizedTypeArguments.iterator();
        while (it.hasNext()) {
            arrayList.add(companion.create((Type) it.next()));
        }
        return arrayList;
    }

    public boolean isDeprecatedInJavaDoc() {
        return false;
    }

    public boolean isRaw() {
        Type reflectType = getReflectType();
        if (reflectType instanceof Class) {
            TypeVariable[] typeParameters = ((Class) reflectType).getTypeParameters();
            typeParameters.getClass();
            if (!(typeParameters.length == 0)) {
                return true;
            }
        }
        return false;
    }
}
