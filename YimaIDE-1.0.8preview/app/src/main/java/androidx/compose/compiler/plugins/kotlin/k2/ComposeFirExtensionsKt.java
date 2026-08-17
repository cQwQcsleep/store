package androidx.compose.compiler.plugins.kotlin.k2;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"useLegacyCustomFunctionTypeSerializationUntil", Argument.Delimiters.none, "getUseLegacyCustomFunctionTypeSerializationUntil", "()Ljava/lang/String;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComposeFirExtensionsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String getUseLegacyCustomFunctionTypeSerializationUntil() {
        if (!((LanguageVersion) ArraysKt.last(LanguageVersion.values())).isStable()) {
            return ((LanguageVersion) ArraysKt.last(LanguageVersion.values())).getVersionString();
        }
        w01.a("Last value in `LanguageVersion` enum is not expected to be a stable version.");
        return null;
    }
}
