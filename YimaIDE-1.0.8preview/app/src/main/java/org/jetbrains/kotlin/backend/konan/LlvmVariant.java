package org.jetbrains.kotlin.backend.konan;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.konan.file.File;
import org.jetbrains.kotlin.konan.target.HostManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00062\u00020\u0001:\u0005\u0006\u0007\b\t\nB\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H&\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/LlvmVariant;", "", "()V", "getKonanPropertiesEntry", "Lkotlin/Pair;", "", "Companion", "Custom", "Dev", "DevWithAsserts", "User", "Lorg/jetbrains/kotlin/backend/konan/LlvmVariant$Custom;", "Lorg/jetbrains/kotlin/backend/konan/LlvmVariant$Dev;", "Lorg/jetbrains/kotlin/backend/konan/LlvmVariant$DevWithAsserts;", "Lorg/jetbrains/kotlin/backend/konan/LlvmVariant$User;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class LlvmVariant {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<String> konanPropertiesKey$delegate = LazyKt.lazy(Companion.konanPropertiesKey.2.INSTANCE);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/LlvmVariant$Custom;", "Lorg/jetbrains/kotlin/backend/konan/LlvmVariant;", "path", "Lorg/jetbrains/kotlin/konan/file/File;", "(Lorg/jetbrains/kotlin/konan/file/File;)V", "getPath", "()Lorg/jetbrains/kotlin/konan/file/File;", "getKonanPropertiesEntry", "Lkotlin/Pair;", "", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Custom extends LlvmVariant {
        private final File path;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Custom(File file) {
            super(null);
            file.getClass();
            this.path = file;
        }

        @Override // org.jetbrains.kotlin.backend.konan.LlvmVariant
        public Pair<String, String> getKonanPropertiesEntry() {
            return TuplesKt.to(LlvmVariant.INSTANCE.getKonanPropertiesKey(), this.path.getCanonicalPath());
        }

        public final File getPath() {
            return this.path;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/LlvmVariant$Dev;", "Lorg/jetbrains/kotlin/backend/konan/LlvmVariant;", "()V", "getKonanPropertiesEntry", "Lkotlin/Pair;", "", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Dev extends LlvmVariant {
        public static final Dev INSTANCE = new Dev();

        private Dev() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.backend.konan.LlvmVariant
        public Pair<String, String> getKonanPropertiesEntry() {
            return TuplesKt.to(LlvmVariant.INSTANCE.getKonanPropertiesKey(), "$llvm." + HostManager.Companion.getHostName() + ".dev");
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/LlvmVariant$DevWithAsserts;", "Lorg/jetbrains/kotlin/backend/konan/LlvmVariant;", "()V", "getKonanPropertiesEntry", "Lkotlin/Pair;", "", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DevWithAsserts extends LlvmVariant {
        public static final DevWithAsserts INSTANCE = new DevWithAsserts();

        private DevWithAsserts() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.backend.konan.LlvmVariant
        public Pair<String, String> getKonanPropertiesEntry() {
            return TuplesKt.to(LlvmVariant.INSTANCE.getKonanPropertiesKey(), "$llvm." + HostManager.Companion.getHostName() + ".dev-with-asserts");
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/LlvmVariant$User;", "Lorg/jetbrains/kotlin/backend/konan/LlvmVariant;", "()V", "getKonanPropertiesEntry", "Lkotlin/Pair;", "", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class User extends LlvmVariant {
        public static final User INSTANCE = new User();

        private User() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.backend.konan.LlvmVariant
        public Pair<String, String> getKonanPropertiesEntry() {
            return TuplesKt.to(LlvmVariant.INSTANCE.getKonanPropertiesKey(), "$llvm." + HostManager.Companion.getHostName() + ".user");
        }
    }

    public /* synthetic */ LlvmVariant(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Pair<String, String> getKonanPropertiesEntry();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/LlvmVariant$Companion;", "", "()V", "konanPropertiesKey", "", "getKonanPropertiesKey", "()Ljava/lang/String;", "konanPropertiesKey$delegate", "Lkotlin/Lazy;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getKonanPropertiesKey() {
            return (String) LlvmVariant.konanPropertiesKey$delegate.getValue();
        }

        private Companion() {
        }
    }

    private LlvmVariant() {
    }
}
