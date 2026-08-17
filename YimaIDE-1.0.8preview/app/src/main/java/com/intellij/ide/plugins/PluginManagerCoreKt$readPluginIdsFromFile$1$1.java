package com.intellij.ide.plugins;

import com.intellij.psi.impl.source.tree.ChildRole;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public /* synthetic */ class PluginManagerCoreKt$readPluginIdsFromFile$1$1 extends FunctionReferenceImpl implements Function1<String, String> {
    public static final PluginManagerCoreKt$readPluginIdsFromFile$1$1 INSTANCE = new PluginManagerCoreKt$readPluginIdsFromFile$1$1();

    public PluginManagerCoreKt$readPluginIdsFromFile$1$1() {
        super(1, StringsKt.class, "trim", "trim(Ljava/lang/String;)Ljava/lang/String;", 1);
    }

    public final String invoke(String str) {
        str.getClass();
        return StringsKt.trim(str).toString();
    }
}
