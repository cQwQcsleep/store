package org.jetbrains.kotlin.library.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.descriptors.ModuleCapability;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/library/metadata/KlibModuleOrigin;", "", "<init>", "()V", "Companion", "Lorg/jetbrains/kotlin/library/metadata/CompiledKlibModuleOrigin;", "Lorg/jetbrains/kotlin/library/metadata/SyntheticModulesOrigin;", "org.jetbrains.kotlin:kotlin-util-klib-metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class KlibModuleOrigin {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ModuleCapability<KlibModuleOrigin> CAPABILITY = new ModuleCapability<>("KlibModuleOrigin");

    public /* synthetic */ KlibModuleOrigin(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/library/metadata/KlibModuleOrigin$Companion;", "", "<init>", "()V", "CAPABILITY", "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "Lorg/jetbrains/kotlin/library/metadata/KlibModuleOrigin;", "getCAPABILITY", "()Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "org.jetbrains.kotlin:kotlin-util-klib-metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ModuleCapability<KlibModuleOrigin> getCAPABILITY() {
            return KlibModuleOrigin.CAPABILITY;
        }

        private Companion() {
        }
    }

    private KlibModuleOrigin() {
    }
}
