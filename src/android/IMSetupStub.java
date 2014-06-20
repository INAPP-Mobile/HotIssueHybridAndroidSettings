package kr.imapp.hybrid.imhotissue.settings;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

/**
 * This class echoes a string called from JavaScript.
 */
public class IMSetupStub extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("show")) {
            String message = args.getString(0);
            this.show(message, callbackContext);
            return true;
        }
        return false;
    }

    private void show(String message, CallbackContext callbackContext) {
//        if (message != null && message.length() > 0) {
//            callbackContext.success(message);
//        } else {
//            callbackContext.error("Expected one non-empty string argument.");
//        }
    	
    	Activity activity = this.cordova.getActivity();
        Intent intent = new Intent(activity,com.blogspot.imapp.imhotissue.IMSetup.class);
        activity.startActivity(intent);
    }
}
