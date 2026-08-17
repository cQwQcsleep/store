package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtSourceFileLinesMapping;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirCliExceptionHandler;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.util.AnalysisExceptionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirCliExceptionHandler;", "Lorg/jetbrains/kotlin/fir/FirExceptionHandler;", "<init>", "()V", "handleExceptionOnElementAnalysis", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "throwable", Argument.Delimiters.none, "handleExceptionOnFileAnalysis", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCliExceptionHandler extends FirExceptionHandler {
    public static final FirCliExceptionHandler INSTANCE = new FirCliExceptionHandler();

    private FirCliExceptionHandler() {
    }

    public static Pair a(FirFile firFile, int i) {
        KtSourceFileLinesMapping sourceFileLinesMapping = firFile.getSourceFileLinesMapping();
        if (sourceFileLinesMapping != null) {
            return sourceFileLinesMapping.getLineAndColumnByOffset(i);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.FirExceptionHandler
    public Void handleExceptionOnElementAnalysis(FirElement element, Throwable throwable) throws Throwable {
        element.getClass();
        throwable.getClass();
        throw AnalysisExceptionsKt.wrapIntoSourceCodeAnalysisExceptionIfNeeded(throwable, element.getSource());
    }

    @Override // org.jetbrains.kotlin.fir.FirExceptionHandler
    public Void handleExceptionOnFileAnalysis(final FirFile file, Throwable throwable) throws Throwable {
        file.getClass();
        throwable.getClass();
        KtSourceFile sourceFile = file.getSourceFile();
        throw AnalysisExceptionsKt.wrapIntoFileAnalysisExceptionIfNeeded(throwable, sourceFile != null ? sourceFile.getPath() : null, file.getSource(), new Function1() { // from class: qz4
            public final Object invoke(Object obj) {
                return FirCliExceptionHandler.a(file, ((Integer) obj).intValue());
            }
        });
    }
}
