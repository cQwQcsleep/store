package com.android.tools.r8.references;

import com.android.tools.r8.DataResource;
import com.android.tools.r8.internal.C0929Wj;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PackageReference {
    private final String a;

    public PackageReference(String str) {
        if (str == null) {
            w01.a("Package name cannot be null.");
            throw null;
        }
        if (str.isEmpty() || C0929Wj.F(str)) {
            this.a = str;
        } else {
            kg9.a("Package name '", str, "' is not valid.");
            throw null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PackageReference) {
            return this.a.equals(((PackageReference) obj).a);
        }
        return false;
    }

    public String getPackageBinaryName() {
        return this.a.replace('.', DataResource.SEPARATOR);
    }

    public String getPackageName() {
        return this.a;
    }

    public int hashCode() {
        return Objects.hash(this.a);
    }
}
