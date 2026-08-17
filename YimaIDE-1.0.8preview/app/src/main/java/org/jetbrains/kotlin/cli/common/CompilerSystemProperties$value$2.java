package org.jetbrains.kotlin.cli.common;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class CompilerSystemProperties$value$2 extends FunctionReferenceImpl implements Function2<String, String, String> {
    public static final CompilerSystemProperties$value$2 INSTANCE = new CompilerSystemProperties$value$2();

    public CompilerSystemProperties$value$2() {
        super(2, System.class, "setProperty", "setProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", 0);
    }

    public final String invoke(String str, String str2) {
        return System.setProperty(str, str2);
    }
}
