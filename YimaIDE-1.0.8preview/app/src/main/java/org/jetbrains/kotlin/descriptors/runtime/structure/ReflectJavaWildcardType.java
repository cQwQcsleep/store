package org.jetbrains.kotlin.descriptors.runtime.structure;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.java.structure.JavaWildcardType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaWildcardType;", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaType;", "Lorg/jetbrains/kotlin/load/java/structure/JavaWildcardType;", "reflectType", "Ljava/lang/reflect/WildcardType;", "<init>", "(Ljava/lang/reflect/WildcardType;)V", "getReflectType", "()Ljava/lang/reflect/WildcardType;", "bound", "getBound", "()Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaType;", "isExtends", Argument.Delimiters.none, "()Z", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotation;", "getAnnotations", "()Ljava/util/Collection;", "isDeprecatedInJavaDoc", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReflectJavaWildcardType extends ReflectJavaType implements JavaWildcardType {
    private final Collection<JavaAnnotation> annotations;
    private final boolean isDeprecatedInJavaDoc;
    private final WildcardType reflectType;

    public ReflectJavaWildcardType(WildcardType wildcardType) {
        wildcardType.getClass();
        this.reflectType = wildcardType;
        this.annotations = CollectionsKt.emptyList();
    }

    public Collection<JavaAnnotation> getAnnotations() {
        return this.annotations;
    }

    /* JADX INFO: renamed from: getBound, reason: merged with bridge method [inline-methods] */
    public ReflectJavaType m190getBound() {
        Type[] upperBounds = getReflectType().getUpperBounds();
        Type[] lowerBounds = getReflectType().getLowerBounds();
        if (upperBounds.length > 1 || lowerBounds.length > 1) {
            o1c.a("Wildcard types with many bounds are not yet supported: ", getReflectType());
            return null;
        }
        if (lowerBounds.length == 1) {
            ReflectJavaType.Companion companion = ReflectJavaType.INSTANCE;
            Object objSingle = ArraysKt.single(lowerBounds);
            objSingle.getClass();
            return companion.create((Type) objSingle);
        }
        if (upperBounds.length == 1) {
            Type type = (Type) ArraysKt.single(upperBounds);
            if (!Intrinsics.areEqual(type, Object.class)) {
                ReflectJavaType.Companion companion2 = ReflectJavaType.INSTANCE;
                type.getClass();
                return companion2.create(type);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: isDeprecatedInJavaDoc, reason: from getter */
    public boolean getIsDeprecatedInJavaDoc() {
        return this.isDeprecatedInJavaDoc;
    }

    public boolean isExtends() {
        Type[] upperBounds = getReflectType().getUpperBounds();
        upperBounds.getClass();
        return !Intrinsics.areEqual(ArraysKt.firstOrNull(upperBounds), Object.class);
    }

    @Override // org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaType
    public WildcardType getReflectType() {
        return this.reflectType;
    }
}
