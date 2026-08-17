package org.jetbrains.kotlin.backend.common.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fH&J'\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\r\"\u00020\u0006¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H&J \u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH&J\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H&¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/extensions/IrGeneratedDeclarationsRegistrar;", "", "<init>", "()V", "getMetadataVisibleAnnotationsForElement", "", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "addMetadataVisibleAnnotationsToElement", "", "annotations", "", "", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;[Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;)V", "registerFunctionAsMetadataVisible", "irFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "registerConstructorAsMetadataVisible", "irConstructor", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "addCustomMetadataExtension", "irDeclaration", "pluginId", "", "data", "", "getCustomMetadataExtension", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrGeneratedDeclarationsRegistrar {
    public abstract void addCustomMetadataExtension(IrDeclaration irDeclaration, String pluginId, byte[] data);

    public abstract void addMetadataVisibleAnnotationsToElement(IrDeclaration declaration, List<? extends IrAnnotation> annotations);

    public final void addMetadataVisibleAnnotationsToElement(IrDeclaration declaration, IrAnnotation... annotations) {
        declaration.getClass();
        annotations.getClass();
        addMetadataVisibleAnnotationsToElement(declaration, ArraysKt.toList(annotations));
    }

    public abstract byte[] getCustomMetadataExtension(IrDeclaration irDeclaration, String pluginId);

    public abstract List<IrAnnotation> getMetadataVisibleAnnotationsForElement(IrDeclaration declaration);

    public abstract void registerConstructorAsMetadataVisible(IrConstructor irConstructor);

    public abstract void registerFunctionAsMetadataVisible(IrSimpleFunction irFunction);
}
