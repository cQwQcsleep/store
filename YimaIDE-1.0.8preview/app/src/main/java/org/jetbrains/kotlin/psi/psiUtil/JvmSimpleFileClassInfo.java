package org.jetbrains.kotlin.psi.psiUtil;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\tR\u0014\u0010\u000e\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/psi/psiUtil/JvmSimpleFileClassInfo;", "Lorg/jetbrains/kotlin/psi/psiUtil/JvmFileClassInfo;", "fileClassFqName", "Lorg/jetbrains/kotlin/name/FqName;", "withJvmName", "", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Z)V", "getFileClassFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getWithJvmName", "()Z", "facadeClassFqName", "getFacadeClassFqName", "withJvmMultifileClass", "getWithJvmMultifileClass", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JvmSimpleFileClassInfo implements JvmFileClassInfo {
    private final FqName fileClassFqName;
    private final boolean withJvmName;

    public JvmSimpleFileClassInfo(FqName fqName, boolean z) {
        fqName.getClass();
        this.fileClassFqName = fqName;
        this.withJvmName = z;
    }

    @Override // org.jetbrains.kotlin.psi.psiUtil.JvmFileClassInfo
    public FqName getFacadeClassFqName() {
        return getFileClassFqName();
    }

    @Override // org.jetbrains.kotlin.psi.psiUtil.JvmFileClassInfo
    public FqName getFileClassFqName() {
        return this.fileClassFqName;
    }

    @Override // org.jetbrains.kotlin.psi.psiUtil.JvmFileClassInfo
    public boolean getWithJvmMultifileClass() {
        return false;
    }

    @Override // org.jetbrains.kotlin.psi.psiUtil.JvmFileClassInfo
    public boolean getWithJvmName() {
        return this.withJvmName;
    }
}
