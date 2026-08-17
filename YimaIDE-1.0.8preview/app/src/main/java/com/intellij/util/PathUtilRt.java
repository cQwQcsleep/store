package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.LoggerRt;
import com.intellij.openapi.util.SystemInfoRt;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PathUtilRt {
    private static final Set<String> WINDOWS_RESERVED_NAMES = new HashSet(Arrays.asList("CON", "PRN", "AUX", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"));
    private static final Charset FS_CHARSET = fsCharset();

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 com.intellij.util.PathUtilRt$Platform, still in use, count: 1, list:
      (r0v0 com.intellij.util.PathUtilRt$Platform) from ?: TERNARY null = ((wrap boolean:0x001a: SGET  A[WRAPPED] com.intellij.openapi.util.SystemInfoRt.isWindows boolean) == true) ? (r1v1 com.intellij.util.PathUtilRt$Platform) : (r0v0 com.intellij.util.PathUtilRt$Platform)
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class Platform {
        UNIX,
        WINDOWS;

        public static final Platform CURRENT;

        static {
            CURRENT = SystemInfoRt.isWindows ? new Platform() : new Platform();
        }

        private Platform() {
            super(str, i);
        }

        public static Platform valueOf(String str) {
            return (Platform) Enum.valueOf(Platform.class, str);
        }

        public static Platform[] values() {
            return (Platform[]) $VALUES.clone();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 1:
            case 2:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 3:
            case 5:
            case 11:
            default:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                i2 = 3;
                break;
            case 3:
            case 5:
            case 11:
            default:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 2:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 13:
                objArr[0] = "path";
                break;
            case 3:
            case 5:
            case 11:
            default:
                objArr[0] = "com/intellij/util/PathUtilRt";
                break;
            case 9:
            case 10:
                objArr[0] = "text";
                break;
            case 12:
                objArr[0] = "fileName";
                break;
            case 14:
                objArr[0] = "s";
                break;
            case 15:
                objArr[0] = "name";
                break;
            case 16:
                objArr[0] = "os";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                objArr[1] = "com/intellij/util/PathUtilRt";
                break;
            case 3:
                objArr[1] = "getParentPath";
                break;
            case 5:
                objArr[1] = "getParentPathSequence";
                break;
            case 11:
                objArr[1] = "suggestFileName";
                break;
            default:
                objArr[1] = "getFileName";
                break;
        }
        switch (i) {
            case 1:
                objArr[2] = "lastNonSeparatorIndex";
                break;
            case 2:
                objArr[2] = "getParentPath";
                break;
            case 4:
                objArr[2] = "getParentPathSequence";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "getParentPathEndOffset";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "isWindowsUNCRoot";
                break;
            case 8:
                objArr[2] = "hasFileSeparatorsOrNavigatableDots";
                break;
            case 9:
            case 10:
                objArr[2] = "suggestFileName";
                break;
            case 12:
            case 15:
            case 16:
                objArr[2] = "isValidFileName";
                break;
            case 13:
                objArr[2] = "startsWithSeparatorSeparator";
                break;
            case 14:
                objArr[2] = "lastSeparatorIndex";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 1:
            case 2:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                throw new IllegalArgumentException(str2);
            case 3:
            case 5:
            case 11:
            default:
                throw new IllegalStateException(str2);
        }
    }

    private PathUtilRt() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0008, code lost:
    
        r0 = java.lang.System.getProperty("sun.jnu.encoding");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Charset fsCharset() {
        String property;
        if (SystemInfoRt.isWindows || SystemInfoRt.isMac || property == null) {
            return null;
        }
        try {
            return Charset.forName(property);
        } catch (Exception e) {
            LoggerRt.getInstance(PathUtilRt.class).warn("unknown JNU charset: ".concat(property), e);
            return null;
        }
    }

    public static String getFileName(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        int iLastNonSeparatorIndex = lastNonSeparatorIndex(str);
        int iLastSeparatorIndex = lastSeparatorIndex(str, iLastNonSeparatorIndex);
        if (isWindowsUNCRoot(str, iLastSeparatorIndex)) {
            iLastSeparatorIndex = -1;
        }
        return str.substring(iLastSeparatorIndex + 1, iLastNonSeparatorIndex + 1);
    }

    public static String getParentPath(String str) {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        int parentPathEndOffset = getParentPathEndOffset(str);
        return parentPathEndOffset == 0 ? "" : str.substring(0, parentPathEndOffset);
    }

    private static int getParentPathEndOffset(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(6);
        }
        if (charSequence.length() == 0) {
            return 0;
        }
        int iLastSeparatorIndex = lastSeparatorIndex(charSequence, charSequence.length() - 1);
        if (iLastSeparatorIndex == charSequence.length() - 1 && iLastSeparatorIndex >= 1) {
            iLastSeparatorIndex = lastSeparatorIndex(charSequence, iLastSeparatorIndex - 1);
        }
        if (iLastSeparatorIndex == -1 || iLastSeparatorIndex == 0 || isWindowsUNCRoot(charSequence, iLastSeparatorIndex)) {
            return 0;
        }
        return isSeparator(charSequence.charAt(iLastSeparatorIndex + (-1))) ? iLastSeparatorIndex - 1 : iLastSeparatorIndex;
    }

    private static boolean hasFileSeparatorsOrNavigatableDots(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            $$$reportNull$$$0(8);
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            char cCharAt = charSequence.charAt(i3);
            if (isSeparator(cCharAt)) {
                return true;
            }
            if (cCharAt == '.' && (i3 == 2 || (i3 == 3 && charSequence.charAt(2) == '.'))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSeparator(char c) {
        return c == '/' || c == '\\';
    }

    public static boolean isWindowsUNCRoot(CharSequence charSequence, int i) {
        if (charSequence == null) {
            $$$reportNull$$$0(7);
        }
        return Platform.CURRENT == Platform.WINDOWS && startsWithSeparatorSeparator(charSequence) && i >= 1 && !hasFileSeparatorsOrNavigatableDots(charSequence, 2, i);
    }

    private static int lastNonSeparatorIndex(String str) {
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            if (!isSeparator(str.charAt(length))) {
                return length;
            }
        }
        return -1;
    }

    public static int lastSeparatorIndex(CharSequence charSequence, int i) {
        if (charSequence == null) {
            $$$reportNull$$$0(14);
        }
        while (i >= 0) {
            if (isSeparator(charSequence.charAt(i))) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static boolean startsWithSeparatorSeparator(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(13);
        }
        return charSequence.length() > 1 && isSeparator(charSequence.charAt(0)) && charSequence.charAt(1) == charSequence.charAt(0);
    }
}
