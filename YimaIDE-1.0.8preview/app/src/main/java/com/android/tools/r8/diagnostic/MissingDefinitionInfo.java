package com.android.tools.r8.diagnostic;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MissingDefinitionInfo {
    default MissingClassInfo asMissingClass() {
        return null;
    }

    default MissingFieldInfo asMissingField() {
        return null;
    }

    default MissingMethodInfo asMissingMethod() {
        return null;
    }

    String getDiagnosticMessage();

    Collection<DefinitionContext> getReferencedFromContexts();

    default boolean isMissingClass() {
        return false;
    }

    default boolean isMissingField() {
        return false;
    }

    default boolean isMissingMethod() {
        return false;
    }
}
