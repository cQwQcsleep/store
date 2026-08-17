package org.jetbrains.kotlin.diagnostics.rendering;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.LanguageFeatureMessageRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001:\u0001\u0018B\u001f\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u001a\u0002\b\u000b¢\u0006\u0004\b\t\u0010\nJ$\u0010\u0014\u001a\u00020\u000e2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/LanguageFeatureMessageRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/diagnostics/rendering/LanguageFeatureMessageRenderer$Type;", "useHtml", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/diagnostics/rendering/LanguageFeatureMessageRenderer$Type;Z)V", "Lkotlin/jvm/JvmOverloads;", "additionalFeatureMessages", Argument.Delimiters.none, Argument.Delimiters.none, "featureToFlagMap", "getFeatureToFlagMap", "()Ljava/util/Map;", "featureToFlagMap$delegate", "Lkotlin/Lazy;", "render", "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "Type", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageFeatureMessageRenderer implements DiagnosticParameterRenderer<Pair<? extends LanguageFeature, ? extends LanguageVersionSettings>> {
    private final Map<LanguageFeature, String> additionalFeatureMessages;

    /* JADX INFO: renamed from: featureToFlagMap$delegate, reason: from kotlin metadata */
    private final Lazy featureToFlagMap;
    private final Type type;
    private final boolean useHtml;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/LanguageFeatureMessageRenderer$Type;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "UNSUPPORTED", "WARNING", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Type {
        UNSUPPORTED,
        WARNING;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Type> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Type.values().length];
            try {
                iArr[Type.UNSUPPORTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Type.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LanguageFeatureMessageRenderer(Type type, boolean z) {
        type.getClass();
        this.type = type;
        this.useHtml = z;
        this.additionalFeatureMessages = MapsKt.mapOf(TuplesKt.to(LanguageFeature.UnitConversionsOnArbitraryExpressions, "You can also change the original type of this expression to (...) -> Unit"));
        this.featureToFlagMap = LazyKt.lazy(new Function0() { // from class: no8
            public final Object invoke() {
                return LanguageFeatureMessageRenderer.a(this.b);
            }
        });
    }

    public static Map a(LanguageFeatureMessageRenderer languageFeatureMessageRenderer) {
        ClassLoader classLoader = languageFeatureMessageRenderer.getClass().getClassLoader();
        classLoader.getClass();
        return RuntimeFeatureToFlagMapKt.buildRuntimeFeatureToFlagMap(classLoader);
    }

    private final Map<LanguageFeature, String> getFeatureToFlagMap() {
        return (Map) this.featureToFlagMap.getValue();
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    public String render(Pair<? extends LanguageFeature, ? extends LanguageVersionSettings> obj, RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        LanguageFeature languageFeature = (LanguageFeature) obj.component1();
        LanguageVersionSettings languageVersionSettings = (LanguageVersionSettings) obj.component2();
        LanguageVersion sinceVersion = languageFeature.getSinceVersion();
        StringBuilder sb = new StringBuilder("The feature \"");
        sb.append(languageFeature.getPresentableName());
        sb.append("\" is ");
        String str = getFeatureToFlagMap().get(languageFeature);
        if (str == null) {
            str = "-XXLanguage:+" + languageFeature.name();
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()];
        if (i != 1) {
            if (i != 2) {
                bu8.a();
                return null;
            }
            sb.append("experimental");
        } else if (languageVersionSettings.supportsFeature(languageFeature) && languageVersionSettings.getLanguageVersion().compareTo(LanguageVersion.KOTLIN_2_0) < 0) {
            sb.append("not supported in language versions 1.*, please use version 2.0 or later");
        } else if (languageFeature.getTestOnly()) {
            sb.append("unsupported.");
        } else if (sinceVersion == null) {
            sb.append("experimental and should be enabled explicitly. This can be done by supplying the compiler argument '" + str + "', but note that no stability guarantees are provided.");
        } else if (sinceVersion.compareTo(languageVersionSettings.getLanguageVersion()) > 0) {
            sb.append("only available since language version ");
            sb.append(sinceVersion.getVersionString());
        } else if (languageFeature.getSinceApiVersion().compareTo(languageVersionSettings.getApiVersion()) > 0) {
            sb.append("only available since API version ");
            sb.append(languageFeature.getSinceApiVersion().getVersionString());
        } else {
            sb.append("disabled");
        }
        String hintUrl = languageFeature.getHintUrl();
        if (hintUrl != null) {
            if (this.useHtml) {
                sb.append(" (see more <a href=\"");
                sb.append(hintUrl);
                sb.append("\">here</a>)");
            } else {
                sb.append(" (see: ");
                sb.append(hintUrl);
                sb.append(")");
            }
        }
        if (this.additionalFeatureMessages.containsKey(languageFeature)) {
            sb.append(". " + this.additionalFeatureMessages.get(languageFeature));
        }
        return sb.toString();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LanguageFeatureMessageRenderer(Type type) {
        this(type, false, 2, null);
        type.getClass();
    }

    public /* synthetic */ LanguageFeatureMessageRenderer(Type type, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(type, (i & 2) != 0 ? false : z);
    }
}
