package org.jetbrains.kotlin.fir.lightTree;

import com.intellij.lang.LighterASTNode;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Charsets;
import org.jetbrains.kotlin.KtIoFileSourceFile;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtSourceFileLinesMapping;
import org.jetbrains.kotlin.KtSourceFileLinesMappingKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.lightTree.converter.LightTreeRawFirDeclarationBuilder;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.parsing.KotlinLightParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J$\u0010\f\u001a\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/LightTree2Fir;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "diagnosticsReporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "buildFirFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", ModuleXmlParser.PATH, "Ljava/nio/file/Path;", "file", "Ljava/io/File;", "lightTree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "Lcom/intellij/lang/LighterASTNode;", "sourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "linesMapping", "Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "code", Argument.Delimiters.none, "makeErrorListener", "Lorg/jetbrains/kotlin/parsing/KotlinLightParser$LightTreeParsingErrorListener;", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LightTree2Fir {
    private final DiagnosticReporter diagnosticsReporter;
    private final FirScopeProvider scopeProvider;
    private final FirSession session;

    public LightTree2Fir(FirSession firSession, FirScopeProvider firScopeProvider, DiagnosticReporter diagnosticReporter) {
        firSession.getClass();
        firScopeProvider.getClass();
        this.session = firSession;
        this.scopeProvider = firScopeProvider;
        this.diagnosticsReporter = diagnosticReporter;
    }

    private final KotlinLightParser.LightTreeParsingErrorListener makeErrorListener(KtSourceFile sourceFile) {
        DiagnosticReporter diagnosticReporter = this.diagnosticsReporter;
        if (diagnosticReporter == null) {
            return null;
        }
        return LightTreeParsingErrorListenerKt.toKotlinParsingErrorListener(diagnosticReporter, sourceFile, FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session));
    }

    public final FirFile buildFirFile(File file) {
        file.getClass();
        KtIoFileSourceFile ktIoFileSourceFile = new KtIoFileSourceFile(file);
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), Charsets.UTF_8);
        try {
            Pair<CharSequence, KtSourceFileLinesMapping> sourceFileWithMapping = KtSourceFileLinesMappingKt.readSourceFileWithMapping(inputStreamReader);
            CloseableKt.closeFinally(inputStreamReader, (Throwable) null);
            return buildFirFile((CharSequence) sourceFileWithMapping.component1(), ktIoFileSourceFile, (KtSourceFileLinesMapping) sourceFileWithMapping.component2());
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(inputStreamReader, th);
                throw th2;
            }
        }
    }

    public final FirSession getSession() {
        return this.session;
    }

    public /* synthetic */ LightTree2Fir(FirSession firSession, FirScopeProvider firScopeProvider, DiagnosticReporter diagnosticReporter, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firScopeProvider, (i & 4) != 0 ? null : diagnosticReporter);
    }

    public final FirFile buildFirFile(Path path) {
        path.getClass();
        File file = path.toFile();
        file.getClass();
        return buildFirFile(file);
    }

    public final FirFile buildFirFile(FlyweightCapableTreeStructure<LighterASTNode> lightTree, KtSourceFile sourceFile, KtSourceFileLinesMapping linesMapping) {
        lightTree.getClass();
        sourceFile.getClass();
        linesMapping.getClass();
        LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder = new LightTreeRawFirDeclarationBuilder(this.session, this.scopeProvider, lightTree, null, 8, null);
        Object root = lightTree.getRoot();
        root.getClass();
        return lightTreeRawFirDeclarationBuilder.convertFile((LighterASTNode) root, sourceFile, linesMapping);
    }

    public final FirFile buildFirFile(CharSequence code, KtSourceFile sourceFile, KtSourceFileLinesMapping linesMapping) {
        code.getClass();
        sourceFile.getClass();
        linesMapping.getClass();
        return buildFirFile(KotlinLightParser.INSTANCE.buildLightTree(code, sourceFile, makeErrorListener(sourceFile)), sourceFile, linesMapping);
    }
}
