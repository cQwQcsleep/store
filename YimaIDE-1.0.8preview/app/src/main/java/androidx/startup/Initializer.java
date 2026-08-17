package androidx.startup;

import android.content.Context;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface Initializer<T> {
    T create(Context context);

    List<Class<? extends Initializer<?>>> dependencies();
}
