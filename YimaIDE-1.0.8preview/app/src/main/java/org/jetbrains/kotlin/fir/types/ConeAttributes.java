package org.jetbrains.kotlin.fir.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.util.ConeTypeRegistry;
import org.jetbrains.kotlin.util.ArrayMap;
import org.jetbrains.kotlin.util.AttributeArrayOwner;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 &2\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0003:\u0001&B\u001b\b\u0002\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0015\b\u0012\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0002¢\u0006\u0004\b\u0006\u0010\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000J\u0012\u0010\r\u001a\u00020\u00002\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0002J\u0015\u0010\u000e\u001a\u00020\u000f2\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0086\u0002J\u001d\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00020\u0011H\u0086\u0002J,\u0010\u0012\u001a\u0004\u0018\u0001H\u0013\"\f\b\u0000\u0010\u0013*\u0006\u0012\u0002\b\u00030\u00022\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0011H\u0086\u0002¢\u0006\u0002\u0010\u0014J\u0012\u0010\u0015\u001a\u00020\u00002\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0002J\u001e\u0010\u0015\u001a\u00020\u00002\u0016\u0010\u0016\u001a\u0012\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00020\u0011j\u0002`\u0017J\u0006\u0010\u0018\u001a\u00020\u0000J@\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002-\u0010\u001a\u001a)\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u001b¢\u0006\u0002\b\u001cH\u0082\bJ\u0011\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u0000H\u0086\u0004J$\u0010\u001e\u001a\u0004\u0018\u00010\u00002\u0014\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0006\u0012\u0004\u0018\u00010!0 H\u0086\bø\u0001\u0000R(\u0010\"\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020#8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "Lorg/jetbrains/kotlin/util/AttributeArrayOwner;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", Argument.Delimiters.none, "attributes", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "attribute", "(Lorg/jetbrains/kotlin/fir/types/ConeAttribute;)V", "union", "other", "intersect", "add", "contains", Argument.Delimiters.none, "attributeKey", "Lkotlin/reflect/KClass;", "get", "T", "(Lkotlin/reflect/KClass;)Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "remove", "key", "Lorg/jetbrains/kotlin/fir/types/ConeAttributeKey;", "filterNecessaryToKeep", "perform", "op", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "definitelyDifferFrom", "transformTypesWith", "transform", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeRegistry", "Lorg/jetbrains/kotlin/util/TypeRegistry;", "getTypeRegistry", "()Lorg/jetbrains/kotlin/util/TypeRegistry;", "Companion", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeAttributes extends AttributeArrayOwner<ConeAttribute<?>, ConeAttribute<?>> implements Iterable<ConeAttribute<?>>, KMappedMarker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ConeAttributes Empty = new ConeAttributes(CollectionsKt.emptyList());
    private static final ConeAttributes WithExtensionFunctionType = new ConeAttributes(CollectionsKt.listOf(CompilerConeAttributes.ExtensionFunctionType.INSTANCE));

    private ConeAttributes(List<? extends ConeAttribute<?>> list) {
        for (ConeAttribute<?> coneAttribute : list) {
            registerComponent(coneAttribute.getKey(), coneAttribute);
        }
    }

    public final ConeAttributes add(ConeAttributes other) {
        ConeAttribute coneAttributeAdd;
        other.getClass();
        if (isEmpty() && other.isEmpty()) {
            return this;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = INSTANCE.getIndices().iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            ConeAttribute coneAttribute = (ConeAttribute) getArrayMap().get(iIntValue);
            ConeAttribute coneAttribute2 = (ConeAttribute) other.getArrayMap().get(iIntValue);
            if (coneAttribute == null) {
                coneAttributeAdd = coneAttribute2 != null ? coneAttribute2.add(coneAttribute) : null;
            } else {
                coneAttributeAdd = coneAttribute.add(coneAttribute2);
            }
            org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(arrayList, coneAttributeAdd);
        }
        return INSTANCE.create(arrayList);
    }

    public final boolean contains(KClass<? extends ConeAttribute<?>> attributeKey) {
        attributeKey.getClass();
        return get(attributeKey) != null;
    }

    public final boolean definitelyDifferFrom(ConeAttributes other) {
        other.getClass();
        if (this == other) {
            return false;
        }
        if (isEmpty() && other.isEmpty()) {
            return false;
        }
        Iterator it = INSTANCE.getIndices().iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            ConeAttribute coneAttribute = (ConeAttribute) getArrayMap().get(iIntValue);
            ConeAttribute coneAttribute2 = (ConeAttribute) other.getArrayMap().get(iIntValue);
            if (coneAttribute != null || coneAttribute2 != null) {
                ConeAttribute coneAttribute3 = coneAttribute == null ? coneAttribute2 : coneAttribute;
                coneAttribute3.getClass();
                if (coneAttribute3.getImplementsEquality()) {
                    if ((coneAttribute == null) != (coneAttribute2 == null) || !Intrinsics.areEqual(coneAttribute, coneAttribute2)) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ConeAttributes filterNecessaryToKeep() {
        if ((this instanceof Collection) && ((Collection) this).isEmpty()) {
            return this;
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            if (!((ConeAttribute) it.next()).getKeepInInferredDeclarationType()) {
                Companion companion = INSTANCE;
                ArrayList arrayList = new ArrayList();
                for (Object obj : this) {
                    if (((ConeAttribute) obj).getKeepInInferredDeclarationType()) {
                        arrayList.add(obj);
                    }
                }
                return companion.create(arrayList);
            }
        }
        return this;
    }

    public final <T extends ConeAttribute<?>> T get(KClass<T> attributeKey) {
        attributeKey.getClass();
        return (T) getArrayMap().get(INSTANCE.getId(attributeKey));
    }

    public TypeRegistry<ConeAttribute<?>, ConeAttribute<?>> getTypeRegistry() {
        return INSTANCE;
    }

    public final ConeAttributes intersect(ConeAttributes other) {
        ConeAttribute coneAttributeIntersect;
        other.getClass();
        if (isEmpty() && other.isEmpty()) {
            return this;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = INSTANCE.getIndices().iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            ConeAttribute coneAttribute = (ConeAttribute) getArrayMap().get(iIntValue);
            ConeAttribute coneAttribute2 = (ConeAttribute) other.getArrayMap().get(iIntValue);
            if (coneAttribute == null) {
                coneAttributeIntersect = coneAttribute2 != null ? coneAttribute2.intersect(coneAttribute) : null;
            } else {
                coneAttributeIntersect = coneAttribute.intersect(coneAttribute2);
            }
            org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(arrayList, coneAttributeIntersect);
        }
        return INSTANCE.create(arrayList);
    }

    public final ConeAttributes remove(KClass<? extends ConeAttribute<?>> key) {
        key.getClass();
        if (!isEmpty()) {
            ArrayMap arrayMap = getArrayMap();
            ArrayList arrayList = new ArrayList();
            for (Object obj : arrayMap) {
                if (!Intrinsics.areEqual(((ConeAttribute) obj).getKey(), key)) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.size() != getArrayMap().getSize()) {
                return INSTANCE.create(arrayList);
            }
        }
        return this;
    }

    public final ConeAttributes transformTypesWith(Function1<? super ConeKotlinType, ? extends ConeKotlinType> transform) {
        ConeKotlinType coneType;
        transform.getClass();
        if (isEmpty()) {
            return null;
        }
        List<? extends ConeAttribute<?>> mutableList = null;
        int i = 0;
        boolean z = false;
        for (ConeAttribute<?> coneAttribute : this) {
            int i2 = i + 1;
            if (coneAttribute instanceof ConeAttributeWithConeType) {
                ConeAttributeWithConeType coneAttributeWithConeTypeCopyWith = (ConeAttributeWithConeType) coneAttribute;
                ConeKotlinType coneKotlinType = (ConeKotlinType) transform.invoke(coneAttributeWithConeTypeCopyWith.getConeType());
                if (coneKotlinType == null) {
                    coneAttributeWithConeTypeCopyWith = null;
                } else if (!Intrinsics.areEqual(coneKotlinType, coneAttributeWithConeTypeCopyWith.getConeType())) {
                    ConeAttributeWithConeType coneAttributeWithConeType = (ConeAttributeWithConeType) coneKotlinType.getAttributes().get(coneAttributeWithConeTypeCopyWith.getKey());
                    if (coneAttributeWithConeType != null && (coneType = coneAttributeWithConeType.getConeType()) != null) {
                        coneKotlinType = coneType;
                    }
                    coneAttributeWithConeTypeCopyWith = coneAttributeWithConeTypeCopyWith.copyWith(coneKotlinType);
                }
                if (coneAttributeWithConeTypeCopyWith != null) {
                    if (mutableList == null) {
                        mutableList = CollectionsKt.toMutableList(this);
                    }
                    mutableList.set(i, coneAttributeWithConeTypeCopyWith);
                    z = z || !Intrinsics.areEqual(coneAttributeWithConeTypeCopyWith, coneAttribute);
                }
            }
            i = i2;
        }
        if (mutableList != null && !z) {
            return this;
        }
        if (mutableList != null) {
            return INSTANCE.create(mutableList);
        }
        return null;
    }

    public final ConeAttributes union(ConeAttributes other) {
        ConeAttribute coneAttributeUnion;
        other.getClass();
        if (isEmpty() && other.isEmpty()) {
            return this;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = INSTANCE.getIndices().iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            ConeAttribute coneAttribute = (ConeAttribute) getArrayMap().get(iIntValue);
            ConeAttribute coneAttribute2 = (ConeAttribute) other.getArrayMap().get(iIntValue);
            if (coneAttribute == null) {
                coneAttributeUnion = coneAttribute2 != null ? coneAttribute2.union(coneAttribute) : null;
            } else {
                coneAttributeUnion = coneAttribute.union(coneAttribute2);
            }
            org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(arrayList, coneAttributeUnion);
        }
        return INSTANCE.create(arrayList);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J)\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\b0\u0006\"\u0010\b\u0000\u0010\b\u0018\u0001*\b\u0012\u0004\u0012\u0002H\b0\u0002H\u0086\bJ\u0018\u0010\u000e\u001a\u00020\u00072\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0010R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeAttributes$Companion;", "Lorg/jetbrains/kotlin/fir/util/ConeTypeRegistry;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "<init>", "()V", "attributeAccessor", "Lkotlin/properties/ReadOnlyProperty;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "T", "Empty", "getEmpty", "()Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "WithExtensionFunctionType", "getWithExtensionFunctionType", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "attributes", Argument.Delimiters.none, "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends ConeTypeRegistry<ConeAttribute<?>, ConeAttribute<?>> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final /* synthetic */ <T extends ConeAttribute<? extends T>> ReadOnlyProperty<ConeAttributes, T> attributeAccessor() {
            Intrinsics.reifiedOperationMarker(4, "T");
            NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = generateNullableAccessor(Reflection.getOrCreateKotlinClass(ConeAttribute.class));
            nullableArrayMapAccessorGenerateNullableAccessor.getClass();
            return nullableArrayMapAccessorGenerateNullableAccessor;
        }

        public final ConeAttributes create(List<? extends ConeAttribute<?>> attributes) {
            attributes.getClass();
            return attributes.isEmpty() ? getEmpty() : new ConeAttributes(attributes, null);
        }

        public final ConeAttributes getEmpty() {
            return ConeAttributes.Empty;
        }

        public final ConeAttributes getWithExtensionFunctionType() {
            return ConeAttributes.WithExtensionFunctionType;
        }

        private Companion() {
        }
    }

    public final boolean contains(ConeAttribute<?> attribute) {
        attribute.getClass();
        return contains(attribute.getKey());
    }

    public /* synthetic */ ConeAttributes(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    public final ConeAttributes remove(ConeAttribute<?> attribute) {
        attribute.getClass();
        if (!isEmpty()) {
            ArrayMap arrayMap = getArrayMap();
            ArrayList arrayList = new ArrayList();
            for (Object obj : arrayMap) {
                if (!Intrinsics.areEqual((ConeAttribute) obj, attribute)) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.size() != getArrayMap().getSize()) {
                return INSTANCE.create(arrayList);
            }
        }
        return this;
    }

    public final ConeAttributes add(ConeAttribute<?> attribute) {
        attribute.getClass();
        return add(INSTANCE.create(CollectionsKt.listOf(attribute)));
    }
}
