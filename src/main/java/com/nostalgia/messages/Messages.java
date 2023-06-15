package com.nostalgia.messages;

import java.util.HashMap;

public class Messages {

    public HashMap<Message, HashMap<String, String>> messages;// <MessageName, HashMap<Language, Message>>

    public Messages() {
        messages = new HashMap<>();
        initMessages();
    }

    public void initMessages() {
        initGlobalMessages();
    }

    public void initGlobalMessages() {
        messages.put(Message.GAME_COUNTDOWN, new HashMap<String, String>() {{
            put("en", "Game starting in %s seconds");
            put("fr", "La partie commence dans %s% secondes.");
        }});
        messages.put(Message.GAME_START, new HashMap<String, String>() {{
            put("en", "Game started");
            put("fr", "La partie commence.");
        }});
        messages.put(Message.NOT_ENOUGH_PLAYERS, new HashMap<String, String>() {{
            put("en", "Not enough players to start the game.");
            put("fr", "Pas assez de joueurs pour commencer la partie.");
        }});
        messages.put(Message.ELIMINATED_BY, new HashMap<String, String>() {{
            put("en", "You have been eliminated by %player%.");
            put("fr", "Vous avez été éliminé par %player%.");
        }});
        messages.put(Message.KILL_PLAYER, new HashMap<String, String>() {{
            put("en", "You killed %player%.");
            put("fr", "Vous avez éliminé %player.");
        }});
        messages.put(Message.SPECTATOR, new HashMap<String, String>() {{
            put("en", "You are now a spectator.");
            put("fr", "Vous êtes maintenant un spectateur.");
        }});
        messages.put(Message.PLAYER_JOIN, new HashMap<String, String>() {{
            put("en", "%player% joined the game.");
            put("fr", "%player% a rejoint la partie.");
        }});
    }

    public String get(Message message, String lang) {
        return messages.get(message).get(lang);
    }
}
