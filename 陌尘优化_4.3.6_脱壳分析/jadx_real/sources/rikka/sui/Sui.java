package rikka.sui;

import android.os.IBinder;
import android.os.Parcel;
import obfuse.NPStringFog;
import rikka.shizuku.Shizuku;
import rikka.shizuku.SystemServiceHelper;

/* loaded from: /workspace/unpacked/classes.dex */
public class Sui {
    private static final int BRIDGE_ACTION_GET_BINDER = 2;
    private static final String BRIDGE_SERVICE_DESCRIPTOR = "android.app.IActivityManager";
    private static final String BRIDGE_SERVICE_NAME = "activity";
    private static final int BRIDGE_TRANSACTION_CODE = 1599296841;
    private static boolean isSui;

    public static boolean init(String str) {
        IBinder iBinderRequestBinder = requestBinder();
        if (iBinderRequestBinder == null) {
            isSui = false;
            return false;
        }
        Shizuku.onBinderReceived(iBinderRequestBinder, str);
        isSui = true;
        return true;
    }

    public static boolean isSui() {
        return isSui;
    }

    private static IBinder requestBinder() {
        IBinder systemService = SystemServiceHelper.getSystemService(NPStringFog.decode("0F1319081808131C"));
        if (systemService == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken(NPStringFog.decode("0F1E09130108034B131E0043282F02130C040704142C0F0F0602171C"));
            parcelObtain.writeInt(2);
            systemService.transact(BRIDGE_TRANSACTION_CODE, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            IBinder strongBinder = parcelObtain2.readStrongBinder();
            if (strongBinder != null) {
                return strongBinder;
            }
        } finally {
            try {
                return null;
            } finally {
            }
        }
        return null;
    }
}
