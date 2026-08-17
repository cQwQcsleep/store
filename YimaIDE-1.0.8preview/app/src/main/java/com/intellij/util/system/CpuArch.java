package com.intellij.util.system;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum CpuArch {
    X86(32),
    X86_64(64),
    ARM32(32),
    ARM64(64),
    OTHER(0),
    UNKNOWN(0);

    public static final CpuArch CURRENT = fromString(System.getProperty("os.arch"));
    public final int width;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/system/CpuArch", "fromString"));
    }

    CpuArch(int i) {
        if (i == 0) {
            try {
                i = Integer.parseInt(System.getProperty("sun.arch.data.model", "32"));
            } catch (NumberFormatException unused) {
            }
        }
        this.width = i;
    }

    public static CpuArch fromString(String str) {
        if ("x86_64".equals(str) || "amd64".equals(str)) {
            CpuArch cpuArch = X86_64;
            if (cpuArch == null) {
                $$$reportNull$$$0(0);
            }
            return cpuArch;
        }
        if ("i386".equals(str) || "x86".equals(str)) {
            CpuArch cpuArch2 = X86;
            if (cpuArch2 == null) {
                $$$reportNull$$$0(1);
            }
            return cpuArch2;
        }
        if ("aarch64".equals(str) || "arm64".equals(str)) {
            CpuArch cpuArch3 = ARM64;
            if (cpuArch3 == null) {
                $$$reportNull$$$0(2);
            }
            return cpuArch3;
        }
        CpuArch cpuArch4 = (str == null || str.trim().isEmpty()) ? UNKNOWN : OTHER;
        if (cpuArch4 == null) {
            $$$reportNull$$$0(3);
        }
        return cpuArch4;
    }

    public static boolean is32Bit() {
        return CURRENT.width == 32;
    }

    public static boolean isArm64() {
        return CURRENT == ARM64;
    }

    public static boolean isIntel64() {
        return CURRENT == X86_64;
    }
}
