package org.jetbrains.kotlin.ir.backend.js.transformers.irToJs;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.js.config.JsGenerationGranularity;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'FULL_DEV' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:160)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/TranslationMode;", "", "production", "", "granularity", "Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "minimizedMemberNames", "<init>", "(Ljava/lang/String;IZLorg/jetbrains/kotlin/js/config/JsGenerationGranularity;Z)V", "getProduction", "()Z", "getGranularity", "()Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "getMinimizedMemberNames", "FULL_DEV", "FULL_PROD", "FULL_PROD_MINIMIZED_NAMES", "PER_MODULE_DEV", "PER_MODULE_PROD", "PER_MODULE_PROD_MINIMIZED_NAMES", "PER_FILE_DEV", "PER_FILE_PROD", "PER_FILE_PROD_MINIMIZED_NAMES", "Companion", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class TranslationMode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TranslationMode[] $VALUES;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final TranslationMode FULL_DEV;
    public static final TranslationMode FULL_PROD;
    public static final TranslationMode FULL_PROD_MINIMIZED_NAMES;
    public static final TranslationMode PER_FILE_DEV;
    public static final TranslationMode PER_FILE_PROD;
    public static final TranslationMode PER_FILE_PROD_MINIMIZED_NAMES;
    public static final TranslationMode PER_MODULE_DEV;
    public static final TranslationMode PER_MODULE_PROD;
    public static final TranslationMode PER_MODULE_PROD_MINIMIZED_NAMES;
    private final JsGenerationGranularity granularity;
    private final boolean minimizedMemberNames;
    private final boolean production;

    private static final /* synthetic */ TranslationMode[] $values() {
        return new TranslationMode[]{FULL_DEV, FULL_PROD, FULL_PROD_MINIMIZED_NAMES, PER_MODULE_DEV, PER_MODULE_PROD, PER_MODULE_PROD_MINIMIZED_NAMES, PER_FILE_DEV, PER_FILE_PROD, PER_FILE_PROD_MINIMIZED_NAMES};
    }

    static {
        JsGenerationGranularity jsGenerationGranularity = JsGenerationGranularity.WHOLE_PROGRAM;
        FULL_DEV = new TranslationMode("FULL_DEV", 0, false, jsGenerationGranularity, false);
        FULL_PROD = new TranslationMode("FULL_PROD", 1, true, jsGenerationGranularity, false);
        FULL_PROD_MINIMIZED_NAMES = new TranslationMode("FULL_PROD_MINIMIZED_NAMES", 2, true, jsGenerationGranularity, true);
        JsGenerationGranularity jsGenerationGranularity2 = JsGenerationGranularity.PER_MODULE;
        PER_MODULE_DEV = new TranslationMode("PER_MODULE_DEV", 3, false, jsGenerationGranularity2, false);
        PER_MODULE_PROD = new TranslationMode("PER_MODULE_PROD", 4, true, jsGenerationGranularity2, false);
        PER_MODULE_PROD_MINIMIZED_NAMES = new TranslationMode("PER_MODULE_PROD_MINIMIZED_NAMES", 5, true, jsGenerationGranularity2, true);
        JsGenerationGranularity jsGenerationGranularity3 = JsGenerationGranularity.PER_FILE;
        PER_FILE_DEV = new TranslationMode("PER_FILE_DEV", 6, false, jsGenerationGranularity3, false);
        PER_FILE_PROD = new TranslationMode("PER_FILE_PROD", 7, true, jsGenerationGranularity3, false);
        PER_FILE_PROD_MINIMIZED_NAMES = new TranslationMode("PER_FILE_PROD_MINIMIZED_NAMES", 8, true, jsGenerationGranularity3, true);
        TranslationMode[] translationModeArr$values = $values();
        $VALUES = translationModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(translationModeArr$values);
        INSTANCE = new Companion(null);
    }

    private TranslationMode(String str, int i, boolean z, JsGenerationGranularity jsGenerationGranularity, boolean z2) {
        super(str, i);
        this.production = z;
        this.granularity = jsGenerationGranularity;
        this.minimizedMemberNames = z2;
    }

    public static EnumEntries<TranslationMode> getEntries() {
        return $ENTRIES;
    }

    public static TranslationMode valueOf(String str) {
        return (TranslationMode) Enum.valueOf(TranslationMode.class, str);
    }

    public static TranslationMode[] values() {
        return (TranslationMode[]) $VALUES.clone();
    }

    public final JsGenerationGranularity getGranularity() {
        return this.granularity;
    }

    public final boolean getMinimizedMemberNames() {
        return this.minimizedMemberNames;
    }

    public final boolean getProduction() {
        return this.production;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/TranslationMode$Companion;", "", "<init>", "()V", "fromFlags", "Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/TranslationMode;", "production", "", "granularity", "Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "minimizedMemberNames", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[JsGenerationGranularity.values().length];
                try {
                    iArr[JsGenerationGranularity.PER_MODULE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[JsGenerationGranularity.PER_FILE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[JsGenerationGranularity.WHOLE_PROGRAM.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TranslationMode fromFlags(boolean production, JsGenerationGranularity granularity, boolean minimizedMemberNames) {
            granularity.getClass();
            int i = WhenMappings.$EnumSwitchMapping$0[granularity.ordinal()];
            if (i == 1) {
                if (production) {
                    return minimizedMemberNames ? TranslationMode.PER_MODULE_PROD_MINIMIZED_NAMES : TranslationMode.PER_MODULE_PROD;
                }
                return TranslationMode.PER_MODULE_DEV;
            }
            if (i == 2) {
                if (production) {
                    return minimizedMemberNames ? TranslationMode.PER_FILE_PROD_MINIMIZED_NAMES : TranslationMode.PER_FILE_PROD;
                }
                return TranslationMode.PER_FILE_DEV;
            }
            if (i != 3) {
                bu8.a();
                return null;
            }
            if (production) {
                return minimizedMemberNames ? TranslationMode.FULL_PROD_MINIMIZED_NAMES : TranslationMode.FULL_PROD;
            }
            return TranslationMode.FULL_DEV;
        }

        private Companion() {
        }
    }
}
