package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\n\u0010\u0016\u001a\u00020\u0017H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "notNullType", "directType", "isEnumClass", Argument.Delimiters.none, "isPrimitive", "isBuiltin", "isValueClass", "isFinal", "isClass", "canHaveSubtypesAccordingToK1", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;ZZZZZZZ)V", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getNotNullType", "getDirectType", "()Z", "getCanHaveSubtypesAccordingToK1", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeInfo {
    private final boolean canHaveSubtypesAccordingToK1;
    private final ConeKotlinType directType;
    private final boolean isBuiltin;
    private final boolean isClass;
    private final boolean isEnumClass;
    private final boolean isFinal;
    private final boolean isPrimitive;
    private final boolean isValueClass;
    private final ConeKotlinType notNullType;
    private final ConeKotlinType type;

    public TypeInfo(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, ConeKotlinType coneKotlinType3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        coneKotlinType3.getClass();
        this.type = coneKotlinType;
        this.notNullType = coneKotlinType2;
        this.directType = coneKotlinType3;
        this.isEnumClass = z;
        this.isPrimitive = z2;
        this.isBuiltin = z3;
        this.isValueClass = z4;
        this.isFinal = z5;
        this.isClass = z6;
        this.canHaveSubtypesAccordingToK1 = z7;
    }

    public final boolean getCanHaveSubtypesAccordingToK1() {
        return this.canHaveSubtypesAccordingToK1;
    }

    public final ConeKotlinType getDirectType() {
        return this.directType;
    }

    public final ConeKotlinType getNotNullType() {
        return this.notNullType;
    }

    public final ConeKotlinType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: isBuiltin, reason: from getter */
    public final boolean getIsBuiltin() {
        return this.isBuiltin;
    }

    /* JADX INFO: renamed from: isClass, reason: from getter */
    public final boolean getIsClass() {
        return this.isClass;
    }

    /* JADX INFO: renamed from: isEnumClass, reason: from getter */
    public final boolean getIsEnumClass() {
        return this.isEnumClass;
    }

    /* JADX INFO: renamed from: isFinal, reason: from getter */
    public final boolean getIsFinal() {
        return this.isFinal;
    }

    /* JADX INFO: renamed from: isPrimitive, reason: from getter */
    public final boolean getIsPrimitive() {
        return this.isPrimitive;
    }

    /* JADX INFO: renamed from: isValueClass, reason: from getter */
    public final boolean getIsValueClass() {
        return this.isValueClass;
    }

    public String toString() {
        return String.valueOf(this.type);
    }
}
