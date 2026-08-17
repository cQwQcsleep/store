package com.android.tools.r8.internal;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class C extends AbstractC1811jB implements InterfaceFutureC2933wL {
    public static final boolean e;
    public static final Logger f;
    public static final AbstractC2559s g;
    public static final Object h;
    public volatile Object b;
    public volatile C2901w c;
    public volatile B d;

    static {
        boolean z;
        Throwable th;
        AbstractC2559s c3072y;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        e = z;
        f = Logger.getLogger(C.class.getName());
        Throwable th2 = null;
        try {
            c3072y = new A();
            th = null;
        } catch (Error | RuntimeException e2) {
            th = e2;
            try {
                c3072y = new C2987x(AtomicReferenceFieldUpdater.newUpdater(B.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(B.class, B.class, "b"), AtomicReferenceFieldUpdater.newUpdater(C.class, B.class, "d"), AtomicReferenceFieldUpdater.newUpdater(C.class, C2901w.class, "c"), AtomicReferenceFieldUpdater.newUpdater(C.class, Object.class, "b"));
            } catch (Error | RuntimeException e3) {
                th2 = e3;
                c3072y = new C3072y();
            }
        }
        g = c3072y;
        if (th2 != null) {
            Logger logger = f;
            Level level = Level.SEVERE;
            logger.log(level, "UnsafeAtomicHelper is broken!", th);
            logger.log(level, "SafeAtomicHelper is broken!", th2);
        }
        h = new Object();
    }

    public final void a(StringBuilder sb) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                try {
                    obj = get();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                } catch (Throwable th) {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            } catch (CancellationException unused2) {
                sb.append("CANCELLED");
                return;
            } catch (RuntimeException e2) {
                sb.append("UNKNOWN, cause=[");
                sb.append(e2.getClass());
                sb.append(" thrown from get()]");
                return;
            } catch (ExecutionException e3) {
                sb.append("FAILURE, cause=[");
                sb.append(e3.getCause());
                sb.append("]");
                return;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        sb.append("SUCCESS, result=[");
        a(sb, obj);
        sb.append("]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String b() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        C2644t c2644t;
        Object obj = this.b;
        if (obj == null) {
            if (e) {
                c2644t = new C2644t(z, new CancellationException("Future.cancel() was called."));
            } else {
                c2644t = z ? C2644t.c : C2644t.d;
                Objects.requireNonNull(c2644t);
            }
            if (g.a(this, obj, c2644t)) {
                a(this);
                return true;
            }
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public Object get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.b;
        if (obj != null) {
            return a(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            B b = this.d;
            if (b != B.c) {
                B b2 = new B();
                while (true) {
                    AbstractC2559s abstractC2559s = g;
                    abstractC2559s.a(b2, b);
                    if (abstractC2559s.a(this, b, b2)) {
                        do {
                            LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                            if (Thread.interrupted()) {
                                a(b2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.b;
                            if (obj2 != null) {
                                return a(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        a(b2);
                        break;
                    }
                    b = this.d;
                    if (b == B.c) {
                    }
                }
            }
            Object obj3 = this.b;
            Objects.requireNonNull(obj3);
            return a(obj3);
        }
        while (nanos > 0) {
            Object obj4 = this.b;
            if (obj4 != null) {
                return a(obj4);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        String strConcat = "Waited " + j + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String strConcat2 = strConcat.concat(" (plus ");
            long j2 = -nanos;
            long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
            long nanos2 = j2 - timeUnit.toNanos(jConvert);
            boolean z = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String strConcat3 = strConcat2 + jConvert + " " + lowerCase;
                if (z) {
                    strConcat3 = strConcat3.concat(",");
                }
                strConcat2 = strConcat3.concat(" ");
            }
            if (z) {
                strConcat2 = strConcat2 + nanos2 + " nanoseconds ";
            }
            strConcat = strConcat2.concat("delay)");
        }
        if (isDone()) {
            throw new TimeoutException(strConcat.concat(" but future completed as timeout expired"));
        }
        e41.a(strConcat, string);
        return null;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.b instanceof C2644t;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.b != null;
    }

    public final String toString() {
        String strA;
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            int length = sb.length();
            sb.append("PENDING");
            try {
                strA = XW.a(b());
            } catch (RuntimeException | StackOverflowError e2) {
                strA = "Exception thrown from implementation: " + e2.getClass();
            }
            if (strA != null) {
                sb.append(", info=[");
                sb.append(strA);
                sb.append("]");
            }
            if (isDone()) {
                sb.delete(length, sb.length());
                a(sb);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public final void a(B b) {
        b.a = null;
        while (true) {
            B b2 = this.d;
            if (b2 == B.c) {
                return;
            }
            B b3 = null;
            while (b2 != null) {
                B b4 = b2.b;
                if (b2.a != null) {
                    b3 = b2;
                } else if (b3 != null) {
                    b3.b = b4;
                    if (b3.a == null) {
                    }
                } else if (!g.a(this, b2, b4)) {
                }
                b2 = b4;
            }
            return;
        }
    }

    public static Object a(Object obj) throws ExecutionException {
        if (!(obj instanceof C2644t)) {
            if (!(obj instanceof C2815v)) {
                if (obj == h) {
                    return null;
                }
                return obj;
            }
            throw new ExecutionException(((C2815v) obj).a);
        }
        Throwable th = ((C2644t) obj).b;
        CancellationException cancellationException = new CancellationException("Task was cancelled.");
        cancellationException.initCause(th);
        throw cancellationException;
    }

    public void a() {
    }

    public static void a(C c) {
        c.getClass();
        for (B b = g.b(c); b != null; b = b.b) {
            Thread thread = b.a;
            if (thread != null) {
                b.a = null;
                LockSupport.unpark(thread);
            }
        }
        c.a();
        C2901w c2901wA = g.a(c);
        C2901w c2901w = null;
        while (c2901wA != null) {
            C2901w c2901w2 = c2901wA.a;
            c2901wA.a = c2901w;
            c2901w = c2901wA;
            c2901wA = c2901w2;
        }
        if (c2901w != null) {
            throw null;
        }
    }

    public final void a(StringBuilder sb, Object obj) {
        if (obj == null) {
            sb.append("null");
        } else {
            if (obj == this) {
                sb.append("this future");
                return;
            }
            sb.append(obj.getClass().getName());
            sb.append("@");
            sb.append(Integer.toHexString(System.identityHashCode(obj)));
        }
    }

    @Override // java.util.concurrent.Future
    public Object get() throws InterruptedException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.b;
            if (obj2 != null) {
                return a(obj2);
            }
            B b = this.d;
            if (b != B.c) {
                B b2 = new B();
                do {
                    AbstractC2559s abstractC2559s = g;
                    abstractC2559s.a(b2, b);
                    if (abstractC2559s.a(this, b, b2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.b;
                            } else {
                                a(b2);
                                throw new InterruptedException();
                            }
                        } while (obj == null);
                        return a(obj);
                    }
                    b = this.d;
                } while (b != B.c);
            }
            Object obj3 = this.b;
            Objects.requireNonNull(obj3);
            return a(obj3);
        }
        throw new InterruptedException();
    }
}
