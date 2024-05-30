package tui.view;

import model.Subscription;

import java.time.LocalDateTime;
import java.time.Period;

public class SubscriptionView extends View{
    public void printSubscriptionMenu() {
        System.out.println("\n--- Подписки ---");
        System.out.println("1) Текущая подписка");
        System.out.println("2) Все подписки");
        System.out.println("3) Сменить подписку");
        System.out.println("0) Назад");
    }

    public void printAllSubscriptions(Subscription subscription, int i) {
        Period period = subscription.getSubscriptionPeriod();
        String periodStr;
        if (period.getYears() > 100)
            periodStr = "Бессрочно";
        else
            periodStr = period.toString();
        System.out.printf("%d)%s\n\tСтоимость: %d\n\tСкидка: %.2f%%\n\tЛьготные часы: %d\n\tСрок подписки: %s\n",
                i, subscription.getTitle(), subscription.getCost(), subscription.getDiscount() * 100,
                subscription.getGraceHours(), periodStr);
    }

    public void printCurrentSubscription(Subscription subscription, LocalDateTime dateExpired) {
        String expiration  = (dateExpired.getYear() > 2100) ? "Бессрочно" : dateExpired.toString();
        System.out.print("Ваша текущая подписка: ");
        System.out.printf("""
                        %s
                        \tСтоимость: %d
                        \tСкидка: %.2f%%
                        \tЛьготные часы: %d
                        \tДата завершения подписки: %s
                        """,
                subscription.getTitle(), subscription.getCost(), subscription.getDiscount() * 100,
                subscription.getGraceHours(), expiration);
    }

    public void printSuccessNewSubscription() {
        System.out.println("Подписка успешно приобретена");
    }

    public void printNonSuccessNewSubscription() {
        System.out.println("На Вашем счету недостаточно средств для покупки");
    }
}
