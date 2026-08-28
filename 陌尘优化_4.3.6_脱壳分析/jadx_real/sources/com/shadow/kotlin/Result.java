package com.shadow.kotlin;

import com.shadow.kotlin.io.CloseableKt;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Result<T> implements Serializable {
    public static final Companion Companion = new Companion();
    private final Object value;

    public final class Companion {
    }

    public final class Failure implements Serializable {
        public final Throwable exception;

        public Failure(Throwable th) {
            CloseableKt.checkNotNullParameter(th, "exception");
            this.exception = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Failure) && CloseableKt.areEqual(this.exception, ((Failure) obj).exception);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        public String toString() {
            return "Failure(" + this.exception + ')';
        }
    }

    private /* synthetic */ Result(Object obj) {
        this.value = obj;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Result m0boximpl(Object obj) {
        return new Result(obj);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static <T> Object m1constructorimpl(Object obj) {
        return obj;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof Result) && CloseableKt.areEqual(obj, ((Result) obj2).m9unboximpl());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3equalsimpl0(Object obj, Object obj2) {
        return CloseableKt.areEqual(obj, obj2);
    }

    /* renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m4exceptionOrNullimpl(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m5hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* renamed from: isFailure-impl, reason: not valid java name */
    public static final boolean m6isFailureimpl(Object obj) {
        return obj instanceof Failure;
    }

    /* renamed from: isSuccess-impl, reason: not valid java name */
    public static final boolean m7isSuccessimpl(Object obj) {
        return !(obj instanceof Failure);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m8toStringimpl(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).toString();
        }
        return "Success(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m2equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m5hashCodeimpl(this.value);
    }

    public String toString() {
        return m8toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m9unboximpl() {
        return this.value;
    }
}
