package io.github.rosemoe.sora.langs.textmate.registry.dsl;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"languages", "Lio/github/rosemoe/sora/langs/textmate/registry/dsl/LanguageDefinitionListBuilder;", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "language-textmate_release"}, k = 2, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class GrammarDefinitionDSLKt {
    public static final LanguageDefinitionListBuilder languages(Function1<? super LanguageDefinitionListBuilder, Unit> function1) {
        function1.getClass();
        LanguageDefinitionListBuilder languageDefinitionListBuilder = new LanguageDefinitionListBuilder();
        function1.invoke(languageDefinitionListBuilder);
        return languageDefinitionListBuilder;
    }
}
