package org.jetbrains.kotlin.incremental.classpathDiff;

import java.io.Closeable;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\br\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eJF\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u000326\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\n0\u0006H&J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0004H&\u0082\u0001\u0002\u000f\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/DirectoryOrJarReader;", "Ljava/io/Closeable;", "getUnixStyleRelativePaths", "", "", "filter", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "unixStyleRelativePath", "", "isDirectory", "readBytes", "", "Companion", "Lorg/jetbrains/kotlin/incremental/classpathDiff/DirectoryReader;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/JarReader;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
interface DirectoryOrJarReader extends Closeable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/DirectoryOrJarReader$Companion;", "", "<init>", "()V", "create", "Lorg/jetbrains/kotlin/incremental/classpathDiff/DirectoryOrJarReader;", "directoryOrJar", "Ljava/io/File;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final DirectoryOrJarReader create(File directoryOrJar) {
            directoryOrJar.getClass();
            if (directoryOrJar.isDirectory()) {
                return new DirectoryReader(directoryOrJar);
            }
            if (directoryOrJar.isFile()) {
                String path = directoryOrJar.getPath();
                path.getClass();
                if (StringsKt.endsWith(path, ".jar", true)) {
                    return new JarReader(directoryOrJar);
                }
            }
            k2d.a("Check failed.");
            return null;
        }
    }

    List<String> getUnixStyleRelativePaths(Function2<? super String, ? super Boolean, Boolean> filter);

    byte[] readBytes(String unixStyleRelativePath);
}
