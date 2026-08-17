package org.jetbrains.kotlin.psi.psiUtil;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/psi/psiUtil/JvmMultifileClassPartInfo;", "Lorg/jetbrains/kotlin/psi/psiUtil/JvmFileClassInfo;", "fileClassFqName", "Lorg/jetbrains/kotlin/name/FqName;", "facadeClassFqName", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/name/FqName;)V", "getFileClassFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getFacadeClassFqName", "withJvmName", "", "getWithJvmName", "()Z", "withJvmMultifileClass", "getWithJvmMultifileClass", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JvmMultifileClassPartInfo implements JvmFileClassInfo {
    private final FqName facadeClassFqName;
    private final FqName fileClassFqName;

    public JvmMultifileClassPartInfo(FqName fqName, FqName fqName2) {
        fqName.getClass();
        fqName2.getClass();
        this.fileClassFqName = fqName;
        this.facadeClassFqName = fqName2;
    }

    @Override // org.jetbrains.kotlin.psi.psiUtil.JvmFileClassInfo
    public FqName getFacadeClassFqName() {
        return this.facadeClassFqName;
    }

    @Override // org.jetbrains.kotlin.psi.psiUtil.JvmFileClassInfo
    public FqName getFileClassFqName() {
        return this.fileClassFqName;
    }

    @Override // org.jetbrains.kotlin.psi.psiUtil.JvmFileClassInfo
    public boolean getWithJvmMultifileClass() {
        return true;
    }

    @Override // org.jetbrains.kotlin.psi.psiUtil.JvmFileClassInfo
    public boolean getWithJvmName() {
        return true;
    }
}
