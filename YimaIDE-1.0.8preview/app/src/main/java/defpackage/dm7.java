package defpackage;

import java.nio.file.Path;
import java.nio.file.Paths;
import javax.tools.StandardJavaFileManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class dm7 implements StandardJavaFileManager.PathFactory {
    @Override // javax.tools.StandardJavaFileManager.PathFactory
    public final Path getPath(String str, String[] strArr) {
        return Paths.get(str, strArr);
    }
}
