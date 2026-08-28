package j$.util.stream;

import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class U implements IntConsumer {
    public final /* synthetic */ InterfaceC0182r2 a;

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.a.accept(i);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }
}
