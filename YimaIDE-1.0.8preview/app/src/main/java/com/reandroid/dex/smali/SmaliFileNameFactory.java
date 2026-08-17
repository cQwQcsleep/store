package com.reandroid.dex.smali;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliFileNameFactory {
    public static final int DEFAULT_MAX_PATH_LENGTH;
    public static final int DEFAULT_MAX_PATH_SEGMENTS;
    public static final String DEFAULT_RENAMED_PREFIX;
    public static final SmaliFileNameFactory INSTANCE;
    public static final boolean WINDOWS_OS;
    private boolean caseSensitive;
    private int maxFilePathLength;
    private int maxPathSegments;
    private String renamedPrefix;

    static {
        boolean zContains;
        try {
            zContains = System.getProperty("os.name", "").toLowerCase().contains("windows");
        } catch (Throwable unused) {
            zContains = false;
        }
        WINDOWS_OS = zContains;
        int iOf = ObjectsUtil.of(200);
        DEFAULT_MAX_PATH_LENGTH = iOf;
        int iOf2 = ObjectsUtil.of(25);
        DEFAULT_MAX_PATH_SEGMENTS = iOf2;
        String strOf = ObjectsUtil.of("renamed_");
        DEFAULT_RENAMED_PREFIX = strOf;
        INSTANCE = new SmaliFileNameFactory(zContains, iOf, iOf2, strOf);
    }

    private SmaliFileNameFactory(boolean z, int i, int i2, String str) {
        this.caseSensitive = z;
        this.maxFilePathLength = i;
        this.maxPathSegments = i2;
        this.renamedPrefix = str;
    }

    private String createHashString(String str) {
        String hexString = Long.toHexString(longHashCode(str));
        String renamedPrefix = getRenamedPrefix();
        StringBuilder sb = new StringBuilder(renamedPrefix.length() + 16);
        sb.append(renamedPrefix);
        if (getMaxFilePathLength() > 32) {
            int length = 16 - hexString.length();
            for (int i = 0; i < length; i++) {
                sb.append('0');
            }
        }
        sb.append(hexString);
        return sb.toString();
    }

    private String fixPathLength(String str) {
        int length = str.length();
        int maxFilePathLength = getMaxFilePathLength();
        int maxPathSegments = getMaxPathSegments();
        char c = File.separatorChar;
        int iCountChar = StringsUtil.countChar(str, c);
        if (length <= maxFilePathLength && iCountChar <= maxPathSegments) {
            return str;
        }
        int iLastIndexOf = str.lastIndexOf(c);
        return ((iLastIndexOf > maxFilePathLength / 3 || iLastIndexOf < 0 || iCountChar > maxPathSegments) ? "" : str.substring(0, iLastIndexOf + 1)) + createHashString(str);
    }

    private File generateUniqueFile(File file, TypeKey typeKey) {
        String rawPath = toRawPath(typeKey);
        File fileWithSuffix = file;
        for (int i = 0; i < 50000; i++) {
            fileWithSuffix = toFileWithSuffix(file, rawPath, i);
            if (!fileWithSuffix.exists()) {
                return fileWithSuffix;
            }
        }
        return fileWithSuffix;
    }

    private static long longHashCode(String str) {
        int length = str.length();
        long jCharAt = 1;
        for (int i = 0; i < length; i++) {
            jCharAt = (jCharAt * 31) + ((long) str.charAt(i));
        }
        return jCharAt < 0 ? jCharAt & 9151314442816847871L : jCharAt;
    }

    public static SmaliFileNameFactory newInstance() {
        SmaliFileNameFactory smaliFileNameFactory = INSTANCE;
        return new SmaliFileNameFactory(smaliFileNameFactory.isCaseSensitive(), smaliFileNameFactory.getMaxFilePathLength(), smaliFileNameFactory.getMaxPathSegments(), smaliFileNameFactory.getRenamedPrefix());
    }

    private static TypeKey readClassType(File file) {
        try {
            SmaliReader smaliReaderOf = SmaliReader.of(file);
            smaliReaderOf.skipWhitespacesOrComment();
            SmaliParseException.expect(smaliReaderOf, SmaliDirective.CLASS);
            AccessFlag.parse(smaliReaderOf);
            smaliReaderOf.skipWhitespacesOrComment();
            return TypeKey.read(smaliReaderOf);
        } catch (IOException unused) {
            return null;
        }
    }

    private boolean shouldGenerateUniqueFile(File file, TypeKey typeKey) {
        if (!isCaseSensitive() || !file.exists()) {
            return false;
        }
        TypeKey classType = readClassType(file);
        return classType == null || !classType.equals(typeKey);
    }

    private File toFileWithSuffix(File file, String str, int i) {
        return new File(file, str + "_" + i + ".smali");
    }

    private String toRawPath(TypeKey typeKey) {
        String typeName = typeKey.getTypeName();
        return fixPathLength(typeName.substring(1, typeName.length() - 1).replace('/', File.separatorChar));
    }

    public int getMaxFilePathLength() {
        return this.maxFilePathLength;
    }

    public int getMaxPathSegments() {
        return this.maxPathSegments;
    }

    public String getRenamedPrefix() {
        String str = this.renamedPrefix;
        if (str != null) {
            return str;
        }
        this.renamedPrefix = "";
        return "";
    }

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    public void setCaseSensitive(boolean z) {
        this.caseSensitive = z;
    }

    public void setMaxFilePathLength(int i) {
        if (i >= 2) {
            this.maxFilePathLength = i;
        } else {
            qf1.a("Maximum file path length too small: ", i);
        }
    }

    public void setMaxPathSegments(int i) {
        if (i >= 2) {
            this.maxPathSegments = i;
        } else {
            qf1.a("Maximum file path segments too small: ", i);
        }
    }

    public void setRenamedPrefix(String str) {
        this.renamedPrefix = str;
    }

    public File toFile(File file, TypeKey typeKey) {
        File file2 = new File(file, toPath(typeKey));
        return shouldGenerateUniqueFile(file2, typeKey) ? generateUniqueFile(file, typeKey) : file2;
    }

    public String toPath(TypeKey typeKey) {
        return toRawPath(typeKey) + ".smali";
    }

    public SmaliFileNameFactory() {
        this(WINDOWS_OS, DEFAULT_MAX_PATH_LENGTH, DEFAULT_MAX_PATH_SEGMENTS, DEFAULT_RENAMED_PREFIX);
    }
}
