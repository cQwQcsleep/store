package org.jline.builtins;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Commands {
    public static List<Path> findFiles(Path path, String str) throws IOException {
        String str2;
        Path path2;
        String strReplaceAll;
        if (str.startsWith("~")) {
            str = str.replace("~", System.getProperty("user.home"));
        }
        Path path3 = Paths.get("/", new String[0]);
        if (new File(str).isAbsolute()) {
            strReplaceAll = str.replaceAll("\\\\", "/").replaceAll("//", "/");
            if (strReplaceAll.contains("/")) {
                String strSubstring = strReplaceAll.substring(0, strReplaceAll.lastIndexOf("/") + 1);
                while (true) {
                    if (!strSubstring.contains("*") && !strSubstring.contains("?")) {
                        break;
                    }
                    strSubstring = strSubstring.substring(0, strSubstring.lastIndexOf("/"));
                }
                path2 = Paths.get(strSubstring.concat("/"), new String[0]);
            } else {
                path2 = path3;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            if (path.toString().length() == 0) {
                str2 = "";
            } else {
                str2 = path + "/";
            }
            sb.append(str2);
            sb.append(str);
            path2 = path;
            strReplaceAll = sb.toString().replaceAll("\\\\", "/").replaceAll("//", "/");
        }
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + strReplaceAll);
        Stream<Path> streamWalk = Files.walk(path2, new FileVisitOption[0]);
        try {
            Objects.requireNonNull(pathMatcher);
            List<Path> list = (List) streamWalk.filter(new k72(pathMatcher)).collect(Collectors.toList());
            streamWalk.close();
            return list;
        } catch (Throwable th) {
            if (streamWalk != null) {
                try {
                    streamWalk.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
