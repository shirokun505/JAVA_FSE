public class Main {
    public static void main(String[] args) {
        Computer gamingComputer = new Computer.Builder("Intel i9", "32GB")
            .setStorage("1TB SSD")
            .setGraphicsCard(true)
            .setBluetooth(true)
            .build();

        Computer officeComputer = new Computer.Builder("Intel i5", "16GB")
            .build();

        System.out.println("Gaming Computer: " + gamingComputer);
        System.out.println("Office Computer: " + officeComputer);
    }
}
