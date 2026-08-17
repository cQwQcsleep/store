package com.intellij.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.openapi.util.SimpleModificationTracker;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Service
public final class ForcefulReparseModificationTracker extends SimpleModificationTracker {
    public static ModificationTracker getInstance() {
        return (ModificationTracker) ApplicationManager.getApplication().getService(ForcefulReparseModificationTracker.class);
    }

    public static void increment() {
        getInstance().incModificationCount();
    }
}
