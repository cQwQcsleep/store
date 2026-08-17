package com.intellij.openapi.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class CompositeModificationTracker extends SimpleModificationTracker {
    private final ModificationTracker myAdditionalTracker;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tracker", "com/intellij/openapi/util/CompositeModificationTracker", "<init>"));
    }

    public CompositeModificationTracker(ModificationTracker modificationTracker) {
        if (modificationTracker == null) {
            $$$reportNull$$$0(0);
        }
        this.myAdditionalTracker = modificationTracker;
    }

    public long getModificationCount() {
        return super.getModificationCount() + this.myAdditionalTracker.getModificationCount();
    }
}
