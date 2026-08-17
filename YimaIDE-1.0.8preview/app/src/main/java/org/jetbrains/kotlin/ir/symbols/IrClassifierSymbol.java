package org.jetbrains.kotlin.ir.symbols;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u00012\u00020\u0002R\u001e\u0010\u0003\u001a\u00020\u00048&X§\u0004r\u0002\b\t¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0003\n\u000b\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptor;", "getDescriptor$annotations", "()V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrScriptSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrTypeParameterSymbol;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface IrClassifierSymbol extends IrSymbol, TypeConstructorMarker {
    @ObsoleteDescriptorBasedAPI
    static /* synthetic */ void getDescriptor$annotations() {
    }

    ClassifierDescriptor getDescriptor();

    /* JADX INFO: renamed from: getDescriptor, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default DeclarationDescriptor m710getDescriptor() {
        return (DeclarationDescriptor) getDescriptor();
    }
}
