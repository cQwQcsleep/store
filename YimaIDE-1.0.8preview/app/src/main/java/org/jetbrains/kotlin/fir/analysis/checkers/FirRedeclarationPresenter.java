package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u0005*\u00060\u0006j\u0002`\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0004\u001a\u00020\u0005*\u00060\u0006j\u0002`\u00072\u0006\u0010\b\u001a\u00020\nH\u0002J\u0018\u0010\u0004\u001a\u00020\u0005*\u00060\u0006j\u0002`\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0002J\u001c\u0010\f\u001a\u00020\u0005*\u00060\u0006j\u0002`\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u0005*\u00060\u0006j\u0002`\u00072\u0006\u0010\b\u001a\u00020\u000fH\u0002J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u000fJ\u0012\u0010\u0010\u001a\u00020\u00112\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0014J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0015J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0016J\u0014\u0010\u0017\u001a\u00020\u00112\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0002J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0018¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirRedeclarationPresenter;", Argument.Delimiters.none, "<init>", "()V", "appendRepresentation", Argument.Delimiters.none, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "it", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/name/CallableId;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "appendRepresentationBeforeCallableId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "appendValueParameters", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "represent", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "representClassLike", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "owner", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRedeclarationPresenter {
    public static final FirRedeclarationPresenter INSTANCE = new FirRedeclarationPresenter();

    private FirRedeclarationPresenter() {
    }

    private final void appendRepresentation(StringBuilder sb, CallableId callableId) {
        sb.append(callableId.getPackageName().asString());
        sb.append('/');
        if (callableId.getClassName() != null) {
            sb.append(callableId.getClassName());
            sb.append('.');
        }
        sb.append(callableId.getCallableName());
    }

    private final void appendRepresentationBeforeCallableId(StringBuilder sb, FirCallableSymbol<?> firCallableSymbol) {
        if (FirSymbolStatusUtilsKt.isCompanionExtension(firCallableSymbol)) {
            sb.append('#');
        }
        sb.append('<');
        int size = firCallableSymbol.getTypeParameterSymbols().size();
        for (int i = 0; i < size; i++) {
            sb.append(',');
        }
        sb.append('>');
        sb.append('[');
        if (firCallableSymbol.getReceiverParameterSymbol() != null) {
            sb.append(',');
        }
        sb.append(']');
    }

    private final void appendValueParameters(StringBuilder sb, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        sb.append('(');
        Iterator<T> it = firNamedFunctionSymbol.getValueParameterSymbols().iterator();
        while (it.hasNext()) {
            INSTANCE.appendRepresentation(sb, (FirValueParameterSymbol) it.next());
            sb.append(',');
        }
        sb.append(')');
    }

    private final String representClassLike(FirClassLikeSymbol<?> it) {
        StringBuilder sb = new StringBuilder();
        sb.append("<>[]");
        INSTANCE.appendRepresentation(sb, it.getClassId());
        return sb.toString();
    }

    public final String represent(FirConstructorSymbol it, FirClassLikeSymbol<?> owner) {
        it.getClass();
        owner.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        int size = it.getTypeParameterSymbols().size();
        for (int i = 0; i < size; i++) {
            sb.append(',');
        }
        sb.append(">[]");
        INSTANCE.appendRepresentation(sb, owner.getClassId());
        sb.append('(');
        Iterator<T> it2 = it.getValueParameterSymbols().iterator();
        while (it2.hasNext()) {
            INSTANCE.appendRepresentation(sb, (FirValueParameterSymbol) it2.next());
            sb.append(',');
        }
        sb.append(')');
        return sb.toString();
    }

    private final void appendRepresentation(StringBuilder sb, ClassId classId) {
        sb.append(classId.getPackageFqName().asString());
        sb.append('/');
        sb.append(classId.getRelativeClassName().asString());
    }

    private final void appendRepresentation(StringBuilder sb, FirValueParameterSymbol firValueParameterSymbol) {
        if (firValueParameterSymbol.isVararg()) {
            sb.append("vararg ");
        }
    }

    public final String represent(FirNamedFunctionSymbol it) {
        it.getClass();
        StringBuilder sb = new StringBuilder();
        FirRedeclarationPresenter firRedeclarationPresenter = INSTANCE;
        firRedeclarationPresenter.appendRepresentationBeforeCallableId(sb, it);
        firRedeclarationPresenter.appendRepresentation(sb, it.getCallableId());
        firRedeclarationPresenter.appendValueParameters(sb, it);
        return sb.toString();
    }

    public final String represent(FirVariableSymbol<?> it) {
        it.getClass();
        StringBuilder sb = new StringBuilder();
        FirRedeclarationPresenter firRedeclarationPresenter = INSTANCE;
        firRedeclarationPresenter.appendRepresentationBeforeCallableId(sb, it);
        CallableId callableId = it.getCallableId();
        callableId.getClass();
        firRedeclarationPresenter.appendRepresentation(sb, callableId);
        if (it instanceof FirFieldSymbol) {
            sb.append("#f");
        }
        return sb.toString();
    }

    public final String represent(FirTypeAliasSymbol it) {
        it.getClass();
        return representClassLike(it);
    }

    public final String represent(FirRegularClassSymbol it) {
        it.getClass();
        return representClassLike(it);
    }

    public final String represent(FirBasedSymbol<?> declaration) {
        declaration.getClass();
        if (declaration instanceof FirNamedFunctionSymbol) {
            return represent((FirNamedFunctionSymbol) declaration);
        }
        if (declaration instanceof FirRegularClassSymbol) {
            return represent((FirRegularClassSymbol) declaration);
        }
        if (declaration instanceof FirTypeAliasSymbol) {
            return represent((FirTypeAliasSymbol) declaration);
        }
        if (declaration instanceof FirPropertySymbol) {
            return represent((FirVariableSymbol<?>) declaration);
        }
        return null;
    }
}
