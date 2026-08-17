package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import org.jetbrains.kotlin.cli.common.arguments.CommonKlibBasedCompilerArguments;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class PartialLinkageConfigurationKt$setupPartialLinkageConfig$1 extends MutablePropertyReference1Impl {
    public static final PartialLinkageConfigurationKt$setupPartialLinkageConfig$1 INSTANCE = new PartialLinkageConfigurationKt$setupPartialLinkageConfig$1();

    public PartialLinkageConfigurationKt$setupPartialLinkageConfig$1() {
        super(CommonKlibBasedCompilerArguments.class, "partialLinkageMode", "getPartialLinkageMode()Ljava/lang/String;", 0);
    }

    public Object get(Object obj) {
        return ((CommonKlibBasedCompilerArguments) obj).getPartialLinkageMode();
    }

    public void set(Object obj, Object obj2) {
        ((CommonKlibBasedCompilerArguments) obj).setPartialLinkageMode((String) obj2);
    }
}
