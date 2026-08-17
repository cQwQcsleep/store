package org.jetbrains.kotlin.fir.deserialization;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.deserialization.PackagePartsCacheData;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001#B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R-\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\t0\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR-\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\t0\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b\u001e\u0010\u001aR-\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\t0\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u001c\u001a\u0004\b!\u0010\u001a¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData;", Argument.Delimiters.none, "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Package;", "context", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "extra", "Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData$Extra;", "fileAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Package;Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData$Extra;Ljava/util/List;)V", "getProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Package;", "getContext", "()Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "getExtra", "()Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData$Extra;", "getFileAnnotations", "()Ljava/util/List;", "topLevelFunctionNameIndex", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "getTopLevelFunctionNameIndex", "()Ljava/util/Map;", "topLevelFunctionNameIndex$delegate", "Lkotlin/Lazy;", "topLevelPropertyNameIndex", "getTopLevelPropertyNameIndex", "topLevelPropertyNameIndex$delegate", "typeAliasNameIndex", "getTypeAliasNameIndex", "typeAliasNameIndex$delegate", "Extra", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PackagePartsCacheData {
    private final FirDeserializationContext context;
    private final Extra extra;
    private final List<FirAnnotation> fileAnnotations;
    private final ProtoBuf.Package proto;

    /* JADX INFO: renamed from: topLevelFunctionNameIndex$delegate, reason: from kotlin metadata */
    private final Lazy topLevelFunctionNameIndex;

    /* JADX INFO: renamed from: topLevelPropertyNameIndex$delegate, reason: from kotlin metadata */
    private final Lazy topLevelPropertyNameIndex;

    /* JADX INFO: renamed from: typeAliasNameIndex$delegate, reason: from kotlin metadata */
    private final Lazy typeAliasNameIndex;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData$Extra;", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface Extra {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PackagePartsCacheData(ProtoBuf.Package r1, FirDeserializationContext firDeserializationContext, Extra extra, List<? extends FirAnnotation> list) {
        r1.getClass();
        firDeserializationContext.getClass();
        list.getClass();
        this.proto = r1;
        this.context = firDeserializationContext;
        this.extra = extra;
        this.fileAnnotations = list;
        this.topLevelFunctionNameIndex = LazyKt.lazy(new Function0() { // from class: xva
            public final Object invoke() {
                return PackagePartsCacheData.c(this.b);
            }
        });
        this.topLevelPropertyNameIndex = LazyKt.lazy(new Function0() { // from class: yva
            public final Object invoke() {
                return PackagePartsCacheData.a(this.b);
            }
        });
        this.typeAliasNameIndex = LazyKt.lazy(new Function0() { // from class: zva
            public final Object invoke() {
                return PackagePartsCacheData.b(this.b);
            }
        });
    }

    public static Map a(PackagePartsCacheData packagePartsCacheData) {
        List propertyList = packagePartsCacheData.proto.getPropertyList();
        propertyList.getClass();
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(propertyList);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (IndexedValue indexedValue : iterableWithIndex) {
            Name name = NameResolverUtilKt.getName(packagePartsCacheData.context.getNameResolver(), ((ProtoBuf.Property) indexedValue.getValue()).getName());
            Object arrayList = linkedHashMap.get(name);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(name, arrayList);
            }
            ((List) arrayList).add(Integer.valueOf(indexedValue.getIndex()));
        }
        return linkedHashMap;
    }

    public static Map b(PackagePartsCacheData packagePartsCacheData) {
        List typeAliasList = packagePartsCacheData.proto.getTypeAliasList();
        typeAliasList.getClass();
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(typeAliasList);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (IndexedValue indexedValue : iterableWithIndex) {
            Name name = NameResolverUtilKt.getName(packagePartsCacheData.context.getNameResolver(), ((ProtoBuf.TypeAlias) indexedValue.getValue()).getName());
            Object arrayList = linkedHashMap.get(name);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(name, arrayList);
            }
            ((List) arrayList).add(Integer.valueOf(indexedValue.getIndex()));
        }
        return linkedHashMap;
    }

    public static Map c(PackagePartsCacheData packagePartsCacheData) {
        List functionList = packagePartsCacheData.proto.getFunctionList();
        functionList.getClass();
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(functionList);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (IndexedValue indexedValue : iterableWithIndex) {
            Name name = NameResolverUtilKt.getName(packagePartsCacheData.context.getNameResolver(), ((ProtoBuf.Function) indexedValue.getValue()).getName());
            Object arrayList = linkedHashMap.get(name);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(name, arrayList);
            }
            ((List) arrayList).add(Integer.valueOf(indexedValue.getIndex()));
        }
        return linkedHashMap;
    }

    public final FirDeserializationContext getContext() {
        return this.context;
    }

    public final Extra getExtra() {
        return this.extra;
    }

    public final List<FirAnnotation> getFileAnnotations() {
        return this.fileAnnotations;
    }

    public final ProtoBuf.Package getProto() {
        return this.proto;
    }

    public final Map<Name, List<Integer>> getTopLevelFunctionNameIndex() {
        return (Map) this.topLevelFunctionNameIndex.getValue();
    }

    public final Map<Name, List<Integer>> getTopLevelPropertyNameIndex() {
        return (Map) this.topLevelPropertyNameIndex.getValue();
    }

    public final Map<Name, List<Integer>> getTypeAliasNameIndex() {
        return (Map) this.typeAliasNameIndex.getValue();
    }

    public /* synthetic */ PackagePartsCacheData(ProtoBuf.Package r1, FirDeserializationContext firDeserializationContext, Extra extra, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(r1, firDeserializationContext, (i & 4) != 0 ? null : extra, (i & 8) != 0 ? CollectionsKt.emptyList() : list);
    }
}
