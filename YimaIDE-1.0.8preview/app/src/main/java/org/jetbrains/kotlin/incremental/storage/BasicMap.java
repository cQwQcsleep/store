package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.IOUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.utils.Printer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bJ\u0015\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/BasicMap;", "KEY", "VALUE", "Lorg/jetbrains/kotlin/incremental/storage/PersistentStorage;", "clear", "", "deleteStorageFiles", "dump", "", "dumpKey", "key", "(Ljava/lang/Object;)Ljava/lang/String;", "dumpValue", "value", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface BasicMap<KEY, VALUE> extends PersistentStorage<KEY, VALUE> {
    /* JADX WARN: Multi-variable type inference failed */
    default void clear() {
        synchronized (this) {
            try {
                Iterator it = getKeys().iterator();
                while (it.hasNext()) {
                    remove(it.next());
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    default void deleteStorageFiles() {
        synchronized (this) {
            if (!IOUtil.deleteAllFilesStartingWith(getStorageFile())) {
                throw new IllegalStateException(("Unable to delete storage file(s) with name prefix: " + getStorageFile().getPath()).toString());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    default String dump() {
        LinkedHashMap linkedHashMap;
        synchronized (this) {
            Set keys = getKeys();
            linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(keys, 10)), 16));
            for (Object obj : keys) {
                Object obj2 = get(obj);
                obj2.getClass();
                linkedHashMap.put(obj, obj2);
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(TuplesKt.to(dumpKey(entry.getKey()), dumpValue(entry.getValue())));
        }
        Map map = MapsKt.toMap(CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.incremental.storage.BasicMap$dump$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((String) ((Pair) t).getFirst(), (String) ((Pair) t2).getFirst());
            }
        }));
        StringBuilder sb = new StringBuilder();
        Printer printer = new Printer(sb, 0, (String) null, 6, (DefaultConstructorMarker) null);
        StringBuilder sb2 = new StringBuilder();
        String name = getStorageFile().getName();
        name.getClass();
        sb2.append(StringsKt.substringBefore$default(name, ".tab", (String) null, 2, (Object) null));
        sb2.append(" (");
        sb2.append(getClass().getSimpleName());
        sb2.append(')');
        printer.println(new Object[]{sb2.toString()});
        printer.pushIndent();
        for (Map.Entry entry2 : map.entrySet()) {
            printer.println(new Object[]{((String) entry2.getKey()) + " -> " + ((String) entry2.getValue())});
        }
        printer.popIndent();
        return sb.toString();
    }

    default String dumpKey(KEY key) {
        return String.valueOf(key);
    }

    default String dumpValue(VALUE value) {
        return value instanceof Collection ? CollectionsKt.sortedWith((Iterable) value, new Comparator() { // from class: org.jetbrains.kotlin.incremental.storage.BasicMap$dumpValue$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(String.valueOf(t), String.valueOf(t2));
            }
        }).toString() : String.valueOf(value);
    }
}
