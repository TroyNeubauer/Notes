package com.fish.notes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.pay.Information;
import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.PurchaseSystem;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.fish.core.NotesConstants;
import com.fish.core.notes.Account;
import com.fish.core.notes.Core;
import com.fish.core.notes.LoginResult;
import com.fish.core.notes.Post;
import com.fish.core.notes.PostDataImage;
import com.fish.core.notes.PostDataText;
import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;


public class Notes extends Game {
	public static Skin skin;
	public static GDXDialogs dialogs;
	public static Account account;
    public static PurchaseManagerConfig purchaseManagerConfig;
    public static PurchaseObserver purchaseObserver;
    public static PlatformResolver m_platformResolver;
    public static ScreensManager screensManager;
    public static OpenGallery gallery;
    public static ApplicationAdapter adapt;

    public Notes(OpenGallery gallery)
    {
        this.gallery = gallery;
    }
	@Override
	public void create () {
        dialogs = GDXDialogsSystem.install();
		skin = new Skin(Gdx.files.internal("default.json"));
        addCreators();
        account = new Account(12546772, "testuser", new byte[0], "testeremail@tester.com");
        screensManager = new ScreensManager(this);
        setScreen(new MakeScreen(this));

        purchaseManagerConfig = new PurchaseManagerConfig();
        purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(NotesConstants.FIVE_COIN_ID));
        purchaseObserver = new PurchaseObserver()
        {
            @Override
            public void handleRestore (Transaction[] transactions) {
                for (int i = 0; i < transactions.length; i++) {
                    if (checkTransaction(transactions[i].getIdentifier()) == true) break;
                }
            }

            @Override
            public void handleRestoreError (Throwable e) {
                //getPlatformResolver().showToast("PurchaseObserver: handleRestoreError!");
                Gdx.app.log("ERROR", "PurchaseObserver: handleRestoreError!: " + e.getMessage());
                throw new GdxRuntimeException(e);
            }

            @Override
            public void handleInstall () {
                // getPlatformResolver().showToast("PurchaseObserver: installed successfully...");
                Gdx.app.log("handleInstall: ", "successfully..");
            }

            @Override
            public void handleInstallError (Throwable e) {
                // getPlatformResolver().showToast("PurchaseObserver: handleInstallError!");
                Gdx.app.log("ERROR", "PurchaseObserver: handleInstallError!: " + e.getMessage());
                throw new GdxRuntimeException(e);
            }

            @Override
            public void handlePurchase (Transaction transaction) {
                checkTransaction(transaction.getIdentifier());
            }

            @Override
            public void handlePurchaseError (Throwable e) {
                if (e.getMessage().equals("There has been a Problem with your Internet connection. Please try again later")) {

                    // this check is needed because user-cancel is a handlePurchaseError too)
                    // getPlatformResolver().showToast("handlePurchaseError: " + e.getMessage());
                }
                throw new GdxRuntimeException(e);
            }

            @Override
            public void handlePurchaseCanceled () {
            }
        };
        PurchaseSystem.install(purchaseObserver, purchaseManagerConfig);
        PurchaseSystem.purchase(NotesConstants.FIVE_COIN_ID);
        //Information information = PurchaseSystem.getInformation(NotesConstants.FIVE_COIN_ID);

        getPlatformResolver().requestPurchaseRestore();
        
        Backend.init();
    }

    public  PlatformResolver getPlatformResolver()
    {
        return m_platformResolver;
    }

    //checks if transaction was made, if true, returns back to shop screen
    protected boolean checkTransaction (String ID) {
        boolean returnbool = false;
        if (NotesConstants.ONE_COIN_ID.equals(ID)) {
            returnbool = true;
        }
        else if(NotesConstants.FIVE_COIN_ID.equals(ID))
        {
            returnbool = true;
        }
        else if(NotesConstants.TWENTY_COIN_ID.equals(ID))
        {
            returnbool = true;
        }
        return returnbool;
    }

	public static void showDialog(String title, String message) {
        final GDXButtonDialog warningDialog = dialogs.newDialog(GDXButtonDialog.class);
        warningDialog.setTitle(title).setMessage(message);
        warningDialog.addButton("Ok");

        warningDialog.setClickListener(new ButtonClickListener() {
            @Override
            public void click(int button) {
                warningDialog.dismiss();
            }
        });
        warningDialog.build().show();
    }

    private void addCreators() {
        Core.creatorMap.put(PostDataImage.class, new Core.Creator() {
            @Override
            public Object create(Post post) {
                return new BaseActor(post){
                    @Override
                    public void addImpl(Table table) {

                    }
                };
            }
        });
        Core.creatorMap.put(PostDataText.class, new Core.Creator() {
            @Override
            public Object create(Post post) {
                return new BaseActor(post){
                    @Override
                    public void addImpl(Table table) {

                    }
                };
            }
        });
    }

    public static boolean validateResult(LoginResult result, String title) {
        if(result == Backend.DISCONNECTED_FROM_SERVER) {
            Notes.showDialog("No connection", "Check your internet connection or try again later!");
        } else {
            if(result == null) {
                Notes.showDialog("Invalid server request!", "");
            } else if (result.isSuccess()) {
                return true;
            } else {
                Notes.showDialog(title, result.getMessage());
            }
        }
        return false;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	    super.render();
    }

    static abstract class BaseActor extends HorizontalGroup {

	    private Post post;
	    private Texture texture;

        public BaseActor(Post post) {
            this.post = post;
            byte[] jpeg = Backend.getAccount(post.getPosterUserID()).getProfilePic();
            Table left = new Table();

            left.add(new Label("", skin));


            Table right = new Table();
            addImpl(right);

            this.addActor(left);
            this.addActor(right);
        }

        public abstract void addImpl(Table table);
    }
    public static void setPlatformResolver (PlatformResolver platformResolver) {
        m_platformResolver = platformResolver;
    }

    public static Texture getPics(String path)
    {
        return new Texture(path);
    }
}
