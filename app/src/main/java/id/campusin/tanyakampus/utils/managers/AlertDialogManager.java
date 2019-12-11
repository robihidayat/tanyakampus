package id.campusin.tanyakampus.utils.managers;

import androidx.appcompat.app.AlertDialog;

import android.app.Dialog;
import android.content.Context;

import id.campusin.tanyakampus.R;

public class AlertDialogManager {

    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     *               - pass null if you don't want icon
     * */
    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton(Dialog.BUTTON_POSITIVE,"OK", (dialog, which) -> {

        });

        // Showing Alert Message
        alertDialog.show();
    }

}
