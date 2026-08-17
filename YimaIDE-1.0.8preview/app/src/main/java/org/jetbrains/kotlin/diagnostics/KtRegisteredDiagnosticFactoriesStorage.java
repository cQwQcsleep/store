package org.jetbrains.kotlin.diagnostics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000b¢\u0006\u0002\u0010\fJ\u0014\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\rR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\r8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtRegisteredDiagnosticFactoriesStorage;", Argument.Delimiters.none, "<init>", "()V", "factories", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "registerDiagnosticContainers", Argument.Delimiters.none, "containers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "([Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;)V", Argument.Delimiters.none, "allDiagnosticFactories", "Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticFactory;", "getAllDiagnosticFactories", "()Ljava/util/List;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtRegisteredDiagnosticFactoriesStorage {
    private final Set<BaseDiagnosticRendererFactory> factories = new LinkedHashSet();

    public final List<AbstractKtDiagnosticFactory> getAllDiagnosticFactories() {
        Set<BaseDiagnosticRendererFactory> set = this.factories;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((BaseDiagnosticRendererFactory) it.next()).getMAP().getFactories());
        }
        return arrayList;
    }

    public final void registerDiagnosticContainers(List<? extends KtDiagnosticsContainer> containers) {
        containers.getClass();
        Set<BaseDiagnosticRendererFactory> set = this.factories;
        List<? extends KtDiagnosticsContainer> list = containers;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((KtDiagnosticsContainer) it.next()).getRendererFactory());
        }
        CollectionsKt.addAll(set, arrayList);
    }

    public final void registerDiagnosticContainers(KtDiagnosticsContainer... containers) {
        containers.getClass();
        registerDiagnosticContainers(ArraysKt.toList(containers));
    }
}
