package io.github.rosemoe.sora.langs.textmate.registry.dsl;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0086\u0004R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/github/rosemoe/sora/langs/textmate/registry/dsl/LanguageEmbeddedLanguagesDefinitionBuilder;", "", "map", "", "", "<init>", "(Ljava/util/Map;)V", "to", "", "languageName", "language-textmate_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class LanguageEmbeddedLanguagesDefinitionBuilder {
    private final Map<String, String> map;

    public LanguageEmbeddedLanguagesDefinitionBuilder(Map<String, String> map) {
        map.getClass();
        this.map = map;
    }

    public final void to(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.map.put(str, str2);
    }
}
