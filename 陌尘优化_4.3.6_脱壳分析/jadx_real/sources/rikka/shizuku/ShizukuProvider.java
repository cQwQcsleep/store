package rikka.shizuku;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import moe.shizuku.api.BinderContainer;
import obfuse.NPStringFog;
import rikka.sui.Sui;

/* loaded from: /workspace/unpacked/classes.dex */
public class ShizukuProvider extends ContentProvider {
    public static final String ACTION_BINDER_RECEIVED = "moe.shizuku.api.action.BINDER_RECEIVED";
    private static final String EXTRA_BINDER = "moe.shizuku.privileged.api.intent.extra.BINDER";
    public static final String MANAGER_APPLICATION_ID = "moe.shizuku.privileged.api";
    public static final String METHOD_GET_BINDER = "getBinder";
    public static final String METHOD_SEND_BINDER = "sendBinder";
    public static final String PERMISSION = "moe.shizuku.manager.permission.API_V23";
    private static final String TAG = "ShizukuProvider";
    private static boolean enableMultiProcess = false;
    private static boolean isProviderProcess = false;
    private static boolean enableSuiInitialization = true;

    public static void disableAutomaticSuiInitialization() {
        enableSuiInitialization = false;
    }

    public static void enableMultiProcessSupport(boolean z) {
        Log.d(NPStringFog.decode("3D18041B1B0A123500010604050B13"), NPStringFog.decode("2B1E0C030204470707071C194C070F4708070204044C1E130806171D034D121B11170A001A5045071C0E0A45") + (z ? NPStringFog.decode("1E02021707050217521E0202020B1214") : NPStringFog.decode("001F034C1E1308131B0A151F411E130806171D03")) + NPStringFog.decode("47"));
        isProviderProcess = z;
        enableMultiProcess = true;
    }

    private boolean handleGetBinder(Bundle bundle) {
        IBinder binder = Shizuku.getBinder();
        if (binder == null || !binder.pingBinder()) {
            return false;
        }
        bundle.putParcelable(NPStringFog.decode("031F084F1D090E1F07050543111C08110C1E0B1708054000170C5C071E1904001549000A1A020C4F2C282921373C"), new BinderContainer(binder));
        return true;
    }

    private void handleSendBinder(Bundle bundle) {
        boolean zPingBinder = Shizuku.pingBinder();
        String strDecode = NPStringFog.decode("3D18041B1B0A123500010604050B13");
        if (zPingBinder) {
            Log.d(strDecode, NPStringFog.decode("1D1503052C080901171C5004124E0206091E0B144D160604094513020208000A1847045202191B08000647071B00140813"));
            return;
        }
        String strDecode2 = NPStringFog.decode("031F084F1D090E1F07050543111C08110C1E0B1708054000170C5C071E1904001549000A1A020C4F2C282921373C");
        BinderContainer binderContainer = (BinderContainer) bundle.getParcelable(strDecode2);
        if (binderContainer == null || binderContainer.binder == null) {
            return;
        }
        Log.d(strDecode, NPStringFog.decode("0C1903050B134717170D1504170B05"));
        Shizuku.onBinderReceived(binderContainer.binder, getContext().getPackageName());
        if (enableMultiProcess) {
            Log.d(strDecode, NPStringFog.decode("0C0202000A020616064E12040F0A0415"));
            getContext().sendBroadcast(new Intent(NPStringFog.decode("031F084F1D090E1F07050543001E084904111A19020F40232E2B362B2232332B22222C242B34")).putExtra(strDecode2, binderContainer).setPackage(getContext().getPackageName()));
        }
    }

    public static void requestBinderForNonProviderProcess(Context context) {
        Bundle bundleCall;
        if (isProviderProcess) {
            return;
        }
        Log.d(TAG, NPStringFog.decode("1C151C140B12134510071E09041C410E0B52001F034C1E1308131B0A151F411E130806171D03"));
        context.registerReceiver(new BroadcastReceiver() { // from class: rikka.shizuku.ShizukuProvider.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                BinderContainer binderContainer = (BinderContainer) intent.getParcelableExtra(NPStringFog.decode("031F084F1D090E1F07050543111C08110C1E0B1708054000170C5C071E1904001549000A1A020C4F2C282921373C"));
                if (binderContainer == null || binderContainer.binder == null) {
                    return;
                }
                Log.i(NPStringFog.decode("3D18041B1B0A123500010604050B13"), "binder received from broadcast");
                Shizuku.onBinderReceived(binderContainer.binder, context2.getPackageName());
            }
        }, new IntentFilter(NPStringFog.decode("031F084F1D090E1F07050543001E084904111A19020F40232E2B362B2232332B22222C242B34")));
        try {
            bundleCall = context.getContentResolver().call(Uri.parse(NPStringFog.decode("0D1F03150B0F135F5D41") + context.getPackageName() + NPStringFog.decode("4003050814140C10")), NPStringFog.decode("09151923070F030000"), (String) null, new Bundle());
        } catch (Throwable th) {
            bundleCall = null;
        }
        if (bundleCall != null) {
            bundleCall.setClassLoader(BinderContainer.class.getClassLoader());
            BinderContainer binderContainer = (BinderContainer) bundleCall.getParcelable(NPStringFog.decode("031F084F1D090E1F07050543111C08110C1E0B1708054000170C5C071E1904001549000A1A020C4F2C282921373C"));
            if (binderContainer == null || binderContainer.binder == null) {
                return;
            }
            Log.i(TAG, NPStringFog.decode("2C1903050B134717170D1504170B05470300011D4D0E1A090217521E0202020B1214"));
            Shizuku.onBinderReceived(binderContainer.binder, context.getPackageName());
        }
    }

    public static void setIsProviderProcess(boolean z) {
        isProviderProcess = z;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.multiprocess) {
            throw new IllegalStateException(NPStringFog.decode("0F1E09130108035F1F1B1C19081E130806171D034D0C1B121345100B500B00021202"));
        }
        if (!providerInfo.exported) {
            throw new IllegalStateException(NPStringFog.decode("0F1E09130108035F17160002131A0403451F1B0319410C044711001B15"));
        }
        isProviderProcess = true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005d A[RETURN] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Bundle call(String str, String str2, Bundle bundle) {
        char c;
        if (Sui.isSui()) {
            Log.w(NPStringFog.decode("3D18041B1B0A123500010604050B13"), "Provider called when Sui is available. Are you using Shizuku and Sui at the same time?");
            return new Bundle();
        }
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(BinderContainer.class.getClassLoader());
        Bundle bundle2 = new Bundle();
        switch (str.hashCode()) {
            case -11990190:
                if (!str.equals(NPStringFog.decode("1D1503052C080901171C"))) {
                    c = 65535;
                    break;
                } else {
                    c = 0;
                    break;
                }
            case 307050656:
                if (str.equals(NPStringFog.decode("09151923070F030000"))) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                handleSendBinder(bundle);
                return bundle2;
            case 1:
                if (handleGetBinder(bundle2)) {
                    return bundle2;
                }
                return null;
        }
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (!enableSuiInitialization || Sui.isSui()) {
            return true;
        }
        Log.d(NPStringFog.decode("3D18041B1B0A123500010604050B13"), NPStringFog.decode("271E041507000B0C080B503E14075B47") + Sui.init(getContext().getPackageName()));
        return true;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
