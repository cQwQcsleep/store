package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/EmptyDeprecationsProvider;", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "<init>", "()V", "getDeprecationsInfo", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsPerUseSite;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EmptyDeprecationsProvider extends DeprecationsProvider {
    public static final EmptyDeprecationsProvider INSTANCE = new EmptyDeprecationsProvider();

    private EmptyDeprecationsProvider() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.DeprecationsProvider
    public DeprecationsPerUseSite getDeprecationsInfo(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        return null;
    }
}
