package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.MissingDefinitionInfo;
import com.android.tools.r8.diagnostic.MissingDefinitionsDiagnostic;
import com.android.tools.r8.diagnostic.internal.m;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class m implements MissingDefinitionsDiagnostic {
    public static final /* synthetic */ boolean c = true;
    public final Collection b;

    public m(AbstractC0551Hu abstractC0551Hu) {
        if (c || !abstractC0551Hu.isEmpty()) {
            this.b = abstractC0551Hu;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static /* synthetic */ void a(StringBuilder sb, MissingDefinitionInfo missingDefinitionInfo) {
        sb.append(System.lineSeparator());
        k.a(sb, missingDefinitionInfo);
    }

    @Override // com.android.tools.r8.Diagnostic
    public final String getDiagnosticMessage() {
        final StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList(this.b);
        arrayList.sort(k.a);
        Iterator it = arrayList.iterator();
        if (!c && !it.hasNext()) {
            x1f.a();
            return null;
        }
        k.a(sb, (MissingDefinitionInfo) it.next());
        it.forEachRemaining(new Consumer() { // from class: jkh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                m.a(sb, (MissingDefinitionInfo) obj);
            }
        });
        return sb.toString();
    }

    @Override // com.android.tools.r8.diagnostic.MissingDefinitionsDiagnostic
    public final Collection getMissingDefinitions() {
        ArrayList arrayList = new ArrayList(this.b);
        arrayList.sort(k.a);
        return arrayList;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Origin getOrigin() {
        return Origin.unknown();
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Position getPosition() {
        return Position.UNKNOWN;
    }
}
