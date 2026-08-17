package org.jetbrains.kotlin.backend.common.extensions;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J \u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContextImpl$DummyIrGeneratedDeclarationsRegistrar;", "Lorg/jetbrains/kotlin/backend/common/extensions/IrGeneratedDeclarationsRegistrar;", "<init>", "()V", "getMetadataVisibleAnnotationsForElement", "", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "addMetadataVisibleAnnotationsToElement", "", "annotations", "", "registerFunctionAsMetadataVisible", "irFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "registerConstructorAsMetadataVisible", "irConstructor", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "addCustomMetadataExtension", "irDeclaration", "pluginId", "", "data", "", "getCustomMetadataExtension", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrPluginContextImpl$DummyIrGeneratedDeclarationsRegistrar extends IrGeneratedDeclarationsRegistrar {
    public static final IrPluginContextImpl$DummyIrGeneratedDeclarationsRegistrar INSTANCE = new IrPluginContextImpl$DummyIrGeneratedDeclarationsRegistrar();

    private IrPluginContextImpl$DummyIrGeneratedDeclarationsRegistrar() {
    }

    @Override // org.jetbrains.kotlin.backend.common.extensions.IrGeneratedDeclarationsRegistrar
    public void addCustomMetadataExtension(IrDeclaration irDeclaration, String pluginId, byte[] data) {
        irDeclaration.getClass();
        pluginId.getClass();
        data.getClass();
    }

    @Override // org.jetbrains.kotlin.backend.common.extensions.IrGeneratedDeclarationsRegistrar
    public void addMetadataVisibleAnnotationsToElement(IrDeclaration declaration, List<? extends IrAnnotation> annotations) {
        declaration.getClass();
        annotations.getClass();
        declaration.setAnnotations(CollectionsKt.plus(declaration.getAnnotations(), annotations));
    }

    @Override // org.jetbrains.kotlin.backend.common.extensions.IrGeneratedDeclarationsRegistrar
    public byte[] getCustomMetadataExtension(IrDeclaration irDeclaration, String pluginId) {
        irDeclaration.getClass();
        pluginId.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.backend.common.extensions.IrGeneratedDeclarationsRegistrar
    public List<IrAnnotation> getMetadataVisibleAnnotationsForElement(IrDeclaration declaration) {
        declaration.getClass();
        return new ArrayList();
    }

    @Override // org.jetbrains.kotlin.backend.common.extensions.IrGeneratedDeclarationsRegistrar
    public void registerConstructorAsMetadataVisible(IrConstructor irConstructor) {
        irConstructor.getClass();
    }

    @Override // org.jetbrains.kotlin.backend.common.extensions.IrGeneratedDeclarationsRegistrar
    public void registerFunctionAsMetadataVisible(IrSimpleFunction irFunction) {
        irFunction.getClass();
    }
}
