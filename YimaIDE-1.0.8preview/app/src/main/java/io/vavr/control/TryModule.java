package io.vavr.control;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
interface TryModule {

    public static class ThreadDeathResolver {
        static final Class<?> THREAD_DEATH_CLASS = resolve();

        public static boolean isThreadDeath(Throwable th) {
            Class<?> cls = THREAD_DEATH_CLASS;
            return cls != null && cls.isInstance(th);
        }

        private static Class<?> resolve() {
            try {
                return Class.forName("java.lang.ThreadDeath");
            } catch (ClassNotFoundException unused) {
                return null;
            }
        }
    }

    static boolean isFatal(Throwable th) {
        return (th instanceof InterruptedException) || (th instanceof LinkageError) || ThreadDeathResolver.isThreadDeath(th) || (th instanceof VirtualMachineError);
    }

    static <T extends Throwable, R> R sneakyThrow(Throwable th) throws Throwable {
        throw th;
    }
}
