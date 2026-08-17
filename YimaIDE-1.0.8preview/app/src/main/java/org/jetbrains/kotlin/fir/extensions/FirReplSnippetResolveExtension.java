package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.scopes.FirScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0003H&J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H&R\u001c\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirReplSnippetResolveExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "componentClass", "Lkotlin/reflect/KClass;", "getComponentClass", "()Lkotlin/reflect/KClass;", "getSnippetDefaultImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "sourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "snippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "getSnippetScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "currentSnippet", "useSiteSession", "updateResolved", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirReplSnippetResolveExtension extends FirExtensionSessionComponent {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirReplSnippetResolveExtension(FirSession firSession) {
        super(firSession);
        firSession.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirExtensionSessionComponent
    public KClass<? extends FirExtensionSessionComponent> getComponentClass() {
        return Reflection.getOrCreateKotlinClass(FirReplSnippetResolveExtension.class);
    }

    public abstract List<FirImport> getSnippetDefaultImports(KtSourceFile sourceFile, FirReplSnippet snippet);

    public abstract FirScope getSnippetScope(FirReplSnippet currentSnippet, FirSession useSiteSession);

    public abstract void updateResolved(FirReplSnippet snippet);
}
