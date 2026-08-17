package org.jetbrains.kotlin.psi2ir.generators;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.NotFoundClasses;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/psi2ir/generators/DeclarationStubGeneratorForNotFoundClasses;", "Lorg/jetbrains/kotlin/ir/IrProvider;", "stubGenerator", "Lorg/jetbrains/kotlin/psi2ir/generators/DeclarationStubGeneratorImpl;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/psi2ir/generators/DeclarationStubGeneratorImpl;)V", "getDeclaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "org.jetbrains.kotlin:ir.psi2ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DeclarationStubGeneratorForNotFoundClasses implements IrProvider {
    private final DeclarationStubGeneratorImpl stubGenerator;

    public DeclarationStubGeneratorForNotFoundClasses(DeclarationStubGeneratorImpl declarationStubGeneratorImpl) {
        declarationStubGeneratorImpl.getClass();
        this.stubGenerator = declarationStubGeneratorImpl;
    }

    public IrDeclaration getDeclaration(IrSymbol symbol) {
        symbol.getClass();
        if (symbol.isBound()) {
            return null;
        }
        NotFoundClasses.MockClassDescriptor descriptor = symbol.getDescriptor();
        NotFoundClasses.MockClassDescriptor mockClassDescriptor = descriptor instanceof NotFoundClasses.MockClassDescriptor ? descriptor : null;
        if (mockClassDescriptor == null) {
            return null;
        }
        return this.stubGenerator.generateClassStub(mockClassDescriptor);
    }
}
