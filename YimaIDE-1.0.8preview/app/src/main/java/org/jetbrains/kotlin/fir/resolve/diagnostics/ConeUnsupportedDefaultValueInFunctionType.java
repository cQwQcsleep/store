package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnsupportedDefaultValueInFunctionType;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnsupported;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ConeUnsupportedDefaultValueInFunctionType extends ConeUnsupported {
    public /* synthetic */ ConeUnsupportedDefaultValueInFunctionType(KtSourceElement ktSourceElement, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : ktSourceElement);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ConeUnsupportedDefaultValueInFunctionType() {
        KtSourceElement ktSourceElement = null;
        this(ktSourceElement, 1, ktSourceElement);
    }

    public ConeUnsupportedDefaultValueInFunctionType(KtSourceElement ktSourceElement) {
        super("Function type parameters cannot have default values.", ktSourceElement);
    }
}
