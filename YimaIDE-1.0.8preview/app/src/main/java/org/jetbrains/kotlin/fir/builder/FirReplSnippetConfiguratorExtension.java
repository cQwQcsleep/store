package org.jetbrains.kotlin.fir.builder;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.builder.FirFileBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReplSnippetBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBlockBuilder;
import org.jetbrains.kotlin.fir.extensions.FirExtension;
import org.jetbrains.kotlin.fir.extensions.FirExtensionPointName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000  2\u00020\u0001:\u0002 !B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0014\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H&J\"\u0010\u0019\u001a\u00020\u0015*\u00020\u00162\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH&J*\u0010\u001c\u001a\u00020\u0015*\u00020\u001d2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH&J0\u0010\u0019\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u001f0\u001e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH&R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/FirReplSnippetConfiguratorExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "getName", "()Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "extensionType", "Lkotlin/reflect/KClass;", "getExtensionType", "()Lkotlin/reflect/KClass;", "isReplSnippetsSource", Argument.Delimiters.none, "sourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "scriptSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "configureContainingFile", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/builder/FirReplSnippetBuilder;", "fileBuilder", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFileBuilder;", "configure", "context", "Lorg/jetbrains/kotlin/fir/builder/Context;", "configureEvalBody", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirBlockBuilder;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "Companion", "Factory", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirReplSnippetConfiguratorExtension extends FirExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FirExtensionPointName NAME = new FirExtensionPointName("ReplSnippetConfigurator");
    private final KClass<? extends FirExtension> extensionType;

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0003À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/FirReplSnippetConfiguratorExtension$Factory;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension$Factory;", "Lorg/jetbrains/kotlin/fir/builder/FirReplSnippetConfiguratorExtension;", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface Factory extends FirExtension.Factory<FirReplSnippetConfiguratorExtension> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirReplSnippetConfiguratorExtension(FirSession firSession) {
        super(firSession);
        firSession.getClass();
        this.extensionType = Reflection.getOrCreateKotlinClass(FirReplSnippetConfiguratorExtension.class);
    }

    public abstract void configure(List<FirElement> list, KtSourceFile ktSourceFile, KtSourceElement ktSourceElement, Context<?> context);

    public abstract void configure(FirReplSnippetBuilder firReplSnippetBuilder, KtSourceFile ktSourceFile, Context<?> context);

    public abstract void configureContainingFile(FirReplSnippetBuilder firReplSnippetBuilder, FirFileBuilder firFileBuilder);

    public abstract void configureEvalBody(FirBlockBuilder firBlockBuilder, KtSourceFile ktSourceFile, KtSourceElement ktSourceElement, Context<?> context);

    @Override // org.jetbrains.kotlin.fir.extensions.FirExtension
    public final KClass<? extends FirExtension> getExtensionType() {
        return this.extensionType;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirExtension
    public final FirExtensionPointName getName() {
        return NAME;
    }

    public abstract boolean isReplSnippetsSource(KtSourceFile sourceFile, KtSourceElement scriptSource);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/FirReplSnippetConfiguratorExtension$Companion;", Argument.Delimiters.none, "<init>", "()V", "NAME", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "getNAME", "()Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirExtensionPointName getNAME() {
            return FirReplSnippetConfiguratorExtension.NAME;
        }

        private Companion() {
        }
    }
}
