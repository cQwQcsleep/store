package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class CommonRenderers$onNextLines$1$1<T> extends FunctionReferenceImpl implements Function1<T, String> {
    public CommonRenderers$onNextLines$1$1(Object obj) {
        super(1, obj, ContextIndependentParameterRenderer.class, "render", "render(Ljava/lang/Object;)Ljava/lang/String;", 0);
    }

    public final String invoke(T t) {
        return ((ContextIndependentParameterRenderer) ((CallableReference) this).receiver).render(t);
    }
}
