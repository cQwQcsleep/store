package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.references.FirSuperReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"hadExplicitTypeInSource", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/references/FirSuperReference;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReferenceUtilsKt {
    public static final boolean hadExplicitTypeInSource(FirSuperReference firSuperReference) {
        firSuperReference.getClass();
        KtSourceElement source = firSuperReference.getSuperTypeRef().getSource();
        return !Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.SuperCallImplicitType.INSTANCE);
    }
}
