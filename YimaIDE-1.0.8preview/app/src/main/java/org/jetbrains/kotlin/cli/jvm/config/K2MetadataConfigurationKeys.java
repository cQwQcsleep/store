package org.jetbrains.kotlin.cli.jvm.config;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/config/K2MetadataConfigurationKeys;", Argument.Delimiters.none, "<init>", "()V", "FRIEND_PATHS", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", Argument.Delimiters.none, Argument.Delimiters.none, "getFRIEND_PATHS", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "REFINES_PATHS", "getREFINES_PATHS", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2MetadataConfigurationKeys {
    private static final CompilerConfigurationKey<List<String>> FRIEND_PATHS;
    public static final K2MetadataConfigurationKeys INSTANCE = new K2MetadataConfigurationKeys();
    private static final CompilerConfigurationKey<List<String>> REFINES_PATHS;

    static {
        CompilerConfigurationKey.Companion companion = CompilerConfigurationKey.INSTANCE;
        FRIEND_PATHS = companion.create("FRIEND_PATHS");
        REFINES_PATHS = companion.create("REFINES_PATHS");
    }

    private K2MetadataConfigurationKeys() {
    }

    public final CompilerConfigurationKey<List<String>> getFRIEND_PATHS() {
        return FRIEND_PATHS;
    }

    public final CompilerConfigurationKey<List<String>> getREFINES_PATHS() {
        return REFINES_PATHS;
    }
}
