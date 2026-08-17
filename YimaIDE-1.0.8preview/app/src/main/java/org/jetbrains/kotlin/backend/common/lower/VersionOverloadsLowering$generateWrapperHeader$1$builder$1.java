package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.ir.builders.declarations.DeclarationBuildersKt;
import org.jetbrains.kotlin.ir.builders.declarations.IrFunctionBuilder;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class VersionOverloadsLowering$generateWrapperHeader$1$builder$1 extends FunctionReferenceImpl implements Function1<Function1<? super IrFunctionBuilder, ? extends Unit>, IrConstructor> {
    public VersionOverloadsLowering$generateWrapperHeader$1$builder$1(Object obj) {
        super(1, obj, DeclarationBuildersKt.class, "buildConstructor", "buildConstructor(Lorg/jetbrains/kotlin/ir/declarations/IrFactory;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", 1);
    }

    public final IrConstructor invoke(Function1<? super IrFunctionBuilder, Unit> function1) {
        function1.getClass();
        IrFactory irFactory = (IrFactory) ((CallableReference) this).receiver;
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        function1.invoke(irFunctionBuilder);
        return DeclarationBuildersKt.buildConstructor(irFactory, irFunctionBuilder);
    }
}
