package org.jetbrains.kotlin.fir.analysis.js.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.js.config.ModuleKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsModuleKind;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "moduleKind", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "<init>", "(Lorg/jetbrains/kotlin/js/config/ModuleKind;)V", "getModuleKind", "()Lorg/jetbrains/kotlin/js/config/ModuleKind;", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsModuleKind implements FirSessionComponent {
    private final ModuleKind moduleKind;

    public FirJsModuleKind(ModuleKind moduleKind) {
        moduleKind.getClass();
        this.moduleKind = moduleKind;
    }

    public final ModuleKind getModuleKind() {
        return this.moduleKind;
    }
}
