package com.google.common.base;

import com.google.common.base.internal.Finalizer;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class FinalizableReferenceQueue implements Closeable {
    private static final String FINALIZER_CLASS_NAME = "com.google.common.base.internal.Finalizer";
    private static final FinalizerStarter finalizerStarter;
    private static final Logger logger = Logger.getLogger(FinalizableReferenceQueue.class.getName());
    final PhantomReference<Object> frqRef;
    final ReferenceQueue<Object> queue;
    final boolean threadStarted;

    public static class DecoupledLoader implements FinalizerLoader {
        private static final String LOADING_ERROR = "Could not load Finalizer in its own class loader. Loading Finalizer in the current class loader instead. As a result, you will not be able to garbage collect this class loader. To support reclaiming this class loader, either resolve the underlying issue, or move Guava to your system class path.";

        public URL getBaseUrl() throws IOException {
            String str = FinalizableReferenceQueue.FINALIZER_CLASS_NAME.replace('.', '/') + ".class";
            URL resource = getClass().getClassLoader().getResource(str);
            if (resource == null) {
                throw new FileNotFoundException(str);
            }
            String string = resource.toString();
            if (string.endsWith(str)) {
                return new URL(resource, string.substring(0, string.length() - str.length()));
            }
            a16.a("Unsupported path style: ".concat(string));
            return null;
        }

        @Override // com.google.common.base.FinalizableReferenceQueue.FinalizerLoader
        public Class<?> loadFinalizer() {
            if (FinalizableReferenceQueue.isAndroid()) {
                return null;
            }
            try {
                return newLoader(getBaseUrl()).loadClass(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
            } catch (Exception e) {
                FinalizableReferenceQueue.logger.log(Level.WARNING, LOADING_ERROR, (Throwable) e);
                return null;
            }
        }

        public URLClassLoader newLoader(URL url) {
            return new URLClassLoader(new URL[]{url}, null);
        }
    }

    public static final class DirectStarter implements FinalizerStarter {
        private DirectStarter() {
        }

        @Override // com.google.common.base.FinalizableReferenceQueue.FinalizerStarter
        public void startFinalizer(Class<?> cls, ReferenceQueue<Object> referenceQueue, PhantomReference<Object> phantomReference) {
            Finalizer.startFinalizer(cls, referenceQueue, phantomReference);
        }
    }

    public interface FinalizerLoader {
        Class<?> loadFinalizer();
    }

    public interface FinalizerStarter {
        void startFinalizer(Class<?> cls, ReferenceQueue<Object> referenceQueue, PhantomReference<Object> phantomReference) throws Throwable;
    }

    public static final class ReflectiveStarter implements FinalizerStarter {
        final Method startFinalizer;

        public ReflectiveStarter(Method method) {
            this.startFinalizer = method;
        }

        @Override // com.google.common.base.FinalizableReferenceQueue.FinalizerStarter
        public void startFinalizer(Class<?> cls, ReferenceQueue<Object> referenceQueue, PhantomReference<Object> phantomReference) throws Throwable {
            this.startFinalizer.invoke(null, cls, referenceQueue, phantomReference);
        }
    }

    public static final class SystemLoader implements FinalizerLoader {
        static boolean disabled;

        @Override // com.google.common.base.FinalizableReferenceQueue.FinalizerLoader
        public Class<?> loadFinalizer() {
            if (disabled || FinalizableReferenceQueue.isAndroid()) {
                return null;
            }
            try {
                ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                if (systemClassLoader != null) {
                    try {
                        return systemClassLoader.loadClass(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
                    } catch (ClassNotFoundException unused) {
                    }
                }
                return null;
            } catch (SecurityException unused2) {
                FinalizableReferenceQueue.logger.info("Not allowed to access system class loader.");
                return null;
            }
        }
    }

    static {
        Class<?> clsLoadFinalizer = loadFinalizer(new SystemLoader(), new DecoupledLoader());
        finalizerStarter = clsLoadFinalizer != null ? new ReflectiveStarter(getStartFinalizer(clsLoadFinalizer)) : new DirectStarter();
    }

    public FinalizableReferenceQueue() {
        boolean z;
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        this.queue = referenceQueue;
        PhantomReference<Object> phantomReference = new PhantomReference<>(this, referenceQueue);
        this.frqRef = phantomReference;
        try {
            finalizerStarter.startFinalizer(FinalizableReference.class, referenceQueue, phantomReference);
            z = true;
        } catch (Throwable th) {
            logger.log(Level.INFO, "Failed to start reference finalizer thread. Reference cleanup will only occur when new references are created.", th);
            z = false;
        }
        this.threadStarted = z;
    }

    public static Method getStartFinalizer(Class<?> cls) {
        try {
            return cls.getMethod("startFinalizer", Class.class, ReferenceQueue.class, PhantomReference.class);
        } catch (NoSuchMethodException e) {
            x01.a(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAndroid() {
        String property = System.getProperty("java.runtime.name", "");
        java.util.Objects.requireNonNull(property);
        return property.contains("Android");
    }

    private static Class<?> loadFinalizer(FinalizerLoader... finalizerLoaderArr) {
        for (FinalizerLoader finalizerLoader : finalizerLoaderArr) {
            Class<?> clsLoadFinalizer = finalizerLoader.loadFinalizer();
            if (clsLoadFinalizer != null) {
                return clsLoadFinalizer;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void cleanUp() {
        if (this.threadStarted) {
            return;
        }
        while (true) {
            Reference<? extends Object> referencePoll = this.queue.poll();
            if (referencePoll == 0) {
                return;
            }
            referencePoll.clear();
            try {
                ((FinalizableReference) referencePoll).finalizeReferent();
            } catch (Throwable th) {
                logger.log(Level.SEVERE, "Error cleaning up after reference.", th);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.frqRef.enqueue();
        cleanUp();
    }
}
