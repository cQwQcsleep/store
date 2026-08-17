package androidx.compose.compiler.plugins.kotlin.analysis;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityConfigParserImpl;", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityConfigParser;", "lines", "", "", "<init>", "(Ljava/util/List;)V", "stableTypeMatchers", "", "Landroidx/compose/compiler/plugins/kotlin/analysis/FqNameMatcher;", "getStableTypeMatchers", "()Ljava/util/Set;", "errorMessage", "line", "lineNumber", "", "message", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class StabilityConfigParserImpl implements StabilityConfigParser {
    private final Set<FqNameMatcher> stableTypeMatchers;

    public StabilityConfigParserImpl(List<String> list) {
        list.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            String string = StringsKt.trim(str).toString();
            if (!StringsKt.startsWith$default(string, "//", false, 2, (Object) null) && !StringsKt.isBlank(string)) {
                if (StringsKt.contains$default(string, "//", false, 2, (Object) null)) {
                    dwe.a(errorMessage(str, i, "Comments are only supported at the start of a line."));
                    throw null;
                }
                try {
                    linkedHashSet.add(new FqNameMatcher(string));
                } catch (IllegalStateException e) {
                    String message = e.getMessage();
                    mx5.a(errorMessage(str, i, message == null ? "" : message));
                    throw null;
                }
            }
            i = i2;
        }
        this.stableTypeMatchers = CollectionsKt.toSet(linkedHashSet);
    }

    public final String errorMessage(String line, int lineNumber, String message) {
        line.getClass();
        message.getClass();
        return StringsKt.trimIndent("\n            Error parsing stability configuration file on line " + lineNumber + ".\n            " + message + "\n            " + line + "\n        ");
    }

    @Override // androidx.compose.compiler.plugins.kotlin.analysis.StabilityConfigParser
    public Set<FqNameMatcher> getStableTypeMatchers() {
        return this.stableTypeMatchers;
    }
}
