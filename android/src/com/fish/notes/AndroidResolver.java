package com.fish.notes;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.pay.PurchaseManagerConfig;

public class AndroidResolver extends PlatformResolver {

    private final static String GOOGLE_KEY = "..............................";
    static final int RC_REQUEST = 10001;	// (arbitrary) request code for the purchase flow

    public AndroidApplication androidApplication;
    public Notes notes;

    public AndroidResolver(Notes notes, AndroidApplication androidApplication) {
        super(notes);
        this.notes = notes;
        this.androidApplication = androidApplication;

        PurchaseManagerConfig config = Notes.purchaseManagerConfig;
        config.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, GOOGLE_KEY);

        initializeIAP(null, notes.purchaseObserver, config);
    }
}