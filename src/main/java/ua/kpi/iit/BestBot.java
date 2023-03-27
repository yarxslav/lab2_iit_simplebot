package ua.kpi.iit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BestBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private final String token, botName;

    protected BestBot(String token, String botName) {
        this.token = token;
        this.botName = botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();

            Long userId = update.getMessage().getChatId();
            String userFirstName = update.getMessage().getFrom().getFirstName();

            log.info("[{}, {}] : {}", userId, userFirstName, textFromUser);

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(userId.toString())
                    .text("Привіт, " + userFirstName + "! \nЦе я і друга лаба по ІІТ :( \nЯк бачимо, Вагрант робіт, а ти написав: " + textFromUser)
                    .build();
            try {
                this.sendApiMethod(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Exception when sending message: ", e);
            }
        } else {
            log.warn("Unexpected update from user");
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }


}
