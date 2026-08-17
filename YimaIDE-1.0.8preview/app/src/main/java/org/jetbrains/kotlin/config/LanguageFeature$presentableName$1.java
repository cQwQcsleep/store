package org.jetbrains.kotlin.config;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class LanguageFeature$presentableName$1 extends FunctionReferenceImpl implements Function1<String, String> {
    public static final LanguageFeature$presentableName$1 INSTANCE = new LanguageFeature$presentableName$1();

    public LanguageFeature$presentableName$1() {
        super(1, StringsKt.class, "lowercase", "lowercase(Ljava/lang/String;)Ljava/lang/String;", 1);
    }

    public final String invoke(String str) {
        str.getClass();
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return lowerCase;
    }
}
