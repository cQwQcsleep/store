package com.intellij.util.lang;

import androidx.compose.compiler.plugins.kotlin.analysis.StabilityExternalClassNameMatchingKt;
import defpackage.j2d;
import defpackage.w01;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class JavaVersion implements Comparable<JavaVersion> {
    private static JavaVersion current;
    public final int build;
    public final boolean ea;
    public final int feature;
    public final int minor;
    public final int update;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3) ? 2 : 3];
        if (i == 1 || i == 2 || i == 3) {
            objArr[0] = "com/intellij/util/lang/JavaVersion";
        } else if (i != 4) {
            objArr[0] = "o";
        } else {
            objArr[0] = "versionString";
        }
        if (i == 1) {
            objArr[1] = "toFeatureString";
        } else if (i == 2) {
            objArr[1] = "toFeatureMinorUpdateString";
        } else if (i != 3) {
            objArr[1] = "com/intellij/util/lang/JavaVersion";
        } else {
            objArr[1] = "current";
        }
        if (i != 1 && i != 2 && i != 3) {
            if (i != 4) {
                objArr[2] = "compareTo";
            } else {
                objArr[2] = "parse";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private JavaVersion(int i, int i2, int i3, int i4, boolean z) {
        this.feature = i;
        this.minor = i2;
        this.update = i3;
        this.build = i4;
        this.ea = z;
    }

    public static JavaVersion compose(int i, int i2, int i3, int i4, boolean z) throws IllegalArgumentException {
        if (i < 0) {
            j2d.a();
            return null;
        }
        if (i2 < 0) {
            j2d.a();
            return null;
        }
        if (i3 < 0) {
            j2d.a();
            return null;
        }
        if (i4 >= 0) {
            return new JavaVersion(i, i2, i3, i4, z);
        }
        j2d.a();
        return null;
    }

    public static JavaVersion current() {
        if (current == null) {
            JavaVersion javaVersion = parse(System.getProperty("java.version"));
            JavaVersion javaVersionRtVersion = rtVersion();
            if (javaVersionRtVersion == null) {
                try {
                    javaVersionRtVersion = parse(System.getProperty("java.runtime.version"));
                } catch (Throwable unused) {
                }
            }
            if (javaVersionRtVersion != null && javaVersionRtVersion.feature == javaVersion.feature && javaVersionRtVersion.minor == javaVersion.minor) {
                javaVersion = javaVersionRtVersion;
            }
            current = javaVersion;
        }
        JavaVersion javaVersion2 = current;
        if (javaVersion2 == null) {
            $$$reportNull$$$0(3);
        }
        return javaVersion2;
    }

    private String formatVersionTo(boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        int i = this.feature;
        if (i > 8) {
            sb.append(i);
            if (!z) {
                if (this.minor > 0 || this.update > 0) {
                    sb.append(StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR);
                    sb.append(this.minor);
                }
                if (this.update > 0) {
                    sb.append(StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR);
                    sb.append(this.update);
                }
                if (!z2) {
                    if (this.ea) {
                        sb.append("-ea");
                    }
                    if (this.build > 0) {
                        sb.append('+');
                        sb.append(this.build);
                    }
                }
            }
        } else {
            sb.append("1.");
            sb.append(this.feature);
            if (!z) {
                if (this.minor > 0 || this.update > 0 || this.ea || this.build > 0) {
                    sb.append(StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR);
                    sb.append(this.minor);
                }
                if (this.update > 0) {
                    sb.append('_');
                    sb.append(this.update);
                }
                if (!z2) {
                    if (this.ea) {
                        sb.append("-ea");
                    }
                    if (this.build > 0) {
                        sb.append("-b");
                        sb.append(this.build);
                    }
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static JavaVersion parse(String str) throws IllegalArgumentException {
        int i;
        int i2;
        int i3;
        int i4;
        boolean z;
        int i5;
        int i6;
        int i7;
        boolean z2;
        boolean z3;
        int iIndexOf;
        int i8 = 4;
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        String strTrim = str.trim();
        HashMap map = new HashMap();
        map.put("Runtime Environment", "(build ");
        map.put("OpenJ9", "version ");
        map.put("GraalVM", "Java ");
        for (String str2 : map.keySet()) {
            if (strTrim.contains(str2) && (iIndexOf = strTrim.indexOf((String) map.get(str2))) > 0) {
                strTrim = strTrim.substring(iIndexOf);
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int length = strTrim.length();
        int i9 = 0;
        int i10 = 0;
        boolean z4 = false;
        while (i10 < length) {
            int i11 = i10;
            while (i11 < length && Character.isDigit(strTrim.charAt(i11)) == z4) {
                i11++;
            }
            (z4 ? arrayList : arrayList2).add(strTrim.substring(i10, i11));
            z4 = !z4;
            i10 = i11;
        }
        if (!arrayList.isEmpty() && !arrayList2.isEmpty()) {
            try {
                int i12 = Integer.parseInt((String) arrayList.get(0));
                boolean z5 = true;
                if (i12 >= 5 && i12 < 50) {
                    int i13 = 1;
                    while (i13 < arrayList2.size() && ".".equals(arrayList2.get(i13))) {
                        i13++;
                    }
                    if (i13 <= 1 || arrayList.size() <= 2) {
                        i5 = 0;
                        i6 = 0;
                    } else {
                        i5 = Integer.parseInt((String) arrayList.get(1));
                        i6 = Integer.parseInt((String) arrayList.get(2));
                    }
                    if (i13 < arrayList2.size()) {
                        String str3 = (String) arrayList2.get(i13);
                        if (str3 == null || str3.isEmpty() || str3.charAt(0) != '-') {
                            z3 = false;
                        } else {
                            z3 = startsWithWord(str3, "-ea") || startsWithWord(str3, "-internal");
                            if (i13 < arrayList.size() && str3.charAt(str3.length() - 1) == '+') {
                                i9 = Integer.parseInt((String) arrayList.get(i13));
                            }
                            i13++;
                        }
                        if (i9 == 0 && i13 < arrayList2.size() && i13 < arrayList.size() && "+".equals(arrayList2.get(i13))) {
                            i9 = Integer.parseInt((String) arrayList.get(i13));
                        }
                        z2 = z3;
                        i7 = i9;
                    } else {
                        i7 = 0;
                        z2 = false;
                    }
                    return new JavaVersion(i12, i5, i6, i7, z2);
                }
                if (i12 == 1 && arrayList.size() > 1 && arrayList2.size() > 1 && ".".equals(arrayList2.get(1)) && (i = Integer.parseInt((String) arrayList.get(1))) <= 50) {
                    if (arrayList.size() > 2 && arrayList2.size() > 2 && ".".equals(arrayList2.get(2))) {
                        int i14 = Integer.parseInt((String) arrayList.get(2));
                        if (arrayList.size() <= 3 || arrayList2.size() <= 3 || !StabilityExternalClassNameMatchingKt.STABILITY_GENERIC_EXCLUDE.equals(arrayList2.get(3))) {
                            i2 = i14;
                            i3 = 0;
                        } else {
                            int i15 = Integer.parseInt((String) arrayList.get(3));
                            if (arrayList2.size() > 4) {
                                String str4 = (String) arrayList2.get(4);
                                if (str4 == null || str4.isEmpty() || str4.charAt(0) != '-' || (!startsWithWord(str4, "-ea") && !startsWithWord(str4, "-internal"))) {
                                    z5 = false;
                                }
                                while (i8 < arrayList2.size() && !((String) arrayList2.get(i8)).endsWith("-b")) {
                                    i8++;
                                }
                                i2 = i14;
                                i4 = i8 < arrayList.size() ? Integer.parseInt((String) arrayList.get(i8)) : 0;
                                i3 = i15;
                                z = z5;
                            } else {
                                i2 = i14;
                                i4 = 0;
                                z = 0;
                                i3 = i15;
                            }
                        }
                        return new JavaVersion(i, i2, i3, i4, z);
                    }
                    i2 = 0;
                    i3 = 0;
                    i4 = i3;
                    z = i4;
                    return new JavaVersion(i, i2, i3, i4, z);
                }
            } catch (NumberFormatException unused) {
            }
        }
        w01.a(str);
        return null;
    }

    private static JavaVersion rtVersion() {
        try {
            Object objInvoke = Runtime.class.getMethod("version", null).invoke(null, null);
            int iIntValue = ((Integer) objInvoke.getClass().getMethod("major", null).invoke(objInvoke, null)).intValue();
            int iIntValue2 = ((Integer) objInvoke.getClass().getMethod("minor", null).invoke(objInvoke, null)).intValue();
            int iIntValue3 = ((Integer) objInvoke.getClass().getMethod("security", null).invoke(objInvoke, null)).intValue();
            Object objInvoke2 = objInvoke.getClass().getMethod("build", null).invoke(objInvoke, null);
            int iIntValue4 = ((Integer) objInvoke2.getClass().getMethod("orElse", Object.class).invoke(objInvoke2, 0)).intValue();
            Object objInvoke3 = objInvoke.getClass().getMethod("pre", null).invoke(objInvoke, null);
            return new JavaVersion(iIntValue, iIntValue2, iIntValue3, iIntValue4, ((Boolean) objInvoke3.getClass().getMethod("isPresent", null).invoke(objInvoke3, null)).booleanValue());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean startsWithWord(String str, String str2) {
        if (str.startsWith(str2)) {
            return str.length() == str2.length() || !Character.isLetterOrDigit(str.charAt(str2.length()));
        }
        return false;
    }

    public static JavaVersion tryParse(String str) {
        if (str == null) {
            return null;
        }
        try {
            return parse(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(JavaVersion javaVersion) {
        if (javaVersion == null) {
            $$$reportNull$$$0(0);
        }
        int i = this.feature - javaVersion.feature;
        if (i != 0) {
            return i;
        }
        int i2 = this.minor - javaVersion.minor;
        if (i2 != 0) {
            return i2;
        }
        int i3 = this.update - javaVersion.update;
        if (i3 != 0) {
            return i3;
        }
        int i4 = this.build - javaVersion.build;
        return i4 != 0 ? i4 : (!this.ea ? 1 : 0) - (!javaVersion.ea ? 1 : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JavaVersion)) {
            return false;
        }
        JavaVersion javaVersion = (JavaVersion) obj;
        return this.feature == javaVersion.feature && this.minor == javaVersion.minor && this.update == javaVersion.update && this.build == javaVersion.build && this.ea == javaVersion.ea;
    }

    public int hashCode() {
        return (((((((this.feature * 31) + this.minor) * 31) + this.update) * 31) + this.build) * 31) + (this.ea ? 1231 : 1237);
    }

    public String toString() {
        return formatVersionTo(false, false);
    }

    public static JavaVersion compose(int i) {
        return compose(i, 0, 0, 0, false);
    }
}
