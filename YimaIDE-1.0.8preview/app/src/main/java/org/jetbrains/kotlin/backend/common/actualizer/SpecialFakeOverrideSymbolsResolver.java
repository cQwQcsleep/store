package org.jetbrains.kotlin.backend.common.actualizer;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrOverridableDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.symbols.IrBindableSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrFakeOverrideSymbolBase;
import org.jetbrains.kotlin.ir.symbols.impl.IrFieldFakeOverrideSymbol;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.ir.util.SymbolRemapper;
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u0014H\u0016J\u001e\u0010\u0015\u001a\u0002H\u0016\"\n\b\u0000\u0010\u0016\u0018\u0001*\u00020\b*\u0002H\u0016H\u0082\b¢\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000bH\u0002J\u001c\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00072\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0002J$\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0 *\u0006\u0012\u0002\b\u00030\u001e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0002J\u000e\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$R&\u0010\u0004\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/actualizer/SpecialFakeOverrideSymbolsResolver;", "Lorg/jetbrains/kotlin/ir/util/SymbolRemapper$Empty;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "cachedFakeOverrides", "", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "processedClasses", "", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getReferencedFunction", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "symbol", "getReferencedSimpleFunction", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getReferencedProperty", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "getReferencedField", "Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "remap", "S", "(Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;)Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "processClass", "", "irClass", "processDeclaration", "classSymbol", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;", "collectOverrides", "Lkotlin/sequences/Sequence;", "visited", "cacheFakeOverridesOfAllClasses", "irModuleFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "org.jetbrains.kotlin:ir.actualization"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SpecialFakeOverrideSymbolsResolver extends SymbolRemapper.Empty {
    private final Map<Pair<IrClassSymbol, IrSymbol>, IrSymbol> cachedFakeOverrides = new LinkedHashMap();
    private final Set<IrClass> processedClasses = new LinkedHashSet();

    /* JADX INFO: renamed from: org.jetbrains.kotlin.backend.common.actualizer.SpecialFakeOverrideSymbolsResolver$collectOverrides$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;"}, k = 3, mv = {2, 4, 0}, xi = 48)
    public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super IrSymbol>, Continuation<? super Unit>, Object> {
        final /* synthetic */ IrOverridableDeclaration<?> $this_collectOverrides;
        final /* synthetic */ Set<IrSymbol> $visited;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ SpecialFakeOverrideSymbolsResolver this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Set<IrSymbol> set, IrOverridableDeclaration<?> irOverridableDeclaration, SpecialFakeOverrideSymbolsResolver specialFakeOverrideSymbolsResolver, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$visited = set;
            this.$this_collectOverrides = irOverridableDeclaration;
            this.this$0 = specialFakeOverrideSymbolsResolver;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$visited, this.$this_collectOverrides, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(SequenceScope<? super IrSymbol> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:18:0x005c  */
        /* JADX WARN: Code duplicated, block: B:20:0x0068  */
        /* JADX WARN: Code duplicated, block: B:21:0x006a  */
        /* JADX WARN: Code duplicated, block: B:31:0x00b4 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:32:0x00b3 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:34:? A[LOOP:0: B:16:0x0056->B:34:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0048, code lost:
        
            if (r0.yield(r11, r10) == r1) goto L25;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Iterator it;
            IrSymbol irSymbol;
            SpecialFakeOverrideSymbolsResolver specialFakeOverrideSymbolsResolver;
            IrFakeOverrideSymbolBase irFakeOverrideSymbolBase;
            IrSymbol irSymbol2;
            Sequence sequenceCollectOverrides;
            SequenceScope sequenceScope = (SequenceScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$visited.add(this.$this_collectOverrides.getSymbol())) {
                    IrSymbol symbol = this.$this_collectOverrides.getSymbol();
                    this.L$0 = sequenceScope;
                    this.label = 1;
                }
                return Unit.INSTANCE;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                it = (Iterator) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            while (it.hasNext()) {
                irSymbol = (IrSymbol) it.next();
                specialFakeOverrideSymbolsResolver = this.this$0;
                if (irSymbol instanceof IrFakeOverrideSymbolBase) {
                    irFakeOverrideSymbolBase = (IrFakeOverrideSymbolBase) irSymbol;
                    specialFakeOverrideSymbolsResolver.processClass(irFakeOverrideSymbolBase.getContainingClassSymbol().getOwner());
                    irSymbol2 = (IrSymbol) specialFakeOverrideSymbolsResolver.cachedFakeOverrides.get(TuplesKt.to(irFakeOverrideSymbolBase.getContainingClassSymbol(), irFakeOverrideSymbolBase.getOriginalSymbol()));
                    if (irSymbol2 == null) {
                        StringBuilder sb = new StringBuilder("No override for ");
                        sb.append(irFakeOverrideSymbolBase.getOriginalSymbol());
                        ej7.a(sb, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol());
                        return null;
                    }
                } else {
                    irSymbol2 = irSymbol;
                }
                IrOverridableDeclaration owner = irSymbol2.getOwner();
                owner.getClass();
                sequenceCollectOverrides = specialFakeOverrideSymbolsResolver.collectOverrides(owner, this.$visited);
                this.L$0 = sequenceScope;
                this.L$1 = it;
                this.L$2 = SpillingKt.nullOutSpilledVariable(irSymbol);
                this.label = 2;
                if (sequenceScope.yieldAll(sequenceCollectOverrides, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
            it = this.$this_collectOverrides.getOverriddenSymbols().iterator();
            while (it.hasNext()) {
                irSymbol = (IrSymbol) it.next();
                specialFakeOverrideSymbolsResolver = this.this$0;
                if (irSymbol instanceof IrFakeOverrideSymbolBase) {
                    irSymbol2 = irSymbol;
                } else {
                    irFakeOverrideSymbolBase = (IrFakeOverrideSymbolBase) irSymbol;
                    specialFakeOverrideSymbolsResolver.processClass(irFakeOverrideSymbolBase.getContainingClassSymbol().getOwner());
                    irSymbol2 = (IrSymbol) specialFakeOverrideSymbolsResolver.cachedFakeOverrides.get(TuplesKt.to(irFakeOverrideSymbolBase.getContainingClassSymbol(), irFakeOverrideSymbolBase.getOriginalSymbol()));
                    if (irSymbol2 == null) {
                        StringBuilder sb2 = new StringBuilder("No override for ");
                        sb2.append(irFakeOverrideSymbolBase.getOriginalSymbol());
                        ej7.a(sb2, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol());
                        return null;
                    }
                }
                IrOverridableDeclaration owner2 = irSymbol2.getOwner();
                owner2.getClass();
                sequenceCollectOverrides = specialFakeOverrideSymbolsResolver.collectOverrides(owner2, this.$visited);
                this.L$0 = sequenceScope;
                this.L$1 = it;
                this.L$2 = SpillingKt.nullOutSpilledVariable(irSymbol);
                this.label = 2;
                if (sequenceScope.yieldAll(sequenceCollectOverrides, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Sequence<IrSymbol> collectOverrides(IrOverridableDeclaration<?> irOverridableDeclaration, Set<IrSymbol> set) {
        return SequencesKt.sequence(new AnonymousClass1(set, irOverridableDeclaration, this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processClass(IrClass irClass) {
        if (irClass.isExpect()) {
            dt1.a("There should be no references to expect classes at this point\n", RenderIrElementKt.render$default(irClass, (DumpIrTreeOptions) null, 1, (Object) null));
            return;
        }
        if (this.processedClasses.add(irClass)) {
            for (IrProperty irProperty : irClass.getDeclarations()) {
                if (irProperty instanceof IrOverridableDeclaration) {
                    processDeclaration(irClass.getSymbol(), (IrOverridableDeclaration) irProperty);
                    if (irProperty instanceof IrProperty) {
                        IrProperty irProperty2 = irProperty;
                        IrSimpleFunction getter = irProperty2.getGetter();
                        if (getter != null) {
                            processDeclaration(irClass.getSymbol(), getter);
                        }
                        IrSimpleFunction setter = irProperty2.getSetter();
                        if (setter != null) {
                            processDeclaration(irClass.getSymbol(), setter);
                        }
                    }
                }
            }
        }
    }

    private final void processDeclaration(IrClassSymbol classSymbol, IrOverridableDeclaration<?> declaration) {
        Iterator it = collectOverrides(declaration, new LinkedHashSet()).iterator();
        while (it.hasNext()) {
            this.cachedFakeOverrides.put(TuplesKt.to(classSymbol, (IrSymbol) it.next()), declaration.getSymbol());
        }
    }

    public final void cacheFakeOverridesOfAllClasses(IrModuleFragment irModuleFragment) {
        irModuleFragment.getClass();
        IrVisitorsKt.acceptChildrenVoid(irModuleFragment, new IrVisitorVoid() { // from class: org.jetbrains.kotlin.backend.common.actualizer.SpecialFakeOverrideSymbolsResolver$cacheFakeOverridesOfAllClasses$visitor$1
            public void visitClass(IrClass declaration) {
                declaration.getClass();
                if (!declaration.isExpect()) {
                    this.this$0.processClass(declaration);
                }
                IrVisitorsKt.acceptChildrenVoid(declaration, this);
            }

            public void visitElement(IrElement element) {
                element.getClass();
            }

            public void visitFile(IrFile declaration) {
                declaration.getClass();
                IrVisitorsKt.acceptChildrenVoid(declaration, this);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public IrFieldSymbol getReferencedField(IrFieldSymbol symbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        symbol.getClass();
        if (!(symbol instanceof IrFieldFakeOverrideSymbol)) {
            return symbol;
        }
        IrFieldFakeOverrideSymbol irFieldFakeOverrideSymbol = (IrFieldFakeOverrideSymbol) symbol;
        IrBindableSymbol correspondingPropertySymbol = irFieldFakeOverrideSymbol.getCorrespondingPropertySymbol();
        if (correspondingPropertySymbol instanceof IrFakeOverrideSymbolBase) {
            IrFakeOverrideSymbolBase irFakeOverrideSymbolBase = (IrFakeOverrideSymbolBase) correspondingPropertySymbol;
            processClass(irFakeOverrideSymbolBase.getContainingClassSymbol().getOwner());
            IrBindableSymbol irBindableSymbol = (IrSymbol) this.cachedFakeOverrides.get(TuplesKt.to(irFakeOverrideSymbolBase.getContainingClassSymbol(), irFakeOverrideSymbolBase.getOriginalSymbol()));
            if (irBindableSymbol == null) {
                StringBuilder sb = new StringBuilder("No override for ");
                sb.append(irFakeOverrideSymbolBase.getOriginalSymbol());
                ej7.a(sb, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol());
                return null;
            }
            IrBindableSymbol irBindableSymbol2 = (IrPropertySymbol) (!(irBindableSymbol instanceof IrPropertySymbol) ? null : irBindableSymbol);
            if (irBindableSymbol2 == null) {
                StringBuilder sb2 = new StringBuilder("Override for ");
                sb2.append(irFakeOverrideSymbolBase.getOriginalSymbol());
                bhc.a(sb2, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol(), " has incompatible type: ", irBindableSymbol);
                return null;
            }
            correspondingPropertySymbol = irBindableSymbol2;
        }
        IrField backingField = correspondingPropertySymbol.getOwner().getBackingField();
        if (backingField != null) {
            return backingField.getSymbol();
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Remapped property for f/o field doesn't contain backing field");
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        exceptionAttachmentBuilder.withEntry("originalField", RenderIrElementKt.render$default(irFieldFakeOverrideSymbol.getOriginalSymbol().getOwner(), (DumpIrTreeOptions) null, 1, (Object) null));
        exceptionAttachmentBuilder.withEntry("containingClass", RenderIrElementKt.render$default(irFieldFakeOverrideSymbol.getContainingClassSymbol().getOwner(), (DumpIrTreeOptions) null, 1, (Object) null));
        exceptionAttachmentBuilder.withEntry("remappedProperty", RenderIrElementKt.render$default(correspondingPropertySymbol.getOwner(), (DumpIrTreeOptions) null, 1, (Object) null));
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public IrFunctionSymbol getReferencedFunction(IrFunctionSymbol symbol) {
        symbol.getClass();
        if (!(symbol instanceof IrFakeOverrideSymbolBase)) {
            return symbol;
        }
        IrFakeOverrideSymbolBase irFakeOverrideSymbolBase = (IrFakeOverrideSymbolBase) symbol;
        processClass(irFakeOverrideSymbolBase.getContainingClassSymbol().getOwner());
        IrFunctionSymbol irFunctionSymbol = (IrSymbol) this.cachedFakeOverrides.get(TuplesKt.to(irFakeOverrideSymbolBase.getContainingClassSymbol(), irFakeOverrideSymbolBase.getOriginalSymbol()));
        if (irFunctionSymbol == null) {
            StringBuilder sb = new StringBuilder("No override for ");
            sb.append(irFakeOverrideSymbolBase.getOriginalSymbol());
            ej7.a(sb, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol());
            return null;
        }
        IrFunctionSymbol irFunctionSymbol2 = !(irFunctionSymbol instanceof IrFunctionSymbol) ? null : irFunctionSymbol;
        if (irFunctionSymbol2 != null) {
            return irFunctionSymbol2;
        }
        StringBuilder sb2 = new StringBuilder("Override for ");
        sb2.append(irFakeOverrideSymbolBase.getOriginalSymbol());
        bhc.a(sb2, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol(), " has incompatible type: ", irFunctionSymbol);
        return null;
    }

    public IrPropertySymbol getReferencedProperty(IrPropertySymbol symbol) {
        symbol.getClass();
        if (!(symbol instanceof IrFakeOverrideSymbolBase)) {
            return symbol;
        }
        IrFakeOverrideSymbolBase irFakeOverrideSymbolBase = (IrFakeOverrideSymbolBase) symbol;
        processClass(irFakeOverrideSymbolBase.getContainingClassSymbol().getOwner());
        IrPropertySymbol irPropertySymbol = (IrSymbol) this.cachedFakeOverrides.get(TuplesKt.to(irFakeOverrideSymbolBase.getContainingClassSymbol(), irFakeOverrideSymbolBase.getOriginalSymbol()));
        if (irPropertySymbol == null) {
            StringBuilder sb = new StringBuilder("No override for ");
            sb.append(irFakeOverrideSymbolBase.getOriginalSymbol());
            ej7.a(sb, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol());
            return null;
        }
        IrPropertySymbol irPropertySymbol2 = !(irPropertySymbol instanceof IrPropertySymbol) ? null : irPropertySymbol;
        if (irPropertySymbol2 != null) {
            return irPropertySymbol2;
        }
        StringBuilder sb2 = new StringBuilder("Override for ");
        sb2.append(irFakeOverrideSymbolBase.getOriginalSymbol());
        bhc.a(sb2, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol(), " has incompatible type: ", irPropertySymbol);
        return null;
    }

    public IrSimpleFunctionSymbol getReferencedSimpleFunction(IrSimpleFunctionSymbol symbol) {
        symbol.getClass();
        if (!(symbol instanceof IrFakeOverrideSymbolBase)) {
            return symbol;
        }
        IrFakeOverrideSymbolBase irFakeOverrideSymbolBase = (IrFakeOverrideSymbolBase) symbol;
        processClass(irFakeOverrideSymbolBase.getContainingClassSymbol().getOwner());
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSymbol) this.cachedFakeOverrides.get(TuplesKt.to(irFakeOverrideSymbolBase.getContainingClassSymbol(), irFakeOverrideSymbolBase.getOriginalSymbol()));
        if (irSimpleFunctionSymbol == null) {
            StringBuilder sb = new StringBuilder("No override for ");
            sb.append(irFakeOverrideSymbolBase.getOriginalSymbol());
            ej7.a(sb, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol());
            return null;
        }
        IrSimpleFunctionSymbol irSimpleFunctionSymbol2 = !(irSimpleFunctionSymbol instanceof IrSimpleFunctionSymbol) ? null : irSimpleFunctionSymbol;
        if (irSimpleFunctionSymbol2 != null) {
            return irSimpleFunctionSymbol2;
        }
        StringBuilder sb2 = new StringBuilder("Override for ");
        sb2.append(irFakeOverrideSymbolBase.getOriginalSymbol());
        bhc.a(sb2, " in ", irFakeOverrideSymbolBase.getContainingClassSymbol(), " has incompatible type: ", irSimpleFunctionSymbol);
        return null;
    }
}
