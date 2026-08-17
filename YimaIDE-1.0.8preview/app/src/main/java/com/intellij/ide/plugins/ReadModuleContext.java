package com.intellij.ide.plugins;

import com.intellij.psi.impl.source.tree.ChildRole;
import com.intellij.util.xml.dom.XmlInterner;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lcom/intellij/ide/plugins/ReadModuleContext;", "", "interner", "Lcom/intellij/util/xml/dom/XmlInterner;", "getInterner", "()Lcom/intellij/util/xml/dom/XmlInterner;", "isMissingIncludeIgnored", "", "()Z", "intellij.platform.core.impl"}, k = 1, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public interface ReadModuleContext {
    XmlInterner getInterner();

    default boolean isMissingIncludeIgnored() {
        return false;
    }
}
