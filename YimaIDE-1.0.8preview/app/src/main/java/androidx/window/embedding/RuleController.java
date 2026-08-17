package androidx.window.embedding;

import android.content.Context;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.io.IOException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bJ\u0014\u0010\r\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0006\u0010\u000f\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/embedding/RuleController;", "", "embeddingBackend", "Landroidx/window/embedding/EmbeddingBackend;", "<init>", "(Landroidx/window/embedding/EmbeddingBackend;)V", "getRules", "", "Landroidx/window/embedding/EmbeddingRule;", "addRule", "", "rule", "removeRule", "setRules", "rules", "clearRules", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RuleController {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final EmbeddingBackend embeddingBackend;

    public RuleController(EmbeddingBackend embeddingBackend) {
        embeddingBackend.getClass();
        this.embeddingBackend = embeddingBackend;
    }

    @JvmStatic
    public static final RuleController getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    @JvmStatic
    public static final Set<EmbeddingRule> parseRules(Context context, int i) {
        return INSTANCE.parseRules(context, i);
    }

    public final void addRule(EmbeddingRule rule) {
        rule.getClass();
        this.embeddingBackend.addRule(rule);
    }

    public final void clearRules() {
        this.embeddingBackend.setRules(SetsKt.emptySet());
    }

    public final Set<EmbeddingRule> getRules() {
        return this.embeddingBackend.getRules();
    }

    public final void removeRule(EmbeddingRule rule) {
        rule.getClass();
        this.embeddingBackend.removeRule(rule);
    }

    public final void setRules(Set<? extends EmbeddingRule> rules) {
        rules.getClass();
        this.embeddingBackend.setRules(rules);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"Landroidx/window/embedding/RuleController$Companion;", "", "<init>", "()V", "getInstance", "Landroidx/window/embedding/RuleController;", "context", "Landroid/content/Context;", "parseRules", "", "Landroidx/window/embedding/EmbeddingRule;", "staticRuleResourceId", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final RuleController getInstance(Context context) {
            context.getClass();
            Context applicationContext = context.getApplicationContext();
            EmbeddingBackend.Companion companion = EmbeddingBackend.INSTANCE;
            applicationContext.getClass();
            return new RuleController(companion.getInstance(applicationContext));
        }

        @JvmStatic
        public final Set<EmbeddingRule> parseRules(Context context, int staticRuleResourceId) throws XmlPullParserException, IOException {
            context.getClass();
            RuleParser ruleParser = RuleParser.INSTANCE;
            Context applicationContext = context.getApplicationContext();
            applicationContext.getClass();
            Set<EmbeddingRule> rules$window_release = ruleParser.parseRules$window_release(applicationContext, staticRuleResourceId);
            return rules$window_release == null ? SetsKt.emptySet() : rules$window_release;
        }

        private Companion() {
        }
    }
}
