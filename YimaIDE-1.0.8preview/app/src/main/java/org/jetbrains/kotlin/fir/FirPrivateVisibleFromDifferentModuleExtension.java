package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirPrivateVisibleFromDifferentModuleExtension;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "canSeePrivateDeclarationsOfModule", Argument.Delimiters.none, "otherModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "canSeePrivateTopLevelDeclarationsFromFile", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "targetFile", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirPrivateVisibleFromDifferentModuleExtension implements FirSessionComponent {
    public abstract boolean canSeePrivateDeclarationsOfModule(FirModuleData otherModuleData);

    public abstract boolean canSeePrivateTopLevelDeclarationsFromFile(FirFile useSiteFile, FirFile targetFile);
}
