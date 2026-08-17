package org.jetbrains.kotlin.backend.konan.ir.annotations;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/ir/annotations/BindClassToObjCName;", "", "annotationElement", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "kotlinClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "objCName", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;Lorg/jetbrains/kotlin/ir/declarations/IrClass;Ljava/lang/String;)V", "getAnnotationElement", "()Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "getKotlinClass", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getObjCName", "()Ljava/lang/String;", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class BindClassToObjCName {
    private final IrAnnotation annotationElement;
    private final IrClass kotlinClass;
    private final String objCName;

    public BindClassToObjCName(IrAnnotation irAnnotation, IrClass irClass, String str) {
        irAnnotation.getClass();
        irClass.getClass();
        str.getClass();
        this.annotationElement = irAnnotation;
        this.kotlinClass = irClass;
        this.objCName = str;
    }

    public final IrAnnotation getAnnotationElement() {
        return this.annotationElement;
    }

    public final IrClass getKotlinClass() {
        return this.kotlinClass;
    }

    public final String getObjCName() {
        return this.objCName;
    }
}
