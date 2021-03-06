package systems.llau.jaws.LLau;

import android.content.Context;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import systems.llau.jaws.Base.LLSendable;

/**
 * Created by pp on 12/31/15.
 */
public class LLContact extends SugarRecord implements LLTarget, LLSendable
{
    private String firstName;                   /** The first name */
    private String lastName;                    /** The last name */
    private LLOrganization parentOrganization;  /** Organization he belongs to */


    public void updateWithJSON(JSONObject json)
    {
        try
        {
            this.firstName = json.getString("firstName");
            this.lastName = json.getString("lastName");
            this.parentOrganization.updateWithJSON(json.getJSONObject("parent"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public JSONObject toJSON()
    {
        JSONObject o = null;

        try
        {
            o = new JSONObject();
            o.put("firstName", this.firstName);
            o.put("lastName",  this.lastName);
            o.put("parent",    this.parentOrganization.toJSON()); // Nesting JSON :)
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return o;
    }

    /**
     * Custom constructor
     * @param first The first name
     * @param last  The last name
     * @param org   The parent organization
     */
    public LLContact(String first, String last, LLOrganization org)
    {
        this.firstName      = first;
        this.lastName       = last;
        this.parentOrganization = org;
    }

    /**
     * Indicates the Type of target
     * @return The target type enum
     */
    public LLTargetType getType()
    {
        return LLTargetType.LL_TARGET_CONTACT;
    }

    /**
     * Returns a list of all available actions
     * @return A list of actions
     */
    public List<LLAction> getAvailableActions()
    {
        return new ArrayList();
    }

    /**
     * A name for displaying this in a list
     * @param c The context
     * @return A localized string representing the object
     */
    public String getDisplayName(Context c)
    {
        return this.firstName + " " + this.lastName;
    }

    /**
     * The contact's name
     * @return The contact's name
     */
    public String getName()
    {
        return this.firstName + " " + this.lastName;
    }

}
