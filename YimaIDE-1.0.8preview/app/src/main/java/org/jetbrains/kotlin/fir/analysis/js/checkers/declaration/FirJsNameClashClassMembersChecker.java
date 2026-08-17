package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsStableName;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsStableNameKt;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNameClashClassMembersChecker;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.utils.CollectionsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0004\u001f !\"B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t*\b\u0012\u0004\u0012\u00020\n0\u000b2\u0006\u0010\f\u001a\u00020\u0002H\u0002J=\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b*\b\u0012\u0004\u0012\u00020\n0\u000b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00130\u0012H\u0002R\u00020\u000fj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0014J-\u0010\u0015\u001a\u0004\u0018\u00010\u000e*\b\u0012\u0004\u0012\u00020\n0\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u00020\u000fj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0019J-\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\u000fR\u00020\u001cj\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0002\u0010\u001e\u0082\u0001\u0002#$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "filterFakeOverrideNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName;", Argument.Delimiters.none, "declaration", "collectNonFakeOverrideClashes", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$ClashedSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "isFakeOverrideName", "Lkotlin/Function1;", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "findFirstFakeOverrideClash", Argument.Delimiters.none, "stableNameCollector", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$StableNamesCollector;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$StableNamesCollector;)Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$ClashedSymbol;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "Regular", "ForExpectClass", "StableNamesCollector", "ClashedSymbol", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$Regular;", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirJsNameClashClassMembersChecker extends FirDeclarationChecker<FirClass> {

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u0013\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0005HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\u0012\b\u0002\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$ClashedSymbol;", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "clashedWith", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Ljava/util/List;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getClashedWith", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ClashedSymbol {
        private final List<FirBasedSymbol<?>> clashedWith;
        private final FirBasedSymbol<?> symbol;

        /* JADX WARN: Multi-variable type inference failed */
        public ClashedSymbol(FirBasedSymbol<?> firBasedSymbol, List<? extends FirBasedSymbol<?>> list) {
            firBasedSymbol.getClass();
            list.getClass();
            this.symbol = firBasedSymbol;
            this.clashedWith = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ClashedSymbol copy$default(ClashedSymbol clashedSymbol, FirBasedSymbol firBasedSymbol, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                firBasedSymbol = clashedSymbol.symbol;
            }
            if ((i & 2) != 0) {
                list = clashedSymbol.clashedWith;
            }
            return clashedSymbol.copy(firBasedSymbol, list);
        }

        public final FirBasedSymbol<?> component1() {
            return this.symbol;
        }

        public final List<FirBasedSymbol<?>> component2() {
            return this.clashedWith;
        }

        public final ClashedSymbol copy(FirBasedSymbol<?> symbol, List<? extends FirBasedSymbol<?>> clashedWith) {
            symbol.getClass();
            clashedWith.getClass();
            return new ClashedSymbol(symbol, clashedWith);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ClashedSymbol)) {
                return false;
            }
            ClashedSymbol clashedSymbol = (ClashedSymbol) other;
            return Intrinsics.areEqual(this.symbol, clashedSymbol.symbol) && Intrinsics.areEqual(this.clashedWith, clashedSymbol.clashedWith);
        }

        public final List<FirBasedSymbol<?>> getClashedWith() {
            return this.clashedWith;
        }

        public final FirBasedSymbol<?> getSymbol() {
            return this.symbol;
        }

        public int hashCode() {
            return (this.symbol.hashCode() * 31) + this.clashedWith.hashCode();
        }

        public String toString() {
            return "ClashedSymbol(symbol=" + this.symbol + ", clashedWith=" + this.clashedWith + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirJsNameClashClassMembersChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNameClashClassMembersChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firClass);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirJsNameClashClassMembersChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNameClashClassMembersChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                return;
            }
            super.check(checkerContext, diagnosticReporter, firClass);
        }
    }

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0013*\u00020\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J?\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b2\f\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000bH\u0002R\u00020\u0018j\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0002\u0010\u001cJ;\u0010\u001d\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00060\u00052\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002R\u00020\u0018j\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0002\u0010\u001cJ)\u0010\u001e\u001a\u00020\u00172\u0010\u0010!\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\"R\u00020\u001fj\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0002\u0010#J\u001f\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&R\u00020\u0018j\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0002\u0010'R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bRe\u0010\t\u001aV\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u001c\u0012\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\fj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b`\r0\nj*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u001c\u0012\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\fj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b`\r`\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashClassMembersChecker$StableNamesCollector;", Argument.Delimiters.none, "<init>", "()V", "jsStableNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName;", "getJsStableNames", "()Ljava/util/Set;", "overrideIntersections", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "Lkotlin/collections/HashMap;", "getOverrideIntersections", "()Ljava/util/HashMap;", "allSymbols", "collectOverriddenLeaves", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "classMemberSymbol", "addStableJavaScriptName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "targetSymbol", "overriddenSymbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "addAllStableJavaScriptNames", "addAllSymbolsFrom", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "sessionHolder", "symbols", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Ljava/util/Collection;)V", "processStableJavaScriptNamesForMembers", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class StableNamesCollector {
        private final Set<FirJsStableName> jsStableNames = new LinkedHashSet();
        private final HashMap<FirCallableSymbol<?>, HashSet<FirCallableSymbol<?>>> overrideIntersections = new HashMap<>();
        private final Set<FirCallableSymbol<?>> allSymbols = new LinkedHashSet();

        public static Unit a(StableNamesCollector stableNamesCollector, CheckerContext checkerContext, FirClassifierSymbol firClassifierSymbol) {
            firClassifierSymbol.getClass();
            if (firClassifierSymbol instanceof FirClassLikeSymbol) {
                CollectionsKt.addIfNotNull(stableNamesCollector.jsStableNames, FirJsStableName.INSTANCE.createStableNameOrNull(checkerContext, firClassifierSymbol));
            }
            return Unit.INSTANCE;
        }

        private final void addAllStableJavaScriptNames(CheckerContext checkerContext, Set<FirJsStableName> set, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
            addStableJavaScriptName(checkerContext, set, firCallableSymbol, firCallableSymbol2);
            if ((firCallableSymbol instanceof FirPropertySymbol) && (firCallableSymbol2 instanceof FirPropertySymbol)) {
                FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firCallableSymbol;
                FirPropertySymbol firPropertySymbol2 = (FirPropertySymbol) firCallableSymbol2;
                addStableJavaScriptName(checkerContext, set, firPropertySymbol.getGetterSymbol(), firPropertySymbol2.getGetterSymbol());
                addStableJavaScriptName(checkerContext, set, firPropertySymbol.getSetterSymbol(), firPropertySymbol2.getSetterSymbol());
            }
        }

        private final void addStableJavaScriptName(CheckerContext checkerContext, Set<FirJsStableName> set, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
            FirJsStableName firJsStableNameCreateStableNameOrNull;
            if (firCallableSymbol == null || firCallableSymbol2 == null) {
                return;
            }
            FirConstructorSymbol firConstructorSymbol = firCallableSymbol instanceof FirConstructorSymbol ? (FirConstructorSymbol) firCallableSymbol : null;
            if (firConstructorSymbol == null || !firConstructorSymbol.isPrimary()) {
                if ((FirJsHelpersKt.isPresentInGeneratedCode(firCallableSymbol2, checkerContext.getSession()) || firCallableSymbol2.getResolvedStatus().getModality() != Modality.FINAL) && (firJsStableNameCreateStableNameOrNull = FirJsStableName.INSTANCE.createStableNameOrNull(checkerContext, firCallableSymbol2)) != null) {
                    if (firJsStableNameCreateStableNameOrNull.isPresentInGeneratedCode()) {
                        set.add(FirJsStableName.copy$default(firJsStableNameCreateStableNameOrNull, null, firCallableSymbol, false, 5, null));
                    } else {
                        set.add(FirJsStableName.copy$default(firJsStableNameCreateStableNameOrNull, null, firCallableSymbol, firCallableSymbol.getOrigin() instanceof FirDeclarationOrigin.SubstitutionOverride ? FirJsHelpersKt.isPresentInGeneratedCode(firCallableSymbol2, checkerContext.getSession()) : FirJsHelpersKt.isPresentInGeneratedCode(firCallableSymbol, checkerContext.getSession()), 1, null));
                    }
                }
            }
        }

        private final Set<FirCallableSymbol<?>> collectOverriddenLeaves(FirTypeScope firTypeScope, FirCallableSymbol<?> firCallableSymbol) {
            MemberWithBaseScope memberWithBaseScope = new MemberWithBaseScope(firCallableSymbol, firTypeScope);
            HashSet hashSetHashSetOf = SetsKt.hashSetOf(new MemberWithBaseScope[]{memberWithBaseScope});
            List listMutableListOf = kotlin.collections.CollectionsKt.mutableListOf(new MemberWithBaseScope[]{memberWithBaseScope});
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (!listMutableListOf.isEmpty()) {
                MemberWithBaseScope memberWithBaseScope2 = (MemberWithBaseScope) AddToStdlibKt.popLast(listMutableListOf);
                FirCallableSymbol firCallableSymbolComponent1 = memberWithBaseScope2.component1();
                List<MemberWithBaseScope<FirCallableSymbol<?>>> directOverriddenMembersWithBaseScopeSafe = FirTypeScopeKt.getDirectOverriddenMembersWithBaseScopeSafe(memberWithBaseScope2.getBaseScope(), firCallableSymbolComponent1);
                for (MemberWithBaseScope<FirCallableSymbol<?>> memberWithBaseScope3 : directOverriddenMembersWithBaseScopeSafe) {
                    if (hashSetHashSetOf.add(memberWithBaseScope3)) {
                        listMutableListOf.add(memberWithBaseScope3);
                    }
                }
                if (directOverriddenMembersWithBaseScopeSafe.isEmpty()) {
                    linkedHashSet.add(firCallableSymbolComponent1);
                }
            }
            return linkedHashSet;
        }

        public final void addAllSymbolsFrom(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, Collection<? extends FirCallableSymbol<?>> collection) {
            sessionAndScopeSessionHolder.getClass();
            collection.getClass();
            for (DeclarationSymbolMarker declarationSymbolMarker : collection) {
                if (declarationSymbolMarker instanceof FirIntersectionCallableSymbol) {
                    List<FirCallableSymbol<?>> nonSubsumedOverriddenSymbols = DeclarationUtilsKt.getNonSubsumedOverriddenSymbols(sessionAndScopeSessionHolder, (FirIntersectionCallableSymbol) declarationSymbolMarker);
                    ArrayList<FirCallableSymbol<?>> arrayList = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(nonSubsumedOverriddenSymbols, 10));
                    Iterator<T> it = nonSubsumedOverriddenSymbols.iterator();
                    while (it.hasNext()) {
                        FirCallableSymbol<FirCallableDeclaration> firCallableSymbol = (FirCallableSymbol) it.next();
                        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
                        FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                        FirCallableSymbol<FirCallableDeclaration> symbol = originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null;
                        if (symbol != null) {
                            firCallableSymbol = symbol;
                        }
                        arrayList.add(firCallableSymbol);
                    }
                    addAllSymbolsFrom(sessionAndScopeSessionHolder, arrayList);
                    for (FirCallableSymbol<?> firCallableSymbol2 : arrayList) {
                        HashMap<FirCallableSymbol<?>, HashSet<FirCallableSymbol<?>>> map = this.overrideIntersections;
                        HashSet<FirCallableSymbol<?>> hashSet = map.get(firCallableSymbol2);
                        if (hashSet == null) {
                            hashSet = new HashSet<>();
                            map.put(firCallableSymbol2, hashSet);
                        }
                        hashSet.addAll(arrayList);
                    }
                } else {
                    this.allSymbols.add((FirCallableSymbol<?>) declarationSymbolMarker);
                }
            }
        }

        public final Set<FirJsStableName> getJsStableNames() {
            return this.jsStableNames;
        }

        public final HashMap<FirCallableSymbol<?>, HashSet<FirCallableSymbol<?>>> getOverrideIntersections() {
            return this.overrideIntersections;
        }

        public final void processStableJavaScriptNamesForMembers(final CheckerContext checkerContext, FirClass firClass) {
            checkerContext.getClass();
            firClass.getClass();
            DeclarationUtilsKt.processAllClassifiers$default(firClass.getSymbol(), checkerContext.getSession(), null, new Function1() { // from class: org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.a
                public final Object invoke(Object obj) {
                    return FirJsNameClashClassMembersChecker.StableNamesCollector.a(this.b, checkerContext, (FirClassifierSymbol) obj);
                }
            }, 2, null);
            FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firClass.getSymbol());
            firTypeScopeUnsubstitutedScope.processDeclaredConstructors(new FirJsNameClashClassMembersChecker$StableNamesCollector$processStableJavaScriptNamesForMembers$2(this.allSymbols));
            addAllSymbolsFrom(checkerContext, FirContainingNamesAwareScopeKt.collectAllFunctions(firTypeScopeUnsubstitutedScope));
            addAllSymbolsFrom(checkerContext, FirContainingNamesAwareScopeKt.collectAllProperties(firTypeScopeUnsubstitutedScope));
            for (FirCallableSymbol<?> firCallableSymbol : this.allSymbols) {
                Iterator<FirCallableSymbol<?>> it = collectOverriddenLeaves(firTypeScopeUnsubstitutedScope, firCallableSymbol).iterator();
                while (it.hasNext()) {
                    addAllStableJavaScriptNames(checkerContext, this.jsStableNames, firCallableSymbol, it.next());
                }
            }
        }
    }

    public /* synthetic */ FirJsNameClashClassMembersChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    public static boolean b(Set set, FirJsStableName firJsStableName) {
        firJsStableName.getClass();
        return set.contains(firJsStableName);
    }

    private final List<ClashedSymbol> collectNonFakeOverrideClashes(CheckerContext checkerContext, List<FirJsStableName> list, Function1<? super FirJsStableName, Boolean> function1) {
        List listCreateListBuilder = kotlin.collections.CollectionsKt.createListBuilder();
        for (FirJsStableName firJsStableName : list) {
            List<FirJsStableName> listCollectNameClashesWith = FirJsStableNameKt.collectNameClashesWith(checkerContext, list, firJsStableName);
            if (((Boolean) function1.invoke(firJsStableName)).booleanValue()) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : listCollectNameClashesWith) {
                    if (!((Boolean) function1.invoke((FirJsStableName) obj)).booleanValue()) {
                        arrayList.add(obj);
                    }
                }
                listCollectNameClashesWith = arrayList;
            }
            if (!listCollectNameClashesWith.isEmpty()) {
                FirBasedSymbol<?> symbol = firJsStableName.getSymbol();
                List<FirJsStableName> list2 = listCollectNameClashesWith;
                ArrayList arrayList2 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((FirJsStableName) it.next()).getSymbol());
                }
                listCreateListBuilder.add(new ClashedSymbol(symbol, arrayList2));
            }
        }
        return kotlin.collections.CollectionsKt.build(listCreateListBuilder);
    }

    private final Set<FirJsStableName> filterFakeOverrideNames(List<FirJsStableName> list, FirClass firClass) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : list) {
            if (!Intrinsics.areEqual(ContainingClassUtilsKt.getContainingClassSymbol(((FirJsStableName) obj).getSymbol()), firClass.getSymbol())) {
                linkedHashSet.add(obj);
            }
        }
        return linkedHashSet;
    }

    private final ClashedSymbol findFirstFakeOverrideClash(CheckerContext checkerContext, Set<FirJsStableName> set, StableNamesCollector stableNamesCollector) {
        for (FirJsStableName firJsStableName : set) {
            Iterable iterableEmptySet = (HashSet) stableNamesCollector.getOverrideIntersections().get(firJsStableName.getSymbol());
            if (iterableEmptySet == null) {
                iterableEmptySet = SetsKt.emptySet();
            }
            List<FirJsStableName> listCollectNameClashesWith = FirJsStableNameKt.collectNameClashesWith(checkerContext, set, firJsStableName);
            ArrayList arrayList = new ArrayList();
            for (Object obj : listCollectNameClashesWith) {
                if (!kotlin.collections.CollectionsKt.contains(iterableEmptySet, ((FirJsStableName) obj).getSymbol())) {
                    arrayList.add(obj);
                }
            }
            if (!arrayList.isEmpty()) {
                FirBasedSymbol<?> symbol = firJsStableName.getSymbol();
                ArrayList arrayList2 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((FirJsStableName) it.next()).getSymbol());
                }
                return new ClashedSymbol(symbol, arrayList2);
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        KtSourceElement source;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        if (FirJsHelpersKt.isPresentInGeneratedCode(firClass.getSymbol(), checkerContext.getSession())) {
            StableNamesCollector stableNamesCollector = new StableNamesCollector();
            stableNamesCollector.processStableJavaScriptNamesForMembers(checkerContext, firClass);
            Set<FirJsStableName> jsStableNames = stableNamesCollector.getJsStableNames();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : jsStableNames) {
                String name = ((FirJsStableName) obj).getName();
                Object arrayList = linkedHashMap.get(name);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(name, arrayList);
                }
                ((List) arrayList).add(obj);
            }
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                String str = (String) entry.getKey();
                List<FirJsStableName> list = (List) entry.getValue();
                final Set<FirJsStableName> setFilterFakeOverrideNames = filterFakeOverrideNames(list, firClass);
                for (ClashedSymbol clashedSymbol : collectNonFakeOverrideClashes(checkerContext, list, new Function1() { // from class: s95
                    public final Object invoke(Object obj2) {
                        return Boolean.valueOf(FirJsNameClashClassMembersChecker.b(setFilterFakeOverrideNames, (FirJsStableName) obj2));
                    }
                })) {
                    FirBasedSymbol<?> firBasedSymbolComponent1 = clashedSymbol.component1();
                    List<FirBasedSymbol<?>> listComponent2 = clashedSymbol.component2();
                    if (firBasedSymbolComponent1 instanceof FirCallableSymbol) {
                        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirCallableSymbol) firBasedSymbolComponent1).getFir();
                        while (true) {
                            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                            if (originalForSubstitutionOverrideAttr == null) {
                                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                                if (originalForSubstitutionOverrideAttr == null) {
                                    DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                                    originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                                }
                            }
                            if (originalForSubstitutionOverrideAttr == null) {
                                break;
                            } else {
                                firCallableDeclaration = originalForSubstitutionOverrideAttr;
                            }
                        }
                        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
                        if (symbol == null) {
                            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                            return;
                        }
                        source = symbol.getSource();
                    } else {
                        source = firBasedSymbolComponent1.getSource();
                    }
                    if (source == null) {
                        source = firClass.getSource();
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory2) FirJsErrors.INSTANCE.getJS_NAME_CLASH(), (Object) str, (Object) listComponent2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
                ClashedSymbol clashedSymbolFindFirstFakeOverrideClash = findFirstFakeOverrideClash(checkerContext, setFilterFakeOverrideNames, stableNamesCollector);
                if (clashedSymbolFindFirstFakeOverrideClash != null) {
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory3<String, FirBasedSymbol<?>, List<FirBasedSymbol<?>>>) ((KtDiagnosticFactory3<Object, Object, Object>) FirJsErrors.INSTANCE.getJS_FAKE_NAME_CLASH()), str, clashedSymbolFindFirstFakeOverrideClash.component1(), clashedSymbolFindFirstFakeOverrideClash.component2(), (64 & 64) != 0 ? null : null);
                }
            }
        }
    }

    private FirJsNameClashClassMembersChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}
