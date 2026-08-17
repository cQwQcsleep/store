package com.intellij.util;

import androidx.collection.ScatterMapKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SystemProperties {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 4 || i == 5 || i == 6 || i == 7) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 3 || i == 4 || i == 5 || i == 6 || i == 7) ? 3 : 2];
        if (i == 3 || i == 4 || i == 5 || i == 6 || i == 7) {
            objArr[0] = "key";
        } else {
            objArr[0] = "com/intellij/util/SystemProperties";
        }
        switch (i) {
            case 1:
                objArr[1] = "getUserName";
                break;
            case 2:
                objArr[1] = "getJavaHome";
                break;
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[1] = "com/intellij/util/SystemProperties";
                break;
            default:
                objArr[1] = "getUserHome";
                break;
        }
        if (i == 3) {
            objArr[2] = "getIntProperty";
        } else if (i == 4) {
            objArr[2] = "getLongProperty";
        } else if (i == 5) {
            objArr[2] = "getFloatProperty";
        } else if (i == 6) {
            objArr[2] = "getBooleanProperty";
        } else if (i == 7) {
            objArr[2] = "setProperty";
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 4 && i != 5 && i != 6 && i != 7) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    private SystemProperties() {
    }

    public static boolean getBooleanProperty(String str, boolean z) {
        if (str == null) {
            $$$reportNull$$$0(6);
        }
        String property = System.getProperty(str);
        return property != null ? Boolean.parseBoolean(property) : z;
    }

    public static float getFloatProperty(String str, float f) {
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        String property = System.getProperty(str);
        if (property != null) {
            try {
                return Float.parseFloat(property);
            } catch (NumberFormatException unused) {
            }
        }
        return f;
    }

    public static int getIntProperty(String str, int i) {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        String property = System.getProperty(str);
        if (property != null) {
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException unused) {
            }
        }
        return i;
    }

    public static String getJavaHome() {
        String property = System.getProperty("java.home");
        if (property == null) {
            $$$reportNull$$$0(2);
        }
        return property;
    }

    public static long getLongProperty(String str, long j) {
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        String property = System.getProperty(str);
        if (property != null) {
            try {
                return Long.parseLong(property);
            } catch (NumberFormatException unused) {
            }
        }
        return j;
    }

    public static String getUserHome() {
        String property = System.getProperty("user.home");
        if (property == null) {
            $$$reportNull$$$0(0);
        }
        return property;
    }

    public static String getUserName() {
        String property = System.getProperty("user.name");
        if (property == null) {
            $$$reportNull$$$0(1);
        }
        return property;
    }

    public static String setProperty(String str, String str2) {
        if (str == null) {
            $$$reportNull$$$0(7);
        }
        return str2 != null ? System.setProperty(str, str2) : System.clearProperty(str);
    }
}
