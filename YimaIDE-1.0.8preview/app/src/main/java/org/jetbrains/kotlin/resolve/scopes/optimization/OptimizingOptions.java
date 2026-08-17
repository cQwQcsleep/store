package org.jetbrains.kotlin.resolve.scopes.optimization;

import kotlin.Metadata;
import org.jetbrains.kotlin.container.DefaultImplementation;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@DefaultImplementation(impl = Default.class)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001:\u0001\u0006J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&Ê\u0001\f\b\b\u0012\b\b\t\u0012\u0004\b\t0\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/scopes/optimization/OptimizingOptions;", "", "shouldCalculateAllNamesForLazyImportScopeOptimizing", "", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "Default", "org.jetbrains.kotlin:resolution", "Lorg/jetbrains/kotlin/container/DefaultImplementation;", "impl", "Lorg/jetbrains/kotlin/resolve/scopes/optimization/OptimizingOptions$Default;"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface OptimizingOptions {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/scopes/optimization/OptimizingOptions$Default;", "Lorg/jetbrains/kotlin/resolve/scopes/optimization/OptimizingOptions;", "<init>", "()V", "shouldCalculateAllNamesForLazyImportScopeOptimizing", "", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Default implements OptimizingOptions {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.resolve.scopes.optimization.OptimizingOptions
        public boolean shouldCalculateAllNamesForLazyImportScopeOptimizing(ModuleDescriptor moduleDescriptor) {
            return true;
        }
    }

    boolean shouldCalculateAllNamesForLazyImportScopeOptimizing(ModuleDescriptor moduleDescriptor);
}
