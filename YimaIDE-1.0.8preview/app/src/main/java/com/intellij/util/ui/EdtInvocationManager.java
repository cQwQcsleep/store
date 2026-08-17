package com.intellij.util.ui;

import com.intellij.openapi.diagnostic.Logger;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class EdtInvocationManager {
    private static final AtomicReference<EdtInvocationManager> ourInstance = new AtomicReference<>();

    public static class SwingEdtInvocationManager extends EdtInvocationManager {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            objArr[0] = "task";
            objArr[1] = "com/intellij/util/ui/EdtInvocationManager$SwingEdtInvocationManager";
            if (i != 1) {
                objArr[2] = "invokeLater";
            } else {
                objArr[2] = "invokeAndWait";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.ui.EdtInvocationManager
        public void invokeAndWait(Runnable runnable) throws InterruptedException, InvocationTargetException {
            if (runnable == null) {
                $$$reportNull$$$0(1);
            }
            EventQueue.invokeAndWait(runnable);
        }

        @Override // com.intellij.util.ui.EdtInvocationManager
        public void invokeLater(Runnable runnable) {
            if (runnable == null) {
                $$$reportNull$$$0(0);
            }
            EventQueue.invokeLater(runnable);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "runnable";
        } else {
            objArr[0] = "com/intellij/util/ui/EdtInvocationManager";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/ui/EdtInvocationManager";
        } else {
            objArr[1] = "getInstance";
        }
        if (i != 1) {
            if (i != 2) {
                objArr[2] = "invokeLaterIfNeeded";
            } else {
                objArr[2] = "invokeAndWaitIfNeeded";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public static EdtInvocationManager getInstance() {
        AtomicReference<EdtInvocationManager> atomicReference = ourInstance;
        EdtInvocationManager swingEdtInvocationManager = atomicReference.get();
        if (swingEdtInvocationManager == null) {
            swingEdtInvocationManager = new SwingEdtInvocationManager();
            if (!atomicReference.compareAndSet(null, swingEdtInvocationManager)) {
                swingEdtInvocationManager = atomicReference.get();
            }
        }
        if (swingEdtInvocationManager == null) {
            $$$reportNull$$$0(1);
        }
        return swingEdtInvocationManager;
    }

    public static void invokeAndWaitIfNeeded(Runnable runnable) {
        if (runnable == null) {
            $$$reportNull$$$0(2);
        }
        if (EDT.isCurrentThreadEdt()) {
            runnable.run();
            return;
        }
        try {
            getInstance().invokeAndWait(runnable);
        } catch (Exception e) {
            Logger.getInstance(EdtInvocationManager.class).error(e);
        }
    }

    public static void invokeLaterIfNeeded(Runnable runnable) {
        if (runnable == null) {
            $$$reportNull$$$0(0);
        }
        if (EDT.isCurrentThreadEdt()) {
            runnable.run();
        } else {
            getInstance().invokeLater(runnable);
        }
    }

    public abstract void invokeAndWait(Runnable runnable) throws InterruptedException, InvocationTargetException;

    public abstract void invokeLater(Runnable runnable);
}
