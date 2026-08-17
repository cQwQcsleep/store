package com.android.tools.r8.internal;

import java.util.List;
import java.util.ServiceLoader;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1483fO extends YI implements InterfaceC1270cr {
    public static final C1483fO c = new C1483fO();

    public C1483fO() {
        super(0);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1270cr
    public final Object a() {
        ServiceLoader serviceLoaderLoad = ServiceLoader.load(InterfaceC1654hO.class, InterfaceC1654hO.class.getClassLoader());
        KB.b(serviceLoaderLoad, "load(...)");
        List listC = AbstractC1760ie.c(serviceLoaderLoad);
        if (!listC.isEmpty()) {
            return listC;
        }
        k2d.a("No MetadataExtensions instances found in the classpath. Please ensure that the META-INF/services/ is not stripped from your application and that the Java virtual machine is not running under a security manager");
        return null;
    }
}
