package j$.util;

import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* renamed from: j$.util.x, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0228x implements PrimitiveIterator.OfInt {
    public final /* synthetic */ InterfaceC0229y a;

    private /* synthetic */ C0228x(InterfaceC0229y interfaceC0229y) {
        this.a = interfaceC0229y;
    }

    public static /* synthetic */ PrimitiveIterator.OfInt a(InterfaceC0229y interfaceC0229y) {
        if (interfaceC0229y == null) {
            return null;
        }
        return interfaceC0229y instanceof C0227w ? ((C0227w) interfaceC0229y).a : new C0228x(interfaceC0229y);
    }

    public final /* synthetic */ boolean equals(Object obj) {
        InterfaceC0229y interfaceC0229y = this.a;
        if (obj instanceof C0228x) {
            obj = ((C0228x) obj).a;
        }
        return interfaceC0229y.equals(obj);
    }

    @Override // java.util.PrimitiveIterator
    public final /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
        this.a.forEachRemaining((Object) intConsumer);
    }

    @Override // java.util.PrimitiveIterator.OfInt, java.util.Iterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.a.forEachRemaining(consumer);
    }

    @Override // java.util.PrimitiveIterator.OfInt
    /* renamed from: forEachRemaining, reason: avoid collision after fix types in other method */
    public final /* synthetic */ void forEachRemaining2(IntConsumer intConsumer) {
        this.a.forEachRemaining(intConsumer);
    }

    @Override // java.util.Iterator
    public final /* synthetic */ boolean hasNext() {
        return this.a.hasNext();
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.PrimitiveIterator.OfInt, java.util.Iterator
    public final /* synthetic */ Integer next() {
        return this.a.next();
    }

    @Override // java.util.PrimitiveIterator.OfInt, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.a.next();
    }

    @Override // java.util.PrimitiveIterator.OfInt
    public final /* synthetic */ int nextInt() {
        return this.a.nextInt();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ void remove() {
        this.a.remove();
    }
}
