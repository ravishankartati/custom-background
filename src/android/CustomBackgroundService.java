package com.durity.ravi.background;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import 	android.content.Context;


/**
 * This class echoes a string called from JavaScript.
 */
public class CustomBackgroundService extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        Context context=this.cordova.getActivity().getApplicationContext(); 
        if (message != null && message.length() > 0) {
            Intent intent = new Intent(context, UploadFileService.class);
            context.startService(intent);
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
