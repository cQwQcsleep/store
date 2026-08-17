package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000eH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/VisibilityPolicy;", "", "forClass", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "inInlineFunctionScope", "", "forConstructor", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "forCapturedField", "value", "Lorg/jetbrains/kotlin/ir/symbols/IrValueSymbol;", "forSimpleFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "Companion", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface VisibilityPolicy {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/VisibilityPolicy$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "DEFAULT", "Lorg/jetbrains/kotlin/backend/common/lower/VisibilityPolicy;", "getDEFAULT", "()Lorg/jetbrains/kotlin/backend/common/lower/VisibilityPolicy;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final VisibilityPolicy DEFAULT = new VisibilityPolicy() { // from class: org.jetbrains.kotlin.backend.common.lower.VisibilityPolicy$Companion$DEFAULT$1
        };

        private Companion() {
        }

        public final VisibilityPolicy getDEFAULT() {
            return DEFAULT;
        }
    }

    default DescriptorVisibility forCapturedField(IrValueSymbol value) {
        value.getClass();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
        descriptorVisibility.getClass();
        return descriptorVisibility;
    }

    default DescriptorVisibility forClass(IrClass declaration, boolean inInlineFunctionScope) {
        declaration.getClass();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
        descriptorVisibility.getClass();
        return descriptorVisibility;
    }

    default DescriptorVisibility forConstructor(IrConstructor declaration, boolean inInlineFunctionScope) {
        declaration.getClass();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
        descriptorVisibility.getClass();
        return descriptorVisibility;
    }

    default DescriptorVisibility forSimpleFunction(IrSimpleFunction declaration) {
        declaration.getClass();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
        descriptorVisibility.getClass();
        return descriptorVisibility;
    }
}
