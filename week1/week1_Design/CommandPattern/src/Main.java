public class Main {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room");
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(lightOn);
        remoteControl.pressButton();
        remoteControl.setCommand(lightOff);
        remoteControl.pressButton();
    }
}
