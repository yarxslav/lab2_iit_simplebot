package ua.kpi.iit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws TelegramApiException {

        if (args == null || args.length != 2) {
            log.info("You must run bot with 2 args - BotToken and bot UserName");
        } else {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            try {
                log.info("Registering bot...");
                telegramBotsApi.registerBot(new BestBot(args[0], args[1]));
                telegramBotsApi.registerBot(new BestBot("TOKEN", "BOT_NAME"));
            } catch (TelegramApiRequestException e) {
                log.error("Failed to register bot(check internet connection / bot token or make sure only one instance of bot is running).", e);
            }
            log.info("Telegram bot is ready to accept updates from user......");
        }
    }

}
