package org.eclipse.tm4e.core.internal.registry;

import org.eclipse.tm4e.core.internal.grammar.ScopeStack;
import org.eclipse.tm4e.core.internal.theme.StyleAttributes;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IThemeProvider {
    StyleAttributes getDefaults();

    StyleAttributes themeMatch(ScopeStack scopeStack);
}
