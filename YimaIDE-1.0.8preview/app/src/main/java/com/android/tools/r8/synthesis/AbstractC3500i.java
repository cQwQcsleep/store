package com.android.tools.r8.synthesis;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.DexFilePerClassFileConsumer;
import com.android.tools.r8.DexIndexedConsumer;
import com.android.tools.r8.GlobalSyntheticsConsumer;
import com.android.tools.r8.ProgramConsumer;
import com.android.tools.r8.internal.C1031a3;
import com.android.tools.r8.internal.C1264cm;
import com.android.tools.r8.internal.YV;
import defpackage.x0g;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3500i {
    public static final /* synthetic */ boolean a = true;

    public static GlobalSyntheticsConsumer a(boolean z, Path path, GlobalSyntheticsConsumer globalSyntheticsConsumer, ProgramConsumer programConsumer) {
        if (!a && path != null && globalSyntheticsConsumer != null) {
            x1f.a();
            return null;
        }
        if (!z) {
            return null;
        }
        if (globalSyntheticsConsumer != null) {
            return globalSyntheticsConsumer;
        }
        if (path == null) {
            return null;
        }
        YV c1264cm = Files.isDirectory(path, new LinkOption[0]) ? new C1264cm(path) : new C1031a3(path);
        c1264cm.open();
        if (programConsumer instanceof DexIndexedConsumer) {
            return new C3497f(c1264cm);
        }
        if (programConsumer instanceof DexFilePerClassFileConsumer) {
            return new C3498g(c1264cm);
        }
        if (programConsumer instanceof ClassFileConsumer) {
            return new C3499h(c1264cm);
        }
        x0g.a("Unexpected program consumer type");
        return null;
    }
}
