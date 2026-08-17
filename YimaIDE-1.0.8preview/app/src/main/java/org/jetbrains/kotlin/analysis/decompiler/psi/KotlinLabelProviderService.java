package org.jetbrains.kotlin.analysis.decompiler.psi;

import com.intellij.openapi.application.ApplicationManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b&\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/psi/KotlinLabelProviderService;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getLabelForBuiltInFileType", "", "getLabelForKlibMetaFileType", "getLabelForKotlinJavaScriptMetaFileType", "Companion", "org.jetbrains.kotlin:decompiler-to-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class KotlinLabelProviderService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public abstract String getLabelForBuiltInFileType();

    public abstract String getLabelForKlibMetaFileType();

    public abstract String getLabelForKotlinJavaScriptMetaFileType();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/psi/KotlinLabelProviderService$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getService", "Lorg/jetbrains/kotlin/analysis/decompiler/psi/KotlinLabelProviderService;", "org.jetbrains.kotlin:decompiler-to-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KotlinLabelProviderService getService() {
            return (KotlinLabelProviderService) ApplicationManager.getApplication().getService(KotlinLabelProviderService.class);
        }

        private Companion() {
        }
    }
}
