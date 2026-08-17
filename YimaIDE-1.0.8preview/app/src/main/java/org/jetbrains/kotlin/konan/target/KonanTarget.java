package org.jetbrains.kotlin.konan.target;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.konan.util.Named;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u001b2\u00020\u00012\u00020\u0002:\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,B\u001f\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0004H\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0015-./0123456789:;<=>?@A¨\u0006B"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "Lorg/jetbrains/kotlin/konan/util/Named;", "Ljava/io/Serializable;", "name", "", "family", "Lorg/jetbrains/kotlin/konan/target/Family;", "architecture", "Lorg/jetbrains/kotlin/konan/target/Architecture;", "(Ljava/lang/String;Lorg/jetbrains/kotlin/konan/target/Family;Lorg/jetbrains/kotlin/konan/target/Architecture;)V", "getArchitecture", "()Lorg/jetbrains/kotlin/konan/target/Architecture;", "getFamily", "()Lorg/jetbrains/kotlin/konan/target/Family;", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "toString", "ANDROID_ARM32", "ANDROID_ARM64", "ANDROID_X64", "ANDROID_X86", "Companion", "IOS_ARM64", "IOS_SIMULATOR_ARM64", "IOS_X64", "LINUX_ARM32_HFP", "LINUX_ARM64", "LINUX_X64", "MACOS_ARM64", "MACOS_X64", "MINGW_X64", "TVOS_ARM64", "TVOS_SIMULATOR_ARM64", "TVOS_X64", "WATCHOS_ARM32", "WATCHOS_ARM64", "WATCHOS_DEVICE_ARM64", "WATCHOS_SIMULATOR_ARM64", "WATCHOS_X64", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$ANDROID_ARM32;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$ANDROID_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$ANDROID_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$ANDROID_X86;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$IOS_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$IOS_SIMULATOR_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$IOS_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$LINUX_ARM32_HFP;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$LINUX_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$LINUX_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$MACOS_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$MACOS_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$MINGW_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$TVOS_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$TVOS_SIMULATOR_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$TVOS_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_ARM32;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_DEVICE_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_SIMULATOR_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_X64;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class KonanTarget implements Serializable, Named {
    private final Architecture architecture;
    private final Family family;
    private final String name;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<Map<String, KonanTarget>> predefinedTargets$delegate = LazyKt.lazy(new Function0<Map<String, ? extends KonanTarget>>() { // from class: org.jetbrains.kotlin.konan.target.KonanTarget$Companion$predefinedTargets$2
        public final Map<String, KonanTarget> invoke() {
            List listListOf = CollectionsKt.listOf(new KonanTarget[]{KonanTarget.ANDROID_X64.INSTANCE, KonanTarget.ANDROID_X86.INSTANCE, KonanTarget.ANDROID_ARM32.INSTANCE, KonanTarget.ANDROID_ARM64.INSTANCE, KonanTarget.IOS_ARM64.INSTANCE, KonanTarget.IOS_X64.INSTANCE, KonanTarget.IOS_SIMULATOR_ARM64.INSTANCE, KonanTarget.WATCHOS_ARM32.INSTANCE, KonanTarget.WATCHOS_ARM64.INSTANCE, KonanTarget.WATCHOS_X64.INSTANCE, KonanTarget.WATCHOS_SIMULATOR_ARM64.INSTANCE, KonanTarget.WATCHOS_DEVICE_ARM64.INSTANCE, KonanTarget.TVOS_ARM64.INSTANCE, KonanTarget.TVOS_X64.INSTANCE, KonanTarget.TVOS_SIMULATOR_ARM64.INSTANCE, KonanTarget.LINUX_X64.INSTANCE, KonanTarget.MINGW_X64.INSTANCE, KonanTarget.MACOS_X64.INSTANCE, KonanTarget.MACOS_ARM64.INSTANCE, KonanTarget.LINUX_ARM64.INSTANCE, KonanTarget.LINUX_ARM32_HFP.INSTANCE});
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listListOf, 10)), 16));
            for (Object obj : listListOf) {
                linkedHashMap.put(((KonanTarget) obj).getName(), obj);
            }
            return linkedHashMap;
        }
    });
    private static final Lazy<Set<KonanTarget>> deprecatedTargets$delegate = LazyKt.lazy(new Function0<Set<? extends KonanTarget>>() { // from class: org.jetbrains.kotlin.konan.target.KonanTarget$Companion$deprecatedTargets$2
        public final Set<KonanTarget> invoke() {
            return SetsKt.setOf(new KonanTarget[]{KonanTarget.LINUX_ARM32_HFP.INSTANCE, KonanTarget.WATCHOS_X64.INSTANCE, KonanTarget.TVOS_X64.INSTANCE, KonanTarget.MACOS_X64.INSTANCE});
        }
    });
    private static final Lazy<Set<KonanTarget>> toleratedDeprecatedTargets$delegate = LazyKt.lazy(new Function0<Set<? extends KonanTarget>>() { // from class: org.jetbrains.kotlin.konan.target.KonanTarget$Companion$toleratedDeprecatedTargets$2
        public final Set<KonanTarget> invoke() {
            return SetsKt.setOf(new KonanTarget[]{KonanTarget.LINUX_ARM32_HFP.INSTANCE, KonanTarget.WATCHOS_X64.INSTANCE, KonanTarget.TVOS_X64.INSTANCE, KonanTarget.MACOS_X64.INSTANCE});
        }
    });

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$ANDROID_ARM32;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ANDROID_ARM32 extends KonanTarget {
        public static final ANDROID_ARM32 INSTANCE = new ANDROID_ARM32();

        private ANDROID_ARM32() {
            super("android_arm32", Family.ANDROID, Architecture.ARM32, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$ANDROID_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ANDROID_ARM64 extends KonanTarget {
        public static final ANDROID_ARM64 INSTANCE = new ANDROID_ARM64();

        private ANDROID_ARM64() {
            super("android_arm64", Family.ANDROID, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$ANDROID_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ANDROID_X64 extends KonanTarget {
        public static final ANDROID_X64 INSTANCE = new ANDROID_X64();

        private ANDROID_X64() {
            super("android_x64", Family.ANDROID, Architecture.X64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$ANDROID_X86;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ANDROID_X86 extends KonanTarget {
        public static final ANDROID_X86 INSTANCE = new ANDROID_X86();

        private ANDROID_X86() {
            super("android_x86", Family.ANDROID, Architecture.X86, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$IOS_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class IOS_ARM64 extends KonanTarget {
        public static final IOS_ARM64 INSTANCE = new IOS_ARM64();

        private IOS_ARM64() {
            super("ios_arm64", Family.IOS, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$IOS_SIMULATOR_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class IOS_SIMULATOR_ARM64 extends KonanTarget {
        public static final IOS_SIMULATOR_ARM64 INSTANCE = new IOS_SIMULATOR_ARM64();

        private IOS_SIMULATOR_ARM64() {
            super("ios_simulator_arm64", Family.IOS, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$IOS_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class IOS_X64 extends KonanTarget {
        public static final IOS_X64 INSTANCE = new IOS_X64();

        private IOS_X64() {
            super("ios_x64", Family.IOS, Architecture.X64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$LINUX_ARM32_HFP;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class LINUX_ARM32_HFP extends KonanTarget {
        public static final LINUX_ARM32_HFP INSTANCE = new LINUX_ARM32_HFP();

        private LINUX_ARM32_HFP() {
            super("linux_arm32_hfp", Family.LINUX, Architecture.ARM32, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$LINUX_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class LINUX_ARM64 extends KonanTarget {
        public static final LINUX_ARM64 INSTANCE = new LINUX_ARM64();

        private LINUX_ARM64() {
            super("linux_arm64", Family.LINUX, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$LINUX_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class LINUX_X64 extends KonanTarget {
        public static final LINUX_X64 INSTANCE = new LINUX_X64();

        private LINUX_X64() {
            super("linux_x64", Family.LINUX, Architecture.X64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$MACOS_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MACOS_ARM64 extends KonanTarget {
        public static final MACOS_ARM64 INSTANCE = new MACOS_ARM64();

        private MACOS_ARM64() {
            super("macos_arm64", Family.OSX, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$MACOS_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MACOS_X64 extends KonanTarget {
        public static final MACOS_X64 INSTANCE = new MACOS_X64();

        private MACOS_X64() {
            super("macos_x64", Family.OSX, Architecture.X64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$MINGW_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MINGW_X64 extends KonanTarget {
        public static final MINGW_X64 INSTANCE = new MINGW_X64();

        private MINGW_X64() {
            super("mingw_x64", Family.MINGW, Architecture.X64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$TVOS_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TVOS_ARM64 extends KonanTarget {
        public static final TVOS_ARM64 INSTANCE = new TVOS_ARM64();

        private TVOS_ARM64() {
            super("tvos_arm64", Family.TVOS, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$TVOS_SIMULATOR_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TVOS_SIMULATOR_ARM64 extends KonanTarget {
        public static final TVOS_SIMULATOR_ARM64 INSTANCE = new TVOS_SIMULATOR_ARM64();

        private TVOS_SIMULATOR_ARM64() {
            super("tvos_simulator_arm64", Family.TVOS, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$TVOS_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TVOS_X64 extends KonanTarget {
        public static final TVOS_X64 INSTANCE = new TVOS_X64();

        private TVOS_X64() {
            super("tvos_x64", Family.TVOS, Architecture.X64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_ARM32;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class WATCHOS_ARM32 extends KonanTarget {
        public static final WATCHOS_ARM32 INSTANCE = new WATCHOS_ARM32();

        private WATCHOS_ARM32() {
            super("watchos_arm32", Family.WATCHOS, Architecture.ARM32, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class WATCHOS_ARM64 extends KonanTarget {
        public static final WATCHOS_ARM64 INSTANCE = new WATCHOS_ARM64();

        private WATCHOS_ARM64() {
            super("watchos_arm64", Family.WATCHOS, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_DEVICE_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class WATCHOS_DEVICE_ARM64 extends KonanTarget {
        public static final WATCHOS_DEVICE_ARM64 INSTANCE = new WATCHOS_DEVICE_ARM64();

        private WATCHOS_DEVICE_ARM64() {
            super("watchos_device_arm64", Family.WATCHOS, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_SIMULATOR_ARM64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class WATCHOS_SIMULATOR_ARM64 extends KonanTarget {
        public static final WATCHOS_SIMULATOR_ARM64 INSTANCE = new WATCHOS_SIMULATOR_ARM64();

        private WATCHOS_SIMULATOR_ARM64() {
            super("watchos_simulator_arm64", Family.WATCHOS, Architecture.ARM64, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$WATCHOS_X64;", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class WATCHOS_X64 extends KonanTarget {
        public static final WATCHOS_X64 INSTANCE = new WATCHOS_X64();

        private WATCHOS_X64() {
            super("watchos_x64", Family.WATCHOS, Architecture.X64, null);
        }
    }

    private KonanTarget(String str, Family family, Architecture architecture) {
        this.name = str;
        this.family = family;
        this.architecture = architecture;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KonanTarget)) {
            return false;
        }
        KonanTarget konanTarget = (KonanTarget) other;
        return Intrinsics.areEqual(getName(), konanTarget.getName()) && this.family == konanTarget.family && this.architecture == konanTarget.architecture;
    }

    public final Architecture getArchitecture() {
        return this.architecture;
    }

    public final Family getFamily() {
        return this.family;
    }

    @Override // org.jetbrains.kotlin.konan.util.Named
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return (((getName().hashCode() * 31) + this.family.hashCode()) * 31) + this.architecture.hashCode();
    }

    public String toString() {
        return getName();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R'\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\r\u0010\u000eR!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0011\u0010\u0007¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/KonanTarget$Companion;", "", "()V", "deprecatedTargets", "", "Lorg/jetbrains/kotlin/konan/target/KonanTarget;", "getDeprecatedTargets", "()Ljava/util/Set;", "deprecatedTargets$delegate", "Lkotlin/Lazy;", "predefinedTargets", "", "", "getPredefinedTargets", "()Ljava/util/Map;", "predefinedTargets$delegate", "toleratedDeprecatedTargets", "getToleratedDeprecatedTargets", "toleratedDeprecatedTargets$delegate", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Set<KonanTarget> getDeprecatedTargets() {
            return (Set) KonanTarget.deprecatedTargets$delegate.getValue();
        }

        public final Map<String, KonanTarget> getPredefinedTargets() {
            return (Map) KonanTarget.predefinedTargets$delegate.getValue();
        }

        public final Set<KonanTarget> getToleratedDeprecatedTargets() {
            return (Set) KonanTarget.toleratedDeprecatedTargets$delegate.getValue();
        }

        private Companion() {
        }
    }

    public /* synthetic */ KonanTarget(String str, Family family, Architecture architecture, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, family, architecture);
    }
}
