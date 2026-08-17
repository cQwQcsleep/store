package org.jetbrains.kotlin.ir.symbols;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0013R\u001e\u0010\u0006\u001a\u00028\u00018&X§\u0004r\u0002\b\u000b¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001e\u0010\f\u001a\u00028\u00008&X§\u0004r\u0002\b\u0010¢\u0006\f\u0012\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/symbols/IrBindableSymbol;", "Descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "Owner", "Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "owner", "getOwner$annotations", "()V", "getOwner", "()Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;", "Lorg/jetbrains/kotlin/ir/symbols/UnsafeDuringIrConstructionAPI;", "descriptor", "getDescriptor$annotations", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", "bind", "", "(Lorg/jetbrains/kotlin/ir/declarations/IrSymbolOwner;)V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrBindableSymbol<Descriptor extends DeclarationDescriptor, Owner extends IrSymbolOwner> extends IrSymbol {
    @ObsoleteDescriptorBasedAPI
    static /* synthetic */ void getDescriptor$annotations() {
    }

    @UnsafeDuringIrConstructionAPI
    static /* synthetic */ void getOwner$annotations() {
    }

    void bind(Owner owner);

    @Override // org.jetbrains.kotlin.ir.symbols.IrSymbol
    /* JADX INFO: renamed from: getDescriptor */
    Descriptor mo416getDescriptor();

    @Override // org.jetbrains.kotlin.ir.symbols.IrSymbol
    Owner getOwner();
}
