package br.iface.entities.relationships;

import br.iface.entities.PostMessage;
import br.iface.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserData extends User {
    private List<User> friends_requests;
    private List<User> my_friends;
    private List<MessageFeed> chats;
    private List<CommunityFeed> communities;
    private List<PostMessage> posts;
    private UserFeed personal_feed;

    public UserData(){}

    public UserData(String login, String password, String name){
        super(login, password, name);

        this.friends_requests = new ArrayList();
        this.my_friends = new ArrayList();
        this.chats = new ArrayList();
        this.communities = new ArrayList();
        this.posts = new ArrayList();
        this.personal_feed = new UserFeed();
    }


    /*Friendship Data Management*/
    public List<User> getFriendsRequests() {
        return this.friends_requests;
    }

    public void setAFriendsRequest(User friend) {
        this.friends_requests.add(friend);
    }

    public void removeFriendRequest(User friend){
        this.friends_requests.remove(friend);
    }

    public List<User> getMyfriends() {
        return this.my_friends;
    }

    public void setNewFriend(User friend_username) {
        this.my_friends.add(friend_username);
    }
    public void removeAFriend(User friend_username){
        this.my_friends.remove(friend_username);
    }

    /*Message Service Data Management*/
    public List<MessageFeed> getChats(){
        return this.chats;
    }

    public void setNewChat(MessageFeed messageFeed){
        this.chats.add(messageFeed);
    }

    public void removeChat(MessageFeed messageFeed){
        this.chats.remove(messageFeed);
    }

    /*Community Data Management*/
    public List<CommunityFeed> getCommunities() {
        return this.communities;
    }

    public void setNewCommunity(CommunityFeed new_com) {
        this.communities.add(new_com);
    }

    public void removeFromCommunity(CommunityFeed com) {
        this.communities.remove(com);
    }

    /*Personal Feed Data Management*/
    public List<PostMessage> getMyPosts() {
        return this.posts;
    }

    public void setNewPost(PostMessage newPostMessage) {
        this.posts.add(newPostMessage);
    }

    public UserFeed getMyPrivateFeed() {
        return this.personal_feed;
    }

    /*All Data Reset*/
    public void deleteAllInfo(){
        setLogin(null);
        setPass(null);
        setName(null);

        this.friends_requests = null;
        this.my_friends = null;
        this.chats = null;
        this.communities = null;
        this.posts = null;
        this.personal_feed = null;
    }

}
