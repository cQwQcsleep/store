package defpackage;

import com.android.tools.r8.retrace.RetracedSingleFrame;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class a71 implements Consumer {
    public final /* synthetic */ Stream.Builder b;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.add((RetracedSingleFrame) obj);
    }
}
