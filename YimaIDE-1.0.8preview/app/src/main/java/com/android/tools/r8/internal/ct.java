package com.android.tools.r8.internal;

import com.sun.management.HotSpotDiagnosticMXBean;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ct {
    public static volatile HotSpotDiagnosticMXBean a;

    public static void a(Path path, boolean z) throws IOException {
        if (a == null) {
            synchronized (ct.class) {
                try {
                    if (a == null) {
                        a = (HotSpotDiagnosticMXBean) ManagementFactory.newPlatformMXBeanProxy(ManagementFactory.getPlatformMBeanServer(), "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        a.dumpHeap(path.toString(), z);
    }
}
