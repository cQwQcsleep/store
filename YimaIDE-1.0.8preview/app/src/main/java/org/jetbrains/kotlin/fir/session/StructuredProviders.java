package org.jetbrains.kotlin.fir.session;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/StructuredProviders;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "sourceProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "dependencyProviders", "sharedProvider", "<init>", "(Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;)V", "getSourceProviders", "()Ljava/util/List;", "getDependencyProviders", "getSharedProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StructuredProviders implements FirSessionComponent {
    private final List<FirSymbolProvider> dependencyProviders;
    private final FirSymbolProvider sharedProvider;
    private final List<FirSymbolProvider> sourceProviders;

    /* JADX WARN: Multi-variable type inference failed */
    public StructuredProviders(List<? extends FirSymbolProvider> list, List<? extends FirSymbolProvider> list2, FirSymbolProvider firSymbolProvider) {
        list.getClass();
        list2.getClass();
        firSymbolProvider.getClass();
        this.sourceProviders = list;
        this.dependencyProviders = list2;
        this.sharedProvider = firSymbolProvider;
    }

    public final List<FirSymbolProvider> getDependencyProviders() {
        return this.dependencyProviders;
    }

    public final FirSymbolProvider getSharedProvider() {
        return this.sharedProvider;
    }

    public final List<FirSymbolProvider> getSourceProviders() {
        return this.sourceProviders;
    }
}
