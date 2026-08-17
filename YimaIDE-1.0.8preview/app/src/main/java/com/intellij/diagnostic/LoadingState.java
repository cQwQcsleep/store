package com.intellij.diagnostic;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 com.intellij.diagnostic.LoadingState, still in use, count: 1, list:
  (r0v0 com.intellij.diagnostic.LoadingState) from 0x005c: CONSTRUCTOR (r0v0 com.intellij.diagnostic.LoadingState) A[GenericInfoAttr{[com.intellij.diagnostic.LoadingState], explicit=false}, MD:(V):void (c), WRAPPED] call: java.util.concurrent.atomic.AtomicReference.<init>(java.lang.Object):void type: CONSTRUCTOR
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final class LoadingState {
    BOOTSTRAP("bootstrap"),
    COMPONENTS_REGISTERED("app component registered"),
    CONFIGURATION_STORE_INITIALIZED("app store initialized"),
    COMPONENTS_LOADED("app component loaded"),
    APP_READY("app ready"),
    APP_STARTED("app started"),
    PROJECT_OPENED("project opened");

    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean CHECK_LOADING_PHASE;
    private static final AtomicReference<LoadingState> currentState = new AtomicReference<>(new LoadingState("bootstrap"));
    public static BiConsumer<String, Throwable> errorHandler;
    private static Set<ThrowableWrapper> stackTraces;
    final String displayName;

    public static final class ThrowableWrapper {
        final Throwable throwable;

        private ThrowableWrapper(Throwable th) {
            this.throwable = th;
        }

        private static String fingerprint(Throwable th) {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                sb.append(stackTraceElement.getClassName());
                sb.append(stackTraceElement.getMethodName());
            }
            return sb.toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ThrowableWrapper)) {
                return false;
            }
            Throwable th = ((ThrowableWrapper) obj).throwable;
            Throwable th2 = this.throwable;
            return th2 == th || fingerprint(th2).equals(fingerprint(th));
        }

        public int hashCode() {
            return fingerprint(this.throwable).hashCode();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 1:
                objArr[0] = "currentState";
                break;
            case 2:
                objArr[0] = "state";
                break;
            case 3:
            case 5:
                objArr[0] = "expectedState";
                break;
            case 4:
            case 6:
                objArr[0] = "newState";
                break;
            default:
                objArr[0] = "displayName";
                break;
        }
        objArr[1] = "com/intellij/diagnostic/LoadingState";
        switch (i) {
            case 1:
                objArr[2] = "logStateError";
                break;
            case 2:
                objArr[2] = "setCurrentState";
                break;
            case 3:
            case 4:
                objArr[2] = "compareAndSetCurrentState";
                break;
            case 5:
            case 6:
                objArr[2] = "setCurrentStateIfAtLeast";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    static {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private LoadingState(String str) {
        super(str, i);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        this.displayName = str;
    }

    private static boolean isKnownViolator() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (className.contains("com.intellij.util.indexing.IndexInfrastructure") || className.contains("com.intellij.psi.impl.search.IndexPatternSearcher") || className.contains("com.jetbrains.performancePlugin.ProjectLoaded")) {
                return true;
            }
        }
        return false;
    }

    private synchronized void logStateError(LoadingState loadingState) {
        if (loadingState == null) {
            try {
                $$$reportNull$$$0(1);
            } catch (Throwable th) {
                throw th;
            }
        }
        Throwable th2 = new Throwable();
        if (stackTraces == null) {
            stackTraces = new HashSet();
        }
        if (stackTraces.add(new ThrowableWrapper(th2))) {
            BiConsumer<String, Throwable> biConsumer = errorHandler;
            if (biConsumer != null) {
                biConsumer.accept("Should be called at least in the state " + this + ", the current state is: " + loadingState + "\nCurrent violators count: " + stackTraces.size() + "\n\n", th2);
            }
        }
    }

    public static LoadingState valueOf(String str) {
        return (LoadingState) Enum.valueOf(LoadingState.class, str);
    }

    public static LoadingState[] values() {
        return (LoadingState[]) $VALUES.clone();
    }

    public void checkOccurred() {
        if (CHECK_LOADING_PHASE) {
            LoadingState loadingState = currentState.get();
            if (loadingState.compareTo(this) >= 0 || isKnownViolator()) {
                return;
            }
            logStateError(loadingState);
        }
    }

    public boolean isOccurred() {
        return currentState.get().compareTo(this) >= 0;
    }
}
