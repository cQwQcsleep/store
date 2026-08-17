package androidx.compose.compiler.plugins.kotlin.analysis;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityConfigParser;", "", "stableTypeMatchers", "", "Landroidx/compose/compiler/plugins/kotlin/analysis/FqNameMatcher;", "getStableTypeMatchers", "()Ljava/util/Set;", "Companion", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface StabilityConfigParser {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0014\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityConfigParser$Companion;", "", "<init>", "()V", "fromFile", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityConfigParser;", "filepath", "", "fromLines", "lines", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final StabilityConfigParser fromFile(String filepath) {
            return filepath == null ? new StabilityConfigParserImpl(CollectionsKt.emptyList()) : new StabilityConfigParserImpl(FilesKt.readLines$default(new File(filepath), (Charset) null, 1, (Object) null));
        }

        public final StabilityConfigParser fromLines(List<String> lines) {
            lines.getClass();
            return new StabilityConfigParserImpl(lines);
        }
    }

    Set<FqNameMatcher> getStableTypeMatchers();
}
