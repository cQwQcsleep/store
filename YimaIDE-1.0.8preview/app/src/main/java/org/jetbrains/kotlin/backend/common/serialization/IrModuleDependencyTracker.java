package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.IrModuleDependencies;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \f2\u00020\u0001:\u0001\fJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/IrModuleDependencyTracker;", "", "addModuleForTracking", "", "module", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "trackDependency", "fromModule", "toModule", "reverseTopoOrder", "Lorg/jetbrains/kotlin/backend/common/IrModuleDependencies;", "moduleDependencies", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface IrModuleDependencyTracker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/IrModuleDependencyTracker$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "DISABLED", "Lorg/jetbrains/kotlin/backend/common/serialization/IrModuleDependencyTracker;", "getDISABLED", "()Lorg/jetbrains/kotlin/backend/common/serialization/IrModuleDependencyTracker;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final IrModuleDependencyTracker DISABLED = new IrModuleDependencyTracker() { // from class: org.jetbrains.kotlin.backend.common.serialization.IrModuleDependencyTracker$Companion$DISABLED$1
            @Override // org.jetbrains.kotlin.backend.common.serialization.IrModuleDependencyTracker
            public void addModuleForTracking(IrModuleFragment module) {
                module.getClass();
            }

            @Override // org.jetbrains.kotlin.backend.common.serialization.IrModuleDependencyTracker
            public IrModuleDependencies reverseTopoOrder(IrModuleDependencies moduleDependencies) {
                moduleDependencies.getClass();
                return moduleDependencies;
            }

            @Override // org.jetbrains.kotlin.backend.common.serialization.IrModuleDependencyTracker
            public void trackDependency(IrModuleFragment fromModule, IrModuleFragment toModule) {
                fromModule.getClass();
                toModule.getClass();
            }
        };

        private Companion() {
        }

        public final IrModuleDependencyTracker getDISABLED() {
            return DISABLED;
        }
    }

    void addModuleForTracking(IrModuleFragment module);

    IrModuleDependencies reverseTopoOrder(IrModuleDependencies moduleDependencies);

    void trackDependency(IrModuleFragment fromModule, IrModuleFragment toModule);
}
