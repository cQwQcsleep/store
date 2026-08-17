package org.jetbrains.kotlin.analyzer;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/PackageOracle;", "", "packageExists", "", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "Optimistic", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface PackageOracle {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/PackageOracle$Optimistic;", "Lorg/jetbrains/kotlin/analyzer/PackageOracle;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "packageExists", "", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Optimistic implements PackageOracle {
        public static final Optimistic INSTANCE = new Optimistic();

        private Optimistic() {
        }

        @Override // org.jetbrains.kotlin.analyzer.PackageOracle
        public boolean packageExists(FqName fqName) {
            fqName.getClass();
            return true;
        }
    }

    boolean packageExists(FqName fqName);
}
