package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/PlatformSupertypeUpdater;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "updateSupertypesIfNeeded", Argument.Delimiters.none, "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PlatformSupertypeUpdater implements FirSessionComponent {
    public abstract void updateSupertypesIfNeeded(FirClass firClass, ScopeSession scopeSession);
}
