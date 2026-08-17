package org.jetbrains.kotlin.ir.symbols;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001R\u001e\u0010\u0002\u001a\u00020\u00038&X§\u0004r\u0002\b\b¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\t\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/symbols/IrPackageFragmentSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "getDescriptor$annotations", "()V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", "Lorg/jetbrains/kotlin/ir/symbols/IrExternalPackageFragmentSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrFileSymbol;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrPackageFragmentSymbol extends IrSymbol {
    @ObsoleteDescriptorBasedAPI
    static /* synthetic */ void getDescriptor$annotations() {
    }

    @Override // org.jetbrains.kotlin.ir.symbols.IrSymbol
    /* JADX INFO: renamed from: getDescriptor, reason: collision with other method in class and merged with bridge method [inline-methods] */
    PackageFragmentDescriptor mo416getDescriptor();
}
