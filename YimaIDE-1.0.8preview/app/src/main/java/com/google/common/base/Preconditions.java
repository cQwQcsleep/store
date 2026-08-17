package com.google.common.base;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class Preconditions {
    private Preconditions() {
    }

    private static String badElementIndex(int i, int i2, String str) {
        if (i < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 >= 0) {
            return Strings.lenientFormat("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        qf1.a("negative size: ", i2);
        return null;
    }

    private static String badPositionIndex(int i, int i2, String str) {
        if (i < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 >= 0) {
            return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        qf1.a("negative size: ", i2);
        return null;
    }

    private static String badPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i > i3) {
            return badPositionIndex(i, i3, "start index");
        }
        return (i2 < 0 || i2 > i3) ? badPositionIndex(i2, i3, "end index") : Strings.lenientFormat("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
    }

    public static void checkArgument(boolean z, String str, char c, char c2) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Character.valueOf(c), Character.valueOf(c2)));
    }

    public static int checkElementIndex(int i, int i2, String str) {
        if (i >= 0 && i < i2) {
            return i;
        }
        jb9.a(badElementIndex(i, i2, str));
        return 0;
    }

    public static <T> T checkNotNull(T t, String str, char c, char c2) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Character.valueOf(c), Character.valueOf(c2)));
        return null;
    }

    public static int checkPositionIndex(int i, int i2, String str) {
        if (i >= 0 && i <= i2) {
            return i;
        }
        jb9.a(badPositionIndex(i, i2, str));
        return 0;
    }

    public static void checkPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            jb9.a(badPositionIndexes(i, i2, i3));
        }
    }

    public static void checkState(boolean z, String str, char c, char c2) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Character.valueOf(c), Character.valueOf(c2)));
    }

    public static int checkElementIndex(int i, int i2) {
        return checkElementIndex(i, i2, "index");
    }

    public static int checkPositionIndex(int i, int i2) {
        return checkPositionIndex(i, i2, "index");
    }

    public static void checkArgument(boolean z, Object obj) {
        if (z) {
            return;
        }
        w01.a(Platform.stringValueOf(obj));
    }

    public static void checkState(boolean z, Object obj) {
        if (z) {
            return;
        }
        k2d.a(Platform.stringValueOf(obj));
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, objArr));
    }

    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.stringValueOf(obj));
        return null;
    }

    public static void checkState(boolean z, String str, Object... objArr) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, objArr));
    }

    public static void checkArgument(boolean z, String str, char c) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Character.valueOf(c)));
    }

    public static <T> T checkNotNull(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, objArr));
        return null;
    }

    public static void checkState(boolean z, String str, char c) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Character.valueOf(c)));
    }

    public static void checkArgument(boolean z, String str, int i) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Integer.valueOf(i)));
    }

    public static <T> T checkNotNull(T t, String str, char c) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Character.valueOf(c)));
        return null;
    }

    public static void checkState(boolean z, String str, int i) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Integer.valueOf(i)));
    }

    public static void checkArgument(boolean z, String str, long j) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Long.valueOf(j)));
    }

    public static <T> T checkNotNull(T t, String str, int i) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Integer.valueOf(i)));
        return null;
    }

    public static void checkState(boolean z, String str, long j) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Long.valueOf(j)));
    }

    public static void checkArgument(boolean z, String str, Object obj) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, obj));
    }

    public static <T> T checkNotNull(T t, String str, long j) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Long.valueOf(j)));
        return null;
    }

    public static void checkState(boolean z, String str, Object obj) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, obj));
    }

    public static void checkArgument(boolean z) {
        if (z) {
            return;
        }
        j2d.a();
    }

    public static <T> T checkNotNull(T t, String str, Object obj) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, obj));
        return null;
    }

    public static void checkState(boolean z) {
        if (z) {
            return;
        }
        g33.a();
    }

    public static void checkArgument(boolean z, String str, char c, int i) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Character.valueOf(c), Integer.valueOf(i)));
    }

    public static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    public static void checkState(boolean z, String str, char c, int i) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Character.valueOf(c), Integer.valueOf(i)));
    }

    public static void checkArgument(boolean z, String str, char c, long j) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Character.valueOf(c), Long.valueOf(j)));
    }

    public static <T> T checkNotNull(T t, String str, char c, int i) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Character.valueOf(c), Integer.valueOf(i)));
        return null;
    }

    public static void checkState(boolean z, String str, char c, long j) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Character.valueOf(c), Long.valueOf(j)));
    }

    public static void checkArgument(boolean z, String str, char c, Object obj) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, Character.valueOf(c), obj));
    }

    public static <T> T checkNotNull(T t, String str, char c, long j) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Character.valueOf(c), Long.valueOf(j)));
        return null;
    }

    public static void checkState(boolean z, String str, char c, Object obj) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, Character.valueOf(c), obj));
    }

    public static void checkArgument(boolean z, String str, int i, char c) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c)));
    }

    public static <T> T checkNotNull(T t, String str, char c, Object obj) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, Character.valueOf(c), obj));
        return null;
    }

    public static void checkState(boolean z, String str, int i, char c) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c)));
    }

    public static void checkArgument(boolean z, String str, int i, int i2) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static <T> T checkNotNull(T t, String str, int i, char c) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c)));
        return null;
    }

    public static void checkState(boolean z, String str, int i, int i2) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static void checkArgument(boolean z, String str, int i, long j) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
    }

    public static <T> T checkNotNull(T t, String str, int i, int i2) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
        return null;
    }

    public static void checkState(boolean z, String str, int i, long j) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
    }

    public static void checkArgument(boolean z, String str, int i, Object obj) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, Integer.valueOf(i), obj));
    }

    public static <T> T checkNotNull(T t, String str, int i, long j) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
        return null;
    }

    public static void checkState(boolean z, String str, int i, Object obj) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, Integer.valueOf(i), obj));
    }

    public static void checkArgument(boolean z, String str, long j, char c) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c)));
    }

    public static <T> T checkNotNull(T t, String str, int i, Object obj) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, Integer.valueOf(i), obj));
        return null;
    }

    public static void checkState(boolean z, String str, long j, char c) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c)));
    }

    public static void checkArgument(boolean z, String str, long j, int i) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
    }

    public static <T> T checkNotNull(T t, String str, long j, char c) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c)));
        return null;
    }

    public static void checkState(boolean z, String str, long j, int i) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
    }

    public static void checkArgument(boolean z, String str, long j, long j2) {
        if (z) {
            return;
        }
        w01.a(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    public static <T> T checkNotNull(T t, String str, long j, int i) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
        return null;
    }

    public static void checkState(boolean z, String str, long j, long j2) {
        if (z) {
            return;
        }
        k2d.a(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    public static void checkArgument(boolean z, String str, long j, Object obj) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, Long.valueOf(j), obj));
    }

    public static <T> T checkNotNull(T t, String str, long j, long j2) {
        if (t != null) {
            return t;
        }
        x0e.a(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
        return null;
    }

    public static void checkState(boolean z, String str, long j, Object obj) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, Long.valueOf(j), obj));
    }

    public static void checkArgument(boolean z, String str, Object obj, char c) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, obj, Character.valueOf(c)));
    }

    public static <T> T checkNotNull(T t, String str, long j, Object obj) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, Long.valueOf(j), obj));
        return null;
    }

    public static void checkState(boolean z, String str, Object obj, char c) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, obj, Character.valueOf(c)));
    }

    public static void checkArgument(boolean z, String str, Object obj, int i) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, obj, Integer.valueOf(i)));
    }

    public static <T> T checkNotNull(T t, String str, Object obj, char c) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, obj, Character.valueOf(c)));
        return null;
    }

    public static void checkState(boolean z, String str, Object obj, int i) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, obj, Integer.valueOf(i)));
    }

    public static void checkArgument(boolean z, String str, Object obj, long j) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, obj, Long.valueOf(j)));
    }

    public static <T> T checkNotNull(T t, String str, Object obj, int i) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, obj, Integer.valueOf(i)));
        return null;
    }

    public static void checkState(boolean z, String str, Object obj, long j) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, obj, Long.valueOf(j)));
    }

    public static void checkArgument(boolean z, String str, Object obj, Object obj2) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, obj, obj2));
    }

    public static <T> T checkNotNull(T t, String str, Object obj, long j) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, obj, Long.valueOf(j)));
        return null;
    }

    public static void checkState(boolean z, String str, Object obj, Object obj2) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, obj, obj2));
    }

    public static void checkArgument(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, obj, obj2, obj3));
    }

    public static <T> T checkNotNull(T t, String str, Object obj, Object obj2) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, obj, obj2));
        return null;
    }

    public static void checkState(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, obj, obj2, obj3));
    }

    public static void checkArgument(boolean z, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (z) {
            return;
        }
        w01.a(Platform.lenientFormat(str, obj, obj2, obj3, obj4));
    }

    public static <T> T checkNotNull(T t, String str, Object obj, Object obj2, Object obj3) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, obj, obj2, obj3));
        return null;
    }

    public static void checkState(boolean z, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (z) {
            return;
        }
        k2d.a(Platform.lenientFormat(str, obj, obj2, obj3, obj4));
    }

    public static <T> T checkNotNull(T t, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (t != null) {
            return t;
        }
        x0e.a(Platform.lenientFormat(str, obj, obj2, obj3, obj4));
        return null;
    }
}
