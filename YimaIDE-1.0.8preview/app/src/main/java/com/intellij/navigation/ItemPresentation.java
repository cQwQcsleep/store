package com.intellij.navigation;

import javax.swing.Icon;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public interface ItemPresentation {
    Icon getIcon(boolean z);

    default String getLocationString() {
        return null;
    }
}
