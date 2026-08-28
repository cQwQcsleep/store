package j$.util;

import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* renamed from: j$.util.t, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0224t implements PrimitiveIterator.OfDouble {
    public final /* synthetic */ InterfaceC0225u a;

    private /* synthetic */ C0224t(InterfaceC0225u interfaceC0225u) {
        this.a = interfaceC0225u;
    }

    public static /* synthetic */ PrimitiveIterator.OfDouble a(InterfaceC0225u interfaceC0225u) {
        if (interfaceC0225u == null) {
            return null;
        }
        return interfaceC0225u instanceof C0094s ? ((C0094s) interfaceC0225u).a : new C0224t(interfaceC0225u);
    }

    public final /* synthetic */ boolean equals(Object obj) {
        InterfaceC0225u interfaceC0225u = this.a;
        if (obj instanceof C0224t) {
            obj = ((C0224t) obj).a;
        }
        return interfaceC0225u.equals(obj);
    }

    @Override // java.util.PrimitiveIterator
    public final /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
        this.a.forEachRemaining((Object) doubleConsumer);
    }

    @Override // java.util.PrimitiveIterator.OfDouble, java.util.Iterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.a.forEachRemaining(consumer);
    }

    @Override // java.util.PrimitiveIterator.OfDouble
    /* renamed from: forEachRemaining, reason: avoid collision after fix types in other method */
    public final /* synthetic */ void forEachRemaining2(DoubleConsumer doubleConsumer) {
        this.a.forEachRemaining(doubleConsumer);
    }

    @Override // java.util.Iterator
    public final /* synthetic */ boolean hasNext() {
        return this.a.hasNext();
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.PrimitiveIterator.OfDouble, java.util.Iterator
    public final /* synthetic */ Double next() {
        return this.a.next();
    }

    @Override // java.util.PrimitiveIterator.OfDouble, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.a.next();
    }

    @Override // java.util.PrimitiveIterator.OfDouble
    public final /* synthetic */ double nextDouble() {
        return this.a.nextDouble();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ void remove() {
        this.a.remove();
    }
}
