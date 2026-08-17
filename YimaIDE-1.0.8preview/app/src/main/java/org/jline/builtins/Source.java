package org.jline.builtins;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface Source {

    public static class PathSource implements Source {
        final String name;
        final Path path;

        public PathSource(Path path, String str) {
            Objects.requireNonNull(path);
            this.path = path;
            this.name = str;
        }

        public InputStream read() throws IOException {
            return Files.newInputStream(this.path, new OpenOption[0]);
        }
    }
}
