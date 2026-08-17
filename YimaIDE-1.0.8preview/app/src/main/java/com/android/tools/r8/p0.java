package com.android.tools.r8;

import com.android.tools.r8.internal.Wf0;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class p0 {
    public static final /* synthetic */ boolean c = true;
    public final Path a;
    public final Path b;

    public p0(Path path, Path path2) {
        this.a = path;
        this.b = path2;
    }

    public static p0 a(String str) {
        List<String> listA = Wf0.a(str, File.pathSeparatorChar);
        Path path = null;
        if (listA.size() == 0 || listA.size() > 2) {
            w01.a("Feature input/output takes one or two paths.");
            return null;
        }
        String str2 = listA.get(0);
        Path path2 = !str2.isEmpty() ? Paths.get(str2, new String[0]) : null;
        if (listA.size() == 2) {
            if (!c && listA.get(1).length() <= 0) {
                x1f.a();
                return null;
            }
            path = Paths.get(listA.get(1), new String[0]);
        }
        return new p0(path2, path);
    }
}
