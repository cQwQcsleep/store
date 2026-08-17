package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u00042\u00020\u0005R\u0012\u0010\u0006\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0012\u0010\u000b\u001a\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0012\u0010\r\u001a\u00028\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters4Marker;", "A", "B", "C", "D", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticMarker;", "a", "getA", "()Ljava/lang/Object;", "b", "getB", "c", "getC", "d", "getD", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface DiagnosticWithParameters4Marker<A, B, C, D> extends DiagnosticMarker {
    A getA();

    B getB();

    C getC();

    D getD();
}
