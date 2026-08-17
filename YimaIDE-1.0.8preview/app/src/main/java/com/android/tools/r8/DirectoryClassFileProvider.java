package com.android.tools.r8;

import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C0929Wj;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DirectoryClassFileProvider implements ClassFileResourceProvider {
    static final /* synthetic */ boolean b = true;
    private final Path a;

    private DirectoryClassFileProvider(Path path) {
        this.a = path;
    }

    private void a(Path path, HashSet hashSet) {
        File[] fileArrListFiles;
        File file = path.toFile();
        if (!file.exists() || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                a(file2.toPath(), hashSet);
            } else {
                Path pathRelativize = this.a.relativize(file2.toPath());
                if (C0831Sp.b(pathRelativize)) {
                    hashSet.add(C0929Wj.a(pathRelativize));
                }
            }
        }
    }

    public static ClassFileResourceProvider fromDirectory(Path path) {
        return new DirectoryClassFileProvider(path.toAbsolutePath());
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public /* bridge */ /* synthetic */ void finished(DiagnosticsHandler diagnosticsHandler) throws IOException {
        super.finished(diagnosticsHandler);
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public Set<String> getClassDescriptors() {
        HashSet hashSet = new HashSet();
        a(this.a, hashSet);
        return hashSet;
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public ProgramResource getProgramResource(String str) {
        if (!b && !C0929Wj.z(str)) {
            x1f.a();
            return null;
        }
        String strI = C0929Wj.i(str);
        Path pathResolve = this.a.resolve(strI + ".class");
        if (!Files.exists(pathResolve, new LinkOption[0]) || Files.isDirectory(pathResolve, new LinkOption[0])) {
            return null;
        }
        return ProgramResource.fromFile(ProgramResource.Kind.CF, pathResolve);
    }

    public Path getRoot() {
        return this.a;
    }
}
