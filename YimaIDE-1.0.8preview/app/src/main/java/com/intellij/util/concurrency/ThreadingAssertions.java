package com.intellij.util.concurrency;

import androidx.collection.ScatterMapKt;
import com.intellij.concurrency.ThreadContext;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Attachment;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.diagnostic.RuntimeExceptionWithAttachments;
import com.intellij.util.ui.EDT;
import defpackage.zde;
import java.awt.EventQueue;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ThreadingAssertions {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3 || i == 6 || i == 7) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3 || i == 6 || i == 7) ? 3 : 2];
        if (i == 1 || i == 2) {
            objArr[0] = "message";
        } else if (i == 3) {
            objArr[0] = "advice";
        } else if (i == 6 || i == 7) {
            objArr[0] = "getter";
        } else {
            objArr[0] = "com/intellij/util/concurrency/ThreadingAssertions";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[1] = "com/intellij/util/concurrency/ThreadingAssertions";
                break;
            case 4:
                objArr[1] = "getThreadDetails";
                break;
            case 5:
                objArr[1] = "describe";
                break;
            default:
                objArr[1] = "getLogger";
                break;
        }
        if (i == 1) {
            objArr[2] = "throwThreadAccessException";
        } else if (i == 2) {
            objArr[2] = "createThreadAccessException";
        } else if (i == 3) {
            objArr[2] = "createLockingForbiddenException";
        } else if (i == 6) {
            objArr[2] = "isFlagSet";
        } else if (i == 7) {
            objArr[2] = "getStringDetail";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3 && i != 6 && i != 7) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    private ThreadingAssertions() {
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.openapi.diagnostic.RuntimeExceptionWithAttachments */
    public static void assertBackgroundThread() throws RuntimeExceptionWithAttachments {
        if (EDT.isCurrentThreadEdt()) {
            throwThreadAccessException("Access from Event Dispatch Thread (EDT) is not allowed");
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.openapi.diagnostic.RuntimeExceptionWithAttachments */
    public static void assertEventDispatchThread() throws RuntimeExceptionWithAttachments {
        if (EDT.isCurrentThreadEdt()) {
            return;
        }
        throwThreadAccessException("Access is allowed from Event Dispatch Thread (EDT) only");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.openapi.diagnostic.RuntimeExceptionWithAttachments */
    public static void assertWriteAccess() throws RuntimeExceptionWithAttachments {
        if (isFlagSet(new Function() { // from class: yde
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((Application) obj).isWriteAccessAllowed());
            }
        })) {
            trySoftAssertWriteAccessWhenLocksAreForbidden();
        } else {
            throwThreadAccessException("Write access is allowed inside write-action only (see Application.runWriteAction())");
        }
    }

    private static RuntimeExceptionWithAttachments createLockingForbiddenException(String str) {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        return new RuntimeExceptionWithAttachments(str + "\nSee https://jb.gg/ij-platform-threading for details\n" + getThreadDetails(), new Attachment[0]);
    }

    private static RuntimeExceptionWithAttachments createThreadAccessException(String str) {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        boolean z = EDT.isCurrentThreadEdt() && ThreadContext.currentThreadContextOrNull() != null;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("; If you access or modify model on EDT consider wrapping your code in WriteIntentReadAction ");
        sb.append(z ? "" : " or ReadAction");
        sb.append("; see https://jb.gg/ij-platform-threading for details\n");
        sb.append(getThreadDetails());
        return new RuntimeExceptionWithAttachments(sb.toString(), new Attachment[0]);
    }

    private static String describe(Thread thread) {
        if (thread == null) {
            return "null";
        }
        return thread + " " + System.identityHashCode(thread);
    }

    private static Logger getLogger() {
        Logger logger = Logger.getInstance(ThreadingAssertions.class);
        if (logger == null) {
            $$$reportNull$$$0(0);
        }
        return logger;
    }

    private static String getStringDetail(Function<Application, String> function) {
        if (function == null) {
            $$$reportNull$$$0(7);
        }
        Application application = ApplicationManager.getApplication();
        if (application != null) {
            return function.apply(application);
        }
        return null;
    }

    private static String getThreadDetails() {
        Thread threadCurrentThread = Thread.currentThread();
        Thread eventDispatchThreadOrNull = EDT.getEventDispatchThreadOrNull();
        StringBuilder sb = new StringBuilder("Current thread: ");
        sb.append(describe(threadCurrentThread));
        sb.append(" (EventQueue.isDispatchThread()=");
        sb.append(EventQueue.isDispatchThread());
        sb.append(")\nSystemEventQueueThread: ");
        sb.append(eventDispatchThreadOrNull == threadCurrentThread ? "(same)" : describe(eventDispatchThreadOrNull));
        return sb.toString();
    }

    private static boolean isFlagSet(Function<Application, Boolean> function) {
        if (function == null) {
            $$$reportNull$$$0(6);
        }
        Application application = ApplicationManager.getApplication();
        return application != null && function.apply(application).booleanValue();
    }

    public static void softAssertReadAccess() {
        if (isFlagSet(new Function() { // from class: aee
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((Application) obj).isReadAccessAllowed());
            }
        })) {
            trySoftAssertReadAccessWhenLocksAreForbidden();
        } else {
            getLogger().error(createThreadAccessException("Read access is allowed from inside read-action only (see Application.runReadAction())"));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.openapi.diagnostic.RuntimeExceptionWithAttachments */
    private static void throwThreadAccessException(String str) throws RuntimeExceptionWithAttachments {
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        throw createThreadAccessException(str);
    }

    private static void trySoftAssertReadAccessWhenLocksAreForbidden() {
        String stringDetail = getStringDetail(new zde());
        if (stringDetail != null) {
            getLogger().error(createLockingForbiddenException("This thread requested read access, but it does not have permission to use locks.\n".concat(stringDetail)));
        }
    }

    private static void trySoftAssertWriteAccessWhenLocksAreForbidden() {
        String stringDetail = getStringDetail(new zde());
        if (stringDetail != null) {
            getLogger().error(createLockingForbiddenException("This thread requested write access, but it does not have permission to use locks.\n".concat(stringDetail)));
        }
    }
}
