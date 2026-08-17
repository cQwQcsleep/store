package kotlin.reflect.jvm.internal.impl.km;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class KmVersionRequirement {
    private Integer errorCode;
    public KmVersionRequirementVersionKind kind;
    public KmVersionRequirementLevel level;
    private String message;
    public KmVersion version;

    public final Integer getErrorCode() {
        return this.errorCode;
    }

    public final KmVersionRequirementVersionKind getKind() {
        KmVersionRequirementVersionKind kmVersionRequirementVersionKind = this.kind;
        if (kmVersionRequirementVersionKind != null) {
            return kmVersionRequirementVersionKind;
        }
        Intrinsics.throwUninitializedPropertyAccessException("kind");
        return null;
    }

    public final KmVersionRequirementLevel getLevel() {
        KmVersionRequirementLevel kmVersionRequirementLevel = this.level;
        if (kmVersionRequirementLevel != null) {
            return kmVersionRequirementLevel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("level");
        return null;
    }

    public final String getMessage() {
        return this.message;
    }

    public final KmVersion getVersion() {
        KmVersion kmVersion = this.version;
        if (kmVersion != null) {
            return kmVersion;
        }
        Intrinsics.throwUninitializedPropertyAccessException("version");
        return null;
    }

    public final void setErrorCode(Integer num) {
        this.errorCode = num;
    }

    public final void setKind(KmVersionRequirementVersionKind kmVersionRequirementVersionKind) {
        kmVersionRequirementVersionKind.getClass();
        this.kind = kmVersionRequirementVersionKind;
    }

    public final void setLevel(KmVersionRequirementLevel kmVersionRequirementLevel) {
        kmVersionRequirementLevel.getClass();
        this.level = kmVersionRequirementLevel;
    }

    public final void setMessage(String str) {
        this.message = str;
    }

    public final void setVersion(KmVersion kmVersion) {
        kmVersion.getClass();
        this.version = kmVersion;
    }

    public String toString() {
        return "KmVersionRequirement(kind=" + getKind() + ", level=" + getLevel() + ", version=" + getVersion() + ", errorCode=" + this.errorCode + ", message=" + this.message + ')';
    }
}
