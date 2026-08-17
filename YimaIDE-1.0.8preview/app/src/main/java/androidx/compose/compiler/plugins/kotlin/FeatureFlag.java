package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0015"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/FeatureFlag;", "", "featureName", "", "default", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;Z)V", "getFeatureName", "()Ljava/lang/String;", "getDefault", "()Z", "StrongSkipping", "IntrinsicRemember", "OptimizeNonSkippingGroups", "PausableComposition", "disabledName", "getDisabledName", "name", "enabled", "Companion", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum FeatureFlag {
    StrongSkipping("StrongSkipping", true),
    IntrinsicRemember("IntrinsicRemember", true),
    OptimizeNonSkippingGroups("OptimizeNonSkippingGroups", true),
    PausableComposition("PausableComposition", true);

    private final boolean default;
    private final String featureName;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    FeatureFlag(String str, boolean z) {
        this.featureName = str;
        this.default = z;
    }

    public static EnumEntries<FeatureFlag> getEntries() {
        return $ENTRIES;
    }

    public final boolean getDefault() {
        return this.default;
    }

    public final String getDisabledName() {
        return "-" + this.featureName;
    }

    public final String getFeatureName() {
        return this.featureName;
    }

    public final String name(boolean enabled) {
        return enabled ? this.featureName : getDisabledName();
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/FeatureFlag$Companion;", "", "<init>", "()V", "fromString", "Lkotlin/Pair;", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlag;", "", "featureName", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Pair<FeatureFlag, Boolean> fromString(String featureName) {
            featureName.getClass();
            Object obj = null;
            Pair pair = StringsKt.startsWith$default(featureName, "+", false, 2, (Object) null) ? TuplesKt.to(featureName.substring(1), Boolean.TRUE) : StringsKt.startsWith$default(featureName, "-", false, 2, (Object) null) ? TuplesKt.to(featureName.substring(1), Boolean.FALSE) : TuplesKt.to(featureName, Boolean.TRUE);
            String str = (String) pair.component1();
            Boolean bool = (Boolean) pair.component2();
            bool.booleanValue();
            for (Object obj2 : FeatureFlag.getEntries()) {
                if (StringsKt.equals(StringsKt.trim(str).toString(), ((FeatureFlag) obj2).getFeatureName(), true)) {
                    obj = obj2;
                    break;
                }
            }
            return TuplesKt.to(obj, bool);
        }

        private Companion() {
        }
    }
}
