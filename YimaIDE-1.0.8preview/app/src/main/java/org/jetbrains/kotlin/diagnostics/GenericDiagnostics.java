package org.jetbrains.kotlin.diagnostics;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.UnboundDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0082\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/GenericDiagnostics;", "T", "Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", Argument.Delimiters.none, "all", Argument.Delimiters.none, "isEmpty", Argument.Delimiters.none, "iterator", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface GenericDiagnostics<T extends UnboundDiagnostic> extends Iterable<T>, KMappedMarker {
    Collection<T> all();

    default boolean isEmpty() {
        return all().isEmpty();
    }

    @Override // java.lang.Iterable
    default Iterator<T> iterator() {
        return all().iterator();
    }
}
