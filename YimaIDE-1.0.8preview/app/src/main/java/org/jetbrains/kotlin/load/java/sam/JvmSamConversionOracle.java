package org.jetbrains.kotlin.load.java.sam;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.resolve.sam.SamConversionOracle;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/load/java/sam/JvmSamConversionOracle;", "Lorg/jetbrains/kotlin/resolve/sam/SamConversionOracle;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)V", "shouldRunSamConversionForFunction", "", "candidate", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "isPossibleSamType", "samType", "Lorg/jetbrains/kotlin/types/KotlinType;", "isJavaApplicableCandidate", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JvmSamConversionOracle implements SamConversionOracle {
    private final LanguageVersionSettings languageVersionSettings;

    public JvmSamConversionOracle(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        this.languageVersionSettings = languageVersionSettings;
    }

    public boolean isJavaApplicableCandidate(CallableDescriptor candidate) {
        candidate.getClass();
        return JavaBasedSamConversionOracle.INSTANCE.shouldRunSamConversionForFunction(candidate);
    }

    public boolean isPossibleSamType(KotlinType samType) {
        samType.getClass();
        return JavaBasedSamConversionOracle.INSTANCE.isPossibleSamType(samType);
    }

    public boolean shouldRunSamConversionForFunction(CallableDescriptor candidate) {
        candidate.getClass();
        if (this.languageVersionSettings.supportsFeature(LanguageFeature.SamConversionForKotlinFunctions)) {
            return true;
        }
        return JavaBasedSamConversionOracle.INSTANCE.shouldRunSamConversionForFunction(candidate);
    }
}
