package org.jetbrains.kotlin.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableSet;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import org.jline.reader.LineReader;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010)\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u0016*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0003\u0016\u0017\u0018B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0096\u0082\u0004J\u0017\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u0010\u0012J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\u0017\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0012R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\bX\u0096\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/utils/SmartSet;", "T", "Lkotlin/collections/AbstractMutableSet;", "<init>", "()V", "data", "", "size", "", "getSize", "()I", "setSize", "(I)V", "iterator", "", "add", "", "element", "(Ljava/lang/Object;)Z", LineReader.CLEAR, "", "contains", "Companion", "SingletonIterator", "ArrayIterator", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class SmartSet<T> extends AbstractMutableSet<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Object data;
    private int size;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\t\u001a\u00028\u0001H\u0096\u0082\u0004¢\u0006\u0002\u0010\nJ\n\u0010\u0007\u001a\u00020\bH\u0096\u0082\u0004J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004R\u0010\u0010\u0003\u001a\u00028\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/utils/SmartSet$SingletonIterator;", "T", "", "element", "<init>", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "hasNext", "", "next", "()Ljava/lang/Object;", "remove", "", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class SingletonIterator<T> implements Iterator<T>, KMutableIterator {
        private final T element;
        private boolean hasNext = true;

        public SingletonIterator(T t) {
            this.element = t;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.hasNext) {
                this.hasNext = false;
                return this.element;
            }
            z0e.a();
            return null;
        }

        @Override // java.util.Iterator
        public Void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public /* synthetic */ SmartSet(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final <T> SmartSet<T> create() {
        return INSTANCE.create();
    }

    public boolean add(T element) {
        Object obj;
        if (size() == 0) {
            this.data = element;
        } else if (size() != 1) {
            int size = size();
            Object obj2 = this.data;
            if (size < 5) {
                obj2.getClass();
                Object[] objArr = (Object[]) obj2;
                if (ArraysKt.contains(objArr, element)) {
                    return false;
                }
                if (size() == 4) {
                    LinkedHashSet linkedHashSetLinkedSetOf = SetsKt.linkedSetOf(Arrays.copyOf(objArr, objArr.length));
                    linkedHashSetLinkedSetOf.add(element);
                    obj = linkedHashSetLinkedSetOf;
                } else {
                    Object[] objArrCopyOf = Arrays.copyOf(objArr, size() + 1);
                    objArrCopyOf[objArrCopyOf.length - 1] = element;
                    obj = objArrCopyOf;
                }
                this.data = obj;
            } else {
                obj2.getClass();
                if (!TypeIntrinsics.asMutableSet(obj2).add(element)) {
                    return false;
                }
            }
        } else {
            if (Intrinsics.areEqual(this.data, element)) {
                return false;
            }
            this.data = new Object[]{this.data, element};
        }
        setSize(size() + 1);
        return true;
    }

    public void clear() {
        this.data = null;
        setSize(0);
    }

    public boolean contains(Object element) {
        if (size() == 0) {
            return false;
        }
        if (size() == 1) {
            return Intrinsics.areEqual(this.data, element);
        }
        int size = size();
        Object obj = this.data;
        if (size < 5) {
            obj.getClass();
            return ArraysKt.contains((Object[]) obj, element);
        }
        obj.getClass();
        return ((Set) obj).contains(element);
    }

    public int getSize() {
        return this.size;
    }

    public Iterator<T> iterator() {
        if (size() == 0) {
            return Collections.EMPTY_SET.iterator();
        }
        if (size() == 1) {
            return new SingletonIterator(this.data);
        }
        int size = size();
        Object obj = this.data;
        if (size < 5) {
            obj.getClass();
            return new ArrayIterator((Object[]) obj);
        }
        obj.getClass();
        return TypeIntrinsics.asMutableSet(obj).iterator();
    }

    public void setSize(int i) {
        this.size = i;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0001\u0010\bH\u0007J\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0001\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/utils/SmartSet$Companion;", "", "<init>", "()V", "ARRAY_THRESHOLD", "", "create", "Lorg/jetbrains/kotlin/utils/SmartSet;", "T", "set", "", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.util.AbstractCollection, org.jetbrains.kotlin.utils.SmartSet, org.jetbrains.kotlin.utils.SmartSet<T>] */
        @JvmStatic
        public final <T> SmartSet<T> create(Collection<? extends T> set) {
            set.getClass();
            ?? r1 = (SmartSet<T>) new SmartSet(null);
            r1.addAll(set);
            return r1;
        }

        private Companion() {
        }

        @JvmStatic
        public final <T> SmartSet<T> create() {
            return new SmartSet<>(null);
        }
    }

    private SmartSet() {
    }

    @JvmStatic
    public static final <T> SmartSet<T> create(Collection<? extends T> collection) {
        return INSTANCE.create(collection);
    }
}
