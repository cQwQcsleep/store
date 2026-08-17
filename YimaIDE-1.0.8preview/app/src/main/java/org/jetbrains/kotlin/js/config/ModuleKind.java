package org.jetbrains.kotlin.js.config;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0011\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/js/config/ModuleKind;", "", "jsExtension", "", "tsExtension", "type", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getJsExtension", "()Ljava/lang/String;", "getTsExtension", "getType", "PLAIN", "AMD", "COMMON_JS", "UMD", "ES", "dtsExtension", "getDtsExtension", "Companion", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum ModuleKind {
    PLAIN(".js", ".ts", "plain"),
    AMD(".js", ".ts", "amd"),
    COMMON_JS(".js", ".ts", "commonjs"),
    UMD(".js", ".ts", "umd"),
    ES(".mjs", ".mts", "es");

    private static final Set<String> allowedJsExtensions;
    private static final Map<String, ModuleKind> moduleMap;
    private final String jsExtension;
    private final String tsExtension;
    private final String type;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    static {
        EnumEntries<ModuleKind> entries = getEntries();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(entries, 10)), 16));
        for (Object obj : entries) {
            linkedHashMap.put(((ModuleKind) obj).type, obj);
        }
        moduleMap = linkedHashMap;
        EnumEntries<ModuleKind> entries2 = getEntries();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = entries2.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(StringsKt.removePrefix(((ModuleKind) it.next()).jsExtension, "."));
        }
        allowedJsExtensions = linkedHashSet;
    }

    ModuleKind(String str, String str2, String str3) {
        this.jsExtension = str;
        this.tsExtension = str2;
        this.type = str3;
    }

    @JvmStatic
    public static final ModuleKind fromType(String str) {
        return INSTANCE.fromType(str);
    }

    public static EnumEntries<ModuleKind> getEntries() {
        return $ENTRIES;
    }

    public final String getDtsExtension() {
        return ".d" + this.tsExtension;
    }

    public final String getJsExtension() {
        return this.jsExtension;
    }

    public final String getTsExtension() {
        return this.tsExtension;
    }

    public final String getType() {
        return this.type;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0006H\u0007R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/js/config/ModuleKind$Companion;", "", "<init>", "()V", "moduleMap", "", "", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "allowedJsExtensions", "", "getAllowedJsExtensions", "()Ljava/util/Set;", "fromType", "type", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ModuleKind fromType(String type) {
            type.getClass();
            ModuleKind moduleKind = (ModuleKind) ModuleKind.moduleMap.get(type);
            if (moduleKind != null) {
                return moduleKind;
            }
            w04.a("Unknown module type: ", type);
            return null;
        }

        public final Set<String> getAllowedJsExtensions() {
            return ModuleKind.allowedJsExtensions;
        }

        private Companion() {
        }
    }
}
