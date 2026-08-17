package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDefaultStarImportingScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J.\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u001c\u0010\u000f\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f0\u0010H\u0016J,\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u001c\u0010\u000f\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00140\u0010Jc\u0010\u0015\u001a\u00020\f\"\f\b\u0000\u0010\u0016*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\r\u001a\u00020\u000e2/\u0010\u0018\u001a+\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u00020\f0\u001a\u0012\u0004\u0012\u00020\f0\u0019¢\u0006\u0002\b\u001b2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u00020\f0\u001aH\u0002J$\u0010\u001c\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\f0\u001aH\u0016J(\u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0016\u0010\u000f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f\u0012\u0004\u0012\u00020\f0\u001aH\u0016J\u001c\u0010 \u001a\u00020\f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\f0\u001aH\u0016J\u001c\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0017b\u0002\b'R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDefaultStarImportingScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultStarImportingScopeMarker;", "first", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirSingleLevelDefaultStarImportingScope;", "second", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/impl/FirSingleLevelDefaultStarImportingScope;Lorg/jetbrains/kotlin/fir/scopes/impl/FirSingleLevelDefaultStarImportingScope;)V", "getFirst", "()Lorg/jetbrains/kotlin/fir/scopes/impl/FirSingleLevelDefaultStarImportingScope;", "getSecond", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processClassifiersByNameWithSubstitutionFromBothLevelsConditionally", Argument.Delimiters.none, "processSymbolsByName", "S", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processingFactory", "Lkotlin/Function3;", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "processFunctionsByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "withReplacedSessionOrNull", "newSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDefaultStarImportingScope extends FirScope implements DefaultStarImportingScopeMarker {
    private final FirSingleLevelDefaultStarImportingScope first;
    private final FirSingleLevelDefaultStarImportingScope second;

    public FirDefaultStarImportingScope(FirSingleLevelDefaultStarImportingScope firSingleLevelDefaultStarImportingScope, FirSingleLevelDefaultStarImportingScope firSingleLevelDefaultStarImportingScope2) {
        firSingleLevelDefaultStarImportingScope.getClass();
        firSingleLevelDefaultStarImportingScope2.getClass();
        this.first = firSingleLevelDefaultStarImportingScope;
        this.second = firSingleLevelDefaultStarImportingScope2;
    }

    public static boolean b(Function2 function2, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        function2.invoke(firClassifierSymbol, coneSubstitutor);
        return true;
    }

    public static Unit c(Ref.BooleanRef booleanRef, Function2 function2, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        booleanRef.element = ((Boolean) function2.invoke(firClassifierSymbol, coneSubstitutor)).booleanValue();
        return Unit.INSTANCE;
    }

    public static Unit d(Ref.BooleanRef booleanRef, Function1 function1, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        booleanRef.element = true;
        function1.invoke(firConstructorSymbol);
        return Unit.INSTANCE;
    }

    public static Unit e(Ref.BooleanRef booleanRef, Function1 function1, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        booleanRef.element = true;
        function1.invoke(firCallableSymbol);
        return Unit.INSTANCE;
    }

    private final <S extends FirCallableSymbol<?>> void processSymbolsByName(Name name, Function3<? super FirScope, ? super Name, ? super Function1<? super S, Unit>, Unit> processingFactory, final Function1<? super S, Unit> processor) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        processingFactory.invoke(this.first, name, new Function1() { // from class: j25
            public final Object invoke(Object obj) {
                return FirDefaultStarImportingScope.e(booleanRef, processor, (FirCallableSymbol) obj);
            }
        });
        if (booleanRef.element) {
            return;
        }
        processingFactory.invoke(this.second, name, processor);
    }

    public final FirSingleLevelDefaultStarImportingScope getFirst() {
        return this.first;
    }

    public final FirSingleLevelDefaultStarImportingScope getSecond() {
        return this.second;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, final Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        processClassifiersByNameWithSubstitutionFromBothLevelsConditionally(name, new Function2() { // from class: k25
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(FirDefaultStarImportingScope.b(processor, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2));
            }
        });
    }

    public final void processClassifiersByNameWithSubstitutionFromBothLevelsConditionally(Name name, final Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Boolean> processor) {
        name.getClass();
        processor.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        this.first.processClassifiersByNameWithSubstitution(name, new Function2() { // from class: h25
            public final Object invoke(Object obj, Object obj2) {
                return FirDefaultStarImportingScope.c(booleanRef, processor, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2);
            }
        });
        if (booleanRef.element) {
            return;
        }
        this.second.processClassifiersByNameWithSubstitution(name, new AnonymousClass2(processor));
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(final Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        this.first.processDeclaredConstructors(new Function1() { // from class: i25
            public final Object invoke(Object obj) {
                return FirDefaultStarImportingScope.d(booleanRef, processor, (FirConstructorSymbol) obj);
            }
        });
        if (booleanRef.element) {
            return;
        }
        this.second.processDeclaredConstructors(processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        processSymbolsByName(name, AnonymousClass1.INSTANCE, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        processSymbolsByName(name, C00621.INSTANCE, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirDefaultStarImportingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirDefaultStarImportingScope(this.first.withReplacedSessionOrNull(newSession, newScopeSession), this.second.withReplacedSessionOrNull(newSession, newScopeSession));
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.impl.FirDefaultStarImportingScope$processFunctionsByName$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<FirScope, Name, Function1<? super FirNamedFunctionSymbol, ? extends Unit>, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(3, FirScope.class, "processFunctionsByName", "processFunctionsByName(Lorg/jetbrains/kotlin/name/Name;Lkotlin/jvm/functions/Function1;)V", 0);
        }

        public final void invoke(FirScope firScope, Name name, Function1<? super FirNamedFunctionSymbol, Unit> function1) {
            firScope.getClass();
            name.getClass();
            function1.getClass();
            firScope.processFunctionsByName(name, function1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((FirScope) obj, (Name) obj2, (Function1<? super FirNamedFunctionSymbol, Unit>) obj3);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.impl.FirDefaultStarImportingScope$processPropertiesByName$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00621 extends FunctionReferenceImpl implements Function3<FirScope, Name, Function1<? super FirVariableSymbol<?>, ? extends Unit>, Unit> {
        public static final C00621 INSTANCE = new C00621();

        public C00621() {
            super(3, FirScope.class, "processPropertiesByName", "processPropertiesByName(Lorg/jetbrains/kotlin/name/Name;Lkotlin/jvm/functions/Function1;)V", 0);
        }

        public final void invoke(FirScope firScope, Name name, Function1<? super FirVariableSymbol<?>, Unit> function1) {
            firScope.getClass();
            name.getClass();
            function1.getClass();
            firScope.processPropertiesByName(name, function1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((FirScope) obj, (Name) obj2, (Function1<? super FirVariableSymbol<?>, Unit>) obj3);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.impl.FirDefaultStarImportingScope$processClassifiersByNameWithSubstitutionFromBothLevelsConditionally$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit> {
        public AnonymousClass2(Object obj) {
            super(2, obj, Function2.class, "invoke", "invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 8);
        }

        public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
            firClassifierSymbol.getClass();
            coneSubstitutor.getClass();
            ((Function2) ((AdaptedFunctionReference) this).receiver).invoke(firClassifierSymbol, coneSubstitutor);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
            return Unit.INSTANCE;
        }
    }
}
