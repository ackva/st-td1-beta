package org.inria.restlet.mta.internals;

/**
 *
 * User
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class Tweet
{

    /** Internal id of the tweet.*/
    //private int tweetId_;

    /** String of Tweet Text.*/
    private String tweetText_;

    /** Date of the tweet (20170917 for 17/09/2017.*/
    //private int tweetDate_;


    public Tweet(String tweetText)
    {
        tweetText_ = tweetText;
     }

    public String getTweetText()
    {
        return tweetText_;
    }

    public void setTweetText(String tweetText)
    {
        tweetText_ = tweetText;
    }

    /**
    public int getTweetDate()
    {
        return tweetDate_;
    }

    public void setTweetDate(int tweetDate)
    {
        tweetDate_ = tweetDate;
    }

    public int getTweetId()
    {
        return tweetId_;
    }

    public void setId(int tweetId)
    {
        tweetId_ = tweetId;
    }
    */

}
