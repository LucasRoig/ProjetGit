import java.text.SimpleDateFormat;
import java.util.Date;

import model.GitObject;

public abstract class ObjectDataController {
	
	public abstract void setDataContent(GitObject object);
	
	public String gitDateToString(String chaine){
		long sec = Integer.parseInt(chaine.substring(0, chaine.length()-6));
        Date date = new Date(sec * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM yyyy, h:mm a");
        String formattedDate = sdf.format(date);
        String timeZone = chaine.substring(chaine.length()-6, chaine.length()-2) + ":" + chaine.substring(chaine.length()-2, chaine.length());
		return (formattedDate + timeZone);
	}

}
