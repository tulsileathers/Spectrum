package apps.jayceleathers.me.spectrum.Data;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * Created by Jayce on 12/5/14.
 */
@ParseClassName("_User")
public class SpectrumUser extends ParseUser {
    public SpectrumUser(){
        super();
    }

    public int getGenderBiological() {
        return getInt("genderBiological");
    }

    public void setGenderBiological(int genderBiological) {
        put("genderBiological", genderBiological);
    }

    public int getGenderIdentity() {
        return getInt("genderIdentity");
    }

    public void setGenderIdentity(int genderIdentity) {
        put("genderIdentity", genderIdentity);
    }

    public int getGenderExpression() {
        return getInt("genderExpression");
    }

    public void setGenderExpression(int genderExpression) {
        put("genderExpression", genderExpression);
    }

    public int getSexualIdentity() {
        return getInt("sexualIdentity");
    }

    public void setSexualIdentity(int sexualIdentity) {
        put("sexualIdentity",sexualIdentity);
    }

    public int getMatchGenderBiological() {
        return getInt("matchGenderBiological");
    }

    public void setMatchGenderBiological(int matchGenderBiological) {
        put("matchGenderBiological", matchGenderBiological);
    }

    public int getMatchGenderIdentity() {
        return getInt("matchGenderIdentity");
    }

    public void setMatchGenderIdentity(int matchGenderIdentity) {
        put("matchGenderIdentity", matchGenderIdentity);
    }

    public int getMatchGenderExpression() {
        return getInt("matchGenderExpression");
    }

    public void setMatchGenderExpression(int matchGenderExpression) {
        put("matchGenderExpression", matchGenderExpression);
    }

    public int getMatchSexualIdentity() {
        return getInt("matchSexualIdentity");
    }

    public void setMatchSexualIdentity(int matchSexualIdentity) {
        put("matchSexualIdentity",matchSexualIdentity);
    }

    public ParseFile getProfPic() {
        return getParseFile("profPic");
    }

    public void setProfPic(ParseFile profPic) {
        put("profPic", profPic);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }
}
