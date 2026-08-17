package org.jetbrains.kotlin.cli.jvm.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.MainFunctionDetectionKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.java.JavaUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00030\u0001B!\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ&\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016J&\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016J&\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00052\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016J&\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\t2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/FirMainClassFinder;", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "groupedMainFunctions", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "<init>", "(Ljava/util/Map;)V", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "parents", "visitFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "visitRegularClass", "regularClass", "visitNamedFunction", "namedFunction", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirMainClassFinder extends FirVisitor<Unit, Pair<? extends FirDeclaration, ? extends FirRegularClass>> {
    private Map<FirDeclaration, List<FirNamedFunction>> groupedMainFunctions;

    public FirMainClassFinder(Map<FirDeclaration, List<FirNamedFunction>> map) {
        map.getClass();
        this.groupedMainFunctions = map;
    }

    public static String b(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        return JavaUtilsKt.findJvmNameValue(firNamedFunction);
    }

    public static boolean c(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        return JavaUtilsKt.findJvmStaticAnnotation(firNamedFunction) != null;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, Pair<? extends FirDeclaration, ? extends FirRegularClass> pair) {
        visitElement2(firElement, pair);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: visitFile, reason: avoid collision after fix types in other method */
    public void visitFile2(FirFile file, Pair<? extends FirDeclaration, ? extends FirRegularClass> parents) {
        file.getClass();
        parents.getClass();
        file.acceptChildren(this, TuplesKt.to(file, null));
    }

    /* JADX INFO: renamed from: visitNamedFunction, reason: avoid collision after fix types in other method */
    public void visitNamedFunction2(FirNamedFunction namedFunction, Pair<? extends FirDeclaration, ? extends FirRegularClass> parents) {
        namedFunction.getClass();
        parents.getClass();
        if (MainFunctionDetectionKt.isMaybeMainFunction(namedFunction, new Function1() { // from class: org.jetbrains.kotlin.cli.jvm.compiler.b
            public final Object invoke(Object obj) {
                return FirMainClassFinder.b((FirNamedFunction) obj);
            }
        }, new Function1() { // from class: org.jetbrains.kotlin.cli.jvm.compiler.c
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirMainClassFinder.c((FirNamedFunction) obj));
            }
        })) {
            FirDeclaration firDeclaration = (FirDeclaration) parents.component1();
            FirRegularClass firRegularClass = (FirRegularClass) parents.component2();
            if (!(firDeclaration instanceof FirRegularClass) || ((FirRegularClass) firDeclaration).getClassKind() == ClassKind.OBJECT) {
                Map<FirDeclaration, List<FirNamedFunction>> map = this.groupedMainFunctions;
                if (firRegularClass != null) {
                    firDeclaration = firRegularClass;
                }
                List<FirNamedFunction> arrayList = map.get(firDeclaration);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    map.put(firDeclaration, arrayList);
                }
                arrayList.add(namedFunction);
            }
        }
    }

    /* JADX INFO: renamed from: visitRegularClass, reason: avoid collision after fix types in other method */
    public void visitRegularClass2(FirRegularClass regularClass, Pair<? extends FirDeclaration, ? extends FirRegularClass> parents) {
        regularClass.getClass();
        parents.getClass();
        if (regularClass.getIsLocal()) {
            return;
        }
        FirRegularClass firRegularClass = null;
        if (regularClass.getStatus().isCompanion()) {
            Object first = parents.getFirst();
            if (first instanceof FirRegularClass) {
                firRegularClass = (FirRegularClass) first;
            }
        }
        regularClass.acceptChildren(this, TuplesKt.to(regularClass, firRegularClass));
    }

    /* JADX INFO: renamed from: visitElement, reason: avoid collision after fix types in other method */
    public void visitElement2(FirElement element, Pair<? extends FirDeclaration, ? extends FirRegularClass> parents) {
        element.getClass();
        parents.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitFile(FirFile firFile, Pair<? extends FirDeclaration, ? extends FirRegularClass> pair) {
        visitFile2(firFile, pair);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitRegularClass(FirRegularClass firRegularClass, Pair<? extends FirDeclaration, ? extends FirRegularClass> pair) {
        visitRegularClass2(firRegularClass, pair);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitNamedFunction(FirNamedFunction firNamedFunction, Pair<? extends FirDeclaration, ? extends FirRegularClass> pair) {
        visitNamedFunction2(firNamedFunction, pair);
        return Unit.INSTANCE;
    }
}
