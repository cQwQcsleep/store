package org.jetbrains.kotlin.konan.target;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nj\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/Family;", "", "exeSuffix", "", "dynamicPrefix", "dynamicSuffix", "staticPrefix", "staticSuffix", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDynamicPrefix", "()Ljava/lang/String;", "getDynamicSuffix", "getExeSuffix", "isAppleFamily", "", "()Z", "getStaticPrefix", "getStaticSuffix", "OSX", "IOS", "TVOS", "WATCHOS", "LINUX", "MINGW", "ANDROID", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public enum Family {
    OSX("kexe", "lib", "dylib", "lib", "a"),
    IOS("kexe", "lib", "dylib", "lib", "a"),
    TVOS("kexe", "lib", "dylib", "lib", "a"),
    WATCHOS("kexe", "lib", "dylib", "lib", "a"),
    LINUX("kexe", "lib", "so", "lib", "a"),
    MINGW("exe", "", "dll", "lib", "a"),
    ANDROID("kexe", "lib", "so", "lib", "a");

    private final String dynamicPrefix;
    private final String dynamicSuffix;
    private final String exeSuffix;
    private final String staticPrefix;
    private final String staticSuffix;

    Family(String str, String str2, String str3, String str4, String str5) {
        this.exeSuffix = str;
        this.dynamicPrefix = str2;
        this.dynamicSuffix = str3;
        this.staticPrefix = str4;
        this.staticSuffix = str5;
    }

    public final String getDynamicPrefix() {
        return this.dynamicPrefix;
    }

    public final String getDynamicSuffix() {
        return this.dynamicSuffix;
    }

    public final String getExeSuffix() {
        return this.exeSuffix;
    }

    public final String getStaticPrefix() {
        return this.staticPrefix;
    }

    public final String getStaticSuffix() {
        return this.staticSuffix;
    }

    public final boolean isAppleFamily() {
        return this == OSX || this == IOS || this == TVOS || this == WATCHOS;
    }
}
