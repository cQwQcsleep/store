package org.jetbrains.kotlin.contracts.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.MutableContextInfo;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 .2\u00020\u0001:\u0001.B\u007f\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006\u0012\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006\u0012\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006\u0012\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\tJ\u0016\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\tJ\u0016\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007J\u0016\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0004J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0000J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0000JP\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0\b0\u0006\"\u0004\b\u0000\u0010$*\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0\b0\u00062\u0018\u0010%\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0\b0\u0006H\u0002JP\u0010&\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0\b0\u0006\"\u0004\b\u0000\u0010$*\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0\b0'2\u0018\u0010%\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0\b0'H\u0002J9\u0010(\u001a\u00020)\"\u0004\b\u0000\u0010$*\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0\b0\u00062\u0006\u0010*\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u0002H$H\u0002¢\u0006\u0002\u0010+J\u0006\u0010,\u001a\u00020-R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R#\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R#\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R#\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/MutableContextInfo;", Argument.Delimiters.none, "firedEffects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "subtypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESValue;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/KotlinType;", "notSubtypes", "equalValues", "notEqualValues", "<init>", "(Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "getFiredEffects", "()Ljava/util/List;", "getSubtypes", "()Ljava/util/Map;", "getNotSubtypes", "getEqualValues", "getNotEqualValues", "subtype", "value", ModuleXmlParser.TYPE, "notSubtype", "equal", "left", "right", "notEqual", "fire", "effect", "or", "other", "and", "intersect", "D", "that", "union", Argument.Delimiters.none, "initAndAdd", Argument.Delimiters.none, "key", "(Ljava/util/Map;Lorg/jetbrains/kotlin/contracts/model/ESValue;Ljava/lang/Object;)V", "print", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MutableContextInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<ESValue, Set<ESValue>> equalValues;
    private final List<ESEffect> firedEffects;
    private final Map<ESValue, Set<ESValue>> notEqualValues;
    private final Map<ESValue, Set<KotlinType>> notSubtypes;
    private final Map<ESValue, Set<KotlinType>> subtypes;

    private MutableContextInfo(List<ESEffect> list, Map<ESValue, Set<KotlinType>> map, Map<ESValue, Set<KotlinType>> map2, Map<ESValue, Set<ESValue>> map3, Map<ESValue, Set<ESValue>> map4) {
        this.firedEffects = list;
        this.subtypes = map;
        this.notSubtypes = map2;
        this.equalValues = map3;
        this.notEqualValues = map4;
    }

    public static Set a(Function2 function2, Object obj, Object obj2) {
        return (Set) function2.invoke(obj, obj2);
    }

    public static Set b(Object obj, ESValue eSValue, Set set) {
        eSValue.getClass();
        if (set == null) {
            set = new LinkedHashSet();
        }
        set.add(obj);
        return set;
    }

    private final <D> void initAndAdd(Map<ESValue, Set<D>> map, ESValue eSValue, final D d) {
        final Function2 function2 = new Function2() { // from class: k9a
            public final Object invoke(Object obj, Object obj2) {
                return MutableContextInfo.b(d, (ESValue) obj, (Set) obj2);
            }
        };
        map.compute(eSValue, new BiFunction() { // from class: l9a
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return MutableContextInfo.a(function2, obj, obj2);
            }
        });
    }

    private final <D> Map<ESValue, Set<D>> intersect(Map<ESValue, Set<D>> map, Map<ESValue, Set<D>> map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ESValue eSValue : CollectionsKt.intersect(map.keySet(), map2.keySet())) {
            Set<D> set = map.get(eSValue);
            set.getClass();
            Set<D> set2 = map2.get(eSValue);
            set2.getClass();
            Set setIntersect = CollectionsKt.intersect(set, set2);
            if (!setIntersect.isEmpty()) {
                linkedHashMap.put(eSValue, CollectionsKt.toMutableSet(setIntersect));
            }
        }
        return linkedHashMap;
    }

    private static final <D> void print$lambda$0$printMapEntriesWithSeparator(Map<ESValue, ? extends Set<? extends D>> map, StringBuilder sb, String str) {
        Set<Map.Entry<ESValue, ? extends Set<? extends D>>> setEntrySet = map.entrySet();
        ArrayList<Map.Entry> arrayList = new ArrayList();
        for (Object obj : setEntrySet) {
            if (!((Collection) ((Map.Entry) obj).getValue()).isEmpty()) {
                arrayList.add(obj);
            }
        }
        for (Map.Entry entry : arrayList) {
            ESValue eSValue = (ESValue) entry.getKey();
            Set set = (Set) entry.getValue();
            sb.append(eSValue.toString());
            sb.append(Argument.Delimiters.space + str + ' ');
            sb.append(set.toString());
            sb.append('\n');
        }
    }

    private final <D> Map<ESValue, Set<D>> union(Map<ESValue, ? extends Set<D>> map, Map<ESValue, ? extends Set<D>> map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        Iterator<T> it = map2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            ESValue eSValue = (ESValue) entry.getKey();
            Set set = (Set) entry.getValue();
            Set linkedHashSet = (Set) linkedHashMap.get(eSValue);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet();
            }
            linkedHashSet.addAll(set);
            linkedHashMap.put(eSValue, linkedHashSet);
        }
        return linkedHashMap;
    }

    public final MutableContextInfo and(MutableContextInfo other) {
        other.getClass();
        return new MutableContextInfo(CollectionsKt.toMutableList(CollectionsKt.union(this.firedEffects, other.firedEffects)), union(this.subtypes, other.subtypes), union(this.notSubtypes, other.notSubtypes), union(this.equalValues, other.equalValues), union(this.notEqualValues, other.notEqualValues));
    }

    public final MutableContextInfo equal(ESValue left, ESValue right) {
        left.getClass();
        right.getClass();
        initAndAdd(this.equalValues, left, right);
        initAndAdd(this.equalValues, right, left);
        return this;
    }

    public final MutableContextInfo fire(ESEffect effect) {
        effect.getClass();
        this.firedEffects.add(effect);
        return this;
    }

    public final Map<ESValue, Set<ESValue>> getEqualValues() {
        return this.equalValues;
    }

    public final List<ESEffect> getFiredEffects() {
        return this.firedEffects;
    }

    public final Map<ESValue, Set<ESValue>> getNotEqualValues() {
        return this.notEqualValues;
    }

    public final Map<ESValue, Set<KotlinType>> getNotSubtypes() {
        return this.notSubtypes;
    }

    public final Map<ESValue, Set<KotlinType>> getSubtypes() {
        return this.subtypes;
    }

    public final MutableContextInfo notEqual(ESValue left, ESValue right) {
        left.getClass();
        right.getClass();
        initAndAdd(this.notEqualValues, left, right);
        initAndAdd(this.notEqualValues, right, left);
        return this;
    }

    public final MutableContextInfo notSubtype(ESValue value, KotlinType type) {
        value.getClass();
        type.getClass();
        initAndAdd(this.notSubtypes, value, type);
        return this;
    }

    public final MutableContextInfo or(MutableContextInfo other) {
        other.getClass();
        return new MutableContextInfo(CollectionsKt.toMutableList(CollectionsKt.intersect(this.firedEffects, other.firedEffects)), intersect(this.subtypes, other.subtypes), intersect(this.notSubtypes, other.notSubtypes), intersect(this.equalValues, other.equalValues), intersect(this.notEqualValues, other.notEqualValues));
    }

    public final String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fired effects: ");
        sb.append(CollectionsKt.joinToString$default(this.firedEffects, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        sb.append("\n");
        print$lambda$0$printMapEntriesWithSeparator(this.subtypes, sb, "is");
        print$lambda$0$printMapEntriesWithSeparator(this.notSubtypes, sb, "!is");
        print$lambda$0$printMapEntriesWithSeparator(this.equalValues, sb, "==");
        print$lambda$0$printMapEntriesWithSeparator(this.notEqualValues, sb, "!=");
        return sb.toString();
    }

    public final MutableContextInfo subtype(ESValue value, KotlinType type) {
        value.getClass();
        type.getClass();
        initAndAdd(this.subtypes, value, type);
        return this;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/MutableContextInfo$Companion;", Argument.Delimiters.none, "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/contracts/model/MutableContextInfo;", "getEMPTY", "()Lorg/jetbrains/kotlin/contracts/model/MutableContextInfo;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MutableContextInfo getEMPTY() {
            return new MutableContextInfo(new ArrayList(), new LinkedHashMap(), new LinkedHashMap(), new LinkedHashMap(), new LinkedHashMap(), null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ MutableContextInfo(List list, Map map, Map map2, Map map3, Map map4, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, map, map2, map3, map4);
    }
}
