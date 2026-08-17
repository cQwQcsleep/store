package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorWithNavigationSubstitute;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "substitute", "getSubstitute", "()Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public interface DeclarationDescriptorWithNavigationSubstitute extends DeclarationDescriptor {
    DeclarationDescriptor getSubstitute();
}
