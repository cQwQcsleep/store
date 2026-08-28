package j$.util.stream;

import java.util.stream.Collector;

/* renamed from: j$.util.stream.j, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0140j {
    public final /* synthetic */ Collector a;

    private /* synthetic */ C0140j(Collector collector) {
        this.a = collector;
    }

    public static /* synthetic */ C0140j a(Collector collector) {
        if (collector == null) {
            return null;
        }
        return new C0140j(collector);
    }

    public final /* synthetic */ boolean equals(Object obj) {
        Collector collector = this.a;
        if (obj instanceof C0140j) {
            obj = ((C0140j) obj).a;
        }
        return collector.equals(obj);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }
}
