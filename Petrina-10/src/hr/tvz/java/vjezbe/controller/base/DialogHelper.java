package hr.tvz.java.vjezbe.controller.base;

import org.controlsfx.dialog.Dialogs;

@SuppressWarnings("deprecation")
public class DialogHelper {

	public static void DatabaseError() {
		Dialogs.create()
        .title("Database Error")
        .masthead("Database Error")
        .message("Dogodila se greska prilikom pristupanja bazi podataka!")
        .showError();
	}

}
