package org.jetbrains.kotlin.konan.target;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.konan.TempFiles;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005\u0012\n\u0010\b\u001a\u00060\u0006j\u0002`\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\b\u001a\u00060\u0006j\u0002`\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/LinkerArguments;", "", "tempFiles", "Lorg/jetbrains/kotlin/konan/TempFiles;", "objectFiles", "", "", "Lorg/jetbrains/kotlin/konan/target/ObjectFile;", "executable", "Lorg/jetbrains/kotlin/konan/target/ExecutableFile;", "staticLibraries", "dynamicLibraries", "linkerArgs", "optimize", "", "debug", "kind", "Lorg/jetbrains/kotlin/konan/target/LinkerOutputKind;", "outputDsymBundle", "sanitizer", "Lorg/jetbrains/kotlin/konan/target/SanitizerKind;", "(Lorg/jetbrains/kotlin/konan/TempFiles;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZZLorg/jetbrains/kotlin/konan/target/LinkerOutputKind;Ljava/lang/String;Lorg/jetbrains/kotlin/konan/target/SanitizerKind;)V", "getDebug", "()Z", "getDynamicLibraries", "()Ljava/util/List;", "getExecutable", "()Ljava/lang/String;", "getKind", "()Lorg/jetbrains/kotlin/konan/target/LinkerOutputKind;", "getLinkerArgs", "getObjectFiles", "getOptimize", "getOutputDsymBundle", "getSanitizer", "()Lorg/jetbrains/kotlin/konan/target/SanitizerKind;", "getStaticLibraries", "getTempFiles", "()Lorg/jetbrains/kotlin/konan/TempFiles;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LinkerArguments {
    private final boolean debug;
    private final List<String> dynamicLibraries;
    private final String executable;
    private final LinkerOutputKind kind;
    private final List<String> linkerArgs;
    private final List<String> objectFiles;
    private final boolean optimize;
    private final String outputDsymBundle;
    private final SanitizerKind sanitizer;
    private final List<String> staticLibraries;
    private final TempFiles tempFiles;

    public LinkerArguments(TempFiles tempFiles, List<String> list, String str, List<String> list2, List<String> list3, List<String> list4, boolean z, boolean z2, LinkerOutputKind linkerOutputKind, String str2, SanitizerKind sanitizerKind) {
        tempFiles.getClass();
        list.getClass();
        str.getClass();
        list2.getClass();
        list3.getClass();
        list4.getClass();
        linkerOutputKind.getClass();
        str2.getClass();
        this.tempFiles = tempFiles;
        this.objectFiles = list;
        this.executable = str;
        this.staticLibraries = list2;
        this.dynamicLibraries = list3;
        this.linkerArgs = list4;
        this.optimize = z;
        this.debug = z2;
        this.kind = linkerOutputKind;
        this.outputDsymBundle = str2;
        this.sanitizer = sanitizerKind;
    }

    public final boolean getDebug() {
        return this.debug;
    }

    public final List<String> getDynamicLibraries() {
        return this.dynamicLibraries;
    }

    public final String getExecutable() {
        return this.executable;
    }

    public final LinkerOutputKind getKind() {
        return this.kind;
    }

    public final List<String> getLinkerArgs() {
        return this.linkerArgs;
    }

    public final List<String> getObjectFiles() {
        return this.objectFiles;
    }

    public final boolean getOptimize() {
        return this.optimize;
    }

    public final String getOutputDsymBundle() {
        return this.outputDsymBundle;
    }

    public final SanitizerKind getSanitizer() {
        return this.sanitizer;
    }

    public final List<String> getStaticLibraries() {
        return this.staticLibraries;
    }

    public final TempFiles getTempFiles() {
        return this.tempFiles;
    }

    public /* synthetic */ LinkerArguments(TempFiles tempFiles, List list, String str, List list2, List list3, List list4, boolean z, boolean z2, LinkerOutputKind linkerOutputKind, String str2, SanitizerKind sanitizerKind, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(tempFiles, list, str, list2, list3, list4, z, z2, linkerOutputKind, str2, (i & BinaryVersion.MAX_LENGTH) != 0 ? null : sanitizerKind);
    }
}
