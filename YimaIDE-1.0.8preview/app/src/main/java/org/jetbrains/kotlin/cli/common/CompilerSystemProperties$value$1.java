package org.jetbrains.kotlin.cli.common;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class CompilerSystemProperties$value$1 extends FunctionReferenceImpl implements Function1<String, String> {
    public static final CompilerSystemProperties$value$1 INSTANCE = new CompilerSystemProperties$value$1();

    public CompilerSystemProperties$value$1() {
        super(1, System.class, "getProperty", "getProperty(Ljava/lang/String;)Ljava/lang/String;", 0);
    }

    public final String invoke(String str) {
        return System.getProperty(str);
    }
}
