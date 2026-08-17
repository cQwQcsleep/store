package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rB1\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b'\u0010\u0012R\u0011\u0010(\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b(\u0010\u0012¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "isSkipped", Argument.Delimiters.none, "index", Argument.Delimiters.none, "remapValue", "Lorg/jetbrains/kotlin/codegen/StackValue;", "declarationIndex", "typeOnStack", "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;ZILorg/jetbrains/kotlin/codegen/StackValue;ILorg/jetbrains/org/objectweb/asm/Type;)V", "skipped", "(Lorg/jetbrains/org/objectweb/asm/Type;ZIII)V", "getType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "()Z", "getIndex", "()I", "getRemapValue", "()Lorg/jetbrains/kotlin/codegen/StackValue;", "setRemapValue", "(Lorg/jetbrains/kotlin/codegen/StackValue;)V", "getDeclarationIndex", "getTypeOnStack", "fieldEquivalent", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;", "getFieldEquivalent", "()Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;", "setFieldEquivalent", "(Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;)V", "functionalArgument", "Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "getFunctionalArgument", "()Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "setFunctionalArgument", "(Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;)V", "isSkippedOrRemapped", "isRemapped", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ParameterInfo {
    private final int declarationIndex;
    private CapturedParamInfo fieldEquivalent;
    private FunctionalArgument functionalArgument;
    private final int index;
    private final boolean isSkipped;
    private StackValue remapValue;
    private final Type type;
    private final Type typeOnStack;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ParameterInfo(Type type, boolean z, int i, int i2, int i3) {
        this(type, z, i, i2 == -1 ? null : new StackValue.Local(i2, type, null), i3, null, 32, null);
        type.getClass();
    }

    public final int getDeclarationIndex() {
        return this.declarationIndex;
    }

    public final CapturedParamInfo getFieldEquivalent() {
        return this.fieldEquivalent;
    }

    public final FunctionalArgument getFunctionalArgument() {
        return this.functionalArgument;
    }

    public final int getIndex() {
        return this.index;
    }

    public final StackValue getRemapValue() {
        return this.remapValue;
    }

    public final Type getType() {
        return this.type;
    }

    public final Type getTypeOnStack() {
        return this.typeOnStack;
    }

    public final boolean isRemapped() {
        return this.remapValue != null;
    }

    /* JADX INFO: renamed from: isSkipped, reason: from getter */
    public final boolean getIsSkipped() {
        return this.isSkipped;
    }

    public final boolean isSkippedOrRemapped() {
        return this.isSkipped || isRemapped();
    }

    public final void setFieldEquivalent(CapturedParamInfo capturedParamInfo) {
        this.fieldEquivalent = capturedParamInfo;
    }

    public final void setFunctionalArgument(FunctionalArgument functionalArgument) {
        this.functionalArgument = functionalArgument;
    }

    public final void setRemapValue(StackValue stackValue) {
        this.remapValue = stackValue;
    }

    public /* synthetic */ ParameterInfo(Type type, boolean z, int i, StackValue stackValue, int i2, Type type2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(type, z, i, stackValue, i2, (i3 & 32) != 0 ? type : type2);
    }

    public ParameterInfo(Type type, boolean z, int i, StackValue stackValue, int i2, Type type2) {
        type.getClass();
        type2.getClass();
        this.type = type;
        this.isSkipped = z;
        this.index = i;
        this.remapValue = stackValue;
        this.declarationIndex = i2;
        this.typeOnStack = type2;
    }
}
