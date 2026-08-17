package org.jetbrains.kotlin.backend.common.serialization.mangle;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\f\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/mangle/MangleConstant;", "", "prefix", "", "separator", "suffix", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;ICCC)V", "getPrefix", "()C", "getSeparator", "getSuffix", "VALUE_PARAMETERS", "TYPE_PARAMETERS", "UPPER_BOUNDS", "TYPE_ARGUMENTS", "FLEXIBLE_TYPE", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum MangleConstant {
    VALUE_PARAMETERS(Util.C_PARAM_START, ';', Util.C_PARAM_END),
    TYPE_PARAMETERS('{', ';', '}'),
    UPPER_BOUNDS(Util.C_GENERIC_START, '&', Util.C_GENERIC_END),
    TYPE_ARGUMENTS(Util.C_GENERIC_START, ',', Util.C_GENERIC_END),
    FLEXIBLE_TYPE(Util.C_ARRAY, '~', ']');

    public static final String ANON_INIT_NAME_PREFIX = "<ANI>";
    public static final String BACKING_FIELD_NAME = "<BF>";
    public static final String DYNAMIC_MARK = "<dynamic>";
    public static final String ENHANCED_NULLABILITY_MARK = "{EnhancedNullability}";
    public static final String ENUM_ENTRY_CLASS_NAME = "<EEC>";
    public static final String ERROR_MARK = "<ERROR CLASS>";
    public static final char EXTENSION_RECEIVER_PREFIX = '@';
    public static final char FQN_SEPARATOR = '.';
    public static final char FUNCTION_NAME_PREFIX = '#';
    public static final char INDEX_SEPARATOR = ':';
    public static final String JAVA_FIELD_SUFFIX = "#jf";
    public static final char LOCAL_DECLARATION_INDEX_PREFIX = '$';
    public static final char PLATFORM_FUNCTION_MARKER = '%';
    public static final char Q_MARK = '?';
    public static final char STAR_MARK = '*';
    public static final String STATIC_MEMBER_MARK = "#static";
    public static final String SUSPEND_FUNCTION_MARK = "#suspend";
    public static final String TYPE_PARAMETER_MARKER_NAME = "<TP>";
    public static final String TYPE_PARAMETER_MARKER_NAME_SETTER = "<STP>";
    public static final char TYPE_PARAM_INDEX_PREFIX = '@';
    public static final char UPPER_BOUND_SEPARATOR = 167;
    public static final char VARIANCE_SEPARATOR = '|';
    public static final String VAR_ARG_MARK = "...";
    private final char prefix;
    private final char separator;
    private final char suffix;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    MangleConstant(char c, char c2, char c3) {
        this.prefix = c;
        this.separator = c2;
        this.suffix = c3;
    }

    public static EnumEntries<MangleConstant> getEntries() {
        return $ENTRIES;
    }

    public final char getPrefix() {
        return this.prefix;
    }

    public final char getSeparator() {
        return this.separator;
    }

    public final char getSuffix() {
        return this.suffix;
    }
}
