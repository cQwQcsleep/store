package androidx.compose.compiler.plugins.kotlin.inference;

import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0000\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0013\u001a\u00020\u0014J\n\u0010\u0015\u001a\u00020\u0014H\u0096\u0080\u0004J\u0014\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\u000e\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u0000J\n\u0010\u001e\u001a\u00020\u001fH\u0096\u0080\u0004J\u0010\u0010 \u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u0000H\u0002J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u0000H\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\u0012\u0010#\u001a\u00020\u001f*\b\u0012\u0004\u0012\u00020\u00000\u0005H\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020\u0000H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018¨\u0006)"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "", "target", "Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "parameters", "", "result", "anyParameters", "", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/inference/Item;Ljava/util/List;Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;Z)V", "getTarget", "()Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "getParameters", "()Ljava/util/List;", "getResult", "()Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "getAnyParameters", "()Z", "serialize", "", "toString", "parametersStr", "getParametersStr", "()Ljava/lang/String;", "resultStr", "getResultStr", "equals", "other", "canOverride", "hashCode", "", "simpleCanOverride", "simpleEquals", "simpleHashCode", "hashOfElements", "serializeTo", "", "writer", "Landroidx/compose/compiler/plugins/kotlin/inference/SchemeStringSerializationWriter;", "alphaRename", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Scheme {
    private final boolean anyParameters;
    private final List<Scheme> parameters;
    private final Scheme result;
    private final Item target;

    public Scheme(Item item, List<Scheme> list, Scheme scheme, boolean z) {
        item.getClass();
        list.getClass();
        this.target = item;
        this.parameters = list;
        this.result = scheme;
        this.anyParameters = z;
        if (!z || list.isEmpty()) {
            return;
        }
        k2d.a("`anyParameters` == true must have empty parameters");
        throw null;
    }

    public static CharSequence a(Scheme scheme) {
        scheme.getClass();
        return scheme.toString();
    }

    private final Scheme alphaRename() {
        int index;
        Item item = this.target;
        if (((item instanceof Open) && (-1 > (index = ((Open) item).getIndex()) || index >= 1)) || !this.parameters.isEmpty()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            alphaRename$scan(linkedHashMap, new Ref.IntRef(), this);
            if (!linkedHashMap.isEmpty()) {
                return alphaRename$rename(linkedHashMap, this);
            }
        }
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x004b  */
    /* JADX WARN: Code duplicated, block: B:33:0x00b4 A[RETURN] */
    private static final Scheme alphaRename$rename(Map<Integer, Integer> map, Scheme scheme) {
        Item open;
        Item item = scheme.target;
        List<Scheme> list = scheme.parameters;
        Scheme scheme2 = scheme.result;
        Scheme scheme3 = null;
        if (item instanceof Open) {
            Open open2 = (Open) item;
            int index = open2.getIndex();
            Integer num = map.get(Integer.valueOf(open2.getIndex()));
            if (num != null && index == num.intValue()) {
                open = item;
            } else {
                Integer num2 = map.get(Integer.valueOf(open2.getIndex()));
                num2.getClass();
                open = new Open(num2.intValue(), false, 2, scheme3);
            }
        } else {
            open = item;
        }
        List<Scheme> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(alphaRename$rename(map, (Scheme) it.next()));
        }
        Scheme schemeAlphaRename$rename = scheme2 != null ? alphaRename$rename(map, scheme2) : null;
        if (item == open) {
            List<Pair> listZip = CollectionsKt.zip(arrayList, list2);
            if (!(listZip instanceof Collection) || !listZip.isEmpty()) {
                for (Pair pair : listZip) {
                    if (((Scheme) pair.component1()) != ((Scheme) pair.component2())) {
                    }
                }
                if (Intrinsics.areEqual(schemeAlphaRename$rename, scheme2)) {
                    return scheme;
                }
            } else if (Intrinsics.areEqual(schemeAlphaRename$rename, scheme2)) {
                return scheme;
            }
        }
        return new Scheme(open, arrayList, schemeAlphaRename$rename, false, 8, null);
    }

    private static final void alphaRename$scan(Map<Integer, Integer> map, Ref.IntRef intRef, Scheme scheme) {
        Integer num;
        Item item = scheme.target;
        List<Scheme> list = scheme.parameters;
        Scheme scheme2 = scheme.result;
        if (item instanceof Open) {
            int index = ((Open) item).getIndex();
            if (!map.containsKey(Integer.valueOf(index))) {
                map.put(Integer.valueOf(index), -1);
            } else if (index >= 0 && (num = map.get(Integer.valueOf(index))) != null && num.intValue() == -1) {
                Integer numValueOf = Integer.valueOf(index);
                int i = intRef.element;
                intRef.element = i + 1;
                map.put(numValueOf, Integer.valueOf(i));
            }
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            alphaRename$scan(map, intRef, (Scheme) it.next());
        }
        if (scheme2 != null) {
            alphaRename$scan(map, intRef, scheme2);
        }
    }

    private final String getParametersStr() {
        if (this.parameters.isEmpty()) {
            return "";
        }
        return ", " + CollectionsKt.joinToString$default(this.parameters, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: lvc
            public final Object invoke(Object obj) {
                return Scheme.a((Scheme) obj);
            }
        }, 30, (Object) null);
    }

    private final String getResultStr() {
        Scheme scheme = this.result;
        if (scheme == null) {
            return "";
        }
        return ": " + scheme;
    }

    private final int hashOfElements(List<Scheme> list) {
        if (list.isEmpty()) {
            return 0;
        }
        List<Scheme> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((Scheme) it.next()).simpleHashCode()));
        }
        ListIterator listIterator = arrayList.listIterator(arrayList.size());
        if (!listIterator.hasPrevious()) {
            c41.a("Empty list can't be reduced.");
            return 0;
        }
        Object objPrevious = listIterator.previous();
        while (listIterator.hasPrevious()) {
            objPrevious = Integer.valueOf(((Number) listIterator.previous()).intValue() + (((Number) objPrevious).intValue() * 31));
        }
        return ((Number) objPrevious).intValue();
    }

    private final void serializeTo(SchemeStringSerializationWriter writer) {
        writer.writeOpen();
        this.target.serializeTo$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(writer);
        if (this.anyParameters) {
            writer.writeAnyParameters();
        } else {
            Iterator<T> it = this.parameters.iterator();
            while (it.hasNext()) {
                ((Scheme) it.next()).serializeTo(writer);
            }
        }
        if (this.result != null) {
            writer.writeResultPrefix();
            this.result.serializeTo(writer);
        }
        writer.writeClose();
    }

    private final boolean simpleCanOverride(Scheme other) {
        Scheme scheme;
        Item item = other.target;
        boolean z = item instanceof Open;
        Item item2 = this.target;
        if (z) {
            if (!(item2 instanceof Open) || ((Open) item).getIndex() != ((Open) this.target).getIndex()) {
                return false;
            }
        } else if (!item2.getIsUnspecified() && !Intrinsics.areEqual(this.target, other.target)) {
            return false;
        }
        List<Pair> listZip = CollectionsKt.zip(this.parameters, other.parameters);
        if (!(listZip instanceof Collection) || !listZip.isEmpty()) {
            for (Pair pair : listZip) {
                if (!((Scheme) pair.component1()).simpleCanOverride((Scheme) pair.component2())) {
                    return false;
                }
            }
        }
        if (Intrinsics.areEqual(this.result, other.result)) {
            return true;
        }
        Scheme scheme2 = other.result;
        return (scheme2 == null || (scheme = this.result) == null || !scheme.canOverride(scheme2)) ? false : true;
    }

    private final boolean simpleEquals(Scheme other) {
        if (!Intrinsics.areEqual(this.target, other.target)) {
            return false;
        }
        List<Pair> listZip = CollectionsKt.zip(this.parameters, other.parameters);
        if (!(listZip instanceof Collection) || !listZip.isEmpty()) {
            for (Pair pair : listZip) {
                if (!Intrinsics.areEqual((Scheme) pair.component1(), (Scheme) pair.component2())) {
                    return false;
                }
            }
        }
        Scheme scheme = this.result;
        return Intrinsics.areEqual(scheme, scheme);
    }

    private final int simpleHashCode() {
        int iHashCode = (this.target.hashCode() * 31) + hashOfElements(this.parameters);
        Scheme scheme = this.result;
        return iHashCode + (scheme != null ? scheme.hashCode() : 0);
    }

    public final boolean canOverride(Scheme other) {
        other.getClass();
        return alphaRename().simpleCanOverride(other.alphaRename());
    }

    public boolean equals(Object other) {
        Scheme scheme = other instanceof Scheme ? (Scheme) other : null;
        if (scheme == null) {
            return false;
        }
        return alphaRename().simpleEquals(scheme.alphaRename());
    }

    public final boolean getAnyParameters() {
        return this.anyParameters;
    }

    public final List<Scheme> getParameters() {
        return this.parameters;
    }

    public final Scheme getResult() {
        return this.result;
    }

    public final Item getTarget() {
        return this.target;
    }

    public int hashCode() {
        return alphaRename().simpleHashCode();
    }

    public final String serialize() {
        StringBuilder sb = new StringBuilder();
        serializeTo(new SchemeStringSerializationWriter(sb));
        return sb.toString();
    }

    public String toString() {
        return "[" + this.target + getParametersStr() + getResultStr() + ']';
    }

    public /* synthetic */ Scheme(Item item, List list, Scheme scheme, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(item, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? null : scheme, (i & 8) != 0 ? false : z);
    }
}
