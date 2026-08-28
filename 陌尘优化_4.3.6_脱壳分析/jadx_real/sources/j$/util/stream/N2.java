package j$.util.stream;

import j$.util.Collection$EL;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

/* loaded from: /workspace/unpacked/classes3.dex */
final class N2 extends F2 {
    private ArrayList d;

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.d = j >= 0 ? new ArrayList((int) j) : new ArrayList();
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final void l() {
        ArrayList arrayList = this.d;
        Object[] array = arrayList.toArray();
        Arrays.sort(array, this.b);
        ListIterator listIterator = arrayList.listIterator();
        for (Object obj : array) {
            listIterator.next();
            listIterator.set(obj);
        }
        long size = this.d.size();
        InterfaceC0182r2 interfaceC0182r2 = this.a;
        interfaceC0182r2.m(size);
        if (!this.c) {
            ArrayList arrayList2 = this.d;
            Objects.requireNonNull(interfaceC0182r2);
            Collection$EL.a(arrayList2, new C0095a(interfaceC0182r2, 1));
        } else {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (interfaceC0182r2.o()) {
                    break;
                } else {
                    interfaceC0182r2.accept((InterfaceC0182r2) next);
                }
            }
        }
        interfaceC0182r2.l();
        this.d = null;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.d.add(obj);
    }
}
