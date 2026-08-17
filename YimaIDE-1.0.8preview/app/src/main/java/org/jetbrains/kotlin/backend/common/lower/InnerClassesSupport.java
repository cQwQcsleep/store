package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrField;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/InnerClassesSupport;", "", "getOuterThisField", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "innerClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getInnerClassConstructorWithOuterThisParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "innerClassConstructor", "getInnerClassOriginalPrimaryConstructorOrNull", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface InnerClassesSupport {
    IrConstructor getInnerClassConstructorWithOuterThisParameter(IrConstructor innerClassConstructor);

    IrConstructor getInnerClassOriginalPrimaryConstructorOrNull(IrClass innerClass);

    IrField getOuterThisField(IrClass innerClass);
}
