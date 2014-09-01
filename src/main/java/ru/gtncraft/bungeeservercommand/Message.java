package ru.gtncraft.bungeeservercommand;

public enum Message {
    welcome_to_server {
        @Override
        public String toString() {
            return "&2Welcome to server &l%s!";
        }
    },
    you_must_supply_server {
        @Override
        public String toString() {
            return "&4You must supply server.";
        }
    },
    you_must_supply_player {
        @Override
        public String toString() {
            return "&4You must supply player.";
        }
    },
    player_not_found {
        @Override
        public String toString() {
            return "&4Player &l%s offline or never play on this server.";
        }
    }
}
