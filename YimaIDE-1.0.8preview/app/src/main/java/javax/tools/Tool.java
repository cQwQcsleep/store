package javax.tools;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import javax.lang.model.SourceVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Tool {
    Set<SourceVersion> getSourceVersions();

    default String name() {
        return "";
    }

    int run(InputStream inputStream, OutputStream outputStream, OutputStream outputStream2, String... strArr);
}
