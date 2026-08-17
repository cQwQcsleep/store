package org.jetbrains.kotlin.resolve.multiplatform;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.mpp.SourceElementMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ClassicSourceElement;", "Lorg/jetbrains/kotlin/mpp/SourceElementMarker;", "element", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/SourceElement;)V", "getElement", "()Lorg/jetbrains/kotlin/descriptors/SourceElement;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ClassicSourceElement implements SourceElementMarker {
    private final SourceElement element;

    public ClassicSourceElement(SourceElement sourceElement) {
        this.element = sourceElement;
    }

    public final SourceElement getElement() {
        return this.element;
    }
}
