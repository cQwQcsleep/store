package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.DataResource;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipFile;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Sp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0831Sp {
    public static final boolean a = System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik");
    public static final /* synthetic */ boolean b = true;

    public static void a(Path path, OutputStream outputStream, ByteDataView byteDataView) throws Throwable {
        C0378Bd c0378Bd = new C0378Bd(C0378Bd.c);
        try {
            OpenOption[] openOptionArr = {StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE};
            if (path != null) {
                outputStream = Files.newOutputStream(path, openOptionArr);
                c0378Bd.a(outputStream);
            } else if (!b && outputStream == null) {
                throw new AssertionError();
            }
            outputStream.write(byteDataView.getBuffer(), byteDataView.getOffset(), byteDataView.getLength());
            c0378Bd.close();
        } catch (Throwable th) {
            try {
                c0378Bd.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String b(String str) {
        char c = File.separatorChar;
        if (c == '/') {
            return str.replace('\\', DataResource.SEPARATOR);
        }
        if (b || c == '\\') {
            return str.replace(DataResource.SEPARATOR, '\\');
        }
        x1f.a();
        return null;
    }

    public static boolean c(Path path) {
        String strI = Wf0.i(path.getFileName().toString());
        if (strI.startsWith("classes") && strI.endsWith(".dex")) {
            String strA = AbstractC0005a.a(4, 7, strI);
            if (strA.isEmpty()) {
                return true;
            }
            char cCharAt = strA.charAt(0);
            if (strA.length() == 1) {
                return '2' <= cCharAt && cCharAt <= '9';
            }
            if (cCharAt >= '1' && '9' >= cCharAt) {
                for (int i = 1; i < strA.length(); i++) {
                    char cCharAt2 = strA.charAt(i);
                    if (cCharAt2 < '0' || '9' < cCharAt2) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean d(Path path) {
        return Wf0.i(path.getFileName().toString()).endsWith(".dex");
    }

    public static boolean e(Path path) {
        return Wf0.i(path.getFileName().toString()).endsWith(".jar");
    }

    public static boolean f(Path path) {
        return Wf0.i(path.getFileName().toString()).endsWith(".java");
    }

    public static boolean g(Path path) {
        return Wf0.i(path.getFileName().toString()).endsWith(".zip");
    }

    public static List<String> h(Path path) throws IOException {
        return Files.readAllLines(path);
    }

    public static byte[] i(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    public static boolean b(Path path) {
        return a(path.getFileName().toString());
    }

    public static boolean a(Path path) {
        String strI = Wf0.i(path.getFileName().toString());
        return strI.endsWith(".apk") || strI.endsWith(".jar") || strI.endsWith(".zip") || strI.endsWith(".aar");
    }

    public static String a(Path path, Charset charset) throws IOException {
        return new String(Files.readAllBytes(path), charset);
    }

    public static boolean a(String str) {
        String strI = Wf0.i(str);
        if (strI.equals("module-info.class")) {
            return false;
        }
        return strI.endsWith(".class");
    }

    public static Path a(Path path, List<String> list) throws IOException {
        Files.write(path, list, new OpenOption[0]);
        return path;
    }

    public static Path a(Path path, String... strArr) throws IOException {
        Files.write(path, Arrays.asList(strArr), new OpenOption[0]);
        return path;
    }

    public static void a(C2742u50 c2742u50, Path path) {
        if (path == null || g(path) || e(path)) {
            return;
        }
        if (Files.exists(path, new LinkOption[0]) && Files.isDirectory(path, new LinkOption[0])) {
            return;
        }
        c2742u50.error(new StringDiagnostic("Invalid output: " + path + "\nOutput must be a .zip or .jar archive or an existing directory"));
    }

    public static void a(Path path, OutputStream outputStream, byte[] bArr) throws Throwable {
        a(path, outputStream, ByteDataView.of(bArr));
    }

    public static ZipFile a(File file, Charset charset) {
        if (!a) {
            return new ZipFile(file, charset);
        }
        if (Charset.defaultCharset() == StandardCharsets.UTF_8) {
            return new ZipFile(file);
        }
        ib0.a("R8 can run on dex only with UTF_8 as the default charset, but the charset used is ", Charset.defaultCharset());
        return null;
    }
}
