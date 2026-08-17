package org.jetbrains.kotlin.utils;

import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.util.containers.CollectionFactory;
import com.intellij.util.containers.ContainerUtil;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class JavaSdkUtil {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "home";
        } else {
            objArr[0] = "org/jetbrains/kotlin/utils/JavaSdkUtil";
        }
        if (i != 1) {
            objArr[1] = "org/jetbrains/kotlin/utils/JavaSdkUtil";
        } else {
            objArr[1] = "getJdkClassesRoots";
        }
        if (i != 1) {
            objArr[2] = "getJdkClassesRoots";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    private static String getCanonicalPath(Path path) {
        try {
            return path.toRealPath(new LinkOption[0]).toString();
        } catch (IOException unused) {
            return null;
        }
    }

    public static List<Path> getJdkClassesRoots(Path path, boolean z) {
        Path[] pathArr;
        Path pathResolve;
        String canonicalPath;
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        Path fileName = path.getFileName();
        if (fileName != null && "Home".equals(fileName.toString()) && Files.exists(path.resolve("../Classes/classes.jar"), new LinkOption[0])) {
            Path pathResolve2 = path.resolve("lib");
            pathArr = new Path[]{pathResolve2.resolve("endorsed"), pathResolve2, path.resolveSibling("Classes"), pathResolve2.resolve("ext")};
        } else if (Files.exists(path.resolve("lib/jrt-fs.jar"), new LinkOption[0])) {
            pathArr = new Path[0];
        } else {
            Path pathResolve3 = path.resolve(z ? "lib" : "jre/lib");
            pathArr = new Path[]{pathResolve3.resolve("endorsed"), pathResolve3, pathResolve3.resolve("ext")};
        }
        ArrayList arrayList = new ArrayList();
        if (Registry.is("project.structure.add.tools.jar.to.new.jdk", false)) {
            Path pathResolve4 = path.resolve("lib/tools.jar");
            if (Files.isRegularFile(pathResolve4, new LinkOption[0])) {
                arrayList.add(pathResolve4);
            }
        }
        Set setCreateFilePathSet = CollectionFactory.createFilePathSet();
        for (Path path2 : pathArr) {
            if (path2 != null && Files.isDirectory(path2, new LinkOption[0])) {
                try {
                    DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path2, "*.jar");
                    try {
                        for (Path path3 : directoryStreamNewDirectoryStream) {
                            String string = path3.getFileName().toString();
                            if (!string.equals("alt-rt.jar") && !string.equals("alt-string.jar") && (canonicalPath = getCanonicalPath(path3)) != null && setCreateFilePathSet.add(canonicalPath)) {
                                arrayList.add(path3);
                            }
                        }
                        directoryStreamNewDirectoryStream.close();
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            if (directoryStreamNewDirectoryStream != null) {
                                try {
                                    directoryStreamNewDirectoryStream.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                            }
                            throw th2;
                        }
                    }
                } catch (IOException unused) {
                    continue;
                }
            }
        }
        if (ContainerUtil.exists(arrayList, new Condition() { // from class: hl7
            public final boolean value(Object obj) {
                return ((Path) obj).getFileName().toString().startsWith("ibm");
            }
        })) {
            if (z) {
                pathResolve = path;
            } else {
                try {
                    pathResolve = path.resolve("jre");
                } catch (IOException unused2) {
                }
            }
            Stream<Path> streamWalk = Files.walk(pathResolve, new FileVisitOption[0]);
            try {
                streamWalk.filter(new Predicate() { // from class: il7
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ((Path) obj).getFileName().toString().equals("vm.jar");
                    }
                }).findFirst().ifPresent(new j4a(arrayList));
                streamWalk.close();
            } catch (Throwable th4) {
                try {
                    throw th4;
                } catch (Throwable th5) {
                    if (streamWalk != null) {
                        try {
                            streamWalk.close();
                        } catch (Throwable th6) {
                            th4.addSuppressed(th6);
                        }
                    }
                    throw th5;
                }
            }
        }
        Path pathResolve5 = path.resolve("lib/classes.zip");
        if (Files.isRegularFile(pathResolve5, new LinkOption[0])) {
            arrayList.add(pathResolve5);
        }
        if (arrayList.isEmpty()) {
            Path pathResolve6 = path.resolve("classes");
            if (Files.isDirectory(pathResolve6, new LinkOption[0])) {
                arrayList.add(pathResolve6);
            }
        }
        return arrayList;
    }
}
