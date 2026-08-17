package org.jetbrains.kotlin.cli.common;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ActionsKt;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Disposer;
import java.io.File;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtSourceFileLinesMapping;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocationWithRange;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageUtil;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.IncrementalCompilation;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNamesUtilKt;
import org.jetbrains.kotlin.platform.SimplePlatform;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.TargetPlatformKt;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.KtPackageDirective;
import org.jetbrains.kotlin.util.PerformanceManagerImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001aL\u0010\u0005\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\r0\f2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u0002H\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\f\u001a\u001c\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00110\n\u001a\u001c\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00130\n\u001a\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0013H\u0002\u001a)\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019*\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\u0010\u001c\u001a \u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001aH\u0002\u001aM\u0010 \u001a\u0004\u0018\u00010!\"\b\b\u0000\u0010\"*\u00020#2\b\u0010$\u001a\u0004\u0018\u0001H\"2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u00020!0\f2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010(\u001a\u00020'¢\u0006\u0002\u0010)\u001a\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-\u001a\u000e\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201¨\u00062"}, d2 = {"incrementalCompilationIsEnabled", Argument.Delimiters.none, "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "incrementalCompilationIsEnabledForJs", "checkKotlinPackageUsage", "F", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "files", Argument.Delimiters.none, "getPackage", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/name/FqName;", "getMessageLocation", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "checkKotlinPackageUsageForPsi", "Lorg/jetbrains/kotlin/psi/KtFile;", "checkKotlinPackageUsageForLightTree", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getLocationWithin", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocationWithRange;", "Lorg/jetbrains/kotlin/KtSourceElement;", "file", "getLineAndColumnStartingWithOnesAt", "Lkotlin/Pair;", Argument.Delimiters.none, "offset", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;Ljava/lang/Integer;)Lkotlin/Pair;", "getLineAndColumnByOffsetStartingWithOnes", "Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "startOffset", "getLibraryFromHome", "Ljava/io/File;", "PathProvider", Argument.Delimiters.none, "paths", "getLibrary", "libraryName", Argument.Delimiters.none, "noLibraryArgument", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/String;)Ljava/io/File;", "createPerformanceManagerFor", "Lorg/jetbrains/kotlin/util/PerformanceManagerImpl;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "disposeRootInWriteAction", Argument.Delimiters.none, "disposable", "Lcom/intellij/openapi/Disposable;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UtilsKt {
    public static FqName a(KtFile ktFile) {
        ktFile.getClass();
        return ktFile.getPackageFqName();
    }

    public static Unit b(Disposable disposable) {
        Disposer.dispose(disposable);
        return Unit.INSTANCE;
    }

    public static FqName c(FirFile firFile) {
        firFile.getClass();
        return org.jetbrains.kotlin.fir.UtilsKt.getPackageFqName(firFile);
    }

    public static final <F> boolean checkKotlinPackageUsage(CompilerConfiguration compilerConfiguration, Collection<? extends F> collection, Function1<? super F, FqName> function1, Function1<? super F, ? extends CompilerMessageSourceLocation> function2) {
        compilerConfiguration.getClass();
        collection.getClass();
        function1.getClass();
        function2.getClass();
        if (compilerConfiguration.getBoolean(CLIConfigurationKeys.ALLOW_KOTLIN_PACKAGE)) {
            return true;
        }
        FqName fqName = new FqName("kotlin");
        for (F f : collection) {
            if (FqNamesUtilKt.isSubpackageOf((FqName) function1.invoke(f), fqName)) {
                CliDiagnosticReportingKt.report(compilerConfiguration, CliDiagnostics.INSTANCE.getKOTLIN_PACKAGE_USAGE(), "Only the Kotlin standard library is allowed to use the 'kotlin' package", (CompilerMessageSourceLocation) function2.invoke(f));
                return false;
            }
        }
        return true;
    }

    public static final boolean checkKotlinPackageUsageForLightTree(CompilerConfiguration compilerConfiguration, Collection<? extends FirFile> collection) {
        compilerConfiguration.getClass();
        collection.getClass();
        return checkKotlinPackageUsage(compilerConfiguration, collection, new Function1() { // from class: i2f
            public final Object invoke(Object obj) {
                return UtilsKt.c((FirFile) obj);
            }
        }, new Function1() { // from class: k2f
            public final Object invoke(Object obj) {
                return UtilsKt.e((FirFile) obj);
            }
        });
    }

    public static final boolean checkKotlinPackageUsageForPsi(CompilerConfiguration compilerConfiguration, Collection<? extends KtFile> collection) {
        compilerConfiguration.getClass();
        collection.getClass();
        return checkKotlinPackageUsage(compilerConfiguration, collection, new Function1() { // from class: m2f
            public final Object invoke(Object obj) {
                return UtilsKt.a((KtFile) obj);
            }
        }, new Function1() { // from class: o2f
            public final Object invoke(Object obj) {
                return UtilsKt.d((KtFile) obj);
            }
        });
    }

    public static final PerformanceManagerImpl createPerformanceManagerFor(TargetPlatform targetPlatform) {
        targetPlatform.getClass();
        StringBuilder sb = new StringBuilder("Kotlin to ");
        sb.append(TargetPlatformKt.isCommon(targetPlatform) ? "Metadata" : ((SimplePlatform) CollectionsKt.first(targetPlatform)).getPlatformName());
        sb.append(" compiler");
        return new PerformanceManagerImpl(targetPlatform, sb.toString());
    }

    public static CompilerMessageSourceLocation d(KtFile ktFile) {
        ktFile.getClass();
        KtPackageDirective packageDirective = ktFile.getPackageDirective();
        packageDirective.getClass();
        return MessageUtil.psiElementToMessageLocation(packageDirective);
    }

    public static final void disposeRootInWriteAction(final Disposable disposable) {
        disposable.getClass();
        if (ApplicationManager.getApplication() != null) {
            ActionsKt.runWriteAction(new Function0() { // from class: q2f
                public final Object invoke() {
                    return UtilsKt.b(disposable);
                }
            });
        } else {
            Disposer.dispose(disposable);
        }
    }

    public static CompilerMessageSourceLocation e(FirFile firFile) {
        firFile.getClass();
        KtSourceElement source = firFile.getPackageDirective().getSource();
        if (source != null) {
            return getLocationWithin(source, firFile);
        }
        return null;
    }

    public static final <PathProvider> File getLibraryFromHome(PathProvider pathprovider, Function1<? super PathProvider, ? extends File> function1, String str, CompilerConfiguration compilerConfiguration, String str2) {
        function1.getClass();
        str.getClass();
        compilerConfiguration.getClass();
        str2.getClass();
        if (pathprovider != null) {
            File file = (File) function1.invoke(pathprovider);
            if (file.exists()) {
                return file;
            }
        }
        CliDiagnosticReportingKt.report(compilerConfiguration, CliDiagnostics.INSTANCE.getROOTS_RESOLUTION_WARNING(), "Unable to find " + str + " in the Kotlin home directory. Pass either " + str2 + " to prevent adding it to the classpath, or the correct '-kotlin-home'", null);
        return null;
    }

    private static final Pair<Integer, Integer> getLineAndColumnByOffsetStartingWithOnes(KtSourceFileLinesMapping ktSourceFileLinesMapping, int i) {
        Pair lineAndColumnByOffset = ktSourceFileLinesMapping.getLineAndColumnByOffset(i);
        return TuplesKt.to(Integer.valueOf(((Number) lineAndColumnByOffset.component1()).intValue() + 1), Integer.valueOf(((Number) lineAndColumnByOffset.component2()).intValue() + 1));
    }

    private static final Pair<Integer, Integer> getLineAndColumnStartingWithOnesAt(FirFile firFile, Integer num) {
        if (num != null) {
            int iIntValue = num.intValue();
            KtSourceFileLinesMapping sourceFileLinesMapping = firFile.getSourceFileLinesMapping();
            if (sourceFileLinesMapping != null) {
                return getLineAndColumnByOffsetStartingWithOnes(sourceFileLinesMapping, iIntValue);
            }
        }
        return null;
    }

    private static final CompilerMessageLocationWithRange getLocationWithin(KtSourceElement ktSourceElement, FirFile firFile) {
        Pair<Integer, Integer> lineAndColumnStartingWithOnesAt;
        KtSourceFile sourceFile = firFile.getSourceFile();
        if (sourceFile == null || (lineAndColumnStartingWithOnesAt = getLineAndColumnStartingWithOnesAt(firFile, Integer.valueOf(ktSourceElement.getStartOffset()))) == null) {
            return null;
        }
        int iIntValue = ((Number) lineAndColumnStartingWithOnesAt.component1()).intValue();
        int iIntValue2 = ((Number) lineAndColumnStartingWithOnesAt.component2()).intValue();
        Pair<Integer, Integer> lineAndColumnStartingWithOnesAt2 = getLineAndColumnStartingWithOnesAt(firFile, Integer.valueOf(ktSourceElement.getEndOffset()));
        if (lineAndColumnStartingWithOnesAt2 == null) {
            return null;
        }
        int iIntValue3 = ((Number) lineAndColumnStartingWithOnesAt2.component1()).intValue();
        int iIntValue4 = ((Number) lineAndColumnStartingWithOnesAt2.component2()).intValue();
        CompilerMessageLocationWithRange.Companion companion = CompilerMessageLocationWithRange.INSTANCE;
        String path = sourceFile.getPath();
        Integer numValueOf = Integer.valueOf(iIntValue3);
        Integer numValueOf2 = Integer.valueOf(iIntValue4);
        CharSequence text = KtSourceElementKt.getText(ktSourceElement);
        return companion.create(path, iIntValue, iIntValue2, numValueOf, numValueOf2, text != null ? text.toString() : null);
    }

    public static final boolean incrementalCompilationIsEnabled(CommonCompilerArguments commonCompilerArguments) {
        commonCompilerArguments.getClass();
        Boolean incrementalCompilation = commonCompilerArguments.getIncrementalCompilation();
        return incrementalCompilation != null ? incrementalCompilation.booleanValue() : IncrementalCompilation.isEnabledForJvm();
    }

    public static final boolean incrementalCompilationIsEnabledForJs(CommonCompilerArguments commonCompilerArguments) {
        commonCompilerArguments.getClass();
        Boolean incrementalCompilation = commonCompilerArguments.getIncrementalCompilation();
        return incrementalCompilation != null ? incrementalCompilation.booleanValue() : IncrementalCompilation.isEnabledForJs();
    }
}
