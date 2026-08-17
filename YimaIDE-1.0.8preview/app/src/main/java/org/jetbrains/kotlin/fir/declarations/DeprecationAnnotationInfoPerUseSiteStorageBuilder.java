package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\bJ\u001e\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010R\"\u0010\u0004\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/DeprecationAnnotationInfoPerUseSiteStorageBuilder;", Argument.Delimiters.none, "<init>", "()V", "storage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/DeprecationInfoProvider;", "add", Argument.Delimiters.none, "useSite", "info", AbstractDiagnosticCollector.SUPPRESS_ALL_INFOS, Argument.Delimiters.none, "other", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationAnnotationInfoPerUseSiteStorage;", "build", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeprecationAnnotationInfoPerUseSiteStorageBuilder {
    private final Map<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> storage = new LinkedHashMap();

    public final void add(DeprecationAnnotationInfoPerUseSiteStorage other) {
        other.getClass();
        for (Map.Entry<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> entry : other.getStorage().entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    public final DeprecationAnnotationInfoPerUseSiteStorage build() {
        return new DeprecationAnnotationInfoPerUseSiteStorage(this.storage);
    }

    public final void add(AnnotationUseSiteTarget useSite, Iterable<? extends DeprecationInfoProvider> infos) {
        infos.getClass();
        Map<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> map = this.storage;
        List<DeprecationInfoProvider> arrayList = map.get(useSite);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            map.put(useSite, arrayList);
        }
        CollectionsKt.addAll(arrayList, infos);
    }

    public final void add(AnnotationUseSiteTarget useSite, DeprecationInfoProvider info) {
        info.getClass();
        Map<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> map = this.storage;
        List<DeprecationInfoProvider> arrayList = map.get(useSite);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            map.put(useSite, arrayList);
        }
        arrayList.add(info);
    }
}
