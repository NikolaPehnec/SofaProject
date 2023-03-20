package sofascore.academy.sofaproject.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import sofascore.academy.sofaproject.R

object Functions {
    fun showSuccesSnackbarFighterAdded(context: Context, view: View) {
        Snackbar.make(
            view,
            context.getString(R.string.add_fighter_success),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun showErrorToastNewFighter(context: Context) {
        Toast.makeText(
            context,
            context.getString(R.string.add_fighter_error),
            Toast.LENGTH_SHORT
        ).show()
    }
}
