package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1980l90 extends YI implements InterfaceC1439er {
    public static final C1980l90 c = new C1980l90();

    public C1980l90() {
        super(1);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        W50 w50 = (W50) obj;
        KB.c(w50, "it");
        String str = w50.d;
        AbstractC0551Hu abstractC0551Hu = Qa0.a;
        str.getClass();
        boolean z = true;
        switch (str) {
            case "gcm_defaultSenderId":
            case "google_storage_bucket":
            case "google_crash_reporting_api_key":
            case "google_app_id":
            case "firebase_database_url":
            case "ga_trackingID":
            case "default_web_client_id":
            case "google_api_key":
                break;
            default:
                z = false;
                break;
        }
        return Boolean.valueOf(z);
    }
}
