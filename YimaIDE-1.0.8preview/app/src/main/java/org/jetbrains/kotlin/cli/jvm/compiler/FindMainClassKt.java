package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.psi.util.PsiTreeUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.FindMainClassKt;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fileClasses.JvmFileClassUtil;
import org.jetbrains.kotlin.idea.MainFunctionDetector;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtDeclarationContainer;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.KtNamedFunction;
import org.jetbrains.kotlin.psi.KtObjectDeclaration;
import org.jetbrains.kotlin.resolve.BindingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u001a\u0016\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u000e"}, d2 = {"findMainClass", "Lorg/jetbrains/kotlin/name/FqName;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "files", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "findMainFunction", "Lorg/jetbrains/kotlin/psi/KtNamedFunction;", "Lorg/jetbrains/kotlin/idea/MainFunctionDetector;", "container", "Lorg/jetbrains/kotlin/psi/KtDeclarationContainer;", "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FindMainClassKt {
    public static FqName a(MainFunctionDetector mainFunctionDetector, KtFile ktFile) {
        FqName fqNameParent;
        ktFile.getClass();
        KtNamedFunction ktNamedFunctionFindMainFunction = findMainFunction(mainFunctionDetector, ktFile);
        if (ktNamedFunctionFindMainFunction == null) {
            return null;
        }
        if (ktNamedFunctionFindMainFunction.isTopLevel()) {
            return JvmFileClassUtil.getFileClassInfoNoResolve(ktFile).getFacadeClassFqName();
        }
        KtObjectDeclaration ktObjectDeclaration = (KtClassOrObject) PsiTreeUtil.getParentOfType(ktNamedFunctionFindMainFunction, KtClassOrObject.class, true);
        if (!(ktObjectDeclaration instanceof KtObjectDeclaration) || !ktObjectDeclaration.isCompanion()) {
            FqName fqName = ktNamedFunctionFindMainFunction.getFqName();
            if (fqName != null) {
                return fqName.parent();
            }
            return null;
        }
        FqName fqName2 = ktNamedFunctionFindMainFunction.getFqName();
        if (fqName2 == null || (fqNameParent = fqName2.parent()) == null) {
            return null;
        }
        return fqNameParent.parent();
    }

    public static final FqName findMainClass(BindingContext bindingContext, LanguageVersionSettings languageVersionSettings, List<? extends KtFile> list) {
        bindingContext.getClass();
        languageVersionSettings.getClass();
        list.getClass();
        final MainFunctionDetector mainFunctionDetector = new MainFunctionDetector(bindingContext, languageVersionSettings);
        Object obj = null;
        boolean z = false;
        Object obj2 = null;
        for (Object obj3 : SequencesKt.map(CollectionsKt.asSequence(list), new Function1() { // from class: xs4
            public final Object invoke(Object obj4) {
                return FindMainClassKt.a(mainFunctionDetector, (KtFile) obj4);
            }
        })) {
            if (((FqName) obj3) != null) {
                if (z) {
                    return (FqName) obj;
                }
                z = true;
                obj2 = obj3;
            }
        }
        if (z) {
            obj = obj2;
        }
        return (FqName) obj;
    }

    private static final KtNamedFunction findMainFunction(MainFunctionDetector mainFunctionDetector, KtDeclarationContainer ktDeclarationContainer) {
        MainFunctionDetector mainFunctionDetector2;
        List<KtDeclaration> declarations = ktDeclarationContainer.getDeclarations();
        declarations.getClass();
        ArrayList arrayList = new ArrayList();
        for (KtDeclaration ktDeclaration : declarations) {
            KtNamedFunction ktNamedFunctionFindMainFunction = null;
            if (ktDeclaration instanceof KtNamedFunction) {
                mainFunctionDetector2 = mainFunctionDetector;
                if (!MainFunctionDetector.isMain$default(mainFunctionDetector2, (KtNamedFunction) ktDeclaration, false, false, 6, (Object) null)) {
                    ktDeclaration = null;
                }
                ktNamedFunctionFindMainFunction = (KtNamedFunction) ktDeclaration;
            } else {
                mainFunctionDetector2 = mainFunctionDetector;
                if (ktDeclaration instanceof KtDeclarationContainer) {
                    ktNamedFunctionFindMainFunction = findMainFunction(mainFunctionDetector2, (KtDeclarationContainer) ktDeclaration);
                }
            }
            if (ktNamedFunctionFindMainFunction != null) {
                arrayList.add(ktNamedFunctionFindMainFunction);
            }
            mainFunctionDetector = mainFunctionDetector2;
        }
        return (KtNamedFunction) CollectionsKt.singleOrNull(arrayList);
    }
}
