package org.jetbrains.kotlin.ir.symbols;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001R\u001e\u0010\u0002\u001a\u00020\u00038&X§\u0004r\u0002\b\b¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u00020\n8&X§\u0004r\u0002\b\u000e¢\u0006\f\u0012\u0004\b\u000b\u0010\u0005\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000f\u001a\u00020\u00108&X§\u0004r\u0002\b\u000e¢\u0006\f\u0012\u0004\b\u0011\u0010\u0005\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u0016X¦\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0018\"\u0004\b\u001b\u0010\u001cø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001dÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "Lorg/jetbrains/kotlin/mpp/DeclarationSymbolMarker;", "owner", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "getOwner$annotations", "()V", "getOwner", "()Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "Lorg/jetbrains/kotlin/ir/symbols/UnsafeDuringIrConstructionAPI;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "getDescriptor$annotations", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", "hasDescriptor", "", "getHasDescriptor$annotations", "getHasDescriptor", "()Z", "isBound", "signature", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "getSignature", "()Lorg/jetbrains/kotlin/ir/util/IdSignature;", "privateSignature", "getPrivateSignature", "setPrivateSignature", "(Lorg/jetbrains/kotlin/ir/util/IdSignature;)V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrSymbol extends DeclarationSymbolMarker {
    @ObsoleteDescriptorBasedAPI
    static /* synthetic */ void getDescriptor$annotations() {
    }

    @ObsoleteDescriptorBasedAPI
    static /* synthetic */ void getHasDescriptor$annotations() {
    }

    @UnsafeDuringIrConstructionAPI
    static /* synthetic */ void getOwner$annotations() {
    }

    /* JADX INFO: renamed from: getDescriptor */
    DeclarationDescriptor mo416getDescriptor();

    boolean getHasDescriptor();

    IrSymbolOwner getOwner();

    IdSignature getPrivateSignature();

    IdSignature getSignature();

    boolean isBound();

    void setPrivateSignature(IdSignature idSignature);
}
