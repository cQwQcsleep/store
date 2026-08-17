package com.android.tools.r8.diagnostic;

import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DefinitionContext {
    default DefinitionClassContext asClassContext() {
        return null;
    }

    default DefinitionFieldContext asFieldContext() {
        return null;
    }

    default DefinitionMethodContext asMethodContext() {
        return null;
    }

    Origin getOrigin();

    default boolean isClassContext() {
        return false;
    }

    default boolean isFieldContext() {
        return false;
    }

    default boolean isMethodContext() {
        return false;
    }
}
