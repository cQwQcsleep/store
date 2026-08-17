package com.android.tools.r8.threading;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0613Ke;
import defpackage.n33;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class a {
    public static List<String> a() {
        return AbstractC0551Hu.a("com.android.tools.r8.threading.providers.blocking.ThreadingModuleBlockingProvider", "com.android.tools.r8.threading.providers.singlethreaded.ThreadingModuleSingleThreadedProvider");
    }

    public static ThreadingModuleProvider b() {
        Iterator<String> it = a().iterator();
        while (it.hasNext()) {
            try {
                return (ThreadingModuleProvider) Class.forName(it.next()).getDeclaredConstructor(null).newInstance(null);
            } catch (ClassNotFoundException unused) {
            } catch (ReflectiveOperationException e) {
                throw new C0613Ke("Failure creating provider for the threading module", e);
            }
        }
        n33.a("Failure to find a provider for the threading module");
        return null;
    }
}
