package org.jetbrains.kotlin.backend.jvm;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.SourceFile;
import org.jetbrains.kotlin.fileClasses.JvmFileClassInfo;
import org.jetbrains.kotlin.fileClasses.JvmFileClassUtil;
import org.jetbrains.kotlin.load.kotlin.FacadeClassSource;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.kotlin.resolve.source.PsiSourceFile;
import org.jetbrains.kotlin.serialization.deserialization.IncompatibleVersionErrorData;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerAbiStability;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.PreReleaseInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001eR\u0016\u0010!\u001a\u0004\u0018\u00010\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001e¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/FacadeClassSourceShimForFragmentCompilation;", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "Lorg/jetbrains/kotlin/load/kotlin/FacadeClassSource;", "containingFile", "Lorg/jetbrains/kotlin/resolve/source/PsiSourceFile;", "<init>", "(Lorg/jetbrains/kotlin/resolve/source/PsiSourceFile;)V", "fileClassInfo", "Lorg/jetbrains/kotlin/fileClasses/JvmFileClassInfo;", "incompatibility", "Lorg/jetbrains/kotlin/serialization/deserialization/IncompatibleVersionErrorData;", "getIncompatibility", "()Lorg/jetbrains/kotlin/serialization/deserialization/IncompatibleVersionErrorData;", "preReleaseInfo", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/PreReleaseInfo;", "getPreReleaseInfo", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/PreReleaseInfo;", "abiStability", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerAbiStability;", "getAbiStability", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerAbiStability;", "presentableString", Argument.Delimiters.none, "getPresentableString", "()Ljava/lang/String;", "getContainingFile", "Lorg/jetbrains/kotlin/descriptors/SourceFile;", "className", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "getClassName", "()Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "jvmClassName", "getJvmClassName", "facadeClassName", "getFacadeClassName", "org.jetbrains.kotlin:backend.jvm.entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FacadeClassSourceShimForFragmentCompilation implements FacadeClassSource, DeserializedContainerSource {
    private final PsiSourceFile containingFile;
    private final JvmFileClassInfo fileClassInfo;

    public FacadeClassSourceShimForFragmentCompilation(PsiSourceFile psiSourceFile) {
        psiSourceFile.getClass();
        this.containingFile = psiSourceFile;
        KtFile psiFile = psiSourceFile.getPsiFile();
        psiFile.getClass();
        this.fileClassInfo = JvmFileClassUtil.getFileClassInfoNoResolve(psiFile);
    }

    @Override // org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
    public DeserializedContainerAbiStability getAbiStability() {
        return DeserializedContainerAbiStability.STABLE;
    }

    public JvmClassName getClassName() {
        JvmClassName jvmClassNameByFqNameWithoutInnerClasses = JvmClassName.byFqNameWithoutInnerClasses(this.fileClassInfo.getFileClassFqName());
        jvmClassNameByFqNameWithoutInnerClasses.getClass();
        return jvmClassNameByFqNameWithoutInnerClasses;
    }

    @Override // org.jetbrains.kotlin.descriptors.SourceElement
    public SourceFile getContainingFile() {
        return this.containingFile;
    }

    public JvmClassName getFacadeClassName() {
        if (this.fileClassInfo.getWithJvmMultifileClass()) {
            return JvmClassName.byFqNameWithoutInnerClasses(this.fileClassInfo.getFacadeClassFqName());
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
    /* JADX INFO: renamed from: getIncompatibility */
    public IncompatibleVersionErrorData<?> mo247getIncompatibility() {
        return null;
    }

    public JvmClassName getJvmClassName() {
        return null;
    }

    @Override // org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
    public PreReleaseInfo getPreReleaseInfo() {
        return PreReleaseInfo.Companion.getDEFAULT_VISIBLE();
    }

    @Override // org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
    public String getPresentableString() {
        return "Fragment for " + this.containingFile;
    }
}
