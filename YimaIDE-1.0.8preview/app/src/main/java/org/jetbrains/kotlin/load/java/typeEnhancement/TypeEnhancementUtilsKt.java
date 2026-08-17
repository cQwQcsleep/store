package org.jetbrains.kotlin.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aE\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0005\u001a\u0002H\u00012\b\u0010\u0006\u001a\u0004\u0018\u0001H\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\t\u001a&\u0010\u0000\u001a\u0004\u0018\u00010\n*\b\u0012\u0004\u0012\u00020\n0\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a0\u0010\u0013\u001a\u00020\f*\u00020\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b\u001a\u0014\u0010\u0018\u001a\u00020\b*\u00020\f2\u0006\u0010\u0019\u001a\u00020\bH\u0002\u001a\u0012\u0010\u001a\u001a\u00020\b*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d\"\u001a\u0010\u000b\u001a\u0004\u0018\u00010\n*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"select", "T", "", "", "low", "high", "own", "isCovariant", "", "(Ljava/util/Set;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Z)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifier;", "nullabilityForErrors", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/JavaTypeQualifiers;", "getNullabilityForErrors", "(Lorg/jetbrains/kotlin/load/java/typeEnhancement/JavaTypeQualifiers;)Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifier;", "mutabilityForErrors", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/MutabilityQualifier;", "getMutabilityForErrors", "(Lorg/jetbrains/kotlin/load/java/typeEnhancement/JavaTypeQualifiers;)Lorg/jetbrains/kotlin/load/java/typeEnhancement/MutabilityQualifier;", "computeQualifiersForOverride", "superQualifiers", "", "isForVarargParameter", "ignoreDeclarationNullabilityAnnotations", "isDefinitelyNotNullAndSameSeverity", "isForWarning", "hasEnhancedNullability", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "type", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class TypeEnhancementUtilsKt {
    public static final JavaTypeQualifiers computeQualifiersForOverride(JavaTypeQualifiers javaTypeQualifiers, Collection<JavaTypeQualifiers> collection, boolean z, boolean z2, boolean z3) {
        NullabilityQualifier nullabilityQualifierSelect;
        boolean z4;
        MutabilityQualifier mutabilityQualifier;
        javaTypeQualifiers.getClass();
        collection.getClass();
        Collection<JavaTypeQualifiers> collection2 = collection;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            NullabilityQualifier nullabilityForErrors = getNullabilityForErrors((JavaTypeQualifiers) it.next());
            if (nullabilityForErrors != null) {
                arrayList.add(nullabilityForErrors);
            }
        }
        NullabilityQualifier nullabilityQualifierSelect2 = select(CollectionsKt.toSet(arrayList), getNullabilityForErrors(javaTypeQualifiers), z);
        if (nullabilityQualifierSelect2 == null) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it2 = collection2.iterator();
            while (it2.hasNext()) {
                NullabilityQualifier nullability = ((JavaTypeQualifiers) it2.next()).getNullability();
                if (nullability != null) {
                    arrayList2.add(nullability);
                }
            }
            nullabilityQualifierSelect = select(CollectionsKt.toSet(arrayList2), javaTypeQualifiers.getNullability(), z);
        } else {
            nullabilityQualifierSelect = nullabilityQualifierSelect2;
        }
        NullabilityQualifier nullabilityQualifier = (nullabilityQualifierSelect == null || z3 || (z2 && nullabilityQualifierSelect == NullabilityQualifier.NULLABLE)) ? null : nullabilityQualifierSelect;
        boolean z5 = nullabilityQualifier != null && nullabilityQualifierSelect2 == null;
        if (nullabilityQualifier != NullabilityQualifier.NOT_NULL) {
            z4 = false;
        } else {
            if (!isDefinitelyNotNullAndSameSeverity(javaTypeQualifiers, z5)) {
                if (!collection2.isEmpty()) {
                    Iterator<T> it3 = collection2.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (isDefinitelyNotNullAndSameSeverity((JavaTypeQualifiers) it3.next(), z5)) {
                            }
                        }
                    }
                }
                z4 = false;
            }
            z4 = true;
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator<T> it4 = collection2.iterator();
        while (it4.hasNext()) {
            MutabilityQualifier mutabilityForErrors = getMutabilityForErrors((JavaTypeQualifiers) it4.next());
            if (mutabilityForErrors != null) {
                arrayList3.add(mutabilityForErrors);
            }
        }
        MutabilityQualifier mutabilityQualifier2 = (MutabilityQualifier) select(CollectionsKt.toSet(arrayList3), MutabilityQualifier.MUTABLE, MutabilityQualifier.READ_ONLY, getMutabilityForErrors(javaTypeQualifiers), z);
        if (mutabilityQualifier2 == null) {
            ArrayList arrayList4 = new ArrayList();
            Iterator<T> it5 = collection2.iterator();
            while (it5.hasNext()) {
                MutabilityQualifier mutability = ((JavaTypeQualifiers) it5.next()).getMutability();
                if (mutability != null) {
                    arrayList4.add(mutability);
                }
            }
            mutabilityQualifier = (MutabilityQualifier) select(CollectionsKt.toSet(arrayList4), MutabilityQualifier.MUTABLE, MutabilityQualifier.READ_ONLY, javaTypeQualifiers.getMutability(), z);
        } else {
            mutabilityQualifier = mutabilityQualifier2;
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, z4, z5, mutabilityQualifier != null && mutabilityQualifier2 == null);
    }

    private static final MutabilityQualifier getMutabilityForErrors(JavaTypeQualifiers javaTypeQualifiers) {
        if (javaTypeQualifiers.isMutabilityQualifierForWarning()) {
            return null;
        }
        return javaTypeQualifiers.getMutability();
    }

    private static final NullabilityQualifier getNullabilityForErrors(JavaTypeQualifiers javaTypeQualifiers) {
        if (javaTypeQualifiers.isNullabilityQualifierForWarning()) {
            return null;
        }
        return javaTypeQualifiers.getNullability();
    }

    public static final boolean hasEnhancedNullability(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        typeSystemCommonBackendContext.getClass();
        kotlinTypeMarker.getClass();
        FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        fqName.getClass();
        return typeSystemCommonBackendContext.hasAnnotation(kotlinTypeMarker, fqName);
    }

    private static final boolean isDefinitelyNotNullAndSameSeverity(JavaTypeQualifiers javaTypeQualifiers, boolean z) {
        return javaTypeQualifiers.isNullabilityQualifierForWarning() == z && javaTypeQualifiers.getDefinitelyNotNull();
    }

    private static final <T> T select(Set<? extends T> set, T t, T t2, T t3, boolean z) {
        Set<? extends T> set2;
        T t4;
        if (!z) {
            if (t3 != null && (set2 = CollectionsKt.toSet(SetsKt.plus(set, t3))) != null) {
                set = set2;
            }
            return (T) CollectionsKt.singleOrNull(set);
        }
        if (set.contains(t)) {
            t4 = t;
        } else {
            t4 = set.contains(t2) ? t2 : null;
        }
        if (Intrinsics.areEqual(t4, t) && Intrinsics.areEqual(t3, t2)) {
            return null;
        }
        return t3 == null ? t4 : t3;
    }

    private static final NullabilityQualifier select(Set<? extends NullabilityQualifier> set, NullabilityQualifier nullabilityQualifier, boolean z) {
        NullabilityQualifier nullabilityQualifier2 = NullabilityQualifier.FORCE_FLEXIBILITY;
        return nullabilityQualifier == nullabilityQualifier2 ? nullabilityQualifier2 : (NullabilityQualifier) select(set, NullabilityQualifier.NOT_NULL, NullabilityQualifier.NULLABLE, nullabilityQualifier, z);
    }
}
