package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.GlobalInlineContext;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.utils.ThreadLocalKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001#B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\fJ\u0006\u0010\u0017\u001a\u00020\u0015J4\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0018\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00150\u001eJ\u0006\u0010\u001f\u001a\u00020\u0015J\u000e\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u0011J\u000e\u0010\"\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u0011R#\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\r\u0010\u000eR'\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000b0\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0012\u0010\u0007¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/GlobalInlineContext;", Argument.Delimiters.none, "<init>", "()V", "inlineCallsAndDeclarations", "Ljava/util/LinkedList;", "getInlineCallsAndDeclarations", "()Ljava/util/LinkedList;", "inlineCallsAndDeclarations$delegate", "Lkotlin/properties/ReadWriteProperty;", "inlineDeclarationSet", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "getInlineDeclarationSet", "()Ljava/util/Set;", "inlineDeclarationSet$delegate", "typesUsedInInlineFunctions", Argument.Delimiters.none, "getTypesUsedInInlineFunctions", "typesUsedInInlineFunctions$delegate", "enterDeclaration", Argument.Delimiters.none, "descriptor", "exitDeclaration", "enterIntoInlining", Argument.Delimiters.none, "callee", "element", "Lorg/jetbrains/kotlin/codegen/inline/GlobalInlineContext$InlineFunctionSource;", "reportInlineCallCycle", "Lkotlin/Function2;", "exitFromInlining", "recordTypeFromInlineFunction", ModuleXmlParser.TYPE, "isTypeFromInlineFunction", "InlineFunctionSource", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GlobalInlineContext {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(GlobalInlineContext.class, "inlineCallsAndDeclarations", "getInlineCallsAndDeclarations()Ljava/util/LinkedList;", 0), new PropertyReference1Impl<>(GlobalInlineContext.class, "inlineDeclarationSet", "getInlineDeclarationSet()Ljava/util/Set;", 0), new PropertyReference1Impl<>(GlobalInlineContext.class, "typesUsedInInlineFunctions", "getTypesUsedInInlineFunctions()Ljava/util/LinkedList;", 0)};

    /* JADX INFO: renamed from: inlineCallsAndDeclarations$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty inlineCallsAndDeclarations = ThreadLocalKt.threadLocal(new Function0() { // from class: mz5
        public final Object invoke() {
            return GlobalInlineContext.c();
        }
    });

    /* JADX INFO: renamed from: inlineDeclarationSet$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty inlineDeclarationSet = ThreadLocalKt.threadLocal(new Function0() { // from class: nz5
        public final Object invoke() {
            return GlobalInlineContext.b();
        }
    });

    /* JADX INFO: renamed from: typesUsedInInlineFunctions$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty typesUsedInInlineFunctions = ThreadLocalKt.threadLocal(new Function0() { // from class: oz5
        public final Object invoke() {
            return GlobalInlineContext.a();
        }
    });

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/GlobalInlineContext$InlineFunctionSource;", Argument.Delimiters.none, "<init>", "()V", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class InlineFunctionSource {
    }

    public static LinkedList a() {
        return new LinkedList();
    }

    public static Set b() {
        return new LinkedHashSet();
    }

    public static LinkedList c() {
        return new LinkedList();
    }

    private final LinkedList<Object> getInlineCallsAndDeclarations() {
        return (LinkedList) this.inlineCallsAndDeclarations.getValue(this, $$delegatedProperties[0]);
    }

    private final Set<CallableDescriptor> getInlineDeclarationSet() {
        return (Set) this.inlineDeclarationSet.getValue(this, $$delegatedProperties[1]);
    }

    private final LinkedList<Set<String>> getTypesUsedInInlineFunctions() {
        return (LinkedList) this.typesUsedInInlineFunctions.getValue(this, $$delegatedProperties[2]);
    }

    public final void enterDeclaration(CallableDescriptor descriptor) {
        descriptor.getClass();
        getInlineDeclarationSet().contains(descriptor.m84getOriginal());
        Set<CallableDescriptor> inlineDeclarationSet = getInlineDeclarationSet();
        CallableDescriptor callableDescriptorM84getOriginal = descriptor.m84getOriginal();
        callableDescriptorM84getOriginal.getClass();
        inlineDeclarationSet.add(callableDescriptorM84getOriginal);
        getInlineCallsAndDeclarations().add(descriptor.m84getOriginal());
    }

    public final boolean enterIntoInlining(CallableDescriptor callee, InlineFunctionSource element, Function2<? super InlineFunctionSource, ? super CallableDescriptor, Unit> reportInlineCallCycle) {
        reportInlineCallCycle.getClass();
        if (callee == null || !getInlineDeclarationSet().contains(callee.m84getOriginal())) {
            getInlineCallsAndDeclarations().add(element);
            getTypesUsedInInlineFunctions().push(new HashSet());
            return true;
        }
        if (element != null) {
            CallableDescriptor callableDescriptorM84getOriginal = callee.m84getOriginal();
            callableDescriptorM84getOriginal.getClass();
            reportInlineCallCycle.invoke(element, callableDescriptorM84getOriginal);
        }
        LinkedList<Object> inlineCallsAndDeclarations = getInlineCallsAndDeclarations();
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (Object obj : inlineCallsAndDeclarations) {
            if (z) {
                arrayList.add(obj);
            } else if (Intrinsics.areEqual(obj, callee.m84getOriginal())) {
                arrayList.add(obj);
                z = true;
            }
        }
        for (Pair pair : CollectionsKt.zipWithNext(arrayList)) {
            Object objComponent1 = pair.component1();
            Object objComponent2 = pair.component2();
            if ((objComponent1 instanceof InlineFunctionSource) && (objComponent2 instanceof CallableDescriptor)) {
                reportInlineCallCycle.invoke(objComponent1, objComponent2);
            }
        }
        return false;
    }

    public final void exitDeclaration() {
        Set<CallableDescriptor> inlineDeclarationSet = getInlineDeclarationSet();
        TypeIntrinsics.asMutableCollection(inlineDeclarationSet).remove(getInlineCallsAndDeclarations().removeLast());
    }

    public final void exitFromInlining() {
        getInlineCallsAndDeclarations().removeLast();
        Set<String> setPop = getTypesUsedInInlineFunctions().pop();
        Set<String> setPeek = getTypesUsedInInlineFunctions().peek();
        if (setPeek != null) {
            setPop.getClass();
            setPeek.addAll(setPop);
        }
    }

    public final boolean isTypeFromInlineFunction(String type) {
        type.getClass();
        return getTypesUsedInInlineFunctions().peek().contains(type);
    }

    public final boolean recordTypeFromInlineFunction(String type) {
        type.getClass();
        return getTypesUsedInInlineFunctions().peek().add(type);
    }
}
