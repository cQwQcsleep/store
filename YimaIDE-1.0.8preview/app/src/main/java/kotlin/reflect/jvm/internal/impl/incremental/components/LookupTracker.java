package kotlin.reflect.jvm.internal.impl.incremental.components;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface LookupTracker {

    public static final class DO_NOTHING implements LookupTracker {
        public static final DO_NOTHING INSTANCE = new DO_NOTHING();

        private DO_NOTHING() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker
        public boolean getRequiresPosition() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker
        public void record(String str, Position position, String str2, ScopeKind scopeKind, String str3) {
            str.getClass();
            position.getClass();
            str2.getClass();
            scopeKind.getClass();
            str3.getClass();
        }
    }

    boolean getRequiresPosition();

    void record(String str, Position position, String str2, ScopeKind scopeKind, String str3);
}
