package org.jetbrains.kotlin.fir.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.ir.builders.Scope;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.util.PrivateForInline;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ð\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JB\u0010#\u001a\u0002H$\"\b\b\u0000\u0010%*\u00020\n\"\u0004\b\u0001\u0010$2\u0006\u0010&\u001a\u0002H%2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u0002H$0(¢\u0006\u0002\b)H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010*J3\u0010+\u001a\u0002H%\"\u0004\b\u0000\u0010%2\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020.2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H%0/H\u0000¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u0004\u0018\u00010\u001f2\u0006\u00103\u001a\u00020\u001eJ\b\u00104\u001a\u0004\u0018\u000105J\"\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u00192\f\u0010'\u001a\b\u0012\u0004\u0012\u0002070/H\u0086\bø\u0001\u0000J\u0006\u00109\u001a\u00020\nJ\u0006\u0010:\u001a\u00020\u0015J\u0010\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010=\u001a\u00020>J\u000e\u0010?\u001a\u00020<2\u0006\u0010=\u001a\u00020@J'\u0010A\u001a\u0007HB¢\u0006\u0002\bC\"\n\b\u0000\u0010B\u0018\u0001*\u00020D2\u0006\u0010E\u001a\u00020FH\u0086\b¢\u0006\u0002\u0010GJ\u001d\u0010H\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020D2\u0006\u0010I\u001a\u0002H%¢\u0006\u0002\u0010JJ\b\u0010K\u001a\u0004\u0018\u00010\u0019JB\u0010P\u001a\u0002H$\"\b\b\u0000\u0010%*\u00020M\"\u0004\b\u0001\u0010$2\u0006\u0010Q\u001a\u0002H%2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u0002H$0(¢\u0006\u0002\b)H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010RJD\u0010Y\u001a\u0002H$\"\u0004\b\u0000\u0010$2\u0006\u0010Z\u001a\u00020U2\n\b\u0002\u0010[\u001a\u0004\u0018\u00010V2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020U\u0012\u0004\u0012\u0002H$0(¢\u0006\u0002\b)H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\\J8\u0010`\u001a\u0002H$\"\u0004\b\u0000\u0010$2\u0006\u0010a\u001a\u00020.2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u0002H$0(¢\u0006\u0002\b)H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010bJ/\u0010j\u001a\u0002H%\"\u0004\b\u0000\u0010%2\b\u0010k\u001a\u0004\u0018\u00010d2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H%0/H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010lJ/\u0010m\u001a\u0002H%\"\u0004\b\u0000\u0010%2\b\u0010k\u001a\u0004\u0018\u00010d2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H%0/H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010lJ\u0016\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020q2\u0006\u0010r\u001a\u00020sJ\b\u0010&\u001a\u0004\u0018\u00010\nJ\u0006\u0010t\u001a\u00020uJ\u0010\u0010v\u001a\u0004\u0018\u00010w2\u0006\u0010-\u001a\u00020.J\u0006\u0010x\u001a\u00020dJ\u0006\u0010y\u001a\u00020dJ\u000e\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020}J0\u0010\u0084\u0001\u001a\u0002H%\"\u0004\b\u0000\u0010%2\u0007\u0010\u0085\u0001\u001a\u00020\u007f2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H%0/H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u0086\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R*\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000eR*\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\t8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000eR*\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\t8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u000eR0\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001d8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\f\u001a\u0004\b!\u0010\"R*\u0010L\u001a\b\u0012\u0004\u0012\u00020M0\t8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\bN\u0010\f\u001a\u0004\bO\u0010\u000eR8\u0010S\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020U\u0012\u0006\u0012\u0004\u0018\u00010V0T0\t8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\bW\u0010\f\u001a\u0004\bX\u0010\u000eR*\u0010]\u001a\b\u0012\u0004\u0012\u00020.0\t8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b^\u0010\f\u001a\u0004\b_\u0010\u000eR*\u0010c\u001a\b\u0012\u0004\u0012\u00020d0\t8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\be\u0010\f\u001a\u0004\bf\u0010\u000eR*\u0010g\u001a\b\u0012\u0004\u0012\u00020d0\t8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\bh\u0010\f\u001a\u0004\bi\u0010\u000eR,\u0010~\u001a\b\u0012\u0004\u0012\u00020\u007f0\t8\u0000X\u0081\u0004r\u0002\b\u000fr\u0002\b\u0010¢\u0006\u0010\n\u0000\u0012\u0005\b\u0080\u0001\u0010\f\u001a\u0005\b\u0081\u0001\u0010\u000eR\u001c\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\u007f0\u00128@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0083\u0001\u0010\u000e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0087\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;)V", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "_parentStack", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "get_parentStack$annotations", "()V", "get_parentStack", "()Ljava/util/List;", "Lkotlin/PublishedApi;", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "parentStack", Argument.Delimiters.none, "getParentStack", "scopeStack", "Lorg/jetbrains/kotlin/ir/builders/Scope;", "getScopeStack$annotations", "getScopeStack", "containingFirClassStack", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getContainingFirClassStack$annotations", "getContainingFirClassStack", "currentlyGeneratedDelegatedConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "getCurrentlyGeneratedDelegatedConstructors$annotations", "getCurrentlyGeneratedDelegatedConstructors", "()Ljava/util/Map;", "withParent", "R", "T", "parent", "f", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "forDelegatingConstructorCall", "constructor", "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "Lkotlin/Function0;", "forDelegatingConstructorCall$org_jetbrains_kotlin_fir2ir", "(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getConstructorForCurrentlyGeneratedDelegatedConstructor", "itClassSymbol", "containingFileIfAny", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "withContainingFirClass", Argument.Delimiters.none, "containingFirClass", "parentFromStack", "scope", "parentAccessorOfPropertyFromStack", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "propertySymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "parentAccessorOfDelegatedPropertyFromStack", "Lorg/jetbrains/kotlin/ir/symbols/IrLocalDelegatedPropertySymbol;", "findDeclarationInParentsStack", "D", "Lkotlin/internal/NoInfer;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "(Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;)Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "applyParentFromStackTo", "declaration", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;)Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "containerFirClass", "functionStack", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getFunctionStack$annotations", "getFunctionStack", "withFunction", "function", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "propertyStack", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getPropertyStack$annotations", "getPropertyStack", "withProperty", "property", "firProperty", "(Lorg/jetbrains/kotlin/ir/declarations/IrProperty;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "classStack", "getClassStack$annotations", "getClassStack", "withClass", "klass", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "whenSubjectVariableStack", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "getWhenSubjectVariableStack$annotations", "getWhenSubjectVariableStack", "safeCallSubjectVariableStack", "getSafeCallSubjectVariableStack$annotations", "getSafeCallSubjectVariableStack", "withWhenSubject", "subject", "(Lorg/jetbrains/kotlin/ir/declarations/IrVariable;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withSafeCallSubject", "returnTarget", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "defaultConversionTypeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "dispatchReceiverParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "lastWhenSubject", "lastSafeCallSubject", "shouldEraseType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeTypeParameterType;", "_initBlocksStack", "Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;", "get_initBlocksStack$annotations", "get_initBlocksStack", "initBlocksStack", "getInitBlocksStack$org_jetbrains_kotlin_fir2ir", "withInitBlock", "initializer", "(Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrConversionScope {
    private final List<IrAnonymousInitializer> _initBlocksStack;
    private final List<IrDeclarationParent> _parentStack;
    private final List<IrClass> classStack;
    private final Fir2IrConfiguration configuration;
    private final List<FirClass> containingFirClassStack;
    private final Map<IrClassSymbol, IrConstructor> currentlyGeneratedDelegatedConstructors;
    private final List<IrFunction> functionStack;
    private final List<Pair<IrProperty, FirProperty>> propertyStack;
    private final List<IrVariable> safeCallSubjectVariableStack;
    private final List<Scope> scopeStack;
    private final List<IrVariable> whenSubjectVariableStack;

    public Fir2IrConversionScope(Fir2IrConfiguration fir2IrConfiguration) {
        fir2IrConfiguration.getClass();
        this.configuration = fir2IrConfiguration;
        this._parentStack = new ArrayList();
        this.scopeStack = new ArrayList();
        this.containingFirClassStack = new ArrayList();
        this.currentlyGeneratedDelegatedConstructors = new LinkedHashMap();
        this.functionStack = new ArrayList();
        this.propertyStack = new ArrayList();
        this.classStack = new ArrayList();
        this.whenSubjectVariableStack = new ArrayList();
        this.safeCallSubjectVariableStack = new ArrayList();
        this._initBlocksStack = new ArrayList();
    }

    @PrivateForInline
    public static /* synthetic */ void getClassStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getContainingFirClassStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getCurrentlyGeneratedDelegatedConstructors$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getFunctionStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getPropertyStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getSafeCallSubjectVariableStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getScopeStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getWhenSubjectVariableStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void get_initBlocksStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void get_parentStack$annotations() {
    }

    public static /* synthetic */ Object withProperty$default(Fir2IrConversionScope fir2IrConversionScope, IrProperty irProperty, FirProperty firProperty, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            firProperty = null;
        }
        irProperty.getClass();
        function1.getClass();
        fir2IrConversionScope.getPropertyStack().add(TuplesKt.to(irProperty, firProperty));
        try {
            return function1.invoke(irProperty);
        } finally {
            InlineMarker.finallyStart(1);
            fir2IrConversionScope.getPropertyStack().remove(fir2IrConversionScope.getPropertyStack().size() - 1);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T extends IrDeclaration> T applyParentFromStackTo(T declaration) {
        declaration.getClass();
        declaration.setParent((IrDeclarationParent) CollectionsKt.last(this._parentStack));
        return declaration;
    }

    public final FirClass containerFirClass() {
        return (FirClass) CollectionsKt.lastOrNull(this.containingFirClassStack);
    }

    public final IrFile containingFileIfAny() {
        Object orNull = CollectionsKt.getOrNull(this._parentStack, 0);
        if (orNull instanceof IrFile) {
            return (IrFile) orNull;
        }
        return null;
    }

    public final ConversionTypeOrigin defaultConversionTypeOrigin() {
        IrFunction irFunctionParent = parent();
        IrFunction irFunction = irFunctionParent instanceof IrFunction ? irFunctionParent : null;
        return (irFunction == null || !AdditionalIrUtilsKt.isSetter(irFunction)) ? ConversionTypeOrigin.DEFAULT : ConversionTypeOrigin.SETTER;
    }

    public final IrValueParameter dispatchReceiverParameter(IrClass irClass) {
        IrValueParameter thisReceiver;
        irClass.getClass();
        for (IrFunction irFunction : CollectionsKt.asReversedMutable(this.functionStack)) {
            if (Intrinsics.areEqual(IrUtilsKt.getParentClassOrNull(irFunction), irClass)) {
                if ((irFunction instanceof IrConstructor) && irClass.isInner() && (thisReceiver = irClass.getThisReceiver()) != null) {
                    return thisReceiver;
                }
                IrValueParameter dispatchReceiverParameter = irFunction.getDispatchReceiverParameter();
                if (dispatchReceiverParameter != null) {
                    return dispatchReceiverParameter;
                }
            }
        }
        return irClass.getThisReceiver();
    }

    public final /* synthetic */ <D extends IrDeclaration> D findDeclarationInParentsStack(IrSymbol symbol) {
        D d;
        D d2;
        symbol.getClass();
        if (!AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
            D owner = symbol.getOwner();
            Intrinsics.reifiedOperationMarker(1, "D");
            return owner;
        }
        Iterator it = CollectionsKt.asReversedMutable(get_parentStack()).iterator();
        do {
            if (!it.hasNext()) {
                if (!getConfiguration().getAllowNonCachedDeclarations()) {
                    x04.a("Declaration with symbol ", symbol, " is not found in parents stack");
                    return null;
                }
                D owner2 = symbol.getOwner();
                Intrinsics.reifiedOperationMarker(1, "D");
                return owner2;
            }
            d = (IrDeclarationParent) it.next();
            d2 = d instanceof IrDeclaration ? d : null;
        } while (!Intrinsics.areEqual(d2 != null ? d2.getSymbol() : null, symbol));
        Intrinsics.reifiedOperationMarker(1, "D");
        return d;
    }

    public final <T> T forDelegatingConstructorCall$org_jetbrains_kotlin_fir2ir(IrConstructor constructor, IrClass irClass, Function0<? extends T> f) {
        constructor.getClass();
        irClass.getClass();
        f.getClass();
        this.currentlyGeneratedDelegatedConstructors.put(irClass.getSymbol(), constructor);
        try {
            return (T) f.invoke();
        } finally {
            this.currentlyGeneratedDelegatedConstructors.remove(irClass.getSymbol());
        }
    }

    public final List<IrClass> getClassStack() {
        return this.classStack;
    }

    public final Fir2IrConfiguration getConfiguration() {
        return this.configuration;
    }

    public final IrConstructor getConstructorForCurrentlyGeneratedDelegatedConstructor(IrClassSymbol itClassSymbol) {
        itClassSymbol.getClass();
        return this.currentlyGeneratedDelegatedConstructors.get(itClassSymbol);
    }

    public final List<FirClass> getContainingFirClassStack() {
        return this.containingFirClassStack;
    }

    public final Map<IrClassSymbol, IrConstructor> getCurrentlyGeneratedDelegatedConstructors() {
        return this.currentlyGeneratedDelegatedConstructors;
    }

    public final List<IrFunction> getFunctionStack() {
        return this.functionStack;
    }

    public final List<IrAnonymousInitializer> getInitBlocksStack$org_jetbrains_kotlin_fir2ir() {
        return this._initBlocksStack;
    }

    public final List<IrDeclarationParent> getParentStack() {
        return this._parentStack;
    }

    public final List<Pair<IrProperty, FirProperty>> getPropertyStack() {
        return this.propertyStack;
    }

    public final List<IrVariable> getSafeCallSubjectVariableStack() {
        return this.safeCallSubjectVariableStack;
    }

    public final List<Scope> getScopeStack() {
        return this.scopeStack;
    }

    public final List<IrVariable> getWhenSubjectVariableStack() {
        return this.whenSubjectVariableStack;
    }

    public final List<IrAnonymousInitializer> get_initBlocksStack() {
        return this._initBlocksStack;
    }

    public final List<IrDeclarationParent> get_parentStack() {
        return this._parentStack;
    }

    public final IrVariable lastSafeCallSubject() {
        return (IrVariable) CollectionsKt.last(this.safeCallSubjectVariableStack);
    }

    public final IrVariable lastWhenSubject() {
        return (IrVariable) CollectionsKt.last(this.whenSubjectVariableStack);
    }

    public final IrDeclarationParent parent() {
        return (IrDeclarationParent) CollectionsKt.lastOrNull(this._parentStack);
    }

    public final IrSimpleFunction parentAccessorOfDelegatedPropertyFromStack(IrLocalDelegatedPropertySymbol propertySymbol) {
        propertySymbol.getClass();
        IrLocalDelegatedProperty owner = propertySymbol.getOwner();
        for (IrSimpleFunction irSimpleFunction : CollectionsKt.asReversedMutable(this._parentStack)) {
            if (Intrinsics.areEqual(irSimpleFunction, owner.getGetter())) {
                irSimpleFunction.getClass();
                return irSimpleFunction;
            }
            if (Intrinsics.areEqual(irSimpleFunction, owner.getSetter())) {
                irSimpleFunction.getClass();
                return irSimpleFunction;
            }
        }
        b88.a("Accessor of property ", RenderIrElementKt.render$default(owner, (DumpIrTreeOptions) null, 1, (Object) null), " not found on parent stack");
        return null;
    }

    public final IrSimpleFunction parentAccessorOfPropertyFromStack(IrPropertySymbol propertySymbol) {
        propertySymbol.getClass();
        IrProperty owner = propertySymbol.getOwner();
        for (IrSimpleFunction irSimpleFunction : CollectionsKt.asReversedMutable(this._parentStack)) {
            if (Intrinsics.areEqual(irSimpleFunction, owner.getGetter())) {
                irSimpleFunction.getClass();
                return irSimpleFunction;
            }
            if (Intrinsics.areEqual(irSimpleFunction, owner.getSetter())) {
                irSimpleFunction.getClass();
                return irSimpleFunction;
            }
        }
        return null;
    }

    public final IrDeclarationParent parentFromStack() {
        return (IrDeclarationParent) CollectionsKt.last(this._parentStack);
    }

    public final IrFunctionSymbol returnTarget(FirReturnExpression expression, Fir2IrDeclarationStorage declarationStorage) {
        IrConstructorSymbol cachedIrFunctionSymbol$default;
        IrConstructorSymbol symbol;
        expression.getClass();
        declarationStorage.getClass();
        FirFunction firFunction = (FirFunction) expression.getTarget().getLabeledElement();
        if (firFunction instanceof FirConstructor) {
            cachedIrFunctionSymbol$default = declarationStorage.getCachedIrConstructorSymbol((FirConstructor) firFunction);
        } else if (firFunction instanceof FirPropertyAccessor) {
            Iterator it = CollectionsKt.asReversedMutable(this.propertyStack).iterator();
            loop1: while (true) {
                symbol = null;
                while (true) {
                    if (!it.hasNext()) {
                        break loop1;
                    }
                    Pair pair = (Pair) it.next();
                    IrProperty irProperty = (IrProperty) pair.component1();
                    FirProperty firProperty = (FirProperty) pair.component2();
                    if ((firProperty != null ? firProperty.getGetter() : null) == firFunction) {
                        IrSimpleFunction getter = irProperty.getGetter();
                        if (getter != null) {
                            symbol = getter.getSymbol();
                        }
                    } else {
                        if ((firProperty != null ? firProperty.getSetter() : null) != firFunction) {
                            continue;
                        } else {
                            IrSimpleFunction setter = irProperty.getSetter();
                            if (setter != null) {
                                symbol = setter.getSymbol();
                            }
                        }
                    }
                }
            }
            cachedIrFunctionSymbol$default = symbol;
        } else {
            cachedIrFunctionSymbol$default = Fir2IrDeclarationStorage.getCachedIrFunctionSymbol$default(declarationStorage, firFunction, (ConeClassLikeLookupTag) null, 2, (Object) null);
        }
        Iterator it2 = CollectionsKt.asReversedMutable(this.functionStack).iterator();
        while (it2.hasNext()) {
            IrFunctionSymbol symbol2 = ((IrFunction) it2.next()).getSymbol();
            if (Intrinsics.areEqual(symbol2, cachedIrFunctionSymbol$default)) {
                return symbol2;
            }
        }
        return ((IrFunction) CollectionsKt.last(this.functionStack)).getSymbol();
    }

    public final Scope scope() {
        return (Scope) CollectionsKt.last(this.scopeStack);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final boolean shouldEraseType(ConeTypeParameterType type) throws KotlinIllegalArgumentExceptionWithAttachments {
        type.getClass();
        List<FirClass> listAsReversedMutable = CollectionsKt.asReversedMutable(this.containingFirClassStack);
        if ((listAsReversedMutable instanceof Collection) && listAsReversedMutable.isEmpty()) {
            return false;
        }
        for (FirClass firClass : listAsReversedMutable) {
            if ((firClass instanceof FirAnonymousObject) || firClass.getIsLocal()) {
                FirTypeParameterSymbol typeParameterSymbol = type.getLookupTag().getTypeParameterSymbol();
                FirDeclaration fir = typeParameterSymbol.getContainingDeclarationSymbol().getFir();
                if ((fir instanceof FirProperty) && ((FirProperty) fir).getDelegate() != null && FirDeclarationUtilKt.isExtension((FirCallableDeclaration) fir)) {
                    List<FirTypeParameterRef> typeParameters = firClass.getTypeParameters();
                    if (!(typeParameters instanceof Collection) || !typeParameters.isEmpty()) {
                        Iterator<T> it = typeParameters.iterator();
                        while (it.hasNext()) {
                            if (((FirTypeParameterRef) it.next()).getSymbol() == typeParameterSymbol) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final <R> R withClass(IrClass klass, Function1<? super IrClass, ? extends R> f) {
        klass.getClass();
        f.getClass();
        getClassStack().add(klass);
        try {
            return (R) f.invoke(klass);
        } finally {
            InlineMarker.finallyStart(1);
            getClassStack().remove(getClassStack().size() - 1);
            InlineMarker.finallyEnd(1);
        }
    }

    public final void withContainingFirClass(FirClass containingFirClass, Function0<Unit> f) {
        containingFirClass.getClass();
        f.getClass();
        getContainingFirClassStack().add(containingFirClass);
        try {
            f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContainingFirClassStack().remove(getContainingFirClassStack().size() - 1);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T extends IrFunction, R> R withFunction(T function, Function1<? super T, ? extends R> f) {
        function.getClass();
        f.getClass();
        getFunctionStack().add(function);
        try {
            return (R) f.invoke(function);
        } finally {
            InlineMarker.finallyStart(1);
            getFunctionStack().remove(getFunctionStack().size() - 1);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withInitBlock(IrAnonymousInitializer initializer, Function0<? extends T> f) {
        initializer.getClass();
        f.getClass();
        get_initBlocksStack().add(initializer);
        try {
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            get_initBlocksStack().remove(get_initBlocksStack().size() - 1);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T extends IrDeclarationParent, R> R withParent(T parent, Function1<? super T, ? extends R> f) {
        parent.getClass();
        f.getClass();
        get_parentStack().add(parent);
        boolean z = parent instanceof IrDeclaration;
        if (z) {
            getScopeStack().add(new Scope(((IrSymbolOwner) parent).getSymbol()));
        }
        try {
            return (R) f.invoke(parent);
        } finally {
            InlineMarker.finallyStart(1);
            if (z) {
                getScopeStack().remove(getScopeStack().size() - 1);
            }
            get_parentStack().remove(get_parentStack().size() - 1);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <R> R withProperty(IrProperty property, FirProperty firProperty, Function1<? super IrProperty, ? extends R> f) {
        property.getClass();
        f.getClass();
        getPropertyStack().add(TuplesKt.to(property, firProperty));
        try {
            return (R) f.invoke(property);
        } finally {
            InlineMarker.finallyStart(1);
            getPropertyStack().remove(getPropertyStack().size() - 1);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withSafeCallSubject(IrVariable subject, Function0<? extends T> f) {
        f.getClass();
        if (subject != null) {
            getSafeCallSubjectVariableStack().add(subject);
        }
        try {
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (subject != null) {
                getSafeCallSubjectVariableStack().remove(getSafeCallSubjectVariableStack().size() - 1);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withWhenSubject(IrVariable subject, Function0<? extends T> f) {
        f.getClass();
        if (subject != null) {
            getWhenSubjectVariableStack().add(subject);
        }
        try {
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (subject != null) {
                getWhenSubjectVariableStack().remove(getWhenSubjectVariableStack().size() - 1);
            }
            InlineMarker.finallyEnd(1);
        }
    }
}
