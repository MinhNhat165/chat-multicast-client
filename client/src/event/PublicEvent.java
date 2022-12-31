package event;

public class PublicEvent {

    private static PublicEvent instance;
    private EventImageView eventImageView;
    private EventChatBody eventChatBody;
    private EventUser eventUser;
    private EventMainChat eventMainChat;
    private EventMenuRight eventMenuRight;
    private EventSidebarRight eventSidebarRight;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }

    public void addEventImageView(EventImageView event) {
        this.eventImageView = event;
    }
    public void addEventUser(EventUser event) {
        this.eventUser = event;}


    public void addEventMainChat(EventMainChat event) {
        this.eventMainChat = event;
    }
    public void  addEventMenuRight(EventMenuRight event) {this.eventMenuRight = event;}

    public void  addEventChatBody(EventChatBody event) {
        this.eventChatBody = event;
    }
    public void addEventSidebarRight(EventSidebarRight event){
        this.eventSidebarRight = event;
    }

    public EventImageView getEventImageView() {
        return eventImageView;
    }

    public EventChatBody getEventChatBody() {
        return  eventChatBody;
    }

    public EventUser getEventUser() {
        return eventUser;
    }

    public EventMainChat getEventMainChat() {
        return  eventMainChat;
    }
    public EventMenuRight getEventMenuRight(){return  eventMenuRight;}
    public EventSidebarRight getEventSidebarRight() {
        return  eventSidebarRight;
    }
}
