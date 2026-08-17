package io.github.rosemoe.sora.langs.textmate.registry.dsl;

import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.model.DefaultGrammarDefinition;
import io.github.rosemoe.sora.langs.textmate.registry.model.GrammarDefinition;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.eclipse.tm4e.core.registry.IGrammarSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\f¢\u0006\u0002\b\rJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lio/github/rosemoe/sora/langs/textmate/registry/dsl/LanguageDefinitionListBuilder;", "", "<init>", "()V", "allBuilder", "", "Lio/github/rosemoe/sora/langs/textmate/registry/dsl/LanguageDefinitionBuilder;", "language", "", "name", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "build", "", "Lio/github/rosemoe/sora/langs/textmate/registry/model/GrammarDefinition;", "language-textmate_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class LanguageDefinitionListBuilder {
    private final List<LanguageDefinitionBuilder> allBuilder = new ArrayList();

    public final List<GrammarDefinition> build() {
        List<LanguageDefinitionBuilder> list = this.allBuilder;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (LanguageDefinitionBuilder languageDefinitionBuilder : list) {
            arrayList.add(DefaultGrammarDefinition.withLanguageConfiguration(IGrammarSource.fromInputStream(FileProviderRegistry.getInstance().tryGetInputStream(languageDefinitionBuilder.getGrammar()), languageDefinitionBuilder.getGrammar(), Charset.defaultCharset()), languageDefinitionBuilder.getLanguageConfiguration(), languageDefinitionBuilder.getName(), languageDefinitionBuilder.getScopeName()).withEmbeddedLanguages(languageDefinitionBuilder.getEmbeddedLanguages()));
        }
        return arrayList;
    }

    public final void language(String name, Function1<? super LanguageDefinitionBuilder, Unit> block) {
        name.getClass();
        block.getClass();
        List<LanguageDefinitionBuilder> list = this.allBuilder;
        LanguageDefinitionBuilder languageDefinitionBuilder = new LanguageDefinitionBuilder(name);
        block.invoke(languageDefinitionBuilder);
        list.add(languageDefinitionBuilder);
    }
}
