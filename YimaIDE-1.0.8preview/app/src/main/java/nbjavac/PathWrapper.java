package nbjavac;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class PathWrapper {
    public static Path of(URI uri) {
        return new File(uri).toPath();
    }
}
