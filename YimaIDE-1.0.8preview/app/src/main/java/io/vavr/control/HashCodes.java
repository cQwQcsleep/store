package io.vavr.control;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Deprecated
public interface HashCodes {
    static int hash(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return ((((((((((((((hash(obj) + 31) * 31) + hash(obj2)) * 31) + hash(obj3)) * 31) + hash(obj4)) * 31) + hash(obj5)) * 31) + hash(obj6)) * 31) + hash(obj7)) * 31) + hash(obj8);
    }

    static int hash(int i, int i2) {
        return ((hash(i) + 31) * 31) + hash(i2);
    }

    static int hash(long j) {
        return Long.hashCode(j);
    }

    static int hash(byte b) {
        return Byte.hashCode(b);
    }

    static int hash(short s) {
        return Short.hashCode(s);
    }

    static int hash(char c) {
        return Character.hashCode(c);
    }

    static int hash(boolean z) {
        return Boolean.hashCode(z);
    }

    static int hash(float f) {
        return Float.hashCode(f);
    }

    static int hash(double d) {
        return Double.hashCode(d);
    }

    static int hash(Object obj) {
        return Objects.hashCode(obj);
    }

    static int hash(int i, Object obj) {
        return ((hash(i) + 31) * 31) + hash(obj);
    }

    static int hash(long j, Object obj) {
        return ((hash(j) + 31) * 31) + hash(obj);
    }

    static int hash(byte b, Object obj) {
        return ((hash(b) + 31) * 31) + hash(obj);
    }

    static int hash(short s, Object obj) {
        return ((hash(s) + 31) * 31) + hash(obj);
    }

    static int hash(char c, Object obj) {
        return ((hash(c) + 31) * 31) + hash(obj);
    }

    static int hash(boolean z, Object obj) {
        return ((hash(z) + 31) * 31) + hash(obj);
    }

    static int hash(float f, Object obj) {
        return ((hash(f) + 31) * 31) + hash(obj);
    }

    static int hash(double d, Object obj) {
        return ((hash(d) + 31) * 31) + hash(obj);
    }

    static int hash(Object obj, Object obj2) {
        return ((hash(obj) + 31) * 31) + hash(obj2);
    }

    static int hash(Object obj, Object obj2, Object obj3) {
        return ((((hash(obj) + 31) * 31) + hash(obj2)) * 31) + hash(obj3);
    }

    static int hash(Object obj, Object obj2, Object obj3, Object obj4) {
        return ((((((hash(obj) + 31) * 31) + hash(obj2)) * 31) + hash(obj3)) * 31) + hash(obj4);
    }

    static int hash(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return ((((((((hash(obj) + 31) * 31) + hash(obj2)) * 31) + hash(obj3)) * 31) + hash(obj4)) * 31) + hash(obj5);
    }

    static int hash(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return ((((((((((hash(obj) + 31) * 31) + hash(obj2)) * 31) + hash(obj3)) * 31) + hash(obj4)) * 31) + hash(obj5)) * 31) + hash(obj6);
    }

    static int hash(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return ((((((((((((hash(obj) + 31) * 31) + hash(obj2)) * 31) + hash(obj3)) * 31) + hash(obj4)) * 31) + hash(obj5)) * 31) + hash(obj6)) * 31) + hash(obj7);
    }

    static int hash(int i) {
        return Integer.hashCode(i);
    }
}
