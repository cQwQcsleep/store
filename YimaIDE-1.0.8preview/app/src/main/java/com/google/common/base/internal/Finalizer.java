package com.google.common.base.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class Finalizer implements Runnable {
    private static final Constructor<Thread> bigThreadConstructor;
    private static final Field inheritableThreadLocals;
    private static final Logger logger = Logger.getLogger(Finalizer.class.getName());
    private final WeakReference<Class<?>> finalizableReferenceClassReference;
    private final PhantomReference<Object> frqReference;
    private final ReferenceQueue<Object> queue;

    static {
        Constructor<Thread> bigThreadConstructor2 = getBigThreadConstructor();
        bigThreadConstructor = bigThreadConstructor2;
        inheritableThreadLocals = bigThreadConstructor2 == null ? getInheritableThreadLocalsField() : null;
    }

    private Finalizer(Class<?> cls, ReferenceQueue<Object> referenceQueue, PhantomReference<Object> phantomReference) {
        this.queue = referenceQueue;
        this.finalizableReferenceClassReference = new WeakReference<>(cls);
        this.frqReference = phantomReference;
    }

    private boolean cleanUp(Reference<?> reference) {
        Reference<? extends Object> referencePoll;
        Method finalizeReferentMethod = getFinalizeReferentMethod();
        if (finalizeReferentMethod == null || !finalizeReference(reference, finalizeReferentMethod)) {
            return false;
        }
        do {
            referencePoll = this.queue.poll();
            if (referencePoll == null) {
                return true;
            }
        } while (finalizeReference(referencePoll, finalizeReferentMethod));
        return false;
    }

    private boolean finalizeReference(Reference<?> reference, Method method) {
        reference.clear();
        if (reference == this.frqReference) {
            return false;
        }
        try {
            method.invoke(reference, null);
            return true;
        } catch (Throwable th) {
            logger.log(Level.SEVERE, "Error cleaning up after reference.", th);
            return true;
        }
    }

    private static Constructor<Thread> getBigThreadConstructor() {
        try {
            return Thread.class.getConstructor(ThreadGroup.class, Runnable.class, String.class, Long.TYPE, Boolean.TYPE);
        } catch (Throwable unused) {
            return null;
        }
    }

    private Method getFinalizeReferentMethod() {
        Class<?> cls = this.finalizableReferenceClassReference.get();
        if (cls == null) {
            return null;
        }
        try {
            return cls.getMethod("finalizeReferent", null);
        } catch (NoSuchMethodException e) {
            x01.a(e);
            return null;
        }
    }

    private static Field getInheritableThreadLocalsField() {
        if (isAndroid()) {
            return null;
        }
        try {
            Field declaredField = Thread.class.getDeclaredField("inheritableThreadLocals");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            logger.log(Level.INFO, "Couldn't access Thread.inheritableThreadLocals. Reference finalizer threads will inherit thread local values.");
            return null;
        }
    }

    private static boolean isAndroid() {
        String property = System.getProperty("java.runtime.name", "");
        Objects.requireNonNull(property);
        return property.contains("Android");
    }

    public static void startFinalizer(Class<?> cls, ReferenceQueue<Object> referenceQueue, PhantomReference<Object> phantomReference) {
        Thread thread;
        Finalizer finalizer = new Finalizer(cls, referenceQueue, phantomReference);
        Constructor<Thread> constructor = bigThreadConstructor;
        if (constructor != null) {
            try {
                thread = constructor.newInstance(null, finalizer, "com.google.common.base.internal.Finalizer", 0L, Boolean.FALSE);
            } catch (Throwable th) {
                logger.log(Level.INFO, "Failed to create a thread without inherited thread-local values", th);
                thread = null;
            }
        } else {
            thread = null;
        }
        if (thread == null) {
            thread = new Thread(null, finalizer, "com.google.common.base.internal.Finalizer");
        }
        thread.setDaemon(true);
        try {
            Field field = inheritableThreadLocals;
            if (field != null) {
                field.set(thread, null);
            }
        } catch (Throwable th2) {
            logger.log(Level.INFO, "Failed to clear thread local values inherited by reference finalizer thread.", th2);
        }
        thread.start();
    }

    @Override // java.lang.Runnable
    public void run() {
        while (cleanUp(this.queue.remove())) {
        }
    }
}
