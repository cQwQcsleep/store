package org.jetbrains.kotlin.fir.resolve.providers;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¨\u0006\u0005"}, d2 = {"mayContainTopLevelClassifier", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "shortClassName", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSymbolNamesProviderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean mayContainTopLevelClassifier(Set<Name> set, Name name) {
        return name.isSpecial() || set.contains(name);
    }
}
