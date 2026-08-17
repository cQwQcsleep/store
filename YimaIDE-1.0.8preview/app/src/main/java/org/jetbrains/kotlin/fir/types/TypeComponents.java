package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/TypeComponents;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "getTypeContext", "()Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "typeApproximator", "Lorg/jetbrains/kotlin/fir/types/ConeTypeApproximator;", "getTypeApproximator", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeApproximator;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class TypeComponents implements FirSessionComponent {
    private final FirSession session;
    private final ConeTypeApproximator typeApproximator;
    private final ConeInferenceContext typeContext;

    public TypeComponents(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        ConeInferenceContext coneInferenceContext = new ConeInferenceContext() { // from class: org.jetbrains.kotlin.fir.types.TypeComponents$typeContext$1
            public FirSession getSession() {
                return this.this$0.getSession();
            }
        };
        this.typeContext = coneInferenceContext;
        this.typeApproximator = new ConeTypeApproximator(coneInferenceContext, FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession));
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final ConeTypeApproximator getTypeApproximator() {
        return this.typeApproximator;
    }

    public final ConeInferenceContext getTypeContext() {
        return this.typeContext;
    }
}
