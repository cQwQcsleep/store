package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1333de0;
import com.android.tools.r8.internal.C1249ce0;
import com.android.tools.r8.startup.StartupProfileProvider;
import com.android.tools.r8.startup.diagnostic.MissingStartupProfileItemsDiagnostic;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ce0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1249ce0 {
    public static String a(C2752uB c2752uB, StartupProfileProvider startupProfileProvider) throws Exception {
        Vd0.a aVarA = Vd0.a(c2752uB, MissingStartupProfileItemsDiagnostic.a.b(), startupProfileProvider);
        aVarA.c = null;
        startupProfileProvider.getStartupProfile(aVarA);
        final StringBuilder sb = new StringBuilder();
        aVarA.build().a(new InterfaceC1936kh0() { // from class: dlg
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                C1249ce0.a(sb, (AbstractC1333de0) obj);
            }
        });
        return sb.toString();
    }

    public static StartupProfileProvider a(Path path) {
        return new C1166be0(path, C0822Sg.b());
    }

    public static void a(StringBuilder sb, AbstractC1333de0 abstractC1333de0) {
        abstractC1333de0.a(sb);
        sb.append('\n');
    }
}
