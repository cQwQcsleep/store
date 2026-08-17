package defpackage;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.InterfaceC0968Xw;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class xlh implements Consumer {
    public final /* synthetic */ InterfaceC0968Xw b;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.add((AbstractC0890Uw) obj);
    }
}
