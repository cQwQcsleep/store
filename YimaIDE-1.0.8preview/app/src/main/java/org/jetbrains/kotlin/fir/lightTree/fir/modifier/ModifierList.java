package org.jetbrains.kotlin.fir.lightTree.fir.modifier;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.IElementType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0016J\u0006\u0010\u001a\u001a\u00020\u0016J\u0006\u0010\u001b\u001a\u00020\u0016J\u0006\u0010\u001c\u001a\u00020\u0016J\u0006\u0010\u001d\u001a\u00020\u0016J\u0006\u0010\u001e\u001a\u00020\u0016J\u0006\u0010\u001f\u001a\u00020\u0016J\u0006\u0010 \u001a\u00020\u0016J\u0010\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u0016J\u0006\u0010$\u001a\u00020\u0016J\u0006\u0010%\u001a\u00020\u0016J\u0006\u0010&\u001a\u00020\u0016J\u0006\u0010'\u001a\u00020\u0016J\u0006\u0010(\u001a\u00020\u0016J\u0006\u0010)\u001a\u00020\u0016J\u0006\u0010*\u001a\u00020\u0016J\u0006\u0010+\u001a\u00020\u0016J\u000e\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020.J\u0010\u0010/\u001a\u0004\u0018\u00010.2\u0006\u00100\u001a\u00020\u0016J\u0006\u00101\u001a\u000202J\u0006\u00103\u001a\u00020\u0016J\u0006\u00104\u001a\u00020\u0016J\u0006\u00105\u001a\u00020\u0016J\u0006\u00106\u001a\u00020\u0016J\u0006\u00107\u001a\u00020\u0016J\u0006\u00108\u001a\u00020\u0016J\u0010\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020;H\u0004J\u0012\u0010<\u001a\u00020\u00132\b\u0010:\u001a\u0004\u0018\u00010;H\u0004J\n\u0010=\u001a\u00020>H\u0096\u0080\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\r\"\u0004\b\u0010\u0010\u0011¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/ModifierList;", Argument.Delimiters.none, "modifiers", Argument.Delimiters.none, "<init>", "(J)V", "getModifiers", "()J", "setModifiers", "annotations", Argument.Delimiters.none, "Lcom/intellij/lang/LighterASTNode;", "getAnnotations", "()Ljava/util/List;", "contextLists", "getContextLists", "setContextLists", "(Ljava/util/List;)V", "addModifier", Argument.Delimiters.none, "modifier", "isInClass", Argument.Delimiters.none, "isEnum", "isAnnotation", "isDataClass", "isInlineClass", "isValueClass", "isInner", "isCompanion", "isFunctionalInterface", "hasOverride", "hasLateinit", "getVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "publicByDefault", "hasTailrec", "hasOperator", "hasInfix", "hasInline", "hasExternal", "hasSuspend", "hasCompanion", "isConst", "hasModality", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getModality", "isClassOrObject", "getVariance", "Lorg/jetbrains/kotlin/types/Variance;", "hasVararg", "hasNoinline", "hasCrossinline", "hasExpect", "hasActual", "hasConst", "hasFlag", "flag", "Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/ModifierFlag;", "setFlag", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ModifierList {
    private final List<LighterASTNode> annotations;
    private List<LighterASTNode> contextLists;
    private long modifiers;

    public ModifierList(long j) {
        this.modifiers = j;
        this.annotations = new ArrayList();
        this.contextLists = new ArrayList();
    }

    public static /* synthetic */ void addModifier$default(ModifierList modifierList, LighterASTNode lighterASTNode, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: addModifier");
            return;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        modifierList.addModifier(lighterASTNode, z);
    }

    public static /* synthetic */ Visibility getVisibility$default(ModifierList modifierList, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getVisibility");
            return null;
        }
        if ((i & 1) != 0) {
            z = false;
        }
        return modifierList.getVisibility(z);
    }

    public final void addModifier(LighterASTNode modifier, boolean isInClass) {
        modifier.getClass();
        IElementType tokenType = modifier.getTokenType();
        if (Intrinsics.areEqual(tokenType, KtTokens.CONST_KEYWORD)) {
            setFlag(ModifierFlag.PROPERTY_CONST);
            setFlag(ModifierFlag.PARAMETER_CONST);
        } else if (Intrinsics.areEqual(tokenType, KtTokens.INLINE_KEYWORD)) {
            setFlag(isInClass ? ModifierFlag.CLASS_INLINE : ModifierFlag.FUNCTION_INLINE);
        } else if (Intrinsics.areEqual(tokenType, KtTokens.VALUE_KEYWORD)) {
            setFlag(ModifierFlag.CLASS_VALUE);
        } else {
            setFlag(ModifierFlag.INSTANCE.getElementTypeToModifierFlagMap().get(tokenType));
        }
    }

    public final List<LighterASTNode> getAnnotations() {
        return this.annotations;
    }

    public final List<LighterASTNode> getContextLists() {
        return this.contextLists;
    }

    public final Modality getModality(boolean isClassOrObject) {
        if (hasFlag(ModifierFlag.INHERITANCE_FINAL)) {
            return Modality.FINAL;
        }
        if (hasFlag(ModifierFlag.INHERITANCE_SEALED)) {
            if (isClassOrObject) {
                return Modality.SEALED;
            }
            return null;
        }
        if (hasFlag(ModifierFlag.INHERITANCE_ABSTRACT)) {
            return Modality.ABSTRACT;
        }
        if (hasFlag(ModifierFlag.INHERITANCE_OPEN)) {
            return Modality.OPEN;
        }
        return null;
    }

    public final long getModifiers() {
        return this.modifiers;
    }

    public final Variance getVariance() {
        if (hasFlag(ModifierFlag.VARIANCE_IN)) {
            return Variance.IN_VARIANCE;
        }
        return hasFlag(ModifierFlag.VARIANCE_OUT) ? Variance.OUT_VARIANCE : Variance.INVARIANT;
    }

    public final Visibility getVisibility(boolean publicByDefault) {
        if (hasFlag(ModifierFlag.VISIBILITY_PRIVATE)) {
            return Visibilities.Private.INSTANCE;
        }
        if (hasFlag(ModifierFlag.VISIBILITY_PUBLIC)) {
            return Visibilities.Public.INSTANCE;
        }
        if (hasFlag(ModifierFlag.VISIBILITY_PROTECTED)) {
            return Visibilities.Protected.INSTANCE;
        }
        if (hasFlag(ModifierFlag.VISIBILITY_INTERNAL)) {
            return Visibilities.Internal.INSTANCE;
        }
        return publicByDefault ? Visibilities.Public.INSTANCE : Visibilities.Unknown.INSTANCE;
    }

    public final boolean hasActual() {
        return hasFlag(ModifierFlag.PLATFORM_ACTUAL);
    }

    public final boolean hasCompanion() {
        return hasFlag(ModifierFlag.CLASS_COMPANION);
    }

    public final boolean hasConst() {
        return hasFlag(ModifierFlag.PARAMETER_CONST);
    }

    public final boolean hasCrossinline() {
        return hasFlag(ModifierFlag.PARAMETER_CROSSINLINE);
    }

    public final boolean hasExpect() {
        return hasFlag(ModifierFlag.PLATFORM_EXPECT);
    }

    public final boolean hasExternal() {
        return hasFlag(ModifierFlag.FUNCTION_EXTERNAL);
    }

    public final boolean hasFlag(ModifierFlag flag) {
        flag.getClass();
        return (this.modifiers & flag.getValue()) == flag.getValue();
    }

    public final boolean hasInfix() {
        return hasFlag(ModifierFlag.FUNCTION_INFIX);
    }

    public final boolean hasInline() {
        return hasFlag(ModifierFlag.FUNCTION_INLINE);
    }

    public final boolean hasLateinit() {
        return hasFlag(ModifierFlag.MEMBER_LATEINIT);
    }

    public final boolean hasModality(Modality modality) {
        modality.getClass();
        if (modality == Modality.FINAL && hasFlag(ModifierFlag.INHERITANCE_FINAL)) {
            return true;
        }
        if (modality == Modality.SEALED && hasFlag(ModifierFlag.INHERITANCE_SEALED)) {
            return true;
        }
        if (modality == Modality.ABSTRACT && hasFlag(ModifierFlag.INHERITANCE_ABSTRACT)) {
            return true;
        }
        return modality == Modality.OPEN && hasFlag(ModifierFlag.INHERITANCE_OPEN);
    }

    public final boolean hasNoinline() {
        return hasFlag(ModifierFlag.PARAMETER_NOINLINE);
    }

    public final boolean hasOperator() {
        return hasFlag(ModifierFlag.FUNCTION_OPERATOR);
    }

    public final boolean hasOverride() {
        return hasFlag(ModifierFlag.MEMBER_OVERRIDE);
    }

    public final boolean hasSuspend() {
        return hasFlag(ModifierFlag.FUNCTION_SUSPEND);
    }

    public final boolean hasTailrec() {
        return hasFlag(ModifierFlag.FUNCTION_TAILREC);
    }

    public final boolean hasVararg() {
        return hasFlag(ModifierFlag.PARAMETER_VARARG);
    }

    public final boolean isAnnotation() {
        return hasFlag(ModifierFlag.CLASS_ANNOTATION);
    }

    public final boolean isCompanion() {
        return hasFlag(ModifierFlag.CLASS_COMPANION);
    }

    public final boolean isConst() {
        return hasFlag(ModifierFlag.PROPERTY_CONST);
    }

    public final boolean isDataClass() {
        return hasFlag(ModifierFlag.CLASS_DATA);
    }

    public final boolean isEnum() {
        return hasFlag(ModifierFlag.CLASS_ENUM);
    }

    public final boolean isFunctionalInterface() {
        return hasFlag(ModifierFlag.CLASS_FUN);
    }

    public final boolean isInlineClass() {
        return hasFlag(ModifierFlag.CLASS_INLINE);
    }

    public final boolean isInner() {
        return hasFlag(ModifierFlag.CLASS_INNER);
    }

    public final boolean isValueClass() {
        return hasFlag(ModifierFlag.CLASS_VALUE);
    }

    public final void setContextLists(List<LighterASTNode> list) {
        list.getClass();
        this.contextLists = list;
    }

    public final void setFlag(ModifierFlag flag) {
        if (flag != null) {
            this.modifiers |= flag.getValue();
        }
    }

    public final void setModifiers(long j) {
        this.modifiers = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (ModifierFlag modifierFlag : ModifierFlag.getEntries()) {
            if (hasFlag(modifierFlag) && modifierFlag != ModifierFlag.NONE) {
                if (z) {
                    z = false;
                } else {
                    sb.append(Argument.Delimiters.space);
                }
                sb.append(modifierFlag.name());
            }
        }
        return sb.toString();
    }

    public ModifierList() {
        this(0L, 1, null);
    }

    public /* synthetic */ ModifierList(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ModifierFlag.NONE.getValue() : j);
    }
}
