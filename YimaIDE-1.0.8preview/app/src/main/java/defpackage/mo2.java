package defpackage;

import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class mo2 implements INameEnvironment {
    public final INameEnvironment b;
    public final INameEnvironment c;

    public mo2(INameEnvironment iNameEnvironment, INameEnvironment iNameEnvironment2) {
        iNameEnvironment.getClass();
        iNameEnvironment2.getClass();
        this.b = iNameEnvironment;
        this.c = iNameEnvironment2;
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public void cleanup() {
        try {
            this.b.cleanup();
        } catch (Throwable unused) {
        }
        try {
            this.c.cleanup();
        } catch (Throwable unused2) {
        }
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public NameEnvironmentAnswer findType(char[] cArr, char[][] cArr2) {
        cArr.getClass();
        cArr2.getClass();
        NameEnvironmentAnswer nameEnvironmentAnswerFindType = this.b.findType(cArr, cArr2);
        return nameEnvironmentAnswerFindType == null ? this.c.findType(cArr, cArr2) : nameEnvironmentAnswerFindType;
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public boolean isPackage(char[][] cArr, char[] cArr2) {
        return this.b.isPackage(cArr, cArr2) || this.c.isPackage(cArr, cArr2);
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public NameEnvironmentAnswer findType(char[][] cArr) {
        cArr.getClass();
        NameEnvironmentAnswer nameEnvironmentAnswerFindType = this.b.findType(cArr);
        return nameEnvironmentAnswerFindType == null ? this.c.findType(cArr) : nameEnvironmentAnswerFindType;
    }
}
