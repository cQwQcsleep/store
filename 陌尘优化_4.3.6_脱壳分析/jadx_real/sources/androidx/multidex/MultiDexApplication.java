package androidx.multidex;

import android.app.Application;
import android.content.Context;

/* loaded from: /workspace/unpacked/classes2.dex */
public class MultiDexApplication extends Application {
    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
