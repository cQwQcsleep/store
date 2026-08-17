package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.library.KotlinAbiVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/CompatibilityMode;", "", "abiVersion", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/library/KotlinAbiVersion;)V", "getAbiVersion", "()Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "legacySignaturesForPrivateAndLocalDeclarations", "", "getLegacySignaturesForPrivateAndLocalDeclarations", "()Z", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CompatibilityMode {
    private final KotlinAbiVersion abiVersion;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final CompatibilityMode CURRENT = new CompatibilityMode(KotlinAbiVersion.Companion.getCURRENT());
    private static final KotlinAbiVersion LAST_WITH_LEGACY_SIGNATURES_FOR_PRIVATE_AND_LOCAL_DECLARATIONS = new KotlinAbiVersion(1, 5, 0);

    public CompatibilityMode(KotlinAbiVersion kotlinAbiVersion) {
        kotlinAbiVersion.getClass();
        this.abiVersion = kotlinAbiVersion;
        if (kotlinAbiVersion.isCompatible()) {
            return;
        }
        w01.a("Incompatible KLIB should have been discarded in libraryMatch");
        throw null;
    }

    public final KotlinAbiVersion getAbiVersion() {
        return this.abiVersion;
    }

    public final boolean getLegacySignaturesForPrivateAndLocalDeclarations() {
        return this.abiVersion.isAtMost(LAST_WITH_LEGACY_SIGNATURES_FOR_PRIVATE_AND_LOCAL_DECLARATIONS);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/CompatibilityMode$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "CURRENT", "Lorg/jetbrains/kotlin/backend/common/serialization/CompatibilityMode;", "getCURRENT", "()Lorg/jetbrains/kotlin/backend/common/serialization/CompatibilityMode;", "LAST_WITH_LEGACY_SIGNATURES_FOR_PRIVATE_AND_LOCAL_DECLARATIONS", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "getLAST_WITH_LEGACY_SIGNATURES_FOR_PRIVATE_AND_LOCAL_DECLARATIONS", "()Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CompatibilityMode getCURRENT() {
            return CompatibilityMode.CURRENT;
        }

        public final KotlinAbiVersion getLAST_WITH_LEGACY_SIGNATURES_FOR_PRIVATE_AND_LOCAL_DECLARATIONS() {
            return CompatibilityMode.LAST_WITH_LEGACY_SIGNATURES_FOR_PRIVATE_AND_LOCAL_DECLARATIONS;
        }

        private Companion() {
        }
    }
}
