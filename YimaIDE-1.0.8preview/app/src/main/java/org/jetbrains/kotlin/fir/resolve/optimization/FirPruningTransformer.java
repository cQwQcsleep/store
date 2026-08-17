package org.jetbrains.kotlin.fir.resolve.optimization;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0019\u0012\u0010\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\b\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\n2\u0006\u0010\u000b\u001a\u0002H\t2\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\rJ\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u0018\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/optimization/FirPruningTransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "reachable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "<init>", "(Ljava/util/Set;)V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Void;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "shouldKeep", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPruningTransformer extends FirTransformer {
    private final Set<FirBasedSymbol<?>> reachable;

    /* JADX WARN: Multi-variable type inference failed */
    public FirPruningTransformer(Set<? extends FirBasedSymbol<?>> set) {
        set.getClass();
        this.reachable = set;
    }

    private final boolean shouldKeep(FirDeclaration declaration) {
        return this.reachable.contains(declaration.getSymbol());
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, Void data) {
        file.getClass();
        List<FirDeclaration> declarations = file.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (shouldKeep((FirDeclaration) obj)) {
                arrayList.add(obj);
            }
        }
        file.replaceDeclarations(arrayList);
        file.transformDeclarations(this, data);
        return file;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformRegularClass(FirRegularClass regularClass, Void data) {
        regularClass.getClass();
        List<FirDeclaration> declarations = regularClass.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (shouldKeep((FirDeclaration) obj)) {
                arrayList.add(obj);
            }
        }
        regularClass.replaceDeclarations(arrayList);
        regularClass.transformDeclarations((FirTransformer<? super Void>) this, data);
        return regularClass;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, Void data) {
        element.getClass();
        return element;
    }
}
