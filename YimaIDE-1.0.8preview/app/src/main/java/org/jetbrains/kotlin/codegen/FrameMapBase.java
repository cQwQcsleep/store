package org.jetbrains.kotlin.codegen;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001\u001fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010J\u0015\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0019\u001a\f0\u001aR\b\u0012\u0004\u0012\u00028\u00000\u0000J\u0018\u0010\u001b\u001a\f0\u001aR\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001c\u001a\u00020\tJ\n\u0010\u001d\u001a\u00020\u001eH\u0096\u0080\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/codegen/FrameMapBase;", "T", Argument.Delimiters.none, "<init>", "()V", "myVarIndex", "Lit/unimi/dsi/fastutil/objects/Object2IntOpenHashMap;", "myVarSizes", "value", Argument.Delimiters.none, "currentSize", "getCurrentSize", "()I", "enter", "key", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "(Ljava/lang/Object;Lorg/jetbrains/org/objectweb/asm/Type;)I", "leave", "(Ljava/lang/Object;)I", "enterTemp", "leaveTemp", Argument.Delimiters.none, "getIndex", "descriptor", "mark", "Lorg/jetbrains/kotlin/codegen/FrameMapBase$Mark;", "skipTo", "target", "toString", Argument.Delimiters.none, "Mark", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FrameMapBase<T> {
    private int currentSize;
    private final Object2IntOpenHashMap<T> myVarIndex = new Object2IntOpenHashMap<>();
    private final Object2IntOpenHashMap<T> myVarSizes = new Object2IntOpenHashMap<>();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/FrameMapBase$Mark;", Argument.Delimiters.none, "myIndex", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/FrameMapBase;I)V", "dropTo", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class Mark {
        private final int myIndex;

        public Mark(int i) {
            this.myIndex = i;
        }

        public final void dropTo() {
            ArrayList arrayList = new ArrayList();
            ObjectIterator objectIteratorFastIterator = ((FrameMapBase) FrameMapBase.this).myVarIndex.object2IntEntrySet().fastIterator();
            while (objectIteratorFastIterator.hasNext()) {
                Object2IntMap.Entry entry = (Object2IntMap.Entry) objectIteratorFastIterator.next();
                entry.getClass();
                Object key = entry.getKey();
                if (((Integer) entry.getValue()).intValue() >= this.myIndex) {
                    arrayList.add(key);
                }
            }
            Iterator it = arrayList.iterator();
            it.getClass();
            while (it.hasNext()) {
                Object next = it.next();
                next.getClass();
                ((FrameMapBase) FrameMapBase.this).myVarIndex.removeInt(next);
                ((FrameMapBase) FrameMapBase.this).myVarSizes.removeInt(next);
            }
            ((FrameMapBase) FrameMapBase.this).currentSize = this.myIndex;
        }
    }

    public int enter(T key, Type type) {
        key.getClass();
        type.getClass();
        int i = this.currentSize;
        this.myVarIndex.put(key, i);
        this.currentSize += type.getSize();
        this.myVarSizes.put(key, type.getSize());
        return i;
    }

    public final int enterTemp(Type type) {
        type.getClass();
        int i = this.currentSize;
        this.currentSize = type.getSize() + i;
        return i;
    }

    public final int getCurrentSize() {
        return this.currentSize;
    }

    public int getIndex(T descriptor) {
        descriptor.getClass();
        if (this.myVarIndex.containsKey(descriptor)) {
            return this.myVarIndex.getInt(descriptor);
        }
        return -1;
    }

    public int leave(T key) {
        key.getClass();
        Integer num = (Integer) MapsKt.getValue(this.myVarSizes, key);
        int i = this.currentSize;
        num.getClass();
        this.currentSize = i - num.intValue();
        this.myVarSizes.removeInt(key);
        int iRemoveInt = this.myVarIndex.removeInt(key);
        if (iRemoveInt == this.currentSize) {
            return iRemoveInt;
        }
        qu7.a("Descriptor can be left only if it is last: ", key);
        return 0;
    }

    public final void leaveTemp(Type type) {
        type.getClass();
        this.currentSize -= type.getSize();
    }

    public final FrameMapBase<T>.Mark mark() {
        return new Mark(this.currentSize);
    }

    public final FrameMapBase<T>.Mark skipTo(int target) {
        FrameMapBase<T>.Mark mark = mark();
        if (this.currentSize < target) {
            this.currentSize = target;
        }
        return mark;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.myVarIndex.size() != this.myVarSizes.size()) {
            return "inconsistent";
        }
        ArrayList<Triple> arrayList = new ArrayList();
        ObjectIterator it = this.myVarIndex.keySet().iterator();
        it.getClass();
        while (it.hasNext()) {
            Object next = it.next();
            next.getClass();
            arrayList.add(new Triple(next, Integer.valueOf(this.myVarIndex.getInt(next)), Integer.valueOf(this.myVarSizes.getInt(next))));
        }
        boolean z = true;
        if (arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.codegen.FrameMapBase$toString$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) ((Triple) t).getSecond(), (Integer) ((Triple) t2).getSecond());
                }
            });
        }
        sb.append("size=");
        sb.append(this.currentSize);
        for (Triple triple : arrayList) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(triple.getFirst());
            sb.append(",i=");
            sb.append(((Number) triple.getSecond()).intValue());
            sb.append(",s=");
            sb.append(((Number) triple.getThird()).intValue());
            z = false;
        }
        return sb.toString();
    }
}
