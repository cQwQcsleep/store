package org.jetbrains.kotlin.fir.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirFunctionTarget;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirLoopTarget;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.builder.FirOuterClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.PrivateForInline;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001|B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020\u000f2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0)J\u0006\u0010]\u001a\u00020YJ\u001c\u0010^\u001a\u00020Y2\u0006\u0010_\u001a\u00020\u000f2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020\\0\u001cJ\u0012\u0010j\u001a\u00020Y2\n\u0010k\u001a\u0006\u0012\u0002\b\u00030SJ\u0012\u0010l\u001a\u00020Y2\n\u0010k\u001a\u0006\u0012\u0002\b\u00030SJ\u0010\u0010m\u001a\u0004\u0018\u00010$2\u0006\u0010n\u001a\u00020\u0002J\u000e\u0010o\u001a\u00020Y2\u0006\u0010p\u001a\u00020$J\u0010\u0010q\u001a\u00020Y2\b\u0010r\u001a\u0004\u0018\u00010\u0002J\u0006\u0010s\u001a\u00020YJ7\u0010t\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010p\u001a\u00020$2\b\u0010u\u001a\u0004\u0018\u00010\u00022\f\u0010v\u001a\b\u0012\u0004\u0012\u0002H\u00010wH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010xJ\u0018\u0010y\u001a\u00020Y2\u0006\u0010z\u001a\u00020\u00022\b\u0010{\u001a\u0004\u0018\u00010\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\b\"\u0004\b\u001a\u0010\nR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR&\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001c8\u0006X\u0087\u0004r\u0002\b'¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u0004\u001a\u0004\b&\u0010\u001fR\u001d\u0010(\u001a\b\u0012\u0004\u0012\u00020$0)8F¢\u0006\f\u0012\u0004\b*\u0010\u0004\u001a\u0004\b+\u0010\u001fR*\u0010,\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000er\u0002\b'¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\u0004\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0017\u00102\u001a\b\u0012\u0004\u0012\u0002030\u001c¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001fR\u0017\u00105\u001a\b\u0012\u0004\u0012\u0002060\u001c¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001fR\u001d\u00108\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020:09¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u001c¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u001fR\u001a\u0010@\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0011\"\u0004\bB\u0010\u0013R\u001a\u0010C\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0011\"\u0004\bE\u0010\u0013R\u001c\u0010F\u001a\u0004\u0018\u00010GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001c\u0010L\u001a\u0004\u0018\u00010MX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR \u0010R\u001a\b\u0012\u0002\b\u0003\u0018\u00010SX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR5\u0010b\u001a\b\u0012\u0002\b\u0003\u0018\u00010S2\f\u0010a\u001a\b\u0012\u0002\b\u0003\u0018\u00010S@GX\u0086\u000e\u0082\u0001\u0002\b'¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010U\"\u0004\bd\u0010WR\u0015\u0010e\u001a\u0006\u0012\u0002\b\u00030S8F¢\u0006\u0006\u001a\u0004\bf\u0010UR\u0017\u0010g\u001a\b\u0012\u0002\b\u0003\u0018\u00010S8F¢\u0006\u0006\u001a\u0004\bh\u0010UR\u0018\u0010i\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030S0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006}"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/Context;", "T", Argument.Delimiters.none, "<init>", "()V", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "setPackageFqName", "(Lorg/jetbrains/kotlin/name/FqName;)V", "className", "getClassName", "setClassName", "inLocalContext", Argument.Delimiters.none, "getInLocalContext", "()Z", "setInLocalContext", "(Z)V", "currentClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "getCurrentClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "classNameBeforeLocalContext", "getClassNameBeforeLocalContext", "setClassNameBeforeLocalContext", "firFunctionTargets", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirFunctionTarget;", "getFirFunctionTargets", "()Ljava/util/List;", "calleeNamesForLambda", "Lorg/jetbrains/kotlin/name/Name;", "getCalleeNamesForLambda", "_firLabels", "Lorg/jetbrains/kotlin/fir/FirLabel;", "get_firLabels$annotations", "get_firLabels", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "firLabels", Argument.Delimiters.none, "getFirLabels$annotations", "getFirLabels", "firLabelUserNode", "getFirLabelUserNode$annotations", "getFirLabelUserNode", "()Ljava/lang/Object;", "setFirLabelUserNode", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "firLoopTargets", "Lorg/jetbrains/kotlin/fir/FirLoopTarget;", "getFirLoopTargets", "capturedTypeParameters", "Lorg/jetbrains/kotlin/fir/builder/Context$StatusFirTypeParameterSymbolList;", "getCapturedTypeParameters", "arraySetArgument", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getArraySetArgument", "()Ljava/util/Map;", "dispatchReceiverTypesStack", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "getDispatchReceiverTypesStack", "containerIsExpect", "getContainerIsExpect", "setContainerIsExpect", "forceKeepingTheBodyInHeaderMode", "getForceKeepingTheBodyInHeaderMode", "setForceKeepingTheBodyInHeaderMode", "containingScriptSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "getContainingScriptSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "setContainingScriptSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;)V", "containingReplSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "getContainingReplSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "setContainingReplSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;)V", "currentCompanionBlockOwnerOrNull", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getCurrentCompanionBlockOwnerOrNull", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "setCurrentCompanionBlockOwnerOrNull", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "pushFirTypeParameters", Argument.Delimiters.none, "isInnerOrLocal", "parameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "popFirTypeParameters", "appendOuterTypeParameters", "ignoreLastLevel", "typeParameters", "value", "forcedContainerSymbol", "getForcedContainerSymbol", "setForcedContainerSymbol", "containerSymbol", "getContainerSymbol", "containerSymbolIfAny", "getContainerSymbolIfAny", "_containerSymbolStack", "pushContainerSymbol", "symbol", "popContainerSymbol", "getLastLabel", "currentNode", "addNewLabel", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "setNewLabelUserNode", "useNode", "dropLastLabel", "withNewLabel", "userNode", "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/FirLabel;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forwardLabelUsagePermission", "currentUserNode", "newUserNode", "StatusFirTypeParameterSymbolList", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Context<T> {
    private final List<FirBasedSymbol<?>> _containerSymbolStack;
    private final List<FirLabel> _firLabels;
    private final Map<T, FirExpression> arraySetArgument;
    private final List<Name> calleeNamesForLambda;
    private final List<StatusFirTypeParameterSymbolList> capturedTypeParameters;
    private FqName className;
    private FqName classNameBeforeLocalContext;
    private boolean containerIsExpect;
    private FirReplSnippetSymbol containingReplSymbol;
    private FirScriptSymbol containingScriptSymbol;
    private FirBasedSymbol<?> currentCompanionBlockOwnerOrNull;
    private final List<ConeClassLikeType> dispatchReceiverTypesStack;
    private final List<FirFunctionTarget> firFunctionTargets;
    private Object firLabelUserNode;
    private final List<FirLoopTarget> firLoopTargets;
    private boolean forceKeepingTheBodyInHeaderMode;
    private FirBasedSymbol<?> forcedContainerSymbol;
    private boolean inLocalContext;
    public FqName packageFqName;

    public Context() {
        FqName fqName = FqName.ROOT;
        this.className = fqName;
        this.classNameBeforeLocalContext = fqName;
        this.firFunctionTargets = new ArrayList();
        this.calleeNamesForLambda = new ArrayList();
        this._firLabels = new ArrayList();
        this.firLoopTargets = new ArrayList();
        this.capturedTypeParameters = new ArrayList();
        this.arraySetArgument = new LinkedHashMap();
        this.dispatchReceiverTypesStack = new ArrayList();
        this._containerSymbolStack = new ArrayList();
    }

    @PrivateForInline
    public static /* synthetic */ void getFirLabelUserNode$annotations() {
    }

    public static /* synthetic */ void getFirLabels$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void get_firLabels$annotations() {
    }

    public final void addNewLabel(FirLabel label) {
        label.getClass();
        this._firLabels.add(label);
    }

    public final void appendOuterTypeParameters(boolean ignoreLastLevel, List<FirTypeParameterRef> typeParameters) {
        typeParameters.getClass();
        for (int lastIndex = CollectionsKt.getLastIndex(this.capturedTypeParameters); -1 < lastIndex; lastIndex--) {
            StatusFirTypeParameterSymbolList statusFirTypeParameterSymbolList = this.capturedTypeParameters.get(lastIndex);
            if (lastIndex < CollectionsKt.getLastIndex(this.capturedTypeParameters) || !ignoreLastLevel) {
                for (FirTypeParameterSymbol firTypeParameterSymbol : statusFirTypeParameterSymbolList.getList()) {
                    FirOuterClassTypeParameterRefBuilder firOuterClassTypeParameterRefBuilder = new FirOuterClassTypeParameterRefBuilder();
                    firOuterClassTypeParameterRefBuilder.setSymbol(firTypeParameterSymbol);
                    typeParameters.add(firOuterClassTypeParameterRefBuilder.build());
                }
            }
            if (!statusFirTypeParameterSymbolList.isInnerOrLocal()) {
                return;
            }
        }
    }

    public final void dropLastLabel() {
        CollectionsKt.removeLast(this._firLabels);
        this.firLabelUserNode = null;
    }

    public final void forwardLabelUsagePermission(Object currentUserNode, Object newUserNode) {
        currentUserNode.getClass();
        if (Intrinsics.areEqual(currentUserNode, this.firLabelUserNode)) {
            this.firLabelUserNode = newUserNode;
        }
    }

    public final Map<T, FirExpression> getArraySetArgument() {
        return this.arraySetArgument;
    }

    public final List<Name> getCalleeNamesForLambda() {
        return this.calleeNamesForLambda;
    }

    public final List<StatusFirTypeParameterSymbolList> getCapturedTypeParameters() {
        return this.capturedTypeParameters;
    }

    public final FqName getClassName() {
        return this.className;
    }

    public final FqName getClassNameBeforeLocalContext() {
        return this.classNameBeforeLocalContext;
    }

    public final boolean getContainerIsExpect() {
        return this.containerIsExpect;
    }

    public final FirBasedSymbol<?> getContainerSymbol() {
        return (FirBasedSymbol) CollectionsKt.last(this._containerSymbolStack);
    }

    public final FirBasedSymbol<?> getContainerSymbolIfAny() {
        return (FirBasedSymbol) CollectionsKt.lastOrNull(this._containerSymbolStack);
    }

    public final FirReplSnippetSymbol getContainingReplSymbol() {
        return this.containingReplSymbol;
    }

    public final FirScriptSymbol getContainingScriptSymbol() {
        return this.containingScriptSymbol;
    }

    public final ClassId getCurrentClassId() {
        return this.inLocalContext ? new ClassId(CallableId.Companion.getPACKAGE_FQ_NAME_FOR_LOCAL(), this.className, true) : new ClassId(getPackageFqName(), this.className, false);
    }

    public final FirBasedSymbol<?> getCurrentCompanionBlockOwnerOrNull() {
        return this.currentCompanionBlockOwnerOrNull;
    }

    public final List<ConeClassLikeType> getDispatchReceiverTypesStack() {
        return this.dispatchReceiverTypesStack;
    }

    public final List<FirFunctionTarget> getFirFunctionTargets() {
        return this.firFunctionTargets;
    }

    public final Object getFirLabelUserNode() {
        return this.firLabelUserNode;
    }

    public final List<FirLabel> getFirLabels() {
        return this._firLabels;
    }

    public final List<FirLoopTarget> getFirLoopTargets() {
        return this.firLoopTargets;
    }

    public final boolean getForceKeepingTheBodyInHeaderMode() {
        return this.forceKeepingTheBodyInHeaderMode;
    }

    public final FirBasedSymbol<?> getForcedContainerSymbol() {
        return this.forcedContainerSymbol;
    }

    public final boolean getInLocalContext() {
        return this.inLocalContext;
    }

    public final FirLabel getLastLabel(Object currentNode) {
        currentNode.getClass();
        if (Intrinsics.areEqual(this.firLabelUserNode, currentNode)) {
            return (FirLabel) CollectionsKt.last(getFirLabels());
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FqName getPackageFqName() throws UninitializedPropertyAccessException {
        FqName fqName = this.packageFqName;
        if (fqName != null) {
            return fqName;
        }
        Intrinsics.throwUninitializedPropertyAccessException("packageFqName");
        return null;
    }

    public final List<FirLabel> get_firLabels() {
        return this._firLabels;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:9:0x001b  */
    public final void popContainerSymbol(FirBasedSymbol<?> symbol) throws KotlinIllegalStateExceptionWithAttachments {
        symbol.getClass();
        FirBasedSymbol<?> firBasedSymbol = (FirBasedSymbol) CollectionsKt.removeLast(this._containerSymbolStack);
        FirBasedSymbol<?> firBasedSymbol2 = this.forcedContainerSymbol;
        if (firBasedSymbol2 == null) {
            firBasedSymbol2 = symbol;
        } else {
            if (!this._containerSymbolStack.isEmpty()) {
                firBasedSymbol2 = null;
            }
            if (firBasedSymbol2 == null) {
                firBasedSymbol2 = symbol;
            }
        }
        if (firBasedSymbol == firBasedSymbol2) {
            return;
        }
        KotlinIllegalStateExceptionWithAttachments kotlinIllegalStateExceptionWithAttachments = new KotlinIllegalStateExceptionWithAttachments("Inconsistent declaration stack");
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirSymbolEntry(exceptionAttachmentBuilder, "expected", firBasedSymbol2);
        FirExceptionUtilsKt.withFirSymbolEntry(exceptionAttachmentBuilder, "actual", firBasedSymbol);
        if (!Intrinsics.areEqual(symbol, firBasedSymbol2)) {
            FirExceptionUtilsKt.withFirSymbolEntry(exceptionAttachmentBuilder, "replaced symbol", symbol);
        }
        exceptionAttachmentBuilder.withEntry("stack", CollectionsKt.asReversedMutable(this._containerSymbolStack).toString());
        kotlinIllegalStateExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalStateExceptionWithAttachments;
    }

    public final void popFirTypeParameters() {
        List<StatusFirTypeParameterSymbolList> list = this.capturedTypeParameters;
        list.remove(CollectionsKt.getLastIndex(list));
    }

    public final void pushContainerSymbol(FirBasedSymbol<?> symbol) {
        symbol.getClass();
        FirBasedSymbol<?> firBasedSymbol = this.forcedContainerSymbol;
        if (firBasedSymbol != null) {
            if (!this._containerSymbolStack.isEmpty()) {
                firBasedSymbol = null;
            }
            if (firBasedSymbol != null) {
                symbol = firBasedSymbol;
            }
        }
        this._containerSymbolStack.add(symbol);
    }

    public final void pushFirTypeParameters(boolean isInnerOrLocal, List<? extends FirTypeParameterRef> parameters) {
        parameters.getClass();
        List<StatusFirTypeParameterSymbolList> list = this.capturedTypeParameters;
        List<? extends FirTypeParameterRef> list2 = parameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTypeParameterRef) it.next()).getSymbol());
        }
        list.add(new StatusFirTypeParameterSymbolList(isInnerOrLocal, arrayList));
    }

    public final void setClassName(FqName fqName) {
        fqName.getClass();
        this.className = fqName;
    }

    public final void setClassNameBeforeLocalContext(FqName fqName) {
        fqName.getClass();
        this.classNameBeforeLocalContext = fqName;
    }

    public final void setContainerIsExpect(boolean z) {
        this.containerIsExpect = z;
    }

    public final void setContainingReplSymbol(FirReplSnippetSymbol firReplSnippetSymbol) {
        this.containingReplSymbol = firReplSnippetSymbol;
    }

    public final void setContainingScriptSymbol(FirScriptSymbol firScriptSymbol) {
        this.containingScriptSymbol = firScriptSymbol;
    }

    public final void setCurrentCompanionBlockOwnerOrNull(FirBasedSymbol<?> firBasedSymbol) {
        this.currentCompanionBlockOwnerOrNull = firBasedSymbol;
    }

    public final void setFirLabelUserNode(Object obj) {
        this.firLabelUserNode = obj;
    }

    public final void setForceKeepingTheBodyInHeaderMode(boolean z) {
        this.forceKeepingTheBodyInHeaderMode = z;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @PrivateForInline
    public final void setForcedContainerSymbol(FirBasedSymbol<?> firBasedSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (this.forcedContainerSymbol == null) {
            this.forcedContainerSymbol = firBasedSymbol;
            return;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("The value cannot be reassigned");
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        if (firBasedSymbol != null) {
            FirExceptionUtilsKt.withFirSymbolEntry(exceptionAttachmentBuilder, "newValue", firBasedSymbol);
        }
        FirBasedSymbol<?> firBasedSymbol2 = this.forcedContainerSymbol;
        if (firBasedSymbol2 != null) {
            FirExceptionUtilsKt.withFirSymbolEntry(exceptionAttachmentBuilder, "oldValue", firBasedSymbol2);
        }
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public final void setInLocalContext(boolean z) {
        this.inLocalContext = z;
    }

    public final void setNewLabelUserNode(Object useNode) {
        this.firLabelUserNode = useNode;
    }

    public final void setPackageFqName(FqName fqName) {
        fqName.getClass();
        this.packageFqName = fqName;
    }

    public final <T> T withNewLabel(FirLabel label, Object userNode, Function0<? extends T> block) {
        label.getClass();
        block.getClass();
        addNewLabel(label);
        setNewLabelUserNode(userNode);
        try {
            return (T) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            dropLastLabel();
            InlineMarker.finallyEnd(1);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/Context$StatusFirTypeParameterSymbolList;", Argument.Delimiters.none, "isInnerOrLocal", Argument.Delimiters.none, "list", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "<init>", "(ZLjava/util/List;)V", "()Z", "getList", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class StatusFirTypeParameterSymbolList {
        private final boolean isInnerOrLocal;
        private final List<FirTypeParameterSymbol> list;

        public /* synthetic */ StatusFirTypeParameterSymbolList(boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StatusFirTypeParameterSymbolList copy$default(StatusFirTypeParameterSymbolList statusFirTypeParameterSymbolList, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = statusFirTypeParameterSymbolList.isInnerOrLocal;
            }
            if ((i & 2) != 0) {
                list = statusFirTypeParameterSymbolList.list;
            }
            return statusFirTypeParameterSymbolList.copy(z, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsInnerOrLocal() {
            return this.isInnerOrLocal;
        }

        public final List<FirTypeParameterSymbol> component2() {
            return this.list;
        }

        public final StatusFirTypeParameterSymbolList copy(boolean isInnerOrLocal, List<FirTypeParameterSymbol> list) {
            list.getClass();
            return new StatusFirTypeParameterSymbolList(isInnerOrLocal, list);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StatusFirTypeParameterSymbolList)) {
                return false;
            }
            StatusFirTypeParameterSymbolList statusFirTypeParameterSymbolList = (StatusFirTypeParameterSymbolList) other;
            return this.isInnerOrLocal == statusFirTypeParameterSymbolList.isInnerOrLocal && Intrinsics.areEqual(this.list, statusFirTypeParameterSymbolList.list);
        }

        public final List<FirTypeParameterSymbol> getList() {
            return this.list;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.isInnerOrLocal) * 31) + this.list.hashCode();
        }

        public final boolean isInnerOrLocal() {
            return this.isInnerOrLocal;
        }

        public String toString() {
            return "StatusFirTypeParameterSymbolList(isInnerOrLocal=" + this.isInnerOrLocal + ", list=" + this.list + ')';
        }

        public StatusFirTypeParameterSymbolList(boolean z, List<FirTypeParameterSymbol> list) {
            list.getClass();
            this.isInnerOrLocal = z;
            this.list = list;
        }
    }
}
