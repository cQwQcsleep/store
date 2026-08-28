package j$.util.stream;

import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.o, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0165o implements DoubleConsumer {
    public final /* synthetic */ InterfaceC0182r2 a;

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.a.accept(d);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }
}
