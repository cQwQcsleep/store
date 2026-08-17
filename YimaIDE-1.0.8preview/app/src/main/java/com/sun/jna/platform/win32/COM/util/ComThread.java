package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.WinNT;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ComThread {
    private static ThreadLocal<Boolean> isCOMThread = new ThreadLocal<>();
    ExecutorService executor;
    Runnable firstTask;
    boolean requiresInitialisation;
    long timeoutMilliseconds;
    Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public ComThread(final String str, long j, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, final int i) {
        this.requiresInitialisation = true;
        this.timeoutMilliseconds = j;
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        this.firstTask = new Runnable() { // from class: com.sun.jna.platform.win32.COM.util.ComThread.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    WinNT.HRESULT hresultCoInitializeEx = Ole32.INSTANCE.CoInitializeEx(null, i);
                    ComThread.isCOMThread.set(Boolean.TRUE);
                    COMUtils.checkRC(hresultCoInitializeEx);
                    ComThread.this.requiresInitialisation = false;
                } catch (Throwable th) {
                    ComThread.this.uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
                }
            }
        };
        this.executor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.sun.jna.platform.win32.COM.util.ComThread.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                if (!ComThread.this.requiresInitialisation) {
                    f63.a("ComThread executor has a problem.");
                    return null;
                }
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(true);
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.sun.jna.platform.win32.COM.util.ComThread.2.1
                    @Override // java.lang.Thread.UncaughtExceptionHandler
                    public void uncaughtException(Thread thread2, Throwable th) {
                        ComThread comThread = ComThread.this;
                        comThread.requiresInitialisation = true;
                        comThread.uncaughtExceptionHandler.uncaughtException(thread2, th);
                    }
                });
                return thread;
            }
        });
    }

    public static void setComThread(boolean z) {
        isCOMThread.set(Boolean.valueOf(z));
    }

    public <T> T execute(Callable<T> callable) throws ExecutionException, InterruptedException, TimeoutException {
        Boolean bool = isCOMThread.get();
        if (bool == null) {
            bool = Boolean.FALSE;
        }
        if (bool.booleanValue()) {
            try {
                return callable.call();
            } catch (Exception e) {
                throw new ExecutionException(e);
            }
        }
        if (this.requiresInitialisation) {
            this.executor.execute(this.firstTask);
        }
        return this.executor.submit(callable).get(this.timeoutMilliseconds, TimeUnit.MILLISECONDS);
    }

    public void finalize() throws Throwable {
        if (this.executor.isShutdown()) {
            return;
        }
        terminate(100L);
    }

    public void terminate(long j) {
        try {
            this.executor.submit(new Runnable() { // from class: com.sun.jna.platform.win32.COM.util.ComThread.3
                @Override // java.lang.Runnable
                public void run() {
                    Ole32.INSTANCE.CoUninitialize();
                }
            }).get(j, TimeUnit.MILLISECONDS);
            this.executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        } catch (TimeoutException unused) {
            this.executor.shutdownNow();
        }
    }

    public ComThread(String str, long j, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this(str, j, uncaughtExceptionHandler, 0);
    }
}
