package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.config.LanguageVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeLanguageFeature;", "", "since", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "<init>", "(Ljava/lang/String;ILorg/jetbrains/kotlin/config/LanguageVersion;)V", "getSince", "()Lorg/jetbrains/kotlin/config/LanguageVersion;", "DefaultParametersInAbstractFunctions", "DefaultParametersInOpenFunctions", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum ComposeLanguageFeature {
    DefaultParametersInAbstractFunctions(LanguageVersion.KOTLIN_2_1),
    DefaultParametersInOpenFunctions(LanguageVersion.KOTLIN_2_2);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final LanguageVersion since;

    ComposeLanguageFeature(LanguageVersion languageVersion) {
        this.since = languageVersion;
    }

    public static EnumEntries<ComposeLanguageFeature> getEntries() {
        return $ENTRIES;
    }

    public final LanguageVersion getSince() {
        return this.since;
    }
}
