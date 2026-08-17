package com.android.tools.r8.relocator;

import com.android.tools.r8.CompilationFailedException;
import com.android.tools.r8.Version;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class a {
    public static void a(String[] strArr) throws CompilationFailedException {
        RelocatorCommand relocatorCommandBuild = RelocatorCommand.Builder.parse(strArr, com.android.tools.r8.origin.a.f).build();
        if (relocatorCommandBuild.isPrintHelp()) {
            System.out.println(RelocatorCommand.j);
            return;
        }
        if (!relocatorCommandBuild.isPrintVersion()) {
            Relocator.run(relocatorCommandBuild);
            return;
        }
        System.out.println("Relocator " + Version.getVersionString());
    }
}
