package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.utils.DescriptionAware;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0012\u0010\t\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\bR\u0012\u0010\n\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageOrApiVersion;", "Lorg/jetbrains/kotlin/utils/DescriptionAware;", "versionString", Argument.Delimiters.none, "getVersionString", "()Ljava/lang/String;", "isStable", Argument.Delimiters.none, "()Z", "isDeprecated", "isUnsupported", "description", "getDescription", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface LanguageOrApiVersion extends DescriptionAware {
    default String getDescription() {
        if (!isStable()) {
            return getVersionString() + " (experimental)";
        }
        if (isDeprecated()) {
            return getVersionString() + " (deprecated)";
        }
        if (!isUnsupported()) {
            return getVersionString();
        }
        return getVersionString() + " (unsupported)";
    }

    String getVersionString();

    boolean isDeprecated();

    boolean isStable();

    boolean isUnsupported();
}
