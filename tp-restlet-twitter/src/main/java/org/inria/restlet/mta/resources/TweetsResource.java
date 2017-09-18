package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Resource exposing the users
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class TweetsResource extends ServerResource
{

    /** Backend. */
    private Backend backend_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public TweetsResource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes()
            .get("backend");
    }

    /**
     *
     * Returns the list of all the users
     *
     * @return  JSON representation of the users
     * @throws JSONException
     */
    @Get("json")
    public Representation getTweets() throws JSONException
    {
        //searches the user in the database
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        User user_ = backend_.getDatabase().getUser(userId);

        Collection<Tweet> tweets = user_.getTweets();
        Collection<JSONObject> jsonTweets = new ArrayList<JSONObject>();

        for (Tweet tweet : tweets)
        {
            JSONObject current = new JSONObject();
            current.put("tweetText", tweet.getTweetText());
            jsonTweets.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonTweets);
        return new JsonRepresentation(jsonArray);
    }

    @Post("json")
    public Representation createTweet(JsonRepresentation representation)
        throws Exception
    {
        JSONObject object = representation.getJsonObject();
        String tweetText = object.getString("tweetText");

        // Save the tweet
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        User user_ = backend_.getDatabase().getUser(userId);
        Tweet newTweet = new Tweet(tweetText);
        user_.getTweets().add(newTweet);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("tweetText", newTweet.getTweetText());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }

}