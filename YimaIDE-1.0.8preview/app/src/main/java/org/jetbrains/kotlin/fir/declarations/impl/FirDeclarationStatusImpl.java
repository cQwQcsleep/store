package org.jetbrains.kotlin.fir.declarations.impl;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirPureAbstractElement;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002:\u0001gB\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086\u0002J\u0019\u0010 \u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u001dH\u0086\u0002J5\u0010T\u001a\u00020!\"\u0004\b\u0000\u0010U\"\u0004\b\u0001\u0010V2\u0012\u0010W\u001a\u000e\u0012\u0004\u0012\u0002HU\u0012\u0004\u0012\u0002HV0X2\u0006\u0010Y\u001a\u0002HVH\u0016¢\u0006\u0002\u0010ZJ)\u0010[\u001a\u00020\u0000\"\u0004\b\u0000\u0010V2\f\u0010\\\u001a\b\u0012\u0004\u0012\u0002HV0]2\u0006\u0010Y\u001a\u0002HVH\u0016¢\u0006\u0002\u0010^J \u0010_\u001a\u00020`2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010a\u001a\u00020bH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u0004\u0018\u00010\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00128@X\u0081\u0004r\u0002\b\u001b¢\u0006\f\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u0014R$\u0010#\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010'\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b'\u0010$\"\u0004\b(\u0010&R$\u0010)\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&R$\u0010+\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R$\u0010-\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b-\u0010$\"\u0004\b.\u0010&R$\u0010/\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b/\u0010$\"\u0004\b0\u0010&R$\u00101\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b1\u0010$\"\u0004\b2\u0010&R$\u00103\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b3\u0010$\"\u0004\b4\u0010&R$\u00105\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b5\u0010$\"\u0004\b6\u0010&R$\u00107\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R$\u00109\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b9\u0010$\"\u0004\b:\u0010&R$\u0010;\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b;\u0010$\"\u0004\b<\u0010&R$\u0010=\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b=\u0010$\"\u0004\b>\u0010&R$\u0010?\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b?\u0010$\"\u0004\b@\u0010&R$\u0010A\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bA\u0010$\"\u0004\bB\u0010&R$\u0010C\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bC\u0010$\"\u0004\bD\u0010&R$\u0010E\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bE\u0010$\"\u0004\bF\u0010&R$\u0010G\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bG\u0010$\"\u0004\bH\u0010&R$\u0010I\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bI\u0010$\"\u0004\bJ\u0010&R$\u0010K\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bL\u0010$\"\u0004\bM\u0010&R$\u0010O\u001a\u00020N2\u0006\u0010\"\u001a\u00020N8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u0014\u0010c\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010\fR\u0014\u0010e\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bf\u0010\n¨\u0006h"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl;", "Lorg/jetbrains/kotlin/fir/FirPureAbstractElement;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Modality;)V", "getVisibility", "()Lorg/jetbrains/kotlin/descriptors/Visibility;", "getModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "flags", Argument.Delimiters.none, "getFlags", "()I", "setFlags", "(I)V", "rawFlags", "getRawFlags$org_jetbrains_kotlin_tree$annotations", "()V", "getRawFlags$org_jetbrains_kotlin_tree", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "get", Argument.Delimiters.none, "modifier", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl$Modifier;", "set", Argument.Delimiters.none, "value", "isExpect", "()Z", "setExpect", "(Z)V", "isActual", "setActual", "isOverride", "setOverride", "isOperator", "setOperator", "isInfix", "setInfix", "isInline", "setInline", "isValue", "setValue", "isTailRec", "setTailRec", "isExternal", "setExternal", "isConst", "setConst", "isLateInit", "setLateInit", "isInner", "setInner", "isCompanion", "setCompanion", "isData", "setData", "isSuspend", "setSuspend", "isStatic", "setStatic", "isFromSealedClass", "setFromSealedClass", "isFromEnumClass", "setFromEnumClass", "isFun", "setFun", "hasStableParameterNames", "getHasStableParameterNames", "setHasStableParameterNames", "Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "returnValueStatus", "getReturnValueStatus", "()Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "setReturnValueStatus", "(Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;)V", "acceptChildren", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl;", "resolved", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;", "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "defaultModality", "getDefaultModality", "defaultVisibility", "getDefaultVisibility", "Modifier", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirDeclarationStatusImpl extends FirPureAbstractElement implements FirDeclarationStatus {
    private int flags;
    private final Modality modality;
    private final Visibility visibility;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl$Modifier;", Argument.Delimiters.none, "mask", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;II)V", "getMask", "()I", "EXPECT", "ACTUAL", "OVERRIDE", "OPERATOR", "INFIX", "INLINE", "TAILREC", "EXTERNAL", "CONST", "LATEINIT", "INNER", "COMPANION", "DATA", "SUSPEND", "STATIC", "FROM_SEALED", "FROM_ENUM", "FUN", "HAS_STABLE_PARAMETER_NAMES", "VALUE", "HAS_MUST_USE_RETURN_VALUE", "HAS_IGNORABLE_RETURN_VALUE", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Modifier {
        EXPECT(1),
        ACTUAL(2),
        OVERRIDE(4),
        OPERATOR(8),
        INFIX(16),
        INLINE(32),
        TAILREC(64),
        EXTERNAL(128),
        CONST(256),
        LATEINIT(512),
        INNER(1024),
        COMPANION(2048),
        DATA(4096),
        SUSPEND(8192),
        STATIC(16384),
        FROM_SEALED(32768),
        FROM_ENUM(65536),
        FUN(131072),
        HAS_STABLE_PARAMETER_NAMES(262144),
        VALUE(524288),
        HAS_MUST_USE_RETURN_VALUE(1048576),
        HAS_IGNORABLE_RETURN_VALUE(2097152);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final int mask;

        Modifier(int i) {
            this.mask = i;
        }

        public static EnumEntries<Modifier> getEntries() {
            return $ENTRIES;
        }

        public final int getMask() {
            return this.mask;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReturnValueStatus.values().length];
            try {
                iArr[ReturnValueStatus.MustUse.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReturnValueStatus.ExplicitlyIgnorable.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FirDeclarationStatusImpl(Visibility visibility, Modality modality) {
        visibility.getClass();
        this.visibility = visibility;
        this.modality = modality;
        this.flags = Modifier.HAS_STABLE_PARAMETER_NAMES.getMask();
    }

    @FirImplementationDetail
    public static /* synthetic */ void getRawFlags$org_jetbrains_kotlin_tree$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    public final boolean get(Modifier modifier) {
        modifier.getClass();
        return (this.flags & modifier.getMask()) != 0;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public Modality getDefaultModality() {
        return Modality.FINAL;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public Visibility getDefaultVisibility() {
        return Visibilities.INSTANCE.getDEFAULT_VISIBILITY();
    }

    public final int getFlags() {
        return this.flags;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean getHasStableParameterNames() {
        return get(Modifier.HAS_STABLE_PARAMETER_NAMES);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public Modality getModality() {
        return this.modality;
    }

    public final int getRawFlags$org_jetbrains_kotlin_tree() {
        return this.flags;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public ReturnValueStatus getReturnValueStatus() {
        return ReturnValueStatus.Companion.fromBitFlags(get(Modifier.HAS_MUST_USE_RETURN_VALUE), get(Modifier.HAS_IGNORABLE_RETURN_VALUE));
    }

    @Override // org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isActual() {
        return get(Modifier.ACTUAL);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isCompanion() {
        return get(Modifier.COMPANION);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isConst() {
        return get(Modifier.CONST);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isData() {
        return get(Modifier.DATA);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isExpect() {
        return get(Modifier.EXPECT);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isExternal() {
        return get(Modifier.EXTERNAL);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isFromEnumClass() {
        return get(Modifier.FROM_ENUM);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isFromSealedClass() {
        return get(Modifier.FROM_SEALED);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isFun() {
        return get(Modifier.FUN);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isInfix() {
        return get(Modifier.INFIX);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isInline() {
        return get(Modifier.INLINE);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isInner() {
        return get(Modifier.INNER);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isLateInit() {
        return get(Modifier.LATEINIT);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isOperator() {
        return get(Modifier.OPERATOR);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isOverride() {
        return get(Modifier.OVERRIDE);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isStatic() {
        return get(Modifier.STATIC);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isSuspend() {
        return get(Modifier.SUSPEND);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isTailRec() {
        return get(Modifier.TAILREC);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public boolean isValue() {
        return get(Modifier.VALUE);
    }

    public FirResolvedDeclarationStatusImpl resolved(Visibility visibility, Modality modality, EffectiveVisibility effectiveVisibility) {
        visibility.getClass();
        modality.getClass();
        effectiveVisibility.getClass();
        return new FirResolvedDeclarationStatusImpl(visibility, modality, effectiveVisibility, this.flags);
    }

    public final void set(Modifier modifier, boolean value) {
        modifier.getClass();
        int i = this.flags;
        this.flags = value ? modifier.getMask() | i : (~modifier.getMask()) & i;
    }

    public void setActual(boolean z) {
        set(Modifier.ACTUAL, z);
    }

    public void setCompanion(boolean z) {
        set(Modifier.COMPANION, z);
    }

    public void setConst(boolean z) {
        set(Modifier.CONST, z);
    }

    public void setData(boolean z) {
        set(Modifier.DATA, z);
    }

    public void setExpect(boolean z) {
        set(Modifier.EXPECT, z);
    }

    public void setExternal(boolean z) {
        set(Modifier.EXTERNAL, z);
    }

    public final void setFlags(int i) {
        this.flags = i;
    }

    public void setFromEnumClass(boolean z) {
        set(Modifier.FROM_ENUM, z);
    }

    public void setFromSealedClass(boolean z) {
        set(Modifier.FROM_SEALED, z);
    }

    public void setFun(boolean z) {
        set(Modifier.FUN, z);
    }

    public void setHasStableParameterNames(boolean z) {
        set(Modifier.HAS_STABLE_PARAMETER_NAMES, z);
    }

    public void setInfix(boolean z) {
        set(Modifier.INFIX, z);
    }

    public void setInline(boolean z) {
        set(Modifier.INLINE, z);
    }

    public void setInner(boolean z) {
        set(Modifier.INNER, z);
    }

    public void setLateInit(boolean z) {
        set(Modifier.LATEINIT, z);
    }

    public void setOperator(boolean z) {
        set(Modifier.OPERATOR, z);
    }

    public void setOverride(boolean z) {
        set(Modifier.OVERRIDE, z);
    }

    public void setReturnValueStatus(ReturnValueStatus returnValueStatus) {
        returnValueStatus.getClass();
        int i = this.flags;
        Modifier modifier = Modifier.HAS_MUST_USE_RETURN_VALUE;
        int mask = modifier.getMask();
        Modifier modifier2 = Modifier.HAS_IGNORABLE_RETURN_VALUE;
        this.flags = i & (~(mask | modifier2.getMask()));
        int i2 = WhenMappings.$EnumSwitchMapping$0[returnValueStatus.ordinal()];
        if (i2 == 1) {
            set(modifier, true);
        } else {
            if (i2 != 2) {
                return;
            }
            set(modifier2, true);
        }
    }

    public void setStatic(boolean z) {
        set(Modifier.STATIC, z);
    }

    public void setSuspend(boolean z) {
        set(Modifier.SUSPEND, z);
    }

    public void setTailRec(boolean z) {
        set(Modifier.TAILREC, z);
    }

    public void setValue(boolean z) {
        set(Modifier.VALUE, z);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirDeclarationStatusImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }
}
