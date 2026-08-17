package org.jetbrains.kotlin.cli.common.arguments;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004R&\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR&\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR&\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR&\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR&\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000bR&\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000bR&\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR0\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R0\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u001e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020%0\u001e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\"\"\u0004\b(\u0010$R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R,\u0010/\u001a\u0014\u0012\u0004\u0012\u000201\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\u001e00X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "Ljava/io/Serializable;", "<init>", "()V", "value", Argument.Delimiters.none, "allWarningsAsErrors", "getAllWarningsAsErrors", "()Z", "setAllWarningsAsErrors", "(Z)V", "extraWarnings", "getExtraWarnings", "setExtraWarnings", "extraHelp", "getExtraHelp", "setExtraHelp", "help", "getHelp", "setHelp", "suppressWarnings", "getSuppressWarnings", "setSuppressWarnings", "verbose", "getVerbose", "setVerbose", "version", "getVersion", "setVersion", Argument.Delimiters.none, Argument.Delimiters.none, "freeArgs", "getFreeArgs", "()Ljava/util/List;", "setFreeArgs", "(Ljava/util/List;)V", "Lorg/jetbrains/kotlin/cli/common/arguments/ManualLanguageFeatureSetting;", "internalArguments", "getInternalArguments", "setInternalArguments", AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS, "Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentParseErrors;", "getErrors", "()Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentParseErrors;", "setErrors", "(Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentParseErrors;)V", "explicitArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentField;", Argument.Delimiters.none, "getExplicitArguments", "()Ljava/util/Map;", "setExplicitArguments", "(Ljava/util/Map;)V", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CommonToolArguments extends Freezable implements Serializable {

    @Argument(description = "Report an error if there are any warnings.", value = "-Werror")
    private boolean allWarningsAsErrors;
    private transient ArgumentParseErrors errors;

    @Argument(description = "Print a synopsis of advanced options.", value = "-X")
    private boolean extraHelp;

    @Argument(description = "Enable extra checkers for K2.", value = "-Wextra")
    private boolean extraWarnings;

    @Argument(description = "Print a synopsis of standard options.", shortName = "-h", value = "-help")
    private boolean help;

    @Argument(description = "Don't generate any warnings.", value = "-nowarn")
    private boolean suppressWarnings;

    @Argument(description = "Enable verbose logging output.", value = "-verbose")
    private boolean verbose;

    @Argument(description = "Display the compiler version.", value = "-version")
    private boolean version;
    private List<String> freeArgs = CollectionsKt.emptyList();
    private List<ManualLanguageFeatureSetting> internalArguments = CollectionsKt.emptyList();
    private transient Map<ArgumentField, ? extends List<? extends Object>> explicitArguments = MapsKt.emptyMap();

    public final boolean getAllWarningsAsErrors() {
        return this.allWarningsAsErrors;
    }

    public final ArgumentParseErrors getErrors() {
        return this.errors;
    }

    public final Map<ArgumentField, List<Object>> getExplicitArguments() {
        return this.explicitArguments;
    }

    public final boolean getExtraHelp() {
        return this.extraHelp;
    }

    public final boolean getExtraWarnings() {
        return this.extraWarnings;
    }

    public final List<String> getFreeArgs() {
        return this.freeArgs;
    }

    public final boolean getHelp() {
        return this.help;
    }

    public final List<ManualLanguageFeatureSetting> getInternalArguments() {
        return this.internalArguments;
    }

    public final boolean getSuppressWarnings() {
        return this.suppressWarnings;
    }

    public final boolean getVerbose() {
        return this.verbose;
    }

    public final boolean getVersion() {
        return this.version;
    }

    public final void setAllWarningsAsErrors(boolean z) {
        checkFrozen();
        this.allWarningsAsErrors = z;
    }

    public final void setErrors(ArgumentParseErrors argumentParseErrors) {
        this.errors = argumentParseErrors;
    }

    public final void setExplicitArguments(Map<ArgumentField, ? extends List<? extends Object>> map) {
        map.getClass();
        this.explicitArguments = map;
    }

    public final void setExtraHelp(boolean z) {
        checkFrozen();
        this.extraHelp = z;
    }

    public final void setExtraWarnings(boolean z) {
        checkFrozen();
        this.extraWarnings = z;
    }

    public final void setFreeArgs(List<String> list) {
        list.getClass();
        checkFrozen();
        this.freeArgs = list;
    }

    public final void setHelp(boolean z) {
        checkFrozen();
        this.help = z;
    }

    public final void setInternalArguments(List<ManualLanguageFeatureSetting> list) {
        list.getClass();
        checkFrozen();
        this.internalArguments = list;
    }

    public final void setSuppressWarnings(boolean z) {
        checkFrozen();
        this.suppressWarnings = z;
    }

    public final void setVerbose(boolean z) {
        checkFrozen();
        this.verbose = z;
    }

    public final void setVersion(boolean z) {
        checkFrozen();
        this.version = z;
    }
}
