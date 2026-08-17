package org.jetbrains.kotlin.backend.jvm;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrMutableAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\",\u0010\u000b\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007\"3\u0010\u000f\u001a\u0004\u0018\u00010\u000e*\u00020\u000e2\b\u0010\u0000\u001a\u0004\u0018\u00010\u000e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\t\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\"?\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015*\u00020\u00182\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00158F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\"/\u0010\u001f\u001a\u00020\u001e*\u00020\u000e2\u0006\u0010\u0000\u001a\u00020\u001e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\"?\u0010'\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\u0015*\u00020(2\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\u00158F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010\t\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,\"3\u0010/\u001a\u0004\u0018\u00010.*\u00020\u00182\b\u0010\u0000\u001a\u0004\u0018\u00010.8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b4\u0010\t\u001a\u0004\b0\u00101\"\u0004\b2\u00103\"3\u00105\u001a\u0004\u0018\u00010\u0018*\u00020\u00182\b\u0010\u0000\u001a\u0004\u0018\u00010\u00188F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010\t\u001a\u0004\b6\u00107\"\u0004\b8\u00109\"3\u0010;\u001a\u0004\u0018\u00010(*\u00020(2\b\u0010\u0000\u001a\u0004\u0018\u00010(8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b@\u0010\t\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?\"3\u0010B\u001a\u0004\u0018\u00010A*\u00020A2\b\u0010\u0000\u001a\u0004\u0018\u00010A8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010\t\u001a\u0004\bC\u0010D\"\u0004\bE\u0010F\"3\u0010H\u001a\u0004\u0018\u00010A*\u00020A2\b\u0010\u0000\u001a\u0004\u0018\u00010A8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bK\u0010\t\u001a\u0004\bI\u0010D\"\u0004\bJ\u0010F\"K\u0010N\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020M\u0018\u00010L*\u00020\u00182\u0014\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020M\u0018\u00010L8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bS\u0010\t\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010R\"/\u0010T\u001a\u00020\u001e*\u00020\u00182\u0006\u0010\u0000\u001a\u00020\u001e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bX\u0010%\u001a\u0004\bT\u0010U\"\u0004\bV\u0010W\"3\u0010Y\u001a\u0004\u0018\u00010.*\u00020\u00182\b\u0010\u0000\u001a\u0004\u0018\u00010.8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\\\u0010\t\u001a\u0004\bZ\u00101\"\u0004\b[\u00103\"3\u0010]\u001a\u0004\u0018\u00010(*\u00020\u000e2\b\u0010\u0000\u001a\u0004\u0018\u00010(8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bb\u0010\t\u001a\u0004\b^\u0010_\"\u0004\b`\u0010a\"3\u0010c\u001a\u0004\u0018\u00010(*\u00020\u000e2\b\u0010\u0000\u001a\u0004\u0018\u00010(8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bf\u0010\t\u001a\u0004\bd\u0010_\"\u0004\be\u0010a\"3\u0010g\u001a\u0004\u0018\u00010(*\u00020\u000e2\b\u0010\u0000\u001a\u0004\u0018\u00010(8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bj\u0010\t\u001a\u0004\bh\u0010_\"\u0004\bi\u0010a\"3\u0010k\u001a\u0004\u0018\u00010(*\u00020(2\b\u0010\u0000\u001a\u0004\u0018\u00010(8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bn\u0010\t\u001a\u0004\bl\u0010=\"\u0004\bm\u0010?\"/\u0010o\u001a\u00020\u001e*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u001e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bs\u0010%\u001a\u0004\bo\u0010p\"\u0004\bq\u0010r\"3\u0010u\u001a\u0004\u0018\u00010t*\u00020v2\b\u0010\u0000\u001a\u0004\u0018\u00010t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b{\u0010\t\u001a\u0004\bw\u0010x\"\u0004\by\u0010z\"6\u0010}\u001a\u0004\u0018\u00010|*\u00020|2\b\u0010\u0000\u001a\u0004\u0018\u00010|8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0082\u0001\u0010\t\u001a\u0004\b~\u0010\u007f\"\u0006\b\u0080\u0001\u0010\u0081\u0001\"6\u0010\u0083\u0001\u001a\u00020\u001e*\u00030\u0084\u00012\u0006\u0010\u0000\u001a\u00020\u001e8F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b\u0088\u0001\u0010%\u001a\u0006\b\u0083\u0001\u0010\u0085\u0001\"\u0006\b\u0086\u0001\u0010\u0087\u0001¨\u0006\u0089\u0001"}, d2 = {"<set-?>", "Lorg/jetbrains/org/objectweb/asm/Type;", "_localClassType", "Lorg/jetbrains/kotlin/ir/IrElement;", "get_localClassType", "(Lorg/jetbrains/kotlin/ir/IrElement;)Lorg/jetbrains/org/objectweb/asm/Type;", "set_localClassType", "(Lorg/jetbrains/kotlin/ir/IrElement;Lorg/jetbrains/org/objectweb/asm/Type;)V", "_localClassType$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute;", "value", "localClassType", "getLocalClassType", "setLocalClassType", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "enclosingMethodOverride", "getEnclosingMethodOverride", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "setEnclosingMethodOverride", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)V", "enclosingMethodOverride$delegate", "", "Lorg/jetbrains/kotlin/ir/symbols/IrLocalDelegatedPropertySymbol;", "localDelegatedProperties", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getLocalDelegatedProperties", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/List;", "setLocalDelegatedProperties", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Ljava/util/List;)V", "localDelegatedProperties$delegate", "", "hasSpecialBridge", "getHasSpecialBridge", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Z", "setHasSpecialBridge", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Z)V", "hasSpecialBridge$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute$Flag;", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "overridesWithoutStubs", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getOverridesWithoutStubs", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Ljava/util/List;", "setOverridesWithoutStubs", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Ljava/util/List;)V", "overridesWithoutStubs$delegate", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "multifileFacadeForPart", "getMultifileFacadeForPart", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "setMultifileFacadeForPart", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;)V", "multifileFacadeForPart$delegate", "multifileFacadeClassForPart", "getMultifileFacadeClassForPart", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "setMultifileFacadeClassForPart", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/ir/declarations/IrClass;)V", "multifileFacadeClassForPart$delegate", "multifileFacadePartMember", "getMultifileFacadePartMember", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "setMultifileFacadePartMember", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "multifileFacadePartMember$delegate", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "hiddenConstructorMangledParams", "getHiddenConstructorMangledParams", "(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "setHiddenConstructorMangledParams", "(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)V", "hiddenConstructorMangledParams$delegate", "hiddenConstructorOfSealedClass", "getHiddenConstructorOfSealedClass", "setHiddenConstructorOfSealedClass", "hiddenConstructorOfSealedClass$delegate", "", "", "continuationClassVarsCountByType", "getContinuationClassVarsCountByType", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/Map;", "setContinuationClassVarsCountByType", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Ljava/util/Map;)V", "continuationClassVarsCountByType$delegate", "isPublicAbi", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Z", "setPublicAbi", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Z)V", "isPublicAbi$delegate", "classNameOverride", "getClassNameOverride", "setClassNameOverride", "classNameOverride$delegate", "viewOfOriginalSuspendFunction", "getViewOfOriginalSuspendFunction", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "setViewOfOriginalSuspendFunction", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "viewOfOriginalSuspendFunction$delegate", "originalOfSuspendForInline", "getOriginalOfSuspendForInline", "setOriginalOfSuspendForInline", "originalOfSuspendForInline$delegate", "staticSuspendImplMethod", "getStaticSuspendImplMethod", "setStaticSuspendImplMethod", "staticSuspendImplMethod$delegate", "staticDefaultStub", "getStaticDefaultStub", "setStaticDefaultStub", "staticDefaultStub$delegate", "isEnclosedInConstructor", "(Lorg/jetbrains/kotlin/ir/IrElement;)Z", "setEnclosedInConstructor", "(Lorg/jetbrains/kotlin/ir/IrElement;Z)V", "isEnclosedInConstructor$delegate", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "originalSnippetValueSymbol", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "getOriginalSnippetValueSymbol", "(Lorg/jetbrains/kotlin/ir/declarations/IrVariable;)Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "setOriginalSnippetValueSymbol", "(Lorg/jetbrains/kotlin/ir/declarations/IrVariable;Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;)V", "originalSnippetValueSymbol$delegate", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "originalForReflectiveCall", "getOriginalForReflectiveCall", "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;)Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "setOriginalForReflectiveCall", "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;Lorg/jetbrains/kotlin/ir/expressions/IrCall;)V", "originalForReflectiveCall$delegate", "isJavaLangDeprecatedOnlyAddedByCompiler", "Lorg/jetbrains/kotlin/ir/declarations/IrMutableAnnotationContainer;", "(Lorg/jetbrains/kotlin/ir/declarations/IrMutableAnnotationContainer;)Z", "setJavaLangDeprecatedOnlyAddedByCompiler", "(Lorg/jetbrains/kotlin/ir/declarations/IrMutableAnnotationContainer;Z)V", "isJavaLangDeprecatedOnlyAddedByCompiler$delegate", "org.jetbrains.kotlin:backend.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmIrAttributesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute _localClassType$delegate;
    private static final IrAttribute classNameOverride$delegate;
    private static final IrAttribute continuationClassVarsCountByType$delegate;
    private static final IrAttribute enclosingMethodOverride$delegate;
    private static final IrAttribute.Flag hasSpecialBridge$delegate;
    private static final IrAttribute hiddenConstructorMangledParams$delegate;
    private static final IrAttribute hiddenConstructorOfSealedClass$delegate;
    private static final IrAttribute.Flag isEnclosedInConstructor$delegate;
    private static final IrAttribute.Flag isJavaLangDeprecatedOnlyAddedByCompiler$delegate;
    private static final IrAttribute.Flag isPublicAbi$delegate;
    private static final IrAttribute localDelegatedProperties$delegate;
    private static final IrAttribute multifileFacadeClassForPart$delegate;
    private static final IrAttribute multifileFacadeForPart$delegate;
    private static final IrAttribute multifileFacadePartMember$delegate;
    private static final IrAttribute originalForReflectiveCall$delegate;
    private static final IrAttribute originalOfSuspendForInline$delegate;
    private static final IrAttribute originalSnippetValueSymbol$delegate;
    private static final IrAttribute overridesWithoutStubs$delegate;
    private static final IrAttribute staticDefaultStub$delegate;
    private static final IrAttribute staticSuspendImplMethod$delegate;
    private static final IrAttribute viewOfOriginalSuspendFunction$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "_localClassType", "get_localClassType(Lorg/jetbrains/kotlin/ir/IrElement;)Lorg/jetbrains/org/objectweb/asm/Type;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "enclosingMethodOverride", "getEnclosingMethodOverride(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "localDelegatedProperties", "getLocalDelegatedProperties(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/List;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "hasSpecialBridge", "getHasSpecialBridge(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Z", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "overridesWithoutStubs", "getOverridesWithoutStubs(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Ljava/util/List;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "multifileFacadeForPart", "getMultifileFacadeForPart(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "multifileFacadeClassForPart", "getMultifileFacadeClassForPart(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/ir/declarations/IrClass;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "multifileFacadePartMember", "getMultifileFacadePartMember(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "hiddenConstructorMangledParams", "getHiddenConstructorMangledParams(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "hiddenConstructorOfSealedClass", "getHiddenConstructorOfSealedClass(Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;)Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "continuationClassVarsCountByType", "getContinuationClassVarsCountByType(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Ljava/util/Map;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "isPublicAbi", "isPublicAbi(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Z", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "classNameOverride", "getClassNameOverride(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "viewOfOriginalSuspendFunction", "getViewOfOriginalSuspendFunction(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "originalOfSuspendForInline", "getOriginalOfSuspendForInline(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "staticSuspendImplMethod", "getStaticSuspendImplMethod(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "staticDefaultStub", "getStaticDefaultStub(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "isEnclosedInConstructor", "isEnclosedInConstructor(Lorg/jetbrains/kotlin/ir/IrElement;)Z", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "originalSnippetValueSymbol", "getOriginalSnippetValueSymbol(Lorg/jetbrains/kotlin/ir/declarations/IrVariable;)Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "originalForReflectiveCall", "getOriginalForReflectiveCall(Lorg/jetbrains/kotlin/ir/expressions/IrCall;)Lorg/jetbrains/kotlin/ir/expressions/IrCall;", 1), new MutablePropertyReference1Impl<>(JvmIrAttributesKt.class, "isJavaLangDeprecatedOnlyAddedByCompiler", "isJavaLangDeprecatedOnlyAddedByCompiler(Lorg/jetbrains/kotlin/ir/declarations/IrMutableAnnotationContainer;)Z", 1)};
        $$delegatedProperties = kPropertyArr;
        _localClassType$delegate = IrAttributeKt.irAttribute(true).provideDelegate((Object) null, kPropertyArr[0]);
        enclosingMethodOverride$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[1]);
        localDelegatedProperties$delegate = IrAttributeKt.irAttribute(true).provideDelegate((Object) null, kPropertyArr[2]);
        hasSpecialBridge$delegate = IrAttributeKt.irFlag(false).provideDelegate((Object) null, kPropertyArr[3]);
        overridesWithoutStubs$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[4]);
        multifileFacadeForPart$delegate = IrAttributeKt.irAttribute(true).provideDelegate((Object) null, kPropertyArr[5]);
        multifileFacadeClassForPart$delegate = IrAttributeKt.irAttribute(true).provideDelegate((Object) null, kPropertyArr[6]);
        multifileFacadePartMember$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[7]);
        hiddenConstructorMangledParams$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[8]);
        hiddenConstructorOfSealedClass$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[9]);
        continuationClassVarsCountByType$delegate = IrAttributeKt.irAttribute(true).provideDelegate((Object) null, kPropertyArr[10]);
        isPublicAbi$delegate = IrAttributeKt.irFlag(false).provideDelegate((Object) null, kPropertyArr[11]);
        classNameOverride$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[12]);
        viewOfOriginalSuspendFunction$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[13]);
        originalOfSuspendForInline$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[14]);
        staticSuspendImplMethod$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[15]);
        staticDefaultStub$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[16]);
        isEnclosedInConstructor$delegate = IrAttributeKt.irFlag(true).provideDelegate((Object) null, kPropertyArr[17]);
        originalSnippetValueSymbol$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[18]);
        originalForReflectiveCall$delegate = IrAttributeKt.irAttribute(false).provideDelegate((Object) null, kPropertyArr[19]);
        isJavaLangDeprecatedOnlyAddedByCompiler$delegate = IrAttributeKt.irFlag(true).provideDelegate((Object) null, kPropertyArr[20]);
    }

    public static final JvmClassName getClassNameOverride(IrClass irClass) {
        irClass.getClass();
        return (JvmClassName) IrAttributeKt.get(irClass, classNameOverride$delegate);
    }

    public static final Map<Type, Integer> getContinuationClassVarsCountByType(IrClass irClass) {
        irClass.getClass();
        return (Map) IrAttributeKt.get(irClass, continuationClassVarsCountByType$delegate);
    }

    public static final IrFunction getEnclosingMethodOverride(IrFunction irFunction) {
        irFunction.getClass();
        return (IrFunction) IrAttributeKt.get(irFunction, enclosingMethodOverride$delegate);
    }

    public static final boolean getHasSpecialBridge(IrFunction irFunction) {
        irFunction.getClass();
        return hasSpecialBridge$delegate.get(irFunction);
    }

    public static final IrConstructor getHiddenConstructorMangledParams(IrConstructor irConstructor) {
        irConstructor.getClass();
        return (IrConstructor) IrAttributeKt.get(irConstructor, hiddenConstructorMangledParams$delegate);
    }

    public static final IrConstructor getHiddenConstructorOfSealedClass(IrConstructor irConstructor) {
        irConstructor.getClass();
        return (IrConstructor) IrAttributeKt.get(irConstructor, hiddenConstructorOfSealedClass$delegate);
    }

    public static final Type getLocalClassType(IrElement irElement) {
        irElement.getClass();
        return get_localClassType(irElement.getAttributeOwnerId());
    }

    public static final List<IrLocalDelegatedPropertySymbol> getLocalDelegatedProperties(IrClass irClass) {
        irClass.getClass();
        return (List) IrAttributeKt.get(irClass, localDelegatedProperties$delegate);
    }

    public static final IrClass getMultifileFacadeClassForPart(IrClass irClass) {
        irClass.getClass();
        return (IrClass) IrAttributeKt.get(irClass, multifileFacadeClassForPart$delegate);
    }

    public static final JvmClassName getMultifileFacadeForPart(IrClass irClass) {
        irClass.getClass();
        return (JvmClassName) IrAttributeKt.get(irClass, multifileFacadeForPart$delegate);
    }

    public static final IrSimpleFunction getMultifileFacadePartMember(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irSimpleFunction, multifileFacadePartMember$delegate);
    }

    public static final IrCall getOriginalForReflectiveCall(IrCall irCall) {
        irCall.getClass();
        return (IrCall) IrAttributeKt.get(irCall, originalForReflectiveCall$delegate);
    }

    public static final IrSimpleFunction getOriginalOfSuspendForInline(IrFunction irFunction) {
        irFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irFunction, originalOfSuspendForInline$delegate);
    }

    public static final IrSymbol getOriginalSnippetValueSymbol(IrVariable irVariable) {
        irVariable.getClass();
        return (IrSymbol) IrAttributeKt.get(irVariable, originalSnippetValueSymbol$delegate);
    }

    public static final List<IrSimpleFunctionSymbol> getOverridesWithoutStubs(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        return (List) IrAttributeKt.get(irSimpleFunction, overridesWithoutStubs$delegate);
    }

    public static final IrSimpleFunction getStaticDefaultStub(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irSimpleFunction, staticDefaultStub$delegate);
    }

    public static final IrSimpleFunction getStaticSuspendImplMethod(IrFunction irFunction) {
        irFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irFunction, staticSuspendImplMethod$delegate);
    }

    public static final IrSimpleFunction getViewOfOriginalSuspendFunction(IrFunction irFunction) {
        irFunction.getClass();
        return (IrSimpleFunction) IrAttributeKt.get(irFunction, viewOfOriginalSuspendFunction$delegate);
    }

    private static final Type get_localClassType(IrElement irElement) {
        return (Type) IrAttributeKt.get(irElement, _localClassType$delegate);
    }

    public static final boolean isEnclosedInConstructor(IrElement irElement) {
        irElement.getClass();
        return isEnclosedInConstructor$delegate.get(irElement);
    }

    public static final boolean isJavaLangDeprecatedOnlyAddedByCompiler(IrMutableAnnotationContainer irMutableAnnotationContainer) {
        irMutableAnnotationContainer.getClass();
        return isJavaLangDeprecatedOnlyAddedByCompiler$delegate.get((IrElement) irMutableAnnotationContainer);
    }

    public static final boolean isPublicAbi(IrClass irClass) {
        irClass.getClass();
        return isPublicAbi$delegate.get(irClass);
    }

    public static final void setClassNameOverride(IrClass irClass, JvmClassName jvmClassName) {
        irClass.getClass();
        IrAttributeKt.set(irClass, classNameOverride$delegate, jvmClassName);
    }

    public static final void setContinuationClassVarsCountByType(IrClass irClass, Map<Type, Integer> map) {
        irClass.getClass();
        IrAttributeKt.set(irClass, continuationClassVarsCountByType$delegate, map);
    }

    public static final void setEnclosedInConstructor(IrElement irElement, boolean z) {
        irElement.getClass();
        isEnclosedInConstructor$delegate.set(irElement, z);
    }

    public static final void setEnclosingMethodOverride(IrFunction irFunction, IrFunction irFunction2) {
        irFunction.getClass();
        IrAttributeKt.set(irFunction, enclosingMethodOverride$delegate, irFunction2);
    }

    public static final void setHasSpecialBridge(IrFunction irFunction, boolean z) {
        irFunction.getClass();
        hasSpecialBridge$delegate.set(irFunction, z);
    }

    public static final void setHiddenConstructorMangledParams(IrConstructor irConstructor, IrConstructor irConstructor2) {
        irConstructor.getClass();
        IrAttributeKt.set(irConstructor, hiddenConstructorMangledParams$delegate, irConstructor2);
    }

    public static final void setHiddenConstructorOfSealedClass(IrConstructor irConstructor, IrConstructor irConstructor2) {
        irConstructor.getClass();
        IrAttributeKt.set(irConstructor, hiddenConstructorOfSealedClass$delegate, irConstructor2);
    }

    public static final void setJavaLangDeprecatedOnlyAddedByCompiler(IrMutableAnnotationContainer irMutableAnnotationContainer, boolean z) {
        irMutableAnnotationContainer.getClass();
        isJavaLangDeprecatedOnlyAddedByCompiler$delegate.set((IrElement) irMutableAnnotationContainer, z);
    }

    public static final void setLocalClassType(IrElement irElement, Type type) {
        irElement.getClass();
        set_localClassType(irElement.getAttributeOwnerId(), type);
    }

    public static final void setLocalDelegatedProperties(IrClass irClass, List<? extends IrLocalDelegatedPropertySymbol> list) {
        irClass.getClass();
        IrAttributeKt.set(irClass, localDelegatedProperties$delegate, list);
    }

    public static final void setMultifileFacadeClassForPart(IrClass irClass, IrClass irClass2) {
        irClass.getClass();
        IrAttributeKt.set(irClass, multifileFacadeClassForPart$delegate, irClass2);
    }

    public static final void setMultifileFacadeForPart(IrClass irClass, JvmClassName jvmClassName) {
        irClass.getClass();
        IrAttributeKt.set(irClass, multifileFacadeForPart$delegate, jvmClassName);
    }

    public static final void setMultifileFacadePartMember(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2) {
        irSimpleFunction.getClass();
        IrAttributeKt.set(irSimpleFunction, multifileFacadePartMember$delegate, irSimpleFunction2);
    }

    public static final void setOriginalForReflectiveCall(IrCall irCall, IrCall irCall2) {
        irCall.getClass();
        IrAttributeKt.set(irCall, originalForReflectiveCall$delegate, irCall2);
    }

    public static final void setOriginalOfSuspendForInline(IrFunction irFunction, IrSimpleFunction irSimpleFunction) {
        irFunction.getClass();
        IrAttributeKt.set(irFunction, originalOfSuspendForInline$delegate, irSimpleFunction);
    }

    public static final void setOriginalSnippetValueSymbol(IrVariable irVariable, IrSymbol irSymbol) {
        irVariable.getClass();
        IrAttributeKt.set(irVariable, originalSnippetValueSymbol$delegate, irSymbol);
    }

    public static final void setOverridesWithoutStubs(IrSimpleFunction irSimpleFunction, List<? extends IrSimpleFunctionSymbol> list) {
        irSimpleFunction.getClass();
        IrAttributeKt.set(irSimpleFunction, overridesWithoutStubs$delegate, list);
    }

    public static final void setPublicAbi(IrClass irClass, boolean z) {
        irClass.getClass();
        isPublicAbi$delegate.set(irClass, z);
    }

    public static final void setStaticDefaultStub(IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2) {
        irSimpleFunction.getClass();
        IrAttributeKt.set(irSimpleFunction, staticDefaultStub$delegate, irSimpleFunction2);
    }

    public static final void setStaticSuspendImplMethod(IrFunction irFunction, IrSimpleFunction irSimpleFunction) {
        irFunction.getClass();
        IrAttributeKt.set(irFunction, staticSuspendImplMethod$delegate, irSimpleFunction);
    }

    public static final void setViewOfOriginalSuspendFunction(IrFunction irFunction, IrSimpleFunction irSimpleFunction) {
        irFunction.getClass();
        IrAttributeKt.set(irFunction, viewOfOriginalSuspendFunction$delegate, irSimpleFunction);
    }

    private static final void set_localClassType(IrElement irElement, Type type) {
        IrAttributeKt.set(irElement, _localClassType$delegate, type);
    }
}
