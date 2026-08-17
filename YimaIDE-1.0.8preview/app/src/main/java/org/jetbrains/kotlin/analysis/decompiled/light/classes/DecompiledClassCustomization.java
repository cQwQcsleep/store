package org.jetbrains.kotlin.analysis.decompiled.light.classes;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.impl.compiled.ClsFileImpl;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiled/light/classes/DecompiledClassCustomization;", "", "customizeFakeClsFile", "", "fakeFile", "Lcom/intellij/psi/impl/compiled/ClsFileImpl;", "originalKtFile", "Lorg/jetbrains/kotlin/psi/KtFile;", "Companion", "org.jetbrains.kotlin:light-classes-for-decompiled"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface DecompiledClassCustomization {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiled/light/classes/DecompiledClassCustomization$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getInstance", "Lorg/jetbrains/kotlin/analysis/decompiled/light/classes/DecompiledClassCustomization;", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:light-classes-for-decompiled"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @JvmStatic
        public final DecompiledClassCustomization getInstance() {
            return (DecompiledClassCustomization) ApplicationManager.getApplication().getService(DecompiledClassCustomization.class);
        }
    }

    @JvmStatic
    static DecompiledClassCustomization getInstance() {
        return INSTANCE.getInstance();
    }

    void customizeFakeClsFile(ClsFileImpl fakeFile, KtFile originalKtFile);
}
