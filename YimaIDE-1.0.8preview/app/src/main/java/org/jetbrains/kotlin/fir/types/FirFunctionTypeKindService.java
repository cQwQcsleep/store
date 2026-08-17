package org.jetbrains.kotlin.fir.types;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindExtractor;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011J\u0006\u0010\u0012\u001a\u00020\u000fJ\u0016\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0015H&J\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00172\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0015H&J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u00172\u0006\u0010\u0019\u001a\u00020\u001aH&J \u0010\u001b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0017H&R\u0012\u0010\u0004\u001a\u00020\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeKindService;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "extractor", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKindExtractor;", "getExtractor", "()Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKindExtractor;", "getKindByClassNamePrefix", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "className", Argument.Delimiters.none, "hasKindWithSpecificPackage", Argument.Delimiters.none, "getFunctionKindPackageNames", Argument.Delimiters.none, "hasExtensionKinds", "extractSingleSpecialKindForFunction", "functionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "extractAllSpecialKindsForFunction", Argument.Delimiters.none, "extractAllSpecialKindsForFunctionTypeRef", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "extractSingleExtensionKindForDeserializedConeType", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirFunctionTypeKindService implements FirSessionComponent {
    public abstract List<FunctionTypeKind> extractAllSpecialKindsForFunction(FirFunctionSymbol<?> functionSymbol);

    public abstract List<FunctionTypeKind> extractAllSpecialKindsForFunctionTypeRef(FirFunctionTypeRef typeRef);

    public abstract FunctionTypeKind extractSingleExtensionKindForDeserializedConeType(ClassId classId, List<? extends FirAnnotation> annotations);

    public abstract FunctionTypeKind extractSingleSpecialKindForFunction(FirFunctionSymbol<?> functionSymbol);

    public abstract FunctionTypeKindExtractor getExtractor();

    public final Set<FqName> getFunctionKindPackageNames() {
        return getExtractor().getFunctionKindPackageNames();
    }

    public final FunctionTypeKind getKindByClassNamePrefix(FqName packageFqName, String className) {
        packageFqName.getClass();
        className.getClass();
        FunctionTypeKindExtractor.KindWithArity functionalClassKindWithArity = getExtractor().getFunctionalClassKindWithArity(packageFqName, className);
        if (functionalClassKindWithArity != null) {
            return functionalClassKindWithArity.getKind();
        }
        return null;
    }

    public final boolean hasExtensionKinds() {
        return getExtractor().hasExtensionKinds();
    }

    public final boolean hasKindWithSpecificPackage(FqName packageFqName) {
        packageFqName.getClass();
        return getExtractor().hasKindWithSpecificPackage(packageFqName);
    }
}
