package defpackage;

import com.android.tools.r8.BaseCommand;
import java.nio.file.Path;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class gn0 implements Consumer {
    public final /* synthetic */ BaseCommand.Builder b;

    public /* synthetic */ gn0(BaseCommand.Builder builder) {
        this.b = builder;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.a((Path) obj);
    }
}
