package org.jetbrains.kotlin.analysis.decompiler.psi;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b`\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/psi/KotlinBuiltInStubVersionOffsetProvider;", "", "getVersionOffset", "", "Companion", "org.jetbrains.kotlin:decompiler-to-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KotlinBuiltInStubVersionOffsetProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/psi/KotlinBuiltInStubVersionOffsetProvider$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getVersionOffset", "", "org.jetbrains.kotlin:decompiler-to-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final int getVersionOffset() {
            Application application = ApplicationManager.getApplication();
            application.getClass();
            KotlinBuiltInStubVersionOffsetProvider kotlinBuiltInStubVersionOffsetProvider = (KotlinBuiltInStubVersionOffsetProvider) application.getService(KotlinBuiltInStubVersionOffsetProvider.class);
            if (kotlinBuiltInStubVersionOffsetProvider != null) {
                return kotlinBuiltInStubVersionOffsetProvider.getVersionOffset();
            }
            return 0;
        }
    }

    int getVersionOffset();
}
