public class Main {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        Notifier smsAndEmailNotifier = new SMSNotifierDecorator(emailNotifier);
        Notifier slackAndSMSAndEmailNotifier = new SlackNotifierDecorator(smsAndEmailNotifier);
        slackAndSMSAndEmailNotifier.send("Hello, world!");
    }
}
