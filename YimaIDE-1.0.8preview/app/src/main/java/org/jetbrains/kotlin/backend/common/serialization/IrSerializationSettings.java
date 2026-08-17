package org.jetbrains.kotlin.backend.common.serialization;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.KlibAbiCompatibilityLevel;
import org.jetbrains.kotlin.config.KlibConfigurationKeys;
import org.jetbrains.kotlin.config.KlibConfigurationKeysKt;
import org.jetbrains.kotlin.config.LanguageVersionSettings;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fB]\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u0012J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\rHÆ\u0003JU\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0014\u0010&\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004J\n\u0010*\u001a\u00020\tHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/IrSerializationSettings;", "", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "publicAbiOnly", "", "bodiesOnlyForInlines", "sourceBaseDirs", "", "", "normalizeAbsolutePaths", "shouldCheckSignaturesOnUniqueness", "abiCompatibilityLevel", "Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;ZZLjava/util/Collection;ZZLorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;)V", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;ZZLjava/util/Collection;ZZLorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;)V", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getPublicAbiOnly", "()Z", "getBodiesOnlyForInlines", "getSourceBaseDirs", "()Ljava/util/Collection;", "getNormalizeAbsolutePaths", "getShouldCheckSignaturesOnUniqueness", "getAbiCompatibilityLevel", "()Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class IrSerializationSettings {
    private final KlibAbiCompatibilityLevel abiCompatibilityLevel;
    private final boolean bodiesOnlyForInlines;
    private final LanguageVersionSettings languageVersionSettings;
    private final boolean normalizeAbsolutePaths;
    private final boolean publicAbiOnly;
    private final boolean shouldCheckSignaturesOnUniqueness;
    private final Collection<String> sourceBaseDirs;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ IrSerializationSettings(CompilerConfiguration compilerConfiguration, LanguageVersionSettings languageVersionSettings, boolean z, boolean z2, Collection collection, boolean z3, boolean z4, KlibAbiCompatibilityLevel klibAbiCompatibilityLevel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        LanguageVersionSettings languageVersionSettings2 = (i & 2) != 0 ? CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration) : languageVersionSettings;
        boolean z5 = (i & 4) != 0 ? false : z;
        this(compilerConfiguration, languageVersionSettings2, z5, (i & 8) != 0 ? z5 : z2, (i & 16) != 0 ? KlibConfigurationKeysKt.getKlibRelativePathBases(compilerConfiguration) : collection, (i & 32) != 0 ? KlibConfigurationKeysKt.getKlibNormalizeAbsolutePath(compilerConfiguration) : z3, (i & 64) != 0 ? ((Boolean) compilerConfiguration.get(KlibConfigurationKeys.PRODUCE_KLIB_SIGNATURES_CLASH_CHECKS, Boolean.TRUE)).booleanValue() : z4, (i & 128) != 0 ? KlibConfigurationKeysKt.getKlibAbiCompatibilityLevel(compilerConfiguration) : klibAbiCompatibilityLevel);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IrSerializationSettings copy$default(IrSerializationSettings irSerializationSettings, LanguageVersionSettings languageVersionSettings, boolean z, boolean z2, Collection collection, boolean z3, boolean z4, KlibAbiCompatibilityLevel klibAbiCompatibilityLevel, int i, Object obj) {
        if ((i & 1) != 0) {
            languageVersionSettings = irSerializationSettings.languageVersionSettings;
        }
        if ((i & 2) != 0) {
            z = irSerializationSettings.publicAbiOnly;
        }
        if ((i & 4) != 0) {
            z2 = irSerializationSettings.bodiesOnlyForInlines;
        }
        if ((i & 8) != 0) {
            collection = irSerializationSettings.sourceBaseDirs;
        }
        if ((i & 16) != 0) {
            z3 = irSerializationSettings.normalizeAbsolutePaths;
        }
        if ((i & 32) != 0) {
            z4 = irSerializationSettings.shouldCheckSignaturesOnUniqueness;
        }
        if ((i & 64) != 0) {
            klibAbiCompatibilityLevel = irSerializationSettings.abiCompatibilityLevel;
        }
        boolean z5 = z4;
        KlibAbiCompatibilityLevel klibAbiCompatibilityLevel2 = klibAbiCompatibilityLevel;
        boolean z6 = z3;
        boolean z7 = z2;
        return irSerializationSettings.copy(languageVersionSettings, z, z7, collection, z6, z5, klibAbiCompatibilityLevel2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getPublicAbiOnly() {
        return this.publicAbiOnly;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getBodiesOnlyForInlines() {
        return this.bodiesOnlyForInlines;
    }

    public final Collection<String> component4() {
        return this.sourceBaseDirs;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getNormalizeAbsolutePaths() {
        return this.normalizeAbsolutePaths;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getShouldCheckSignaturesOnUniqueness() {
        return this.shouldCheckSignaturesOnUniqueness;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final KlibAbiCompatibilityLevel getAbiCompatibilityLevel() {
        return this.abiCompatibilityLevel;
    }

    public final IrSerializationSettings copy(LanguageVersionSettings languageVersionSettings, boolean publicAbiOnly, boolean bodiesOnlyForInlines, Collection<String> sourceBaseDirs, boolean normalizeAbsolutePaths, boolean shouldCheckSignaturesOnUniqueness, KlibAbiCompatibilityLevel abiCompatibilityLevel) {
        languageVersionSettings.getClass();
        sourceBaseDirs.getClass();
        abiCompatibilityLevel.getClass();
        return new IrSerializationSettings(languageVersionSettings, publicAbiOnly, bodiesOnlyForInlines, sourceBaseDirs, normalizeAbsolutePaths, shouldCheckSignaturesOnUniqueness, abiCompatibilityLevel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IrSerializationSettings)) {
            return false;
        }
        IrSerializationSettings irSerializationSettings = (IrSerializationSettings) other;
        return Intrinsics.areEqual(this.languageVersionSettings, irSerializationSettings.languageVersionSettings) && this.publicAbiOnly == irSerializationSettings.publicAbiOnly && this.bodiesOnlyForInlines == irSerializationSettings.bodiesOnlyForInlines && Intrinsics.areEqual(this.sourceBaseDirs, irSerializationSettings.sourceBaseDirs) && this.normalizeAbsolutePaths == irSerializationSettings.normalizeAbsolutePaths && this.shouldCheckSignaturesOnUniqueness == irSerializationSettings.shouldCheckSignaturesOnUniqueness && this.abiCompatibilityLevel == irSerializationSettings.abiCompatibilityLevel;
    }

    public final KlibAbiCompatibilityLevel getAbiCompatibilityLevel() {
        return this.abiCompatibilityLevel;
    }

    public final boolean getBodiesOnlyForInlines() {
        return this.bodiesOnlyForInlines;
    }

    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    public final boolean getNormalizeAbsolutePaths() {
        return this.normalizeAbsolutePaths;
    }

    public final boolean getPublicAbiOnly() {
        return this.publicAbiOnly;
    }

    public final boolean getShouldCheckSignaturesOnUniqueness() {
        return this.shouldCheckSignaturesOnUniqueness;
    }

    public final Collection<String> getSourceBaseDirs() {
        return this.sourceBaseDirs;
    }

    public int hashCode() {
        return (((((((((((this.languageVersionSettings.hashCode() * 31) + Boolean.hashCode(this.publicAbiOnly)) * 31) + Boolean.hashCode(this.bodiesOnlyForInlines)) * 31) + this.sourceBaseDirs.hashCode()) * 31) + Boolean.hashCode(this.normalizeAbsolutePaths)) * 31) + Boolean.hashCode(this.shouldCheckSignaturesOnUniqueness)) * 31) + this.abiCompatibilityLevel.hashCode();
    }

    public String toString() {
        return "IrSerializationSettings(languageVersionSettings=" + this.languageVersionSettings + ", publicAbiOnly=" + this.publicAbiOnly + ", bodiesOnlyForInlines=" + this.bodiesOnlyForInlines + ", sourceBaseDirs=" + this.sourceBaseDirs + ", normalizeAbsolutePaths=" + this.normalizeAbsolutePaths + ", shouldCheckSignaturesOnUniqueness=" + this.shouldCheckSignaturesOnUniqueness + ", abiCompatibilityLevel=" + this.abiCompatibilityLevel + Util.C_PARAM_END;
    }

    public IrSerializationSettings(LanguageVersionSettings languageVersionSettings, boolean z, boolean z2, Collection<String> collection, boolean z3, boolean z4, KlibAbiCompatibilityLevel klibAbiCompatibilityLevel) {
        languageVersionSettings.getClass();
        collection.getClass();
        klibAbiCompatibilityLevel.getClass();
        this.languageVersionSettings = languageVersionSettings;
        this.publicAbiOnly = z;
        this.bodiesOnlyForInlines = z2;
        this.sourceBaseDirs = collection;
        this.normalizeAbsolutePaths = z3;
        this.shouldCheckSignaturesOnUniqueness = z4;
        this.abiCompatibilityLevel = klibAbiCompatibilityLevel;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public IrSerializationSettings(CompilerConfiguration compilerConfiguration, LanguageVersionSettings languageVersionSettings, boolean z, boolean z2, Collection<String> collection, boolean z3, boolean z4, KlibAbiCompatibilityLevel klibAbiCompatibilityLevel) {
        this(languageVersionSettings, z, z2, collection, z3, z4, klibAbiCompatibilityLevel);
        compilerConfiguration.getClass();
        languageVersionSettings.getClass();
        collection.getClass();
        klibAbiCompatibilityLevel.getClass();
    }
}
